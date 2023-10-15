package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Organ_Form extends AppCompatActivity {


    TextView user_organ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_form);


        user_organ = findViewById(R.id.user_organ);

        user_organ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Organ_Form.this,Organ_Donation.class);
                startActivity(i);

            }
        });
    }
}