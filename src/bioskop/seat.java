package bioskop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class seat extends JFrame {
    public static String bangku;
    // Global Variables
    JFrame frame = new JFrame(); // Creates JFrame
    JLabel title;
    JButton l[][], m[][], r[][]; // Names grid of JButtons
    JPanel panel1, panel2, panel3;


    // Constructor
    public seat(){

        title = new JLabel("Cinema Booking");
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setLocation(12,5);
        title.setSize(600, 60);

        frame.setLayout(null); // Setting Grid Layout
        // panel1.setLayout(new GridLayout(seat,row));
        l = new JButton[4][4]; // Allocating Size of Grid
        panel1 = new JPanel(new GridLayout(4,4));
        panel1.setBackground(Color.black);
        panel1.setBounds(20, 70, 400, 140);
        for(int y = 0; y <4 ; y++){
            for(int x = 0; x < 4; x++){
                l[x][y] = new JButton("L" + y + x); // Creates New JButton
                // l[x][y].addActionListener(this);
                panel1.add(l[x][y]); //adds button to grid

            }
        }

        m = new JButton[4][2]; // Allocating Size of Grid
        panel2 = new JPanel(new GridLayout(2,4));
        panel2.setBackground(Color.black);
        panel2.setBounds(440, 140, 400, 70);
        for(int y = 0; y <2 ; y++){
            for(int x = 0; x < 4; x++){
                m[x][y] = new JButton("M" + y + x); // Creates New JButton
                panel2.add(m[x][y]); //adds button to grid
            }
        }

        r = new JButton[4][4]; // Allocating Size of Grid
        panel3 = new JPanel(new GridLayout(4,4));
        panel3.setBackground(Color.black);
        panel3.setBounds(850, 70, 400, 140);
        for(int y = 0; y <4 ; y++){
            for(int x = 0; x < 4; x++){
                r[x][y] = new JButton("R" + y + x); // Creates New JButton
                panel3.add(r[x][y]); //adds button to grid
            }
        }

        frame.add(title);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setPreferredSize(new Dimension(1300, 280));
        frame.setTitle("Cinema Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); //makes frame visible

        for(int y = 0; y <2 ; y++) {
            for (int x = 0; x < 4; x++) {
                String a = Integer.toString(x);
                String b = Integer.toString(y);
                m[x][y].addActionListener(new ActionListener(){


                    public void actionPerformed(ActionEvent e){
                        bangku = "M" + b + a;

                        new booking().setVisible(true);
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



                        frame.dispose();


                    }
                });
            }
        }
        for(int y = 0; y <4 ; y++){
            for(int x = 0; x < 4; x++){

                String a = Integer.toString(x);
                String b = Integer.toString(y);
                l[x][y].addActionListener(new ActionListener(){


                    public void actionPerformed(ActionEvent e){
                        bangku = "L" + b + a;


                        new booking().setVisible(true);
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


                        frame.dispose();





                        }


                });

                r[y][x].addActionListener(new ActionListener(){


                    public void actionPerformed(ActionEvent e){
                        bangku = "R" + b + a;
                        new booking().setVisible(true);
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


                        frame.dispose();


                    }
                });

            }
        }

    }




    // Main Class
    public static void main(String[] args) {
        new seat(); //makes new ButtonGrid with 2 parameters
    }
}
