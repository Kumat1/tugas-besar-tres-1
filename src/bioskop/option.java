package bioskop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener.*;

/**
 * Created by Taufik Kamil on 01/06/2017.
 */
public class option extends JFrame {
    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public option() {
        setTitle("Option");
        setBounds(20, 100, 200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        setLocationRelativeTo(null);


        button1 = new JButton("Booking");
        button1.setBounds(5, 65, 170, 30);
        panel.add(button1);

        button2 = new JButton("Unbooking");
        button2.setBounds(5, 100, 170, 30);
        panel.add(button2);

        button3 = new JButton("Back");
        button3.setBounds(5, 135, 170, 30);
        panel.add(button3);

        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new menuUtama().setVisible(true);
                dispose();
            }
        });
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new seat().setVisible(true);
                dispose();
            }
        });
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new seat().setVisible(true);
                dispose();
            }
        });
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                option form = new option();
                form.setVisible(true);
            }
        });

    };


}

