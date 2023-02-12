package com.example.healthcarecenter;

public class NGOHelperClass {
    String user_name, user_email, password, user_phone_no, NGO_Landline_Number, NGO_Compant_Type, NGO_Image_Uri;

    public NGOHelperClass() {
    }

    public NGOHelperClass(String user_name, String user_email, String password, String user_phone_no, String NGO_Landline_Number, String NGO_Compant_Type, String NGO_Image_Uri) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_phone_no = user_phone_no;
        this.NGO_Landline_Number = NGO_Landline_Number;
        this.NGO_Compant_Type = NGO_Compant_Type;
        this.NGO_Image_Uri = NGO_Image_Uri;
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

    public String getNGO_Landline_Number() {
        return NGO_Landline_Number;
    }

    public void setNGO_Landline_Number(String NGO_Landline_Number) {
        this.NGO_Landline_Number = NGO_Landline_Number;
    }

    public String getNGO_Compant_Type() {
        return NGO_Compant_Type;
    }

    public void setNGO_Compant_Type(String NGO_Compant_Type) {
        this.NGO_Compant_Type = NGO_Compant_Type;
    }

    public String getNGO_Image_Uri() {
        return NGO_Image_Uri;
    }

    public void setNGO_Image_Uri(String NGO_Image_Uri) {
        this.NGO_Image_Uri = NGO_Image_Uri;
    }
}