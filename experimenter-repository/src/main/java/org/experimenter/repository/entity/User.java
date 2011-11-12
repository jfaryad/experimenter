package org.experimenter.repository.entity;

import java.util.List;

/**
 * Entity for database table USER.
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class User implements Entity {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
