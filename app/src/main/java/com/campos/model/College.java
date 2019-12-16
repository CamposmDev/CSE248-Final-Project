package com.campos.model;

public class College {
    private String id;
    private String name;
    private Address address;
    private String url;
    private TuitionInfo tuitionInfo;
    private int latestStudentSize;
    private double admissionRate;
    private int degreesAwarded;
    private SatScoresInfo satScoresInfo;
    private int menOnly, womenOnly;

    public College(String id, String name, Address address, String url, TuitionInfo tuitionInfo, int latestStudentSize, double admissionRate, int degreesAwarded, SatScoresInfo satScoresInfo, int menOnly, int womenOnly) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.url = url;
        this.tuitionInfo = tuitionInfo;
        this.latestStudentSize = latestStudentSize;
        this.admissionRate = admissionRate;
        this.degreesAwarded = degreesAwarded;
        this.satScoresInfo = satScoresInfo;
        this.menOnly = menOnly;
        this.womenOnly = womenOnly;
    }

    public String getId() {
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

    public double getAdmissionRate() {
        return admissionRate;
    }

    public int getDegreesAwarded() {
        return degreesAwarded;
    }

    public SatScoresInfo getSatScoresInfo() {
        return satScoresInfo;
    }

    public int getMenOnly() {
        return menOnly;
    }

    public int getWomenOnly() {
        return womenOnly;
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
