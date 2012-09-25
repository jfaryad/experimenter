package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProjectService;

public class AvailableProjects extends LoadableDetachableModel<List<Project>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProjectService projectService;

    public AvailableProjects() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Project> load() {
        return projectService.findByExample(new Project());
    }

}
