package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table PROJECT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Project implements Model {

    private static final long serialVersionUID = 1L;
    private Integer projectId;
    private String name;
    private String description;
    private UserGroup userGroup;
    private ProblemType problem;
    private Set<Experiment> experiments;
    private Set<Program> programs;
    private Set<InputSet> inputSets;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Set<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    public Set<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(Set<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

    @Override
    public String toString() {
        return "Proj[ectprojectId: " + projectId + ", name: " + name + ", description: " + description + ", userGroup"
                + userGroup + ", problem: " + problem + "]";
    }

    @Override
    public String toDebugString() {
        return "Proj[ectprojectId: " + projectId + ", name: " + name + ", description: " + description + ", userGroup"
                + userGroup + ", problem: " + problem + ", programs.size: "
                + ((programs != null) ? programs.size() : null) + ", experiments.size: "
                + ((experiments != null) ? experiments.size() : null) + ", inputSets.size: "
                + ((inputSets != null) ? inputSets.size() : null) + "]";
    }

}
