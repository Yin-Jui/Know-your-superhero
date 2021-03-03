package com.example.knowyoursuperhero;

import java.util.ArrayList;
import java.util.List;

public class Contents {

    public List<Question> q_list;
    public int Q_number = 1;

    private Question q1 = new Question("What's your fav. food?",
            "pizza","ramen", "sushi","blood", Question.INTELLIGENCE);

    private Question q2 = new Question("Do you work out?",
            "Can't", "Never","Sometimes","Always",Question.STRENGTH);

    private Question q3 = new Question("How tall are you?",
            "Short","Average", "Tall","Goliath",Question.DURABILITY);

    private Question q4 = new Question("What animal would you describe yourself as?",
            "Starfish","Turtles", "Rabbits","Leopards",Question.SPEED);

    private Question q5 = new Question("What animal would you describe yourself as?",
            "Starfish","Turtles", "Rabbits","Leopards",Question.POWER);
    private Question q6 = new Question("What animal would you describe yourself as?",
            "Starfish","Turtles", "Rabbits","Leopards",Question.COMBAT);



    public Contents(){
        q_list = new ArrayList<>();
        q_list.add(q1);
        q_list.add(q2);
        q_list.add(q3);
        q_list.add(q4);
        Q_number = q_list.size();
    }

}
