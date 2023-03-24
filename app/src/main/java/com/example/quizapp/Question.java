package com.example.quizapp;

// This class is used to build out my questions and access the details of the question.
public class Question {
    private String questionTxt;
    private String[] answerArray;
    private String correctAnswer;

    public Question(String questionTxt, String[] answerArray, String correctAnswer){
        this.questionTxt = questionTxt;
        this.answerArray = answerArray;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion(){
        return questionTxt;
    }

    public String[] getAnswerArray(){
        return answerArray;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }
}
