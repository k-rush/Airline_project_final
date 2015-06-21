/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airlineinterface;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kdr213
 */
public class AirlineInterface {
    public boolean isLogged;

    public static void main(String[] args) {
        System.out.println("Welcome to Rush Airlines!");
        DBController dbcon = new DBController();
        dbcon.connect();
        mainPrompt(dbcon);
    }
    
    public static void mainPrompt(DBController dbcon) {
        String response;
        System.out.println("What would you like to do?");
        System.out.println("Enter:\n"
                + "\tPress 1 for customer login.\n"
                + "\tPress 2 for manager login.\n"
                + "\tPress 3 for pilot login.\n"
                + "\tPress 5 to create new customer account.\n"
                + "\tPress 9 to quit.\nEnter selection: ");
        Scanner in = new Scanner(System.in);
        response = in.nextLine();
        while(!response.equals("1") && !response.equals("2") && !response.equals("3") && !response.equals("9") && !response.equals("5")) {
            System.out.println("You entered an invalid selection.");
            System.out.println("Enter:\n"
                + "\tPress 1 for customer login.\n"
                + "\tPress 2 for manager login.\n"
                + "\tPress 3 for pilot login.\n"
                + "\tPress 5 to create new customer account.\n"
                + "\tPress 9 to quit.\nEnter selection: ");
            response = in.nextLine();
        }
  
        if(response.equals("2")) managerLogin(dbcon);
        else if(response.equals("1")) customerLogin(dbcon);
        else if(response.equals("3")) pilotLogin(dbcon);
        else if(response.equals("5")) createNewAcct(dbcon);
        else if(response.equals("5")) logout(dbcon);
        
    }

