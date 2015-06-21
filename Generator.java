 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rushairdatagen;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author kdr213
 */
public class Generator {
    private Connection con;
    public Generator() {
        
    }
    public void connect() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        TimeZone.setDefault(timeZone);
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@edgar2.cse.lehigh.edu:1521:cse241","kdr213","P890041607");
            } catch(Exception e) { e.printStackTrace();}
    }
    
    public void disConnect() {
        try {
                con.close();
        } catch(Exception e) { e.printStackTrace();}
    }
    
    public void genPilots() {
        String fUpdate = "INSERT INTO CUSTOMER (NAME,FF_MILES,ACCT_NUM) VALUES ";
        String lUpdate = "INSERT INTO LEG (ARR_TIME,DEP_TIME,ROUTE_ID,FLIGHT_ID,CAPTAIN_ID,COPILOT_ID,AIRPLANE_ID) VALUES";
        try{
            int count = 0;
            String line;
            String cupdate = "";
            String aupdate = "";
            String name = "";
            String uname = "";
            String pword = "";
            FileInputStream nstream = new FileInputStream(new File("DATA/cnames.txt"));
            FileInputStream ustream = new FileInputStream(new File("DATA/cusers.txt"));
            FileInputStream pstream = new FileInputStream(new File("DATA/pwords.txt"));
            BufferedReader brn = new BufferedReader(new InputStreamReader(new DataInputStream(nstream)));
            BufferedReader bru = new BufferedReader(new InputStreamReader(new DataInputStream(ustream)));
            BufferedReader brp = new BufferedReader(new InputStreamReader(new DataInputStream(pstream)));
            Statement s = con.createStatement();
            ResultSet r = null;
            int age = 0, p, salary = 0, hrs;
            long msecs = 0;/*
            for(int i = 0; i < 200; i++) {
                name = brn.readLine();
                uname = bru.readLine();
                pword = brp. readLine();
                salary = 40000 + (int)(Math.random()* 30000);
                age = 25 + (int)(Math.random()* 50);
                hrs = 2000 + (int)(Math.random()* 5000);
                
                aupdate = "INSERT INTO E_ACCT (username,password) values ('" + uname + "','" + pword + "')";
                System.out.println(aupdate);
                p = s.executeUpdate(aupdate);
                System.out.println(p);
                
                r = s.executeQuery("SELECT ACCT_ID FROM E_ACCT WHERE USERNAME = '" + uname+"'");
                r.next();
                
                
                cupdate = "INSERT INTO PILOT (NAME,SALARY, AGE,FLIGHT_HOURS,ACCT_ID)  values ('" + name + "'," +salary+ ","+ age + "," + hrs + "," + r.getInt("ACCT_ID") + ")";
                System.out.println(cupdate);
                p = s.executeUpdate(cupdate);
                System.out.println(p);
                count++;
            }*/
            
            for(int i = 0; i < 200; i++) {
                name = brn.readLine();
                uname = bru.readLine();
                pword = brp. readLine();
                salary = 40000 + (int)(Math.random()* 30000);
                age = 25 + (int)(Math.random()* 50);
                
                aupdate = "INSERT INTO E_ACCT (username,password) values ('" + uname + "','" + pword + "')";
                System.out.println(aupdate);
                p = s.executeUpdate(aupdate);
                System.out.println(p);
                
                r = s.executeQuery("SELECT ACCT_ID FROM E_ACCT WHERE USERNAME = '" + uname+"'");
                r.next();
                
                
                cupdate = "INSERT INTO MANAGER (NAME,SALARY,ACCT_ID)  values ('" + name + "'," +salary+ ","+ r.getInt("ACCT_ID") + ")";
                System.out.println(cupdate);
                p = s.executeUpdate(cupdate);
                System.out.println(p);
                count++;
            }
            s.close();
            brn.close();
            bru.close();
            brp.close();
            nstream.close();
            ustream.close();
            pstream.close();
            
        } catch(Exception e) { e.printStackTrace(); }
    }
    
    
    public void genAirplanes() {
        try{
            int count = 0;
            String line;
            String cupdate = "";
            String aupdate = "";
            String name = "";
            String uname = "";
            String pword = "";
            Statement s = con.createStatement();
            ResultSet r = null;
            int age = 0, p, loc = 0, hrs, model;
            long msecs = 0;
            
            
            for(int i = 0; i < 800; i++) {
                model = 1 + (int)(Math.random()* 8);
                //age = 25 + (int)(Math.random()* 10);
                hrs = 5000 + (int)(Math.random()* 5000);
                
                aupdate = "INSERT INTO AIRPLANE (MODEL_ID,FLIGHT_HRS) values ('" +model+"','"+ hrs + "')";
                System.out.println(aupdate);
                p = s.executeUpdate(aupdate);
                System.out.println(p);
                
            }
            s.close();

            
        } catch(Exception e) { e.printStackTrace(); }
    }
    
    public void genCusts() {
        String fUpdate = "INSERT INTO CUSTOMER (NAME,FF_MILES,ACCT_NUM) VALUES ";
        String lUpdate = "INSERT INTO LEG (ARR_TIME,DEP_TIME,ROUTE_ID,FLIGHT_ID,CAPTAIN_ID,COPILOT_ID,AIRPLANE_ID) VALUES";
        try{
            int count = 0;
            String line;
            String cupdate = "";
            String aupdate = "";
            String name = "";
            String uname = "";
            String pword = "";
            FileInputStream nstream = new FileInputStream(new File("DATA/enames.txt"));
            FileInputStream ustream = new FileInputStream(new File("DATA/eusers.txt"));
            FileInputStream pstream = new FileInputStream(new File("DATA/pwords.txt"));
            BufferedReader brn = new BufferedReader(new InputStreamReader(new DataInputStream(nstream)));
            BufferedReader bru = new BufferedReader(new InputStreamReader(new DataInputStream(ustream)));
            BufferedReader brp = new BufferedReader(new InputStreamReader(new DataInputStream(pstream)));
            Statement s = con.createStatement();
            ResultSet r = null;
            int miles = 0, p;
            for(int i = 0; i < 400; i++) {
                name = brn.readLine();
                uname = bru.readLine();
                pword = brp. readLine();
                miles = (int)(Math.random()* 2000);
                
                aupdate = "INSERT INTO C_ACCT (username,password) values ('" + uname + "','" + pword + "')";
                System.out.println(aupdate);
                p = s.executeUpdate(aupdate);
                System.out.println(p);
                
                r = s.executeQuery("SELECT ACCT_ID FROM C_ACCT WHERE USERNAME = '" + uname+"'");
                r.next();
                
                
                cupdate = "INSERT INTO CUSTOMER (NAME,FF_MILES, ACCT_ID)  values ('" + name + "'," +miles+ "," + r.getInt("ACCT_ID") + ")";
                System.out.println(cupdate);
                p = s.executeUpdate(cupdate);
                System.out.println(p);
                count++;
            }
            s.close();
            brn.close();
            bru.close();
            brp.close();
            nstream.close();
            ustream.close();
            pstream.close();
            
        } catch(Exception e) { e.printStackTrace(); }
    }
    
  
    
    public void genBookings() {
        for(int i = 0; i < 50000; i++) {
        try {
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY);
        
        ResultSet r = null;
        
        String countTickets = "SELECT COUNT(*) FROM TICKET";
        System.out.println(countTickets);
        r = s.executeQuery(countTickets);
        r.next();
        int count = r.getInt("COUNT(*)");
        System.out.println("COUNT: " + count);
            int ticket_id = (int)(Math.random()*count);
            
        
        String isBookedQ = "SELECT * FROM TICKET NATURAL JOIN BOOKING WHERE TICKET_ID = " + ticket_id;
        r = s.executeQuery(isBookedQ);
        
        while(r.next()) {
            r = s.executeQuery(countTickets);
            r.next();
            count = r.getInt("COUNT(*)");

            ticket_id = (int)(Math.random()*count);
            isBookedQ = "SELECT * FROM TICKET NATURAL JOIN BOOKING WHERE TICKET_ID = " + ticket_id;
            r = s.executeQuery(isBookedQ);
            
        }
        System.out.println("TICKET_ID: " + ticket_id);
        
        int bags = (int)(Math.random()*count);
        System.out.println("BAGS: " + bags);
        
        int price;
        int miles = 0;
        
        String milesQ = "SELECT * "
            + "FROM TICKET NATURAL JOIN LEG NATURAL JOIN ROUTE "
            + "WHERE TICKET_ID = " + ticket_id;
        
        
        r = s.executeQuery(milesQ);
        if(r.next()) miles = r.getInt("DISTANCE");
        System.out.println("MILES: " + miles);
        price = 100;
        if(miles < 100) price = 50;
        else if (miles < 300) price = 100;
        else if (miles < 500) price = 150;
        else if (miles < 1000) price = 200;
        else if (miles < 1500) price = 300;
        else if (miles < 2000) price = 400;
        else if (miles < 3000) price = 500;
        else if (miles < 4000) price = 600;
        else if (miles >= 4000) price = 750;
        
        price += bags*10;
        System.out.println("PRICE: " + price);
        
        String countCusts = "SELECT COUNT(*) FROM CUSTOMER";
        r = s.executeQuery(countCusts);
            r.next();
            count = r.getInt("COUNT(*)");

            int cust_id = (int)(Math.random()*count);
            
       String bookInsert = "INSERT INTO BOOKING (PRICE, FF_MILES, BAGS, TICKET_ID, CUST_ID) VALUES (" + price + "," + miles + "," + bags + "," + ticket_id + "," + cust_id + ")";
          s.executeUpdate(bookInsert);  
        
        System.out.println("CUST_ID: " + cust_id);
        
        String u = "UPDATE CUSTOMER SET FF_MILES = FF_MILES + " + miles + " WHERE CUST_ID = " + cust_id;
        System.out.println(u);
        s.executeUpdate(u);
        
        String cQ = "SELECT * FROM CUSTOMER WHERE CUST_ID = " + cust_id;
        ResultSet res = s.executeQuery(cQ);
        if(res.next())
            System.out.println(res.getInt("FF_MILES"));

        } catch(Exception e) { e.printStackTrace(); }
        
        }
        
    }
    
    
    public void genFlights() {
        try {
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        
            ResultSet r = null;
            
            int num = 0;
            String statuses[] = {"DELAYED", "ON TIME", "CANCELLED"};
            String status = "", fUpdate ="";
            int st = 0, delay, fid;
            int ap_id;
            int ais, did, route, capid, coid, airid;
            long offset, end, diff;
            Timestamp stamp;
            Date dd, ad;
            
            double dist, speed;
            long hrs;
            int start_loc;
            int num_flights;
            //Loop Through every airplan in fleet.
            for(int a = 36; a <= 40; a++) {
                
                // Random starting date of our airplane's first flight.
                offset = Timestamp.valueOf("2013-04-20 00:00:00").getTime();
                end = Timestamp.valueOf("2013-04-26 00:00:00").getTime();
                diff = end - offset + 1;
                stamp = new Timestamp(offset + (long)(Math.random() * diff));

                dd = new Date(stamp.getTime());
                
                //Random starting location of our airplane's first flight (1 of 7411).
                r = s.executeQuery("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 100)");
                r.next();
                int count = r.getInt("COUNT(*)");
                

                r = s.executeQuery("SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 80");
                r.next();

                r.relative((int)(Math.random()*count)-1);
                ap_id = r.getInt("AIRPORT_ID");
                System.out.println("Starting loc: " + ap_id);
                
                //Random number of flights our airplane will have been scheduled for. (from 6-26)
                num_flights = 4 + (int)(Math.random() * 8);
                
                //For Each airplane, generate flights.
                for(int i = 0; i < num_flights;i++) {
                    
                    //Find unique flight number (1-600000)
                    do {
                        num = (int)(Math.random()* 600000);
                        r = s.executeQuery("SELECT COUNT(*) FROM FLIGHT WHERE FLIGHT_NUM = '" + num + "'");
                        r.next();
                    } while(r.getInt("COUNT(*)") > 0);
                    
                    
                    // Is our flight on time or delayed?
                    st = (int)(Math.random()* 2-.000001);
                    
                    if(st == 1) delay = 0;
                    else delay = (int)(Math.random()* 120);
                    
                    fUpdate = "INSERT INTO FLIGHT (FLIGHT_NUM,STATUS,DELAY) VALUES (" + num + ",'" + statuses[st] + "'," + delay +")";

                    System.out.println(fUpdate);
                    s.executeUpdate(fUpdate);
                    
                    
                    r = s.executeQuery("SELECT FLIGHT_ID FROM FLIGHT WHERE FLIGHT_NUM = " + num + " AND STATUS = '" + statuses[st] + "' AND DELAY = " + delay);
                    r.next();
                    fid = r.getInt("FLIGHT_ID");
                    
                    
                    r = s.executeQuery("SELECT COUNT(*) FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                            + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                            + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 30 )");
                    r.next();
                    count = r.getInt("COUNT(*)");
                    System.out.println("Count: " + count);
                    r = s.executeQuery("SELECT * FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                            + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                            + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 30 )");
                    r.next();
                    
                    r.relative((int)(Math.random()*count));
                    route = r.getInt("ROUTE_ID");
                    
                    
                    r =s.executeQuery("SELECT ARRI_AP_ID FROM ROUTE WHERE ROUTE_ID = " + route);
                    r.next();
                    ap_id = r.getInt("ARRI_AP_ID");
                    
                    r =s.executeQuery("SELECT DISTANCE FROM ROUTE WHERE ROUTE_ID = " + route);
                    r.next();
                    dist = r.getDouble("DISTANCE");

                    r = s.executeQuery("SELECT MAX_SPEED FROM AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                    r.next();
                    speed = r.getDouble("MAX_SPEED");

                    hrs = (long)(3600000. *  dist/speed);
                    stamp = new Timestamp(stamp.getTime() + hrs + 1800000);
                    ad = new Date(stamp.getTime());
                    System.out.println(stamp.toString());
                    // Find Captain and Copilots:
                    capid = (int)(Math.random()*99);
                    r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                    r.next();
                    int x = r.getInt("COUNT(*)");
                    while(x > 1) {
                        capid = (int)(Math.random()*98) + 1;
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                    }
                    
                    coid = 99+(int)(Math.random()*98) + 1;
                    r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                    r.next();
                    x = r.getInt("COUNT(*)");
                    while(x > 1) {
                        coid = 99+(int)(Math.random()*99);
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                    }
                    
                    
                    String departStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(dd);
                    String arrivalStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(ad);
                    s.executeUpdate("INSERT INTO LEG (DEP_TIME,ARR_TIME,ROUTE_ID,CAPTAIN_ID,COPILOT_ID,AIRPLANE_ID, FLIGHT_ID) values (timestamp'" +
                            departStamp + "',timestamp'" + arrivalStamp + "'," + route + "," + capid + "," + coid + "," + a + "," + fid + ")");
                    
                    r = s.executeQuery("SELECT LEG_ID FROM LEG WHERE FLIGHT_ID = " + fid + " AND CAPTAIN_ID = " + capid + " AND ROUTE_ID = " + route + " AND ARR_TIME = timestamp'" + arrivalStamp + "'");
                    r.next();
                    int legId = r.getInt("LEG_ID");
                    
                    r = s.executeQuery("SELECT F_CLASS_SEATS, S_CLASS_SEATS FROM AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                    r.next();
                    int fcSeats = r.getInt("F_CLASS_SEATS");
                    int scSeats = r.getInt("S_CLASS_SEATS");
                    int t = 0;
                    
                    for(; t < fcSeats; t++) {
                        s.executeUpdate("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",1," + legId + ")");
                    }
                    
                    for(; t < scSeats; t++) {
                        s.executeUpdate("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",2," + legId + ")");
                    }

                    for(int j = 0; j < 1+ (int)(Math.random()*10); j++) {
                        stamp = new Timestamp(stamp.getTime() + 3600000);
                        dd = new Date(stamp.getTime());
                        
                        r = s.executeQuery("SELECT COUNT(*) FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                                + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                                + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10 )");
                        r.next();
                        count = r.getInt("COUNT(*)");
                        //System.out.println("Count: " + count);
                        r = s.executeQuery("SELECT * FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                                + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                                + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10 )");
                        r.next();

                        r.relative((int)(Math.random()*count));
                        route = r.getInt("ROUTE_ID");

                        r =s.executeQuery("SELECT ARRI_AP_ID FROM ROUTE WHERE ROUTE_ID = " + route);
                        r.next();
                        ap_id = r.getInt("ARRI_AP_ID");
                        
                        r = s.executeQuery("SELECT DISTANCE FROM ROUTE WHERE ROUTE_ID = " + route);
                        r.next();
                        dist = r.getDouble("DISTANCE");

                        r = s.executeQuery("SELECT MAX_SPEED FROM AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                        r.next();
                        speed = r.getDouble("MAX_SPEED");

                        hrs = (long)(3600000. *  dist/speed);
                        stamp = new Timestamp(stamp.getTime() + hrs + 1800000);
                        ad = new Date(stamp.getTime());

                        // Find Captain and Copilots:
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                        r.next();
                        x = r.getInt("COUNT(*)");
                        while(x > 1) {
                            capid = (int)(Math.random()*99);
                            r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                        }

                        
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                        r.next();
                        x = r.getInt("COUNT(*)");
                        while(x > 1) {
                            coid = 99+(int)(Math.random()*99);
                            r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + "' AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                        }
                        
                        
                        departStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(dd);
                        arrivalStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(ad);
                        s.executeUpdate("INSERT INTO LEG (DEP_TIME,ARR_TIME,ROUTE_ID,CAPTAIN_ID,COPILOT_ID,AIRPLANE_ID, FLIGHT_ID) values (timestamp'" +
                                departStamp + "',timestamp'" + arrivalStamp + "'," + route + "," + capid + "," + coid + "," + a + "," + fid + ")");

                        r = s.executeQuery("SELECT LEG_ID FROM LEG WHERE FLIGHT_ID = " + fid + " AND CAPTAIN_ID = " + capid + " AND ROUTE_ID = " + route + " AND ARR_TIME = timestamp'" + arrivalStamp + "'");
                        r.next();
                        legId = r.getInt("LEG_ID");

                        r = s.executeQuery("SELECT F_CLASS_SEATS, S_CLASS_SEATS FROM AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                        r.next();
                        fcSeats = r.getInt("F_CLASS_SEATS");
                        scSeats = r.getInt("S_CLASS_SEATS");
                        t = 0;

                        for(; t < fcSeats; t++) {
                            s.executeUpdate("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",1," + legId + ")");
                        }

                        for(; t < scSeats; t++) {
                            s.executeUpdate("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",2," + legId + ")");
                        }

                        
                    }
                
                        hrs = (long)(3600000.  * (int)(Math.random()*24));
                        stamp = new Timestamp(stamp.getTime() + hrs + 1800000);
                        dd = new Date(stamp.getTime());

                }
            }
                
        } catch(Exception e) { e.printStackTrace(); }
    }
    
    
    
    
    public void genFlightsTest() {
        try {
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        
            ResultSet r = null;
            
            int num = 0;
            String statuses[] = {"DELAYED", "ON TIME", "CANCELLED"};
            String status = "", fUpdate ="";
            int st = 0, delay, fid;
            int ap_id;
            int ais, did, route, capid, coid, airid;
            long offset, end, diff;
            Timestamp stamp;
            Date dd, ad;
            
            double dist, speed;
            long hrs;
            int start_loc;
            int num_flights;
            //Loop Through every airplan in fleet.
            for(int a = 1; a <= 10; a++) {
                
                // Random starting date of our airplane's first flight.
                offset = Timestamp.valueOf("2011-01-01 00:00:00").getTime();
                end = Timestamp.valueOf("2011-04-04 00:00:00").getTime();
                diff = end - offset + 1;
                stamp = new Timestamp(offset + (long)(Math.random() * diff));

                dd = new Date(stamp.getTime());
                
                //Random starting location of our airplane's first flight (1 of 7411).
                r = s.executeQuery("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 30)");
                r.next();
                int count = r.getInt("COUNT(*)");
                

                r = s.executeQuery("SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10");
                r.next();

                r.relative((int)(Math.random()*count)-1);
                ap_id = r.getInt("AIRPORT_ID");
                System.out.println("Starting loc: " + ap_id);
                
                //Random number of flights our airplane will have been scheduled for. (from 6-20)
                num_flights = 6 + (int)(Math.random() * 20);
                
                //For Each airplane, generate flights.
                for(int i = 0; i < num_flights;i++) {
                    
                    //Find unique flight number (1-600000)
                    do {
                        num = (int)(Math.random()* 600000);
                        r = s.executeQuery("SELECT COUNT(*) FROM FLIGHT WHERE FLIGHT_NUM = '" + num);
                        r.next();
                    } while(r.getInt("COUNT(*)") > 0);
                    
                    
                    // Is our flight on time or delayed?
                    st = (int)(Math.random()* 2);
                    
                    if(st == 2) delay = 0;
                    else delay = (int)(Math.random()* 120);
                    
                    fUpdate = "INSERT INTO FLIGHT (FLIGHT_NUM,STATUS,DELAY) VALUES (" + num + ",'" + statuses[st] + "'," + delay +")";

                    System.out.println(fUpdate);
                    System.out.println(fUpdate);
                    
                    
                    r = s.executeQuery("SELECT FLIGHT_ID FROM FLIGHT WHERE FLIGHT_NUM = " + num + " AND STATUS = '" + statuses[st] + "' AND DELAY = " + delay);
                    r.next();
                    fid = r.getInt("FLIGHT_ID");
                    
                    
                    r = s.executeQuery("SELECT COUNT(*) FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                            + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                            + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10 )");
                    r.next();
                    count = r.getInt("COUNT(*)");
                    System.out.println("Count: " + count);
                    r = s.executeQuery("SELECT * FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                            + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                            + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10 )");
                    r.next();
                    
                    r.relative((int)(Math.random()*count)-1);
                    route = r.getInt("ROUTE_ID");
                    
                    
                    r =s.executeQuery("SELECT ARRI_AP_ID FROM ROUTE WHERE ROUTE_ID = " + route);
                    r.next();
                    ap_id = r.getInt("ARRI_AP_ID");
                    
                    r =s.executeQuery("SELECT DISTANCE FROM ROUTE WHERE ROUTE_ID = " + route);
                    r.next();
                    dist = r.getDouble("DISTANCE");

                    r = s.executeQuery("SELECT MAX_SPEED FROM AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                    r.next();
                    speed = r.getDouble("MAX_SPEED");

                    hrs = (long)(3600000. *  dist/speed);
                    stamp = new Timestamp(stamp.getTime() + hrs + 1800000);
                    ad = new Date(stamp.getTime());
                    
                    // Find Captain and Copilots:
                    capid = (int)(Math.random()*99);
                    r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                    r.next();
                    int x = r.getInt("COUNT(*)");
                    while(x > 1) {
                        capid = (int)(Math.random()*99);
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                    }
                    
                    coid = 99+(int)(Math.random()*99);
                    r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                    r.next();
                    x = r.getInt("COUNT(*)");
                    while(x > 1) {
                        coid = 99+(int)(Math.random()*99);
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                    }
                    
                    
                    String departStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(dd);
                    String arrivalStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(ad);
                    System.out.println("INSERT INTO LEG (DEP_TIME,ARR_TIME,ROUTE_ID,CAPTAIN_ID,COPILOT_ID,AIRPLANE_ID, FLIGHT_ID) values (timestamp'" +
                            departStamp + "',timestamp'" + arrivalStamp + "'," + route + "," + capid + "," + coid + "," + a + "," + fid + ")");
                    
                    r = s.executeQuery("SELECT LEG_ID FROM LEG WHERE FLIGHT_ID = " + fid + " AND CAPTAIN_ID = " + capid + " AND ROUTE_ID = " + route + " AND ARR_TIME = " + arrivalStamp);
                    r.next();
                    int legId = r.getInt("LEG_ID");
                    
                    r = s.executeQuery("SELECT F_CLASS_TICKETS, S_CLASS_TICKETS AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                    r.next();
                    int fcSeats = r.getInt("F_CLASS_SEATS");
                    int scSeats = r.getInt("S_CLASS_SEATS");
                    int t = 0;
                    
                    for(; t < fcSeats; t++) {
                        System.out.println("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",1," + legId);
                    }
                    
                    for(; t < scSeats; t++) {
                        System.out.println("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",2," + legId);
                    }

                    for(int j = 0; j < 1+ (int)(Math.random()*10); j++) {
                        stamp = new Timestamp(stamp.getTime() + 3600000);
                        dd = new Date(stamp.getTime());
                        
                        r = s.executeQuery("SELECT COUNT(*) FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                                + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                                + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10 )");
                        r.next();
                        count = r.getInt("COUNT(*)");
                        //System.out.println("Count: " + count);
                        r = s.executeQuery("SELECT * FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap_id
                                + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + a + ")"
                                + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 10 )");
                        r.next();

                        r.relative((int)(Math.random()*count)-1);
                        route = r.getInt("ROUTE_ID");


                        r = s.executeQuery("SELECT DISTANCE FROM ROUTE WHERE ROUTE_ID = " + route);
                        r.next();
                        dist = r.getDouble("DISTANCE");

                        r = s.executeQuery("SELECT MAX_SPEED FROM AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                        r.next();
                        speed = r.getDouble("MAX_SPEED");

                        hrs = (long)(3600000. *  dist/speed);
                        stamp = new Timestamp(stamp.getTime() + hrs + 1800000);
                        ad = new Date(stamp.getTime());

                        // Find Captain and Copilots:
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                        r.next();
                        x = r.getInt("COUNT(*)");
                        while(x > 1) {
                            capid = (int)(Math.random()*99);
                            r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND CAPTAIN_ID = " + capid);
                        }

                        
                        r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                        r.next();
                        x = r.getInt("COUNT(*)");
                        while(x > 1) {
                            coid = 99+(int)(Math.random()*99);
                            r = s.executeQuery("SELECT COUNT(*) FROM LEG WHERE ARR_TIME >= timestamp'" + stamp.toString() + " AND DEP_TIME <= timestamp'" + stamp.toString() +"' AND COPILOT_ID = " + coid);
                        }
                        
                        
                        departStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(dd);
                        arrivalStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(ad);
                        System.out.println("INSERT INTO LEG (DEP_TIME,ARR_TIME,ROUTE_ID,CAPTAIN_ID,COPILOT_ID,AIRPLANE_ID, FLIGHT_ID) values (timestamp'" +
                                departStamp + "',timestamp'" + arrivalStamp + "'," + route + "," + capid + "," + coid + "," + a + "," + fid + ")");

                        r = s.executeQuery("SELECT LEG_ID FROM LEG WHERE FLIGHT_ID = " + fid + " AND CAPTAIN_ID = " + capid + " AND ROUTE_ID = " + route + " AND ARR_TIME = " + arrivalStamp);
                        r.next();
                        legId = r.getInt("LEG_ID");

                        r = s.executeQuery("SELECT F_CLASS_TICKETS, S_CLASS_TICKETS AIRPLANE NATURAL JOIN MODEL WHERE AIRPLANE_ID = " + a);
                        r.next();
                        fcSeats = r.getInt("F_CLASS_SEATS");
                        scSeats = r.getInt("S_CLASS_SEATS");
                        t = 0;

                        for(; t < fcSeats; t++) {
                            System.out.println("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",1," + legId);
                        }

                        for(; t < scSeats; t++) {
                            System.out.println("INSERT INTO TICKET (SEAT,BOARDING_GROUP,CLASS,LEG_ID) VALUES (" + t + "," + (int)(t/30) + ",2," + legId);
                        }

                        
                    }
                
                    stamp = new Timestamp(stamp.getTime() + 3600000);
                        dd = new Date(stamp.getTime());

                }
            }
                
        } catch(Exception e) { e.printStackTrace(); }
    }
    
    public void test() {
        try {
            int ap = 1218;
            int route = 0;
            int pl = 840;
            int count;
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
            ResultSet r;
            //Random starting location of our airplane's first flight (1 of 7411).
            
            r = s.executeQuery("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 30)");
            r.next();
            count = r.getInt("COUNT(*)");
            System.out.println("Count 1: " + count);
            
            r = s.executeQuery("SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) > 20");
            r.next();

            r.relative((int)(Math.random()*count)-1);
            ap = r.getInt("AIRPORT_ID");
            ap = 1804;
            System.out.println("AIRPORT_ID: " + ap);
            
            
            r = s.executeQuery("SELECT COUNT(*) FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap
                    + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + pl + ")"
                    + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) >6 )");
            r.next();
            count = r.getInt("COUNT(*)");
            System.out.println("Count: " + count);
            r = s.executeQuery("SELECT * FROM (ROUTE A JOIN AIRPORT B ON A.DEPT_AP_ID = B.AIRPORT_ID)  WHERE B.AIRPORT_ID = " + ap
                    + "AND A.ROUTE_ID NOT IN (SELECT ROUTE_ID FROM  ROUTE, (SELECT * FROM AIRPLANE NATURAL JOIN MODEL) A WHERE ROUTE.DISTANCE > A.RANGE AND AIRPLANE_ID = " + pl + ")"
                    + "AND A.ARRI_AP_ID IN (SELECT AIRPORT_ID FROM AIRPORT JOIN ROUTE ON airport_id = dept_ap_id GROUP BY AIRPORT_ID HAVING COUNT(*) >6 )");
                    r.next();
            if(count > 0) {
                r.relative((int)(Math.random()*count)-1);
                route = r.getInt("ROUTE_ID");
                ap = r.getInt("ARRI_AP_ID");
            }

            else route = (int)(Math.random()*51000);
            
            System.out.println("ROUTE: " + route + "\nARRIVING AP: " + ap);
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public void inputData(String tabname, String vars, String path) {
        try {
            int count = 0;
            String line;
            String update = "";
            FileInputStream fstream = new FileInputStream(new File(path));
            BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(fstream)));
            Statement s = con.createStatement();
            int i;
            while( (line = br.readLine()) != null) {
                update = "INSERT INTO " + tabname + " (" + vars + ")  values (" + line + ")";
                System.out.println(update);
                i = 0;
                try {
                                System.out.println("here");

                i = s.executeUpdate(update); } catch(Exception e) {System.out.println("Excpeption."); e.printStackTrace();}
                System.out.println("Value returned: " + i +"\tCount: " + count);
                count++;
            }
            s.close();
            br.close();
            fstream.close();
            
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    
    public void inputUniqueData(String tabname, String vars, String path) {
        try {
            int count = 0;
            String line;
            String update = "";
            FileInputStream fstream = new FileInputStream(new File(path));
            BufferedReader br = new UniqueLineReader(new InputStreamReader(new DataInputStream(fstream)));
            Statement s = con.createStatement();
            int i;
            while( (line = br.readLine()) != null) {
                update = "INSERT INTO " + tabname + " (" + vars + ")  values (" + line + ")";
                System.out.println(update);
                i = 0;
                try {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              i = s.executeUpdate(update); 
                } catch(Exception e) {System.out.println("Excpetion during insert."); e.printStackTrace(); }
                System.out.println("Value returned: " + i +"\tCount: " + count);
                count++;
            }
            s.close();
            br.close();
            fstream.close();
            
        } catch (Exception e) { e.printStackTrace(); }
    }
           
    
    public void executeUpdateFile(String path) {
        try {
            String line;
            FileInputStream fstream = new FileInputStream(new File(path));
            BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(fstream)));
            Statement s = con.createStatement();
            
            int i;
            while( (line = br.readLine()) != null) {
                System.out.println(line);
                i = s.executeUpdate(line);
                System.out.println("Value returned: " + i);
            }
            s.close();
            br.close();
            fstream.close();
            
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public void resetAll() {
        dropTriggers();
        dropSequences();
        dropTables();
        
        
        addTables();
        addConstraints();
        addSequences();
        addTriggers();
        
    }
    
    public void dropTables() {
        executeUpdateFile("DDL/DROP_TABLES");   
    }
    
    public void dropSequences() {
        executeUpdateFile("DDL/DROP_SEQUENCES");
    }
    
    public void dropTriggers() {
        executeUpdateFile("DDL/DROP_TRIGGERS");
    }
    
    public void addTables() {
        executeUpdateFile("DDL/ADD_TABLES");
    }
    
    public void addConstraints() {
        executeUpdateFile("DDL/ADD_CONSTRAINTS");
    }
    
    public void addSequences() {
        executeUpdateFile("DDL/ADD_SEQUENCES");
    }
    
    public void addTriggers() {
        executeUpdateFile("DDL/ADD_TRIGGERS");
    }
    
    public void parseSQL_ForJDBC(String path) {
        try {
            int val;
            char ch;
            //FileInputStream fstream = new FileInputStream(new File(path));
            String fpath = path + "_jdbc";
            String line = "";
            System.out.println("Creating new file: "+fpath);
            
            File f = new File(fpath);
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            BufferedReader br = new BufferedReader(new FileReader(path));
            System.out.println("Writing to "+fpath+": ");
            
            while( (val = br.read()) != -1) {
                ch = (char)val;
                if(ch == ';') {
                    System.out.print("\n");
                    bw.newLine();
                    line = "";
                }
                else if(ch == '\n' || ch == '\r') {
                    System.out.print(' ');
                    bw.write(' ');
                }
                else  {
                    System.out.print(ch);
                    bw.write(ch);
                }
            }
            br.close();
            bw.close();
            
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public void parseAirportData(String path) {
        try {
            int val;
            char ch = ' ';
            //FileInputStream fstream = new FileInputStream(new File(path));
            String fpath = path + "_new";
            String line = "";
            System.out.println("Creating new file: "+fpath);
            
            File f = new File(fpath);
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            BufferedReader br = new BufferedReader(new FileReader(path));
            System.out.println("Writing to "+fpath+": ");
            
            while( (val = br.read()) != -1) {
                ch = (char)val;
                if(ch == '"') 
                    bw.write('\'');
                else if(ch == '\''){
                    bw.write('\'');
                    bw.write('\'');}
                else
                    bw.write(ch);
            }
            br.close();
            bw.close();
            
        } catch (Exception e) { e.printStackTrace(); }
    
    }
    
    
    
    public void parseRoutes(String path) {
            try {
            String vals[];
            int lcount = 0;
            //FileInputStream fstream = new FileInputStream(new File(path));
            String fpath = path + "_parsed";
            String line = "";
            String newline = "";
            String aName = "", bName = "";
            String aQuery, bQuery;
            
            System.out.println("Creating new file: "+fpath);
            File f = new File(fpath);
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            System.out.println("Writing to "+fpath+": ");
            
            Statement s = con.createStatement();
            ResultSet result;
            
            double aLon = 0, aLat = 0, bLon = 0, bLat = 0;
            double d = 0;
            while( (line = br.readLine()) != null) {
                vals = line.split(",");
                if(!vals[3].equals("null") && !vals[5].equals("null")) {
                    System.out.println("COUNT: " + lcount + "\tLINE: " + line);
                    
                    System.out.println("SOURCE ID: " + vals[3] + "\tDEST ID: " + vals[5]);

                    aQuery = "SELECT NAME, LONGITUDE, LATITUDE " +
                            "FROM AIRPORT " +
                            "WHERE AIRPORT_ID = " + vals[3];

                    bQuery = "SELECT NAME, LONGITUDE, LATITUDE " +
                            "FROM AIRPORT " +
                            "WHERE AIRPORT_ID = " + vals[5];

                    result = s.executeQuery(aQuery);

                    if (!result.next()) System.out.println ("Empty result.");
                    else {
                        aName = result.getString("NAME");
                        aLon = Double.parseDouble(result.getString("LONGITUDE"));
                        aLat = Double.parseDouble(result.getString("LATITUDE"));
                      } 


                    result = s.executeQuery(bQuery);

                    if (!result.next()) System.out.println ("Empty result.");
                    else {
                        bName = result.getString("NAME");
                        bLon = Double.parseDouble(result.getString("LONGITUDE"));
                        bLat = Double.parseDouble(result.getString("LATITUDE"));
                      }

                    
                    
                   
                    
                    System.out.println("SOURCE      NAME: " + aName + " [LONGITUDE: " + aLon + " LATITUDE: "+ aLat + "]");
                    System.out.println("DESTINATION NAME: " + bName + " [LONGITUDE: " + bLon + " LATITUDE: "+ bLat + "]");
                    
                    d = (new BigDecimal(distance(aLat, aLon, bLat, bLon, 'M'))).round(new MathContext(6)).doubleValue();
                    
                    System.out.println("DISTANCE: " + d+ "\n");



                    newline = vals[3]+","+vals[5]+","+d+"\n";
                    bw.write(newline);
                    lcount++;
                }
                else {
                    System.out.println("NULL AIRPORT_ID VALUE.\n");
                    lcount++;
                }
            }
            br.close();
            bw.flush();
            bw.close();
            
        } catch (Exception e) { e.printStackTrace(); }
    
    }
    
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
          dist = dist * 1.609344;
        } else if (unit == 'N') {
              dist = dist * 0.8684;
          }
        return (dist);
      }

    private double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
      return (rad * 180 / Math.PI);
    }
    
    public void createFlights() {
        
    }

}
