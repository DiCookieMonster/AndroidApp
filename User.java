package com.example.user.juneyipuser.Customer;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    private String username;
    private String email;
    private String uID;
    private String address;
    private String time;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String uID,String address, String time) {
        this.username = username;
        this.email = email;
        this.uID = uID;
        this.address = address;
        this.time = time;
    }

    public String getUsername()
    {
       return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getuID()
    {
        return uID;
    }

    public String getAddress() { return address; }

    public String getTime () { return time; }


    public void setUsername(String name)
    {
        this.username = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setuID(String id)
    {
        this.uID = id;
    }

    public void setAddress(String address) { this.address = address; }

    public void setTime (String time) { this.time = time; }


}
// [END blog_user_class]
