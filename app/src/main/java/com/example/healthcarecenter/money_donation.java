package com.example.healthcarecenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContentInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class money_donation extends AppCompatActivity {
    TextView title, money_donor_gender;
    EditText money_donor_name, money_email, money_donor_number, money_upi_id, money_amount;
    RadioGroup money_rg1;
    Button money_submit, make_payment;
    ImageView gpay;
    String mon_donar_name, mon_email, mon_donor_number, mon_message, mon_amount;
    final int UPI_PAYMENT = 0;
    RadioButton male,female;

    FirebaseDatabase rootNode;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_donation);

        title = findViewById(R.id.title);
        make_payment = findViewById(R.id.make_payment);
        money_donor_name = findViewById(R.id.money_donor_name);
        money_email = findViewById(R.id.money_email);
        money_donor_number = findViewById(R.id.money_donor_number);
        money_donor_gender = findViewById(R.id.money_donor_gender);
        money_rg1 = findViewById(R.id.money_rg1);
        money_upi_id = findViewById(R.id.money_upi_id);
        money_amount = findViewById(R.id.money_amount);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
//        money_payment = findViewById(R.id.money_payment);
        money_submit = findViewById(R.id.money_submit);
        gpay = findViewById(R.id.gpay);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Money Donor Information");

        make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String donorName = money_donor_name.getText().toString();
                String donorEmail = money_email.getText().toString();
                String donorNumber = money_donor_number.getText().toString();
                String UPI = money_upi_id.getText().toString();
                String amount = money_amount.getText().toString();
                payUsingUPI(donorName, donorEmail, UPI);
            }
        });

        money_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(money_donor_name.getText().toString())) {
                    money_donor_name.setError("Enter donor's name");

                    Toast.makeText(money_donation.this, "Enter donor's name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(money_email.getText().toString())) {
                        money_email.setError("Enter Email ID");
                        Toast.makeText(money_donation.this, "Enter Email ID", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(money_donor_number.getText().toString())) {
                        money_donor_number.setError("Enter Valid Number");
                        Toast.makeText(money_donation.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(money_amount.getText().toString())){
                                money_amount.setError("Enter Donation Amount");
                                Toast.makeText(money_donation.this, "Enter Donation Amount", Toast.LENGTH_SHORT).show();
                            }else {

                                if(male.isSelected()||female.isSelected()){
                                    DialogClass dialogClass = new DialogClass();
                                    dialogClass.show(getSupportFragmentManager(), "Dialog Class");

                                }else {
                                    Toast.makeText(money_donation.this, "Select Gender", Toast.LENGTH_SHORT).show();
                                }
                            }
//                        DialogClass dialogClass = new DialogClass();
//                        dialogClass.show(getSupportFragmentManager(), "Dialog Class");
                    }


            });
        }

        void payUsingUPI (String donorName, String donorEmail, String UPI)  //String amount
                    {
                        Uri uri = Uri.parse("upi://pay").buildUpon()
                                .appendQueryParameter("pa", UPI) //UPI ID nupurdiwate279@okaxis
                                .appendQueryParameter("pn", donorName) //Name
                                .appendQueryParameter("tn", "Money Donation for our health care center")
//                                .appendQueryParameter("am", amount) //Amount
                                .appendQueryParameter("cu", "INR") //currency type
                                .build();
                        Intent upiIntent = new Intent(Intent.ACTION_VIEW);
                        upiIntent.setData(uri);
                        Intent chooser = Intent.createChooser(upiIntent, "Pay with");

                        if (null != chooser.resolveActivity(getPackageManager())) {
                            startActivityForResult(chooser, UPI_PAYMENT);
                        } else {
                            Toast.makeText(this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    protected void onActivityResult ( int requestCode, int resultCode,
                    @Nullable Intent data){
                        super.onActivityResult(requestCode, resultCode, data);

                        switch (requestCode) {
                            case UPI_PAYMENT:
                                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                                    if (data != null) {
                                        String value = data.getStringExtra("response");
                                        Log.d("UPI", "onActivityResult: " + value);
                                        ArrayList<String> datalist = new ArrayList<>();
                                        datalist.add(value);
                                        upiPaymentDataOperation(datalist);
                                    } else {
                                        Log.d("UPI", "onActivityResult: " + "Return activity is null");
                                        ArrayList<String> datalist = new ArrayList<>();
                                        datalist.add("nothing");
                                        upiPaymentDataOperation(datalist);
                                    }
                                } else {
                                    Log.d("UPI", "onActivityResult: " + "Return activity is null");
                                    ArrayList<String> datalist = new ArrayList<>();
                                    datalist.add("nothing");
                                    upiPaymentDataOperation(datalist);
                                }
                                break;
                        }
                    }
                    private void upiPaymentDataOperation (ArrayList < String > data) {
                        if (isConnectionAvailable(money_donation.this)) {
                            String str = data.get(0);
                            Log.d("UPIPAY", "upiPaymentDataOperation: " + str);
                            String paymentCancel = "";
                            if (str == null) {
                                str = "Discard";
                            }
                            String status = "";
                            String approvalRefNo = "";
                            String response[] = str.split("&");
                            for (int i = 0; i < response.length; i++) {
                                String equalStr[] = response[i].split("=");
                                if (equalStr.length >= 2) {
                                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                                        status = equalStr[1].toLowerCase();
                                    } else if ((equalStr[0].toLowerCase().equals("ApprovalRefNo.".toLowerCase())) || (equalStr[0].toLowerCase().equals("txnRef".toLowerCase()))) {
                                        approvalRefNo = equalStr[1];
                                    }
                                } else {
                                    paymentCancel = "Payment cancelled by user";
                                }
                            }
                            if (status.equals("success")) {
                                Toast.makeText(money_donation.this, "Transaction successfull", Toast.LENGTH_SHORT).show();
                                Log.d("UPI", "responseStr: " + approvalRefNo);
                            } else if ("Payment cancelled by user".equals(paymentCancel)) {
                                Toast.makeText(money_donation.this, "Payment cancelled by user", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(money_donation.this, "Transaction failed. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(money_donation.this, "No Internet. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public static boolean isConnectionAvailable (Context context){
                        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager != null) {
                            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                            if (netInfo != null && netInfo.isConnected()
                                    && netInfo.isConnectedOrConnecting()
                                    && netInfo.isAvailable()) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
