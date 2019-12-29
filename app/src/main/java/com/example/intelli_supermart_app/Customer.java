package com.example.intelli_supermart_app;

public class Customer {
    private String title;
    private String name;
    private String email;
    private String house;
    private String area;
    private String city;
    private String addressNickName;

    public Customer(String title, String name, String email, String house, String area, String city, String addressNickName) {
        this.title = title;
        this.name = name;
        this.email = email;
        this.house = house;
        this.area = area;
        this.city = city;
        this.addressNickName = addressNickName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressNickName() {
        return addressNickName;
    }

    public void setAddressNickName(String addressNickName) {
        this.addressNickName = addressNickName;
    }

    public String diplayCutomerData() {
        String data = addressNickName + ": \n" +
                title + name + "\n" +
                email + "\n" +
                house + ", " + area + ", " + city;
        return data;
    }
}
