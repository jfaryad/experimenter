package org.experimenter.web.validator;

import org.apache.wicket.validation.validator.PatternValidator;

/**
 * Convenience validator for validating that textfields contain only alphanumerical characters or spaces and that they
 * have a certain maximum length.
 * 
 * @author jfaryad
 * 
 */
public class NiceStringValidator extends PatternValidator {

    private static final long serialVersionUID = 1L;

    public NiceStringValidator(int maxLength) {
        super("[.\\- a-zA-Z0-9]{1," + maxLength + "}");
    }
}
