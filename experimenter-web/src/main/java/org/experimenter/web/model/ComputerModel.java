package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.service.ComputerService;

/**
 * Default entity model for the {@link Computer} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ComputerModel extends EntityModel<Computer> {

    @SpringBean
    private ComputerService computerService;

    private static final long serialVersionUID = 1L;

    public ComputerModel(Computer computer) {
        super(computer);
    }

    public ComputerModel(Integer id) {
        super(id);
    }

    @Override
    protected Computer loadForId(Integer id) {
        return computerService.findById(id);
    }

}
