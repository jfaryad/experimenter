package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table COMPUTER
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Computer extends Entity {

    private static final long serialVersionUID = 1L;
    private String address;
    private String description;
    private List<Connection> connections;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Computer[id: " + getId() + ", address: " + address + ", description: " + description + "]";
    }
}
