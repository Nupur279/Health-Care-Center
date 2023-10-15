package com.example.healthcarecenter.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthcarecenter.About_App;
import com.example.healthcarecenter.Blood_Form;
import com.example.healthcarecenter.Medicine_Form;
import com.example.healthcarecenter.Money_Form;
import com.example.healthcarecenter.R;
import com.google.android.material.card.MaterialCardView;

public class Home_Fragment extends Fragment {


    public Home_Fragment() {


    }

    MaterialCardView blood_Donation ,Reminder ,About_us ,Money_Donation,equipment_donation,Medicine_Donation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        blood_Donation = view.findViewById(R.id.Blood_Donation);
//        Reminder = view.findViewById(R.id.Reminder);
        About_us = view.findViewById(R.id.About_us);
        Medicine_Donation=view.findViewById(R.id.Medicine_Donation);
        Money_Donation = view.findViewById(R.id.Money_Donation);

        Medicine_Donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Medicine_Form.class);
                startActivity(intent);

            }
        });
        Money_Donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Money_Form.class);
                startActivity(intent);

            }
        });

        blood_Donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Blood_Form.class);
                startActivity(intent);
            }
        });

        About_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), About_App.class);
                startActivity(intent);
            }
        });




        return view;
    }
}