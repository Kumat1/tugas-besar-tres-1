package print;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


public class DisplayReport implements  ActionListener,Console{

    JTextField urlField;
    JEditorPane htmlPane;
    String initialURL;
    Class c = DisplayReport.class;
    URL ul;
    JButton jbPrint = new JButton("Print");
    JButton jbMail = new JButton("Mail Report");
    JButton jbExit = new JButton("Exit");
    JButton jbRefresh = new JButton("Refresh");
    Container cont;
    JScrollPane scrollPane;
    JFrame oneFrame = new JFrame();
    DisplayReport(){

    }
    public DisplayReport(String initialURL) {
        oneFrame.setTitle("Report");
        this.initialURL = initialURL;
        JPanel topPanel = new JPanel();
        JLabel label1;
        topPanel.setBackground(Color.lightGray);
        urlField = new JTextField(500);
        urlField.setText(initialURL);
        urlField.setText(initialURL);
        urlField.addActionListener(this);
        topPanel.add(jbPrint);
        label1 = new JLabel("ID");
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label1.setBounds(35, 95, 55, 20);
        topPanel.add(label1);
        //topPanel.add(jbMail);
        //topPanel.add(jbRefresh);
        topPanel.add(jbExit);
        jbExit.addActionListener(this);
        jbMail.addActionListener(this);
        jbPrint.addActionListener(this);
        jbRefresh.addActionListener(this);
        cont = oneFrame.getContentPane();
        cont.add(topPanel, BorderLayout.NORTH);
        doReport();
        Dimension screenSize = oneFrame.getToolkit().getScreenSize();
        int width = screenSize.width * 8 / 10;
        int height = screenSize.height * 8 / 10;
        //setBounds(width/8, height/8, width, height);
        oneFrame.setLocation(170,190);
        oneFrame.setIconImage(( new ImageIcon( "iIco.gif" ) ).getImage() );;
        oneFrame.setSize(600,380);
        oneFrame.setVisible(true);
    }

    void display(String args) {
        new DisplayReport(args);

    }

    void doReport(){
        try {
            htmlPane = new JEditorPane();
            htmlPane.setEditable(false);
            scrollPane = new JScrollPane(htmlPane);
            cont.add(scrollPane, BorderLayout.CENTER);
            ul= c.getResource(initialURL);
            htmlPane.setPage(ul);
        } catch(Exception ioe) {
            setConsole("Can't Create Report " + initialURL
                    + ": " + ioe);
        }

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==jbExit)
            oneFrame.dispose();

        // For Button Click to send Mail
        if (event.getSource()==jbMail){
        }

        //For Button Click to Print Document
        if (event.getSource()==jbPrint){
            cont.remove(scrollPane);
            Frame f = new Frame();
            f.add(htmlPane);
            PrintJob pjob =Toolkit.getDefaultToolkit().getPrintJob(f,"Printing Test",null);

            if (pjob==null){
                return;
            }

            Graphics pg = pjob.getGraphics();
            pjob.end();
            //pg.dispose();


        }

        if (event.getSource()==jbRefresh){
            try{
                oneFrame.repaint();
                doReport();
            }catch (Exception ex){
                //JOptionPane.showMessageDialog(oneFrame, ex, "Error",
                //                    JOptionPane.ERROR_MESSAGE);
                setConsole("Error: "+ex);
            }

        }


    }

    public void setConsole(String msg)
    {
        consoleField.append(msg);
        consoleField.append("\n");

    }

    private void warnUser(String message) {
        setConsole("Error: "+message);
        //JOptionPane.showMessageDialog(oneFrame, message, "Error",
        //                            JOptionPane.ERROR_MESSAGE);
    }
}