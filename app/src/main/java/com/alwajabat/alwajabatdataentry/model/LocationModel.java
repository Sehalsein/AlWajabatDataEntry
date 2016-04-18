package com.alwajabat.alwajabatdataentry.model;

import java.io.Serializable;

/**
 * Created by sehalsein on 14/04/16.
 */

@SuppressWarnings("serial")
public class LocationModel implements Serializable {

    private String lattitude;
    private String longitude;

    public LocationModel(String lattitude, String longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public LocationModel() {
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
