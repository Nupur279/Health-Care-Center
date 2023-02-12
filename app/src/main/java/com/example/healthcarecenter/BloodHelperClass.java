package com.example.healthcarecenter;

public class BloodHelperClass {
   String blood_type,donar_no, relation_with_donor, donar_age, donar_name, donar_birth_date, Bgender, BmedicalHistory;

   public BloodHelperClass(String blood_type, String donar_no, String relation_with_donor, String donar_age, String donar_name, String donar_birth_date, String bgender, String bmedicalHistory) {
      this.blood_type = blood_type;
      this.donar_no = donar_no;
      this.relation_with_donor = relation_with_donor;
      this.donar_age = donar_age;
      this.donar_name = donar_name;
      this.donar_birth_date = donar_birth_date;
      Bgender = bgender;
      BmedicalHistory = bmedicalHistory;
   }

   public String getBlood_type() {
      return blood_type;
   }

   public void setBlood_type(String blood_type) {
      this.blood_type = blood_type;
   }

   public String getDonar_no() {
      return donar_no;
   }

   public void setDonar_no(String donar_no) {
      this.donar_no = donar_no;
   }

   public String getRelation_with_donor() {
      return relation_with_donor;
   }

   public void setRelation_with_donor(String relation_with_donor) {
      this.relation_with_donor = relation_with_donor;
   }

   public String getDonar_age() {
      return donar_age;
   }

   public void setDonar_age(String donar_age) {
      this.donar_age = donar_age;
   }

   public String getDonar_name() {
      return donar_name;
   }

   public void setDonar_name(String donar_name) {
      this.donar_name = donar_name;
   }

   public String getDonar_birth_date() {
      return donar_birth_date;
   }

   public void setDonar_birth_date(String donar_birth_date) {
      this.donar_birth_date = donar_birth_date;
   }

   public String getBgender() {
      return Bgender;
   }

   public void setBgender(String bgender) {
      Bgender = bgender;
   }

   public String getBmedicalHistory() {
      return BmedicalHistory;
   }

   public void setBmedicalHistory(String bmedicalHistory) {
      BmedicalHistory = bmedicalHistory;
   }
}
