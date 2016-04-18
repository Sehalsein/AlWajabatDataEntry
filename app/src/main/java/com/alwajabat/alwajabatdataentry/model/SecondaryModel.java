package com.alwajabat.alwajabatdataentry.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sehalsein on 14/04/16.
 */

@SuppressWarnings("serial")
public class SecondaryModel implements Serializable {

    private List<CuisineModel>  cuisines =  new ArrayList<>();
    private List<PaymentModel>  paymentModes=  new ArrayList<>();
    private List<TypeModel>     restaurantType=  new ArrayList<>();
    private List<AmmenitityModel> ammenities=  new ArrayList<>();
    private boolean delivery;
    private float costForTwo;
    private float minimumOrder;
    private float deliveryCharge;
    private int openHour, openMinute;
    private int closeHour, closeMinute;

    public SecondaryModel(List<CuisineModel> cuisines, List<PaymentModel> paymentModes, List<TypeModel> restaurantType,
                          List<AmmenitityModel> ammenities, boolean delivery, float costForTwo, float minimumOrder,
                          float deliveryCharge, int openHour, int openMinute, int closeHour, int closeMinute) {
        this.cuisines = cuisines;
        this.paymentModes = paymentModes;
        this.restaurantType = restaurantType;
        this.ammenities = ammenities;
        this.delivery = delivery;
        this.costForTwo = costForTwo;
        this.minimumOrder = minimumOrder;
        this.deliveryCharge = deliveryCharge;
        this.openHour = openHour;
        this.openMinute = openMinute;
        this.closeHour = closeHour;
        this.closeMinute = closeMinute;
    }

    public SecondaryModel() {
    }

    public List<CuisineModel> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<CuisineModel> cuisines) {
        this.cuisines = cuisines;
    }

    public List<PaymentModel> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(List<PaymentModel> paymentModes) {
        this.paymentModes = paymentModes;
    }

    public List<TypeModel> getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(List<TypeModel> restaurantType) {
        this.restaurantType = restaurantType;
    }

    public List<AmmenitityModel> getAmmenities() {
        return ammenities;
    }

    public void setAmmenities(List<AmmenitityModel> ammenities) {
        this.ammenities = ammenities;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public float getCostForTwo() {
        return costForTwo;
    }

    public void setCostForTwo(float costForTwo) {
        this.costForTwo = costForTwo;
    }

    public float getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(float minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public float getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(float deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public int getOpenHour() {
        return openHour;
    }

    public void setOpenHour(int openHour) {
        this.openHour = openHour;
    }

    public int getOpenMinute() {
        return openMinute;
    }

    public void setOpenMinute(int openMinute) {
        this.openMinute = openMinute;
    }

    public int getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(int closeHour) {
        this.closeHour = closeHour;
    }

    public int getCloseMinute() {
        return closeMinute;
    }

    public void setCloseMinute(int closeMinute) {
        this.closeMinute = closeMinute;
    }
}
