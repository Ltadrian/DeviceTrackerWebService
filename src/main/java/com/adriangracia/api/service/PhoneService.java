package com.adriangracia.api.service;

import com.adriangracia.api.model.Location;
import com.adriangracia.api.model.Phone;

import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * PhoneService layer stores and updates devices and locations
 */
@Service
public class PhoneService {

    private HashMap<String, Phone> deviceTrackerList = new HashMap<String, Phone>();
    private GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("AIzaSyBHrjcu5OqJT7bSdEGhxh1hn67S57nzccg")
            .build();


    public String registerPhone(String phoneNumber, String name, String phoneType, String manufacturer) throws Exception {
        if(!deviceTrackerList.containsKey(phoneNumber)){
            Phone registerPhone = new Phone(phoneNumber);
            registerPhone.setName(name);
            registerPhone.setType(phoneType);

            if(phoneType.toLowerCase().equals("android")){
                registerPhone.setManufacturer(manufacturer);
            }

            registerPhone.setRegistered(true);
            deviceTrackerList.put(phoneNumber, registerPhone);
            return "Device registered successfully.";
        }
        else {
            throw new Exception("Device already registered.");
        }

    }

    public String unregisterPhone(String phoneNumber) throws Exception {

        if(deviceTrackerList.containsKey(phoneNumber)){
            deviceTrackerList.get(phoneNumber).setRegistered(false);
            return "Device unregistered successfully.";
        }
        else {
            throw new Exception("Device not found.");
        }
    }

    /**
     * Geocode civic address to find geo address
     *
     */
    public String updateLocation(String phoneNumber, String city, String state, String street, String zipCode)
            throws Exception {
        if(deviceTrackerList.containsKey(phoneNumber)) {
            if(deviceTrackerList.get(phoneNumber).isRegistered()) {
                if (!deviceTrackerList.get(phoneNumber).isPrivateMode()) {
                    String civicAddress = city + " " + state + " " + street + " " + zipCode;
                    GeocodingResult[] results = GeocodingApi.geocode(context, civicAddress).await();
                    Date timestamp = new Date();
                    String geoAddress = (results.length > 0) ? results[0].geometry.location.lat + "," + results[0].geometry.location.lng : null;
                    deviceTrackerList.get(phoneNumber).getLocations().add(new Location(civicAddress, geoAddress, timestamp));
                    return "Update location successful.";
                }
                return "Private Mode enabled. Location not updated.";
            }
            else{
                throw new Exception("Device not registered.");
            }
        }
        else{
            throw new Exception("Device not found.");
        }
    }

    /**
     * Reverse Geocode geo address to find civic address
     *
     */
    public String updateLocation(String phoneNumber, double latitude, double longitude)
            throws Exception, InterruptedException, ApiException, IOException {

        if(deviceTrackerList.containsKey(phoneNumber) ) {
            if (deviceTrackerList.get(phoneNumber).isRegistered()) {
                String geoAddress = latitude + "," + longitude;
                Date timestamp = new Date();
                GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(latitude, longitude)).await();
                String civicAddress = (results.length > 0) ? results[0].formattedAddress : null;
                deviceTrackerList.get(phoneNumber).getLocations().add(new Location(civicAddress, geoAddress, timestamp));
                return "Update location successful.";
            }
            else{
                throw new Exception("Device not registered.");
            }
        }
        else{
            throw new Exception("Device not found.");
        }
    }

    public String getLastLocation(String phoneNumber) throws Exception {

        if(deviceTrackerList.containsKey(phoneNumber)){
            if(deviceTrackerList.get(phoneNumber).isRegistered()) {
                ArrayList<Location> listOfLocations = deviceTrackerList.get(phoneNumber).getLocations();
                return new Gson().toJson(listOfLocations.get(listOfLocations.size() - 1));
            }
            else{
                throw new Exception("Device not registered.");
            }
        }
        else {
            throw new Exception("Device not found.");
        }
    }

    public String getLastLocations(String phoneNumber, Integer numberOfLocations) throws Exception {

        if(deviceTrackerList.containsKey(phoneNumber)) {
            if(deviceTrackerList.get(phoneNumber).isRegistered()) {
                ArrayList<Location> listOfLocations = new ArrayList<Location>();
                ArrayList<Location> getLocations = deviceTrackerList.get(phoneNumber).getLocations();

                if (numberOfLocations <= getLocations.size()) {
                    for (int i = numberOfLocations - 1; i >= 0; i--) {
                        listOfLocations.add(getLocations.get(i));
                    }
                }
                else {
                    throw new Exception("Requested number of locations larger than history for device:" + phoneNumber);
                }
                return new Gson().toJson(listOfLocations);
            }
            else{
                throw new Exception("Device not registered.");
            }
        }
        else {
            throw new Exception("Device not found.");
        }
    }

    public String setPrivateMode(String phoneNumber, boolean privacyMode) throws Exception {

        if(deviceTrackerList.containsKey(phoneNumber)){
            if(deviceTrackerList.get(phoneNumber).isRegistered()) {
                deviceTrackerList.get(phoneNumber).setPrivateMode(privacyMode);
                return "Set private mode successful.";
            }
            else{
                throw new Exception("Device not registered.");
            }
        }
        else {
            throw new Exception("Device not found.");
        }
    }

    public String clearDeviceHistory(String phoneNumber) throws Exception {
        if(deviceTrackerList.containsKey(phoneNumber)){
            if(deviceTrackerList.get(phoneNumber).isRegistered()) {
                deviceTrackerList.get(phoneNumber).getLocations().clear();
                return "Clear device history successful.";
            }
            else{
                throw new Exception("Device not registered.");
            }
        }
        else {
            throw new Exception("Device not found.");
        }
    }

    public String clearAll() {
        deviceTrackerList.clear();
        return "Clear all successful.";
    }
}



