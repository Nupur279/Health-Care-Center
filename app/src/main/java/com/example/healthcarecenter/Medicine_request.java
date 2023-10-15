package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medicine_request extends AppCompatActivity {

    CheckBox rq_crocin,rq_paracetamol,rq_vicks_action,rq_bcomplex,rq_vitafol,rq_omee;
    EditText rq_user_name,rq_Address,rq_email,rq_crocin_no,rq_paracetamol_no,rq_vicks_action_no,rq_bcomplex_no,rq_vitafol_no,rq_omee_no,rq_other_med_count,rq_other_med;
    Button rq_submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    String rq_crocin_count=" ",rq_paracetamol_count=" ",rq_vicks_action_count=" ",rq_bcomplex_count=" ",rq_vitafol_count=" ",rq_omee_count=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_request);

        rq_crocin=findViewById(R.id.rq_crocin);
        rq_paracetamol=findViewById(R.id.rq_paracetamol);
        rq_vicks_action=findViewById(R.id.rq_vicks_action);
        rq_bcomplex=findViewById(R.id.rq_bcomplex);
        rq_vitafol=findViewById(R.id.rq_vitafol);
        rq_omee=findViewById(R.id.rq_omee);
        rq_user_name=findViewById(R.id.rq_user_name);
        rq_Address=findViewById(R.id.rq_Address);
        rq_email=findViewById(R.id.rq_email);

        rq_crocin_no=findViewById(R.id.rq_crocin_no);
        rq_paracetamol_no=findViewById(R.id.rq_paracetamol_no);
        rq_vicks_action_no=findViewById(R.id.rq_vicks_action_no);
        rq_bcomplex_no=findViewById(R.id.rq_bcomplex_no);
        rq_vitafol_no=findViewById(R.id.rq_vitafol_no);
        rq_omee_no=findViewById(R.id.rq_omee_no);
        rq_other_med_count=findViewById(R.id.rq_other_med_count);
        rq_other_med=findViewById(R.id.rq_other_med);
        rq_submit=findViewById(R.id.rq_submit);

        firebaseAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Medicine_Requests");

        rq_crocin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rq_crocin_no.setVisibility(View.VISIBLE);
                    if (rq_crocin.isChecked()) {

                        rq_crocin_no.setText(rq_crocin_count); }

                } else {
                    rq_crocin_no.setVisibility(View.INVISIBLE);

                }
            }
        });
        rq_paracetamol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rq_paracetamol_no.setVisibility(View.VISIBLE);
                    if (rq_paracetamol.isChecked()) {

                        rq_paracetamol_no.setText(rq_paracetamol_count); }

                } else {
                    rq_paracetamol_no.setVisibility(View.INVISIBLE);

                }
            }
        });
        rq_vicks_action.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rq_vicks_action_no.setVisibility(View.VISIBLE);
                    if (rq_vicks_action.isChecked()) {

                        rq_vicks_action_no.setText(rq_vicks_action_count); }

                } else {
                    rq_vicks_action_no.setVisibility(View.INVISIBLE);

                }
            }
        });
        rq_bcomplex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rq_bcomplex_no.setVisibility(View.VISIBLE);
                    if (rq_bcomplex.isChecked()) {

                        rq_bcomplex_no.setText(rq_bcomplex_count); }

                } else {
                    rq_bcomplex_no.setVisibility(View.INVISIBLE);

                }
            }
        });
        rq_vitafol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rq_vitafol_no.setVisibility(View.VISIBLE);
                    if (rq_vitafol.isChecked()) {

                        rq_vitafol_no.setText(rq_vitafol_count); }

                } else {
                    rq_vitafol_no.setVisibility(View.INVISIBLE);

                }
            }
        });
        rq_omee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rq_omee_no.setVisibility(View.VISIBLE);
                    if (rq_omee.isChecked()) {

                        rq_omee_no.setText(rq_omee_count); }

                } else {
                    rq_omee_no.setVisibility(View.INVISIBLE);

                }
            }
        });

        rq_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(rq_user_name.getText().toString())) {
                    rq_user_name.setError("Enter donor's name");

                    Toast.makeText(Medicine_request.this, "Enter donor's name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(rq_Address.getText().toString())) {
                    rq_Address.setError("Enter Valid Number");
                    Toast.makeText(Medicine_request.this, "Enter Email ID", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(rq_email.getText().toString())) {
                    rq_email.setError("Enter Email ID");
                    Toast.makeText(Medicine_request.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }else {
                    insertMedicalData();
                }

            }
        });
    }

    private void insertMedicalData() {
        String reuestUser_name =rq_user_name.getText().toString();
        String reuest_address=rq_Address.getText().toString();
        String request_email=rq_email.getText().toString();
        String rq_crocin_count=rq_crocin_no.getText().toString();
        String rq_paracetamol_count=rq_paracetamol_no.getText().toString();
        String rq_vicks_action_count=rq_vicks_action_no.getText().toString();
        String rq_bcomplex_count=rq_bcomplex_no.getText().toString();
        String rq_vitafol_count=rq_vitafol_no.getText().toString();
        String rq_omee_count=rq_omee_no.getText().toString();
        String req_other_medicine =rq_other_med.getText().toString();
        String req_other_medicine_count=rq_other_med_count.getText().toString();

        request_medicine_Helper_class request_medicine_Helper_class1 = new request_medicine_Helper_class(reuestUser_name,reuest_address,request_email,rq_crocin_count,rq_paracetamol_count,rq_vicks_action_count,rq_bcomplex_count,rq_vitafol_count,rq_omee_count,req_other_medicine,req_other_medicine_count);
        reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(request_medicine_Helper_class1);

        DialogClass dialogClass = new DialogClass();
        dialogClass.show(getSupportFragmentManager(), "Dialog Class");

        Toast.makeText(this, "Requirement Submitted", Toast.LENGTH_SHORT).show();



    }
}