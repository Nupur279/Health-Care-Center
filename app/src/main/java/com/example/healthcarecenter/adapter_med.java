package com.example.healthcarecenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter_med extends FirebaseRecyclerAdapter<medicine_model, adapter_med.myViewHolder> {
    TextView med1_expiry_date_dia,med1_quantity,get_med2_expiry_date,get_med2_quantity,get_med3_expiry_date,get_med3_quantity,get_med4_expiry_date,get_med4_quantity;
    TextView get_med5_expiry_date,get_med5_quantity,get_med6_expiry_date,get_med6_quantity,med7_name,med7_expiry_date,med7_quantity;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    public adapter_med(@NonNull FirebaseRecyclerOptions<medicine_model> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull medicine_model model) {
        holder.user_name.setText(model.getMed_donar_name1());
        holder.email.setText(model.getMed_email());
        holder.user_city.setText(model.getMed_donar_city());

        String s_med1_expiry_date=model.getMed_crosin_exdate();
        String s_med1_quantity=model.getMed_crosin_no();
        String s_med2_expiry_date=model.getMed_paracetamol_exdate();
        String s_med2_quantity=model.getMed_paracetamol_no();
        String s_med3_expiry_date=model.getMed_vicks_exdate();
        String s_med3_quantity=model.getMed_vicks_no();
        String s_med4_expiry_date=model.getMed_bcomplex_exdate();
        String s_med4_quantity=model.getMed_bcomplex_no();
        String s_med5_expiry_date=model.getMed_vitafol_exdate();
        String s_med5_quantity=model.getMed_vitafol_no();
        String s_med6_expiry_date=model.getMed_omee_exdate();
        String s_med6_quantity=model.getMed_omee_no();
        String s_med7_name=model.getMed_other_name();
        String s_med7_expiry_date=model.getMed_other_exdate();
        String s_med7_quantity=model.getMed_other_no();


        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        databaseReference =firebaseDatabase.getReference("Health Care Center");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileEmail4=snapshot.child("user_email").getValue(String.class);
//                holder.mail.setText(profileEmail4);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.user_name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1300)
                        .create();

                View myview=dialogPlus.getHolderView();
                med1_expiry_date_dia=myview.findViewById(R.id.med1_expiry_date_dia);
                med1_quantity= myview.findViewById(R.id.med1_quantity);
                get_med2_expiry_date=myview.findViewById(R.id.get_med2_expiry_date);
                get_med2_quantity=myview.findViewById(R.id.get_med2_quantity);
                get_med3_expiry_date=myview.findViewById(R.id.get_med3_expiry_date);
                get_med3_quantity=myview.findViewById(R.id.get_med3_quantity);
                get_med4_expiry_date=myview.findViewById(R.id.get_med4_expiry_date);
                get_med4_quantity=myview.findViewById(R.id.get_med4_quantity);
                get_med5_expiry_date=myview.findViewById(R.id.get_med5_expiry_date);
                get_med5_quantity=myview.findViewById(R.id.get_med5_quantity);
                get_med6_expiry_date=myview.findViewById(R.id.get_med6_expiry_date);
                get_med6_quantity=myview.findViewById(R.id.get_med6_quantity);
                med7_name=myview.findViewById(R.id.med7_name);
                med7_expiry_date=myview.findViewById(R.id.med7_expiry_date);
                med7_quantity=myview.findViewById(R.id.med7_quantity);



                med1_expiry_date_dia.setText(s_med1_expiry_date);
                med1_quantity.setText(s_med1_quantity);
                get_med2_expiry_date.setText(s_med2_expiry_date);
                get_med2_quantity.setText(s_med2_quantity);
                get_med3_expiry_date.setText(s_med3_expiry_date);
                get_med3_quantity.setText(s_med3_quantity);
                get_med4_expiry_date.setText(s_med4_expiry_date);
                get_med4_quantity.setText(s_med4_quantity);
                get_med5_expiry_date.setText(s_med5_expiry_date);
                get_med5_quantity.setText(s_med5_quantity);
                get_med6_expiry_date.setText(s_med6_expiry_date);
                get_med6_quantity.setText(s_med6_quantity);
                med7_name.setText(s_med7_name);
                med7_expiry_date.setText(s_med7_expiry_date);
                med7_quantity.setText(s_med7_quantity);

                dialogPlus.show();

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_medicine_donar_recycleview,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView user_name,email,user_city;

        Button view,accept;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.user_name);
            email=itemView.findViewById(R.id.email);
            user_city=itemView.findViewById(R.id.user_city);
            view=itemView.findViewById(R.id.view);
        }
    }
}