package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.model.ProjectModel;
import org.experimenter.web.model.aggregate.AvailableProjects;

/**
 * Default provider of the {@link Project} entity.
 * 
 * @author jakub
 * 
 */
public class ProjectProvider extends EntityDataProvider<Project> {

    private static final long serialVersionUID = 1L;

    private final IModel<List<Project>> innerProjectModel;

    public ProjectProvider(IModel<ProblemType> problemTypeFilter) {
        innerProjectModel = new AvailableProjects().filterBy("problem", problemTypeFilter);
    }

    @Override
    public IModel<Project> model(Project project) {
        return new ProjectModel(project);
    }

    @Override
    protected List<Project> load() {
        return innerProjectModel.getObject();
    }

    @Override
    public void detach() {
        super.detach();
        innerProjectModel.detach();
    }

}