    public static void customerLogin(DBController dbcon) {
        // SAMPLE: USERNAME: evililgirleg  PASSWORD:  nvKYc547
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter your username: ");
      String username = in.nextLine();
      System.out.println("Please enter your password: ");
      String password = in.nextLine();
      String input;
     
      
      int logged = dbcon.cLogon(username, password);
      
      
      if(logged == -1) {
          System.out.println("Invalid username.  Try Again? (Y/N): ");
          input = in.nextLine();
          input = input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Invalid username.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) customerLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == -2) {
          System.out.println("Invalid password.  Try Again? (Y/N): ");
          input = in.nextLine();
          input = input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Invalid password.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) customerLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == -3) {
          System.out.println("Something went wrong.  Try Again? (Y/N): ");
          input = in.nextLine();
          input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Something went wrong.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) customerLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == 1) {
          System.out.println("Login successful.  Loading profile...");
          customerProfile(dbcon.getCustId(username), dbcon);
          
      }
 
  
    }
    
    public static void managerLogin(DBController dbcon) {
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter your username: ");
      String username = in.nextLine();
      System.out.println("Please enter your password: ");
      String password = in.nextLine();
      String input;
     
      
      int logged = dbcon.eLogon(username, password);
      
      
      if(logged == -1) {
          System.out.println("Invalid username.  Try Again? (Y/N): ");
          input = in.nextLine();
          input = input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Invalid username.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) managerLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == -2) {
          System.out.println("Invalid password.  Try Again? (Y/N): ");
          input = in.nextLine();
          input = input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Invalid password.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) managerLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == -3) {
          System.out.println("Something went wrong.  Try Again? (Y/N): ");
          input = in.nextLine();
          input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Something went wrong.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) managerLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == 1) {
          System.out.println("Login successful.  Loading profile...");
          if(dbcon.getManId(username) == -1) {
              System.out.println("Pilot information not found.  Retry Login? (Y/N): ");
                input = in.nextLine();
                input = input.toUpperCase();
                while(!input.equals("Y") &&!input.equals("N")) {
                    System.out.println("You entered an invalid selection.");
                    System.out.println("Pilot information not found.  Retry Login? (Y/N): ");
                    input = in.nextLine();
                
                }
                if(input.equals("Y") ) pilotLogin(dbcon);
                else if (input.equals("N")) mainPrompt(dbcon);  
            }
          managerProfile(dbcon.getEmpIdMan(username), dbcon);
          
      }
    }
    
    public static void customerProfile(int cust_id, DBController dbcon) {
        String name = dbcon.getNameCust(cust_id);
        Scanner in = new Scanner(System.in);
        
        int input;
        String response;
        
        System.out.println("Welcome, " + name + " to your account prfile.");
        System.out.println("You have: " + dbcon.getFFMiles(cust_id) + " Frequent Flier Miles!");
        System.out.println("Enter a selection to do the following:");
        
        System.out.println("\tPress 1 to view and manage bookings.");
        System.out.println("\tPress 2 to search for flights to book.");
        //System.out.println("\tPress 3 to search for airports.");
      
        System.out.println("\tPress 9 to logout.");
        
        
        response = in.nextLine();
        
        while(!response.equals("1") && !response.equals("2") && !response.equals("3") && !response.equals("9") ) {
            System.out.println("Enter a selection to do the following:");
        
            System.out.println("\tPress 1 to view and manage bookings.");
            System.out.println("\tPress 2 to search for flights to book.");
            //System.out.println("\tPress 3 to search for airports.");
            System.out.println("\tPress 9 to logout.");
            response = in.nextLine();
            
        }
        
        if(response.equals("1")) {
            viewBookingsCust(cust_id, dbcon);
        }
        
        else if(response.equals("2")) {
            searchFlightsCust(cust_id, dbcon);
        }
        
        
        
        else if( response.equals("9")) {
            logout(dbcon);
        }

    }
    
    public static int viewBookingsCust(int cust_id, DBController dbcon) {
        Scanner in = new Scanner(System.in);
        ResultSet r = dbcon.searchBookings(cust_id);
        String result;
        ArrayList<Integer> bookings = viewBookings(r, dbcon);
        
        System.out.println("If you'd like to cancel a booking, please enter the BOOKING_ID found above.");
        System.out.println("To return to profile, please enter -1.");
        result = in.nextLine();
        
        while(!isInteger(result)) {
            r = dbcon.searchBookings(cust_id);
            viewBookings(r, dbcon);
            System.out.println("Invalid selection.  You enetered " + result + ".");
            System.out.println("If you'd like to cancel a booking, please enter the BOOKING_ID found above.");
            System.out.println("To return to profile, please enter -1.");
            result = in.nextLine();
        }
        
        while(!bookings.contains(Integer.parseInt(result)) && !result.equals("-1")) {
            r = dbcon.searchBookings(cust_id);
            viewBookings(r, dbcon);
            System.out.println("Invalid selection.  You enetered " + result + ".");
            System.out.println("If you'd like to cancel a booking, please enter the BOOKING_ID found above.");
            System.out.println("To return to profile, please enter -1.");
            result = in.nextLine();
            while(!isInteger(result)) {
                r = dbcon.searchBookings(cust_id);
                viewBookings(r, dbcon);
                System.out.println("Invalid selection.  You enetered " + result + ".");
                System.out.println("If you'd like to cancel a booking, please enter the BOOKING_ID found above.");
                System.out.println("To return to profile, please enter -1.");
                result = in.nextLine();
            }
        }
        
       
        if(result.equals("-1")) customerProfile(cust_id, dbcon);
        
        if(bookings.contains(Integer.parseInt(result)) )  {
            if(dbcon.deleteBooking(bookings.get(bookings.indexOf(Integer.parseInt(result))), cust_id)) {
                System.out.println("Booking Deleted Succesfully.");
            }
            else {
                System.out.println("Could not delete booking.");
            }
        }
    
    
    
        
        return 0;
    }
    
    
    public static void managerProfile(int man_id, DBController dbcon) {
        String name = dbcon.getNameMan(man_id);
        Scanner in = new Scanner(System.in);
        
        int input;
        String response;
        
        System.out.println("Welcome, " + name + " to your account prfile.");
        System.out.println("Enter a selection to do the following:");
        
        System.out.println("\tPress 1 to search for flights.");
        //System.out.println("\tPress 2 to search for airports.");
      
        System.out.println("\tPress 9 to logout.");
        
        
        response = in.nextLine();
        
        while(!response.equals("1") && !response.equals("2") && !response.equals("3") && !response.equals("9") ) {
            
            System.out.println("\tPress 1 to search for flights.");
            //System.out.println("\tPress 2 to search for airports.");

            System.out.println("\tPress 9 to logout.");
            response = in.nextLine();
            
        }
        
        if(response.equals("1")) {
            searchFlightsMan(man_id, dbcon);
        }
        
        else if( response.equals("9")) {
            logout(dbcon);
        }
        
    }
    
    public static void pilotProfile(int pilot_id, DBController dbcon) {
        String name = dbcon.getNamePilot(pilot_id);
        Scanner in = new Scanner(System.in);
        
        int input;
        String response;
        
        System.out.println("Welcome, " + name + " to your account prfile.");
        System.out.println("Enter a selection to do the following:");
        
        System.out.println("\tPress 1 to search for flights.");
        //System.out.println("\tPress 2 to search for airports.");
      
        System.out.println("\tPress 9 to logout.");
        
        
        response = in.nextLine();
        
        while(!response.equals("1") && !response.equals("2") && !response.equals("3") && !response.equals("9") ) {
            
            System.out.println("\tPress 1 to search for flights.");
            System.out.println("\tPress 2 to search for airports.");

            System.out.println("\tPress 9 to logout.");
            response = in.nextLine();
        }
        
        if(response.equals("1")) {
            searchFlightsPilot(pilot_id, dbcon);
        }
        
        else if(response.equals("2")) {
            //searchAirports(dbcon);
        }
        
        else if( response.equals("9")) {
            logout(dbcon);
        }
        
    }
    
    public static void logout(DBController dbcon) {
        System.out.println("GoodBye!");
        dbcon.disConnect();
        System.exit(0);
    }
    
    public static ArrayList<Integer> viewBookings(ResultSet bookings, DBController dbcon) {
        System.out.println("BOOKING_ID\t\tTICKET_ID\t\tLEG_ID\t\tSEAT\t\tBOARDING GROUP\t\tCLASS");
        ArrayList<Integer> book_ids = new ArrayList<Integer>();
        try {
            if(!bookings.next() ) 
                System.out.println("We're sorry, but you don't seem to have any bookings.");
            else {
                do { 
                book_ids.add(bookings.getInt("BOOKING_ID"));
                System.out.println(bookings.getInt("BOOKING_ID") + "\t\t" + 
                        bookings.getInt("TICKET_ID") + "\t\t" + 
                        bookings.getInt("LEG_ID") + "\t\t" + 
                        bookings.getInt("SEAT") + "\t\t" + 
                        bookings.getInt("BOARDING_GROUP") + "\t\t" + 
                        bookings.getInt("CLASS") + "\t\t" );
                        
                } while(bookings.next());
            }
        } catch (Exception e) { e.printStackTrace(); }
        return book_ids;
    }
    
    public static void searchAirports() {
        
    }
    
    
    public static void pilotLogin(DBController dbcon) {
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter your username: ");
      String username = in.nextLine();
      System.out.println("Please enter your password: ");
      String password = in.nextLine();
      String input;
     
      
      int logged = dbcon.eLogon(username, password);
      
      
      if(logged == -1) {
          System.out.println("Invalid username.  Try Again? (Y/N): ");
          input = in.nextLine();
          input = input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Invalid username.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) pilotLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == -2) {
          System.out.println("Invalid password.  Try Again? (Y/N): ");
          input = in.nextLine();
          input = input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Invalid password.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) pilotLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == -3) {
          System.out.println("Something went wrong.  Try Again? (Y/N): ");
          input = in.nextLine();
          input.toUpperCase();
          while(!input.equals("Y") &&!input.equals("N")) {
              System.out.println("You entered an invalid selection.");
              System.out.println("Something went wrong.  Try Again? (Y/N): ");
              input = in.nextLine();
          }
          if(input.equals("Y") ) pilotLogin(dbcon);
          else if (input.equals("N")) mainPrompt(dbcon);  
      }
      
      else if(logged == 1) {
          System.out.println("Login successful.  Loading profile...");
          if(dbcon.getPilotId(username) == -1) {
              System.out.println("Pilot information not found.  Retry Login? (Y/N): ");
                input = in.nextLine();
                input = input.toUpperCase();
                while(!input.equals("Y") &&!input.equals("N")) {
                    System.out.println("You entered an invalid selection.");
                    System.out.println("Pilot information not found.  Retry Login? (Y/N): ");
                    input = in.nextLine();
                
                }
                if(input.equals("Y") ) pilotLogin(dbcon);
                else if (input.equals("N")) mainPrompt(dbcon);  
            }
          }
          pilotProfile(dbcon.getCustId(username), dbcon);
          
      
    }
    
    public static void searchFlightsMan(int man_id, DBController dbcon) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        String response = "";
        System.out.println("Enter the maximum number of connections between flights (between 0 and 5, 0 for direct flights only: ");
        response = in.nextLine();
        while(!response.equals("0") && !response.equals("5") && !response.equals("1")&& !response.equals("2")&& !response.equals("3")&& !response.equals("4")) {
            System.out.println("Invalid input. Please enter a number between 0 and 5.");
            System.out.println("Enter the maximum number of connections between flights (between 0 and 5, 0 for direct flights only: ");
            response = in.nextLine();
        }
        int connects = Integer.parseInt(response);
        input = searchFlightsInput();
        
        HashMap<Integer, String> leg_id_list = null;
        ArrayList<Integer> leg_ids = new ArrayList<Integer>();
        ResultSet flights = null;
        
        if(input == 1) {
            flights = searchFlightsByDept(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 2) {
            flights = searchFlightsByArri(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 3) {
            flights = searchFlightsByDeptAndArri(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 4) {
            flights = searchFlightsByDeptAndDates(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 5) {
            flights = searchFlightsByArriAndDates(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        
        else if(input == 6) {
            flights = searchFlightsByDeptAndArriAndDates(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        
        else if(input == 9) {
            managerProfile(man_id, dbcon);
        }
        System.out.println("Press Enter to return to profile...");
        in.nextLine();
        managerProfile(man_id, dbcon);
    }
    
    
     public static void searchFlightsPilot(int pilot_id, DBController dbcon) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        String response = "";
        
        
        
        ResultSet flights = dbcon.searchFlightsPilot(pilot_id);
        
        displayFlightsPilot(flights, dbcon);
        
        
        System.out.println("Press Enter to return to profile...");
        in.nextLine();
        pilotProfile(pilot_id, dbcon);
    }
    
    public static void searchFlightsCust(int cust_id, DBController dbcon) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        String response = "";
        System.out.println("Enter the maximum number of connections between flights (between 0 and 5, 0 for direct flights only: ");
        response = in.nextLine();
        while(!response.equals("0") && !response.equals("5") && !response.equals("1")&& !response.equals("2")&& !response.equals("3")&& !response.equals("4")) {
            System.out.println("Invalid input. Please enter a number between 0 and 5.");
            System.out.println("Enter the maximum number of connections between flights (between 0 and 5, 0 for direct flights only: ");
            response = in.nextLine();
        }
        int connects = Integer.parseInt(response);
        input = searchFlightsInput();
        
        HashMap<Integer, String> leg_id_list = null;
        ArrayList<Integer> leg_ids = new ArrayList<Integer>();
        ResultSet flights = null;
        if(input == 1) {
            flights = searchFlightsByDept(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 2) {
            flights = searchFlightsByArri(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 3) {
            flights = searchFlightsByDeptAndArri(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 4) {
            flights = searchFlightsByDeptAndDates(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        else if(input == 5) {
            flights = searchFlightsByArriAndDates(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        
        else if(input == 6) {
            flights = searchFlightsByDeptAndArriAndDates(dbcon, connects);
            leg_id_list = displayFlights(flights, dbcon); 
        }
        
        else if(input == 9) {
            customerProfile(cust_id, dbcon);
        }
        
            
           
            System.out.println("To search for tickets on one of these flights, enter the number under the SELECT field of the corresponding flight from the table above.");
            System.out.println("To return to profile, please enter -1.");
            response = in.nextLine();
            
            while(!isInteger(response)) {
                displayFlights(flights, dbcon);
                System.out.println("Invalid selection.");
                System.out.println("To purchase a ticket on one of these flights, enter the number under the SELECT field of the corresponding flight from the table above.");
                System.out.println("To return to profile, please enter -1.");
                response = in.nextLine();
            }
            
            while(!leg_id_list.containsKey(Integer.parseInt(response)) && !response.equals("-1")) {
                displayFlights(flights, dbcon);
                System.out.println("Invalid selection.");
                System.out.println("To purchase a ticket on one of these flights, enter the number under the SELECT field of the corresponding flight from the table above.");
                System.out.println("To return to profile, please enter -1.");
                response = in.nextLine();
                while(!isInteger(response)) {
                    displayFlights(flights, dbcon);
                    System.out.println("Invalid selection.");
                    System.out.println("To purchase a ticket on one of these flights, enter the number under the SELECT field of the corresponding flight from the table above.");
                    System.out.println("To return to profile, please enter -1.");
                    response = in.nextLine();
                }
            }
            
            if(response.equals("-1")) customerProfile(cust_id, dbcon);
            int sclass = 1;
            
            if(leg_id_list.containsKey(Integer.parseInt(response))) {
                
                System.out.println("Would you like first or second class tickets? (Enter 1 for first class, 2 for second): ");
                sclass = Integer.parseInt(in.nextLine());
                while(sclass < 1 || sclass > 2) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    System.out.println("Would you like first or second class tickets? (Enter 1 for first class, 2 for second): ");
                    sclass = Integer.parseInt(in.nextLine());
                }
                leg_ids = parseLegIdList(leg_id_list.get(Integer.parseInt(response)));
                ResultSet tickets = null;
                
                
                    tickets = dbcon.searchTicketByLegs(leg_ids, sclass);
                
                ArrayList<Integer> ticket_ids = displayTickets(tickets);
                System.out.println("To purchase ticket(s), please press P now.");
                System.out.println("To return to profile, please enter -1.");
                response = in.nextLine();
                response = response.toUpperCase();
                while(!response.equals("P") && !response.equals("-1")) {
                    displayTickets(tickets);
                    System.out.println("Invalid selection.");
                System.out.println("To purchase ticket(s), please press P now.");
                    System.out.println("To return to profile, please enter -1.");
                    response = in.nextLine();
                response = response.toUpperCase();
                }
                
                if(response.equals("-1")) customerProfile(cust_id, dbcon);
                else if(response.equals("P")) {
                    purchaseTicket(cust_id, ticket_ids, dbcon);
                }
            }
        
    }
    
    public static ArrayList<Integer> parseLegIdList(String leg_id_list) {
        ArrayList<Integer> leg_ids = new ArrayList<Integer>();
        Scanner list = new Scanner(leg_id_list);
        String legQuery = "";
        while(list.hasNextInt()) {
            leg_ids.add(list.nextInt());
        }
        
        return leg_ids;
    }
    
    
    public static void purchaseTicket(int cust_id, ArrayList<Integer> ticket_ids, DBController dbcon) {
        Scanner in = new Scanner(System.in);
        String response;
        int bags;
        System.out.println("Please enter the number of bags you will be checking (0-4): ");
        bags = Integer.parseInt(in.nextLine());
        
        while(bags < 0 || bags > 5 ) {
            System.out.println("Invalid number of bags.");
            System.out.println("Please enter the number of bags (0-5): ");
            bags = Integer.parseInt(in.nextLine());
        }
        
        HashMap<Integer, Integer> tprices = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> tmiles = new HashMap<Integer, Integer>();
        int miles = 0;
        int price = 0;
        for(int ticket : ticket_ids) {
            price += dbcon.calcBookingPrice(ticket,bags);
            tprices.put(ticket, dbcon.calcBookingPrice(ticket,bags));
            miles += dbcon.calcBookingMiles(ticket);
            tmiles.put(ticket, dbcon.calcBookingMiles(ticket));
        }
        
        System.out.println("Total ticket price: " + price);
        System.out.println("Miles: " + miles);
        
        System.out.println("Please select a following payment method: ");
        System.out.println(" Press 1 to pay with a credit card on file.");
        System.out.println(" Press 2 to enter new credit card information.");
        System.out.println(" Press 9 to quit and return to profile.");
        int input = Integer.parseInt(in.nextLine());
        
        while(input != 1 && input != 2 && input != 9) {
            System.out.println("Invalid selection.");
            System.out.println(" Press 1 to pay with a credit card on file.");
            System.out.println(" Press 2 to enter new credit card information.");
            System.out.println(" Press 9 to quit and return to profile.");
            input = Integer.parseInt(in.nextLine());
        }
        int ccreturned = 0;
        if(input == 9) customerProfile(cust_id, dbcon);
        
        else if(input == 2) {
            
            ccreturned = createCreditCard(cust_id, dbcon);
            
            if(ccreturned > 0) {
                System.out.println("Total: $" + price + " charged to credit card. ");
                dbcon.updateFFMiles(cust_id, miles);
                for(int t : ticket_ids) {
                            dbcon.makeBooking(t, cust_id, tprices.get(t), tmiles.get(t), bags);
                            }
                
            }
            
            else {
                
                while (ccreturned <= 0) {
                    System.out.println("Failed to save credit card.");
                    System.out.println("Please select a following payment method: ");
                    System.out.println("\tPress 1 to pay with a credit card on file.");
                    System.out.println("\tPress 2 to enter new credit card information.");
                    System.out.println("\tPress 9 to quit and return to profile.");
                    input = Integer.parseInt(in.nextLine());

                    while(input != 1 && input != 2 && input != 9) {
                        System.out.println("Invalid selection.");
                        System.out.println("\tPress 1 to pay with a credit card on file.");
                        System.out.println("\tPress 2 to enter new credit card information.");
                        System.out.println("\tPress 9 to quit and return to profile.");
                        input = Integer.parseInt(in.nextLine());
                    }
                    
                    if(input == 9) customerProfile(cust_id, dbcon);
                    
                    else if(input == 2) {
                        ccreturned = createCreditCard(cust_id, dbcon);
                    }
                    
                    else if(input == 1) {
                        ResultSet ccs = dbcon.searchCreditCards(cust_id);
                        ArrayList<Integer> cc_ids= displayCreditCards(ccs);
                       

                        System.out.println("To purchase the ticket on one of these cards, enter the corresponding SELECT value from the table above.");
                        System.out.println("To return to profile, please enter -1.");
                        input = Integer.parseInt(in.nextLine());
                        if(input == -1) customerProfile(cust_id, dbcon);
                        while(!cc_ids.contains(input) && input != 9) {
                            displayCreditCards(ccs);
                            System.out.println("Invalid selection.");
                            System.out.println("To purchase the ticket on one of these cards, enter the corresponding SELECT value from the table above.");
                            System.out.println("To return to profile, please enter -1.");
                            input = Integer.parseInt(in.nextLine());
                            if(input == -1) customerProfile(cust_id, dbcon);
                        }
                        
                        
                        System.out.println("Credit card: " + dbcon.getCCN(input) + " will be charged $" + price);
                        System.out.println("Continue? (y/n) :");
                        
                        response = in.nextLine();
                        response = response.toUpperCase();
                        while(!response.equals("Y") && !response.equals("N")) {
                            System.out.println("Invalid selection.");
                            System.out.println("Credit card: " + dbcon.getCCN(input) + " will be charged $" + price);
                            System.out.println("Continue? (y/n) :");
                            response = in.nextLine();
                            response.toUpperCase();
                        }
                        
                        if(response.equals("Y")) {
                            ccreturned = 1;
                            
                        }
                        else {
                            
                            System.out.println("Sale Cancelled.");
                            customerProfile(cust_id,dbcon);
                        }
                    }
                }
                System.out.println("Total: $" + price + " charged to credit card. ");
                for(int t : ticket_ids) {
                            dbcon.makeBooking(t, cust_id, tprices.get(t), tmiles.get(t), bags);
                            }
            }

        }
        
        
        
        else if(input == 1) {
                        ResultSet ccs = dbcon.searchCreditCards(cust_id);
                        ArrayList<Integer> cc_ids= displayCreditCards(ccs);
                       

                        System.out.println("To purchase the ticket on one of these cards, enter the corresponding SELECT value from the table above.");
                        System.out.println("To return to profile, please enter 9.");
                        input = Integer.parseInt(in.nextLine());
                        if(input == 9) customerProfile(cust_id, dbcon);
                        while(!cc_ids.contains(input) && input != 9) {
                            displayCreditCards(ccs);
                            System.out.println("Invalid selection.");
                            System.out.println("To purchase the ticket on one of these cards, enter the corresponding SELECT value from the table above.");
                            System.out.println("To return to profile, please enter 9.");
                            input = Integer.parseInt(in.nextLine());
                            if(input == 9) customerProfile(cust_id, dbcon);
                        }
                        
                        
                        System.out.println("Credit card: " + dbcon.getCCN(input) + " will be charged $" + price);
                        System.out.println("Continue? (y/n) :");
                        
                        response = in.nextLine();
                        response.toUpperCase();
                        while(!response.equals("Y") && !response.equals("N")) {
                            System.out.println("Invalid selection.");
                            System.out.println("Credit card: " + dbcon.getCCN(input) + " will be charged $" + price);
                            System.out.println("Continue? (y/n) :");
                            response = in.nextLine();
                            response.toUpperCase();
                        }
                        
                        if(response.equals("Y")) {
                            System.out.println("Total: $" + price + " charged to credit card. ");
                for(int t : ticket_ids) {
                            dbcon.makeBooking(t, cust_id, tprices.get(t), tmiles.get(t), bags);
                            }
                        }
                        else {
                            System.out.println("Sale Cancelled.");
                            customerProfile(cust_id,dbcon);
                        }
                    }
    }
    
  
    
    public static int createCreditCard(int cust_id, DBController dbcon) {
        Scanner in = new Scanner(System.in);
        long CCN = 0;
        String CCNs = "";
        int input;
        int type = 1;
        String typeString;
        System.out.println("Please enter credit card type: ");
            System.out.println("\tPress 1 for Visa.");
            System.out.println("\tPress 2 for Mastercard.");
            System.out.println("\tPress 3 for Discover.");
            System.out.println("\tPress 8 to return to previous screen.");
            input = Integer.parseInt(in.nextLine());
            
        while(input != 1 && input != 2 && input != 3 && input != 8) {
            System.out.println("Invalid selection.");
            System.out.println("\tPress 1 for Visa.");
            System.out.println("\tPress 2 for Mastercard.");
            System.out.println("\tPress 3 for Discover.");
            System.out.println("\tPress 8 to return to previous screen.");
            input = Integer.parseInt(in.nextLine());
        }
            
        if(input == 8) { 
            System.out.println("User has cancelled creating a credit card.");
            return -1;
        }
        else type = input;

        switch(type) {
            case 1: typeString = "VISA"; break;
            case 2: typeString = "MASTERCARD"; break;
            case 3: typeString = "DISCOVER"; break;
            default: typeString = "VISA";
        }
        String response = "N";
        
        
        while(response.equals("N")) {

            System.out.println("Please enter your Credit Card Number (or press 8 to return to previous screen). ");
            System.out.println("FORMAT ################ (No spaces or other characters.)");

            CCNs = in.nextLine();
            CCNs.replaceAll("[^\\d.]", "");
            CCN = Long.parseLong(CCNs);
            if(CCN == 8) { System.out.println("User has cancelled creating a credit card.");
                           return -1;
            }

            System.out.println("Validating credit card number: " + CCNs);
            boolean test = luhnTest(CCNs);

            while(!test) {
                System.out.println("Invalid Credit Card Number.");
                System.out.println("Please enter your Credit Card Number (or press 8 to return to previous screen). ");
                System.out.println("FORMAT ################ (No spaces or other characters.");

                CCNs = in.nextLine();
                CCNs.replaceAll("[^\\d]", "");
                CCN = Integer.parseInt(CCNs);

                if(CCN == 8) {
                    System.out.println("User has cancelled creating a credit card.");
                    return -1;
                }

               

                if(response.equals("Y")) {
                    System.out.println("Validating credit card number: " + CCNs);
                    test = luhnTest(CCNs);
                }
            }
            System.out.println("Credit card number entered: " + CCN);
            System.out.println("Is this correct? (y/n) :");
            response = in.nextLine();
            response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Credit card number entered: " + CCN);
                System.out.println("Is this correct? (y/n) :");
                response = in.nextLine();
                response.toUpperCase();
            }
        }
        

        System.out.println("Credit Card Number Validated.");


        String name = "";
        response = "N";
        
        while(response.equals("N")) {
            System.out.println("Please enter your name as it appears on the card (or press 8 to return to previous screen).");

            name = in.nextLine();
            if(name.equals("8")) { 
                System.out.println("User has cancelled creating a credit card.");
                return -1;
            }


            System.out.println("Name entered: " + name);
            System.out.println("Is this correct? (y/n) :");
            response = in.nextLine();
            response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Name entered: " + name);
                System.out.println("Is this correct? (y/n) :");
                response = in.nextLine();
                response.toUpperCase();
            }
        }
        
        
        response = "N";
        String secString = "";
        while(response.equals("N")) {
            System.out.println("Please enter the card's 3 or 4 digit security code (or press 8 to return to previous screen):");
            secString = in.nextLine();
            
            if(secString.equals("8")) { 
                System.out.println("User has cancelled creating a credit card.");
                return -1;
            }
            
            while(!secString.matches("^[0-9][0-9][0-9]$")) {
                System.out.println("Invalid security code.");
                System.out.println("Please enter the card's 3 or 4 digit security code (or press 8 to return to previous screen):");
                secString = in.nextLine();
                
                if(secString.equals("8")) { 
                    System.out.println("User has cancelled creating a credit card.");
                    return -1;
                }
            }

            System.out.println("Security code entered: " + secString);
            System.out.println("Is this correct? (y/n) :");
            response = in.nextLine();
            response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Secuirty code entered: " + secString);
                System.out.println("Is this correct? (y/n) :");
                response = in.nextLine();
                response.toUpperCase();
            }
        }
            
        
        int sec = Integer.parseInt(secString);
        
        String expDateStr = "";
        response = "N";
        
        while(response.equals("N")) {
            System.out.println("Please enter the card's expiration date [MM/DD/YYYY] (or press 8 to return to previous screen):");
            expDateStr = in.nextLine();
            
            if(expDateStr.equals("8")) { 
                    System.out.println("User has cancelled creating a credit card.");
                    return -1;
                }
            
            while(!validateDate(expDateStr)) {
                System.out.println("Please enter the card's card's expiration date [MM/DD/YYYY] (or press 8 to return to previous screen):");
                expDateStr = in.nextLine();
                
                if(expDateStr.equals("8")) { 
                    System.out.println("User has cancelled creating a credit card.");
                    return -1;
                }
            }

            System.out.println("Expiration date entered: " + expDateStr);
                System.out.println("Is this correct? (y/n) :");
                response = in.nextLine();
                response.toUpperCase();
                while(!response.equals("Y") && !response.equals("N")) {
                    System.out.println("Invalid selection.");
                    System.out.println("Expiration date entered: " + expDateStr);
                    System.out.println("Is this correct? (y/n) :");
                    response = in.nextLine();
                    response.toUpperCase();
                }
        }
        
        
        System.out.println("Please review the following information before submitting.");
        System.out.println("NAME: " + name);
        System.out.println("CCN: " + CCNs);
        System.out.println("TYPE: " + typeString);
        System.out.println("SECURITY CODE: " + secString);
        System.out.println("EXPIRATION DATE: " + expDateStr);
        
        System.out.println("Is this information correct? (y/n) :");
        response = in.nextLine();
        response.toUpperCase();
        while(!response.equals("Y") && !response.equals("N")) {
            System.out.println("Invalid selection.");
            System.out.println("Is this information correct? (y/n) :");
            response = in.nextLine();
            response.toUpperCase();
        }
        
        if(response.equals("N")) {
            System.out.println("Credit card information not valid.  Would you like to:");
            System.out.println("\tPress 1 to start over.");
            System.out.println("\tPress 2 to return to previous screen.");
            System.out.println("\tPress 9 to return to profile.");
            
            while(input != 1 && input != 2 && input != 9) {
            System.out.println("Credit card information not valid.  Would you like to:");
            System.out.println("\tPress 1 to start over.");
            System.out.println("\tPress 2 to return to previous screen.");
            System.out.println("\tPress 9 to return to profile.");
            input = in.nextInt();
            }
        }
            
        if(input == 2) {
            System.out.println("User has cancelled creating a credit card.");
            return -1;
        }
        
        else if(input == 9) {
            System.out.println("User has cancelled creating a credit card.");
            customerProfile(cust_id,dbcon);
        }
        
        else if (input == 1) {
            System.out.println("Do you wish to save this credit card for future use? (y/n) :");
            response = in.nextLine();
            response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Do you wish to save this credit card for future use? (y/n) :");
                response = in.nextLine();
                response.toUpperCase();
            }

            if(response.equals("Y")) {
                if(dbcon.addCreditCard(cust_id, CCN, name, expDateStr, sec, typeString)) {
                    System.out.println("Credit Card saved.");
                    return 2;
                }
                else {
                    System.out.println("Error saving credit card.");
                    return -2;
                }
            }
            else {
                System.out.println("Credit card will not be saved.");
                return 1;
            }
        }
        return 0;
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    
    
    public static int createNewAcct(DBController dbcon) {
        Scanner in = new Scanner(System.in);
        
        
        String username = "";
        String password = "";
        
        String name = "";
        String response = "N";
        while(response.equals("N")) {
            System.out.println("Please enter your name (or press 8 to return to previous screen).");

            name = in.nextLine();
            if(name.equals("8")) { 
                System.out.println("User has cancelled creating account.");
                return -1;
            }
        
            
            System.out.println("Name entered: " + name);
            System.out.println("Is this correct? (Y/N) :");
            response = in.nextLine();
            response = response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Name entered: " + name);
                System.out.println("Is this correct? (Y/N) :");
                response = in.nextLine();
                response = response.toUpperCase();
            }
        }
        
        
        response = "N";
        boolean userExists = true;
        while(response.equals("N")) {
            
            while(userExists) {
                System.out.println("Please enter the username you wish to use (or press 8 to return to previous screen). ");
                System.out.println("(Alphanumeric and underscores only, between 4 and 20 characters):");

                username = in.nextLine();

                if(username.equals("8")) { 
                        System.out.println("User has cancelled creating aaccount.");
                        return -1;
                    }

                while(!username.matches("^[a-zA-Z0-9_]{4,20}$")) {
                    System.out.println("Invalid username.");
                    System.out.println("Please enter the username you wish to use (or press 8 to return to previous screen). ");
                    System.out.println("(Alphanumeric and underscores only, between 4 and 20 characters):");

                    username = in.nextLine();

                    if(username.equals("8")) { 
                        System.out.println("User has cancelled creating account.");
                        return -1;
                    }
                }

                userExists = dbcon.custAcctExists(username);
                if(userExists) System.out.println("Username " + username + " has already been taken.  Please try again.");
            }
            
            System.out.println("Username entered: " + username);
            System.out.println("Is this correct? (Y/N) :");
            response = in.nextLine();
            response = response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Username entered: " + username);
                System.out.println("Is this correct? (Y/N) :");
                response = in.nextLine();
                response = response.toUpperCase();
            }
            
        }
        
        response = "N";
        while(response.equals("N")) {
            System.out.println("Please enter a password (or press 8 to return to previous screen).");
            System.out.println("(Use any character, must be between 8 and 20 characters.):");
            password = in.nextLine();
            if(password.equals("8")) { 
                System.out.println("User has cancelled creating account.");
                return -1;
            }
            
            while(!username.matches("^.{4,8}$")) {
                    System.out.println("Invalid password.");
                    System.out.println("Please enter a password (or press 8 to return to previous screen).");
                    System.out.println("(Use any character, must be between 8 and 20 characters.):");

                    password = in.nextLine();

                    if(password.equals("8")) { 
                        System.out.println("User has cancelled creating a credit card.");
                        return -1;
                    }
                }
            
            
            System.out.println("Password entered: " + password);
            System.out.println("Is this correct? (Y/N) :");
            response = in.nextLine();
            response = response.toUpperCase();
            while(!response.equals("Y") && !response.equals("N")) {
                System.out.println("Invalid selection.");
                System.out.println("Password entered: " + password);
                System.out.println("Is this correct? (Y/N) :");
                response = in.nextLine();
                response = response.toUpperCase();
            }
        }
        
        
        
        System.out.println("Please review the following information before submitting.");
        System.out.println("NAME: " + name);
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);
        
        System.out.println("Save Account? (y/n) :");
        response = in.nextLine();
        response.toUpperCase();
        while(!response.equals("Y") && !response.equals("N")) {
            System.out.println("Invalid selection.");
            System.out.println("Save Account? (y/n) :");
            response = in.nextLine();
            response.toUpperCase();
        }
        
        
            
        if(response.equals("N")) {
            System.out.println("User has cancelled creating account.");
            return -1;
        }
       
        
        else if (response.equals("Y")) {
                if(dbcon.makeCustomer(username, password, name)) {
                    System.out.println("Account saved.");
                    return 2;
                }
                else {
                    System.out.println("Error saving account");
                    return -2;
                }
            }
        return 0;
 
        
    }
        
        
    
    
    
    /* luhnTest taken from the following website:
     * http://rosettacode.org/wiki/Luhn_test_of_credit_card_numbers#Java
     * Liscenced under the GNU Free Documentation License 1.2 */
    public static boolean luhnTest(String number){
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){
                s1 += digit;
            }else {
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
    
    public static int searchFlightsInput() {
        Scanner in = new Scanner(System.in);
        int input = 0;
        String response;
        System.out.println("Search for flights");

        System.out.println("Enter a selection to search using the following:");
        System.out.println("\tPress 1 to search by departing airport.");
        System.out.println("\tPress 2 to search by arriving airport.");
        System.out.println("\tPress 3 to search by departing and arriving airports.");
        System.out.println("\tPress 4 to search by departing airport and date range.");
        System.out.println("\tPress 5 to search by arriving airport and date range.");
        System.out.println("\tPress 6 to search by departing and departing airports and date range.");
        System.out.println("\tPress 9 to search by return to profile.");
        
        response = in.nextLine();
        while(!response.equals("1") && !response.equals("2") && !response.equals("3") && !response.equals("4") && !response.equals("5") && !response.equals("6") && !response.equals("9")) {
            System.out.println("You entered an invalid selection.");
            System.out.println("Enter a selection to search using the following:");
            System.out.println("\tPress 1 to search by departing airport.");
            System.out.println("\tPress 2 to search by arriving airport.");
            System.out.println("\tPress 3 to search by departing and arriving airports.");
            System.out.println("\tPress 4 to search by departing airport and date range.");
            System.out.println("\tPress 5 to search by arriving airport and date range.");
            System.out.println("\tPress 6 to search by departing and departing airports and date range.");
            System.out.println("\tPress 9 to return to profile.");
            input = in.nextInt();
        }
        return Integer.parseInt(response);
    }
    
   
    public static boolean validateDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date test;
        try {
            test = format.parse(date);
        } catch( java.text.ParseException e ) {
            System.out.println("Invalid Date Format.");
            return false;
            }
        
        if(!format.format(test).equals(date)) {
            System.out.println("Invalid Date.");
            return false;
        }
        return true;
    }
    
    public static ArrayList<Integer> displayCreditCards(ResultSet creditcards) {
        System.out.println("CREDIT CARD ID\t\tCCN\t\tNAME");
        ArrayList<Integer> cc_ids = new ArrayList<Integer>();
        try {
                if(!creditcards.next() ) {
                    System.out.println("No credit cards seem to be on file.");
                    return null;
                }
                else {
                    do { 
                        cc_ids.add(creditcards.getInt("CC_ID"));
                        System.out.println(
                            creditcards.getInt("CC_ID") + "\t\t" + 
                            creditcards.getDouble("CCN") + "\t\t" + 
                            creditcards.getInt("NAME"));
                    } while(creditcards.next());
            }
        } catch (Exception e) { e.printStackTrace(); }
        return cc_ids;
    }
   
    public static HashMap<Integer, String> displayFlights(ResultSet flights, DBController dbcon) {
        System.out.println("SELECT\t\t\tDEPARTING AIRPORT\t\tARRIVING AIRPORT\t\t\tCONNECTIONS\t\t\tDEPARTING TIME\t\t\tARRIVING_TIME");
        HashMap<Integer, String> leg_ids = new HashMap<Integer, String>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        int count = 1;
        try {
                if(!flights.next() ) 
                    System.out.println("No flights seem to match this search.");
                else {
                    do { 
                        leg_ids.put(count, flights.getString("LEG_ID_LIST"));
                        System.out.println(count+ "\t\t" + flights.getString("DEPT_AP_NAME") + "\t\t\t" + 
                                flights.getString("ARRI_AP_NAME") + "\t\t\t" + 
                                (flights.getInt("LEG_COUNT")-1) + "\t\t\t" +
                        format.format(flights.getTimestamp("DEP_TIME")) + "\t\t" + 
                        
                        format.format(flights.getTimestamp("ARR_TIME")));
                        count++;
                    } while(flights.next());
            }
        } catch (Exception e) { e.printStackTrace(); }
        return leg_ids;
    }
    
    public static HashMap<Integer, String> displayFlightsPilot(ResultSet flights, DBController dbcon) {
        System.out.println("LEG_ID\t\tDEPARTING AIRPORT\t\tARRIVING AIRPORT\t\tDEPARTING TIME\t\tARRIVING_TIME");
        HashMap<Integer, String> leg_ids = new HashMap<Integer, String>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        int count = 1;
        try {
                if(!flights.next() ) 
                    System.out.println("No flights seem to match this search.");
                else {
                    do { 
                        leg_ids.put(count, flights.getString("LEG_ID_LIST"));
                        System.out.println(dbcon.getAPNameById(flights.getInt("DEPT_AP_ID")) + "\t\t\t" + 
                                dbcon.getAPNameById(flights.getInt("ARRI_AP_ID")) + "\t\t\t" + 
                 
                        format.format(flights.getTimestamp("DEP_TIME")) + "\t\t" + 
                        
                        format.format(flights.getTimestamp("ARR_TIME")));
                        count++;
                    } while(flights.next());
            }
        } catch (Exception e) { e.printStackTrace(); }
        return leg_ids;
    }
    
    public static ArrayList<Integer> displayTickets(ResultSet tickets) {
        System.out.println("TICKET_ID\t\tSEAT NUM\t\tBOARDING GRP\t\tCLASS");
        ArrayList<Integer> ticket_ids = new ArrayList<Integer>();
        try {
             if(!tickets.next() ) 
                    System.out.println("No flights seem to match this search.");
                else {
                    do {
                ticket_ids.add(tickets.getInt("TICKET_ID"));
                System.out.println(tickets.getInt("TICKET_ID") + "\t\t" + 
                        tickets.getInt("SEAT") + "\t\t" + 
                        tickets.getInt("BOARDING_GROUP") + "\t\t" + 
                        tickets.getInt("CLASS") + "\t\t" );
                        
                } while(tickets.next());
             }
        } catch (Exception e) { e.printStackTrace(); }
        return ticket_ids;
    }
    
    public static ResultSet searchFlightsByDept(DBController dbcon, int connects) {
        Scanner in = new Scanner(System.in);
            System.out.println("Please enter departing airport's IATA/FAA code: ");
            String dept_ap_code = in.nextLine().toUpperCase();
           
            ResultSet r = dbcon.searchAirportsByCode(dept_ap_code);
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("y") && !inputS.equals("n")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByDept(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
                r = dbcon.searchDepartingFlightsWithConnections(r.getInt("AIRPORT_ID"), connects);
            }catch (Exception e) { e.printStackTrace(); }
            
            return r;
    }
    
    
    public static ResultSet searchFlightsByArri(DBController dbcon, int connects) {
        Scanner in = new Scanner(System.in);
            System.out.println("Please enter arriving airport's IATA/FAA code: ");
            String arri_ap_code = in.nextLine().toUpperCase();
           
            ResultSet r = dbcon.searchAirportsByCode(arri_ap_code);
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + arri_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("Y") && !inputS.equals("Y")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + arri_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByArri(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
                r = dbcon.searchArrivingFlightsWithConnections(r.getInt("AIRPORT_ID"), connects);
            }catch (Exception e) { 
                e.printStackTrace(); 
            }
            
            return r;
    }
    
    public static ResultSet searchFlightsByDeptAndArri(DBController dbcon, int connects) {
        Scanner in = new Scanner(System.in);
        int dept_ap_id = 0;
        int arri_ap_id = 0;
            System.out.println("Please enter departing airport's IATA/FAA code: ");
            String dept_ap_code = in.nextLine().toUpperCase();
           
            ResultSet r = dbcon.searchAirportsByCode(dept_ap_code);
            
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("Y") && !inputS.equals("N")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByDeptAndArri(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
                dept_ap_id = r.getInt("AIRPORT_ID");
                
            }catch (Exception e) { e.printStackTrace(); }
            
            System.out.println("Please enter arriving airport's IATA/FAA code: ");
            String arri_ap_code = in.nextLine().toUpperCase();
           
            r = dbcon.searchAirportsByCode(arri_ap_code);
            
            
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("Y") && !inputS.equals("N")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByDeptAndArri(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
                arri_ap_id = r.getInt("AIRPORT_ID");
                r = dbcon.searchDepartingAndArrivingFlightsWithConnections(dept_ap_id, arri_ap_id, connects);
            }catch (Exception e) { e.printStackTrace(); }
            
            return r;
    }
    
    public static ResultSet searchFlightsByDeptAndDates(DBController dbcon, int connects) {
        Scanner in = new Scanner(System.in);
            System.out.println("Please enter departing airport's IATA/FAA code  (or press 8 to return to previous screen): ");
            String dept_ap_code = in.nextLine().toUpperCase();
            int dept_ap_id = 0;
            if(dept_ap_code.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            ResultSet r = dbcon.searchAirportsByCode(dept_ap_code);
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("y") && !inputS.equals("n")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByDeptAndDates(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
               dept_ap_id = r.getInt("AIRPORT_ID");
            }catch (Exception e) { e.printStackTrace(); }
            
            
            System.out.println("Please enter the date to BEGIN your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
            String begin = in.nextLine();
            
            if(begin.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            while(!validateDate(begin)) {
                System.out.println("Invalid date.");
                System.out.println("Please enter the date to BEGIN your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
                begin = in.nextLine();
                
                if(begin.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            }
            
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date beginDate = null;
            try {
                beginDate = format.parse(begin);
            } catch( java.text.ParseException e ) {  }
            Timestamp beginStamp = new Timestamp(beginDate.getTime());
            
            
            System.out.println("Please enter the date to END your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
            String end = in.nextLine();
            
            if(end.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            while(!validateDate(end)) {
                System.out.println("Invalid date.");
                System.out.println("Please enter the date to begin your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
                end = in.nextLine();
                
                if(end.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            }
            
            java.util.Date endDate = null;
            try {
                endDate = format.parse(end);
            } catch( java.text.ParseException e ) {  }
            Timestamp endStamp = new Timestamp(endDate.getTime());
            
            
           
            
            try {
                
                r = dbcon.searchDepartingFlightsWithConnectionsByTime(r.getInt("AIRPORT_ID"), connects, beginStamp, endStamp);
            }catch (Exception e) { e.printStackTrace(); }
            
            return r;
    }
    
    
    public static ResultSet searchFlightsByArriAndDates(DBController dbcon, int connects) {
        Scanner in = new Scanner(System.in);
        int arri_ap_id = 0;
            System.out.println("Please enter arriving airport's IATA/FAA code  (or press 8 to return to previous screen): ");
            String arri_ap_code = in.nextLine().toUpperCase();
            
            if(arri_ap_code.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            ResultSet r = dbcon.searchAirportsByCode(arri_ap_code);
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + arri_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("y") && !inputS.equals("n")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + arri_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByArriAndDates(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
               arri_ap_id = r.getInt("AIRPORT_ID");
            }catch (Exception e) { e.printStackTrace(); }
            
            
            System.out.println("Please enter the date to BEGIN your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
            String begin = in.nextLine();
            
            if(begin.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            while(!validateDate(begin)) {
                System.out.println("Invalid date.");
                System.out.println("Please enter the date to BEGIN your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
                begin = in.nextLine();
                
                if(begin.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            }
            
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date beginDate = null;
            try {
                beginDate = format.parse(begin);
            } catch( java.text.ParseException e ) {  }
            Timestamp beginStamp = new Timestamp(beginDate.getTime());
            
            
            System.out.println("Please enter the date to END your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
            String end = in.nextLine();
            
            if(end.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            while(!validateDate(end)) {
                System.out.println("Invalid date.");
                System.out.println("Please enter the date to begin your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
                end = in.nextLine();
                
                if(end.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            }
            
            java.util.Date endDate = null;
            try {
                endDate = format.parse(end);
            } catch( java.text.ParseException e ) {  }
            Timestamp endStamp = new Timestamp(endDate.getTime());

            r = dbcon.searchArrivingFlightsWithConnectionsByTime(arri_ap_id, connects, beginStamp, endStamp);

            return r;
    }
    
    
    public static ResultSet searchFlightsByDeptAndArriAndDates(DBController dbcon, int connects) {
        Scanner in = new Scanner(System.in);
            System.out.println("Please enter departing airport's IATA/FAA code  (or press 8 to return to previous screen): ");
            String dept_ap_code = in.nextLine().toUpperCase();
            int dept_ap_id = 0;
            if(dept_ap_code.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            ResultSet r = dbcon.searchAirportsByCode(dept_ap_code);
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("y") && !inputS.equals("n")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + dept_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByDeptAndArriAndDates(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
               dept_ap_id = r.getInt("AIRPORT_ID");
            }catch (Exception e) { e.printStackTrace(); }
            
            int arri_ap_id = 0;
            System.out.println("Please enter arriving airport's IATA/FAA code  (or press 8 to return to previous screen): ");
            String arri_ap_code = in.nextLine().toUpperCase();
            
            if(arri_ap_code.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            r = dbcon.searchAirportsByCode(arri_ap_code);
            try {
                while(!r.next()) {
                    String inputS;
                    System.out.println("Airport code : " + arri_ap_code + " does not exist.  Try again? (y/n): ");
                    inputS = in.nextLine().toUpperCase();
                    while( !inputS.equals("y") && !inputS.equals("n")) {
                        System.out.println("You entered an invalid selection.");
                        System.out.println("Airport code : " + arri_ap_code + " does not exist.  Try again? (y/n): ");
                        inputS = in.nextLine();
                    }
                    if(inputS.equals("Y") ) return searchFlightsByArriAndDates(dbcon, connects);
                    else if (inputS.equals("N")) {
                        System.out.println("User has cancelled search.");
                        return null;
                    } 
                }
               arri_ap_id = r.getInt("AIRPORT_ID");
            }catch (Exception e) { e.printStackTrace(); }
            
            
            System.out.println("Please enter the date to BEGIN your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
            String begin = in.nextLine();
            
            if(begin.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            
            while(!validateDate(begin)) {
                System.out.println("Invalid date.");
                System.out.println("Please enter the date to BEGIN your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
                begin = in.nextLine();
                
                if(begin.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            }
            
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date beginDate = null;
            try {
                beginDate = format.parse(begin);
            } catch( java.text.ParseException e ) {  }
            Timestamp beginStamp = new Timestamp(beginDate.getTime());
            
            
            System.out.println("Please enter the date to END your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
            String end = in.nextLine();
            
            if(end.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            
            while(!validateDate(end)) {
                System.out.println("Invalid date.");
                System.out.println("Please enter the date to begin your search from [MM/DD/YYYY] (or press 8 to return to previous screen):");
                end = in.nextLine();
                
                if(end.equals("8")) { 
                    System.out.println("User has cancelled search.");
                    return null;
                }
            }
            
            java.util.Date endDate = null;
            try {
                endDate = format.parse(end);
            } catch( java.text.ParseException e ) {  }
            Timestamp endStamp = new Timestamp(endDate.getTime());
            
            
           
            
                r = dbcon.searchDepartingAndArrivingFlightsWithConnectionsByTime(dept_ap_id, arri_ap_id, connects, beginStamp, endStamp);
           
            
            return r;
    }
}
