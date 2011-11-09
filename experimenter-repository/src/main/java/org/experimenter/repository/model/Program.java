package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table PROGRAM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Program implements Model {

    private static final long serialVersionUID = 1L;
    private Integer programId;
    private String name;
    private String description;
    private String command;
    private Project project;
    private Set<Application> applications;

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
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

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Program[programId: " + programId + ", name: " + name + ", description: " + description + ", command: "
                + command + ", project: " + project + "]";
    }

    @Override
    public String toDebugString() {
        return "Program[programId: " + programId + ", name: " + name + ", description: " + description + ", command: "
                + command + ", project: " + project + ", applications.size: "
                + ((applications != null) ? applications.size() : null) + "]";
    }

}
