package print;

import java.io.*;
import java.sql.*;
import com.mysql.jdbc.*;


// @ Author: Naveen Alok
// @ Date  : June 26, 2004
// @ File  : TestReport.java
// This program tests the iReport component.
// Feel free to mofiy this code.
// This example uses Mysql, optionally you can have any
// database with this tool.

class TestReport {
	private String host=null;
    private java.sql.Connection conn ;	
    private java.sql.Statement statement;
    private java.sql.ResultSet rs; 
    
    TestReport(){
    	try{	
    	
	    host = "jdbc:mysql://localhost/bioskopenak"; // your connection string goes here.
	    
	  	Class.forName("com.mysql.jdbc.Driver").newInstance(); // your driver.
       	conn = DriverManager.getConnection(host, "root", "");
    	statement = conn.createStatement(); 
    	rs = statement.executeQuery("SELECT * FROM pesanan"); // Your Query
    	String title[]={"Bill no.","Date","Article no","Quantity","Price","Total","Type","Comment"};  //Column Title [OPTIONAL]
    	
  	    // iReport code for passing a resultset and creating desired output
  	    // makeReportHtml() & displayHtml() methods used for creating report
  	    // and displaying in HTML format.
  	    // For constructor definations refer to help or java doc.
  	    
  	    iReport iR= new iReport(rs,title,"id_pesanan","studio","harga","It's Easy","Your totals","Comment");
  	    
  	    
  	    //iReport iR= new iReport(rs,"testXPnew","Testing iReport","Comment");
			iR.makeReportHtml();
			iR.displayHtml();
  	    //InsertData ob = new InsertData("rout");
  	    //.setTheLayout();
  	    
    	
    	
    	
    	conn.close();
    	rs.close();
    	}
    	catch(Exception ex){
    		System.out.println(ex);
    		
    	}
    }
    
    public static void main(String ars[])	{
    	new TestReport();
    }
    }