package com.example.healthcarecenter;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class Organ_Donation extends AppCompatActivity {


        EditText Obirth_date ,OBlood_Donar,Oblood_Donar_no,Oage;
        DatePickerDialog.OnDateSetListener dateSetListener;
        RadioButton no,yes,female,male;
        Toolbar toolbar;
        Spinner Oblood_spinner,ORelation_spinner;
        String Orel,Oblo;
        Button Organ_btn;
        ArrayAdapter<CharSequence> adapter_blood;
        ArrayAdapter<CharSequence> adapter_relation;
        String Odonar_name ,Odonar_no,Odonar_age,Odonar_birth_date,Ogrnder,OmedicalHistory;
        FirebaseDatabase rootNode;
        DatabaseReference reference;
        FirebaseAuth firebaseAuth;
        MaterialButton Ocgan_select_medical_doc,Organ_upload_medical_doc;
        StorageReference storageReference;
        FirebaseStorage firebaseStorage;
        DatabaseReference databaseReference;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_organ_donation);

            Organ_upload_medical_doc=findViewById(R.id.Organ_upload_medical_doc);
            Obirth_date = findViewById(R.id.birth_date);
            no= findViewById(R.id.no);
            yes=findViewById(R.id.yes);
            female=findViewById(R.id.female);
            male=findViewById(R.id.male);
            Oage=findViewById(R.id.age);
            Organ_btn=findViewById(R.id.Organ_btn);
            OBlood_Donar=findViewById(R.id.Organ_Donar);
            Oblood_Donar_no=findViewById(R.id.blood_Donar_no);
            Oblood_spinner=findViewById(R.id.Organ_blood_spinner);
            ORelation_spinner=findViewById(R.id.Organ_Relation_spinner);
            Ocgan_select_medical_doc=findViewById(R.id.Ocgan_select_medical_doc);
            firebaseAuth=FirebaseAuth.getInstance();
            firebaseStorage= FirebaseStorage.getInstance();
            storageReference=firebaseStorage.getReference();
            databaseReference= FirebaseDatabase.getInstance().getReference("User Data");

            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference("Health Care Center");

            Calendar calendar = Calendar.getInstance();
            final  int year = calendar.get(Calendar.YEAR);
            final  int month = calendar.get(Calendar.MONTH);
            final  int day = calendar.get(Calendar.DAY_OF_MONTH);

            Obirth_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog =new DatePickerDialog(Organ_Donation.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month+1;
                            String date =day+"/"+month+"/"+year;
                            Obirth_date.setText(date);



                        }
                    },year,month,day );
                    datePickerDialog.show();

                }
            });
            Ocgan_select_medical_doc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPDF();

                }
            });




            adapter_relation = ArrayAdapter.createFromResource(this, R.array.relation_with_donar, android.R.layout.simple_spinner_item);
            adapter_blood = ArrayAdapter.createFromResource(this, R.array.blood_types, android.R.layout.simple_spinner_item);
            adapter_relation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ORelation_spinner.setAdapter(adapter_relation);
            Oblood_spinner.setAdapter(adapter_blood);


            ORelation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Orel= parent.getItemAtPosition(position).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Oblood_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Oblo= parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });




            Organ_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Odonar_name=OBlood_Donar.getText().toString();
                    Odonar_age =Oage.getText().toString();
                    Odonar_no=Oblood_Donar_no.getText().toString();
                    Odonar_birth_date=Obirth_date.getText().toString();

                    if (male.isChecked()) {
                        Ogrnder = "Male";

                    } else if (female.isChecked()) {
                        Ogrnder = "Female";

                    }

                    if (yes.isChecked()) {
                        OmedicalHistory = "Doner has Medical Issue";

                    } else if (female.isChecked()) {
                        OmedicalHistory = "Doner hasn't Medical Issue";

                    }


                    OrganHelperClass OrganHelperClass=new OrganHelperClass(Oblo,Orel,Odonar_age,Odonar_name,Odonar_birth_date,Ogrnder,OmedicalHistory);
                    reference.child(firebaseAuth.getCurrentUser().getUid()).child("Organ Donation").setValue(OrganHelperClass);



                }
            });






        }
        private void selectPDF() {
            Intent pdfpickerIntent=new Intent();
            pdfpickerIntent.setType("application/pdf");
            pdfpickerIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(pdfpickerIntent,"PDF FILE SELECT"),12);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==12 &&resultCode==RESULT_OK && data!=null && data.getData()!=null){


                Organ_upload_medical_doc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Ocgan_select_medical_doc.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/") +1));
                        uploadPDFFileFirebase(data.getData());
                    }
                });
            }

        }
        private void uploadPDFFileFirebase(Uri data) {
            final ProgressDialog pdfPD=new ProgressDialog(this);
            pdfPD.setTitle("File is loding...");
            pdfPD.show();

            StorageReference pdfreference=storageReference.child("User Data").child(firebaseAuth.getCurrentUser().getUid()+"/Medical_Documents"+"/Organ Docs"+"/Medical_Doc.pdf");

            pdfreference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    Uri uri=uriTask.getResult();

                    putPDF putPDF=new putPDF(Ocgan_select_medical_doc.getText().toString(),uri.toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);

                    Toast.makeText(Organ_Donation.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                    pdfPD.dismiss();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                    pdfPD.setMessage("File Uploaded.."+(int)progress+"%");

                }
            });
        }

    }