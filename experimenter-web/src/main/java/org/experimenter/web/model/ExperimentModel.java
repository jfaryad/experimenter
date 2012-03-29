package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;

/**
 * Default entity model for the {@link Experiment} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ExperimentModel extends EntityModel<Experiment> {

    @SpringBean
    private ExperimentService experimentService;

    private static final long serialVersionUID = 1L;

    public ExperimentModel(Experiment experiment) {
        super(experiment);
    }

    public ExperimentModel(Integer id) {
        super(id);
    }

    @Override
    protected Experiment load(Integer id) {
        return experimentService.findById(id);
    }

}
