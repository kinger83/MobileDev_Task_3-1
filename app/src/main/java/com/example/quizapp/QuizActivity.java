package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    // Declare variables
    public String userName = null;
    public TextView welcomeNameMessageTxt = null;
    public Question[] questions = new Question[20];
    public TextView questionNumTxt;
    public ProgressBar progressBar;
    public TextView questionNumTitleTxt;
    public TextView questionTxt;
    public Button answerBtn1;
    public Button answerBtn2;
    public Button answerBtn3;
    public Button answerBtn4;
    public TextView currAnswerTxt;
    public Button subNextBtn;
    public int maxquestions = 5;
    public int curQuestion = -1;
    public int correctAnswers = 0;
    Boolean userHasAnswered;
    public String userCurrentAnswer = "";
    public Random random;
    public Button clickedBtn;
    public Boolean userHasCorrectAnswer;

    // receive intent data, build array of questions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        buildQuestionArray();
    }

    // set view elements
    @Override
    protected void onStart() {
        super.onStart();
        // Set name and welcome message
        welcomeNameMessageTxt = findViewById(R.id.welcomeNameTxt);
        welcomeNameMessageTxt.setText("Welcome to Kinger's Quiz " + userName + "!");
        questionNumTxt = findViewById(R.id.questionNumberTxt);
        progressBar = findViewById(R.id.progressBar);
        questionNumTitleTxt = findViewById(R.id.questionTitleandNumberTxt);
        questionTxt = findViewById(R.id.questionTxt);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        subNextBtn = findViewById(R.id.subNextBtn);
        currAnswerTxt = findViewById(R.id.currAnswerTxt);

        playQuiz();
    }

    public void playQuiz(){
        // initialize for question 1
        curQuestion = 1;
        random = new Random();
        correctAnswers = 0;
        Boolean hasSelectedCorrectAnswer;


        // loop from question 1 to 5
        for (int i = 1; i < 6 ; i++) {
            // get a question from question array
            int qIndex = random.nextInt(questions.length);
            Question thisQuestion = questions[qIndex];
            userHasAnswered = false;

            // display all info into view
            questionNumTxt.setText((String.valueOf(curQuestion) + "/5"));
            progressBar.setMax(5);
            progressBar.setProgress(1);
            subNextBtn.setText("SUBMIT");
            questionNumTitleTxt.setText("Question " + String.valueOf(curQuestion));
            questionTxt.setText(thisQuestion.getQuestion());

            // shuffle answer order.
            String[] answerDisplayOrder = thisQuestion.getAnswerArray();
            Collections.shuffle(Arrays.asList(answerDisplayOrder));

            // Display answer options
            answerBtn1.setText(answerDisplayOrder[0]);
            answerBtn2.setText(answerDisplayOrder[1]);
            answerBtn3.setText(answerDisplayOrder[2]);
            answerBtn4.setText(answerDisplayOrder[3]);

            // set up answer button onClick listener
            View.OnClickListener answerListener = v -> {
                clickedBtn = (Button) v;
                Log.v("Button pressed", clickedBtn.getText().toString());
                Log.v("button is:", String.valueOf(clickedBtn.getId()));
                userCurrentAnswer = clickedBtn.getText().toString();
                currAnswerTxt.setText("You have currently selected:\n" + userCurrentAnswer);
            };

            // setup submit onclick listener
            View.OnClickListener submitListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("Button pressed", "Sub button pressed");
                    if(userCurrentAnswer.equals("")){
                        Toast.makeText(QuizActivity.this, "Please select an answer before submitting.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    userHasCorrectAnswer = checkAnswer(thisQuestion);
                    if(userHasCorrectAnswer) Log.v("user:", "Correct answer");
                    else Log.v("user:", "WRONG answer");
                    processQuestionResults(clickedBtn, thisQuestion);
                }
            };

                // Wait for a onclick message from a button
                answerBtn1.setOnClickListener(answerListener);
                answerBtn2.setOnClickListener(answerListener);
                answerBtn3.setOnClickListener(answerListener);
                answerBtn4.setOnClickListener(answerListener);
                subNextBtn.setOnClickListener(submitListener);
            }
    } // end playQuiz

    // private functions

    private void processQuestionResults(Button clickedBtn, Question thisQuestion) {
            if (userHasCorrectAnswer == false) {
                clickedBtn.setBackgroundColor(Color.RED);
                // TODO make correct answer green.
                return;
            } else {
                correctAnswers++;
                clickedBtn.setBackgroundColor(Color.GREEN);
            }
    }

    // check if Answer is  correct
    private Boolean checkAnswer(Question thisQ){
        String[] answers = thisQ.getAnswerArray();
        int correctIndex = thisQ.getCorrectAnswrIndex();
        String correctAnswer = answers[correctIndex];

        return clickedBtn.getText().toString().equals(correctAnswer);
    }// end checkAnswer

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

    } //end BuildQuestionArray


} // end class