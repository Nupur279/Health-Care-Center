package com.example.healthcarecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    Button forget;
    EditText foremail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        foremail = findViewById(R.id.edit1);
        forget = findViewById(R.id.forget);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = foremail.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(forgot_password.this,"Please Enter Your Mail",Toast.LENGTH_LONG).show();
                }else {
                    forgetPassword();
                }
            }
        });
    }

    private void forgetPassword() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgot_password.this,"Please Check Your Email",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(forgot_password.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(forgot_password.this,"Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}