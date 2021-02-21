package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        defaultOptionsView();


        final View btnOption1 = findViewById(R.id.tv_option1);
        btnOption1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                defaultOptionsView();
                selectedOptionView((TextView) findViewById(R.id.tv_option1),1);

            }
        });

        final View btnOption2 = findViewById(R.id.tv_option2);
        btnOption2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                defaultOptionsView();

                selectedOptionView((TextView) findViewById(R.id.tv_option2),1);

            }
        });

        final View btnOption3 = findViewById(R.id.tv_option3);
        btnOption3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                defaultOptionsView();

                selectedOptionView((TextView) findViewById(R.id.tv_option3),1);

            }
        });

        final View btnOption4 = findViewById(R.id.tv_option4);
        btnOption4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                defaultOptionsView();

                selectedOptionView((TextView) findViewById(R.id.tv_option4),1);

            }
        });









//        tv_option1.setOnClickListener(this)
//        tv_option_two.setOnClickListener(this)
//        tv_option_three.setOnClickListener(this)
//        tv_option_four.setOnClickListener(this)
//        btn_submit.setOnClickListener(this)
    }

    private void selectedOptionView(TextView tv, int selectedNum) {



        tv.setTextColor(
                Color.parseColor("#FFFFFF")
        );
//        tv.setTypeface(tv.typeface, Typeface.BOLD);
//        tv.background = ContextCompat.getDrawable(
//                this@QuizQuestionsActivity,
//        R.drawable.selected_option_border_bg
//        )
    }

    private void defaultOptionsView() {

        List<TextView> options = new ArrayList<TextView>();
        options.add((TextView) findViewById(R.id.tv_option1));
        options.add((TextView) findViewById(R.id.tv_option2));
        options.add((TextView) findViewById(R.id.tv_option3));
        options.add((TextView) findViewById(R.id.tv_option4));

        for (TextView option : options) {
            option.setTextColor(Color.parseColor("#7A8089"));
//            option.typeface = Typeface.DEFAULT;
//            option.background = ContextCompat.getDrawable(
//                    this@QuizQuestionsActivity,
//            R.drawable.default_option_border_bg

        }
    }
}