package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private LinkedList<Object[]> chosen;

    private static Retrofit retrofit = null;
    static final String BASE_URL = "https://www.superheroapi.com/api.php/1133497440454608/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_fight);
        result_list = new ArrayList<>();
        hero_list = new ArrayList<>();
        chosen = new LinkedList<>();
        images = new ArrayList<>();

        Button main_menu = findViewById(R.id.button2);
        main_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn_fight_rslt = findViewById(R.id.button3);
        btn_fight_rslt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FightResultActivity.class);
                String[] chosen_str = new String[3];
                for(int i = 0 ; i < 2 ; i++){
                    chosen_str[i] = ((HeroInfo)chosen.get(i)[0]).getValueImageURL();
                }
                if( Integer.parseInt( ((HeroInfo)chosen.get(0)[0]).getValuePowerstat("combat") )> Integer.parseInt (((HeroInfo)chosen.get(1)[0]).getValuePowerstat("combat")))
                    chosen_str[2] = "0";
                else
                    chosen_str[2] = "1";
                intent.putExtra(FightResultActivity.chosen,chosen_str);
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
                        int finalI = i;
                        ivBasicImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(ivBasicImage.getBackground() == null) {
                                    Drawable highlight = getResources().getDrawable(R.drawable.highlight_hero_fight);
                                    ivBasicImage.setBackground(highlight);
                                    chosen.add(new Object[]{hero_list.get(finalI), ivBasicImage});
                                    if (chosen.size() > 2) {
                                        Object[] temp = chosen.pop();
                                        ((ImageView) temp[1]).setBackground(null);
                                    }

                                }else{
                                    ivBasicImage.setBackground(null);
                                    for(Object[] o : chosen){
                                        if(o[1] == ivBasicImage){
                                            chosen.remove(o);
                                            break;
                                        }
                                    }
                                }
                                for(Object[] h : chosen)
                                    Log.e("click_", ((HeroInfo)h[0]).getName());
                            }
                        });

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