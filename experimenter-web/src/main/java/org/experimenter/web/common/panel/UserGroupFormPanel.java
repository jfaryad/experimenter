package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.UserGroupService;
import org.experimenter.web.model.UserGroupModel;

/**
 * Simple panel with a form to edit the {@link UserGroup} entity.
 * 
 * @author jfaryad
 * 
 */
public class UserGroupFormPanel extends EntityFormPanel<UserGroup> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserGroupService userGroupService;

    public UserGroupFormPanel(String id, UserGroupModel userGroupModel) {
        super(id, userGroupModel);
    }

    @Override
    protected void addFieldsToForm(Form<UserGroup> form) {
        form.add(new RequiredTextField<String>("name"));

    }

    @Override
    protected void save(UserGroup userGroup) {
        userGroupService.saveUpdate(userGroup);
    }

}
