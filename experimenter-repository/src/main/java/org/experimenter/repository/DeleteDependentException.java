package org.experimenter.repository;

import org.experimenter.repository.entity.Entity;

/**
 * {@link RuntimeException} thrown when the user tries to delete an entity that has dependencies.
 * 
 * @author jfaryad
 * 
 */
public class DeleteDependentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DeleteDependentException(String msg) {
        super(msg);
    }

    /**
     * Costructor.
     * 
     * @param deletedClass
     *            the class of the deleted entity
     * @param deletedId
     *            id of the deleted entity
     */
    public DeleteDependentException(Class<? extends Entity> deletedClass, Integer deletedId) {
        super("Error deleting " + deletedClass.getSimpleName() + "(id: " + deletedId + "), it has dependencies.");
    }

}
