package org.experimenter.web.model;

/**
 * an exception thrown by the {@link EntityModel} when the entity is not found for the given id.
 * 
 * @author jfaryad
 * 
 */
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
