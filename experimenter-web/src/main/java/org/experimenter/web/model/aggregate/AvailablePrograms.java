package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ProgramService;

public class AvailablePrograms extends LoadableDetachableModel<List<Program>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProgramService programService;

    public AvailablePrograms() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Program> load() {
        return programService.findByExample(new Program());
    }

}
