package com.example.android.sootakwanas;



public class Doctor {
    public String Name;
    public String Number;
    public String Government;
    public String City;

    public Doctor( String name, String number, String government, String city) {

        this.Name = name;
        this.Number = number;
        this.Government = government;
        this.City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getGovernment() {
        return Government;
    }

    public void setGovernment(String government) {
        Government = government;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", Government='" + Government + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
    }