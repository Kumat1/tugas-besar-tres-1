package bioskop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener.*;

/**
 * Created by Taufik Kamil on 01/06/2017.
 */
public class menuUtama extends JFrame {
    public static String studio;
    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;//start

    public menuUtama() {

        setTitle("Bioskop");
        setBounds(40, 40, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        setLocationRelativeTo(null);

//        label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/1.jpg"))); // NOI18N
  //      label1.setText("jLabel1");


        button1 = new JButton("Studio 1");
        button1.setBounds(25, 405, 100, 30);
        panel.add(button1);

        button2 = new JButton("Studio 2");
        button2.setBounds(185, 405, 100, 30);
        panel.add(button2);

        button3 = new JButton("Studio 2");
        button3.setBounds(355, 405, 100, 30);
        panel.add(button3);

        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                studio = "Studio 1";


                new seat();
                dispose();
            }
            });
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                studio = "Studio 2";
                new seat();
                dispose();
            }
        });
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                studio = "Studio 3";
                new seat();
                dispose();
            }
        });
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                menuUtama form = new menuUtama();
                form.setVisible(true);
            }
        });

    };


}

