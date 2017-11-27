package com.adriangracia.api;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages={"com.adriangracia.api.controller",
                             "com.adriangracia.api.service"})
public class DeviceTrackerApplication {

    public static void main(String[] args){
        SpringApplication.run(DeviceTrackerApplication.class, args);
    }
}
