package com.trim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupEmailScreen extends AppCompatActivity {
    Button continueButton;
    EditText emailInput;
    TextView errorEmailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_email_screen);

        continueButton = findViewById(R.id.button_continue);
        emailInput = findViewById(R.id.email_input);
        errorEmailText = findViewById(R.id.error_email_text);

        continueButton.setOnClickListener(view -> {
            if (!isValidEmail(String.valueOf(emailInput.getText()))) {
                emailInput.setBackground(getDrawable(R.drawable.input_error));
                errorEmailText.setVisibility(View.VISIBLE);
            } else {
                String emailValue = String.valueOf(emailInput.getText());
                Intent intent = new Intent(SignupEmailScreen.this, SignupInfoScreen.class);
                intent.putExtra("emailSignup", emailValue);
                startActivity(intent);
            }
        });
    }

    public boolean isValidEmail(String email_value) {
        if (email_value.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}