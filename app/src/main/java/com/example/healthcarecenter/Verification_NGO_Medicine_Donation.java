package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verification_NGO_Medicine_Donation extends AppCompatActivity {

    EditText NGO_ID_money,NGO_Password_Verification_money;
    Button vrification_money_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_ngo_medicine_donation);

        NGO_ID_money = findViewById(R.id.NGO_ID_Medicine);
        NGO_Password_Verification_money=findViewById(R.id.NGO_Password_Medicine_Verification);
        vrification_money_btn=findViewById(R.id.vrification_Medicine_btn);

        vrification_money_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String veri_Id = NGO_ID_money.getText().toString();
                String veri_pass = NGO_Password_Verification_money.getText().toString();

                if (veri_Id.equals("Medicine")&&veri_pass.equals("123456")){
                    Toast.makeText(Verification_NGO_Medicine_Donation.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Verification_NGO_Medicine_Donation.this,NGO_Medicine_Donation.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Verification_NGO_Medicine_Donation.this, "Please Enter Your ID", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}