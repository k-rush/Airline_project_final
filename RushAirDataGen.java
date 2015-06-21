/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rushairdatagen;

import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author kdr213
 */
public class RushAirDataGen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Generator gen = new Generator();
        gen.connect();
        //gen.parseRoutes("DATA/routes.dat");
        //gen.resetAll();
        //gen.inputData("AIRPORT", "AIRPORT_ID,NAME,CITY,COUNTRY,IATA_FAA,ICAO,LATITUDE,LONGITUDE,ALTITUDE,TIME_ZONE,DST", "DATA/airports.dat");
        //gen.inputData("MODEL", "MAKE,MODEL,F_CLASS_SEATS,S_CLASS_SEATS,RANGE,MAX_SPEED","DATA/MODEL.dat");
        
        //gen.inputData("ROUTE", "ARRI_AP_ID,DEPT_AP_ID,DISTANCE","DATA/routes.dat_parsed");
        //gen.test();
        //gen.genPilots();
        //gen.genFlights();
        //gen.genBookings();
        
        
        gen.disConnect();
        
    }
}
