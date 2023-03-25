package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onStart() {
        super.onStart();
        TextView name = findViewById(R.id.nameTxt);
        TextView score = findViewById(R.id.scoreTxt);
        name.setText("Congratulations " + userName +"!");
        score.setText(String.valueOf(userScore) + "/" + String.valueOf(maxScore));

    }

    public void quit(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        finish();
        finish();
    }

    public void newQuiz(View view){
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("name", userName);
        startActivity(intent);
    }

    public void mainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}