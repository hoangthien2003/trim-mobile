package com.trim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VarifyEmailScreen extends AppCompatActivity {
    View continueButtonContainer;
    Button continueButton;
    TextView emailReceiveCode;
    String emailValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varify_email_screen);

        continueButtonContainer = findViewById(R.id.continue_button_include);
        continueButton = continueButtonContainer.findViewById(R.id.button_continue);
        emailReceiveCode = findViewById(R.id.email_receive_code);

        continueButton.setText("Continue");
        emailValue = ((UserInfo) this.getApplication()).getEmail();
        emailReceiveCode.setText(emailValue);

        continueButton.setOnClickListener(view -> {
            startActivity(new Intent(VarifyEmailScreen.this, SignupInfoScreen.class));
        });
    }
}