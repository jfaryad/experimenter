package org.experimenter.repository.model;

import java.util.HashSet;
import java.util.Set;

public class UserGroup implements Model {

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
        if (users == null)
            return new HashSet<User>();
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<ConnectionFarm> getConnectionFarms() {
        if (connectionFarms == null)
            return new HashSet<ConnectionFarm>();
        return connectionFarms;
    }

    public void setConnectionFarms(Set<ConnectionFarm> connectionFarms) {
        this.connectionFarms = connectionFarms;
    }

    public Set<Project> getProjects() {
        if (projects == null)
            return new HashSet<Project>();
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "UserGroup[userGroupId: " + userGroupId + ", name: " + name + "]";
    }

    @Override
    public String toDebugString() {
        return "UserGroup[userGroupId: " + userGroupId + ", name: " + name + ", users.size: "
                + ((users != null) ? users.size() : null) + ", connectionFarms.size: "
                + ((connectionFarms != null) ? connectionFarms.size() : null) + ", projects.size: "
                + ((projects != null) ? projects.size() : null) + "]";
    }

}
