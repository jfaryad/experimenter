package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProjectService;

/**
 * Default entity model for the {@link Project} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ProjectModel extends EntityModel<Project> {

    @SpringBean
    private ProjectService projectService;

    private static final long serialVersionUID = 1L;

    public ProjectModel(Project project) {
        super(project);
    }

    public ProjectModel(Integer id) {
        super(id);
    }

    @Override
    protected Project load(Integer id) {
        return projectService.findById(id);
    }

}
