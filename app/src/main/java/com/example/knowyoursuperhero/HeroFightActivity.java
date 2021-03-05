package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroFightActivity extends AppCompatActivity {

//    private RecyclerView recyclerView;
    private int count = 0;
    private List<HeroInfo> result_list;
    private List<HeroInfo> hero_list;
    private List<ImageView> images;

    private static Retrofit retrofit = null;
    static final String BASE_URL = "https://www.superheroapi.com/api.php/1133497440454608/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_fight);
        result_list = new ArrayList<>();
        hero_list = new ArrayList<>();
        images = new ArrayList<>();

        Button main_menu = findViewById(R.id.button2);
        main_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        images.add((ImageView) findViewById(R.id.iv_1_1));
        images.add((ImageView) findViewById(R.id.iv_1_2));
        images.add((ImageView) findViewById(R.id.iv_1_3));
        images.add((ImageView) findViewById(R.id.imageView12));
        images.add((ImageView) findViewById(R.id.imageView10));
        images.add((ImageView) findViewById(R.id.imageView11));

        images.add((ImageView) findViewById(R.id.iv_3_1));
        images.add((ImageView) findViewById(R.id.iv_3_2));
        images.add((ImageView) findViewById(R.id.iv_3_3));
        images.add((ImageView) findViewById(R.id.imageView6));
        images.add((ImageView) findViewById(R.id.imageView5));
        images.add((ImageView) findViewById(R.id.imageView4));
        images.add((ImageView) findViewById(R.id.imageView8));
        images.add((ImageView) findViewById(R.id.imageView9));
        images.add((ImageView) findViewById(R.id.imageView7));

        connect("spider-man");
        connect("batman");
        connect("groot");
        connect("Iceman");
        connect("X-Man");
        connect("Hulk");
        connect("Ant-Man");
        connect("Armor");
        connect("Arsenal");
        connect("Godzilla");
        connect("Iron Man");

//        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
//        ImageView ivBasicImage1 = (ImageView) findViewById(R.id.iv_1_1);
//        Picasso.get().load(imageUri).resize(450,450).centerCrop().into(ivBasicImage1);
//        ImageView ivBasicImage2 = (ImageView) findViewById(R.id.iv_1_2);
//        Picasso.get().load(imageUri).resize(450,450).centerCrop().into(ivBasicImage2);
//        ImageView ivBasicImage3 = (ImageView) findViewById(R.id.iv_1_3);
//        Picasso.get().load(imageUri).resize(450,450).centerCrop().into(ivBasicImage3);


//        recyclerView = findViewById(R.id.herofightlist);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(supAdapter);
    }



    public void connect(String superHero) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        SuperHeroApiService superHeroApiService = retrofit.create(SuperHeroApiService.class);
        Call<Hero> call = superHeroApiService.getHero(superHero);

        call.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {


                result_list.addAll(response.body().getMyHero());
                count++;


                if(count == 11){

                    for(HeroInfo hero : result_list){
                        if(!hero.getValuePowerstat("combat").equals("null"))
                            hero_list.add(hero);
                    }

                    Log.e("count" ,Integer.toString( hero_list.size()));
                    for(int i = 0 ; i < Math.min(hero_list.size(),images.size()) ; i ++){


                        ImageView ivBasicImage = images.get(i);
                        Picasso.get().load(hero_list.get(i).getValueImageURL()).resize(450,450).centerCrop().into(ivBasicImage);

//                        double a = calculate_distance(heroInfo);
//                        distances.put(heroInfo,a);
                    }
                }
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Log.e("result_page", t.toString());
            }
        });
    }
}