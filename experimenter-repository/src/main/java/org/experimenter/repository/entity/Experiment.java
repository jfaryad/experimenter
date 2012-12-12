package org.experimenter.repository.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.BeanUtils;

/**
 * Entity for database table EXPERIMENT
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "EXPERIMENT")
@NamedQueries({
        @NamedQuery(
                name = Experiment.Q_GET_BY_USER,
                query = "select e from Experiment e " +
                        "inner join fetch e.application as a " +
                        "inner join fetch a.program as prog " +
                        "inner join fetch prog.project as proj " +
                        "inner join proj.userGroup as g " +
                        "inner join g.users as u " +
                        "where u = :user " +
                        "order by prog.name, a.version, e.name",
                readOnly = false),
        @NamedQuery(
                name = Experiment.Q_GET_SCHEDULED,
                query = "select e from Experiment e " +
                        "where e.scheduledTime > current_timestamp()",
                readOnly = false) })
public class Experiment implements Entity {

    private static final long serialVersionUID = 1L;
    public static final String Q_GET_BY_USER = "Experiment.Q_GET_BY_USER";
    public static final String Q_GET_SCHEDULED = "Experiment.Q_GET_SCHEDULED";
    public static final String Q_GET_EXPERIMENTS_WITH_RESULTS = "Experiment.Q_GET_SCHEDULED";

    public static final String NATIVE_Q_GET_STATUS = "select status from job_status where experiment_id = :experimentId";
    public static final String NATIVE_Q_GET_STATUS_LIST = "select experiment_id, status from job_status where experiment_id in (:experimentIds)";
    public static final String NATIVE_CREATE_JOB = "insert into job_status (experiment_id, status) values (:experimentId, 0)";
    public static final String NATIVE_START_JOB = "update job_status set status = 1 where experiment_id = :experimentId";
    public static final String NATIVE_FINISH_JOB = "update job_status set status = 2 where experiment_id = :experimentId";
    public static final String NATIVE_RESET_JOB = "update job_status set status = 0 where experiment_id = :experimentId";
    public static final String NATIVE_DELETE_JOB = "delete from job_status where experiment_id = :experimentId";
    public static final String NATIVE_SET_RUNNING_JOBS_FAILED = "update job_status set status = 3 where status = 1";

    public static enum Status {
        NEW(0), RUNNING(1), FINISHED(2), FAILED(3);
        private Integer status;

        private Status(Integer status) {
            this.status = status;
        }

        public static Status fromValue(Integer status) {
            if (status == null) {
                return NEW;
            }
            switch (status) {
            case 0:
                return NEW;
            case 1:
                return RUNNING;
            case 2:
                return FINISHED;
            default:
                return FAILED;
            }
        }

        public boolean hasValue(Integer value) {
            return status.equals(value);
        }
    }

    public static final Integer STATUS_NEW = 0;
    public static final Integer STATUS_RUNNING = 1;
    public static final Integer STATUS_FINISHED = 2;

    @Column(name = "experiment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "scheduled_time")
    private Date scheduledTime;

    @Column(name = "max_jobs")
    private Integer maxRunningJobs;

    @Column(name = "command", nullable = false)
    private String command;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Application application;

    @ManyToMany
    @JoinTable(
            name = "EXPERIMENT_FARM",
            joinColumns = @JoinColumn(name = "experiment_id"),
            inverseJoinColumns = @JoinColumn(name = "farm_id"))
    private List<ConnectionFarm> connectionFarms;

    @ManyToMany
    @JoinTable(
            name = "EXPERIMENT_INPUT_SET",
            joinColumns = @JoinColumn(name = "experiment_id"),
            inverseJoinColumns = @JoinColumn(name = "input_set_id"))
    private List<InputSet> inputSets;

    @OneToMany(mappedBy = "experiment")
    private List<Result> results;

    public Experiment() {

    }

    public Experiment(Integer id) {
        this.id = id;
    }

    @Override
    public Experiment clone() {
        Experiment experiment = new Experiment();
        BeanUtils.copyProperties(this, experiment, new String[] { "id" });
        return experiment;
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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Integer getMaxRunningJobs() {
        return maxRunningJobs;
    }

    public void setMaxRunningJobs(Integer maxRunningJobs) {
        this.maxRunningJobs = maxRunningJobs;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
        if (!(o instanceof Experiment)) {
            return false;
        }
        final Experiment other = (Experiment) o;
        return other.getId() == id;
    }

    @Override
    public String toString() {
        return "Experiment[id: " + getId() + ", name: " + name + ", description: " + description + ", cronExpression: "
                + cronExpression + ", application: " + application + "]";
    }

}
