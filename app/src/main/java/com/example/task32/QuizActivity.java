package com.example.task32;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class QuizActivity extends AppCompatActivity {

    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    TextView welmcomeText;
    TextView questionView;
    ArrayList<QuestionModel> quizArrayList;
    RadioGroup optionGroup;
    Button submitButton;
    Button nextButton;
    TextView progressText;


    int currentQuestionIndex=0;
    int progress = 1;
    int correctColor;
    int incorrectColor;

    int whiteColor;

    int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        progressBar = findViewById(R.id.progressBar);
        welmcomeText = findViewById(R.id.welcomText);
        //recyclerView = findViewById(R.id.recycleView);
        questionView = findViewById(R.id.question_text);
        nextButton = findViewById(R.id.next_button);
        submitButton = findViewById(R.id.submit_button);
        optionGroup = findViewById(R.id.optionsGroup);
        progressText = findViewById(R.id.progressText);
        correctColor = getResources().getColor(R.color.correctColor);
        incorrectColor = getResources().getColor(R.color.incorrectColor);
        whiteColor = getResources().getColor(R.color.white);
        Score=0;

        quizArrayList = new ArrayList<>();



        quizArrayList.add(new QuestionModel("What color is the sky on a clear day?", new String[]{"Red","Blue", "Green"}, 1));
        quizArrayList.add(new QuestionModel("What is the name of the largest planet in our solar system", new String[]{"Saturn","Jupiter", "Neptune"}, 1));
        quizArrayList.add(new QuestionModel("Which animal is known for its long neck", new String[]{"Giraffe","Hippopotamus", "Lion"}, 0));
        quizArrayList.add(new QuestionModel("What is the capital city of the United States of America", new String[]{"New York City","Washington, D.C.", "Los Angeles"}, 1));
        quizArrayList.add(new QuestionModel("What is the name of the largest continent on Earth", new String[]{"Europe","Asia", "South America"}, 1));
        sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        String name = sharedPreferences.getString("NAME",null);
        welmcomeText.setText("Welcome,"+name);
        progressBar.setMax(quizArrayList.size());
        progressBar.setProgress(progress);
        progressText.setText(progress+"/"+quizArrayList.size());
        setQuizContent(quizArrayList.get(currentQuestionIndex));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("QUESTIONAMONT", quizArrayList.size());
        editor.apply();



            //click submit button
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get selected radioButton
                    int selectedRadioButtonId = optionGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    Log.v("selectedRadioButton",String.valueOf(selectedRadioButtonId));
                    if(selectedRadioButton != null){
                        setOptionColor(selectedRadioButton,currentQuestionIndex);
                    }else{
                        //user didn't select an answer
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            //click next button
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //update progress, question and options
                    if(currentQuestionIndex<quizArrayList.size()-1){
                        progress++;
                        progressBar.setProgress(progress);
                        progressText.setText(progress+"/"+quizArrayList.size());
                        currentQuestionIndex+=1;
                        setQuizContent(quizArrayList.get(currentQuestionIndex));
                        refreshOptions();


                    }else{
                        editor.putInt("SCORE",Score);
                        editor.apply();
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        startActivity(intent);
                    }
                }
            });
    }

    private void refreshOptions() {
        nextButton.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        int newDrawable= R.drawable.my_rounded_rectangle;
        for(int i=0;i<optionGroup.getChildCount();i++){
            RadioButton radioButton = (RadioButton) optionGroup.getChildAt(i);
            radioButton.setBackgroundResource(R.drawable.my_rounded_rectangle);
        }
    }

    private void setOptionColor(RadioButton selectedRadioButton, int i) {
        int selectedAnswerIndex = optionGroup.indexOfChild(selectedRadioButton);
        if (selectedAnswerIndex == quizArrayList.get(i).getCorrectIndex()) {
            // The selected option is correct
            Score++;
            selectedRadioButton.setBackgroundResource(R.drawable.option_correct);
        } else {
            // The selected option is incorrect

            selectedRadioButton.setBackgroundResource(R.drawable.option_correct);
            int corectIndex = quizArrayList.get(currentQuestionIndex).getCorrectIndex();
            RadioButton correctButton= (RadioButton) optionGroup.getChildAt(corectIndex);

            correctButton.setBackgroundResource(R.drawable.option_incorrect);
        }
        submitButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.VISIBLE);
    }

    private void setQuizContent(QuestionModel questionModel) {
        questionView.setText(questionModel.getQuestionTxt());
        for(int i=0;i<optionGroup.getChildCount();i++){
            RadioButton radioButton = (RadioButton) optionGroup.getChildAt(i);
            radioButton.setText(questionModel.getOpstions()[i]);
        }

    }
}