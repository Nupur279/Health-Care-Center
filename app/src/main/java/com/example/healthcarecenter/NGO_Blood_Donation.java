package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NGO_Blood_Donation extends AppCompatActivity {

    RecyclerView Review;
    blood_adapter blood_adapter;
    FirebaseAuth firebaseAuth;
    ImageView back;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_blood_donation);
        Review=findViewById(R.id.Review);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NGO_Blood_Donation.this, Blood_Form.class);
                startActivity(i);
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        Review.setLayoutManager(new LinearLayoutManager(NGO_Blood_Donation.this));

        FirebaseRecyclerOptions<blood_model> options =
                new FirebaseRecyclerOptions.Builder<blood_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(" Blood Donor Information"), blood_model.class)
                        .build();

        blood_adapter=new blood_adapter(options);
        Review.setAdapter(blood_adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        blood_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        blood_adapter.stopListening();
    }


}