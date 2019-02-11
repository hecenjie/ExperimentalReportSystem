package com.nanwulife.pojo;

public class User {
    private Integer id;

    private Long stuNum;

    private Integer stuClass;

    private String password;

    private Integer role;

    private Integer majorId;

    private String stuName;

    public User(Integer id, Long stuNum, Integer stuClass, String password, Integer role, Integer majorId, String stuName) {
        this.id = id;
        this.stuNum = stuNum;
        this.stuClass = stuClass;
        this.password = password;
        this.role = role;
        this.majorId = majorId;
        this.stuName = stuName;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStuNum() {
        return stuNum;
    }

    public void setStuNum(Long stuNum) {
        this.stuNum = stuNum;
    }

    public Integer getStuClass() {
        return stuClass;
    }

    public void setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", stuNum=" + stuNum +
                ", stuClass=" + stuClass +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", majorId=" + majorId +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}