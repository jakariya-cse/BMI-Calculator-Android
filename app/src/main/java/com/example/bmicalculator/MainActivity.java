package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText weightInput;
    private EditText heightInput;
    private TextView resultText;
    private TextView categoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        resultText = findViewById(R.id.resultText);
        categoryText = findViewById(R.id.categoryText);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button clearButton = findViewById(R.id.clearButton);

        calculateButton.setOnClickListener(view -> calculateBmi());

        clearButton.setOnClickListener(view -> clearForm());
    }

    private void calculateBmi() {
        String weightValue = weightInput.getText().toString().trim();
        String heightValue = heightInput.getText().toString().trim();

        if (weightValue.isEmpty() || heightValue.isEmpty()) {
            showMessage("Please enter weight and height.", "");
            return;
        }

        double weight;
        double heightCm;

        try {
            weight = Double.parseDouble(weightValue);
            heightCm = Double.parseDouble(heightValue);
        } catch (NumberFormatException exception) {
            showMessage("Please enter valid numbers.", "");
            return;
        }

        if (weight <= 0 || heightCm <= 0) {
            showMessage("Values must be greater than zero.", "");
            return;
        }

        double heightMeter = heightCm / 100;
        double bmi = weight / (heightMeter * heightMeter);

        showMessage(
                String.format(Locale.US, "Your BMI: %.1f", bmi),
                getBmiCategory(bmi)
        );
    }

    private String getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "Category: Underweight";
        } else if (bmi < 25) {
            return "Category: Normal weight";
        } else if (bmi < 30) {
            return "Category: Overweight";
        }
        return "Category: Obese";
    }

    private void clearForm() {
        weightInput.setText("");
        heightInput.setText("");
        showMessage("Enter your details to calculate BMI.", "");
        weightInput.requestFocus();
    }

    private void showMessage(String result, String category) {
        resultText.setText(result);
        categoryText.setText(category);
    }
}
