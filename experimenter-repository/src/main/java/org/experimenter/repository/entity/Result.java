package org.experimenter.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table RESULT
 * 
 * @author jfaryad
 * 
 */
@javax.persistence.Entity
@Table(name = "RESULT")
@NamedQueries({
        @NamedQuery(
                name = Result.Q_GET_BY_EXPERIMENT_INPUT_AND_PARAM,
                query = "select r from Result r " +
                        "inner join r.experiment as ex " +
                        "inner join r.input as i " +
                        "where ex.id = :experimentId " +
                        "and i.id = :inputId " +
                        "and r.param = :param",
                readOnly = false),
        @NamedQuery(
                name = Result.Q_GET_BY_EXPERIMENT,
                query = "select r from Result r " +
                        "inner join r.experiment as ex " +
                        "inner join fetch r.input as i " +
                        "where ex = :experiment",
                readOnly = false),
        @NamedQuery(
                name = Result.Q_GET_PARAMS_BY_EXPERIMENT_LIST,
                query = "select distinct r.param from Result r " +
                        "inner join r.experiment as ex " +
                        "where ex.id in (:experimentIds)",
                readOnly = false),
        @NamedQuery(
                name = Result.Q_GET_RESULTS_FOR_PARAM,
                query = "select r from Result r " +
                        "inner join r.experiment as ex " +
                        "where ex.id in (:experimentIds) " +
                        "and r.param = :param",
                readOnly = false) })
public class Result implements Entity {

    private static final long serialVersionUID = 1L;
    public static final String Q_GET_BY_EXPERIMENT_INPUT_AND_PARAM = "Result.Q_GET_BY_EXPERIMENT_INPUT_AND_PARAM";
    public static final String Q_GET_BY_EXPERIMENT = "Result.Q_GET_BY_EXPERIMENT";
    public static final String Q_GET_PARAMS_BY_EXPERIMENT_LIST = "Result.Q_GET_PARAMS_BY_EXPERIMENT_LIST";
    public static final String Q_GET_RESULTS_FOR_PARAM = "Result.Q_GET_RESULTS_FOR_PARAM";

    @Column(name = "result_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_id", referencedColumnName = "experiment_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Experiment experiment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_id", referencedColumnName = "input_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Input input;

    @Column(name = "param", nullable = false)
    String param;

    @Column(name = "value", nullable = true)
    BigDecimal value;

    public Result() {

    }

    public Result(Integer id) {
        this.id = id;
    }

    public Result(Experiment experiment, Input input, String param, BigDecimal value) {
        this.experiment = experiment;
        this.input = input;
        this.param = param;
        this.value = value;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Result)) {
            return false;
        }
        final Result other = (Result) o;
        return other.getId() == id;
    }

    @Override
    public String toString() {
        return "Result[experimentId: " + experiment + ", inputId: " + input + ", param: " + param
                + "value: "
                + value + "]";
    }
}
