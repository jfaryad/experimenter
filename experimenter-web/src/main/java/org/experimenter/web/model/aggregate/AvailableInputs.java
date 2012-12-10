package org.experimenter.web.model.aggregate;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputService;

/**
 * Model of all inputs for a given problem.
 * 
 * @author jfaryad
 * 
 */
public class AvailableInputs extends LoadableDetachableModel<List<Input>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputService inputService;

    private final IModel<ProblemType> problem;

    public AvailableInputs(IModel<ProblemType> problem) {
        Injector.get().inject(this);
        this.problem = problem;
    }

    @Override
    protected List<Input> load() {
        if (problem.getObject() == null) {
            return Collections.emptyList();
        }
        return inputService.findInputsByProblemType(problem.getObject());
    }

}
