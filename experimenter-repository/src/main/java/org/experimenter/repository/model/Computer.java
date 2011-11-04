package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.List;

/**
 * Entity for database table COMPUTER
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Computer implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer computerId;
    private String address;
    private String description;
    private List<Connection> connections;

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

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

}
