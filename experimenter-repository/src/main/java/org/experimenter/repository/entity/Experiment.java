package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table EXPERIMENT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Experiment implements Entity {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description;
    private Project project;
    private Application application;
    private List<ConnectionFarm> connectionFarms;
    private List<InputSet> inputSets;

    public Experiment() {

    }

    public Experiment(Integer id) {
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

    @Override
    public String toString() {
        return "Experiment[id: " + getId() + ", name: " + name + ", description: " + description + ", project: "
                + project + ", application: " + application + "]";
    }

}
