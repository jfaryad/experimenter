package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.model.InputSetModel;
import org.experimenter.web.model.aggregate.AvailableInputSets;
import org.experimenter.web.model.aggregate.AvailableInputSetsByProject;

/**
 * Default provider of the {@link InputSet} entity.
 * 
 * @author jakub
 * 
 */
public class InputSetProvider extends EntityDataProvider<InputSet> {

    private static final long serialVersionUID = 1L;

    private final IModel<Project> projectFilter;
    IModel<Experiment> experimentFilter;
    private final IModel<List<InputSet>> allInputSetsModel;
    private final IModel<List<InputSet>> inputSetsByProject;

    public InputSetProvider(IModel<ProblemType> problemFilter,
            IModel<Project> projectFilter,
            IModel<Experiment> experimentFilter) {
        allInputSetsModel = new AvailableInputSets().filterBy("problem", problemFilter);
        inputSetsByProject = new AvailableInputSetsByProject(this.projectFilter = projectFilter)
                .filterBy("problem", problemFilter);
        this.experimentFilter = experimentFilter;
    }

    @Override
    public IModel<InputSet> model(InputSet inputSet) {
        return new InputSetModel(inputSet);
    }

    @Override
    protected List<InputSet> load() {
        if (experimentFilter.getObject() != null) {
            return experimentFilter.getObject().getInputSets();
        } else if (projectFilter.getObject() == null) {
            return allInputSetsModel.getObject();
        } else {
            return inputSetsByProject.getObject();
        }
    }

    @Override
    public void detach() {
        super.detach();
        allInputSetsModel.detach();
        inputSetsByProject.detach();
    }

}
