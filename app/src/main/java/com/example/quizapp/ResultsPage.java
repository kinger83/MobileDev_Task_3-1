package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ResultsPage extends AppCompatActivity {
    private int maxScore;
    private int userScore;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        Intent resIntent = getIntent();
        maxScore = resIntent.getIntExtra("questions", 0);
        userName = resIntent.getStringExtra("name");
        userScore = resIntent.getIntExtra("score", 0);

    }
}