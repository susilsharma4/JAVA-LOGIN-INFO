package javaclass.dot.com;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;

public class UserDAO {
    static Connection conn=null;
	static ResultSet rs=null;
	
    public static UserBean login(UserBean bean) {
	   Statement stmt=null;
	   
	   String username=bean.getUsername();
	   String password=bean.getPassword();
	   
	   String searchQuery="select * from datasource where "
	   		+ "username='"+username+"' and password=" +password+"' ";
	   
 //    System.out.println("Your user name is"+username);
//	   System.out.println("Your password is"+password);
//	   System.out.println("Query:"+searchQuery);
	   
	   try {
		// currentCon=ConnectionManager.getConnection();
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/data_sharma", "root","");
		 Statement st=conn.createStatement();
		 rs=st.executeQuery(searchQuery);
		 boolean more=rs.next();
		 
		 if(!more) {
			 System.out.print("Sorry, you are not a registered user!please sign up first");
			 bean.setValid(false);
		 }
		 else if(more) {
			 String firstName=rs.getString("firstName");
			 String lastName=rs.getString("lastName");
			 
			 System.out.println("Welcome"+firstName);
			 bean.setFirstName(firstName);
			 bean.setLastName(lastName);
			 bean.setValid(true);
		 }		 
	 }
	     catch (Exception ex){
		   System.out.println("Log in failed:An Exception has occurred"+ex);
	 }
	 finally {
	   if(rs!=null) {
		  try {
			 rs.close(); 
		  }catch(Exception e) {
			  rs=null;
		  }
	   }
		  if(stmt !=null) {
			  try {
				stmt.close();
			} catch (Exception e) {
				 stmt=null;
			}
		  }
	   if(conn !=null) {
		  try {
			  conn.close();
		} catch (Exception e) {
	        
		}
		  conn=null;
	  } 
	}
	 return bean;
   }
}
