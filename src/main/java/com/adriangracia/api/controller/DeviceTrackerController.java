package com.adriangracia.api.controller;

import com.adriangracia.api.service.PhoneService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class DeviceTrackerController {

    @RequestMapping(value = "/registerPhone", method = RequestMethod.POST)
    @ResponseBody
    public String registerPhone(@RequestParam(value = "phoneNumber") String phoneNumber,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "phoneType") String phoneType,
                                @RequestParam(value = "manufacturer", required = false) String manufacturer) throws Exception {
        return this.phoneService.registerPhone(phoneNumber, name, phoneType, manufacturer);
    }

    @RequestMapping(value = "/unregisterPhone", method = RequestMethod.POST)
    @ResponseBody
    public String unregisterPhone(@RequestParam(value = "phoneNumber") String phoneNumber) throws Exception {
        return this.phoneService.unregisterPhone(phoneNumber);
    }

    @RequestMapping(value = "/updateLocationCivic", method = RequestMethod.POST)
    @ResponseBody
    public String updateLocation(@RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "city") String city,
                                 @RequestParam(value = "state") String state,
                                 @RequestParam(value = "street", required = false) String street,
                                 @RequestParam(value = "zipCode", required = false) String zipCode)
            throws Exception, InterruptedException, ApiException, IOException {
        return this.phoneService.updateLocation(phoneNumber, city, state, street, zipCode);
    }

    @RequestMapping(value = "/updateLocationGeo", method = RequestMethod.POST)
    @ResponseBody
    public String updateLocation(@RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "lat") double latitude,
                                 @RequestParam(value = "lng") double longitude)
            throws Exception {
        return this.phoneService.updateLocation(phoneNumber, latitude, longitude);
    }

    @RequestMapping(value = "/getLastLocation", method = RequestMethod.GET)
    @ResponseBody
    public String getLastLocation(@RequestParam(value = "phoneNumber") String phoneNumber) throws Exception {
        return this.phoneService.getLastLocation(phoneNumber);
    }

    @RequestMapping(value = "/getLastLocations", method = RequestMethod.GET)
    @ResponseBody
    public String getLastLocations(@RequestParam(value = "phoneNumber") String phoneNumber,
                                   @RequestParam(value = "numberOfLocations") Integer numberOfLocations) throws Exception {
        return this.phoneService.getLastLocations(phoneNumber, numberOfLocations);
    }

    @RequestMapping(value = "/setPrivateMode", method = RequestMethod.POST)
    @ResponseBody
    public String setPrivateMode(@RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "privacyMode") boolean privacyMode ) throws Exception {
        return this.phoneService.setPrivateMode(phoneNumber, privacyMode);
    }

    @RequestMapping(value = "/clearDeviceHistory", method = RequestMethod.POST)
    @ResponseBody
    public String clearDeviceHistory(@RequestParam(value = "phoneNumber") String phoneNumber ) throws Exception {
        return this.phoneService.clearDeviceHistory(phoneNumber);
    }

    @RequestMapping(value = "/clearAll", method = RequestMethod.POST)
    @ResponseBody
    public String clearAll() {
        return this.phoneService.clearAll();
    }


    @Autowired
    private PhoneService phoneService;
}
