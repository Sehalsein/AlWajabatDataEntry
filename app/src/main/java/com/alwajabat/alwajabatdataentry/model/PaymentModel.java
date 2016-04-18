package com.alwajabat.alwajabatdataentry.model;

import java.io.Serializable;

/**
 * Created by sehalsein on 14/04/16.
 */

@SuppressWarnings("serial")
public class PaymentModel implements Serializable {

    private String id;
    private String name;

    public PaymentModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentModel() {
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