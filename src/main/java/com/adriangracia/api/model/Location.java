package com.adriangracia.api.model;

import java.util.Date;

public class Location {

    private String civicAddress;
    private String geoAddress;
    private Date timestamp;

    public Location(String civicAddress, String geoAddress, Date timestamp){
        this.civicAddress = civicAddress;
        this.geoAddress = geoAddress;
        this.timestamp = timestamp;
    }

    public String getCivicAddress() {
        return civicAddress;
    }

    public void setCivicAddress(String civicAddress) {
        this.civicAddress = civicAddress;
    }

    public String getGeoAddress() {
        return geoAddress;
    }

    public void setGeoAddress(String geoAddress) {
        this.geoAddress = geoAddress;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
