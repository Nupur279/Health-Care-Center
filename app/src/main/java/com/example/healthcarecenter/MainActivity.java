package com.example.healthcarecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;

    TextView CreateNewAccountNgo ,createNewAccountAsDonar,forgotPassword;
    EditText InputEmail, InputPassword;
    Button loginbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
//    ImageView btngoggle;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        InputEmail = findViewById(R.id.InputEmail);
        InputPassword = findViewById(R.id.InputPassword);
        loginbtn = findViewById(R.id.loginbtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
//        btngoggle = findViewById(R.id.btngoggle);
        forgotPassword =findViewById(R.id.forgotPassword);
        createNewAccountAsDonar=findViewById(R.id.createNewAccountAsDonar);


        createNewAccountAsDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, forgot_password.class);
                startActivity(intent);


            }
        });

        CreateNewAccountNgo = findViewById(R.id.CreateNewAccountNgo);
        CreateNewAccountNgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NGO_registration.class);
                startActivity(intent);

            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentlogin();
            }
        });

//        btngoggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,GoogleSign.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//            }
//        });


    }

    private void currentlogin() {
        String email = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();


        if (!email.matches(emailPattern)) {
            InputEmail.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            InputPassword.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage(" Please Wait While  Login...");
            progressDialog.setTitle("Login..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        if (mAuth.getCurrentUser().isEmailVerified()){

                            sendUserToNextActivity();
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, " Please Verify Your Email Address" +task.getException(), Toast.LENGTH_SHORT).show();


                        }

                    }
                }
            });
        }
    }



    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this ,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}