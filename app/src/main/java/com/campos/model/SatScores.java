package com.campos.model;

public class SatScores {
    private int criticalReadingScore;
    private int mathScore;
    private int writingScore;

    public SatScores(int criticalReadingScore, int mathScore, int writingScore) {
        this.criticalReadingScore = criticalReadingScore;
        this.mathScore = mathScore;
        this.writingScore = writingScore;
    }

    public int getCriticalReadingScore() {
        return criticalReadingScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public int getWritingScore() {
        return writingScore;
    }

    public int getTotalScore() {
        return criticalReadingScore + mathScore + writingScore;
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
