package com.example.knowyoursuperhero;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeroInfo {

    @SerializedName("name")
    private final String name;
    @SerializedName("powerstats")
    private final Object powerStats;   // need to check the type, it will return JSON Array
    @SerializedName("appearance")
    private final Object appearnace;
    @SerializedName("biography")
    private final Object bio;
    @SerializedName("work")
    private final Object work;
    @SerializedName("connections")
    private final Object connection;
    @SerializedName("image")
    private final Object imageURL;

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

    public Object getPowerStats() {
        return powerStats;
    }

    public Object getAppearnace() {
        return appearnace;
    }

    public Object getBio() {
        return bio;
    }

    public Object getWork() {
        return work;
    }

    public Object getConnection() {
        return connection;
    }

    public Object getImageURL() {
        return imageURL;
    }
}
