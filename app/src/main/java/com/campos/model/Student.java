package com.campos.model;

public class Student {
    private Name name;
    private String email;
    private SatScores satScores;

    public Student(Name name, String email, SatScores satScores) {
        this.name = name;
        this.email = email;
        this.satScores = satScores;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SatScores getSatScores() {
        return satScores;
    }

    public void setSatScores(SatScores satScores) {
        this.satScores = satScores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", satScores=" + satScores +
                '}';
    }
}
