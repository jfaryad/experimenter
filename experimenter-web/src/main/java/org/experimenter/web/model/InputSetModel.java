package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.InputSetService;

/**
 * Default entity model for the {@link InputSet} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class InputSetModel extends EntityModel<InputSet> {

    @SpringBean
    private InputSetService inputSetService;

    private static final long serialVersionUID = 1L;

    public InputSetModel(InputSet inputSet) {
        super(inputSet);
    }

    public InputSetModel(Integer id) {
        super(id);
    }

    @Override
    protected InputSet loadForId(Integer id) {
        return inputSetService.findById(id);
    }

}
