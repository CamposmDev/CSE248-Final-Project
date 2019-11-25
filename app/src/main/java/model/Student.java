package model;

public class Student {
    private Name name;
    private String email;
    private int satReadingScore;
    private int satMathScore;

    public Student(Name name, String email, int satReadingScore, int satMathScore) {
        this.name = name;
        this.email = email;
        this.satReadingScore = satReadingScore;
        this.satMathScore = satMathScore;
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

    public int getSatReadingScore() {
        return satReadingScore;
    }

    public void setSatReadingScore(int satReadingScore) {
        this.satReadingScore = satReadingScore;
    }

    public int getSatMathScore() {
        return satMathScore;
    }

    public void setSatMathScore(int satMathScore) {
        this.satMathScore = satMathScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", satReadingScore=" + satReadingScore +
                ", satMathScore=" + satMathScore +
                '}';
    }
}
