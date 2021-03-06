package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table APPLICXATION
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "APPLICATION")
@NamedQueries({ @NamedQuery(
        name = Application.Q_GET_BY_PROGRAM,
        query = "select a from Application a where a.program = :program " + "order by a.version",
        readOnly = true) })
public class Application implements Entity {

    private static final long serialVersionUID = 1L;

    public static final String Q_GET_BY_PROGRAM = "Application.Q_GET_BY_PROGRAM";

    @Column(name = "application_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "executable", nullable = false)
    private String executable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", referencedColumnName = "program_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Program program;

    @OneToMany(mappedBy = "application", orphanRemoval = true)
    private List<Experiment> experiments;

    public Application() {

    }

    public Application(Integer id) {
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
