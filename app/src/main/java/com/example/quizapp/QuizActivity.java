package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    // Declare variables
    public String userName = null;
    public TextView welcomeNameMessageTxt = null;

    // receive intent data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
    }

    // set view elements
    @Override
    protected void onStart() {
        super.onStart();
        // Set name and welcome message
        welcomeNameMessageTxt = findViewById(R.id.welcomeNameTxt);
        welcomeNameMessageTxt.setText("Welcome to Kinger's Quiz " + userName + "!");
    }
}