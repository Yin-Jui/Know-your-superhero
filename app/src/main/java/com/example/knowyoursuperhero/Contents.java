package com.example.knowyoursuperhero;

import java.util.ArrayList;
import java.util.List;

public class Contents {

    public List<Question> q_list;
    public int Q_number = 1;

    private Question q1 = new Question("what's your fav. food?",
            "pizza","ramen", "sushi","blood");

    private Question q2 = new Question("what's your fav. food?",
            "pizza","ramen", "sushi","blood");

    private Question q3 = new Question("what's your fav. food?",
            "pizza","ramen", "sushi","blood");

    public Contents(){
        q_list = new ArrayList<>();
        q_list.add(q1);
        q_list.add(q2);
        q_list.add(q3);
        Q_number = q_list.size();
    }

}