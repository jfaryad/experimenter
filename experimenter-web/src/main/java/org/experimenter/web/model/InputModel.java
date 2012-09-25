package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.service.InputService;

/**
 * Default entity model for the {@link Input} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class InputModel extends EntityModel<Input> {

    @SpringBean
    private InputService inputService;

    private static final long serialVersionUID = 1L;

    public InputModel(Input input) {
        super(input);
    }

    public InputModel(Integer id) {
        super(id);
    }

    @Override
    protected Input loadForId(Integer id) {
        return inputService.findById(id);
    }

}
