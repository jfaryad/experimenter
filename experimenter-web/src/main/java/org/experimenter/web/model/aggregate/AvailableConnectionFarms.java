package org.experimenter.web.model.aggregate;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ConnectionFarmService;
import org.experimenter.web.ExperimenterSession;

/**
 * Returns all connection farms belonging to one of the current user's user groups.
 * 
 * @author jfaryad
 * 
 */
public class AvailableConnectionFarms extends LoadableDetachableModel<List<ConnectionFarm>> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ConnectionFarmService connectionFarmService;

    public AvailableConnectionFarms() {
        Injector.get().inject(this);
    }

    @Override
    protected List<ConnectionFarm> load() {
        return connectionFarmService.findConnectionFarmsByUser(ExperimenterSession.get().getCurrentUser());
    }

}
