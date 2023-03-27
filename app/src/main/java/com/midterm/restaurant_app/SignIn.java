package com.midterm.restaurant_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button button_Signin = findViewById(R.id.btn_snin);
        button_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        TextView button_txt_snup = findViewById(R.id.tv_SignUp);
        button_txt_snup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SignIn.this, SignUp.class);
                startActivity(intent2);
            }
        });


        TextView button_txt_Forgotpass_snin = findViewById(R.id.textView_Forgotpass_snin);
        button_txt_Forgotpass_snin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(SignIn.this, Reset_pass.class);
                startActivity(intent3);
            }
        });

    }
}