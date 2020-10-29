package com.example.covidfinder;

public class ClassUserInfo {

    public ClassUserInfo(String name, String email, String password, String covid_check, Double lat, Double lng)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.covid_check = covid_check;
        this.lat = lat;
        this.lng = lng;
    }

    public  String name, email,password,covid_check;
    public Double lat,lng;

}
