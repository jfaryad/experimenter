package org.experimenter.web.model.aggregate;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.InputService;

public class InputsByInputSet extends LoadableDetachableModel<List<Input>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputService inputService;

    private final IModel<InputSet> inputSet;

    public InputsByInputSet(IModel<InputSet> inputSet) {
        Injector.get().inject(this);
        this.inputSet = inputSet;
    }

    @Override
    protected List<Input> load() {
        if (inputSet.getObject() == null) {
            return Collections.emptyList();
        }
        return inputService.findInputsByInputSet(inputSet.getObject());
    }

}
