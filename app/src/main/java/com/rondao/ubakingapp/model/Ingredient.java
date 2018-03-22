package com.rondao.ubakingapp.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    private float quantity;
    private String measure;
    @SerializedName("ingredient")
    private String name;

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
