package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.web.model.ConnectionModel;
import org.experimenter.web.model.aggregate.AvailableConnections;

/**
 * Default provider of the {@link Connection} entity.
 * 
 * @author jakub
 * 
 */
public class ConnectionProvider extends EntityDataProvider<Connection> {

    private static final long serialVersionUID = 1L;

    private final IModel<List<Connection>> innerConnectionModel;

    public ConnectionProvider(IModel<ConnectionFarm> connectionFarmFilter, IModel<Computer> computerFilter) {
        innerConnectionModel = new AvailableConnections()
                .filterBy("connectionFarm", connectionFarmFilter)
                .filterBy("computer", computerFilter);
    }

    @Override
    public IModel<Connection> model(Connection connection) {
        return new ConnectionModel(connection);
    }

    @Override
    protected List<Connection> load() {
        return innerConnectionModel.getObject();
    }

    @Override
    public void detach() {
        super.detach();
        innerConnectionModel.detach();
    }

}
