package org.experimenter.web;

import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.lang.Args;

/**
 * Helper class for adding authorization to components
 * 
 * @author jfaryad
 * 
 */
public class ComponentAuthorization {

    public static final String[] USER_AND_ADMIN = new String[] { Roles.USER, Roles.ADMIN };

    /**
     * Registers the given component for authorization for the render action with the specified roles.
     * 
     * @param component
     *            the component to set authorization for
     * @param allowedRoles
     *            the allowed roles
     * @return the same component as was passed in.
     */
    public static <T extends Component> T authorizeRender(T component, String... allowedRoles) {
        Args.notNull(component, "component");
        Args.notNull(allowedRoles, "allowedRoles");
        for (String role : allowedRoles) {
            MetaDataRoleAuthorizationStrategy.authorize(component, Component.RENDER, role);
        }
        return component;
    }

    /**
     * Registers the given component for authorization for the enable action with the specified roles.
     * 
     * @param component
     *            the component to set authorization for
     * @param allowedRoles
     *            the allowed roles
     * @return the same component as was passed in.
     */
    public static <T extends Component> T authorizeEnabling(T component, String... allowedRoles) {
        Args.notNull(component, "component");
        Args.notNull(allowedRoles, "allowedRoles");
        for (String role : allowedRoles) {
            MetaDataRoleAuthorizationStrategy.authorize(component, Component.ENABLE, role);
        }
        return component;
    }

    /**
     * Returns a new instance of {@link WebMarkupContainer} with the given id, authorized for render only for an ADMIN
     * user
     * 
     * @param id
     *            wicket id of the new {@link WebMarkupContainer}
     */
    public static WebMarkupContainer adminOnlyWebMarkupContainer(String id) {
        WebMarkupContainer wc = new WebMarkupContainer(id);
        return authorizeRender(wc, Roles.ADMIN);
    }

}
