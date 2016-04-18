package com.alwajabat.alwajabatdataentry.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sehalsein on 14/04/16.
 */

@SuppressWarnings("serial")
public class CuisineModel implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
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