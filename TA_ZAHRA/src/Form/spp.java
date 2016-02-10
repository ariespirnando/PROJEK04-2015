/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Aries
 */
public final class spp extends javax.swing.JInternalFrame {
    ResultSet r;
    Statement s;
    Connection c;
    private Object[][]dataSPP=null;
    private String[]label={"ID SPP","NIS","Nama","Tanggal","Jumlah Bulan","Total Bayar"};

    
    
    /**
     * Creates new form spp
     */
    public spp() {
        initComponents();
        bukakoneksi();
        bacaTable();
        
    }
    public JTextField GETextField(){
        return jtNIS;
    }
    public JTextField GETextField1(){
        return jtNama;
    }
    public JTextField GETextField2(){
        return jtkelas;
    }
    public void reset(){
       jtNIS.setText("");
       jtNama.setText("");
       //jtTanggal.setText("");
       jtkelas.setText("");
       jtNIS.requestFocus();
       
    }
    public void on(){
       jtNIS.setEnabled(true);
       jtNama.setEnabled(true);
       jtTanggal.setEnabled(true);
       jtkelas.setEnabled(true);
       jbSimpanspp.setEnabled(true);
       jButton1.setEnabled(true);
    }
    public void ontghn(){
       jttagi.setEnabled(true);
       jcbulan.setEnabled(true);
       jbsimpantghn.setEnabled(true);
       jbTambahtgn.setEnabled(true);
       jbCetak.setEnabled(true);
    }
    public void ofspp(){
       jtNIS.setEnabled(false);
       jtNama.setEnabled(false);
       jtTanggal.setEnabled(false);
       jtkelas.setEnabled(false);
       jbSimpanspp.setEnabled(false);
       jButton1.setEnabled(false);
       jbTambah1.setEnabled(false);
    }
    public void transk(){
        try {
            s=c.createStatement();
            String sql="SELECT id_transaksi FROM spp ORDER BY id_transaksi DESC";
            r = s.executeQuery(sql);
            if(r.next()){
            String id = r.getString("id_transaksi");
            int nomor= Integer.valueOf(id.substring(9,12));
                if(nomor<9){
                jLabel2.setText("TRX-SPP-000"+String.valueOf(nomor+1));
                }else if(nomor<99){
                jLabel2.setText("TRX-SPP-00"+String.valueOf(nomor+1));
                }else if(nomor<999){
                jLabel2.setText("TRX-SPP-0"+String.valueOf(nomor+1));
                }else{
                jLabel2.setText("TRX-SPP-"+String.valueOf(nomor+1));
                }
            }
            else{
                jLabel2.setText("TRX-SPP-0001");
            }
        } catch (Exception e) {
        }
    }
    public void bacaTable(){
        try 
       {
           s=c.createStatement();
           String sql = "SELECT s.id_transaksi, s.NIS, n.nama_siswa, s.tanggal, "
                   + "s.jumlahbln, s.totalbayar FROM spp AS s, siswa AS n WHERE "
                   + "s.NIS=n.NIS ORDER BY s.id_transaksi DESC";
           r=s.executeQuery(sql);
           ResultSetMetaData m=r.getMetaData();
           int kolom=m.getColumnCount();
           int baris=0;
           while(r.next())
           {
               baris = r.getRow();
           }
           dataSPP= new Object[baris][kolom];
           int x=0;
           r.beforeFirst();
           while(r.next())
           {
               dataSPP[x][0]=r.getString("id_transaksi");
               dataSPP[x][1]=r.getString("NIS");
               dataSPP[x][2]=r.getString("nama_siswa");
               dataSPP[x][3]=r.getString("tanggal");
               dataSPP[x][4]=r.getString("jumlahbln");
               dataSPP[x][5]=r.getString("totalbayar");
               
               
               x++;
           }
           jtbspp.setModel(new DefaultTableModel(dataSPP, label));
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void pencarian(){
       try 
       {
           s=c.createStatement();
           String sql = "SELECT s.id_transaksi, s.NIS, n.nama_siswa, s.tanggal, s.jumlahbln, "
                   + "s.totalbayar FROM spp AS s, siswa AS n "
                   + "WHERE s.NIS=n.NIS AND s.NIS LIKE '%"+jcari.getText()+"%'"
                   + "OR s.NIS=n.NIS AND n.nama_siswa LIKE  '%"+jcari.getText()+"%'"
                   + "OR s.id_transaksi LIKE  '%"+jcari.getText()+"%'"
                   + "AND s.NIS=n.NIS ORDER BY s.id_transaksi DESC";
                   
           r=s.executeQuery(sql);
           ResultSetMetaData m=r.getMetaData();
           int kolom=m.getColumnCount();
           int baris=0;
           while(r.next())
           {
               baris = r.getRow();
           }
           dataSPP= new Object[baris][kolom];
           int x=0;
           r.beforeFirst();
           while(r.next())
           {
               dataSPP[x][0]=r.getString("id_transaksi");
               dataSPP[x][1]=r.getString("NIS");
               dataSPP[x][2]=r.getString("nama_siswa");
               dataSPP[x][3]=r.getString("tanggal");
               dataSPP[x][4]=r.getString("jumlahbln");
               dataSPP[x][5]=r.getString("totalbayar");
               
               
               x++;
           }
           jtbspp.setModel(new DefaultTableModel(dataSPP, label));
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    private void simpantrs()
    {
        String tanggal ="YYYY-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(tanggal);
        String tampil = String.valueOf(format.format(jtTanggal.getDate()));
        try 
       {
           String sql = "INSERT INTO spp VALUES('"+jLabel2.getText()+"','"+jtNIS.getText()+"','"+tampil+"',0,0)";
           s.executeUpdate(sql);
           s.close();
           JOptionPane.showMessageDialog(null, "Tambahkan data Pembayaran");
       }
       catch (SQLException e) 
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    private void updatetrs(){
        try 
       {
           String sql = "UPDATE spp SET totalbayar='"+jttot.getText()+"', jumlahbln='"+jljumlah.getText()+"' WHERE id_transaksi='"+jLabel2.getText()+"'";
           s.executeUpdate(sql);
       }
       catch (SQLException e) 
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    private void simpantghn()
    {
        try 
       {
           String sql = "INSERT INTO trxdetail VALUES(null,'"+jLabel2.getText()+"','"+jttagi.getText()+"','"+jcbulan.getSelectedItem()+"')";
           s.executeUpdate(sql);
           s.close();
           JOptionPane.showMessageDialog(null, "Biaya sukses ditambahkan");
       }
       catch (SQLException e) 
       {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    private void setnilai(){
        try {
            String abg = "select * from kelas where namakelas ='"+jtkelas.getText()+"'";
            s =c.createStatement();
            r = s.executeQuery(abg);
            r.next();
            String nilai = String.valueOf(r.getInt("TghnSPP"));
            jttagi.setText(nilai);
            r.close();
        } catch (Exception e) {
        }
    }
     private void settable()
       {
       
       }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtNIS = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtTanggal = new com.toedter.calendar.JDateChooser();
        jbSimpanspp = new javax.swing.JButton();
        jtNama = new javax.swing.JTextField();
        jtkelas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbspp = new javax.swing.JTable();
        jcari = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Jbkeluar = new javax.swing.JButton();
        jbTambah1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jttagi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jbsimpantghn = new javax.swing.JButton();
        jbTambahtgn = new javax.swing.JButton();
        jbCetak = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jljumlah = new javax.swing.JLabel();
        jttot = new javax.swing.JLabel();
        jljumlah1 = new javax.swing.JLabel();
        jcbulan = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("APP :: SPP");

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TRS/SPP/-");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("NIS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, -1, -1));

        jtNIS.setEnabled(false);
        jPanel2.add(jtNIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 42, 150, -1));

        jButton1.setText("< >");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 60, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Kelas");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jtTanggal.setEnabled(false);
        jPanel2.add(jtTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jbSimpanspp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Floppy-Small-icon.png"))); // NOI18N
        jbSimpanspp.setText("Simpan");
        jbSimpanspp.setEnabled(false);
        jbSimpanspp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSimpansppActionPerformed(evt);
            }
        });
        jPanel2.add(jbSimpanspp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 220, 40));

        jtNama.setEnabled(false);
        jPanel2.add(jtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 219, -1));

        jtkelas.setEnabled(false);
        jPanel2.add(jtkelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 220, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tanggal Pembayaran");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Nama Siswa");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ID SPP");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 420, 210));

        jtbspp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbspp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbsppMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbspp);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText(" Cari ID SPP / Nis / Nama");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Zoom-icon.png"))); // NOI18N
        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jcari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 540, 440));

        Jbkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logout-icon.png"))); // NOI18N
        Jbkeluar.setText("Keluar");
        Jbkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbkeluarActionPerformed(evt);
            }
        });

        jbTambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Button-Add-icon.png"))); // NOI18N
        jbTambah1.setText("Tambah");
        jbTambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTambah1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbTambah1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Jbkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbTambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jbkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(316, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 440));

        jttagi.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Biaya Tagihan");

        jbsimpantghn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Floppy-Small-icon.png"))); // NOI18N
        jbsimpantghn.setText("Simpan");
        jbsimpantghn.setEnabled(false);
        jbsimpantghn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbsimpantghnActionPerformed(evt);
            }
        });

        jbTambahtgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Button-Add-icon.png"))); // NOI18N
        jbTambahtgn.setText("Tambah");
        jbTambahtgn.setEnabled(false);
        jbTambahtgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTambahtgnActionPerformed(evt);
            }
        });

        jbCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/printer-icon.png"))); // NOI18N
        jbCetak.setText("Cetak");
        jbCetak.setEnabled(false);
        jbCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCetakActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Ket : ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Jumlah Bulan :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Total Bayar    :");

        jljumlah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jljumlah.setText("0");

        jttot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jttot.setText("0");

        jljumlah1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jljumlah1.setText("Bulan");

        jcbulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..Bulan..", "January", "Februay", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        jcbulan.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Bulan");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jbTambahtgn)
                                .addGap(58, 58, 58)
                                .addComponent(jbsimpantghn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addComponent(jbCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jttagi, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jttot))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jljumlah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jljumlah1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jttagi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jljumlah)
                    .addComponent(jljumlah1)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jttot))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbTambahtgn)
                    .addComponent(jbsimpantghn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCetak))
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 420, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JbkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_JbkeluarActionPerformed
    private void bukakoneksi()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_zahra","root","");
            System.out.println("Koneksi Sukses");
        } 
        catch (Exception e) 
        {
            System.out.println("Koneksi Sukses");
        }
    }
    public void JumlahBulan(){
        try {
            String abg = "SELECT COUNT(harga) AS total FROM trxdetail WHERE id_transaksi ='"+jLabel2.getText()+"'";
            s =c.createStatement();
            r = s.executeQuery(abg);
            r.next();
            String nilai = String.valueOf(r.getInt("total"));
            jljumlah.setText(nilai);
            r.close();
        } catch (Exception e) {
        }
    }
    public void totalbayar(){
        try {
            String abg = "SELECT SUM(harga) AS total FROM trxdetail WHERE id_transaksi ='"+jLabel2.getText()+"'";
            s =c.createStatement();
            r = s.executeQuery(abg);
            r.next();
            String nilai = String.valueOf(r.getInt("total"));
            jttot.setText(nilai);
            r.close();
        } catch (Exception e) {
        }
    }
    private void jbTambahtgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTambahtgnActionPerformed
        // TODO add your handling code here:
        bacaTable();
        jbsimpantghn.setEnabled(true);
    }//GEN-LAST:event_jbTambahtgnActionPerformed

    private void jtbsppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbsppMouseClicked
        // TODO add your handling code here:
        settable();
    }//GEN-LAST:event_jtbsppMouseClicked

    private void jbsimpantghnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbsimpantghnActionPerformed
        // TODO add your handling code here:
        if("..Bulan..".equals(jcbulan.getSelectedItem())){
            JOptionPane.showMessageDialog(null,"Bulan masih Kosong");
        }else{
            simpantghn();
            setnilai();
            totalbayar();
            JumlahBulan();
            updatetrs();
            bacaTable();
            jbsimpantghn.setEnabled(false);
            jcbulan.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jbsimpantghnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cSiswa cs = new cSiswa();
        this.getParent().add(cs);
        cs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbSimpansppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSimpansppActionPerformed
        // TODO add your handling code here:
        if("".equals(jtNIS.getText())||"".equals(jtNama.getText())||"".equals(jtkelas.getText())){
            JOptionPane.showMessageDialog(null, "Data Masih Kosong");
        }
        else{
            simpantrs();
            ontghn();
            ofspp();
            bacaTable();
            jbTambah1.setEnabled(false);
            setnilai();
        }
    }//GEN-LAST:event_jbSimpansppActionPerformed

    private void jbTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTambah1ActionPerformed
        // TODO add your handling code here:
        transk();
        on();
        reset();
        bacaTable();
    }//GEN-LAST:event_jbTambah1ActionPerformed
    private static String path = System.getProperty("user.dir")+"/src/Laporan/";
    private void jbCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCetakActionPerformed
        // TODO add your handling code here:
            String filename = path+"LaporanTRX.jrxml";
            JasperReport jasrep;
            JasperPrint japri;
            JasperViewer javie = null;
            HashMap param = new HashMap(3);
            JasperDesign jasdes;
            try {
                param.put("Transaksi",jLabel2.getText());
                File report = new File(filename);
                jasdes = JRXmlLoader.load(report);
                jasrep = JasperCompileManager.compileReport(jasdes);
                japri = JasperFillManager.fillReport(jasrep,param,Komponen.koneksi.GetConnection());
                javie.viewReport(japri,false);
            } catch (Exception e) {
                System.out.print("gagal ngprint");
            }
        
        
        
       jttagi.setEnabled(false);
       jcbulan.setEnabled(false);
       jbsimpantghn.setEnabled(false);
       jbTambahtgn.setEnabled(false);
       jbCetak.setEnabled(false);
       jbTambah1.setEnabled(true);
    }//GEN-LAST:event_jbCetakActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if("".equals(jcari.getText())){
            bacaTable();
        }else{
            pencarian();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jbkeluar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCetak;
    private javax.swing.JButton jbSimpanspp;
    private javax.swing.JButton jbTambah1;
    private javax.swing.JButton jbTambahtgn;
    private javax.swing.JButton jbsimpantghn;
    private javax.swing.JTextField jcari;
    private javax.swing.JComboBox jcbulan;
    private javax.swing.JLabel jljumlah;
    private javax.swing.JLabel jljumlah1;
    private javax.swing.JTextField jtNIS;
    private javax.swing.JTextField jtNama;
    private com.toedter.calendar.JDateChooser jtTanggal;
    private javax.swing.JTable jtbspp;
    private javax.swing.JTextField jtkelas;
    private javax.swing.JTextField jttagi;
    private javax.swing.JLabel jttot;
    // End of variables declaration//GEN-END:variables
}
