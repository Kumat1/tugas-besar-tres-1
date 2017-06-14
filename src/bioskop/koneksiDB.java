
package bioskop;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */

import java.sql.*;
public class koneksiDB {
    public Connection conn;
    public Statement st;
    public ResultSet rs;

    public void koneksi()   //fungsi koneksi
    {
        konek("localhost", "bioskopenak", "root","");  //memanggil fungsi konek() untuk melakukan koneksi
    }

    public void konek(String server, String db, String user, String passwd) //program untuk melakukan koneksi
    {
        System.out.println("Keterangan");

        try  //memanggil driver
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(
                    "Ada kesalahan " +
                            "Driver JDBC tidak berhasil Load");
            return;
        }

        System.out.println("Mysql JDBC Driver berhasil di Load");
        conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopenak","root","");
        }
        catch (SQLException e) {
            System.out.println("tidak bisa koneksi ke database");
            return;
        }

        if (conn != null)
        {
            System.out.println("berhasil koneksi!");
        }
        else
        {
            System.out.println("koneksi gagal");
        }
    }

}