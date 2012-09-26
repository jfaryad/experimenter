package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;

public class AvailableComputers extends LoadableDetachableModel<List<Computer>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ComputerService computerService;

    public AvailableComputers() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Computer> load() {
        return computerService.findByExample(new Computer());
    }

}
