package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ConnectionFarmService;

/**
 * Default entity model for the {@link ConnectionFarm} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ConnectionFarmModel extends EntityModel<ConnectionFarm> {

    @SpringBean
    private ConnectionFarmService connectionFarmService;

    private static final long serialVersionUID = 1L;

    public ConnectionFarmModel(ConnectionFarm connectionFarm) {
        super(connectionFarm);
    }

    public ConnectionFarmModel(Integer id) {
        super(id);
    }

    @Override
    protected ConnectionFarm loadForId(Integer id) {
        return connectionFarmService.findById(id);
    }

}
