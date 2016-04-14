package com.alwajabat.alwajabatdataentry.models;

/**
 * Created by sehalsein on 14/04/16.
 */
public class CuisineModel {

    private String id;
    private String name;

    public CuisineModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CuisineModel() {
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
