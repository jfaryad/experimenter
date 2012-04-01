package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;
import org.experimenter.web.model.ComputerModel;

/**
 * Default provider of the {@link Computer} entity.
 * 
 * @author jakub
 * 
 */
public class ComputerProvider extends EntityDataProvider<Computer> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ComputerService computerService;

    @Override
    public IModel<Computer> model(Computer computer) {
        return new ComputerModel(computer);
    }

    @Override
    protected List<Computer> load() {
        // loads all computers
        return computerService.findByExample(new Computer());
    }

}
