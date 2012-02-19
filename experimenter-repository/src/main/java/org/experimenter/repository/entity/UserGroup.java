package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table USERGROUP.
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "USERGROUP")
@NamedQueries({ @NamedQuery(
        name = UserGroup.Q_GET_BY_USER,
        query = "select ug from UserGroup ug join ug.users u where u = :user order by ug.name",
        readOnly = true) })
public class UserGroup implements Entity {

    public static final String Q_GET_BY_USER = "UserGroup.Q_GET_BY_USER";

    @Column(name = "usergroup_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "userGroups")
    private List<User> users;

    @OneToMany(mappedBy = "userGroup")
    private List<ConnectionFarm> connectionFarms;

    @OneToMany(mappedBy = "userGroup")
    private List<Project> projects;

    public UserGroup() {

    }

    public UserGroup(Integer id) {
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
