package com.example.healthcarecenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;


public class money_adapter extends FirebaseRecyclerAdapter<money_model, money_adapter.myViewHolder> {

    public money_adapter(@NonNull FirebaseRecyclerOptions<money_model> option) {
//        super(options);
        super(option);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull money_model model) {
        holder.mon_donar_name.setText(model.getMoney_donor_name());
        holder.mon_donor_email.setText(model.getMoney_donor_email());
//        holder.mon_amount.setText(model.getMoney_amount());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_money_donor,parent,false);
        return new myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        TextView mon_donar_name, mon_donor_email;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mon_donar_name=itemView.findViewById(R.id.money_dname);
            mon_donor_email=itemView.findViewById(R.id.money_demail);
//            mon_amount=itemView.findViewById(R.id.money_damount);

        }
    }
}