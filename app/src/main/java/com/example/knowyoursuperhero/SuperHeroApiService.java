package com.example.knowyoursuperhero;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SuperHeroApiService {
    //need to get access token
    @GET("search/{name}")
    Call<Hero> getHero(@Path("name") String name);

//    @GET("{id}/image")
//    Call<Hero> getHeroImage(@Path("id") int character_id);
}
