package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    public static final String results = "results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int[] result_scores = intent.getIntArrayExtra(results);
        Log.d("score_in_result", Integer.toString( result_scores.length));
        TextView intelligence = findViewById(R.id.tv_Intelligence);
        TextView strength = findViewById(R.id.tv_strength);
        TextView speed = findViewById(R.id.tv_speed);
        TextView durability = findViewById(R.id.tv_durability);
        TextView power = findViewById(R.id.tv_power);
        TextView combat = findViewById(R.id.tv_combat);
        TextView hero = findViewById(R.id.heroName);

        intelligence.setText("Intelligence: "+result_scores[0]);
        strength.setText("Strength: "+result_scores[1]);
        speed.setText("Speed: "+result_scores[2]);
        durability.setText("Durability: "+result_scores[3]);
        power.setText("Power: "+result_scores[4]);
        combat.setText("Combat: "+result_scores[5]);



        //mechanism for determining hero
        String res = "Superman";
        hero.setText(res);
        //Write into database
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseFirestore.getInstance().collection("users")
                    .document(uid)
                    .update("hero", res);
        }


        final Button btnMain = findViewById(R.id.btn_toMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}