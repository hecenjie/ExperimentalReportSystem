package com.nanwulife.vo;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 学生基本信息Vo
 * @Author: Cenjie
 * @Date: Created in 2018/9/16
 */
public class StuBasicInfoVo {

    private Integer id;

    private Integer stuNum;

    private Integer stuClass;

    private Integer majorId;

    private String majorName;

    private String stuName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public Integer getStuClass() {
        return stuClass;
    }

    public void setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
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

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
