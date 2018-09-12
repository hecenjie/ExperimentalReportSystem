package com.nanwulife.pojo;

public class User {
    private Integer id;

    private Integer stuNum;

    private Integer stuClass;

    private String password;

    private Integer role;

    private Integer majorId;

    public User(Integer id, Integer stuNum, Integer stuClass, String password, Integer role, Integer majorId) {
        this.id = id;
        this.stuNum = stuNum;
        this.stuClass = stuClass;
        this.password = password;
        this.role = role;
        this.majorId = majorId;
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
}