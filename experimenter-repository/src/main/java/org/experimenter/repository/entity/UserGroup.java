package org.experimenter.repository.entity;

import java.util.List;

public class UserGroup extends Entity {

    private static final long serialVersionUID = 1L;
    private String name;
    private List<User> users;
    private List<ConnectionFarm> connectionFarms;
    private List<Project> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ConnectionFarm> getConnectionFarms() {
        return connectionFarms;
    }

    public void setConnectionFarms(List<ConnectionFarm> connectionFarms) {
        this.connectionFarms = connectionFarms;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "UserGroup[id: " + getId() + ", name: " + name + "]";
    }

}
