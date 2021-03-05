package com.example.knowyoursuperhero;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class ResultActivity extends AppCompatActivity {

    private List<HeroInfo> result_list;
    public static final String results = "results";
    private static Retrofit retrofit = null;
    static final String BASE_URL = "https://www.superheroapi.com/api.php/1133497440454608/";
    private HashMap<HeroInfo, Double> distances;
    private int[] result_scores;
    private TextView hero;
    private int count = 0;

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


        //hardcode 10 potential results, might be modified
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


        final Button btnMain = findViewById(R.id.btn_toMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
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
                Log.e("on_response", "the size of hero list " + result_list.size());
                count++;


                if(count == 10){
                    Log.e("last", result_list.size()+" ");
                    for(HeroInfo heroInfo : result_list){
                        double a = calculate_distance(heroInfo);
                        distances.put(heroInfo,a);
                    }

                    Collections.sort(result_list, new Comparator<HeroInfo>() {
                        @Override
                        public int compare(HeroInfo heroInfo, HeroInfo t1) {
                            if(distances.get(heroInfo) < distances.get(t1) )
                                return -1;
                            else if(distances.get(heroInfo) > distances.get(t1))
                                return 1;
                            return 0;
                        }
                    });

                    String res = result_list.get(0).getName();
                    String image_url = result_list.get(0).getValueImageURL();
                    hero.setText(res);
                    //Write into database
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user != null) {
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseFirestore.getInstance().collection("users")
                                .document(uid)
                                .update("hero", res);

                        FirebaseFirestore.getInstance().collection("users")
                                .document(uid)
                                .update("imageUrl", image_url);
                    }
                }
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Log.e("result_page", t.toString());
            }
        });
    }

    private double calculate_distance(HeroInfo hero){

        double sum = 0;
        String value;
        Log.d("cal_",hero.getName());
        value = hero.getValuePowerstat("intelligence");
        if(value.equals("null")){
            return Integer.MAX_VALUE;
        }

        sum += Math.pow(Integer.parseInt(value)-result_scores[0],2);

        value = hero.getValuePowerstat("strength");
        if(value.equals("null")){
            return Integer.MAX_VALUE;
        }

        sum += Math.pow(Integer.parseInt(value)-result_scores[1],2);
        value = hero.getValuePowerstat("speed");
        if(value.equals("null")){
            return Integer.MAX_VALUE;

        }

        sum += Math.pow(Integer.parseInt(value)-result_scores[2],2);
        value = hero.getValuePowerstat("durability");
        if(value.equals("null")){

            return Integer.MAX_VALUE;
        }

        sum += Math.pow(Integer.parseInt(value)-result_scores[3],2);
        value = hero.getValuePowerstat("power");
        if(value.equals("null")){
            return Integer.MAX_VALUE;

        }

        sum += Math.pow(Integer.parseInt(value)-result_scores[4],2);
        value = hero.getValuePowerstat("combat");
        if(value.equals("null")){
            return Integer.MAX_VALUE;

        }

        sum += Math.pow(Integer.parseInt(value)-result_scores[5],2);

        Log.e("distance",hero.getName()+": "+ Double.toString(sum));

        return sum;
    }


}
