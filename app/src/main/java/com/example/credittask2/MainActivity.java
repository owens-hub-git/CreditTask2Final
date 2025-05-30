package com.example.credittask2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    Button jumpClick;
    CheckBox loginCheckBox;
    SharedPreferences sharedPreferences;
    String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameEditText = findViewById(R.id.usernameEditText);
        loginCheckBox = findViewById(R.id.loginCheckBox);

        sharedPreferences = getSharedPreferences("com.example.credittask2", MODE_PRIVATE);
        checkSharedPreferences();
    }
    public void jumpClick(View view)
    {
        loginClick(view);

        Intent intent = new Intent(this, FirstActivity.class);
        intent.putExtra("username", usernameEditText.getText().toString());
        startActivity(intent);
    }

    public void loginClick(View view)
    {
        if(loginCheckBox.isChecked())
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USER_NAME, usernameEditText.getText().toString());
            editor.apply();
        }
        else
        {
            sharedPreferences.edit().putString(USER_NAME, "").apply();
        }
    }

    public void checkSharedPreferences()
    {
        String username = sharedPreferences.getString(USER_NAME, "");
        usernameEditText.setText(username);
    }

}