package com.example.knowyoursuperhero;

public class Question {

    public final static int INTELLIGENCE = 0;
    public final static int STRENGTH = 1;
    public final static int SPEED = 2;
    public final static int DURABILITY = 3;
    public final static int POWER = 4;
    public final static int COMBAT = 5;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int QUESTION_TYPE;

    public Question(String question,
            String option1,
            String option2,
            String option3,
           String option4, int question_type){

        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        QUESTION_TYPE = question_type;

    }

    public String getOpt1(){
        return option1;
    }

    public String getOpt2(){
        return option2;
    }

    public String getOpt3(){
        return option3;
    }

    public String getOpt4(){
        return option4;
    }

    public String getQ(){
        return question;
    }

    public int getType() {return QUESTION_TYPE;}
}
