package com.example.android.sootakwanas;

public class Places {

    public String Name;
    public String government;
    public String City;

    public Places(String name, String government, String city) {
       this.Name = name;
        this.government = government;
       this.City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
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
                "name='" + Name + '\'' +
                ", government='" + government + '\'' +
                ", city='" + City + '\'' + +
                '}';
    }
}
