package org.experimenter.repository.service.impl;

import org.experimenter.repository.entity.Connection;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.service.ExperimentService;

public class RunMeTask {

    private Integer experimentId;
    private ExperimentService experimentService;

    public RunMeTask() {

    }

    public RunMeTask(Integer experimentId, ExperimentService experimentService) {
        this.experimentId = experimentId;
        this.experimentService = experimentService;
    }

    public void printMe() {
        Experiment experiment = experimentService.findById(experimentId);
        Connection connection = experiment.getConnectionFarms().get(0).getConnections().get(0);
        System.out.println("Experiment has " + experiment.getConnectionFarms().size()
                + " connection farms, \nthe first one has "
                + experiment.getConnectionFarms().get(0).getConnections().size()
                + " connections and the first one of them is: " + connection);
        String name = experiment.getName();
        String hostname = connection.getComputer().getAddress();
        String login = connection.getLogin();
        String password = connection.getPassword();
        String pathToFile = experiment.getInputSets().get(0).getInputs().get(0).getData();
        System.out.println("Running experiment " + name + " on " + hostname + " with credentials " + login + "/"
                + password + " using input data " + pathToFile);
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public ExperimentService getExperimentService() {
        return experimentService;
    }

    public void setExperimentService(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

}
