package org.experimenter.repository.entity;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table INPUT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "INPUT")
@NamedQueries({
        @NamedQuery(
                name = Input.Q_GET_BY_CHECKSUM,
                query = "select i from Input i " +
                        "where i.checksum = :checksum ",
                readOnly = true) })
public class Input implements Entity {

    private static final long serialVersionUID = 1L;
    public static final String Q_GET_BY_CHECKSUM = "Experiment.Q_GET_BY_CHECKSUM";

    public static final Comparator<Input> COMPARATOR_BY_NAME = new ByNameComparator();

    @Column(name = "input_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "checksum", nullable = false, unique = true)
    private String checksum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private ProblemType problem;

    @ManyToMany(mappedBy = "inputs")
    private List<InputSet> inputSets;

    @OneToMany(mappedBy = "input")
    private List<Result> results;

    public Input() {

    }

    public Input(Integer id) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public ProblemType getProblem() {
        return problem;
    }

    public void setProblem(ProblemType problem) {
        this.problem = problem;
    }

    public List<InputSet> getInputSets() {
        return inputSets;
    }

    public void setInputSets(List<InputSet> inputSets) {
        this.inputSets = inputSets;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Input)) {
            return false;
        }
        final Input other = (Input) o;
        return other.getId() == id;
    }

    @Override
    public String toString() {
        return "Input[id: " + getId() + ", name: " + name + ", data: " + data + ", problem: " + problem + "]";
    }

    private static final class ByNameComparator implements Comparator<Input> {
        @Override
        public int compare(final Input i1, final Input i2) {
            return i1.getName().compareTo(i2.getName());
        }
    }

}
