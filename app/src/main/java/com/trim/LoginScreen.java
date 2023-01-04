package com.trim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    EditText passwordInput;
    ImageButton togglePwButton;
    Button loginButton;
    RelativeLayout passwordLayout;

    private boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        passwordInput = findViewById(R.id.password_edit_text);
        togglePwButton = findViewById(R.id.togglePwButton);
        loginButton = findViewById(R.id.button_login);
        passwordLayout = findViewById(R.id.password_input);

        passwordInput.setTypeface(Typeface.DEFAULT);

        togglePwButton.setOnClickListener(view -> ToggleShowPw(passwordInput, togglePwButton));

        loginButton.setOnClickListener(view -> validateLogin(String.valueOf(passwordInput.getText())));

        passwordInput.setOnClickListener(view -> passwordLayout.setBackground(getDrawable(R.drawable.border_input)));

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

    public void validateLogin(String password_value) {
        if (password_value.length() == 0) {
            passwordLayout.setBackground(getDrawable(R.drawable.input_error));
        }
    }
}