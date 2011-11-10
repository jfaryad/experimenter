package org.experimenter.repository.model;

import java.util.Set;

/**
 * Entity for database table USER.
 * 
 * @author Jakub Faryad (jfaryad@gmail.com)
 * 
 */
public class User implements Model {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private Set<UserGroup> userGroups;

    public User() {

    }

    public User(Integer userId) {
        this.userId = userId;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "User[userId: " + userId + ", name: " + name + ", surname: " + surname + ", login: " + login
                + ", password: " + password + ", email: " + email + "]";
    }

    @Override
    public String toDebugString() {
        return "User[userId: " + userId + ", name: " + name + ", surname: " + surname + ", login: " + login
                + ", password: " + password + ", email: " + email + ", userGroups.size: "
                + ((userGroups != null) ? userGroups.size() : null) + "]";
    }

}
