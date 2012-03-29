package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ProblemTypeService;

/**
 * Default entity model for the {@link ProblemType} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ProblemTypeModel extends EntityModel<ProblemType> {

    @SpringBean
    private ProblemTypeService problemTypeService;

    private static final long serialVersionUID = 1L;

    public ProblemTypeModel(ProblemType problemType) {
        super(problemType);
    }

    public ProblemTypeModel(Integer id) {
        super(id);
    }

    @Override
    protected ProblemType load(Integer id) {
        return problemTypeService.findById(id);
    }

}
