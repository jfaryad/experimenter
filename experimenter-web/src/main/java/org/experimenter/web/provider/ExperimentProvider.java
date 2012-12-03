package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.model.ExperimentModel;
import org.experimenter.web.model.aggregate.AvailableExperiments;

/**
 * Default provider of the {@link Experiment} entity.
 * 
 * @author jakub
 * 
 */
public class ExperimentProvider extends EntityDataProvider<Experiment> {

    private static final long serialVersionUID = 1L;

    private final IModel<List<Experiment>> innerExperimentModel;

    public ExperimentProvider(IModel<Program> programFilter) {
        innerExperimentModel = new AvailableExperiments().filterBy("application.program", programFilter);
    }

    public ExperimentProvider(IModel<Project> projectFilter, IModel<Program> programFilter,
            IModel<Application> applicationFilter) {
        innerExperimentModel = new AvailableExperiments()
                .filterBy("application.program", programFilter)
                .filterBy("application.program.project", projectFilter)
                .filterBy("application", applicationFilter);
    }

    @Override
    public IModel<Experiment> model(Experiment experiment) {
        return new ExperimentModel(experiment);
    }

    @Override
    protected List<Experiment> load() {
        return innerExperimentModel.getObject();
    }

    @Override
    public void detach() {
        super.detach();
        innerExperimentModel.detach();
    }

}
