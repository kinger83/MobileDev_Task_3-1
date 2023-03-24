package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // declare variables
    public String userName = null;
    public EditText nameTxt = null;
    public Button beginQuizBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // assign view elements
    @Override
    protected void onStart() {
        super.onStart();
        nameTxt = findViewById(R.id.editTextTextPersonName);
        beginQuizBtn = findViewById(R.id.beginBtn);
    }
    // function called when begin button clicked
    public void onBeginClick(View view){
        // check if name has been entered
        userName = nameTxt.getText().toString();
        if(userName.equals("")){
            Toast.makeText(this, "Please enter a name before starting quiz", Toast.LENGTH_SHORT).show();
            return;
        }

        // start intent and send name to quiz activity
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("name", userName);
        startActivity(intent);

    }
}













