package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProjectService;
import org.experimenter.web.ExperimenterSession;
import org.experimenter.web.model.FilteredListModel;

/**
 * Returns all projects belonging to one of the current user's user groups.
 * 
 * @author jfaryad
 * 
 */
public class AvailableProjects extends FilteredListModel<Project> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProjectService projectService;

    public AvailableProjects() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Project> loadUnfiltered() {
        return projectService.findProjectsByUser(ExperimenterSession.get().getCurrentUser());
    }

}
