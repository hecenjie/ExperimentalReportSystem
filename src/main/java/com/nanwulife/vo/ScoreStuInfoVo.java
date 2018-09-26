package com.nanwulife.vo;

public class ScoreStuInfoVo {

    private Integer stuId;

    private Long stuNum;
    
    private String stuName;
    
    private Integer expId;
    
    private String expName;
    
    private Integer Score;
    
    private Integer majorId;
    
    private String majorName;
    
    private Integer stuClass;

    public Long getStuNum() {
        return stuNum;
    }

    public void setStuNum(Long stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getExpId() {
        return expId;
    }

    public void setExpId(Integer expId) {
        this.expId = expId;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getStuClass() {
        return stuClass;
    }

    public void setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}
