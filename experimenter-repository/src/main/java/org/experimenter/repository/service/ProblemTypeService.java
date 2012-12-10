package org.experimenter.repository.service;

import java.util.List;

import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.form.CriteriaForm;

/**
 * The service taking care of {@link ProblemType} related operations.
 * 
 * @author jfaryad
 */
public interface ProblemTypeService extends EntityService<ProblemType> {

    /**
     * Saves the given {@link ProblemType} to database. If the entry doesn't exists yet, it will be created.
     * 
     * @param problemType
     *            the problemType to save
     */
    @Override
    public void saveUpdate(ProblemType problemType);

    /**
     * Finds the {@link ProblemType} with the given id.
     * 
     * @param id
     *            the identifier of the problemType
     * @return the problemType with the given id or null, if such an entry doesn't exist in the database.
     */
    @Override
    public ProblemType findById(Integer id);

    /**
     * Finds the {@link ProblemType} that matches the properties set in the input form.
     * 
     * @param problemType
     *            a search form with the properties you want to search by
     * @return a list of problemTypes that match the example
     */
    @Override
    public List<ProblemType> findByExample(ProblemType problemType);

    /**
     * Finds the {@link ProblemType} that matches the properties set in the input form. In addition, it is possible to
     * specify the maximum number of returned entities, the first to start with and the search order.
     * 
     * @param criteria
     *            a search form with the properties you want to search by
     * @return a list of problemTypes that match the criteria
     */
    @Override
    public List<ProblemType> findByCriteria(CriteriaForm<ProblemType> criteria);

    /**
     * Deletes the given {@link ProblemType} from the database, including all existing links to other tables.
     * 
     * @param problemType
     *            the problemType to delete
     */
    @Override
    public void delete(ProblemType problemType);

    /**
     * Deletes the given list of {@link ProblemType}s from the database, including all existing links to other tables.
     * 
     * @param problemTypes
     *            the problemTypes to delete
     */
    @Override
    public void delete(List<ProblemType> problemTypes);

}
