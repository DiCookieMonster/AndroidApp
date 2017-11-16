package com.example.user.juneyipuser.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.juneyipuser.BaseActivity;
import com.example.user.juneyipuser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by User on 11/3/2016.
 */

public class updateProfile extends BaseActivity {

    private static final String TAG = "NewOrderActivity";
    private static final String REQUIRED = "Required";
    // [START declare_database_ref]
    private DatabaseReference mRootDB;

    // input
    private EditText mName;
    private EditText mEmail;
    private EditText mAddress;
    private EditText mTime;
    private Button mUpdate;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_user);
        // set screen input to data
        mName = (EditText) findViewById(R.id.reg_fullname);
        mEmail = (EditText) findViewById(R.id.reg_email);
        mAddress = (EditText) findViewById(R.id.reg_address);
        mTime = (EditText) findViewById(R.id.reg_hours);
        mUpdate = (Button) findViewById(R.id.btnUpdate);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
        // [START initialize_database_ref]
        mRootDB = FirebaseDatabase.getInstance().getReference();
        loadProfile();
    }

    private void loadProfile(){
        Toast.makeText(this, "Loading Profile...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mRootDB.child("users").child(userId).child("details").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                          user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.v(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(updateProfile.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // set text fields
                            mName.setText(user.getUsername());
                            mEmail.setText(user.getEmail());
                            mAddress.setText(user.getAddress());
                            mTime.setText(user.getTime());

                        }
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.v(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }




    private void updateProfile() {
        user.setUsername(mName.getText().toString());
        user.setEmail(mEmail.getText().toString());
        user.setAddress(mAddress.getText().toString());
        user.setTime(mTime.getText().toString());

        // Name is required
        if (TextUtils.isEmpty(mName.getText().toString())) {
            mName.setError(REQUIRED);
            return;
        }

        // Email is required
        if (TextUtils.isEmpty(mEmail.getText().toString())) {
            mEmail.setError(REQUIRED);
            return;
        }

        // address is required
        if (TextUtils.isEmpty(mAddress.getText().toString())) {
            mAddress.setError(REQUIRED);
            return;
        }

        // time is required
        if (TextUtils.isEmpty(mTime.getText().toString())) {
            mTime.setError(REQUIRED);
            return;
        }


        Toast.makeText(this, "Updating...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mRootDB.child("users").child(userId).child("details").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                       //User temp = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.v(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(updateProfile.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // update user details
                            mRootDB.child("users").child(user.getuID()).child("details").setValue(user);                        }

                        startActivity(new Intent(updateProfile.this , MainActivityCustomer.class));
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.v(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
}
