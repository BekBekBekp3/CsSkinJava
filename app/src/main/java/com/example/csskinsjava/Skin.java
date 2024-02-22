package com.example.csskinsjava;

import com.google.gson.annotations.SerializedName;

public class Skin {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("skinFloat")
    private Float skinFloat;

    @SerializedName("rarity")
    private String rarity;

    @SerializedName("quality")
    private String quality;

    @SerializedName("ImageURL")
    private String imageURL;

    @SerializedName("StatTrak")
    private Boolean statTrak;

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Float getSkinFloat() {
        return skinFloat;
    }

    public String getRarity() {
        return rarity;
    }

    public String getQuality() {
        return quality;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Boolean getStatTrak() {
        return statTrak;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSkinFloat(Float skinFloat) {
        this.skinFloat = skinFloat;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setStatTrak(Boolean statTrak) {
        this.statTrak = statTrak;
    }
}
