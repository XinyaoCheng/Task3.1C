package com.example.task32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.task32.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    TextView textPersonName;
    Button startButton;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textPersonName = findViewById(R.id.editTextTextPersonName);
        startButton = findViewById(R.id.StartButton);



        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        Log.d("TAG", "SDK version: " + sdkVersion);

        sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        String name = sharedPreferences.getString("NAME", null);
        //Log.v("inputed name",name);
        textPersonName.setText(name);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textPersonName.getText().equals(null)){
                    Log.v("name","is not null");
                    //save user' name
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("NAME",textPersonName.getText().toString());
                    Log.v("name",textPersonName.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}