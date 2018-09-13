package com.nanwulife.pojo;

public class Score {
    private Integer stuId;

    private Integer expId;

    private Integer score;

    public Score(Integer stuId, Integer expId, Integer score) {
        this.stuId = stuId;
        this.expId = expId;
        this.score = score;
    }

    public Score() {
        super();
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getExpId() {
        return expId;
    }

    public void setExpId(Integer expId) {
        this.expId = expId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}