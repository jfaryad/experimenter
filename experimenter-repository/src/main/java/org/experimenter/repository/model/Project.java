package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.List;

/**
 * Entity for database table PROJECT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer projectId;
	private String name;
	private String description;
	private UserGroup userGroup;
	private ProblemType problem;
	private List<Experiment> experiments;
	private List<Program> programs;
	private List<InputSet> inputSets;

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

	public List<Experiment> getExperiments() {
		return experiments;
	}

	public void setExperiments(List<Experiment> experiments) {
		this.experiments = experiments;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<InputSet> getInputSets() {
		return inputSets;
	}

	public void setInputSets(List<InputSet> inputSets) {
		this.inputSets = inputSets;
	}

}
