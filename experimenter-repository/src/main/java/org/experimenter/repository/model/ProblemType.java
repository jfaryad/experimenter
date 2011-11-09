package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table PROBLEM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class ProblemType implements Model {

    private static final long serialVersionUID = 1L;
    private Integer problemId;
    private String name;
    private String description;
    private Set<Project> projects;
    private Set<Input> inputs;
    private Set<InputSet> inputSets;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Input> getInputs() {
        return inputs;
    }

    public void setInputs(Set<Input> inputs) {
        this.inputs = inputs;
    }

    public Set<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(Set<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

    @Override
    public String toString() {
        return "ProblemType[problemId: " + problemId + ", name: " + name + ", description: " + description + "]";
    }

    @Override
    public String toDebugString() {
        return "ProblemType[problemId: " + problemId + ", name: " + name + ", description: " + description
                + ", inputs.size: " + ((inputs != null) ? inputs.size() : null) + ", inputSets.size: "
                + ((inputSets != null) ? inputSets.size() : null) + ", projects.size: "
                + ((projects != null) ? projects.size() : null) + "]";
    }

}
