package com.trim;

import static com.trim.MyUtils.validatePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignupInfoScreen extends AppCompatActivity {
    ImageButton togglePwButton, toggleConfirmPwButton;
    EditText passwordInput, confirmPwInput, fullNameInput;
    TextView errorFullNameText, errorPwText, errorConfirmPwText;
    Button signUpButton;
    RelativeLayout passwordLayout, confirmPwLayout, signUpLayout;

    private boolean isPwShow = false, isConfirmPwShow = false, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_info_screen);

        togglePwButton = findViewById(R.id.toggle_pw_button);
        toggleConfirmPwButton = findViewById(R.id.toggle_confirm_pw_button);
        passwordInput = findViewById(R.id.password_edit_text);
        confirmPwInput = findViewById(R.id.confirm_pw_edit_text);
        fullNameInput = findViewById(R.id.full_name_input);
        errorFullNameText = findViewById(R.id.error_full_name_text);
        errorPwText = findViewById(R.id.error_password_text);
        errorConfirmPwText = findViewById(R.id.error_confirm_pw_text);
        signUpButton = findViewById(R.id.sign_up_button);
        passwordLayout = findViewById(R.id.password_input);
        confirmPwLayout = findViewById(R.id.confirm_password_input);
        signUpLayout = findViewById(R.id.sign_up_layout);

        fullNameInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                fullNameInput.setBackground(getDrawable(R.drawable.border_input));
                errorFullNameText.setVisibility(View.INVISIBLE);
            } else {
                hideKeyboard(view);
            }
        });

        passwordInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                passwordLayout.setBackground(getDrawable(R.drawable.border_input));
                errorPwText.setVisibility(View.INVISIBLE);
            } else {
                hideKeyboard(view);
            }
        });

        confirmPwInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                signUpLayout.setPadding(0, 0, 0, 200);
                confirmPwLayout.setBackground(getDrawable(R.drawable.border_input));
                errorConfirmPwText.setVisibility(View.INVISIBLE);
            } else {
                hideKeyboard(view);
                signUpLayout.setPadding(0,0,0,0);
            }
        });

        togglePwButton.setOnClickListener(view -> {
            isPwShow = isToggleShowPw(passwordInput, togglePwButton, isPwShow);
        });

        toggleConfirmPwButton.setOnClickListener(view -> {
            isConfirmPwShow = isToggleShowPw(confirmPwInput, toggleConfirmPwButton, isConfirmPwShow);
        });

        signUpButton.setOnClickListener(view -> {
            validateSignUpInfo(
                    String.valueOf(fullNameInput.getText()),
                    String.valueOf(passwordInput.getText()),
                    String.valueOf(confirmPwInput.getText())
            );
            if (!flag) {
                String userInfoString = ((UserInfo) this.getApplication()).toString();
                Toast.makeText(this, userInfoString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean isToggleShowPw(EditText passwordInput, ImageButton imageButton, boolean isShow) {
        isShow = !isShow;
        if (!isShow) {
            passwordInput.setTransformationMethod(new PasswordTransformationMethod());
            imageButton.setImageResource(R.drawable.eye_close);
        } else {
            passwordInput.setTransformationMethod(null);
            imageButton.setImageResource(R.drawable.eye_open);
        }
        return isShow;
    }

    public void validateSignUpInfo(String fullNameValue, String passwordValue, String confirmPwValue) {
        if (fullNameValue.isEmpty()) {
            fullNameInput.setBackground(getDrawable(R.drawable.input_error));
            errorFullNameText.setVisibility(View.VISIBLE);
            flag = true;
        } else {
            flag = false;
            ((UserInfo) this.getApplication()).setFullName(fullNameValue);
        }

        if (passwordValue.isEmpty() || !validatePassword(passwordValue)) {
            passwordLayout.setBackground(getDrawable(R.drawable.input_error));
            errorPwText.setVisibility(View.VISIBLE);
            flag = true;
        } else {
            flag = false;
            ((UserInfo) this.getApplication()).setPassword(passwordValue);
        }

        if (confirmPwValue.isEmpty() || !confirmPwValue.equals(passwordValue)) {
            confirmPwLayout.setBackground(getDrawable(R.drawable.input_error));
            errorConfirmPwText.setVisibility(View.VISIBLE);
            flag = true;
        }
    }
}