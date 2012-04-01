package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.web.model.ProjectModel;

/**
 * Default provider of the {@link Project} entity.
 * 
 * @author jakub
 * 
 */
public class ProjectProvider extends EntityDataProvider<Project> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProjectService projectService;

    @Override
    public IModel<Project> model(Project project) {
        return new ProjectModel(project);
    }

    @Override
    protected List<Project> load() {
        // loads all projects
        return projectService.findByExample(new Project());
    }

}
