package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class medicine_Request_Section extends AppCompatActivity {
    RecyclerView medicine_request_review;
    medicine_request_Adapter medicineRequestAdapter;
    FirebaseAuth firebaseAuth;
    ImageView back_donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_request_section);

        medicine_request_review = (RecyclerView) findViewById(R.id.medicine_request_review);
        medicine_request_review.setLayoutManager(new LinearLayoutManager(this));
        back_donation = findViewById(R.id.back_donation);

        back_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(medicine_Request_Section.this,NGO_Medicine_Donation.class);
                startActivity(i);
            }
        });

        firebaseAuth= FirebaseAuth.getInstance();
        medicine_request_review.setLayoutManager(new LinearLayoutManager(medicine_Request_Section.this));

        FirebaseRecyclerOptions<request_medicine_model> options =
                new FirebaseRecyclerOptions.Builder<request_medicine_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Medicine_Requests"), request_medicine_model.class)
                        .build();


        medicineRequestAdapter=new medicine_request_Adapter(options);

        medicine_request_review.setAdapter(medicineRequestAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        medicineRequestAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        medicineRequestAdapter.stopListening();
    }
}