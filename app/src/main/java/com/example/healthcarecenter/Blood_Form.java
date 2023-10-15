package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Blood_Form extends AppCompatActivity {

    TextView textView3, user_blood ,NGO ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_form);

        user_blood = findViewById(R.id.user_blood);
        NGO = findViewById(R.id.NGO);
        textView3 = findViewById(R.id.textView3);


        textView3.animate()
                .translationY(-250)
                .setStartDelay(500)
                .setDuration(1000)
                .start();
        user_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Blood_Form.this ,blood_donation_donar.class);
                startActivity(intent);
            }
        });

        NGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Blood_Form.this ,Verification_NGO_Blood_Donation.class);
                startActivity(intent);
            }
        });

    }


}