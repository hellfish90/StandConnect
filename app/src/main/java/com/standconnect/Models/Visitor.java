package com.standconnect.Models;

/**
 * Created by Marc on 24/11/15.
 */
public class Visitor implements Entity{

    String name;
    int age;
    String gender;
    String city;
    int zip;
    String address;
    String email;

    public Visitor (String name, int age, String gender, String city, int zip, String address, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.zip = zip;
        this.address = address;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }
}
