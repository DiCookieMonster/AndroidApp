package com.example.user.juneyipuser.HandyMan;

/**
 * Created by User on 11/3/2016.
 */

public class HmUser {

    private String username;
    private String email;
    private String uID;
    private String address;
    private String spesialization;

    public HmUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public HmUser(String username, String email,String uID,String address, String spesialization) {
        this.username = username;
        this.email = email;
        this.uID = uID;
        this.address = address;
        this.spesialization = spesialization;
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

    public String getAddress()
    {
        return address;
    }

    public String getSpesialization()
    {
        return spesialization;
    }


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

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setSpesialization(String spesialization)
    {
        this.spesialization = spesialization;
    }




}
// [END blog_user_class]




