package org.experimenter.repository.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entity for database table CONNECTION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "CONNECTION")
public class Connection implements Entity {

    @Column(name = "connection_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description")
    private String description;

    @Column(name = "port")
    private Short port;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id", referencedColumnName = "computer_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Computer computer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", referencedColumnName = "farm_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private ConnectionFarm connectionFarm;

    public Connection() {

    }

    public Connection(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Connection[id: " + getId() + ", name: " + name + ", login: " + login + ", password: " + password
                + ", description: " + description + ", port: " + port + ", computer: " + computer
                + ", connectionFarm: " + connectionFarm + "]";
    }

}
