package com.example.knowyoursuperhero;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

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

    public String getValueAppearnace(String key) {
        LinkedTreeMap temp = (LinkedTreeMap) appearnace;
        return (String) temp.get(key);
    }

    public String getHeightAppearnace(String key) {
        LinkedTreeMap temp = (LinkedTreeMap) appearnace;
        if(key.equals("height")) {
            List myList = (List) temp.get("height");
            return (String) myList.get(0);
        } else if(key.equals("weight")) {
            List myList = (List) temp.get("weight");
            return (String) myList.get(1);
        }
        return "a"; // this is just for compiler not to yell
    }

    public String getValueImageURL() {
        LinkedTreeMap temp = (LinkedTreeMap) imageURL;
        return (String) temp.get("url");
    }

    public String getValuePowerstat(String key) {
        LinkedTreeMap temp = (LinkedTreeMap) powerStats;
        return (String)  temp.get(key);
    }

}
