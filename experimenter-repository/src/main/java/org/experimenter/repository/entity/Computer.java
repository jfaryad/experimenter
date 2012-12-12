package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table COMPUTER
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "COMPUTER")
@NamedQueries({
        @NamedQuery(
                name = Computer.Q_GET_LEAST_LOADED,
                query = "select com from Connection c " +
                        "inner join  c.computer as com " +
                        "inner join c.connectionFarm as f " +
                        "where f.id in (:farmIds) " +
                        "and com.numberOfRunningJobs < :maxJobs " +
                        "and com.numberOfRunningJobs = " +
                        "(select min(comp.numberOfRunningJobs) from Connection conn " +
                        "inner join conn.connectionFarm as farm " +
                        "inner join conn.computer as comp " +
                        "where farm.id in (:farmIds)) " +
                        "order by c.id",
                readOnly = false),
        @NamedQuery(
                name = Computer.Q_RESET_RUNNING_JOBS,
                query = "update Computer set numberOfRunningJobs = 0",
                readOnly = false) })
public class Computer implements Entity {

    private static final long serialVersionUID = 1L;
    public static final String Q_GET_LEAST_LOADED = "Computer.Q_GET_LEAST_LOADED";
    public static final String Q_RESET_RUNNING_JOBS = "Computer.Q_RESET_RUNNING_JOBS";

    @Column(name = "computer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "running_jobs", nullable = false)
    private Integer numberOfRunningJobs;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "computer")
    private List<Connection> connections;

    public Computer() {

    }

    public Computer(Integer id) {
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

    public Integer getNumberOfRunningJobs() {
        return numberOfRunningJobs;
    }

    public void setNumberOfRunningJobs(Integer numberOfRunningJobs) {
        this.numberOfRunningJobs = numberOfRunningJobs;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Computer)) {
            return false;
        }
        final Computer other = (Computer) o;
        return other.getId() == id;
    }

    @Override
    public String toString() {
        return "Computer[id: " + getId() + ", address: " + address + ", description: " + description
                + ", runningJobs: " + numberOfRunningJobs + "]";
    }
}
