package com.example.credittask2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView congratsText = findViewById(R.id.textView6);
        TextView scoreText = findViewById(R.id.textView8);
        Button restartBtn = findViewById(R.id.button);
        Button finishBtn = findViewById(R.id.button2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        int score = ScoreManager.getScore();

        congratsText.setText("Congratulations " + name + "!");
        scoreText.setText(score + "/5");

        restartBtn.setOnClickListener(v -> {
            ScoreManager.reset();
            Intent restartIntent = new Intent(ResultActivity.this, FirstActivity.class);
            restartIntent.putExtra("username", name);
            startActivity(restartIntent);
            finish();
        });

        finishBtn.setOnClickListener(v -> {
            Intent finishIntent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(finishIntent);
            finish();
        });
    }
}
