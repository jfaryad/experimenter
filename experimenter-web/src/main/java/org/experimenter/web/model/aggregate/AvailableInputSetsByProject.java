package org.experimenter.web.model.aggregate;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.model.FilteredListModel;

public class AvailableInputSetsByProject extends FilteredListModel<InputSet> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputSetService inputSetService;

    private final IModel<Project> project;

    public AvailableInputSetsByProject(IModel<Project> project) {
        Injector.get().inject(this);
        this.project = project;
    }

    @Override
    protected List<InputSet> loadUnfiltered() {
        if (project.getObject() == null) {
            return Collections.emptyList();
        }
        return inputSetService.findInputSetsByProject(project.getObject());
    }

}
