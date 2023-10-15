package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Money_Form extends AppCompatActivity {

    TextView user_Money,Money_NGO, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_form);

        user_Money = findViewById(R.id.user_Money);
        Money_NGO = findViewById(R.id.Money_NGO);
        textView3 = findViewById(R.id.textView3);

        textView3.animate()
                .translationY(-250)
                .setStartDelay(500)
                .setDuration(1000)
                .start();

        user_Money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Money_Form.this, money_donation.class);
                startActivity(intent);
            }
        });

        Money_NGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Money_Form.this, verification_NGO_Money_Donation.class);
                startActivity(intent);
            }
        });
    }

}