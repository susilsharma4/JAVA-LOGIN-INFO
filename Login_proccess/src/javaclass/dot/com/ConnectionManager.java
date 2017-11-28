package javaclass.dot.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
   static Connection conn;
  // static String url;
   
   public static Connection getConnection() {
	   try {
		//String url="jdbc:odbc:"+"data_sharma";
		Class.forName("com.jdbc.mysql.Driver");
		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/data_sharma","roo"," ");
			
		} catch (SQLException ex) {
			 //ex.printStackTrace();
			ex.printStackTrace();
		}
	  } 
	  catch (ClassNotFoundException e) {
	    System.out.println(e);
	}
	return conn;
	}
 }

