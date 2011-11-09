package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table APPLICXATION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Application implements Model {

    private static final long serialVersionUID = 1L;
    private Integer applicationId;
    private String version;
    private String executable;
    private Program program;
    private Set<Experiment> experiments;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Set<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    @Override
    public String toString() {
        return "Application[applicationId: " + applicationId + ", version: " + version + ", executable: " + executable
                + ", program: " + program + "]";
    }

    @Override
    public String toDebugString() {
        return "Application[applicationId: " + applicationId + ", version: " + version + ", executable: " + executable
                + ", program: " + program + ", experiments.size: "
                + ((experiments != null) ? experiments.size() : null) + "]";
    }

}
