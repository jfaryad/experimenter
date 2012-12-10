package org.experimenter.repository.exception;

/**
 * An exception thrown when an error while working with the zip file occurrs.
 * 
 * @author jfaryad
 * 
 */
public class ZipException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ZipException(Throwable t) {
        super(t);
    }

}
