package org.experimenter.repository.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.experimenter.repository.dao.ResultDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultDaoImpl extends AbstractBaseDaoImpl<Result> implements ResultDao {

    protected final static Logger LOG = LoggerFactory.getLogger(ResultDaoImpl.class);

    @Override
    public Result findResultByExperimentInputParam(Integer experimentId, Integer inputId, String param) {
        LOG.debug(">> findResultByExperimentInputParam: experiment: " + experimentId + ", input: " + inputId
                + ", param: " + param);
        Result result = (Result) getSession().getNamedQuery(Result.Q_GET_BY_EXPERIMENT_INPUT_AND_PARAM)
                .setInteger("experimentId", experimentId)
                .setInteger("inputId", inputId)
                .setString("param", param)
                .uniqueResult();
        LOG.debug("<< findResultByExperimentInputParam:" + result);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Result> findResultsForExperiment(Experiment experiment) {
        LOG.debug(">> findResultsForExperiment: experimentId: " + experiment.getId());
        List<Result> list = getSession().getNamedQuery(Result.Q_GET_BY_EXPERIMENT)
                .setEntity("experiment", experiment)
                .list();
        LOG.debug("<< findResultsForExperiment: number found: " + list.size());
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findAllExperimentParameters(Collection<Integer> experimentIds) {
        LOG.debug(">> findAllExperimentParameters");
        if (experimentIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> list = getSession().getNamedQuery(Result.Q_GET_PARAMS_BY_EXPERIMENT_LIST)
                .setParameterList("experimentIds", experimentIds)
                .list();
        LOG.debug("<< findAllExperimentParameters: number found: " + list.size());
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> findResultsForParam(String parameter, Collection<Integer> experimentIds) {
        LOG.debug(">> findResultsForParam");
        if (experimentIds.isEmpty() || parameter == null || parameter.isEmpty()) {
            return Collections.emptyList();
        }
        List<Result> list = getSession().getNamedQuery(Result.Q_GET_RESULTS_FOR_PARAM)
                .setParameterList("experimentIds", experimentIds)
                .setString("param", parameter)
                .list();
        LOG.debug("<< findResultsForParam: number found: " + list.size());
        return list;
    }

    @Override
    protected void removeFromAssociations(Result item) {
        // do nothing
    }

    @Override
    public Class<Result> getEntityClass() {
        return Result.class;
    }

}
