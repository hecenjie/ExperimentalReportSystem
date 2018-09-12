package com.nanwulife.pojo;

public class ScoreKey {
    private Integer stuId;

    private Integer expId;

    public ScoreKey(Integer stuId, Integer expId) {
        this.stuId = stuId;
        this.expId = expId;
    }

    public ScoreKey() {
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
}