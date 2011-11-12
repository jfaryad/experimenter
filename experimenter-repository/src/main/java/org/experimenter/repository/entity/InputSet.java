package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table INPUT_SET
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class InputSet implements Entity {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description;
    private ProblemType problem;
    private List<Input> inputs;
    private List<Experiment> experiments;
    private List<Project> projects;

    public InputSet() {

    }

    public InputSet(Integer id) {
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

    @Override
    public String toString() {
        return "InputSet[id: " + getId() + ", name: " + name + ", description: " + description + ", problem: "
                + problem + "]";
    }

}
