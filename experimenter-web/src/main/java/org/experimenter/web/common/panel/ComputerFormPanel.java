package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;
import org.experimenter.web.model.ComputerModel;

/**
 * Simple panel with a form to edit the {@link Computer} entity.
 * 
 * @author jfaryad
 * 
 */
public class ComputerFormPanel extends EntityFormPanel<Computer> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ComputerService computerService;

    public ComputerFormPanel(String id, ComputerModel computerModel) {
        super(id, computerModel);
    }

    @Override
    protected void addFieldsToForm(Form<Computer> form) {
        form.add(new RequiredTextField<String>("address"));
        form.add(new RequiredTextField<String>("description"));

    }

    @Override
    protected void save(Computer computer) {
        computerService.saveUpdate(computer);
    }

}
