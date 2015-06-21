/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airlineinterface;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;


/**
 *
 * @author kdr213
 */
public class DBController {
    private Connection con;
    
    
    public void connect() {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@edgar2.cse.lehigh.edu:1521:cse241","kdr213","P890041607");
            } catch(Exception e) { e.printStackTrace();}
    }
    
    public ResultSet getResult(String select, String from, String where) {
        String q = "SELECT " + select + " FROM " + from + " WHERE " + where;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet getResult(String q) {
        
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    // SAMPLE: USERNAME: evililgirleg  PASSWORD:  nvKYc547
    /* Customer logon.  Returns 1 if ok, -1 if invalid username, 
     * -2 if invalid password, -3 for exception. */
    public int cLogon(String username, String password) {
        System.out.println("Logging in " + username + ".");
        ResultSet r = null;
        try { 
            Statement s = con.createStatement();
             r = s.executeQuery("SELECT * FROM C_ACCT WHERE USERNAME = '" + username + "'");
            if(!r.next()) return -1;
        } catch(Exception e) { 
            e.printStackTrace(); 
            return -3; }
        try {
            String pword = r.getString("PASSWORD");
            if(!pword.equals(password)) return -2;
            else return 1;      
        } catch(Exception e) { 
            e.printStackTrace();
            return -3; }
       
    }
    
    public int getCustId(String username) {
        String q = "SELECT CUST_ID FROM CUSTOMER NATURAL JOIN C_ACCT WHERE USERNAME =  '" + username + "'";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return -1;
            else return result.getInt("CUST_ID");
        } catch(Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public int getManId(String username) {
        String q = "SELECT MANAGER_ID FROM MANAGER NATURAL JOIN E_ACCT WHERE USERNAME =  '" + username + "'";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return -1;
            else return result.getInt("MANAGER_ID");
        } catch(Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public int getPilotId(String username) {
        String q = "SELECT PILOT_ID FROM PILOT NATURAL JOIN E_ACCT WHERE USERNAME =  '" + username + "'";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return -1;
            else return result.getInt("PILOT_ID");
        } catch(Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public int getEmpIdPilot(String username) {
        String q = "SELECT EMP_ID FROM PILOT NATURAL JOIN E_ACCT WHERE USERNAME =  '" + username + "'";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return -1;
            else return result.getInt("EMP_ID");
        } catch(Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public int getEmpIdMan(String username) {
        String q = "SELECT EMP_ID FROM MANAGER NATURAL JOIN E_ACCT WHERE USERNAME =  '" + username + "'";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return -1;
            else ;
        } catch(Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public String getNameCust(int cust_id) {
        String q = "SELECT NAME FROM CUSTOMER WHERE CUST_ID =  " + cust_id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return null;
            else return result.getString("NAME");
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public String getNameMan(int man_id) {
        String q = "SELECT NAME FROM MANAGER WHERE MANAGER_ID =  " + man_id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return null;
            else return result.getString("NAME");
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public String getAPNameById(int id) {
        String q = "SELECT NAME FROM AIRPORT WHERE AIRPORT_ID =  " + id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return null;
            else return result.getString("NAME");
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public String getNamePilot(int pilot_id) {
        String q = "SELECT NAME FROM PILOT WHERE PILOT_ID =  " + pilot_id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return null;
            else return result.getString("NAME");
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    /* Employee logon.  Returns 1 if ok, -1 if invalid username, 
     * -2 if invalid password, -3 for exception. */
    public int eLogon(String username, String password) {
        try { 
            ResultSet r = getResult("SELECT * FROM E_ACCT WHERE USERNAME = '" + username + "'");
            if(!r.next()) return -1;
            else if(!r.getString("PASSWORD").equals(password)) return -2;
            else return 1;      
        } catch(Exception e) {e.printStackTrace();}
        return -3;
    }
    
    public ResultSet searchAirportsByCode(String code) {
        ResultSet r = getResult("*", "AIRPORT", "IATA_FAA = '"+code+"' OR ICAO = '" + code + "'");
        return r;
    }
    
    public ResultSet searchAirportsByCity(String city) {
        ResultSet r = getResult("*", "AIRPORT", "CITY = '" + city + "'");
        return r;
    }
    
    public ResultSet searchAirportsByName(String name) {
        ResultSet r = getResult("*", "AIRPORT", "NAME = '" + name + "'");
        return r;
    }
    
    public ResultSet searchAirportsById(int ap_id) {
        ResultSet r = getResult("*", "AIRPORT", "AIRPORT_ID = " + ap_id);
        return r;
    }
    
    public String getAirportCodeById(int id) {
        ResultSet r = searchAirportsById(id);
        String code = "";
        try {
            if(r.next() ) {
                if((code = r.getString("IATA_FAA")) == null) {
                    code = r.getString("ICAO");
                }
                if(code == null) code = "";
            }
        } catch(Exception e) {e.printStackTrace(); }
        return code;
    }
    
    
    
    public int getAirportIdByCode(String code) {
        ResultSet r = searchAirportsByCode(code);
        int id = -1;
        try {
            if(r.next()) id = r.getInt("AIRPORT_ID");
        } catch(Exception e) { e.printStackTrace(); }
        
        return id;
    }
    
    public ResultSet searchDepartingFlightsWithConnections(int dept_ap_id, int connections) {
        String q = "WITH RECURSIVE_LEG (DEPT_AP_ID, ARRI_AP_ID, LEG_ID, ORIG_DEP_TIME, CUR_DEP_TIME, ARR_TIME, LEG_COUNT, AP_ID_LIST, LEG_ID_LIST, FLIGHT_NUM_LIST) AS  " +
                "(SELECT B.DEPT_AP_ID, B.ARRI_AP_ID, A.LEG_ID, A.DEP_TIME, A.DEP_TIME, A.ARR_TIME,  " +
                 " 1, CAST(B.DEPT_AP_ID||' '||B.ARRI_AP_ID||' '  AS VARCHAR(300)), " +
                 " CAST(LEG_ID||' ' AS VARCHAR(300)), CAST(FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM LEG A NATURAL JOIN ROUTE B NATURAL JOIN FLIGHT G " +
                "WHERE B.DEPT_AP_ID =  " + dept_ap_id + 
                " UNION ALL " +
                "SELECT R.DEPT_AP_ID, D.ARRI_AP_ID, C.LEG_ID, R.ORIG_DEP_TIME, C.DEP_TIME, C.ARR_TIME,  " +
                "  R.LEG_COUNT + 1, CAST(R.AP_ID_LIST||' '||D.ARRI_AP_ID||' '  AS VARCHAR(300)),  " +
                "  CAST(R.LEG_ID_LIST||' '||C.LEG_ID||' ' AS VARCHAR(300)), CAST(R.FLIGHT_NUM_LIST||' '||F.FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM RECURSIVE_LEG R, (LEG C NATURAL JOIN ROUTE D NATURAL JOIN FLIGHT F) " +
                "WHERE R.ARRI_AP_ID = D.DEPT_AP_ID AND C.DEP_TIME > R.ARR_TIME + INTERVAL '40' MINUTE AND C.DEP_TIME < R.ARR_TIME + INTERVAL '1440' MINUTE ) " +
                "CYCLE ARRI_AP_ID SET CYCLIC TO '1' DEFAULT '0'  " +
                "SELECT DAP.NAME AS \"DEPT_AP_NAME\", AAP.NAME AS \"ARRI_AP_NAME\", R.DEPT_AP_ID, R.ARRI_AP_ID, R.ORIG_DEP_TIME AS \"DEP_TIME\", ARR_TIME, R.LEG_COUNT, R.LEG_ID_LIST, AP_ID_LIST, FLIGHT_NUM_LIST, CYCLIC " +
                 "     FROM RECURSIVE_LEG R JOIN AIRPORT DAP ON DEPT_AP_ID = DAP.AIRPORT_ID JOIN AIRPORT AAP ON ARRI_AP_ID = AAP.AIRPORT_ID " +
                 "     WHERE R.LEG_COUNT-1 < " + connections;
        
         try {
                Statement s = con.createStatement();
                ResultSet result = s.executeQuery(q);
                return result;
         } catch(Exception e) { e.printStackTrace(); }
         return null;
    }
    
    public ResultSet searchDepartingFlightsWithConnectionsByTime(int dept_ap_id, int connections, Timestamp begin, Timestamp end) {
        String q = "WITH RECURSIVE_LEG (DEPT_AP_ID, ARRI_AP_ID, LEG_ID, ORIG_DEP_TIME, CUR_DEP_TIME, ARR_TIME, LEG_COUNT, AP_ID_LIST, LEG_ID_LIST, FLIGHT_NUM_LIST) AS  " +
                "(SELECT B.DEPT_AP_ID, B.ARRI_AP_ID, A.LEG_ID, A.DEP_TIME, A.DEP_TIME, A.ARR_TIME,  " +
                 " 1, CAST(B.DEPT_AP_ID||' '||B.ARRI_AP_ID||' '  AS VARCHAR(300)), " +
                 " CAST(LEG_ID||' ' AS VARCHAR(300)), CAST(FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM LEG A NATURAL JOIN ROUTE B NATURAL JOIN FLIGHT G " +
                "WHERE B.DEPT_AP_ID =  " + dept_ap_id + 
                " UNION ALL " +
                "SELECT R.DEPT_AP_ID, D.ARRI_AP_ID, C.LEG_ID, R.ORIG_DEP_TIME, C.DEP_TIME, C.ARR_TIME,  " +
                "  R.LEG_COUNT + 1, CAST(R.AP_ID_LIST||' '||D.ARRI_AP_ID||' '  AS VARCHAR(300)),  " +
                "  CAST(R.LEG_ID_LIST||' '||C.LEG_ID||' ' AS VARCHAR(300)), CAST(R.FLIGHT_NUM_LIST||' '||F.FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM RECURSIVE_LEG R, (LEG C NATURAL JOIN ROUTE D NATURAL JOIN FLIGHT F) " +
                "WHERE R.ARRI_AP_ID = D.DEPT_AP_ID AND C.DEP_TIME > R.ARR_TIME + INTERVAL '40' MINUTE AND C.DEP_TIME < R.ARR_TIME + INTERVAL '1440' MINUTE ) " +
                "CYCLE ARRI_AP_ID SET CYCLIC TO '1' DEFAULT '0'  " +
                "SELECT DAP.NAME AS \"DEPT_AP_NAME\", AAP.NAME AS \"ARRI_AP_NAME\", R.DEPT_AP_ID, R.ARRI_AP_ID, R.ORIG_DEP_TIME AS \"DEP_TIME\", ARR_TIME, R.LEG_COUNT, R.LEG_ID_LIST, AP_ID_LIST, FLIGHT_NUM_LIST, CYCLIC " +
                 "     FROM RECURSIVE_LEG R JOIN AIRPORT DAP ON DEPT_AP_ID = DAP.AIRPORT_ID JOIN AIRPORT AAP ON ARRI_AP_ID = AAP.AIRPORT_ID " +
                 "     WHERE R.LEG_COUNT-1 < " + connections + " AND R.ORIG_DEP_TIME >= timestamp'" + begin.toString() + "' AND R.ORIG_DEP_TIME <= timestamp'" + end.toString() + "' AND CYCLIC = 0 " +
                "ORDER BY LEG_COUNT";
        
         try {
                Statement s = con.createStatement();
                ResultSet result = s.executeQuery(q);
                return result;
         } catch(Exception e) { e.printStackTrace(); }
         return null;
    }
    
    public ResultSet searchArrivingFlightsWithConnections(int arri_ap_id, int connections) {
        String q = "WITH RECURSIVE_LEG (DEPT_AP_ID, ARRI_AP_ID, LEG_ID, ORIG_ARR_TIME, CUR_ARR_TIME, DEP_TIME, LEG_COUNT, AP_ID_LIST, LEG_ID_LIST, FLIGHT_NUM_LIST) AS  " +
                "(SELECT B.DEPT_AP_ID, B.ARRI_AP_ID, A.LEG_ID, A.DEP_TIME, A.DEP_TIME, A.ARR_TIME,  " +
                 " 1, CAST(B.DEPT_AP_ID||' '||B.ARRI_AP_ID||' '  AS VARCHAR(300)), " +
                 " CAST(LEG_ID||' ' AS VARCHAR(300)), CAST(FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM LEG A NATURAL JOIN ROUTE B NATURAL JOIN FLIGHT G " +
                "WHERE B.ARRI_AP_ID =  " + arri_ap_id + 
                " UNION ALL " +
                "SELECT D.DEPT_AP_ID, R.ARRI_AP_ID, C.LEG_ID, R.ORIG_ARR_TIME, C.ARR_TIME, C.DEP_TIME,  " +
                "  R.LEG_COUNT + 1, CAST(R.AP_ID_LIST||' '||D.DEPT_AP_ID||' '  AS VARCHAR(300)),  " +
                "  CAST(R.LEG_ID_LIST||' '||C.LEG_ID||' ' AS VARCHAR(300)), CAST(R.FLIGHT_NUM_LIST||' '||F.FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM RECURSIVE_LEG R, (LEG C NATURAL JOIN ROUTE D NATURAL JOIN FLIGHT F) " +
                "WHERE R.DEPT_AP_ID = D.ARRI_AP_ID AND C.ARR_TIME < R.DEP_TIME - INTERVAL '40' MINUTE AND C.ARR_TIME > R.DEP_TIME - INTERVAL '1440' MINUTE ) " +
                "CYCLE ARRI_AP_ID SET CYCLIC TO '1' DEFAULT '0'  " +
                "SELECT DAP.NAME AS \"DEPT_AP_NAME\", AAP.NAME AS \"ARRI_AP_NAME\", R.DEPT_AP_ID, R.ARRI_AP_ID, R.ORIG_ARR_TIME AS \"ARR_TIME\", DEP_TIME, R.LEG_COUNT, R.LEG_ID_LIST, AP_ID_LIST, FLIGHT_NUM_LIST, CYCLIC " +
                 "     FROM RECURSIVE_LEG R JOIN AIRPORT DAP ON DEPT_AP_ID = DAP.AIRPORT_ID JOIN AIRPORT AAP ON ARRI_AP_ID = AAP.AIRPORT_ID " +
                 "     WHERE R.LEG_COUNT-1 < " + connections;
        
         try {
                Statement s = con.createStatement();
                ResultSet result = s.executeQuery(q);
                return result;
         } catch(Exception e) {e.printStackTrace();  }
         return null;
    }
    
    public ResultSet searchArrivingFlightsWithConnectionsByTime(int arri_ap_id, int connections, Timestamp begin, Timestamp end) {
        String q  = "WITH RECURSIVE_LEG (DEPT_AP_ID, ARRI_AP_ID, LEG_ID, ORIG_ARR_TIME, CUR_ARR_TIME, DEP_TIME, LEG_COUNT, AP_ID_LIST, LEG_ID_LIST, FLIGHT_NUM_LIST) AS  " +
                "(SELECT B.DEPT_AP_ID, B.ARRI_AP_ID, A.LEG_ID, A.DEP_TIME, A.DEP_TIME, A.ARR_TIME,  " +
                 " 1, CAST(B.DEPT_AP_ID||' '||B.ARRI_AP_ID||' '  AS VARCHAR(300)), " +
                 " CAST(LEG_ID||' ' AS VARCHAR(300)), CAST(FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM LEG A NATURAL JOIN ROUTE B NATURAL JOIN FLIGHT G " +
                "WHERE B.ARRI_AP_ID =  " + arri_ap_id + 
                " UNION ALL " +
                "SELECT D.DEPT_AP_ID, R.ARRI_AP_ID, C.LEG_ID, R.ORIG_ARR_TIME, C.ARR_TIME, C.DEP_TIME,  " +
                "  R.LEG_COUNT + 1, CAST(R.AP_ID_LIST||' '||D.DEPT_AP_ID||' '  AS VARCHAR(300)),  " +
                "  CAST(R.LEG_ID_LIST||' '||C.LEG_ID||' ' AS VARCHAR(300)), CAST(R.FLIGHT_NUM_LIST||' '||F.FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM RECURSIVE_LEG R, (LEG C NATURAL JOIN ROUTE D NATURAL JOIN FLIGHT F) " +
                "WHERE R.DEPT_AP_ID = D.ARRI_AP_ID AND C.ARR_TIME < R.DEP_TIME - INTERVAL '40' MINUTE AND C.ARR_TIME > R.DEP_TIME - INTERVAL '1440' MINUTE ) " +
                "CYCLE ARRI_AP_ID SET CYCLIC TO '1' DEFAULT '0'  " +
                "SELECT DAP.NAME AS \"DEPT_AP_NAME\", AAP.NAME AS \"ARRI_AP_NAME\", R.DEPT_AP_ID, R.ARRI_AP_ID, R.ORIG_ARR_TIME AS \"DEP_TIME\", DEP_TIME, R.LEG_COUNT, R.LEG_ID_LIST, AP_ID_LIST, FLIGHT_NUM_LIST, CYCLIC " +
                 "     FROM RECURSIVE_LEG R JOIN AIRPORT DAP ON DEPT_AP_ID = DAP.AIRPORT_ID JOIN AIRPORT AAP ON ARRI_AP_ID = AAP.AIRPORT_ID " +
                 "     WHERE R.LEG_COUNT-1 < " + connections +" AND R.ORIG_ARR_TIME >= timestamp'" + begin.toString() + "' AND R.ORIG_ARR_TIME <= timestamp'" + end.toString() + "'  AND CYCLIC = 0 " +
                          "ORDER BY LEG_COUNT";
        
         try {
                Statement s = con.createStatement();
                ResultSet result = s.executeQuery(q);
                return result;
         } catch(Exception e) { e.printStackTrace(); }
         return null;
    }
    
    
    
    public ResultSet searchDepartingAndArrivingFlightsWithConnectionsByTime(int dept_ap_id, int arri_ap_id, int connections, Timestamp begin, Timestamp end) {
        String q = "WITH RECURSIVE_LEG (DEPT_AP_ID, ARRI_AP_ID, LEG_ID, ORIG_DEP_TIME, CUR_DEP_TIME, ARR_TIME, LEG_COUNT, AP_ID_LIST, LEG_ID_LIST, FLIGHT_NUM_LIST) AS  " +
                "(SELECT B.DEPT_AP_ID, B.ARRI_AP_ID, A.LEG_ID, A.DEP_TIME, A.DEP_TIME, A.ARR_TIME,  " +
                 " 1, CAST(B.DEPT_AP_ID||' '||B.ARRI_AP_ID||' '  AS VARCHAR(300)), " +
                 " CAST(LEG_ID||' ' AS VARCHAR(300)), CAST(FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM LEG A NATURAL JOIN ROUTE B NATURAL JOIN FLIGHT G " +
                "WHERE B.DEPT_AP_ID =  " + dept_ap_id + 
                " UNION ALL " +
                "SELECT R.DEPT_AP_ID, D.ARRI_AP_ID, C.LEG_ID, R.ORIG_DEP_TIME, C.DEP_TIME, C.ARR_TIME,  " +
                "  R.LEG_COUNT + 1, CAST(R.AP_ID_LIST||' '||D.ARRI_AP_ID||' '  AS VARCHAR(300)),  " +
                "  CAST(R.LEG_ID_LIST||' '||C.LEG_ID||' ' AS VARCHAR(300)), CAST(R.FLIGHT_NUM_LIST||' '||F.FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM RECURSIVE_LEG R, (LEG C NATURAL JOIN ROUTE D NATURAL JOIN FLIGHT F) " +
                "WHERE R.ARRI_AP_ID = D.DEPT_AP_ID AND C.DEP_TIME > R.ARR_TIME + INTERVAL '40' MINUTE AND C.DEP_TIME < R.ARR_TIME + INTERVAL '1440' MINUTE ) " +
                "CYCLE ARRI_AP_ID SET CYCLIC TO '1' DEFAULT '0'  " +
                "SELECT DAP.NAME AS \"DEPT_AP_NAME\", AAP.NAME AS \"ARRI_AP_NAME\", R.DEPT_AP_ID, R.ARRI_AP_ID, R.ORIG_DEP_TIME AS \"DEP_TIME\", ARR_TIME, R.LEG_COUNT, R.LEG_ID_LIST, AP_ID_LIST, FLIGHT_NUM_LIST, CYCLIC " +
                 "     FROM RECURSIVE_LEG R JOIN AIRPORT DAP ON DEPT_AP_ID = DAP.AIRPORT_ID JOIN AIRPORT AAP ON ARRI_AP_ID = AAP.AIRPORT_ID " +
                 "     WHERE R.LEG_COUNT-1 < " + connections + " AND ARRI_AP_ID = " + arri_ap_id + " AND R.ORIG_DEP_TIME >= timestamp'" + begin.toString() + "' AND R.ORIG_DEP_TIME <= timestamp'" + end.toString() + "'  AND CYCLIC = 0 " +
                "ORDER BY LEG_COUNT";
        
         try {
                Statement s = con.createStatement();
                ResultSet result = s.executeQuery(q);
                return result;
         } catch(Exception e) { e.printStackTrace(); }
         return null;
    }
    
    public ResultSet getLegsById(String ids) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE " + ids;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    
    public ResultSet searchDepartingAndArrivingFlightsWithConnections(int dept_ap_id, int arri_ap_id, int connections) {
        String q =  "WITH RECURSIVE_LEG (DEPT_AP_ID, ARRI_AP_ID, LEG_ID, ORIG_DEP_TIME, CUR_DEP_TIME, ARR_TIME, LEG_COUNT, AP_ID_LIST, LEG_ID_LIST, FLIGHT_NUM_LIST) AS  " +
                "(SELECT B.DEPT_AP_ID, B.ARRI_AP_ID, A.LEG_ID, A.DEP_TIME, A.DEP_TIME, A.ARR_TIME,  " +
                 " 1, CAST(B.DEPT_AP_ID||' '||B.ARRI_AP_ID||' '  AS VARCHAR(300)), " +
                 " CAST(LEG_ID||' ' AS VARCHAR(300)), CAST(FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM LEG A NATURAL JOIN ROUTE B NATURAL JOIN FLIGHT G " +
                "WHERE B.DEPT_AP_ID =  " + dept_ap_id + 
                " UNION ALL " +
                "SELECT R.DEPT_AP_ID, D.ARRI_AP_ID, C.LEG_ID, R.ORIG_DEP_TIME, C.DEP_TIME, C.ARR_TIME,  " +
                "  R.LEG_COUNT + 1, CAST(R.AP_ID_LIST||' '||D.ARRI_AP_ID||' '  AS VARCHAR(300)),  " +
                "  CAST(R.LEG_ID_LIST||' '||C.LEG_ID||' ' AS VARCHAR(300)), CAST(R.FLIGHT_NUM_LIST||' '||F.FLIGHT_NUM||' ' AS VARCHAR(300)) " +
                "FROM RECURSIVE_LEG R, (LEG C NATURAL JOIN ROUTE D NATURAL JOIN FLIGHT F) " +
                "WHERE R.ARRI_AP_ID = D.DEPT_AP_ID AND C.DEP_TIME > R.ARR_TIME + INTERVAL '40' MINUTE AND C.DEP_TIME < R.ARR_TIME + INTERVAL '1440' MINUTE ) " +
                "CYCLE ARRI_AP_ID SET CYCLIC TO '1' DEFAULT '0'  " +
                "SELECT DAP.NAME AS \"DEPT_AP_NAME\", AAP.NAME AS \"ARRI_AP_NAME\", R.DEPT_AP_ID, R.ARRI_AP_ID, R.ORIG_DEP_TIME AS \"DEP_TIME\", ARR_TIME, R.LEG_COUNT, R.LEG_ID_LIST, AP_ID_LIST, FLIGHT_NUM_LIST, CYCLIC " +
                 "     FROM RECURSIVE_LEG R JOIN AIRPORT DAP ON DEPT_AP_ID = DAP.AIRPORT_ID JOIN AIRPORT AAP ON ARRI_AP_ID = AAP.AIRPORT_ID " +
                 "     WHERE R.LEG_COUNT-1 < " + connections + " AND ARRI_AP_ID = " + arri_ap_id + " ";
        
         try {
                Statement s = con.createStatement();
                ResultSet result = s.executeQuery(q);
                return result;
         } catch(Exception e) { e.printStackTrace();}
         return null;
    }
    
    
    
    public ResultSet searchFlights(int dept_ap_id, int arr_ap_id) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE DEPT_AP_ID = " + dept_ap_id + " AND ARRI_AP_ID = " + arr_ap_id + ") ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchFlightsPilot(int pilot_id) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE CAPTAIN_ID = " + pilot_id + " OR COPILOT = " + pilot_id + ") ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchBookings(int cust_id) {
        String q = "SELECT * FROM BOOKING NATURAL JOIN TICKET NATURAL JOIN LEG WHERE CUST_ID = " + cust_id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchDepartingFlights(int dept_ap_id) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE DEPT_AP_ID = " + dept_ap_id + ") ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    
    public int calcBookingMiles(int ticket_id) {
        String q = "SELECT * "
            + "FROM TICKET NATURAL JOIN LEG NATURAL JOIN ROUTE "
            + "WHERE TICKET_ID = " + ticket_id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(result.next()) return result.getInt("DISTANCE");
            else return 0;
        } catch(Exception e) { e.printStackTrace(); }
        return 0;
    }
    
    public boolean custAcctExists(String username) {
        String q = "SELECT * "
            + "FROM C_ACCT "
            + "WHERE USERNAME = '" + username + "'";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(result.next()) return true;
            else return false;
        } catch(Exception e) { e.printStackTrace(); }
        return true;
    }
    
    public int calcBookingPrice(int ticket_id, int bags) {
        int price;
        int miles = calcBookingMiles(ticket_id);
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
        return price;
    }
    
    public void updateFFMiles(int cust_id, int miles) {
        String u = "UPDATE CUSTOMER SET FF_MILES = FF_MILES + " + miles + " WHERE CUST_ID = " + cust_id;
        try {
            Statement s = con.createStatement();
            s.executeUpdate(u);
        } catch(Exception e) { e.printStackTrace(); }
    }
    
    public int getFFMiles(int cust_id) {
        String i = "SELECT FF_MILES FROM CUSTOMER  WHERE CUST_ID = " + cust_id;
        try {
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(i);
            r.next();
            return r.getInt("FF_MILES");
        } catch(Exception e) { e.printStackTrace(); }
        return 0;
    }
    
    public boolean addCreditCard(int cust_id, long CCN, String name, String expDate, int secCode, String type) {
        String i = "INSERT INTO CREDIT_CARD (CUST_ID,CCN,NAME,EXPIRES,SECURITY_CODE, TYPE) VALUES (" +
                cust_id + "," + CCN + ",'" + name + "','" + expDate + "'," + secCode + ",'" + type + "')";
        try {
            Statement s = con.createStatement();
            s.executeUpdate(i);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
            
    
    public void makeBooking(int ticket_id, int cust_id, int price, int ff_miles, int bags) {
        String i = "INSERT INTO BOOKING (TICKET_ID,CUST_ID,PRICE,FF_MILES,BAGS) VALUES (" +
                ticket_id + "," + cust_id + "," + price + "," + ff_miles + "," + bags + ")";
        try {
            Statement s = con.createStatement();
            s.executeUpdate(i);
        } catch(Exception e) { e.printStackTrace(); }
        
    }
    
    public boolean deleteBooking(int booking_id, int cust_id) {
        String u = "DELETE FROM BOOKING WHERE BOOKING_ID = " + booking_id;
        String i = "SELECT FF_MILES FROM BOOKING WHERE BOOKING_ID = " + booking_id;
        try {
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(i);
            int miles = 0;
            if(r.next()) {
                miles = r.getInt("FF_MILES");
            }
            s.executeUpdate(u);
            updateFFMiles(cust_id, miles);
            
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean makeCustomer(String username, String password, String name ) {
        String i = "INSERT INTO C_ACCT(USERNAME, PASSWORD) VALUES ('" + username + "','" + password + "')";
        try {
            Statement s = con.createStatement();
            s.executeUpdate(i);
        } catch(Exception e) { e.printStackTrace(); return false; }
        
        String q = "SELECT ACCT_ID FROM C_ACCT WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
        int acct_id = 0;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            result.next();
            acct_id = result.getInt("ACCT_ID");
        } catch(Exception e) { e.printStackTrace(); return false; }
        
        i = "INSERT INTO CUSTOMER (NAME,ACCT_ID, FF_MILES) VALUES ('" + name +  "'," + acct_id + ",0)";
        try {
            Statement s = con.createStatement();
            s.executeUpdate(i);
        } catch(Exception e) { e.printStackTrace(); return false; }
        
        return true;
        
    }
    
    public ResultSet searchCreditCards(int cust_id) {
        String q = "SELECT * "
            + "FROM CREDIT_CARD "
            + "WHERE CUST_ID = " + cust_id;
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    
    }
    
    public int getCCN(int cc_id) {
        String q = "SELECT CCN "
            + "FROM CREDIT_CARD "
            + "WHERE CC_ID = " + cc_id + ") ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            if(!result.next()) return -1;
            else return result.getInt("CCN");
        } catch(Exception e) { e.printStackTrace(); }
        return -1;
    
    }
    
    public ResultSet searchArrivingFlights(int dept_ap_id) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE DEPT_AP_ID = " + dept_ap_id + ") ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchDepartingAndArrivingFlights(int dept_ap_id, int arri_ap_id) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE DEPT_AP_ID = " + dept_ap_id + " AND ARRI_AP_ID = " + arri_ap_id + ") ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchDepartingFlightsByTime(int dept_ap_id, Timestamp begin, Timestamp end) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE DEPT_AP_ID = " + dept_ap_id + " AND DEPT_TIME >= timestamp'" + begin.toString() +"' AND DEPT_TIME <= timestamp'" + end.toString() +"'  ) ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchArrivingFlightsByTime(int arri_ap_id, Timestamp begin, Timestamp end) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE ARRI_AP_ID = " + arri_ap_id + " AND ARRI_TIME >= timestamp'" + begin.toString() +"' AND ARRI_TIME <= timestamp'" + end.toString() +"'  ) ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchDepartingAndArrivingFlightsByTime(int dept_ap_id, int arri_ap_id, Timestamp begin, Timestamp end) {
        String q = "SELECT * "
            + "FROM LEG NATURAL JOIN ROUTE NATURAL JOIN FLIGHT "
            + "WHERE ROUTE_ID IN "
            + "(SELECT ROUTE_ID "
            + "FROM ROUTE "
            + "WHERE DEPT_AP_ID = " + dept_ap_id + " AND ARRI_AP_ID = " + arri_ap_id + " AND ARRI_TIME >= timestamp'" + begin.toString() +"' AND ARRI_TIME <= timestamp'" + end.toString() +"'  ) ";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchTicketsByLeg(int leg_id, int sclass) {
        String q = "SELECT * "
            + "FROM TICKET NATURAL JOIN LEG "
            + "WHERE TICKET_ID NOT IN "
            + "(SELECT TICKET_ID "
            + "FROM TICKET NATURAL JOIN BOOKING)"
            + "AND LEG_ID = " + leg_id + " AND CLASS = " + sclass + " AND ROWNUM = 1";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public ResultSet searchTicketByLegs(ArrayList<Integer> legs, int sclass) {
        String q = "";
        for(int leg: legs) System.out.println(leg);
        for(int leg : legs) {
        q += "SELECT * "
            + "FROM TICKET NATURAL JOIN LEG "
            + "WHERE TICKET_ID NOT IN "
            + "(SELECT TICKET_ID "
            + "FROM TICKET NATURAL JOIN BOOKING) "
            + "AND CLASS = " + sclass + " AND LEG_ID = " + leg + " AND ROWNUM = 1 UNION ";
        }
        q += "SELECT * FROM TICKET NATURAL JOIN LEG WHERE ROWNUM = 2";
        try {
            Statement s = con.createStatement();
            ResultSet result = s.executeQuery(q);
            return result;
        } catch(Exception e) { e.printStackTrace(); }
        return null;
    }
    
    
    
    
    
    public void disConnect() {
        try {
                con.close();
        } catch(Exception e) { e.printStackTrace();}
    }
}
