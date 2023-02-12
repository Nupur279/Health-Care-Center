package com.example.healthcarecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class NGO_registration extends AppCompatActivity {


    EditText nago_land_line,Ngo_ComfirmPassword,ngo_InputPassword,ngo_input_full_name,ngo_InputEmail,ngo_phone_no;
    RadioButton privateco,publicco;
    Button ngo_loginbtn,upload_licence, select_licence;
    String landline,Ngo_full_name,Ngo_email,Ngo_password,Ngo_comfirm_password,company_Type,ngo_no,imageUrl,currentUserId ;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressDialog progressDialog;
    StorageReference storageReference;
    FirebaseStorage storage;
    DatabaseReference databaseReference;
    private Uri imageUri;
    private static final int PICK_IMAGE =1 ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_registration);

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        ngo_loginbtn= findViewById(R.id.ngo_loginbtn);
        select_licence=findViewById(R.id.select_licence);
        upload_licence=findViewById(R.id.upload_licence);
        publicco=findViewById(R.id.publicco);
        privateco= findViewById(R.id.privateco);
        ngo_input_full_name=findViewById(R.id.ngo_input_full_name);
        ngo_InputEmail=findViewById(R.id.ngo_InputEmail);
        ngo_InputPassword=findViewById(R.id.ngo_InputPassword);
        Ngo_ComfirmPassword=findViewById(R.id.Ngo_ComfirmPassword);
        nago_land_line=findViewById(R.id.nago_land_line);

        ngo_phone_no=findViewById(R.id.ngo_phone_no);
        firebaseAuth=FirebaseAuth.getInstance();
        rootNode=FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("License Data");
        reference=rootNode.getReference("Health Care Center");
        progressDialog=new ProgressDialog(this);

        select_licence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });

        ngo_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                landline=nago_land_line.getText().toString();
                Ngo_full_name=ngo_input_full_name.getText().toString();
                Ngo_email=ngo_InputEmail.getText().toString();
                Ngo_password=ngo_InputPassword.getText().toString();
                Ngo_comfirm_password=Ngo_ComfirmPassword.getText().toString();
                ngo_no=ngo_phone_no.getText().toString();

                if (Ngo_full_name.isEmpty()) {
                    ngo_input_full_name.setError("Enter NGO Name");
                } else if (!Ngo_email.matches(emailPattern)) {
                    ngo_InputEmail.setError("Enter Correct Email");
                } else if (Ngo_password.isEmpty() && Ngo_password.length() < 6) {
                    ngo_InputPassword.setError("Enter Proper Password");
                } else if (!Ngo_password.matches(Ngo_comfirm_password)) {
                    Ngo_ComfirmPassword.setError("Password Not Matched");
                } else if (ngo_no.isEmpty() || ngo_no.length() > 11) {
                    ngo_phone_no.setError("Enter valid No");
                }else if (landline.isEmpty() || landline.length() > 11) {
                    nago_land_line.setError("Enter valid No");
                }
                else {
                    if (publicco.isChecked()) {
                        company_Type = "Public Limited Company";

                    } else if (privateco.isChecked()) {
                        company_Type = "Private Limited Company";
                    }

                    firebaseAuth.createUserWithEmailAndPassword(Ngo_email, Ngo_comfirm_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            NGOHelperClass ngo = new NGOHelperClass(Ngo_full_name, Ngo_email, Ngo_comfirm_password, ngo_no, landline, company_Type, imageUrl);
                                            reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(ngo);

                                            Toast.makeText(NGO_registration.this, "Registration Successful ,Check Mail for Verification", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(NGO_registration.this, MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }else {

                                            Toast.makeText(NGO_registration.this, "" + task.getException(), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }
                        }
                    });


                }

            }
        });

    }
    private void selectPDF() {
        Intent pdfpickerIntent = new Intent();
        pdfpickerIntent.setType("application/pdf");
        pdfpickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(pdfpickerIntent, "Select License"), 12);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            upload_licence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select_licence.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/") + 1));
                    uploadPDFFileFirebase(data.getData());
                }
            });
        }

    }
    private void uploadPDFFileFirebase(Uri data) {
        final ProgressDialog pdfPD = new ProgressDialog(this);
        pdfPD.setTitle("File loading...");
        pdfPD.show();

        StorageReference pdfreference = storageReference.child("License Data").child(firebaseAuth.getCurrentUser().getUid() + "/NGO" + "/License");

        pdfreference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri uri = uriTask.getResult();

                putPDF putPDF = new putPDF(select_licence.getText().toString(), uri.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);

                Toast.makeText(NGO_registration.this, "License Uploaded", Toast.LENGTH_SHORT).show();
                pdfPD.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pdfPD.setMessage("License Uploaded.." + (int) progress + "%");

            }
        });
    }

}