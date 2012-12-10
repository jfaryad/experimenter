package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ProblemTypeService;

/**
 * Model of all problem types.
 * 
 * @author jfaryad
 * 
 */
public class AvailableProblemTypes extends LoadableDetachableModel<List<ProblemType>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProblemTypeService problemTypeService;

    public AvailableProblemTypes() {
        Injector.get().inject(this);
    }

    @Override
    protected List<ProblemType> load() {
        // all problem types
        return problemTypeService.findByExample(new ProblemType());
    }

}
