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

public class blood_adapter extends FirebaseRecyclerAdapter<blood_model, blood_adapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public blood_adapter(@NonNull FirebaseRecyclerOptions<blood_model> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull blood_model model) {
        holder.donar_name.setText(model.getDonar_name());
        holder.donar_blood_type.setText(model.getBlood_type());
        holder.donar_blood_gender.setText(model.getBgender());
        holder.donar_blood_phone.setText(model.getDonar_no());





    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_donar_single_list,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView donar_name,donar_blood_type,donar_blood_gender,donar_blood_phone;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            donar_name=itemView.findViewById(R.id.donar_name);
            donar_blood_type=itemView.findViewById(R.id.donar_blood_type);
            donar_blood_gender=itemView.findViewById(R.id.donar_blood_gender);
            donar_blood_phone=itemView.findViewById(R.id.donar_blood_phone);

        }
    }
}
