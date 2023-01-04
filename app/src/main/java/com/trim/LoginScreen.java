package com.trim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginScreen extends AppCompatActivity {
    EditText passwordInput;
    ImageButton togglePwButton;
    private boolean isShow = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        passwordInput = findViewById(R.id.passwordInput);
        togglePwButton = findViewById(R.id.togglePwButton);

        passwordInput.setTypeface(Typeface.DEFAULT);
        togglePwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleShowPw(passwordInput, togglePwButton);
            }
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
}