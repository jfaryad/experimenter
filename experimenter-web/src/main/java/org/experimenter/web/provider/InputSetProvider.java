package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.model.InputSetModel;

/**
 * Default provider of the {@link InputSet} entity.
 * 
 * @author jakub
 * 
 */
public class InputSetProvider extends EntityDataProvider<InputSet> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputSetService inputSetService;

    @Override
    public IModel<InputSet> model(InputSet inputSet) {
        return new InputSetModel(inputSet);
    }

    @Override
    protected List<InputSet> load() {
        // loads all inputSets
        return inputSetService.findByExample(new InputSet());
    }

}
