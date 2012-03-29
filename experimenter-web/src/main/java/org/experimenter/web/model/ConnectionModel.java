package org.experimenter.web.model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.service.ConnectionService;

/**
 * Default entity model for the {@link Connection} entity. It's loadable, detachable, supports @SpringBean injections and
 * holding not persisted entities.
 * 
 * @author jfaryad
 * 
 */
public class ConnectionModel extends EntityModel<Connection> {

    @SpringBean
    private ConnectionService connectionService;

    private static final long serialVersionUID = 1L;

    public ConnectionModel(Connection connection) {
        super(connection);
    }

    public ConnectionModel(Integer id) {
        super(id);
    }

    @Override
    protected Connection load(Integer id) {
        return connectionService.findById(id);
    }

}
