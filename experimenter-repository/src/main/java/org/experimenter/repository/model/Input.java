package org.experimenter.repository.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Entity for database table INPUT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class Input implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer inputId;
    private String name;
    private String data;
    private ProblemType problem;
    private Set<InputSet> inputSets;

    public Integer getInputId() {
        return inputId;
    }

    public void setInputId(Integer inputId) {
        this.inputId = inputId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ProblemType getProblem() {
        return problem;
    }

    public void setProblem(ProblemType problem) {
        this.problem = problem;
    }

    public Set<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(Set<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

    @Override
    public String toString() {
        return "Input[inputId: " + inputId + ", name: " + name + ", data: " + data + ", problem: " + problem + "]";
    }

}
