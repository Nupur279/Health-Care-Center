package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verification_NGO_Blood_Donation extends AppCompatActivity {

    EditText NGO_ID,NGO_Password_Verification;
    Button vrification_blood_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_ngo_blood_donation);

        NGO_ID = findViewById(R.id.NGO_ID);
        NGO_Password_Verification=findViewById(R.id.NGO_Password_Blood_Verification);
        vrification_blood_btn=findViewById(R.id.vrification_blood_btn);

        vrification_blood_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String veri_Id = NGO_ID.getText().toString();
                String veri_pass = NGO_Password_Verification.getText().toString();

                if (veri_Id.equals("blood")&&veri_pass.equals("123456")){
                    Toast.makeText(Verification_NGO_Blood_Donation.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Verification_NGO_Blood_Donation.this,NGO_Blood_Donation.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Verification_NGO_Blood_Donation.this, "Please Enter Your ID", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}