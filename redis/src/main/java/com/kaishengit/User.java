package com.kaishengit;

public class User {

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }


    public User(String name,String address) {
       this.name = name;
       this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
