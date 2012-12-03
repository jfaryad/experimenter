package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;
import org.experimenter.web.ExperimenterSession;
import org.experimenter.web.model.FilteredListModel;

public class AvailableExperiments extends FilteredListModel<Experiment> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ExperimentService experimentService;

    public AvailableExperiments() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Experiment> loadUnfiltered() {
        return experimentService.findExperimentsByUser(ExperimenterSession.get().getCurrentUser());
    }

}