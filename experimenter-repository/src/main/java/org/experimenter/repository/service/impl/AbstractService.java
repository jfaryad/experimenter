package org.experimenter.repository.service.impl;

import java.util.List;

import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.dao.JunctionDao;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.repository.service.ComputerService;
import org.experimenter.repository.service.ConnectionFarmService;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.InputService;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.repository.service.ProblemTypeService;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.repository.service.UserService;
import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractService<T extends Entity, D extends BaseDao<T>> {

    protected D baseDao;
    protected JunctionDao junctionDao;

    protected ApplicationService applicationService;
    protected ComputerService computerService;
    protected ConnectionService connectionService;
    protected ConnectionFarmService connectionFarmService;
    protected UserService userService;
    protected UserGroupService userGroupService;
    protected ProjectService projectService;
    protected ProgramService programService;
    protected ExperimentService experimentService;
    protected InputService inputService;
    protected InputSetService inputSetService;
    protected ProblemTypeService problemTypeService;

    public void saveUpdate(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("The entity to save must not be null.");
        if (entity.getId() == null)
            baseDao.insert(entity);
        else
            baseDao.update(entity);
    }

    public T findById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return baseDao.findById(id);
    }

    public List<T> findByExample(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("The example entity to search by must not be null.");
        return baseDao.findByCriteria(new CriteriaForm<T>(entity));
    }

    public List<T> findByCriteria(CriteriaForm<T> criteria) {
        if (criteria == null || criteria.getModel() == null)
            throw new IllegalArgumentException("The criteria or the model entity to search by must not be null.");
        return baseDao.findByCriteria(criteria);
    }

    public void delete(T entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException(
                    "The entity to delete must be an existing entity persisted in the database.");
        deleteDependencies(entity);
        baseDao.deleteById(entity.getId());
    }

    public void delete(List<T> entitys) {
        if (entitys == null)
            throw new IllegalArgumentException("The list of entities to delete must not be null.");
        for (T entity : entitys)
            delete(entity);
    }

    protected abstract void deleteDependencies(T entity);

    protected void checkNotNull(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The entity to search by must not be null.");
        }
    }

    protected void checkIdNotNull(Entity entity) {
        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("The entity to search by or it's id must not be null.");
        }
    }

    @Required
    public void setBaseDao(D baseDao) {
        this.baseDao = baseDao;
    }

    @Required
    public void setJunctionDao(JunctionDao junctionDao) {
        this.junctionDao = junctionDao;
    }

    @Required
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Required
    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    @Required
    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Required
    public void setConnectionFarmService(ConnectionFarmService connectionFarmService) {
        this.connectionFarmService = connectionFarmService;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Required
    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Required
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Required
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @Required
    public void setExperimentService(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @Required
    public void setInputService(InputService inputService) {
        this.inputService = inputService;
    }

    @Required
    public void setInputSetService(InputSetService inputSetService) {
        this.inputSetService = inputSetService;
    }

    @Required
    public void setProblemTypeService(ProblemTypeService problemTypeService) {
        this.problemTypeService = problemTypeService;
    }

}
