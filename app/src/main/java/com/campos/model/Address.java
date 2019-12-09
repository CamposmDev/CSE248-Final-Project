package com.campos.model;

public class Address {
    private int regionId;
    private String zip;
    private String city;
    private String state;

    public Address(int regionId, String zip, String city, String state) {
        this.regionId = regionId;
        this.zip = zip;
        this.city = city;
        this.state = state;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "regionId=" + regionId +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
