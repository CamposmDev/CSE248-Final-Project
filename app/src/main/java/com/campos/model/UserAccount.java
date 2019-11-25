package com.campos.model;

public class UserAccount {
    private String username;
    private String password;
    private Student student;

    public UserAccount(String username, String password, Student student) {
        this.username = username;
        this.password = password;
        this.student = student;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", student=" + student +
                '}';
    }
}
