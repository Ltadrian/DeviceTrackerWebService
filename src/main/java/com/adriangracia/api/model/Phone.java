package com.adriangracia.api.model;

import java.util.ArrayList;


public class Phone {

    private String phoneNumber;

    private String name;

    private String type;

    private String manufacturer;

    private boolean registered;

    private ArrayList<Location> locations;

    private boolean privateMode;

    public Phone(String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.name = null;
        this.type = null;
        this.manufacturer = null;
        this.registered = false;
        this.locations = new ArrayList<Location>();
        this.privateMode = false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public boolean isPrivateMode() {
        return privateMode;
    }

    public void setPrivateMode(boolean privateMode) {
        this.privateMode = privateMode;
    }

}
