package TicTacToe;

import java.sql.*;

public class DB {
   // JDBC driver name and database URL 
   static final String JDBC_DRIVER = "org.h2.Driver";   
   static final String DB_URL = "jdbc:h2:~/test";
   
   //  Database credentials 
   static final String USER = "sa"; 
   static final String PASS = ""; 
   
   public void readResults() {
      Connection conn = null; 
      Statement stmt = null; 
      try { 
         // STEP 1: Register JDBC driver 
         Class.forName(JDBC_DRIVER); 
         
         // STEP 2: Open a connection 
         System.out.println("Connecting to database..."); 
         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
         
         // STEP 3: Execute a query 
         System.out.println("Connected database successfully..."); 
         stmt = conn.createStatement(); 
         String sql = "SELECT * FROM Tictactoe";
         ResultSet rs = stmt.executeQuery(sql);

         System.out.println();
         System.out.println("Result history");
         
         // STEP 4: Extract data from result set 
         while(rs.next()) { 
            // Retrieve by column name
            String firstPlayer = rs.getString("firstPlayer");
            String secondPlayer = rs.getString("secondPlayer");
            String winner = rs.getString("winner");

            
            // Display values
            System.out.print("Player 1: " + firstPlayer + "\t");
            System.out.print("Player 2: " + secondPlayer + "\t Winner: " + winner);
            System.out.println();

         } 
         // STEP 5: Clean-up environment 
         rs.close(); 
      } catch(SQLException se) { 
         // Handle errors for JDBC 
         se.printStackTrace(); 
      } catch(Exception e) { 
         // Handle errors for Class.forName 
         e.printStackTrace(); 
      } finally { 
         // finally block used to close resources 
         try { 
            if(stmt!=null) stmt.close();  
         } catch(SQLException se2) { 
         } // nothing we can do 
         try { 
            if(conn!=null) conn.close(); 
         } catch(SQLException se) { 
            se.printStackTrace(); 
         } // end finally try 
      } // end try
      System.out.println();
      System.out.println("Goodbye!"); 
   };

   public void insertResult(String playerOne, String playerTwo, String winner) {
      Connection conn = null;
      Statement stmt = null;
      try {
         // STEP 1: Register JDBC driver
         Class.forName(JDBC_DRIVER);

         // STEP 2: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);

         // STEP 3: Execute a query
         stmt = conn.createStatement();
         String sql = "INSERT INTO Tictactoe (firstPlayer, secondPlayer, winner) VALUES('"+ playerOne+"', '"+ playerTwo +"','" +winner + "');";

         stmt.executeUpdate(sql);
         System.out.println("Inserted records into the table...");

         // STEP 4: Closing connection
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         // Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         // Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         // finally block used to close resources
         try {
            if(stmt!=null) stmt.close();
         } catch(SQLException se2) {
         } // nothing we can do
         try {
            if(conn!=null) conn.close();
         } catch(SQLException se) {
            se.printStackTrace();
         } // end finally try
      } // end try
      System.out.println();
      System.out.println("Goodbye!");
   }
}