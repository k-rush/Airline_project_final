import java.sql.*;

public class SimpleUpdate {
  public static void main(String[] args) {
    try {
      Connection con=null;
      Class.forName("oracle.jdbc.driver.OracleDriver");
      con=DriverManager.getConnection(
        "jdbc:oracle:thin:@edgar2.cse.lehigh.edu:1521:cse241","youruserid",
       "yourpassword");
      Statement s=con.createStatement();
      String q;
      ResultSet result;
      int i;
      q = "create table enemies2 (dog_name char(20), weight numeric(4,0))";
      i = s.executeUpdate(q);
      System.out.println ("value returned: " + i);
      s.close();
      con.close();
   } catch(Exception e){e.printStackTrace();}
 }
}