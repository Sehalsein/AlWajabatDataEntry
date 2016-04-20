package com.alwajabat.alwajabatdataentry.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sehalsein on 14/04/16.
 */

@SuppressWarnings("serial")
public class TypeModel implements Serializable {

    @SerializedName("_id")
    private String id;
    private String name;

    public TypeModel() {
    }

    public TypeModel(String id, String name) {
        this.id = id;
        this.name = name;
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
