package com.alwajabat.alwajabatdataentry.models;

/**
 * Created by sehalsein on 14/04/16.
 */
public class AreaModel {

    private String id;
    private String name;

    public AreaModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AreaModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
