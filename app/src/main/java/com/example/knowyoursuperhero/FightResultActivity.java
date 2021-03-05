package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import com.google.gson.Gson;

public class FightResultActivity extends AppCompatActivity {

    public static String chosen = "chosen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_result);

        Intent intent = getIntent();
        String[] urls = intent.getStringArrayExtra(chosen);
        int i;
        if(urls[2].equals("0")){
            i = 0;
        }else
            i = 1;
        ImageView ivBasicImage =  findViewById(R.id.imageView13);
        Picasso.get().load(urls[i]).resize(450,450).centerCrop().into(ivBasicImage);

        Button main_menu = findViewById(R.id.button4);
        main_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });


//        ivBasicImage = findViewById(R.id.imageView14);
//        Picasso.get().load(urls[1]).resize(450,450).centerCrop().into(ivBasicImage);

//        String jsonMyObject;
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            jsonMyObject = extras.getString("myObject");
//        }
//        Object[] myObject = new Gson().fromJson(chosen, (new Object[]{}).getClass());
    }
}