package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table FARM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class ConnectionFarm implements Model {

    private static final long serialVersionUID = 1L;
    private Integer connectionFarmId;
    private String name;
    private String description;
    private UserGroup userGroup;
    private Set<Connection> connections;
    private Set<Experiment> experiments;

    public Integer getConnectionFarmId() {
        return connectionFarmId;
    }

    public void setConnectionFarmId(Integer connectionFarmId) {
        this.connectionFarmId = connectionFarmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Set<Connection> getConnections() {
        return connections;
    }

    public void setConnections(Set<Connection> connections) {
        this.connections = connections;
    }

    public Set<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    @Override
    public String toString() {
        return "ConnectionFarm[connectionFarmId: " + connectionFarmId + ", name: " + name + ", description: "
                + description + ", userGroup: " + userGroup + "]";
    }

    @Override
    public String toDebugString() {
        return "ConnectionFarm[connectionFarmId: " + connectionFarmId + ", name: " + name + ", description: "
                + description + ", userGroup: " + userGroup + ", connections.size: "
                + ((connections != null) ? connections.size() : null) + ", experiments.size: "
                + ((experiments != null) ? experiments.size() : null) + "]";
    }

}
