package com.example.healthcarecenter.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcarecenter.R;
import com.example.healthcarecenter.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile_Fragment extends Fragment {

    CircleImageView profile_pic;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    public Uri imageUri;
    TextView Full_Name,profile_email;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText profile_Name,profile_Email,Phone_no,profile_pass;
    FirebaseAuth firebaseAuth;
    View view;
    DatabaseReference ref;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_pic=view.findViewById(R.id.profile_pic);
        Full_Name=view.findViewById(R.id.Full_Name);
        profile_email=view.findViewById(R.id.profile_email);
        profile_Name=view.findViewById(R.id.profile_Name);
        Phone_no=view.findViewById(R.id.Phone_no);
        profile_Email=view.findViewById(R.id.profile_Email);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("Health Care Center");
        firebaseStorage=FirebaseStorage.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        storageReference=firebaseStorage.getReference();
        ref=rootNode.getReference("Health Care Center");
        showAllData();


        firebaseStorage= FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        firebaseAuth=FirebaseAuth.getInstance();

        StorageReference profileRef= storageReference.child("User Data"+"/"+firebaseAuth.getCurrentUser().getUid()+"/User_Profile_Image/User_profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profile_pic);
            }
        });


        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();

            }
        });

        return view;
    }

    private void showAllData() {
        databaseReference =firebaseDatabase.getReference("Health Care Center");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profilename = snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("user_name").getValue(String.class);
                String profileEmail2 =snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("user_email").getValue(String.class);
                String profile_no =snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("user_phone_no").getValue(String.class);
                Full_Name.setText(profilename);
                profile_Name.setText(profilename);
                profile_email.setText(profileEmail2);
                Phone_no.setText(profile_no);
                profile_Email.setText(profileEmail2);

//                Toast.makeText(getContext(), profilename+""+profileEmail, Toast.LENGTH_SHORT).show();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "User Does Not Exists", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void choosePicture() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri=data.getData();
            profile_pic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pb=new ProgressDialog(getContext());
        pb.setTitle("Uploading Image...");
        pb.show();

//        final String randomKey= UUID.randomUUID().toString();
        StorageReference riverRef= storageReference.child("User Data"+"/"+firebaseAuth.getCurrentUser().getUid()+"/User_Profile_Image/User_profile.jpg");

        riverRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pb.dismiss();
                Snackbar.make(view, "Image Uploaded" , Snackbar.LENGTH_LONG).show();
                riverRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profile_pic);
                    }
                });
                if (taskSnapshot.getMetadata()!=null && taskSnapshot.getMetadata().getReference()!=null){
                    Task<Uri> result=taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUri=uri.toString();
                            Map newImageMap=new HashMap();
                            newImageMap.put("Profile_Picture_URI",imageUri);
                            ref.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getContext(), "Image Uri Added", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pb.dismiss();

                Toast.makeText(getContext(),"Failed to Upload", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercentage=(100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pb.setMessage("Percentage: "+ (int) progressPercentage+"%");
            }
        });
    }

}