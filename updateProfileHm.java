package com.example.user.juneyipuser.HandyMan;

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

public class updateProfileHm extends BaseActivity {

    private static final String TAG = "updateHMprofile";
    private static final String REQUIRED = "Required";
    // [START declare_database_ref]
    private DatabaseReference mRootDB;

    // input
    private EditText mName;
    private EditText mEmail;
    private EditText mSpecialization;
    private Button mUpdate;
    private HmUser hmUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_handyman);
        // set screen input to data
        mName = (EditText) findViewById(R.id.reg_fullname);
        mEmail = (EditText) findViewById(R.id.reg_email);
        mSpecialization = (EditText) findViewById(R.id.reg_specialization);
        mUpdate = (Button) findViewById(R.id.btnUpdate);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfileHm();
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
        mRootDB.child("hm").child(userId).child("details").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        hmUser = dataSnapshot.getValue(HmUser.class);

                        // [START_EXCLUDE]
                        if (hmUser == null) {
                            // User is null, error out
                            Log.v(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(updateProfileHm.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // set text fields
                            mName.setText(hmUser.getUsername());
                            mEmail.setText(hmUser.getEmail());
                            mSpecialization.setText(hmUser.getSpesialization());

                        }
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.v(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }




    private void updateProfileHm() {
        hmUser.setUsername(mName.getText().toString());
        hmUser.setEmail(mEmail.getText().toString());
        hmUser.setSpesialization(mSpecialization.getText().toString());

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

        if (TextUtils.isEmpty(mSpecialization.getText().toString())) {
            mSpecialization.setError(REQUIRED);
            return;
        }


        Toast.makeText(this, "Updating...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mRootDB.child("hm").child(userId).child("details").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        //User temp = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (hmUser == null) {
                            // User is null, error out
                            Log.v(TAG, "hm user " + userId + " is unexpectedly null");
                            Toast.makeText(updateProfileHm.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // update user details
                            mRootDB.child("hm").child(hmUser.getuID()).child("details").setValue(hmUser);                        }

                        startActivity(new Intent(updateProfileHm.this , MainActivityHandyman.class));
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.v(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
}
