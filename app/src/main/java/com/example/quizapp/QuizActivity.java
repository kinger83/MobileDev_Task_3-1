package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
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
    int btnColor;
    Boolean answerSubmitted = false;
    Question thisQuestion;
    Question[] quizQuestions = new Question[maxquestions];

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
        btnColor = answerBtn1.getSolidColor();

        random = new Random();

        // setup onClickListeners
        View.OnClickListener answerListener = v -> {
            clickedBtn = (Button) v;
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
                processUserInput(thisQuestion);



            }
        };

        // Wait for a onclick message from a button
        answerBtn1.setOnClickListener(answerListener);
        answerBtn2.setOnClickListener(answerListener);
        answerBtn3.setOnClickListener(answerListener);
        answerBtn4.setOnClickListener(answerListener);
        subNextBtn.setOnClickListener(submitListener);

        playQuiz();
    }

    public void playQuiz(){
        // initialize new quiz
        curQuestion = 1;
        correctAnswers = 0;
        // Build array of questions for current quiz, checking question does not already exist
        int[] assignedIndexes = new int[]{-1, -1, -1, -1, -1};
        int potentialNewQuestion = -1;

        // loop for adding (maxQuestions) questions to quizQuestions array
        for (int i = 0; i < quizQuestions.length ; i++) {
            Boolean inUse = true;
            // loop to see if question is already in use
            while (inUse == true){
                potentialNewQuestion = random.nextInt(questions.length);
                inUse = false;
                for (int j = 0; j < assignedIndexes.length; j++) {
                    if(assignedIndexes[j] == potentialNewQuestion){
                        inUse = true;
                    }
                }
            } // end while loop

            // out of loop means new index to add is unique in th quizQuestions array and int to used indexes
            assignedIndexes[i] = potentialNewQuestion;
            quizQuestions[i] = questions[potentialNewQuestion];
        } // end adding questions loop

        displayNextQuestion(quizQuestions);

    } // end playQuiz

    // private functions

    private void displayNextQuestion(Question[] quizQuestions){
        // Reset all buttons to original state
        answerBtn1.setBackgroundColor(btnColor);
        answerBtn2.setBackgroundColor(btnColor);
        answerBtn3.setBackgroundColor(btnColor);
        answerBtn4.setBackgroundColor(btnColor);
        userCurrentAnswer = "";
        currAnswerTxt.setText("");

        // set thisQuestion
        thisQuestion = quizQuestions[curQuestion -1];
        // Display question
        questionNumTxt.setText((String.valueOf(curQuestion) + "/5"));
        progressBar.setMax(5);
        progressBar.setProgress(curQuestion);
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

    }

    private void processUserInput(Question thisQuestion){
        // check if user has correct answer
        userHasCorrectAnswer = checkAnswer(thisQuestion);
        // iff correct, add 1 to user score
        if(userHasCorrectAnswer) {
            correctAnswers++;
        }
        // set color of button displaying results
        clickedBtn.setBackgroundColor(Color.RED);
        if(answerBtn1.getText().toString().equals(thisQuestion.getCorrectAnswer())){
            answerBtn1.setBackgroundColor(Color.GREEN);
        }
        if(answerBtn2.getText().toString().equals(thisQuestion.getCorrectAnswer())){
            answerBtn2.setBackgroundColor(Color.GREEN);
        }
        if(answerBtn3.getText().toString().equals(thisQuestion.getCorrectAnswer())){
            answerBtn3.setBackgroundColor(Color.GREEN);
        }
        if(answerBtn4.getText().toString().equals(thisQuestion.getCorrectAnswer())){
            answerBtn4.setBackgroundColor(Color.GREEN);
        }

        // start short timer for display to stay active then refresh with next question

        CountDownTimer timer = new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                // update current question
                curQuestion++;

                //if current question is now greater than 5, open end quiz activity
                if (curQuestion > maxquestions){
                    // end quiz code here....
                    loadResultsPage();
                }
                else{
                    displayNextQuestion(quizQuestions);
                }



            }
        }.start();

    }


    // reset buttons after a timer, set userAnswered to true..all in finish
    private void resetConditions(){
        userHasAnswered = true;
    }
    // Process result and display colored buttons, add 1 if needed to correct int


    // check if Answer is  correct
    private Boolean checkAnswer(Question thisQ){
        String correctAnswer = thisQ.getCorrectAnswer();

        return clickedBtn.getText().toString().equals(correctAnswer);
    }// end checkAnswer

    // Build an array of questions
    private void buildQuestionArray(){
        questions[0] = new Question("What is the capital of Australia?", new String[]{"Melbourne", "Sydney", "Canberra", "Hobart"}, "Canberra");
        questions[1] = new Question("What is the capital of Victoria?", new String[]{"Melbourne", "Sydney", "Brisbane", "Hobart"}, "Melbourne");
        questions[2] = new Question("What is the capital of New South Wales?", new String[]{"Melbourne", "Sydney", "Perth", "Darwin"}, "Sydney");
        questions[3] = new Question("What number is equal to 1 dozen?", new String[]{"8", "10", "12", "14"}, "12");
        questions[4] = new Question("How many days are in 1 year?", new String[]{"635", "365", "356", "536"}, "365");
        questions[5] = new Question("What is the 10th month of the year?", new String[]{"September", "August", "November", "October"}, "October");
        questions[6] = new Question("What is not a native Australian animal?", new String[]{"Koala", "Rabbit", "Kangaroo", "Platypus"}, "Rabbit");
        questions[7] = new Question("Which AFL club is the greatest (There is only one!)?", new String[]{"Demons", "Lions", "Tigers", "Cats"}, "Cats");
        questions[8] = new Question("Who won the 2022 AFL Grand Final?", new String[]{"Melbourne", "Sydney", "Geelong", "Adelaide"}, "Geelong");
        questions[9] = new Question("How many balls are bowled in an over of cricket", new String[]{"4", "6", "8", "10"}, "6");
        questions[10] = new Question("What is the highest mountain in the world?", new String[]{"Matterhorn", "Fuji", "Kilimanjaro", "Everest"}, "Everest");
        questions[11] = new Question("What is the worlds largest Ocean?", new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"}, "Pacific Ocean");
        questions[12] = new Question("What is not a primary colour?", new String[]{"Red", "Green", "Yellow", "Blue"}, "Green");
        questions[13] = new Question("What is the worlds largest desert?", new String[]{"Sahara", "Antarctic", "Simpson", "Kalahari"}, "Antarctic");
        questions[14] = new Question("How many holes in a round of golf?", new String[]{"17", "18", "19", "20"}, "18");
        questions[15] = new Question("Which river runs through Melbourne?", new String[]{"Melbourne River", "The Nile", "Barwon River", "Yara"}, "Yara");
        questions[16] = new Question("What is the clowns name on 'The Simpson's'?", new String[]{"Kristy", "Krusty", "Kirsty", "Klusty"}, "Krusty");
        questions[17] = new Question("What is the dogs name on 'Family Guy'?", new String[]{"Spot", "Jack", "Wolfy", "Brian"}, "Brian");
        questions[18] = new Question("What is the pigs name in story mode minecraft? ", new String[]{"Reuben", "Pepper", "Petra", "Lukas"}, "Reuben");
        questions[19] = new Question("Who is not a character in the 'Call of Duty' franchise?", new String[]{"Captain 'Soap'", "Gaz", "Ghost", "Rambo"}, "Rambo");

    } //end BuildQuestionArray

    // load results page
    private void loadResultsPage(){
        Intent resIntent = new Intent(this, ResultsPage.class);
        resIntent.putExtra("score", correctAnswers);
        resIntent.putExtra("questions", maxquestions);
        resIntent.putExtra("name", userName);
        startActivity(resIntent);
    }

} // end class