package print;

import javax.swing.*;
import java.awt.*;

interface Console 
{
	JTextArea consoleField = new JTextArea();
	JFrame comFrame = new JFrame("SQL Executer");
	JFrame winFrame = new JFrame("Results");
	Scrollbar scrollPane1 = new Scrollbar() ;

	public void setConsole(String st);
}