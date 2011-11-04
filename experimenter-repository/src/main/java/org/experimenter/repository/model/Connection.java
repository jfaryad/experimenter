package org.experimenter.repository.model;

import java.io.Serializable;

/**
 * Entity for database table CONNECTION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Connection implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer connectionId;
    private String name;
    private String login;
    private String password;
    private String description;
    private Short port;
    private Computer computer;
    private ConnectionFarm connectionFarm;

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public ConnectionFarm getConnectionFarm() {
        return connectionFarm;
    }

    public void setConnectionFarm(ConnectionFarm connectionFarm) {
        this.connectionFarm = connectionFarm;
    }

}
