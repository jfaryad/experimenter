package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table PROGRAM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Program extends Entity {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String command;
    private Project project;
    private List<Application> applications;

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
