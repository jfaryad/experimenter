package org.experimenter.web.common.panel;

import static org.experimenter.web.renderer.PropertyChoiceRenderer.USERGROUP_RENDERER;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.UserGroup;
import org.experimenter.repository.service.ConnectionFarmService;
import org.experimenter.web.component.FinalEntityPropertyDropDownChoice;
import org.experimenter.web.model.ConnectionFarmModel;
import org.experimenter.web.model.aggregate.AvailableUserGroups;

/**
 * Simple panel with a form to edit the {@link ConnectionFarm} entity.
 * 
 * @author jfaryad
 * 
 */
public class ConnectionFarmFormPanel extends EntityFormPanel<ConnectionFarm> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ConnectionFarmService connectionFarmService;

    public ConnectionFarmFormPanel(String id, ConnectionFarmModel connectionFarmModel) {
        super(id, connectionFarmModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addFieldsToForm(Form<ConnectionFarm> form) {
        form.add(new FinalEntityPropertyDropDownChoice<ConnectionFarm, UserGroup>("userGroup",
                new AvailableUserGroups(), USERGROUP_RENDERER, (IModel<ConnectionFarm>) getDefaultModel()));
        form.add(new RequiredTextField<String>("name"));
        form.add(new TextField<String>("description"));

    }

    @Override
    protected void save(ConnectionFarm connectionFarm) {
        connectionFarmService.saveUpdate(connectionFarm);
    }

}
