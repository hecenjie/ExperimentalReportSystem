package com.nanwulife.pojo;

public class Score extends ScoreKey {
    private Integer score;

    public Score(Integer stuId, Integer expId, Integer score) {
        super(stuId, expId);
        this.score = score;
    }

    public Score() {
        super();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}