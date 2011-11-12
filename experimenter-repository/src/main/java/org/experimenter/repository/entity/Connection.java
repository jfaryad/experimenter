package org.experimenter.repository.entity;


/**
 * Entity for database table CONNECTION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Connection extends Entity {

    private static final long serialVersionUID = 1L;
    private String name;
    private String login;
    private String password;
    private String description;
    private Short port;
    private Computer computer;
    private ConnectionFarm connectionFarm;

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

    @Override
    public String toString() {
        return "Connection[id: " + getId() + ", name: " + name + ", login: " + login + ", password: " + password
                + ", description: " + description + ", port: " + port + ", computer: " + computer
                + ", connectionFarm: " + connectionFarm + "]";
    }

}
