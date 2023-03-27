package com.midterm.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.midterm.restaurant_app.view.SignIn;

public class Reset_pass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        Button button = findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Reset_pass.this, Confirm_reset_pass.class);
                startActivity(intent1);
            }
        });

        TextView button_txt_snin = findViewById(R.id.textView_SignIn);
        button_txt_snin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Reset_pass.this, SignIn.class);
                startActivity(intent2);
            }
        });
    }
}