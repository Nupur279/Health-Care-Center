package com.example.healthcarecenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Medicine_donation extends AppCompatActivity {

    ArrayAdapter<CharSequence> adapter_medicine;
    String medicine_name =" ",Crocin_no="0" ,Crocin_name =" " ,Paracetamol_no="0" ,Paracetamol_name =" ",
            bComplex_no="0",bComplex_name =" ",vicks_Action_no="0",Vicks_Action_name =" ",Vitafol_no="0",vitafol_name =" ",omee_no="0",Omee_name =" ",
            other_med_name =" " ,Other_med_no="0";
    Button med_submit, expiry_crocin, expiry_paracetamol, expiry_Vicks_Action, expiry_BComplex, expiry_vitafol, expiry_Omee, expiry_Other ;
    TextView  med_donar_city, crocin_date, paracetamol_date, Vicks_Action_date, BComplex_date, vitafol_date, Omee_date, Other_date;
    EditText med_donar_name, med_email;
    String City_name;
    private ImageView img;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    private  static final int PICK_IMAGE=1;
    CheckBox crocin, paracetamol, Vicks_Action, BComplex, vitafol, Omee, Other;
    EditText crocin_no, paracetamol_no, Vicks_Action_no, BComplex_no, vitafol_no, Omee_no, Other_no, Other_med_name;
    Spinner city_name;
    String date="No Idea";
    String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_donation);

        med_donar_name = findViewById(R.id.med_donar_name);
        med_email = findViewById(R.id.med_email);
        med_donar_city = findViewById(R.id.med_donar_city);
        med_submit = findViewById(R.id.med_submit);
        img= (ImageView) findViewById(R.id.imageView);
        firebaseAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Medicine Donation");

        crocin = findViewById(R.id.crocin);
        crocin_no = findViewById(R.id.crocin_no);
        expiry_crocin = findViewById(R.id.expiry_crocin);
        crocin_date = findViewById(R.id.crocin_date);

        paracetamol = findViewById(R.id.paracetamol);
        paracetamol_no = findViewById(R.id.paracetamol_no);
        expiry_paracetamol = findViewById(R.id.expiry_paracetamol);
        paracetamol_date = findViewById(R.id.paracetamol_date);

        Vicks_Action = findViewById(R.id.Vicks_Action);
        Vicks_Action_no = findViewById(R.id.Vicks_Action_no);
        expiry_Vicks_Action = findViewById(R.id.expiry_Vicks_Action);
        Vicks_Action_date = findViewById(R.id.Vicks_Action_date);

        vitafol = findViewById(R.id.vitafol);
        vitafol_no = findViewById(R.id.vitafol_no);
        expiry_vitafol = findViewById(R.id.expiry_vitafol);
        vitafol_date = findViewById(R.id.vitafol_date);

        Omee = findViewById(R.id.Omee);
        Omee_no = findViewById(R.id.Omee_no);
        expiry_Omee = findViewById(R.id.expiry_Omee);
        Omee_date = findViewById(R.id.Omee_date);

        BComplex = findViewById(R.id.BComplex);
        BComplex_no = findViewById(R.id.BComplex_no);
        expiry_BComplex = findViewById(R.id.expiry_BComplex);
        BComplex_date = findViewById(R.id.BComplex_date);

        Other = findViewById(R.id.Other);
        Other_no = findViewById(R.id.Other_no);
        Other_med_name=findViewById(R.id.Other_med_name);
        expiry_Other = findViewById(R.id.expiry_Other);
        Other_date=findViewById(R.id.Other_date);


        city_name = findViewById(R.id.city_name);
        ArrayAdapter<CharSequence> adapter_medicine = ArrayAdapter.createFromResource(this, R.array.city_name, android.R.layout.simple_spinner_item);
        adapter_medicine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_name.setAdapter(adapter_medicine);

        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Title"),PICK_IMAGE);
            }
        });

        crocin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    crocin_no.setVisibility(View.VISIBLE);
                    expiry_crocin.setVisibility(View.VISIBLE);
                    crocin_date.setVisibility(View.VISIBLE);
                    if (crocin.isChecked()) {
                        Crocin_name="Crocin";
                        crocin_no.setText(Crocin_no);
                    }

                } else {
                    crocin_no.setVisibility(View.INVISIBLE);
                    expiry_crocin.setVisibility(View.INVISIBLE);
                    crocin_date.setVisibility(View.INVISIBLE);

                }
            }
        });

        paracetamol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    paracetamol_no.setVisibility(View.VISIBLE);
                    expiry_paracetamol.setVisibility(View.VISIBLE);
                    paracetamol_date.setVisibility(View.VISIBLE);
                    if (paracetamol.isChecked()) {
                        Paracetamol_name="Paracetamol";
                        paracetamol_no.setText(Paracetamol_no);
                    }
                } else {
                    paracetamol_no.setVisibility(View.INVISIBLE);
                    expiry_paracetamol.setVisibility(View.INVISIBLE);
                    paracetamol_date.setVisibility(View.INVISIBLE);
                }
            }
        });

        BComplex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    BComplex_no.setVisibility(View.VISIBLE);
                    expiry_BComplex.setVisibility(View.VISIBLE);
                    BComplex_date.setVisibility(View.VISIBLE);
                    if (BComplex.isChecked()) {
                        bComplex_name="BComplex";
                        BComplex_no.setText(bComplex_no);
                    }
                } else {
                    BComplex_no.setVisibility(View.INVISIBLE);
                    expiry_BComplex.setVisibility(View.INVISIBLE);
                    BComplex_date.setVisibility(View.INVISIBLE);
                }
            }
        });

        Vicks_Action.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Vicks_Action_no.setVisibility(View.VISIBLE);
                    expiry_Vicks_Action.setVisibility(View.VISIBLE);
                    Vicks_Action_date.setVisibility(View.VISIBLE);
                    if (Vicks_Action.isChecked()) {
                        Vicks_Action_name="Vicks_Action";
                        Vicks_Action_no.setText(vicks_Action_no);
                    }
                } else {
                    Vicks_Action_no.setVisibility(View.INVISIBLE);
                    expiry_Vicks_Action.setVisibility(View.INVISIBLE);
                    Vicks_Action_date.setVisibility(View.INVISIBLE);
                }
            }
        });

        vitafol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    vitafol_no.setVisibility(View.VISIBLE);
                    expiry_vitafol.setVisibility(View.VISIBLE);
                    vitafol_date.setVisibility(View.VISIBLE);
                    if (vitafol.isChecked()) {
                        vitafol_name="Vitafol";
                        vitafol_no.setText(Vitafol_no);
                    }
                } else {
                    vitafol_no.setVisibility(View.INVISIBLE);
                    expiry_vitafol.setVisibility(View.INVISIBLE);
                    vitafol_date.setVisibility(View.INVISIBLE);
                }
            }
        });

        Omee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Omee_no.setVisibility(View.VISIBLE);
                    expiry_Omee.setVisibility(View.VISIBLE);
                    Omee_date.setVisibility(View.VISIBLE);
                    if (Omee.isChecked()) {
                        Omee_name="Omee";
                        Omee_no.setText(omee_no);
                    }
                } else {
                    Omee_no.setVisibility(View.INVISIBLE);
                    expiry_Omee.setVisibility(View.INVISIBLE);
                    Omee_date.setVisibility(View.INVISIBLE);
                }
            }
        });

        Other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Other_no.setVisibility(View.VISIBLE);
                    Other_med_name.setVisibility(View.VISIBLE);
                    expiry_Other.setVisibility(View.VISIBLE);
                    Other_date.setVisibility(View.VISIBLE);
                    if (Other.isChecked()) {
                        other_med_name="Other Medicines";
                        Other_no.setText(Other_med_no);
                    }

                } else {
                    Other_no.setVisibility(View.INVISIBLE);
                    Other_med_name.setVisibility(View.INVISIBLE);
                    expiry_Other.setVisibility(View.INVISIBLE);
                    Other_date.setVisibility(View.INVISIBLE);
                }
            }
        });



        city_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City_name = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        expiry_crocin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;
                        crocin_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        expiry_paracetamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;
                        paracetamol_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        expiry_Vicks_Action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;
                        Vicks_Action_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        expiry_BComplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;
                        BComplex_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        expiry_vitafol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;
                        vitafol_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        expiry_Omee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;
                        Omee_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        expiry_Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(Medicine_donation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date =day+"/"+month+"/"+year;

                        Other_date.setText(date);
                    }
                },year,month,day );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        med_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String med_donar_name1 = med_donar_name.getText().toString();
                String Med_email1 = med_email.getText().toString();
