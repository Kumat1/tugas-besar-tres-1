package bioskop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class booking extends JFrame{

        public static  int id_pesanan;
    public Connection conn;
    public Statement stat;
    public booking fAB = null;
    String host;
    String user;
    String pass;
    private Connection koneksi;
    private JPanel panel;
        private JLabel title;
        private JLabel label1;
        private JLabel label2;
        private JLabel label3;
        private JLabel label4;
        private JLabel label5;
        private TextField txt1;
        private  TextField txt2;
        private TextField txt3;
        private  TextField txt4;
    private  TextField txt5;
            private  JButton button1;
        private  JButton button2;
        private JButton button3;
    public booking(){

        host = "jdbc:mysql://localhost/bioskopenak"; //jdbc:mysql://localhost/databaseName
        user = "root"; // username in database server "root" is default
        pass = ""; // password in database server
        try {

            if (cekDriver()) { //jika cekDriver==true
                koneksi = DriverManager.getConnection(host, user, pass); /*object dari koneksi yang akan mengurusi koneksi ke database*/
                System.out.println("connection to database established");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed " + e.getMessage());
        }

        koneksiDB x;      //deklarasi kelas
        /**
         * Creates new form booking
         */

        x = new koneksiDB();
        x.koneksi();        //panggil koneksi

    setTitle("BOOKING");
    setBounds(200, 100, 400,450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();
    setContentPane(panel);
    panel.setLayout(null);
    setLocationRelativeTo(null);






        label5 = new JLabel("Id Pesanan");
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        label5.setBounds(250, 60, 55, 20);
        panel.add(label5);

    label1 = new JLabel("ID");
    label1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    label1.setBounds(35, 95, 55, 20);
    panel.add(label1);

    label2 = new JLabel("Studio");
    label2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    label2.setBounds(35, 130, 55, 20);
    panel.add(label2);

    label3 = new JLabel("Jam");
    label3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    label3.setBounds(35, 160, 55, 20);
    panel.add(label3);

    label4 = new JLabel("Harga");
    label4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    label4.setBounds(35, 190, 55, 20);
    panel.add(label4);


        txt5 = new TextField();
        txt5.setBounds(310, 60, 70, 24);
        panel.add(txt5);
    txt1 = new TextField();
    txt1.setBounds(105, 93, 200, 24);
    panel.add(txt1);
    txt1.setEditable(false);

    txt2 = new TextField("Studio");
    txt2.setBounds(105, 128, 200, 24);
    panel.add(txt2);
    txt2.setEditable(false);
    txt3 = new TextField();
    txt3.setBounds(105, 158, 200, 24);
    panel.add(txt3);

    txt4 = new TextField();
    txt4.setBounds(105, 188, 200, 24);
    panel.add(txt4);
    txt4.setEditable(false);
        if(menuUtama.studio == "Studio 1"){
            txt4.setText("35000");
        }
        else if(menuUtama.studio == "Studio 2"){
            txt4.setText("50000");
        }
        else if(menuUtama.studio == "Studio 1"){
            txt4.setText("100000");
        }
        else
            txt4.setText("20000");

        txt2.setText(menuUtama.studio);
        txt1.setText(seat.bangku);
    // Membuat Button di dalam panel ---------------------------->>
    button1 = new JButton("Proses");
    button1.setBounds(55, 245, 80, 30);
    panel.add(button1);


    button2 = new JButton("Batal");
    button2.setBounds(150, 245, 80, 30);
    panel.add(button2);

        button3 = new JButton("print");
        button3.setBounds(250, 245, 80, 30);
        panel.add(button3);


        button1.addActionListener(new ActionListener(){


            public void actionPerformed(ActionEvent e){
                 id_pesanan = Integer.parseInt(txt5.getText());
               String studio = txt2.getText();
                String kode_kursi = txt1.getText();
                String jam = txt3.getText();

                int harga = Integer.parseInt(txt4.getText());

                tambahPerson(id_pesanan, kode_kursi, studio, jam, harga);


            }
        });
        button3.addActionListener(new ActionListener(){


            public void actionPerformed(ActionEvent e){

                new report();
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                booking form = new booking();
                form.setVisible(true);
                form.cetakSemuaPerson();

            }
        });


    }

    public final boolean cekDriver() {
        boolean ada = false;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //di cek is driver exist ?
            System.out.println("Driver oke");
            ada = true;
        } catch (ClassNotFoundException c) {
            System.out.println("Driver tidak ada");
        }
        return ada; //kembalikan keterangan apakah driver ada atau tidak
    }

    public void cetakSemuaPerson() {
        String sql = "select * from pesanan";
        try {
            Statement st = koneksi.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //object dari resultset menampung hasilnya
            ResultSet rs = st.executeQuery(sql);
            System.out.println("id    name       password");
            System.out.println("-------------------------");

            //selama masih ada data (next)
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String pwd = rs.getString(3);
                System.out.printf("%-4d  %10s  %10s \n", id, name, pwd);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean tambahPerson(int id_pesanan, String kode_kursi, String studio, String time, int harga) {
        boolean result = false;
            System.out.println(time);
        System.out.println(kode_kursi);
        String sql = "insert into pesanan (id_pesanan,kode_kursi,studio,jam, harga) values('"+ id_pesanan+"','" + kode_kursi + "', '" + studio +"', '" + time +"', '" + harga +"')";
        try {
            Statement st = koneksi.createStatement();
            st.executeUpdate(sql);
            result = true;
            System.out.println("data berhasil dimasukkan");
        } catch (Exception e) {
            System.out.println("gagal insert data " + e.getMessage());
        }
        return result;
    }

    public void itemTerpilih(){



    }


}
