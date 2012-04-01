package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.web.model.ExperimentModel;

/**
 * Default provider of the {@link Experiment} entity.
 * 
 * @author jakub
 * 
 */
public class ExperimentProvider extends EntityDataProvider<Experiment> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ExperimentService experimentService;

    @Override
    public IModel<Experiment> model(Experiment experiment) {
        return new ExperimentModel(experiment);
    }

    @Override
    protected List<Experiment> load() {
        // loads all experiments
        return experimentService.findByExample(new Experiment());
    }

}
