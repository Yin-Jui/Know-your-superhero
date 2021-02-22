package com.example.knowyoursuperhero;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeroInfo {

    @SerializedName("name")
    private String name;
    @SerializedName("powerstats")
    private List powerStats;   // need to check the type, it will return JSON Array
    @SerializedName("appearance")
    private List appearnace;
    @SerializedName("biography")
    private List bio;
    @SerializedName("work")
    private List work;
    @SerializedName("connections")
    private List connection;
    @SerializedName("image")
    private String imageURL;

    public HeroInfo(String name, List powerStats, List appearnace, List bio, List work, List connection, String imageURL) {
        this.name = name;
        this.powerStats = powerStats;
        this.appearnace = appearnace;
        this.bio = bio;
        this.work = work;
        this.connection = connection;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public List getPowerStats() {
        return powerStats;
    }

    public List getAppearnace() {
        return appearnace;
    }

    public List getBio() {
        return bio;
    }

    public List getWork() {
        return work;
    }

    public List getConnection() {
        return connection;
    }

    public String getImageURL() {
        return imageURL;
    }
}
