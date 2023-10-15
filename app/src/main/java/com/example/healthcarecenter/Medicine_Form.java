package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Medicine_Form extends AppCompatActivity {
    TextView textView3;
    Button user_med, request_med, med_NGO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_form);
        user_med = findViewById(R.id.user_med);
        request_med = findViewById(R.id.request_med);
        med_NGO = findViewById(R.id.med_NGO);
        textView3 = findViewById(R.id.textView3);

//        user_med.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Medicine_Form.this, Medicine_donation.class);
//                startActivity(intent);
//            }
//        });

        request_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Medicine_Form.this, Medicine_request.class);
                startActivity(intent);
            }
        });

        med_NGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Medicine_Form.this,Verification_NGO_Medicine_Donation.class);
                startActivity(intent);

            }
        });
        textView3.animate()
                .translationY(-250)
                .setStartDelay(500)
                .setDuration(1000)
                .start();


//        user_Medicine = findViewById(R.id.user_Medicine);
//        Medicine_NGO= findViewById(R.id.Medicine_NGO);

        user_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Medicine_Form.this, Medicine_donation.class);
                startActivity(intent);
            }
        });
    }
}