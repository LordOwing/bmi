package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private EditText userNameInput;
    private Switch notificationsSwitch;
    private Button saveButton;

    private static final String PREFS_NAME = "AppSettings";
    private static final String USER_NAME_KEY = "user_name";
    private static final String NOTIFICATIONS_KEY = "notifications_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userNameInput = findViewById(R.id.userNameInput);
        notificationsSwitch = findViewById(R.id.notificationsSwitch);
        saveButton = findViewById(R.id.saveSettingsButton);


        loadSettings();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString(USER_NAME_KEY, "");
        boolean notificationsEnabled = prefs.getBoolean(NOTIFICATIONS_KEY, true);

        userNameInput.setText(userName);
        notificationsSwitch.setChecked(notificationsEnabled);
    }

    private void saveSettings() {
        String userName = userNameInput.getText().toString();
        boolean notificationsEnabled = notificationsSwitch.isChecked();

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(USER_NAME_KEY, userName);
        editor.putBoolean(NOTIFICATIONS_KEY, notificationsEnabled);
        editor.apply();

        Toast.makeText(this, "Ustawienia zapisane!", Toast.LENGTH_SHORT).show();
        finish();
    }
}