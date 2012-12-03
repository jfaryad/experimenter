package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table PROGRAM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "PROGRAM")
@NamedQueries({
        @NamedQuery(
                name = Program.Q_GET_BY_USER,
                query = "select p from Program p " +
                        "inner join p.project as proj " +
                        "inner join proj.userGroup as g " +
                        "inner join g.users as u " +
                        "where u = :user " +
                        "order by proj.name, p.name",
                readOnly = true) })
public class Program implements Entity {

    private static final long serialVersionUID = 1L;
    public static final String Q_GET_BY_USER = "Program.Q_GET_BY_USER";

    @Column(name = "program_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "command", nullable = true)
    private String command;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Project project;

    @OneToMany(mappedBy = "program")
    private List<Application> applications;

    public Program() {

    }

    public Program(Integer id) {
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Program[id: " + getId() + ", name: " + name + ", description: " + description + ", command: " + command
                + ", project: " + project + "]";
    }

}
