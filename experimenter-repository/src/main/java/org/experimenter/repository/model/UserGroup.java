package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.Set;

public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userGroupId;
    private String name;
    private Set<User> users;
    private Set<ConnectionFarm> connectionFarms;
    private Set<Project> projects;

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<ConnectionFarm> getConnectionFarms() {
        return connectionFarms;
    }

    public void setConnectionFarms(Set<ConnectionFarm> connectionFarms) {
        this.connectionFarms = connectionFarms;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "UserGroup[userGroupId: " + userGroupId + ", name: " + name + "]";
    }

}
