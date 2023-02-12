package com.example.healthcarecenter;

public class OrganHelperClass {
    String Oblo,Orel,Odonar_age,Odonar_name,Odonar_birth_date,Ogrnder,OmedicalHistory;

    public OrganHelperClass(String oblo, String orel, String odonar_age, String odonar_name, String odonar_birth_date, String ogrnder, String omedicalHistory) {
        Oblo = oblo;
        Orel = orel;
        Odonar_age = odonar_age;
        Odonar_name = odonar_name;
        Odonar_birth_date = odonar_birth_date;
        Ogrnder = ogrnder;
        OmedicalHistory = omedicalHistory;
    }

    public String getOblo() {
        return Oblo;
    }

    public void setOblo(String oblo) {
        Oblo = oblo;
    }

    public String getOrel() {
        return Orel;
    }

    public void setOrel(String orel) {
        Orel = orel;
    }

    public String getOdonar_age() {
        return Odonar_age;
    }

    public void setOdonar_age(String odonar_age) {
        Odonar_age = odonar_age;
    }

    public String getOdonar_name() {
        return Odonar_name;
    }

    public void setOdonar_name(String odonar_name) {
        Odonar_name = odonar_name;
    }

    public String getOdonar_birth_date() {
        return Odonar_birth_date;
    }

    public void setOdonar_birth_date(String odonar_birth_date) {
        Odonar_birth_date = odonar_birth_date;
    }

    public String getOgrnder() {
        return Ogrnder;
    }

    public void setOgrnder(String ogrnder) {
        Ogrnder = ogrnder;
    }

    public String getOmedicalHistory() {
        return OmedicalHistory;
    }

    public void setOmedicalHistory(String omedicalHistory) {
        OmedicalHistory = omedicalHistory;
    }
}