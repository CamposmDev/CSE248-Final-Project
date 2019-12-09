package com.campos.model;

public class School {
    private int id;
    private String name;
    private Address address;
    private String url;
    private TuitionInfo tuitionInfo;
    private int latestStudentSize;
    private float admissionRate;
    private int degreesAwarded;
    private SatScoresInfo satScoresInfo;

    public School(int id, String name, Address address, String url, TuitionInfo tuitionInfo, int latestStudentSize, float admissionRate, int degreesAwarded, SatScoresInfo satScoresInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.url = url;
        this.tuitionInfo = tuitionInfo;
        this.latestStudentSize = latestStudentSize;
        this.admissionRate = admissionRate;
        this.degreesAwarded = degreesAwarded;
        this.satScoresInfo = satScoresInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public TuitionInfo getTuitionInfo() {
        return tuitionInfo;
    }

    public int getLatestStudentSize() {
        return latestStudentSize;
    }

    public float getAdmissionRate() {
        return admissionRate;
    }

    public int getDegreesAwarded() {
        return degreesAwarded;
    }

    public SatScoresInfo getSatScoresInfo() {
        return satScoresInfo;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", url='" + url + '\'' +
                ", tuitionInfo=" + tuitionInfo +
                ", latestStudentSize=" + latestStudentSize +
                ", admissionRate=" + admissionRate +
                ", degreesAwarded=" + degreesAwarded +
                ", satScoresInfo=" + satScoresInfo +
                '}';
    }
}
