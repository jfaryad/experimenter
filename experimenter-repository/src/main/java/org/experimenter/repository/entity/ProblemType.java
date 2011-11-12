package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table PROBLEM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class ProblemType implements Entity {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description;
    private List<Project> projects;
    private List<Input> inputs;
    private List<InputSet> inputSets;

    public ProblemType() {

    }

    public ProblemType(Integer id) {
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(List<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

    @Override
    public String toString() {
        return "ProblemType[id: " + getId() + ", name: " + name + ", description: " + description + "]";
    }

}
