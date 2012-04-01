package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.UserGroupTable;
import org.experimenter.web.provider.UserGroupProvider;

/**
 * Page for userGroup administration.
 * 
 * @author jakub
 * 
 */
public class UserGroupPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public UserGroupPage(final PageParameters parameters) {
        super(parameters, "UserGroups");
        add(new UserGroupTable("userGroup-table", new UserGroupProvider()));
    }
}
