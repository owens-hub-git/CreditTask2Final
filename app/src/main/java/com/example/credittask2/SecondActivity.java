package com.example.credittask2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MaterialButton choice1 = findViewById(R.id.choice1);
        MaterialButton choice2 = findViewById(R.id.choice2);
        MaterialButton choice3 = findViewById(R.id.choice3);
        MaterialButton submitBtn = findViewById(R.id.submitButton);

        TextView welcomeText = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        welcomeText.setText("Welcome " + name + "!");

        int correctIndex = 1;
        final int[] selectedIndex = {-1};
        final boolean[] answered = {false};

        MaterialButton[] choices = {choice1, choice2, choice3};

        for (int i = 0; i < choices.length; i++) {
            final int index = i;
            choices[i].setOnClickListener(v -> {
                if (!answered[0]) {
                    for (MaterialButton btn : choices) {
                        btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                    }
                    selectedIndex[0] = index;
                    choices[index].setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                }
            });
        }

        submitBtn.setOnClickListener(v -> {
            if (!answered[0] && selectedIndex[0] != -1) {
                answered[0] = true;
                if (selectedIndex[0] == correctIndex) {
                    choices[selectedIndex[0]].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    ScoreManager.increment();
                } else {
                    choices[selectedIndex[0]].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    choices[correctIndex].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }
                submitBtn.setText("Next");
            } else if (answered[0]) {
                Intent nextIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                nextIntent.putExtra("username", name);
                startActivity(nextIntent);
                finish();
            }
        });
    }
}
