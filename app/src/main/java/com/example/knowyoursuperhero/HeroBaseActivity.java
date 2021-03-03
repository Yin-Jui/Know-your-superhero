package com.example.knowyoursuperhero;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroBaseActivity extends AppCompatActivity {
    static final String BASE_URL = "https://www.superheroapi.com/api.php/1133497440454608/";

    static final String TAG = HeroBaseActivity.class.getSimpleName();
    static Retrofit retrofit = null;
    private superHeroAdapter supAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hero_list);

//        setContentView(R.layout.activity_hero_base);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        toolBarLayout.setTitle(getTitle());
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        findViewById(R.id.search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testing", "Onclick search botton!!!!");
                EditText searchResult = findViewById(R.id.search_bar);
                String superHero = searchResult.getText().toString();
                connect(superHero);
            }
        });

        connect("batman");
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
                List<HeroInfo> listOfHero = response.body().getMyHero();
                Log.e(TAG, "the size of list " + listOfHero.size());
                supAdapter = new superHeroAdapter(listOfHero);
                recyclerView = findViewById(R.id.supHeroList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(supAdapter);
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}