package org.experimenter.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entity for database table USER.
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
@javax.persistence.Entity
@Table(name = "USER")
@NamedQueries({
        @NamedQuery(
                name = User.Q_GET_BY_USERGROUP,
                query = "select u from User u join u.userGroups ug where ug = :userGroup order by u.surname, u.name",
                readOnly = true),
        @NamedQuery(
                name = User.Q_GET_BY_LOGIN_AND_PASSWORD,
                query = "from User where login = :userGroup and password = :password",
                readOnly = true) })
public class User implements Entity {

    private static final long serialVersionUID = 1L;

    public static final String Q_GET_BY_USERGROUP = "User.Q_GET_BY_USERGROUP";
    public static final String Q_GET_BY_LOGIN_AND_PASSWORD = "User.Q_GET_BY_LOGIN_AND_PASSWORD";

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "admin", nullable = false)
    private Boolean isAdmin;

    @ManyToMany
    @JoinTable(name = "USER_USERGROUP", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(
            name = "usergroup_id"))
    private List<UserGroup> userGroups;

    public User() {

    }

    public User(Integer id) {
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

    public String getFullName() {
        return name + " " + surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "User[id: " + getId() + ", name: " + name + ", surname: " + surname + ", login: " + login
                + ", password: " + password + ", email: " + email + "]";
    }

}
