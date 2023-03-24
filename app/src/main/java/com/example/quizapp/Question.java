package com.example.quizapp;

// This class is used to build out my questions and access the details of the question.
public class Question {
    private String questionTxt;
    private String[] answerArray;
    private int correctAnswerIndex;

    public Question(String questionTxt, String[] answerArray, int correctAnswerIndex){
        this.questionTxt = questionTxt;
        this.answerArray = answerArray;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion(){
        return questionTxt;
    }

    public String[] getAnswerArray(){
        return answerArray;
    }

    public int getCorrectAnswrIndex(){
        return correctAnswerIndex;
    }
}
