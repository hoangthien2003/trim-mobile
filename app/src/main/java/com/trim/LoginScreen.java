package com.trim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {
    EditText passwordInput, emailInput;
    ImageButton togglePwButton;
    Button loginButton;
    RelativeLayout passwordLayout;
    TextView errorEmailText, errorPasswordText, signupText;

    private boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        passwordInput = findViewById(R.id.password_edit_text);
        togglePwButton = findViewById(R.id.toggle_pw_button);
        loginButton = findViewById(R.id.button_continue);
        passwordLayout = findViewById(R.id.password_input);
        emailInput = findViewById(R.id.email_input);
        errorEmailText = findViewById(R.id.error_email_text);
        errorPasswordText = findViewById(R.id.error_password_text);
        signupText = findViewById(R.id.sign_up_text);

        passwordInput.setTypeface(Typeface.DEFAULT);

        togglePwButton.setOnClickListener(view -> ToggleShowPw(passwordInput, togglePwButton));

        loginButton.setOnClickListener(view -> validateLogin(
                String.valueOf(passwordInput.getText()),
                String.valueOf(emailInput.getText())));

        emailInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                emailInput.setBackground(getDrawable(R.drawable.border_input));
                errorEmailText.setVisibility(View.INVISIBLE);
            }
        });

        passwordInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                passwordLayout.setBackground(getDrawable(R.drawable.border_input));
                errorPasswordText.setVisibility(View.INVISIBLE);
            }
        });

        signupText.setOnClickListener(view -> {
            startActivity(new Intent(LoginScreen.this, SignupEmailScreen.class));
        });

    }

    public void ToggleShowPw(EditText passwordInput, ImageButton imageButton) {
        isShow = !isShow;
        if (!isShow) {
            passwordInput.setTransformationMethod(new PasswordTransformationMethod());
            imageButton.setImageResource(R.drawable.eye_close);
        } else {
            passwordInput.setTransformationMethod(null);
            imageButton.setImageResource(R.drawable.eye_open);
        }
    }

    public void validateLogin(String password_value, String email_value) {
        if (email_value.isEmpty() && password_value.isEmpty()) {
            emailInput.setBackground(getDrawable(R.drawable.input_error));
            errorEmailText.setVisibility(View.VISIBLE);
            passwordLayout.setBackground(getDrawable(R.drawable.input_error));
            errorPasswordText.setVisibility(View.VISIBLE);
        } else if (email_value.isEmpty()) {
            emailInput.setBackground(getDrawable(R.drawable.input_error));
            errorEmailText.setVisibility(View.VISIBLE);
        } else {
            passwordLayout.setBackground(getDrawable(R.drawable.input_error));
            errorPasswordText.setVisibility(View.VISIBLE);
        }
    }
}