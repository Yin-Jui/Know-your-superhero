package com.example.knowyoursuperhero;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * this class will store the results form the api in a
 */

public class Hero {
    @SerializedName("results")
    private List<HeroInfo> myHero;

    public Hero(List<HeroInfo> myHero) {
        this.myHero = myHero;
    }

    public List<HeroInfo> getMyHero() {
        return myHero;
    }
}
