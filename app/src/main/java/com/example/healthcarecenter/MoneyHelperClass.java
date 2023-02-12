package com.example.healthcarecenter;

public class MoneyHelperClass {
    String money_donor_name, money_donor_email;

    public String getMoney_donor_name() {
        return money_donor_name;
    }

    public void setMoney_donor_name(String money_donor_name) {
        this.money_donor_name = money_donor_name;
    }

    public String getMoney_donor_email() {
        return money_donor_email;
    }

    public void setMoney_donor_email(String money_donor_email) {
        this.money_donor_email = money_donor_email;
    }
//
//    public String getMoney_amount() {
//        return money_amount;
//    }
//
//    public void setMoney_amount(String money_amount) {
//        this.money_amount = money_amount;
//    }

    public MoneyHelperClass(String money_donor_name, String money_donor_email) {
        this.money_donor_name = money_donor_name;
        this.money_donor_email = money_donor_email;
//        this.money_amount = money_amount;
    }
}
