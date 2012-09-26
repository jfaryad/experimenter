package org.experimenter.web.model.aggregate;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputSetService;

public class AvailableInputSets extends LoadableDetachableModel<List<InputSet>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputSetService inputSetService;

    private final IModel<ProblemType> problem;

    public AvailableInputSets(IModel<ProblemType> problem) {
        Injector.get().inject(this);
        this.problem = problem;
    }

    @Override
    protected List<InputSet> load() {
        if (problem.getObject() == null) {
            return Collections.emptyList();
        }
        return inputSetService.findInputSetsByProblemType(problem.getObject());
    }

}
