package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table PROJECT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "PROJECT")
@NamedQueries({
        @NamedQuery(
                name = Project.Q_GET_BY_USER,
                query = "select p from Project p " +
                        "inner join p.userGroup as g " +
                        "inner join g.users as u " +
                        "where u = :user " +
                        "order by p.name",
                readOnly = true) })
public class Project implements Entity {

    private static final long serialVersionUID = 1L;

    public static final String Q_GET_BY_USER = "Project.Q_GET_BY_USER";

    @Column(name = "project_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private ProblemType problem;

    @OneToMany(mappedBy = "project")
    private List<Program> programs;

    @ManyToMany
    @JoinTable(
            name = "PROJECT_INPUT_SET",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "input_set_id"))
    private List<InputSet> inputSets;

    public Project() {

    }

    public Project(Integer id) {
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

    public ProblemType getProblem() {
        return problem;
    }

    public void setProblem(ProblemType problem) {
        this.problem = problem;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(List<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

    @Override
    public String toString() {
        return "Project[id: " + getId() + ", name: " + name + ", description: " + description + ", userGroup: "
                + userGroup + ", problem: " + problem + "]";
    }

}
