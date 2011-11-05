package org.experimenter.repository.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

/**
 * Entity for database table APPLICXATION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Application implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer applicationId;
	private String version;
	private Blob executable;
	private Program program;
	private List<Experiment> experiments;

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

	public Blob getExecutable() {
		return executable;
	}

	public void setExecutable(Blob executable) {
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
		return "Application[applicationId: " + applicationId + ", version: "
				+ version + ", executable: " + executable + ", program: "
				+ program + ", experiments.size: "
				+ ((experiments != null) ? experiments.size() : null) + "]";
	}

}
