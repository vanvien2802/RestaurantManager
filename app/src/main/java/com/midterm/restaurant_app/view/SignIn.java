package com.midterm.restaurant_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.Reset_pass;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtUser;
    private EditText edtPass;

    private static final int RC_SIGN_IN = 9001;
    private ConstraintLayout btn_google_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        edtUser = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);

        btn_google_login = findViewById(R.id.btn_google_login);

        Button button_Signin = findViewById(R.id.btn_snin);
        button_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtUser.getText().toString().equals("") && !edtPass.getText().toString().equals("")){
                    login(edtUser.getText().toString(),edtPass.getText().toString());
                }
                else if(edtUser.getText().toString().equals("")){
                    Toast toast = Toast.makeText(SignIn.this,"Enter your user !", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(edtPass.getText().toString().equals("")){
                    Toast toast = Toast.makeText(SignIn.this,"Enter your password !", Toast.LENGTH_SHORT);
                    toast.show();
                }
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

    private void login(String email, String pass){
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else{
                            Toast toast = Toast.makeText(SignIn.this,"User or Password error", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
    }
}