# DeviceTrackerWebService

JDK 1.8 Required<br>
JRE 8 Required

To run service enter following command:<br>
$ java -jar target/redsky_device_tracking_service-1.0-SNAPSHOT.jar 


<b>To Build:</b>
1. First install maven
http://maven.apache.org/install.html
2. Run command:
$ mvn package
3. Run service:
$ java -jar target/redsky_device_tracking_service-1.0-SNAPSHOT.jar 


Tested with Postman <br>
https://www.getpostman.com

<b>Example Requests</b> <br>
http://localhost:8080/registerPhone?phoneNumber=7736770795&name=iPhone6&phoneType=iPhone <br>
http://localhost:8080/updateLocationCivic?phoneNumber=7736770795&city=chicago&state=Illinois <br>
http://localhost:8080/updateLocationGeo?phoneNumber=7736770795&lat=41.953600&lng=-87.7176 <br>
http://localhost:8080/getLastLocation?phoneNumber=7736770795 <br>
http://localhost:8080/getLastLocations?phoneNumber=7736770795&numberOfLocations=2 <br>
