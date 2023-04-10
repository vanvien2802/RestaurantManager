package com.midterm.restaurant_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.databinding.ActivitySignUpBinding;
import com.midterm.restaurant_app.model.Account;

public class SignUp extends AppCompatActivity {
    private Button btnCreateUser;
    private EditText edtFullName;
    private EditText edtNameAcc;
    private EditText edtAddress;
    private EditText edtEmail;
    private EditText edtPass;
    private EditText edtPhoneNumber;
    private ProgressBar progressBar;

    private ActivitySignUpBinding bindingSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingSignUp = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = bindingSignUp.getRoot();
        setContentView(view);
        progressBar = bindingSignUp.progressCircular;
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();


        TextView button_txt_SignIn = bindingSignUp.textViewSignIn;
        button_txt_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        btnCreateUser = bindingSignUp.btnCreateUser;
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            
        }
    }
    
    private void registerAccount(){
        edtFullName = bindingSignUp.edtNewName;
        edtNameAcc = bindingSignUp.edtNewNameAccount;
        edtAddress = bindingSignUp.edtNewAddress;
        edtEmail = bindingSignUp.edtNewEmail;
        edtPass = bindingSignUp.edtNewPass;
        edtPhoneNumber = bindingSignUp.edtNewPhone;
        if (edtFullName.getText().toString().trim().isEmpty()){
            edtFullName.setError("Name Required");
            edtFullName.requestFocus();
            return;
        }
        if (edtNameAcc.getText().toString().trim().isEmpty()){
            edtNameAcc.setError("Name Account Required");
            edtNameAcc.requestFocus();
            return;
        }
        if (edtAddress.getText().toString().trim().isEmpty()){
            edtAddress.setError("Address Required");
            edtAddress.requestFocus();
            return;
        }
        if (edtEmail.getText().toString().trim().isEmpty()){
            edtEmail.setError("Email Required");
            edtEmail.requestFocus();
            return;
        }
        if (edtPass.getText().toString().trim().isEmpty()){
            edtPass.setError("Password Required");
            edtPass.requestFocus();
            return;
        }
        if (edtPass.getText().toString().trim().length() < 6){
            edtPass.setError("Password should be atleast 6 characters long");
            edtPass.requestFocus();
            return;
        }
        if (edtPhoneNumber.getText().toString().trim().isEmpty()){
            edtPhoneNumber.setError("Phone Number Required");
            edtPhoneNumber.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString().trim(),edtPass.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Account account = new Account(
                                    idAccount(),
                                    edtNameAcc.getText().toString().trim(),
                                    edtPass.getText().toString().trim(),
                                    edtFullName.getText().toString().trim(),
                                    edtPhoneNumber.getText().toString().trim(),
                                    edtEmail.getText().toString().trim(),
                                    edtAddress.getText().toString().trim(),
                                    "",
                                    0
                            );
                            FirebaseDatabase.getInstance().getReference("Account")
                                    .child(idAccount())
                                    .setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()){
                                                Toast.makeText(SignUp.this, "Register Successfully !",Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(SignUp.this, "Register Fail !",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(SignUp.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String idAccount() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Account");
        final int[] max = {0};
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Account account = dataSnapshot.getValue(Account.class);
                    int num =Integer.parseInt(account.getIdAcc().toString().trim());
                    if(num > max[0])
                        max[0] = num;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(max[0] <10){
            return "Acc0"+max[0];
        }
        return "Acc"+max[0];
    }
}