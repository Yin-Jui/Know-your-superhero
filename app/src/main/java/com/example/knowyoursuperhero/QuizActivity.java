package com.example.knowyoursuperhero;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private int Q_num;
    private int currentQuestion = 1;
    private List<Question> q_list;
    private ProgressBar pb ;
    private TextView tv_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Contents ctn = new Contents();
        Q_num = ctn.Q_number;
        q_list = ctn.q_list;

        pb = findViewById(R.id.progress_bar);
        pb.setMax(Q_num);
        tv_progress = findViewById(R.id.tv_progress);
        tv_progress.setText(currentQuestion+"/"+Q_num);
        defaultOptionsView();

        ((TextView)findViewById(R.id.tv_question)).setText(q_list.get(currentQuestion-1).getQ());
        ((TextView)findViewById(R.id.tv_option1)).setText(q_list.get(currentQuestion-1).getOpt1());
        ((TextView)findViewById(R.id.tv_option2)).setText(q_list.get(currentQuestion-1).getOpt2());
        ((TextView)findViewById(R.id.tv_option3)).setText(q_list.get(currentQuestion-1).getOpt3());
        ((TextView)findViewById(R.id.tv_option4)).setText(q_list.get(currentQuestion-1).getOpt4());

        final View btnOption1 = findViewById(R.id.tv_option1);
        btnOption1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

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
                defaultOptionsView();

                selectedOptionView((TextView) findViewById(R.id.tv_option3),1);

            }
        });

        final View btnOption4 = findViewById(R.id.tv_option4);
        btnOption4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                defaultOptionsView();

                selectedOptionView((TextView) findViewById(R.id.tv_option4),1);

            }
        });

        final View btnNext = findViewById(R.id.button_next);
        btnNext.setOnClickListener( (v) -> {

            if(currentQuestion < Q_num)
                setQuestion();
            else{
                Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                startActivity(intent);
            }

        });

//        setQuestion();








    }
    private void setQuestion() {

//        Question  currQ =  q_list.get(currentQuestion);// Getting the question from the list with the help of current position.

        defaultOptionsView();
        if(currentQuestion < Q_num-1){
            ((TextView)findViewById(R.id.button_next)).setText("Next");
        }else{
            ((TextView)findViewById(R.id.button_next)).setText("Summit");
        }


        pb.setProgress(currentQuestion);
        tv_progress.setText(currentQuestion+"/"+Q_num);
        ((TextView)findViewById(R.id.tv_question)).setText(q_list.get(currentQuestion-1).getQ());
        ((TextView)findViewById(R.id.tv_option1)).setText(q_list.get(currentQuestion-1).getOpt1());
        ((TextView)findViewById(R.id.tv_option2)).setText(q_list.get(currentQuestion-1).getOpt2());
        ((TextView)findViewById(R.id.tv_option3)).setText(q_list.get(currentQuestion-1).getOpt3());
        ((TextView)findViewById(R.id.tv_option4)).setText(q_list.get(currentQuestion-1).getOpt4());

        currentQuestion++;
    }


    private void selectedOptionView(TextView tv, int selectedNum) {



        tv.setTextColor(
                Color.parseColor("#FFFFFF")
        );
    }

    private void defaultOptionsView() {

        List<TextView> options = new ArrayList<TextView>();
        options.add((TextView) findViewById(R.id.tv_option1));
        options.add((TextView) findViewById(R.id.tv_option2));
        options.add((TextView) findViewById(R.id.tv_option3));
        options.add((TextView) findViewById(R.id.tv_option4));

        for (TextView option : options) {
            option.setTextColor(Color.parseColor("#7A8089"));


        }
    }
}