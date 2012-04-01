package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ProgramService;
import org.experimenter.web.model.ProgramModel;

/**
 * Default provider of the {@link Program} entity.
 * 
 * @author jakub
 * 
 */
public class ProgramProvider extends EntityDataProvider<Program> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProgramService programService;

    @Override
    public IModel<Program> model(Program program) {
        return new ProgramModel(program);
    }

    @Override
    protected List<Program> load() {
        // loads all programs
        return programService.findByExample(new Program());
    }

}
