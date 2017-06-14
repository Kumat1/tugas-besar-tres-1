package print;

import javax.swing.*;
import java.sql.*;
import java.lang.*;
import java.io.*;

//*********************************************************
// @ Author: Naveen Alok
// @ Date  : June 26, 2004
// @ File  : iReport.java
// A reporting component, intelligent Report.
// Creates reports, using Result Sets passed as parameter.
//*********************************************************

class iReport implements Console{
	private java.sql.ResultSet rs1,rsx;
	private String columnTitle[];
	private String titleHead;
	private String titleSubHead;
	private String RptName;
	private String reportTitle;
	private String firstFooter;
	private String reportName;
	private String reportFooter;
	private Writer out;
	private int count;
	private int x,y;
	private JLabel label1;
// First constructor with parameters :
// ResultSet , Name of html file 
// Title of Report , Sub Title of Report
// Footer   
	iReport()
	{
		x=180;
		y=20;
	}
	
	iReport(java.sql.ResultSet rs,String reportName1,String reportTitle1,String reportFooter1){
	rs1 = rs;
	rsx = rs;
	titleHead = "";
	titleSubHead = "";
	columnTitle = new String[20];
	reportTitle = reportTitle1;
	firstFooter = "";
	reportName = reportName1;
	reportFooter = reportFooter1;
	}
	
	iReport(java.sql.ResultSet rs,String reportName1,String reportTitle1,String titleSubHead1,String reportFooter1) throws SQLException{
	rs1 = rs;
	rsx = rs;
	columnTitle = new String[20];
	titleHead = "";
	titleSubHead = titleSubHead1;
	reportTitle = reportTitle1;
	firstFooter = "";
	reportName = reportName1;
	reportFooter = reportFooter1;
	}
	
	iReport(java.sql.ResultSet rs,String columnTitle1[],String reportName1,String reportTitle1,String titleHead1,String titleSubHead1,String firstFooter1,String reportFooter1){
	rs1 = rs;
	rsx = rs;
	columnTitle = columnTitle1;
	titleHead = titleHead1;
	titleSubHead = titleSubHead1;
	reportTitle = reportTitle1;
	firstFooter = firstFooter1;
	reportName = reportName1;
	reportFooter = reportFooter1;
	}


	
	
	
// Second constructor with parameters :
// ResultSet  
// Name of html file , Title of Report 
// Sub Title of Report, Footer   

	void setAxis(int m,int n)
	{
		x=m;
		y=n;
	}
	
// Third constructor with parameters :
// ResultSet , Title for columns (optional)
// Name of html file , Report Heading at top (name of Organization etc.)
// Title of Report , Sub Title of Report
// First footer (use for totals etc), Second Footer use for Date etc.  	
	
	void setProperties(java.sql.ResultSet rs,String reportName1,String reportTitle1,String reportFooter1)
	{
	rs1 = rs;
	rsx = rs;
	titleHead = "";
	titleSubHead = "";
	columnTitle = new String[20];
	reportTitle = reportTitle1;
	firstFooter = "";
	reportName = reportName1;
	reportFooter = reportFooter1;
	}
	
	void makeReportHtml(){
	try {
  	  ResultSetMetaData md = rs1.getMetaData();
  	  count=8;
  	  count = md.getColumnCount();
      out  = new FileWriter(reportName+".html");
      out.write("<html><head><title>"+titleHead+"</title></head>");
      out.write("<table border=0 cellspacing=3 cellpadding=0 width=300><td>");
      out.write("<font face=Arial size=6><b>"+titleHead+"</b></font><br>");
      out.write("<font face=Arial size=3><b>"+titleSubHead+"</b><br><br>");
      out.write("<font face=Arial size=-1><b>"+reportTitle+"</b><br><br>");
      out.write("</td></table>");
      out.write("<center><table border=0 cellspacing=3 cellpadding=0 width=800><td>");
      out.write("<table border=0 cellspacing=3 cellpadding=3");
      out.write("<tr>");      
      if (columnTitle.equals(null) || columnTitle.length!=count){
      for(int i=1;i<=count;i++){
      	out.write(" <td width=85 valign=top backcolor=#606060>");
      	out.write("<center><font face=arial size=-1>asdasda<b>"+md.getColumnName(i)+"</b></center></font>");
      	out.write(" <o:p></o:p></span></b></td>");      	
      	
      }
      }
      else {
      	for(int i=1;i<=count;i++){
      	out.write(" <td width=85 valign=top backcolor=#606060>");
      	out.write("<center><font face=arial size=-1><b>"+columnTitle[i-1]+"colomntitle</b></center></font>");
      	out.write(" <o:p></o:p></span></b></td>");

      	
      }
      }
      }catch (Exception e) {
      	System.out.println("In makereport"+ e);
      
    }
    try{
      out.write("</tr>");
      while(rsx.next()){
      	out.write("<tr>");
      	for(int i=1;i<=count;i++){
      		out.write("<td>");
      		out.write("<font face=Verdana size=-1><center>"+rsx.getString(i)+"rsx</center></font>");
      		out.write("test</td>");
      		System.out.println("test</td>");
      	}
      	out.write("</tr>");
      	
      }
      out.write("</table>");
      out.write("</td></table>");
      out.write("</td></table>");
      out.write("</html>");
      out.flush();
    rs1.close();
    rsx.close();
    }
    catch (Exception ex) {
    	try{
              out.write("<h3>Error in processing!!</h3>");
              out.write(String.valueOf(ex));
           }catch (Exception en){
           }
    }
    finally{
    	try{
    	out.close();
    	
  	    }catch (Exception en){
           }
    }

}
void displayHtml(){
	String file =reportName+".html";
	new DisplayReport().display(file);
}


  
  public void setConsole(String msg)
	{
		consoleField.append(msg);
		consoleField.append("\n");
		
	}


}