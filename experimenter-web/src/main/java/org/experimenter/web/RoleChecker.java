package org.experimenter.web;

import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.experimenter.repository.entity.User;

/**
 * Implementation of {@link IRoleCheckingStrategy} that verifies the role of the current user in session.
 * 
 * @author jfaryad
 * 
 */
public class RoleChecker implements IRoleCheckingStrategy
{

    /**
     * Construct.
     */
    public RoleChecker() {
    }

    @Override
    public boolean hasAnyRole(Roles roles) {
        ExperimenterSession authSession = ExperimenterSession.get();
        User currentUser = authSession.getCurrentUser();
        if (currentUser == null) {
            return false;
        }
        if (currentUser.getIsAdmin()) {
            return true;
        }
        return roles.hasRole(Roles.USER);
    }
}
