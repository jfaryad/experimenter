package org.experimenter.web.model;

import org.experimenter.web.ExperimenterSession;

/**
 * Model of the currently signed in user.
 * 
 * @author jfaryad
 * 
 */
public class CurrentUserModel extends UserModel {

    private static final long serialVersionUID = 1L;

    public CurrentUserModel() {
        super(ExperimenterSession.get().getCurrentUser());
    }

}
