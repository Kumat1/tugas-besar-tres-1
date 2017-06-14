package bioskop;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Dimension;

public class report implements Printable{

    JFrame frame;
    JTable tableView;
    String host;
    String user;
    String pass;
    int id;
    String kode_kursi;
    String studio;
    String jam;
    int harga;
    private Connection koneksi;





    public report() {



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
        x.koneksi();





        frame = new JFrame("Monthly Report");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}});
        int id_pes = booking.id_pesanan;
        System.out.print(id_pes+"asdasdsdadasd");
        String sql = "select * from pesanan where id_pesanan = '"+id_pes+"'";

        try {
            Statement st = koneksi.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //object dari resultset menampung hasilnya
            ResultSet rs = st.executeQuery(sql);
            System.out.println("id    name       password");
            System.out.println("-------------------------");


            //selama masih ada data (next)
            while (rs.next()) {
                id = rs.getInt(1);
                 kode_kursi = rs.getString(2);
                studio = rs.getString(3);
                jam = rs.getString(4);
                harga = rs.getInt(5);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        final String[] headers = {"ID PESANAN","KODE KURSI", "STUDIO", "JAM","HARGA"};
        final Object[][] data = {
                {id, kode_kursi, studio, jam, harga},

        };

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return headers.length; }
            public int getRowCount() { return data.length;}
            public Object getValueAt(int row, int col) {
                return data[row][col];}
            public String getColumnName(int column) {
                return headers[column];}
            public Class getColumnClass(int col) {
                return getValueAt(0,col).getClass();}
            public boolean isCellEditable(int row, int col) {
                return (col==1);}
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };

        tableView = new JTable(dataModel);
        JScrollPane scrollpane = new JScrollPane(tableView);

        scrollpane.setPreferredSize(new Dimension(500, 500));
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.CENTER,scrollpane);
        frame.pack();
        JButton printButton= new JButton();

        printButton.setText("print me!");

        frame.getContentPane().add(BorderLayout.SOUTH,printButton);

        // for faster printing turn double buffering off

        RepaintManager.currentManager(
                frame).setDoubleBufferingEnabled(false);

        printButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                PrinterJob pj=PrinterJob.getPrinterJob();
                pj.setPrintable(report.this);
                pj.printDialog();
                try{
                    pj.print();
                }catch (Exception PrintException) {}
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new report();
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

    public int print(Graphics g, PageFormat pageFormat,
                     int pageIndex) throws PrinterException {
        Graphics2D  g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        int fontHeight=g2.getFontMetrics().getHeight();
        int fontDesent=g2.getFontMetrics().getDescent();

        //leave room for page number
        double pageHeight = pageFormat.getImageableHeight()-fontHeight;
        double pageWidth = pageFormat.getImageableWidth();
        double tableWidth = (double) tableView.getColumnModel().getTotalColumnWidth();
        double scale = 1;
        if (tableWidth >= pageWidth) {
            scale =  pageWidth / tableWidth;
        }

        double headerHeightOnPage=
                tableView.getTableHeader().getHeight()*scale;
        double tableWidthOnPage=tableWidth*scale;

        double oneRowHeight=(tableView.getRowHeight()+
                tableView.getRowMargin())*scale;
        int numRowsOnAPage=
                (int)((pageHeight-headerHeightOnPage)/oneRowHeight);
        double pageHeightForTable=oneRowHeight*numRowsOnAPage;
        int totalNumPages= (int)Math.ceil((
                (double)tableView.getRowCount())/numRowsOnAPage);
        if(pageIndex>=totalNumPages) {
            return NO_SUCH_PAGE;
        }

        g2.translate(pageFormat.getImageableX(),
                pageFormat.getImageableY());
        g2.drawString("Page: "+(pageIndex+1),(int)pageWidth/2-35,
                (int)(pageHeight+fontHeight-fontDesent));//bottom center

        g2.translate(0f,headerHeightOnPage);
        g2.translate(0f,-pageIndex*pageHeightForTable);

        //If this piece of the table is smaller than the size available,
        //clip to the appropriate bounds.
        if (pageIndex + 1 == totalNumPages) {
            int lastRowPrinted = numRowsOnAPage * pageIndex;
            int numRowsLeft = tableView.getRowCount() - lastRowPrinted;
            g2.setClip(0, (int)(pageHeightForTable * pageIndex),
                    (int) Math.ceil(tableWidthOnPage),
                    (int) Math.ceil(oneRowHeight * numRowsLeft));
        }
        //else clip to the entire area available.
        else{
            g2.setClip(0, (int)(pageHeightForTable*pageIndex),
                    (int) Math.ceil(tableWidthOnPage),
                    (int) Math.ceil(pageHeightForTable));
        }

        g2.scale(scale,scale);
        tableView.paint(g2);
        g2.scale(1/scale,1/scale);
        g2.translate(0f,pageIndex*pageHeightForTable);
        g2.translate(0f, -headerHeightOnPage);
        g2.setClip(0, 0,(int) Math.ceil(tableWidthOnPage),
                (int)Math.ceil(headerHeightOnPage));
        g2.scale(scale,scale);
        tableView.getTableHeader().paint(g2);//paint header at top

        return Printable.PAGE_EXISTS;
    }
}