package com.example.knowyoursuperhero;

import java.util.ArrayList;
import java.util.List;

public class Contents {

    public List<Question> q_list;
    public int Q_number;

    private Question q1 = new Question("What's your fav. food?",
            "pizza","ramen", "sushi","blood", Question.INTELLIGENCE);

    private Question q2 = new Question("Do you work out?",
            "Can't", "Never","Sometimes","Always",Question.STRENGTH);

    private Question q3 = new Question("How tall are you?",
            "Short","Average", "Tall","Goliath",Question.DURABILITY);

    private Question q4 = new Question("What animal would you describe yourself as?",
            "Starfish","Turtles", "Rabbits","Leopards",Question.SPEED);

    private Question q5 = new Question("How long do you need to sleep?",
            "12 Hours","9 Hours", "4 Hours","Not at All",Question.POWER);
    private Question q6 = new Question("How much do you like to fight",
            "Hate it!","Avoid it if possible", "Good at fighting!","Love it!",Question.COMBAT);



    public Contents(){
        q_list = new ArrayList<>();
        q_list.add(q1);
        q_list.add(q2);
        q_list.add(q3);
        q_list.add(q4);
        q_list.add(q5);
        q_list.add(q6);
        Q_number = q_list.size();
    }

}
