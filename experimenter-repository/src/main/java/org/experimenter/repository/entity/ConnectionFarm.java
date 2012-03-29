package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table FARM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "FARM")
@NamedQueries({
        @NamedQuery(
                name = ConnectionFarm.Q_GET_BY_USERGROUP,
                query = "from ConnectionFarm where userGroup = :userGroup order by name",
                readOnly = true),
        @NamedQuery(
                name = ConnectionFarm.Q_DELETE_BY_USERGROUP,
                query = "delete from ConnectionFarm  where userGroup = :userGroup order by name"),
        @NamedQuery(
                name = ConnectionFarm.Q_GET_BY_EXPERIMENT,
                query = "select c from ConnectionFarm c join c.experiments e where e = :experiment order by name",
                readOnly = true) })
public class ConnectionFarm implements Entity {

    private static final long serialVersionUID = 1L;

    public static final String Q_GET_BY_USERGROUP = "ConnectionFarm.Q_GET_BY_USERGROUP";
    public static final String Q_DELETE_BY_USERGROUP = "ConnectionFarm.Q_DELETE_BY_USERGROUP";
    public static final String Q_GET_BY_EXPERIMENT = "ConnectionFarm.Q_GET_BY_EXPERIMENT";

    @Column(name = "farm_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usergroup_id", referencedColumnName = "usergroup_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private UserGroup userGroup;

    @OneToMany(mappedBy = "connectionFarm")
    private List<Connection> connections;

    @ManyToMany(mappedBy = "connectionFarms")
    private List<Experiment> experiments;

    public ConnectionFarm() {

    }

    public ConnectionFarm(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    @Override
    public String toString() {
        return "ConnectionFarm[id: " + getId() + ", name: " + name + ", description: " + description + ", userGroup: "
                + userGroup + "]";
    }

}
