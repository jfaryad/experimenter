package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table APPLICXATION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Application extends Entity {

    private static final long serialVersionUID = 1L;
    private String version;
    private String executable;
    private Program program;
    private List<Experiment> experiments;

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

    public List<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    @Override
    public String toString() {
        return "Application[id: " + getId() + ", version: " + version + ", executable: " + executable + ", program: "
                + program + "]";
    }

}
