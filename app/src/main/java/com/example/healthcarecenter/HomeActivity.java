package com.example.healthcarecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthcarecenter.fragment.Home_Fragment;
import com.example.healthcarecenter.fragment.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    private Toolbar tool_bar;
    private NavigationView drawer_nav;
    private CircleImageView nav_profileImg;
    private TextView nav_Full_name, nav_email;
    private DatabaseReference userRef;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    ActionBarDrawerToggle toggle;
    String profilename;
    String profileEmail2;
    String profile_no;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();

        bottomNavigationView = findViewById(R.id.nav_bottom);
        drawer = findViewById(R.id.drawer);
        tool_bar = findViewById(R.id.tool_bar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setTitle("Heath Care Center");
        drawer_nav = findViewById(R.id.drawer_nav);
        firebaseDatabase = FirebaseDatabase.getInstance();
        showAllData();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this, drawer, tool_bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        drawer_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.logout:
                        logout();
                        break;
                }

                return false;
            }
        });

        nav_profileImg = drawer_nav.getHeaderView(0).findViewById(R.id.profile_img);
        nav_Full_name = drawer_nav.getHeaderView(0).findViewById(R.id.user_peofile_name);
        nav_email = drawer_nav.getHeaderView(0).findViewById(R.id.user_peofile_email);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new Home_Fragment());
        fragmentTransaction.commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.home:
                        fragmentTransaction.replace(R.id.frame, new Home_Fragment());
                        break;
//                    case R.id.foryou:
//                        fragmentTransaction.replace(R.id.frame, new for_you_Fragment());
//                        break;
                    case R.id.profile:
                        fragmentTransaction.replace(R.id.frame, new Profile_Fragment());
                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });


    }

    private void showAllData() {
        databaseReference = firebaseDatabase.getReference("Health Care Center");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profilename = snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("user_name").getValue(String.class);
                profileEmail2 = snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("user_email").getValue(String.class);
                imageUri = snapshot.child(firebaseAuth.getCurrentUser().getUid()).child("Profile_Picture_URI").getValue(String.class);
                nav_Full_name.setText(profilename);

                nav_email.setText(profileEmail2);
                Glide.with(getApplicationContext()).load(imageUri).into(nav_profileImg);

//                Toast.makeText(getContext(), profilename+""+profileEmail, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "User Does Not Exists", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();

    }


}