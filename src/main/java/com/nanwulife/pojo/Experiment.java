package com.nanwulife.pojo;

public class Experiment {
    private Integer id;

    private String name;

    private Integer open;

    public Experiment(Integer id, String name, Integer open) {
        this.id = id;
        this.name = name;
        this.open = open;
    }

    public Experiment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }
}