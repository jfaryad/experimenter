package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.web.ExperimenterSession;
import org.experimenter.web.model.FilteredListModel;

/**
 * Model of all programs that a user can see.
 * 
 * @author jfaryad
 * 
 */
public class AvailablePrograms extends FilteredListModel<Program> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProgramService programService;

    private final IModel<Project> projectFilter;

    public AvailablePrograms() {
        this(null);
    }

    public AvailablePrograms(IModel<Project> projectFilter) {
        Injector.get().inject(this);
        this.projectFilter = projectFilter;
    }

    @Override
    protected List<Program> loadUnfiltered() {
        return programService.findProgramsByUser(ExperimenterSession.get().getCurrentUser());
    }

}
