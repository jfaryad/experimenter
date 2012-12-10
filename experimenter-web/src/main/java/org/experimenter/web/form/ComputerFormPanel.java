package org.experimenter.web.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;
import org.experimenter.web.model.ComputerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple panel with a form to edit the {@link Computer} entity.
 * 
 * @author jfaryad
 * 
 */
public class ComputerFormPanel extends EntityFormPanel<Computer> {

    private static final Logger LOG = LoggerFactory.getLogger(ComputerFormPanel.class);
    private static final long serialVersionUID = 1L;

    @SpringBean
    private ComputerService computerService;

    public ComputerFormPanel(String id, ComputerModel computerModel) {
        super(id, computerModel);
    }

    @Override
    protected void addFieldsToForm(Form<Computer> form) {
        form.add(new RequiredTextField<String>("address"));
        form.add(new TextField<String>("description"));

    }

    @Override
    protected void save(Computer computer) {
        try {
            if (computer.getNumberOfRunningJobs() == null) {
                computer.setNumberOfRunningJobs(0);
            }
            computerService.saveUpdate(computer);
        } catch (Exception e) {
            LOG.error("Unable to save the computer", e);
            error("Error creating the computer.");
        }
    }
}
