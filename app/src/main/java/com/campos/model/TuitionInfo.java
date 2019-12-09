package com.campos.model;

public class TuitionInfo {
    private int tuitionInState;
    private int tuitionOutOfState;

    public TuitionInfo(int tuitionInState, int tuitionOutOfState) {
        this.tuitionInState = tuitionInState;
        this.tuitionOutOfState = tuitionOutOfState;
    }

    public int getTuitionInState() {
        return tuitionInState;
    }

    public int getTuitionOutOfState() {
        return tuitionOutOfState;
    }

    @Override
    public String toString() {
        return "TuitionInfo{" +
                "tuitionInState=" + tuitionInState +
                ", tuitionOutOfState=" + tuitionOutOfState +
                '}';
    }
}
