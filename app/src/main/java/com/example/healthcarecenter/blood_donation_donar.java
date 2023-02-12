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
import android.widget.TextView;
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

public class blood_donation_donar extends AppCompatActivity {

    EditText  Blood_Donar, blood_Donar_no;
    DatePickerDialog.OnDateSetListener dateSetListener;
    RadioButton no, yes, female, male;
    Toolbar toolbar;
    Spinner blood_spinner, Relation_spinner;
    String relation_with_donor, blood_type, date="date";;
    Button birth_date, blood_btn;
    ArrayAdapter<CharSequence> adapter_blood;
    ArrayAdapter<CharSequence> adapter_relation;
    String donar_name, donar_no,  donar_birth_date, Bgender, BmedicalHistory;
    String donar_age;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView bdate ,age;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    Integer Age;
    DatabaseReference databaseReference;
    Button blood_upload_medical_doc, blood_select_medical_doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_blood_donation_donar);

        birth_date = findViewById(R.id.birth_date);
        bdate = findViewById(R.id.bdate);
        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);
        female = findViewById(R.id.female);
        male = findViewById(R.id.male);
        age = findViewById(R.id.age);
        blood_btn = findViewById(R.id.blood_btn);
        Blood_Donar = findViewById(R.id.Blood_Donar);
        blood_Donar_no = findViewById(R.id.blood_Donar_no);
        blood_spinner = findViewById(R.id.blood_spinner);
        Relation_spinner = findViewById(R.id.Relation_spinner);
        blood_upload_medical_doc = findViewById(R.id.blood_upload_medical_doc);
        blood_select_medical_doc = findViewById(R.id.blood_select_medical_doc);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("User Data");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(" Blood Donor Information");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        birth_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(blood_donation_donar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        if(year<=2004&&month<=12&&day<=31){
                            bdate.setText(date);
                            Age=2022-year;
                            String a=Integer.toString(Age);
                            age.setText(a);
                        }else{
                            Toast.makeText(blood_donation_donar.this, "Your age is not 18+", Toast.LENGTH_LONG).show();
                            bdate.setText("");
                            age.setText("");
                        }
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

       yes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               blood_upload_medical_doc.setVisibility(View.VISIBLE);
               blood_select_medical_doc.setVisibility(View.VISIBLE);
           }
       });
       no.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               blood_upload_medical_doc.setVisibility(View.INVISIBLE);
               blood_select_medical_doc.setVisibility(View.INVISIBLE);

           }
       });

        blood_select_medical_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();

            }
        });



        adapter_relation = ArrayAdapter.createFromResource(this, R.array.relation_with_donar, android.R.layout.simple_spinner_item);
        adapter_blood = ArrayAdapter.createFromResource(this, R.array.blood_types, android.R.layout.simple_spinner_item);
        adapter_relation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Relation_spinner.setAdapter(adapter_relation);
        blood_spinner.setAdapter(adapter_blood);


        Relation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                relation_with_donor = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        blood_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                blood_type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        blood_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donar_name = Blood_Donar.getText().toString();
                donar_age = age.getText().toString();
                donar_no = blood_Donar_no.getText().toString();
                donar_birth_date = bdate.getText().toString();



                if (donar_name.isEmpty()) {
                    Blood_Donar.setError("Enter Donor Name");
                } else if (donar_birth_date.isEmpty()) {
                    bdate.setError("Enter Donor Birth Date");
                } else if (donar_no.isEmpty() || donar_no.length() > 10) {
                    blood_Donar_no.setError("Enter valid No");
                } else {

                    if (male.isChecked()) {
                        Bgender = "Male";

                    } else if (female.isChecked()) {
                        Bgender = "Female";

                    }

                    if (male.isChecked()||female.isChecked()){
                        Toast.makeText(blood_donation_donar.this, "", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(blood_donation_donar.this, "Select Gender", Toast.LENGTH_SHORT).show();
                    }

                    if (yes.isChecked()) {
                        BmedicalHistory = "Doner has Medical Issue";

                    } else if (female.isChecked()) {
                        BmedicalHistory = "Doner hasn't Medical Issue";

                    }
                    BloodHelperClass bloodHelperClass = new BloodHelperClass(blood_type, donar_no, relation_with_donor, donar_age, donar_name, donar_birth_date, Bgender, BmedicalHistory);
                    reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(bloodHelperClass);

                    DialogClass dialogClass = new DialogClass();
                    dialogClass.show(getSupportFragmentManager(), "Dialog Class");

                    Toast.makeText(blood_donation_donar.this, "Thank You For Donation", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void selectPDF() {
        Intent pdfpickerIntent = new Intent();
        pdfpickerIntent.setType("application/pdf");
        pdfpickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(pdfpickerIntent, "PDF FILE SELECT"), 12);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null) {


            blood_upload_medical_doc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    blood_select_medical_doc.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/") + 1));
                    uploadPDFFileFirebase(data.getData());
                }
            });
        }

    }

    private void uploadPDFFileFirebase(Uri data) {
        final ProgressDialog pdfPD = new ProgressDialog(this);
        pdfPD.setTitle("File is loding...");
        pdfPD.show();

        StorageReference pdfreference = storageReference.child("User Data").child(firebaseAuth.getCurrentUser().getUid() + "/Medical_Documents" + "/Blood Docs" + "/Blood_Doc.pdf");

        pdfreference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri uri = uriTask.getResult();

                putPDF putPDF = new putPDF(blood_select_medical_doc.getText().toString(), uri.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);

                Toast.makeText(blood_donation_donar.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                pdfPD.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pdfPD.setMessage("File Uploaded.." + (int) progress + "%");

            }
        });
    }
}