package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.List;

/**
 * Entity for database table EXPERIMENT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Experiment implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer experimentId;
    private String name;
    private String description;
    private Project project;
    private Application application;
    private List<ConnectionFarm> connectionFarms;
    private List<InputSet> inputSets;

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<ConnectionFarm> getConnectionFarms() {
        return connectionFarms;
    }

    public void setConnectionFarms(List<ConnectionFarm> connectionFarms) {
        this.connectionFarms = connectionFarms;
    }

    public List<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(List<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

}
