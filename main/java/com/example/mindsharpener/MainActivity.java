package com.example.mindsharpener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar = findViewById(R.id.toolbar);
    void setSupportActionBar(toolbar);

    // Set app title (you can also set it programmatically if needed)
    getSupportActionBar().setTitle("Mathematics Game");
    private TextView textView4, textView6, textView8;
    private TextView pointTextView;
    private RadioButton radioButton, radioButton3, radioButton4;
    private Button checkButton;

    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView8);
        radioButton = findViewById(R.id.radioButton);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        checkButton = findViewById(R.id.button);
        pointTextView = findViewById(R.id.textView8); // Assuming this is where you want to display points

        // Set Click Listener for Check Button
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate numbers and operator
                String selectedLevel = getSelectedLevel();
                generateQuestion(selectedLevel);

                // Check the answer
                checkAnswer();
            }
        });
    }

    private String getSelectedLevel() {
        if (radioButton.isChecked()) {
            return "i3";
        } else if (radioButton3.isChecked()) {
            return "i5";
        } else if (radioButton4.isChecked()) {
            return "i7";
        }
        return "";
    }

    private void generateQuestion(String level) {
        Random random = new Random();
        int num1, num2, operator;

        switch (level) {
            case "i3":
                num1 = random.nextInt(10); // 0-9
                num2 = random.nextInt(10); // 0-9
                operator = random.nextInt(4); // 0-3
                displayQuestion(num1, num2, operator);
                break;
            case "i5":
                num1 = random.nextInt(100); // 0-99
                num2 = random.nextInt(100); // 0-99
                operator = random.nextInt(4); // 0-3
                displayQuestion(num1, num2, operator);
                break;
            case "i7":
                num1 = random.nextInt(1000); // 0-999
                num2 = random.nextInt(1000); // 0-999
                operator = random.nextInt(4); // 0-3
                displayQuestion(num1, num2, operator);
                break;
        }
    }

    private void displayQuestion(int num1, int num2, int operator) {
        textView4.setText(String.valueOf(num1));
        textView8.setText(String.valueOf(num2));

        switch (operator) {
            case 0:
                textView6.setText("+");
                break;
            case 1:
                textView6.setText("-");
                break;
            case 2:
                textView6.setText("*");
                break;
            case 3:
                textView6.setText("/");
                break;
        }
    }

    @SuppressLint("RestrictedApi")
    private void checkAnswer() {
        int userAnswer;
        WindowDecorActionBar.TabImpl editTextText = null;
        userAnswer = Integer.parseInt(editTextText.getText().toString());
        int num1 = Integer.parseInt(textView4.getText().toString());
        int num2 = Integer.parseInt(textView8.getText().toString());
        int operator = getOperatorValue(textView6.getText().toString());

        int correctAnswer = calculateAnswer(num1, num2, operator);

        if (userAnswer == correctAnswer) {
            points++;
            Toast.makeText(this, "Correct! Points: " + points, Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(this, "Incorrect! Points: " + points, Toast.LENGTH_SHORT).show();
        }

        // Display another question
        String selectedLevel = getSelectedLevel();
        generateQuestion(selectedLevel);
    }

    private int getOperatorValue(String operator) {
        switch (operator) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 3;
            default:
                return 0;
        }
    }

    private int calculateAnswer(int num1, int num2, int operator) {
        switch (operator) {
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            case 3:
                return num1 / num2; // Assuming no division by zero
            default:
                return 0;
        }
    }


}
