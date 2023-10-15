package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NGO_Money_Donation extends AppCompatActivity {
    TextView Money_Donor_List;
    RecyclerView Review_Money;
    money_adapter money_adapter;
    FirebaseAuth firebaseAuth;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_money_donation);
        Money_Donor_List = findViewById(R.id.Money_Donor_List);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NGO_Money_Donation.this, Money_Form.class);
                startActivity(i);
            }
        });

        Review_Money = findViewById(R.id.Review_Money);
        firebaseAuth = FirebaseAuth.getInstance();
        Review_Money.setLayoutManager(new LinearLayoutManager(NGO_Money_Donation.this));

        FirebaseRecyclerOptions<money_model> options = new FirebaseRecyclerOptions.Builder<money_model>().setQuery(FirebaseDatabase.getInstance().getReference().child("Money Donor Information"), money_model.class).build();

        money_adapter = new money_adapter(options);
        Review_Money.setAdapter(money_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        money_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        money_adapter.stopListening();
    }
}