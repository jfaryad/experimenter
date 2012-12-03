package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.service.InputSetService;
import org.experimenter.web.model.FilteredListModel;

/**
 * Model of all existing input sets, no matter what problem type they are of.
 * 
 * @author jfaryad
 * 
 */
public class AvailableInputSets extends FilteredListModel<InputSet> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private InputSetService inputSetService;

    public AvailableInputSets() {
        Injector.get().inject(this);
    }

    @Override
    protected List<InputSet> loadUnfiltered() {
        return inputSetService.findByExample(new InputSet());
    }

}
