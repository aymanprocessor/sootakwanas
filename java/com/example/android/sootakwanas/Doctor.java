package com.example.android.sootakwanas;



public class Doctor {
    public String Name;
    public String Number;
    public String Government;
    public String City;
    public String lat;
    public String lon;
    public String label;
    public String Address;

    public Doctor(String name, String number, String government, String city, String lat, String lon, String label, String address) {
        Name = name;
        Number = number;
        Government = government;
        City = city;
        this.lat = lat;
        this.lon = lon;
        this.label = label;
        Address = address;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}