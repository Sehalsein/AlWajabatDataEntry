package com.alwajabat.alwajabatdataentry.models;

import java.util.List;

/**
 * Created by sehalsein on 14/04/16.
 */
public class PrimaryModel {
    private String restaurantName;
    private String hotelName;
    private String mallName;
    private String humanAddress;
    private String email;
    private String website;
    private String phoneNumber;
    private List<AreaModel> areas;

    public PrimaryModel() {
    }


    public PrimaryModel(String restaurantName, String hotelName, String mallName,
                        String humanAddress, String email, String website, String phoneNumber, List<AreaModel> areas) {
        this.restaurantName = restaurantName;
        this.hotelName = hotelName;
        this.mallName = mallName;
        this.humanAddress = humanAddress;
        this.email = email;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.areas = areas;
    }

    public List<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaModel> areas) {
        this.areas = areas;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getHumanAddress() {
        return humanAddress;
    }

    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
