package org.experimenter.repository.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.experimenter.repository.DeleteDependentException;
import org.experimenter.repository.dao.BaseDao;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.form.CriteriaForm;
import org.experimenter.repository.service.ApplicationService;
import org.experimenter.repository.service.ComputerService;
import org.experimenter.repository.service.ConnectionFarmService;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.repository.service.EntityService;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.repository.service.InputService;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.repository.service.ProblemTypeService;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.repository.service.ResultService;
import org.experimenter.repository.service.StorageService;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.repository.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Base class for all entity services. Implements the common methods from {@link EntityService}
 * 
 * @author jfaryad
 * 
 * @param <T>
 *            the entity managed by this service
 * @param <D>
 *            th dao interface for entity T
 */
public abstract class AbstractService<T extends Entity, D extends BaseDao<T>> implements EntityService<T> {

    protected D baseDao;
    protected StorageService storageService;
    private SessionFactory sessionFactory;

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
    protected ResultService resultService;

    @Override
    public void saveUpdate(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("The entity to save must not be null.");
        if (entity.getId() == null)
            baseDao.insert(entity);
        else
            baseDao.update(entity);
    }

    @Override
    public T findById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("The id to search by must not be null.");
        return baseDao.findById(id);
    }

    @Override
    public List<T> findByExample(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("The example entity to search by must not be null.");
        return baseDao.findByCriteria(new CriteriaForm<T>(entity));
    }

    @Override
    public List<T> findByCriteria(CriteriaForm<T> criteria) {
        if (criteria == null || criteria.getEntity() == null)
            throw new IllegalArgumentException("The criteria or the model entity to search by must not be null.");
        return baseDao.findByCriteria(criteria);
    }

    @Override
    public void delete(T entity) {
        if (entity == null || entity.getId() == null)
            throw new IllegalArgumentException(
                    "The entity to delete must be an existing entity persisted in the database.");
        deleteDependencies(entity);
        baseDao.deleteById(entity.getId());
    }

    @Override
    public void delete(List<T> entities) {
        if (entities == null)
            throw new IllegalArgumentException("The list of entities to delete must not be null.");
        for (Iterator<T> it = entities.iterator(); it.hasNext();) {
            T entity = it.next();
            it.remove();
            delete(entity);
        }
    }

    @Override
    public void tryDelete(T entity) {
        checkIdNotNull(entity);
        if (hasDependencies(entity)) {
            throw new DeleteDependentException(entity.getClass(), entity.getId());
        }
        delete(entity);
    }

    protected abstract void deleteDependencies(T entity);

    /**
     * Checks whether there are any other entities dependent on this one
     * 
     * @param entity
     *            an not-null persisted entity
     * @return
     */
    protected abstract boolean hasDependencies(T entity);

    protected void checkNotNull(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The entity must not be null.");
        }
    }

    protected void checkIdNotNull(Entity entity) {
        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("The entity or it's id must not be null.");
        }
    }

    protected void checkNotEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("The collection must not be null or empty.");
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Required
    public void setBaseDao(D baseDao) {
        this.baseDao = baseDao;
    }

    @Required
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
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

    @Required
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
