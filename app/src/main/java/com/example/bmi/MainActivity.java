package com.example.bmi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput;
    private EditText heightInput;
    private Button calculateButton;
    private TextView resultText;
    private TextView interpretation;
    private Button clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);
        interpretation = findViewById(R.id.interpretation);
        clear = findViewById(R.id.clearButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightInput.setText("");
                heightInput.setText("");
                resultText.setText("Twoje BMI:");
                interpretation.setText("Interpretacja:");
            }
        });
    }

    private void calculateBmi() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {

            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
            builder.setTitle("Błąd walidacji")
                    .setMessage(R.string.error_empty_fields)
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        float weight = Float.parseFloat(weightStr);
        float heightCm = Float.parseFloat(heightStr);

        if (weight <= 0 || heightCm <= 0) {

            Toast.makeText(this, R.string.error_zero_values, Toast.LENGTH_SHORT).show();
            return;
        }

        float heightM = heightCm / 100.0f;
        float bmi = weight / (heightM * heightM);

        String bmiResultString = String.format("%.2f", bmi);
        String resultPrefix = getResources().getString(R.string.bmi_result_prefix);
        resultText.setText(resultPrefix + " " + bmiResultString);


        if(bmi < 18.5){
            interpretation.setText(getResources().getString(R.string.bmi_under));
            interpretation.setTextColor(getResources().getColor(R.color.status_warning, null));
        } else if(bmi <= 24.9){
            interpretation.setText(getResources().getString(R.string.bmi_normal));
            interpretation.setTextColor(getResources().getColor(R.color.status_good, null));
        } else if(bmi <= 29.9){
            interpretation.setText(getResources().getString(R.string.bmi_over));
            interpretation.setTextColor(getResources().getColor(R.color.status_warning, null));
        } else {
            interpretation.setText(getResources().getString(R.string.bmi_obesity));
            interpretation.setTextColor(getResources().getColor(R.color.status_bad, null));
        }
    }
}