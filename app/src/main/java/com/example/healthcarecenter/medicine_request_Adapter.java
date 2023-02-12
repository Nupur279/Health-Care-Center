package com.example.healthcarecenter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class medicine_request_Adapter extends FirebaseRecyclerAdapter<request_medicine_model, medicine_request_Adapter.myViewHolder3> {

    TextView req_med1_quantity,req_med2_quantity,req_med3_quantity,req_med4_quantity,req_med5_quantity,req_med6_quantity,req_med7,req_med7_quantity;

    public medicine_request_Adapter(@NonNull FirebaseRecyclerOptions<request_medicine_model> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder3 holder, int position, @NonNull request_medicine_model model) {

        holder.req_user_name.setText(model.getReuestUser_name());
        holder.req_user_address.setText(model.getReuest_address());
        holder.req_user_email.setText(model.getRequest_email());


        String rq_crocin_count1=model.getRq_crocin_count();
        String rq_paracetamol_count1 =model.getRq_paracetamol_count();
        String rq_vicks_action_count1 =model.getRq_vicks_action_count();
        String rq_bcomplex_count1 =model.getRq_bcomplex_count();
        String rq_vitafol_count1 =model.getRq_vitafol_count();
        String rq_omee_count1 =model.getRq_omee_count();
        String req_other_medicine1 =model.getReq_other_medicine();
        String req_other_medicine_count1=model.getReq_other_medicine_count();

        holder.req_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.req_user_name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.request_list_dailog))
                        .setExpanded(true,1300)
                        .create();

                View myview=dialogPlus.getHolderView();
                req_med1_quantity=myview.findViewById(R.id.req_med1_quantity);
                req_med2_quantity=myview.findViewById(R.id.req_med2_quantity);
                req_med3_quantity=myview.findViewById(R.id.req_med3_quantity);
                req_med4_quantity=myview.findViewById(R.id.req_med4_quantity);
                req_med5_quantity=myview.findViewById(R.id.req_med5_quantity);
                req_med6_quantity=myview.findViewById(R.id.req_med6_quantity);
                req_med7_quantity=myview.findViewById(R.id.req_med7_quantity);
                req_med7=myview.findViewById(R.id.req_med7);

                req_med1_quantity.setText(rq_crocin_count1);
                req_med2_quantity.setText(rq_paracetamol_count1);
                req_med3_quantity.setText(rq_vicks_action_count1);
                req_med4_quantity.setText(rq_bcomplex_count1);
                req_med5_quantity.setText(rq_vitafol_count1);
                req_med6_quantity.setText(rq_omee_count1);
                req_med7_quantity.setText(req_other_medicine_count1);
                req_med7.setText(req_other_medicine1);

                dialogPlus.show();

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_request_list_recycle,parent,false);
        return new myViewHolder3(view);
    }

    class myViewHolder3 extends RecyclerView.ViewHolder{

        TextView req_user_name,req_user_address,req_user_email;
        Button req_view;
        public myViewHolder3(@NonNull View itemView) {
            super(itemView);

            req_user_name=itemView.findViewById(R.id.req_user_name);
            req_user_address=itemView.findViewById(R.id.req_user_address);
            req_user_email=itemView.findViewById(R.id.req_user_email);
            req_view=itemView.findViewById(R.id.req_view);
        }
    }
}