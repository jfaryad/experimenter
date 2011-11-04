package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.List;

/**
 * Entity for database table PROGRAM
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer programId;
    private String name;
    private String description;
    private String command;
    private Project project;
    private List<Application> applications;

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

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

}
