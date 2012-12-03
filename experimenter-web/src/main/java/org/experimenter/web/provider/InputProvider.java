package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputService;
import org.experimenter.web.model.InputModel;

/**
 * Default provider of the {@link Input} entity.
 * 
 * @author jakub
 * 
 */
public class InputProvider extends EntityDataProvider<Input> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputService inputService;

    private final IModel<ProblemType> problemTypeFilter;
    private final IModel<InputSet> inputSetFilter;

    public InputProvider(IModel<ProblemType> problemTypeFilter, IModel<InputSet> inputSetFilter) {
        this.problemTypeFilter = problemTypeFilter;
        this.inputSetFilter = inputSetFilter;
    }

    @Override
    public IModel<Input> model(Input input) {
        return new InputModel(input);
    }

    @Override
    protected List<Input> load() {
        if (inputSetFilter != null && inputSetFilter.getObject() != null) {
            return inputService.findInputsByInputSet(inputSetFilter.getObject());
        } else if (problemTypeFilter != null && problemTypeFilter.getObject() != null) {
            return inputService.findInputsByProblemType(problemTypeFilter.getObject());
        } else {
            return inputService.findByExample(new Input()); // find all
        }
    }

}
