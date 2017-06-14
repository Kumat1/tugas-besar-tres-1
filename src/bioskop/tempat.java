package bioskop;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Taufik Kamil on 01/06/2017.
 */
public class tempat extends JFrame{

    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public tempat() {
        setTitle("PILIH");
        setBounds(40, 40, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        setLocationRelativeTo(null);


        button1 = new JButton("Studio 1");
        button1.setBounds(5, 65, 100, 30);
        panel.add(button1);

        button1 = new JButton("Studio 2");
        button1.setBounds(105, 65, 100, 30);
        panel.add(button1);

        button1 = new JButton("Studio 2");
        button1.setBounds(205, 65, 100, 30);
        panel.add(button1);

    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                menuUtama form = new menuUtama();

            }
        });

    };


}
