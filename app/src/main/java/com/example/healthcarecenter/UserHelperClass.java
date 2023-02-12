package com.example.healthcarecenter;
public class UserHelperClass {
    String user_name,user_email,password,user_phone_no;

    public UserHelperClass() {
    }

    public UserHelperClass(String user_name, String user_email, String password, String user_phone_no) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_phone_no = user_phone_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_phone_no() {
        return user_phone_no;
    }

    public void setUser_phone_no(String user_phone_no) {
        this.user_phone_no = user_phone_no;
    }
}