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

public abstract class AbstractService<T extends Entity> {

    protected BaseDao<T> baseDao;
    protected JunctionDao junctionDao;

    protected ApplicationService applicationService;
    protected ComputerService computerService;
    protected ConnectionService connectionService;
    protected ConnectionFarmService connectionFarmService;
    protected UserService userService;
    protected UserGroupService userGroupService;
    protected ProjectService projectService;
    protected ProgramService origramService;
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

}
