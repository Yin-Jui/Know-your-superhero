package com.example.knowyoursuperhero;

public class Question {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public Question(String question,
            String option1,
            String option2,
            String option3,
           String option4){

        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;

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
}
