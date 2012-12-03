package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.service.ConnectionService;
import org.experimenter.web.ExperimenterSession;
import org.experimenter.web.model.FilteredListModel;

/**
 * Returns all connections belonging to one of the current user's user groups.
 * 
 * @author jfaryad
 * 
 */
public class AvailableConnections extends FilteredListModel<Connection> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ConnectionService connectionService;

    public AvailableConnections() {
        Injector.get().inject(this);
    }

    @Override
    protected List<Connection> loadUnfiltered() {
        return connectionService.findConnectionsByUser(ExperimenterSession.get().getCurrentUser());
    }

}
