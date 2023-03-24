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
    public Question[] questions = new Question[20];

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


    // private functions

    // Build an array of questions
    private void buildQuestionArray(){
        questions[0] = new Question("What is the capital of Australia?", new String[]{"Melbourne", "Sydney", "Canberra", "Hobart"}, 2);
        questions[1] = new Question("What is the capital of Victoria?", new String[]{"Melbourne", "Sydney", "Brisbane", "Hobart"}, 0);
        questions[2] = new Question("What is the capital of New South Wales?", new String[]{"Melbourne", "Sydney", "Perth", "Darwin"}, 1);
        questions[3] = new Question("What number equal 1 dozen?", new String[]{"8", "10", "12", "14"}, 2);
        questions[4] = new Question("How many days are in 1 year?", new String[]{"635", "365", "356", "536"}, 1);
        questions[5] = new Question("What is the 10th month of the year?", new String[]{"September", "August", "November", "October"}, 3);
        questions[6] = new Question("What is not a native Australian animal?", new String[]{"Koala", "Rabbit", "Kangaroo", "Platypus"}, 1);
        questions[7] = new Question("Which AFL club is the greatest (There is only one!)?", new String[]{"Demons", "Lions", "Tigers", "Cats"}, 3);
        questions[8] = new Question("Who won the 2022 AFL Grand Final?", new String[]{"Melbourne", "Sydney", "Geelong", "Adelaide"}, 2);
        questions[9] = new Question("How many balls are bowled in an over of cricket", new String[]{"4", "6", "8", "10"}, 1);
        questions[10] = new Question("What is the higher mountain in the world?", new String[]{"Matterhorn", "Fuji", "Kilimanjaro", "Everest"}, 3);
        questions[11] = new Question("What is the worlds largest Ocean?", new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"}, 0);
        questions[12] = new Question("What is not a primary colour?", new String[]{"Red", "Green", "Yellow", "Blue"}, 1);
        questions[13] = new Question("What is the worlds largest desert?", new String[]{"Sahara", "Antarctic", "Simpson", "Kalahari"}, 1);
        questions[14] = new Question("How many holes in a round of golf?", new String[]{"17", "18", "19", "20"}, 1);
        questions[15] = new Question("Which river runs through Melbourne?", new String[]{"Melbourne River", "The Nile", "Barwon River", "Yara"}, 2);
        questions[16] = new Question("What is the clowns name on 'The Simpson's'?", new String[]{"Kristy", "Krusty", "Kirsty", "Klusty"}, 1);
        questions[17] = new Question("What is the dogs name on 'Family Guy'?", new String[]{"Spot", "Jack", "Wolfy", "Brian"}, 3);
        questions[18] = new Question("What is the pigs name in story mode minecraft? ", new String[]{"Reuben", "Pepper", "Petra", "Lukas"}, 0);
        questions[19] = new Question("Who is not a character in the 'Call of Duty' franchise?", new String[]{"Captain 'Soap'", "Gaz", "Ghost", "Rambo"}, 3);

    }
}