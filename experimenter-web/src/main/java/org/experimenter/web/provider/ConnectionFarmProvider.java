package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.service.ConnectionFarmService;
import org.experimenter.web.model.ConnectionFarmModel;

/**
 * Default provider of the {@link ConnectionFarm} entity.
 * 
 * @author jakub
 * 
 */
public class ConnectionFarmProvider extends EntityDataProvider<ConnectionFarm> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ConnectionFarmService connectionFarmService;

    @Override
    public IModel<ConnectionFarm> model(ConnectionFarm connectionFarm) {
        return new ConnectionFarmModel(connectionFarm);
    }

    @Override
    protected List<ConnectionFarm> load() {
        // loads all connectionFarms
        return connectionFarmService.findByExample(new ConnectionFarm());
    }

}
