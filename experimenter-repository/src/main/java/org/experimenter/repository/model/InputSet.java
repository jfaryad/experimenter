package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.List;

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
    private List<Input> inputs;
    private List<Experiment> experiments;
    private List<Project> projects;

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

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}
