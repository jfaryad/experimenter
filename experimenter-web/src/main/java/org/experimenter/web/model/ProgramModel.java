package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.service.ProgramService;

/**
 * Default entity model for the {@link Program} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ProgramModel extends EntityModel<Program> {

    @SpringBean
    private ProgramService programService;

    private static final long serialVersionUID = 1L;

    public ProgramModel(Program program) {
        super(program);
    }

    public ProgramModel(Integer id) {
        super(id);
    }

    @Override
    protected Program loadForId(Integer id) {
        return programService.findById(id);
    }

}
