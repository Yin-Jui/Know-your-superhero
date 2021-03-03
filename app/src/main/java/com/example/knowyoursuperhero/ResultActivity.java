package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {

    private List<HeroInfo> result_list;
    public static final String results = "results";
    private static Retrofit retrofit = null;
    static final String BASE_URL = "https://www.superheroapi.com/api.php/1133497440454608/";
    private HashMap<HeroInfo, Integer> distances;
    private int[] result_scores;
    private TextView hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        distances = new HashMap<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_list = new ArrayList<>();
        Intent intent = getIntent();
        result_scores = intent.getIntArrayExtra(results);
        Log.d("score_in_result", Integer.toString( result_scores.length));
        TextView intelligence = findViewById(R.id.tv_Intelligence);
        TextView strength = findViewById(R.id.tv_strength);
        TextView speed = findViewById(R.id.tv_speed);
        TextView durability = findViewById(R.id.tv_durability);
        TextView power = findViewById(R.id.tv_power);
        TextView combat = findViewById(R.id.tv_combat);
        hero = findViewById(R.id.heroName);

        intelligence.setText("Intelligence: "+result_scores[0]);
        strength.setText("Strength: "+result_scores[1]);
        speed.setText("Speed: "+result_scores[2]);
        durability.setText("Durability: "+result_scores[3]);
        power.setText("Power: "+result_scores[4]);
        combat.setText("Combat: "+result_scores[5]);

        connect("spider-man",false);
        connect("batman",false);
        connect("groot",true);
//        while(result_list.size() < 7){
//            Log.e("result_page", "the size of hero list " + result_list.size());
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        for(HeroInfo heroInfo : result_list){
//            int a = calculate_distance(heroInfo);
//            distances.put(heroInfo,a);
//            Log.e("result_page", heroInfo.getName()+": " + a);
//        }
//
//        Collections.sort(result_list, new Comparator<HeroInfo>() {
//            @Override
//            public int compare(HeroInfo heroInfo, HeroInfo t1) {
//                return distances.get(heroInfo) - distances.get(t1);
//            }
//        });


//        Log.e("result_page", result_list.get(0).getName());




        //mechanism for determining hero
//        String res = "test";
//        hero.setText(res);
//        //Write into database
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null) {
//            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            FirebaseFirestore.getInstance().collection("users")
//                    .document(uid)
//                    .update("hero", res);
//        }


        final Button btnMain = findViewById(R.id.btn_toMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }



    public void connect(String superHero, boolean last) {
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
                Log.e("result_page", "the size of hero list " + result_list.size());

//                supAdapter = new superHeroAdapter(listOfHero);
//                recyclerView = findViewById(R.id.supHeroList);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(supAdapter);


                if(last){
                    for(HeroInfo heroInfo : result_list){
                        int a = calculate_distance(heroInfo);
                        distances.put(heroInfo,a);
                        Log.e("result_page", heroInfo.getName()+": " + a);
                    }

                    Collections.sort(result_list, new Comparator<HeroInfo>() {
                        @Override
                        public int compare(HeroInfo heroInfo, HeroInfo t1) {
                            return distances.get(heroInfo) - distances.get(t1);
                        }
                    });


                    String res = result_list.get(0).getName();
                    hero.setText(res);
                    //Write into database
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user != null) {
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseFirestore.getInstance().collection("users")
                                .document(uid)
                                .update("hero", res);
                    }
                }
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Log.e("result_page", t.toString());
            }
        });
    }

    private int calculate_distance(HeroInfo hero){

        int sum = 0;
        sum += Math.pow(Integer.parseInt(hero.getValuePowerstat("intelligence"))-result_scores[0],2);
        sum += Math.pow(Integer.parseInt(hero.getValuePowerstat("strength"))-result_scores[1],2);
        sum += Math.pow(Integer.parseInt(hero.getValuePowerstat("speed"))-result_scores[2],2);
        sum += Math.pow(Integer.parseInt(hero.getValuePowerstat("durability"))-result_scores[3],2);
        sum += Math.pow(Integer.parseInt(hero.getValuePowerstat("power"))-result_scores[4],2);
        sum += Math.pow(Integer.parseInt(hero.getValuePowerstat("combat"))-result_scores[5],2);

        return sum;
    }


}