package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table FARM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class ConnectionFarm extends Entity {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private UserGroup userGroup;
    private List<Connection> connections;
    private List<Experiment> experiments;

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

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    @Override
    public String toString() {
        return "ConnectionFarm[id: " + getId() + ", name: " + name + ", description: " + description + ", userGroup: "
                + userGroup + "]";
    }

}
