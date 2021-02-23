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
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private int currentQuestion = 1;
    private List<Question> q_list;
    private ProgressBar pb ;
    private TextView tv_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        pb = findViewById(R.id.progress_bar);
        pb.setMax(10);
        tv_progress = findViewById(R.id.tv_progress);
        tv_progress.setText(currentQuestion+"/10");
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

        final View btnNext = findViewById(R.id.button_next);
        btnNext.setOnClickListener( (v) -> {

            setQuestion();

//                    if (mSelectedOptionPosition == 0) {
//
//                        mCurrentPosition++
//
//                        when {
//
//                            mCurrentPosition <= mQuestionsList!!.size -> {
//
//                                setQuestion()
//                            }
//                        else -> {
//
//                                // TODO (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
//                                // START
//                                val intent =
//                                        Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
//                                intent.putExtra(Constants.USER_NAME, mUserName)
//                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
//                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
//                                startActivity(intent)
//                                finish()
//                                // END
//                            }
//                        }
//                    } else {
//                        val question = mQuestionsList?.get(mCurrentPosition - 1)
//
//                        // This is to check if the answer is wrong
//                        if (question!!.correctAnswer != mSelectedOptionPosition) {
//                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
//                        }
//                    else {
//                            mCorrectAnswers++
//                        }
//
//                        // This is for correct answer
//                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
//
//                        if (mCurrentPosition == mQuestionsList!!.size) {
//                            btn_submit.text = "FINISH"
//                        } else {
//                            btn_submit.text = "GO TO NEXT QUESTION"
//                        }
//
//                        mSelectedOptionPosition = 0
//                    }
                }
                );








//        tv_option1.setOnClickListener(this)
//        tv_option_two.setOnClickListener(this)
//        tv_option_three.setOnClickListener(this)
//        tv_option_four.setOnClickListener(this)
//        btn_submit.setOnClickListener(this)
    }
    private void setQuestion() {

//        Question  currQ =  q_list.get(currentQuestion);// Getting the question from the list with the help of current position.
        currentQuestion++;
        defaultOptionsView();

//        if (mCurrentPosition == mQuestionsList!!.size) {
//            btn_submit.text = "FINISH"
//        } else {
//            btn_submit.text = "SUBMIT"
//        }

        pb.setProgress(currentQuestion);
        tv_progress.setText(currentQuestion+"/10");

//        progressBar.progress = mCurrentPosition
//        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()
//
//        tv_question.text = question.question
//        iv_image.setImageResource(question.image)
//        tv_option_one.text = question.optionOne
//        tv_option_two.text = question.optionTwo
//        tv_option_three.text = question.optionThree
//        tv_option_four.text = question.optionFour
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