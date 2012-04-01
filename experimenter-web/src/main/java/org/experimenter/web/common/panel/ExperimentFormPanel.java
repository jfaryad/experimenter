package org.experimenter.web.common.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.web.model.ExperimentModel;

/**
 * Simple panel with a form to edit the {@link Experiment} entity.
 * 
 * @author jfaryad
 * 
 */
public class ExperimentFormPanel extends EntityFormPanel<Experiment> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ExperimentService experimentService;

    public ExperimentFormPanel(String id, ExperimentModel experimentModel) {
        super(id, experimentModel);
    }

    @Override
    protected void addFieldsToForm(Form<Experiment> form) {
        form.add(new RequiredTextField<String>("name"));
        form.add(new RequiredTextField<String>("description"));

    }

    @Override
    protected void save(Experiment experiment) {
        experimentService.saveUpdate(experiment);
    }

}
