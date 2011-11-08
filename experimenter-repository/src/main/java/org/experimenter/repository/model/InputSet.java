package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Entity for database table INPUT_SET
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class InputSet implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer inputSetId;
    private String name;
    private String description;
    private ProblemType problem;
    private Set<Input> inputs;
    private Set<Experiment> experiments;
    private Set<Project> projects;

    public Integer getInputSetId() {
        return inputSetId;
    }

    public void setInputSetId(Integer inputSetId) {
        this.inputSetId = inputSetId;
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

    public ProblemType getProblem() {
        return problem;
    }

    public void setProblem(ProblemType problem) {
        this.problem = problem;
    }

    public Set<Input> getInputs() {
        return inputs;
    }

    public void setInputs(Set<Input> inputs) {
        this.inputs = inputs;
    }

    public Set<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "InputSet[inputSetId: " + inputSetId + ", name: " + name + ", description: " + description
                + ", problem: " + problem + "]";
    }

}
