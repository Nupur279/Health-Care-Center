package com.example.healthcarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NGO_Medicine_Donation extends AppCompatActivity {
    RecyclerView medicine_Recview;
    adapter_med Madapter;
    FirebaseAuth firebaseAuth;
    Button request_med_btn;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_medicine_donation);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NGO_Medicine_Donation.this, Medicine_Form.class);
                startActivity(i);
            }
        });

        medicine_Recview = (RecyclerView) findViewById(R.id.Review_Medicine);
        medicine_Recview.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth= FirebaseAuth.getInstance();
        medicine_Recview.setLayoutManager(new LinearLayoutManager(NGO_Medicine_Donation.this));

        request_med_btn=findViewById(R.id.request_med_btn);

        request_med_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(NGO_Medicine_Donation.this,medicine_Request_Section.class);
                startActivity(i);
            }
        });

        FirebaseRecyclerOptions<medicine_model> options =
                new FirebaseRecyclerOptions.Builder<medicine_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Medicine Donation"), medicine_model.class)
                        .build();


        Madapter=new adapter_med(options);

        medicine_Recview.setAdapter(Madapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Madapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Madapter.stopListening();
    }
}