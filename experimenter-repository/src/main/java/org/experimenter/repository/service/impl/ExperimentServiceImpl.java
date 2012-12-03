package org.experimenter.repository.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.experimenter.repository.dao.ExperimentDao;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Experiment.Status;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.service.ExperimentService;

public class ExperimentServiceImpl extends AbstractService<Experiment, ExperimentDao> implements ExperimentService {

    @Override
    public void saveUpdate(Experiment experiment) {
        checkNotNull(experiment);
        super.saveUpdate(experiment);
        resetExperimentStatus(experiment);
    }

    @Override
    protected void deleteDependencies(Experiment experiment) {
        resultService.delete(experiment.getResults());
        deleteExperimentStatus(experiment);
    }

    @Override
    public List<Experiment> findExperimentsByApplication(Application application) {
        checkIdNotNull(application);
        return application.getExperiments();
    }

    @Override
    public List<Experiment> findExperimentsByConnectionFarm(ConnectionFarm connectionFarm) {
        checkIdNotNull(connectionFarm);
        return connectionFarm.getExperiments();
    }

    @Override
    public List<Experiment> findExperimentsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return inputSet.getExperiments();
    }

    @Override
    public List<Experiment> findExperimentsByUser(User user) {
        checkIdNotNull(user);
        return baseDao.findExperimentsByUser(user);
    }

    @Override
    protected boolean hasDependencies(Experiment experiment) {
        return false;
    }

    @Override
    public List<Experiment> findScheduledExperiments() {
        return baseDao.findScheduledExperiments();
    }

    @Override
    public synchronized boolean setExperimentStarted(Experiment experiment) {
        checkIdNotNull(experiment);
        Integer status = (Integer) getSession().createSQLQuery(Experiment.NATIVE_Q_GET_STATUS)
                .setInteger("experimentId", experiment.getId()).uniqueResult();
        if (status == null) {
            getSession().createSQLQuery(Experiment.NATIVE_CREATE_JOB)
                    .setInteger("experimentId", experiment.getId())
                    .executeUpdate();
        } else if (!Experiment.Status.NEW.hasValue(status)) {
            return false;
        }
        getSession().createSQLQuery(Experiment.NATIVE_START_JOB)
                .setInteger("experimentId", experiment.getId())
                .executeUpdate();

        return true;
    }

    @Override
    public Status getExperimentStatus(Experiment experiment) {
        checkIdNotNull(experiment);
        return Status.fromValue((Integer) getSession().createSQLQuery(Experiment.NATIVE_Q_GET_STATUS)
                .setInteger("experimentId", experiment.getId()).uniqueResult());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, Status> getExperimentListStatus(List<Experiment> experiments) {
        List<Integer> ids = new ArrayList<Integer>();
        for (Experiment experiment : experiments) {
            if (experiment != null && experiment.getId() != null) {
                ids.add(experiment.getId());
            }
        }
        if (ids.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<Integer, Status> resultMap = new HashMap<Integer, Experiment.Status>();
        List<Object[]> result = getSession().createSQLQuery(Experiment.NATIVE_Q_GET_STATUS_LIST)
                .setParameterList("experimentIds", ids).list();
        for (Object[] row : result) {
            resultMap.put((Integer) row[0], (Status.fromValue((Integer) row[1])));
        }
        return resultMap;
    }

    @Override
    public synchronized boolean setExperimentFinished(Experiment experiment) {
        checkIdNotNull(experiment);
        Integer status = (Integer) getSession().createSQLQuery(Experiment.NATIVE_Q_GET_STATUS)
                .setInteger("experimentId", experiment.getId()).uniqueResult();
        if (!Experiment.Status.RUNNING.hasValue(status)) {
            return false;
        }
        getSession().createSQLQuery(Experiment.NATIVE_FINISH_JOB)
                .setInteger("experimentId", experiment.getId())
                .executeUpdate();
        return true;
    }

    @Override
    public List<Experiment> findFinishedExperimentsByUser(User user) {
        checkIdNotNull(user);
        List<Experiment> allExperiments = baseDao.findExperimentsByUser(user);
        Map<Integer, Status> statuses = getExperimentListStatus(allExperiments);
        List<Experiment> resultList = new ArrayList<Experiment>();
        for (Experiment experiment : allExperiments) {
            Status status = statuses.get(experiment.getId());
            if (status != null && status.equals(Status.FINISHED)) {
                resultList.add(experiment);
            }
        }
        return resultList;
    }

    private synchronized void resetExperimentStatus(Experiment experiment) {
        getSession().createSQLQuery(Experiment.NATIVE_RESET_JOB)
                .setInteger("experimentId", experiment.getId())
                .executeUpdate();
    }

    private synchronized void deleteExperimentStatus(Experiment experiment) {
        getSession().createSQLQuery(Experiment.NATIVE_DELETE_JOB)
                .setInteger("experimentId", experiment.getId())
                .executeUpdate();
    }

}
