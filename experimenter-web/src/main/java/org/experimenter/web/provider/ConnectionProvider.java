package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.web.model.ConnectionModel;

/**
 * Default provider of the {@link Connection} entity.
 * 
 * @author jakub
 * 
 */
public class ConnectionProvider extends EntityDataProvider<Connection> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ConnectionService connectionService;

    @Override
    public IModel<Connection> model(Connection connection) {
        return new ConnectionModel(connection);
    }

    @Override
    protected List<Connection> load() {
        // loads all connections
        return connectionService.findByExample(new Connection());
    }

}