//        String med_email=med_email.getText().toString();
                String med_donar_city = City_name;
                String med_crosin_no = crocin_no.getText().toString();
                String med_crosin_exdate = crocin_date.getText().toString();
                String med_paracetamol_no = paracetamol_no.getText().toString();
                String med_paracetamol_exdate = paracetamol_date.getText().toString();
                String med_vicks_no = Vicks_Action_no.getText().toString();
                String med_vicks_exdate = Vicks_Action_date.getText().toString();
                String med_bcomplex_no = BComplex_no.getText().toString();
                String med_bcomplex_exdate = BComplex_date.getText().toString();
                String med_vitafol_no = vitafol_no.getText().toString();
                String med_vitafol_exdate = vitafol_date.getText().toString();
                String med_omee_no = Omee_no.getText().toString();
                String med_omee_exdate = Omee_date.getText().toString();
                String med_other_name = Other_med_name.getText().toString();
                String med_other_no = Other_no.getText().toString();
                String med_other_exdate = Other_date.getText().toString();

                if (med_donar_name1.isEmpty()) {
                    med_donar_name.setError("Enter donor Name");
                } else if (Med_email1.isEmpty()) {
                    med_email.setError("Enter valid Mail");
                }

                else {

                    if (crocin.isChecked()||paracetamol.isChecked()||BComplex.isChecked()||Vicks_Action.isChecked()||vitafol.isChecked()||Omee.isChecked()){
                        MedicineHelperClass medicineHelperClass = new MedicineHelperClass(med_donar_name1, Med_email1, med_donar_city, med_crosin_no, med_crosin_exdate, med_paracetamol_no, med_paracetamol_exdate, med_vicks_no, med_vicks_exdate, med_bcomplex_no, med_bcomplex_exdate, med_vitafol_no, med_vitafol_exdate, med_omee_no, med_omee_exdate, med_other_name, med_other_no, med_other_exdate);
                        reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(medicineHelperClass);

                        DialogClass dialogClass = new DialogClass();
                        dialogClass.show(getSupportFragmentManager(), "Dialog Class");

                        Toast.makeText(Medicine_donation.this, "Thank You For Donation", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Medicine_donation.this, "You haven't selected any Medicine", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();
            img.setImageURI(uri);
        }
    }
}