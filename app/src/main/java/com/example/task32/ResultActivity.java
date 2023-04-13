package com.example.task32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView congratsText;
    TextView scoreView;
    Button takeNewQuizButton;
    Button finishButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        congratsText = findViewById(R.id.congratsText);
        scoreView= findViewById(R.id.score);
        takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        finishButton = findViewById(R.id.finishButton);

        sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        String name = sharedPreferences.getString("NAME",null);
        int score = sharedPreferences.getInt("SCORE",0);
        int questionAmont = sharedPreferences.getInt("QUESTIONAMONT",0);
        congratsText.setText("Congratulations,"+name+"!");
        scoreView.setText(String.valueOf(score)+"/"+String.valueOf(questionAmont));

        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //app close
                finishAffinity();
            }
        });

    }
}