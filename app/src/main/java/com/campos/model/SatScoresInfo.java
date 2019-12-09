package com.campos.model;

public class SatScoresInfo {
    private SatScores percentile25th;
    private SatScores percentile75th;

    public SatScoresInfo(SatScores percentile25th, SatScores percentile75th) {
        this.percentile25th = percentile25th;
        this.percentile75th = percentile75th;
    }

    public SatScores getPercentile25th() {
        return percentile25th;
    }

    public SatScores getPercentile75th() {
        return percentile75th;
    }

    @Override
    public String toString() {
        return "SatScoresInfo{" +
                "percentile25th=" + percentile25th +
                ", percentile75th=" + percentile75th +
                '}';
    }
}
