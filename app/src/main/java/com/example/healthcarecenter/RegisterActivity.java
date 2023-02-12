package com.example.healthcarecenter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity {


    private static final int PICK_IMAGE =1 ;
    TextView alreadyHaveAccount;
    EditText InputEmail,InputPassword,ComfirmPassword ,register_no ,input_full_name;
    Button Register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String name,register_phone,ConfirmPassword,password,email;
    StorageReference storageReference;

    private DatabaseReference userDataRefer;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private Uri imageUri;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        alreadyHaveAccount =findViewById(R.id.CreateNewAccountNgo);
        input_full_name = findViewById(R.id.input_full_name);
        InputEmail = findViewById(R.id.InputEmail);
        InputPassword = findViewById(R.id.InputPassword);
        ComfirmPassword = findViewById(R.id.ComfirmPassword);
        Register = findViewById(R.id.loginbtn);
        register_no = findViewById(R.id.Phone_no);

        progressDialog = new ProgressDialog(this);
        mAuth =  FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("Health Care Center");
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();


        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });


    }









    private void PerforAuth() {
        name = input_full_name.getText().toString().trim();
        email = InputEmail.getText().toString().trim();
        password = InputPassword.getText().toString().trim();
        ConfirmPassword = ComfirmPassword.getText().toString().trim();
        register_phone = register_no.getText().toString().trim();

        if (name.isEmpty()){
            input_full_name.setError("Enter Your Name");
        }
        else if(!email.matches(emailPattern))
        {
            InputEmail.setError("Enter Correct Email");
        }else if (password.isEmpty() || password.length()<6)
        {
            InputPassword.setError("Enter Proper Password");
        }else if (!password.matches(ConfirmPassword))
        {
            ComfirmPassword.setError("Password Not Matched");
        }else if(register_phone.isEmpty() || register_phone.length()>11)
        {
            register_no.setError("Enter valid No");
        }
        else
        {


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    UserHelperClass userHelperClass=new UserHelperClass(name,email,ConfirmPassword,register_phone);
                                    reference.child(mAuth.getCurrentUser().getUid()).setValue(userHelperClass);

                                    Toast.makeText(RegisterActivity.this, "Registration Successful , check Mail for verification ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this ,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                }else {
                                    Toast.makeText(RegisterActivity.this, "" +task.getException(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

                    }

                }
            });


        }




    }



}

