package com.example.bmi;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button settingsButton;

    private static final String PREFS_NAME = "AppSettings";
    private static final String USER_NAME_KEY = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcomeText);
        settingsButton = findViewById(R.id.settingsButton);


        showWelcomeMessage();

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString(USER_NAME_KEY, "");

        if (!userName.isEmpty()) {
            welcomeText.setText("Witaj, " + userName + "!");
        } else {
            welcomeText.setText("Witaj, gościu!");
        }
    }
}