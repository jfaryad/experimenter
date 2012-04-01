package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
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

    @Override
    public IModel<Input> model(Input input) {
        return new InputModel(input);
    }

    @Override
    protected List<Input> load() {
        // loads all inputs
        return inputService.findByExample(new Input());
    }

}
