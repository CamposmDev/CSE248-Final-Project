package com.campos.model;

public class SatScores {
    private String criticalReadingScore;
    private String mathScore;
    private String writingScore;

    public SatScores(String criticalReadingScore, String mathScore, String writingScore) {
        this.criticalReadingScore = criticalReadingScore;
        this.mathScore = mathScore;
        this.writingScore = writingScore;
    }

    public String getCriticalReadingScore() {
        return criticalReadingScore;
    }

    public String getMathScore() {
        return mathScore;
    }

    public String getWritingScore() {
        return writingScore;
    }

    @Override
    public String toString() {
        return "SatScores{" +
                "criticalReadingScore=" + criticalReadingScore +
                ", mathScore=" + mathScore +
                ", writingScore=" + writingScore +
                '}';
    }
}
