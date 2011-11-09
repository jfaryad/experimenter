package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table COMPUTER
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Computer implements Model {

    private static final long serialVersionUID = 1L;
    private Integer computerId;
    private String address;
    private String description;
    private Set<Connection> connections;

    public Integer getComputerId() {
        return computerId;
    }

    public void setComputerId(Integer computerId) {
        this.computerId = computerId;
    }

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

    public Set<Connection> getConnections() {
        return connections;
    }

    public void setConnections(Set<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Computer[computerId: " + computerId + ", address: " + address + ", description: " + description + "]";
    }

    @Override
    public String toDebugString() {
        return "Computer[computerId: " + computerId + ", address: " + address + ", description: " + description
                + ", connections.size: " + ((connections != null) ? connections.size() : null) + "]";
    }
}
