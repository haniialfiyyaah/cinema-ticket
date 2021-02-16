/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Tiket.PilihTiket;

import Class.Fungsi;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author dadan
 */
public class panelPilihKursi extends javax.swing.JPanel {

    /**
     * Creates new form panelPilihKursi
     */
    Fungsi fungsi = new Fungsi();
    int temp = 0;
    String judulFilm, studio,tanggal,jam,harga,namaKasir;
    ResultSet res = null;
    public static panelPilihKursi panelpilihkursi = new panelPilihKursi();
    public panelPilihKursi() {
        try {
            initComponents();
            getAkses();kursiSet();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void getAkses(){
        try {
            String data[] = new String[5];
            res =  koneksi.lihatData("SELECT * FROM `dummytikethelp`");
            while(res.next()){
                this.judulFilm = data[0] = res.getString(1);
                this.studio = data[1]= res.getString(2);
                this.tanggal = data[2]= res.getString(3);
                this.jam = data[3]= res.getString(4);
                this.harga = data[4]= res.getString(5);
                this.namaKasir = panelidentitas.LNamaKasir.getText();
            }
        } catch (SQLException ex) {
            Logger.getLogger(panelPilihKursi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setwarnaTB(JToggleButton JTB){
        //PLihatPesanan.setVisible(false);
        if(JTB.isSelected()){
            JTB.setBackground(Color.GREEN);
        }
        else{
            JTB.setBackground(Color.DARK_GRAY);
        }
    } 
    private void selectedJTB(JToggleButton JTB, String string){
        int i = 0;
        String kursi = "";
        if(JTB.isSelected()){
            if(temp>9){
                JTB.setSelected(false);
                setwarnaTB(JTB);
                JOptionPane.showMessageDialog(null, "BATAS MAKSIMAL PEMBELIAN TIKET ADALAH \n\n 10 TIKET PER TRANSAKSI");
                
            }else{
            if(LKursi.getText().equals(""))
                kursi = string;
            else{
                kursi = LKursi.getText()+", "+string;
            }
            LKursi.setText(kursi);
            this.temp = temp+1;
            LTiket.setText(String.valueOf(temp)+" TIKET");
            }
        }else{
            //kursi.concat(studio);
            kursi = LKursi.getText().replaceAll(string, "");
            kursi = kursi.replaceAll(", , ", ", ");
            i = kursi.length();
            if(i>2){
                String tmp = kursi.substring(i-2);
                if(tmp.equals(", ")){
                    kursi = kursi.substring(0,i-2);
                }
                String tmp2 = kursi.substring(0,2);
                if(tmp2.equals(", ")){
                    kursi = kursi.substring(2,i);
                }
            }
            LKursi.setText(kursi);
            this.temp = temp-1;
            LTiket.setText(String.valueOf(temp)+" TIKET");
        }
        int x = Integer.valueOf( fungsi.filter(LTiket.getText()));
        int y = Integer.valueOf(harga);
        int total = x*y;
        LHarga.setText("Rp. "+String.valueOf(total));
    }
    private void kursiSet() throws SQLException{
        kursiSedia("A1", A1);kursiSedia("A2", A2);kursiSedia("A3", A3);kursiSedia("A3", A3);kursiSedia("A4", A4);kursiSedia("A5", A5);kursiSedia("A6", A6);kursiSedia("A7", A7);
        kursiSedia("A8", A8);kursiSedia("A9", A9);kursiSedia("A10", A10);kursiSedia("A11", A11);kursiSedia("A12", A12);kursiSedia("A13", A13);
        kursiSedia("B1", B1);kursiSedia("B2", B2);kursiSedia("B3", B3);kursiSedia("B3", B3);kursiSedia("B4", B4);kursiSedia("B5", B5);kursiSedia("B6", B6);kursiSedia("B7", B7);
        kursiSedia("B8", B8);kursiSedia("B9", B9);kursiSedia("B10", B10);kursiSedia("B11", B11);kursiSedia("B12", B12);kursiSedia("B13", B13);
        kursiSedia("C1", C1);kursiSedia("C2", C2);kursiSedia("C3", C3);kursiSedia("C3", C3);kursiSedia("C4", C4);kursiSedia("C5", C5);kursiSedia("C6", C6);kursiSedia("C7", C7);
        kursiSedia("C8", C8);kursiSedia("C9", C9);kursiSedia("C10", C10);kursiSedia("C11", C11);kursiSedia("C12", C12);kursiSedia("C13", C13);
        kursiSedia("D1", D1);kursiSedia("D2", D2);kursiSedia("D3", D3);kursiSedia("D3", D3);kursiSedia("D4", D4);kursiSedia("D5", D5);kursiSedia("D6", D6);kursiSedia("D7", D7);
        kursiSedia("D8", D8);kursiSedia("D9", D9);kursiSedia("D10", D10);kursiSedia("D11", D11);kursiSedia("D12", D12);kursiSedia("D13", D13);
        kursiSedia("E1", E1);kursiSedia("E2", E2);kursiSedia("E3", E3);kursiSedia("E3", E3);kursiSedia("E4", E4);kursiSedia("E5", E5);kursiSedia("E6", E6);kursiSedia("E7", E7);
        kursiSedia("E8", E8);kursiSedia("E9", E9);kursiSedia("E10", E10);kursiSedia("E11", E11);kursiSedia("E12", E12);kursiSedia("E13", E13);
        kursiSedia("F1", F1);kursiSedia("F2", F2);kursiSedia("F3", F3);kursiSedia("F3", F3);kursiSedia("F4", F4);kursiSedia("F5", F5);kursiSedia("F6", F6);kursiSedia("F7", F7);
        kursiSedia("F8", F8);kursiSedia("F9", F9);kursiSedia("F10", F10);kursiSedia("F11", F11);kursiSedia("F12", F12);kursiSedia("F13", F13);
        kursiSedia("G1", G1);kursiSedia("G2", G2);kursiSedia("G3", G3);kursiSedia("G3", G3);kursiSedia("G4", G4);kursiSedia("G5", G5);kursiSedia("G6", G6);kursiSedia("G7", G7);
        kursiSedia("G8", G8);kursiSedia("G9", G9);kursiSedia("G10", G10);kursiSedia("G11", G11);kursiSedia("G12", G12);kursiSedia("G13", G13);
        kursiSedia("H1", H1);kursiSedia("H2", H2);kursiSedia("H3", H3);kursiSedia("H3", H3);kursiSedia("H4", H4);kursiSedia("H5", H5);kursiSedia("H6", H6);kursiSedia("H7", H7);
        kursiSedia("H8", H8);kursiSedia("H9", H9);kursiSedia("H10", H10);kursiSedia("H11", H11);kursiSedia("H12", H12);kursiSedia("H13", H13);
        kursiSedia("I1", I1);kursiSedia("I2", I2);kursiSedia("I3", I3);kursiSedia("I3", I3);kursiSedia("I4", I4);kursiSedia("I5", I5);kursiSedia("I6", I6);kursiSedia("I7", I7);
        kursiSedia("I8", I8);kursiSedia("I9", I9);kursiSedia("I10", I10);kursiSedia("I11", I11);kursiSedia("I12", I12);kursiSedia("J13", J13);
        kursiSedia("J1", J1);kursiSedia("J2", J2);kursiSedia("J3", J3);kursiSedia("J3", J3);kursiSedia("J4", J4);kursiSedia("J5", J5);kursiSedia("J6", J6);kursiSedia("J7", J7);
        kursiSedia("J8", J8);kursiSedia("J9", J9);kursiSedia("J10", J10);kursiSedia("J11", J11);kursiSedia("J12", J12);kursiSedia("J13", J13);
        kursiSedia("K1", K1);kursiSedia("K2", K2);kursiSedia("K3", K3);kursiSedia("K3", K3);kursiSedia("K4", K4);kursiSedia("K5", K5);kursiSedia("K6", K6);kursiSedia("K7", K7);
        kursiSedia("K8", K8);kursiSedia("K9", K9);kursiSedia("K10", K10);kursiSedia("K11", K11);kursiSedia("K12", K12);kursiSedia("K13", K13);
        
        
    }
    private void kursiSedia(String KursiSdia, JToggleButton JTB) throws SQLException{
        String SQL = "SELECT `Kursi` FROM `relasi_pesantiket.kursi` JOIN `relasi_pesantiket`  USING (ID_TIKET) WHERE `Studio`='"+studio+"' AND`Tanggal`='"+tanggal+"' AND `Jam`='"+jam+"'";
        ResultSet res =  koneksi.lihatData(SQL);
            while (res.next()){
                if(res.getString(1).equals(KursiSdia)){
                    JTB.setEnabled(false);
                    JTB.setBackground(Color.LIGHT_GRAY);
                }
            }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        LTiket = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        LHarga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LKursi = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BLihatPesanan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        J13 = new javax.swing.JToggleButton();
        J11 = new javax.swing.JToggleButton();
        J12 = new javax.swing.JToggleButton();
        J8 = new javax.swing.JToggleButton();
        J9 = new javax.swing.JToggleButton();
        J10 = new javax.swing.JToggleButton();
        J7 = new javax.swing.JToggleButton();
        J6 = new javax.swing.JToggleButton();
        J5 = new javax.swing.JToggleButton();
        J4 = new javax.swing.JToggleButton();
        J3 = new javax.swing.JToggleButton();
        J2 = new javax.swing.JToggleButton();
        J1 = new javax.swing.JToggleButton();
        I2 = new javax.swing.JToggleButton();
        I1 = new javax.swing.JToggleButton();
        I13 = new javax.swing.JToggleButton();
        I11 = new javax.swing.JToggleButton();
        I12 = new javax.swing.JToggleButton();
        I8 = new javax.swing.JToggleButton();
        I9 = new javax.swing.JToggleButton();
        I10 = new javax.swing.JToggleButton();
        I7 = new javax.swing.JToggleButton();
        I6 = new javax.swing.JToggleButton();
        I5 = new javax.swing.JToggleButton();
        I4 = new javax.swing.JToggleButton();
        I3 = new javax.swing.JToggleButton();
        H2 = new javax.swing.JToggleButton();
        H1 = new javax.swing.JToggleButton();
        G2 = new javax.swing.JToggleButton();
        G1 = new javax.swing.JToggleButton();
        G13 = new javax.swing.JToggleButton();
        G11 = new javax.swing.JToggleButton();
        G12 = new javax.swing.JToggleButton();
        G8 = new javax.swing.JToggleButton();
        G9 = new javax.swing.JToggleButton();
        G10 = new javax.swing.JToggleButton();
        G7 = new javax.swing.JToggleButton();
        G6 = new javax.swing.JToggleButton();
        G5 = new javax.swing.JToggleButton();
        G4 = new javax.swing.JToggleButton();
        G3 = new javax.swing.JToggleButton();
        H13 = new javax.swing.JToggleButton();
        H11 = new javax.swing.JToggleButton();
        H12 = new javax.swing.JToggleButton();
        H8 = new javax.swing.JToggleButton();
        H9 = new javax.swing.JToggleButton();
        H10 = new javax.swing.JToggleButton();
        H7 = new javax.swing.JToggleButton();
        H6 = new javax.swing.JToggleButton();
        H5 = new javax.swing.JToggleButton();
        H4 = new javax.swing.JToggleButton();
        H3 = new javax.swing.JToggleButton();
        F2 = new javax.swing.JToggleButton();
        F1 = new javax.swing.JToggleButton();
        F13 = new javax.swing.JToggleButton();
        F11 = new javax.swing.JToggleButton();
        F12 = new javax.swing.JToggleButton();
        F8 = new javax.swing.JToggleButton();
        F9 = new javax.swing.JToggleButton();
        F10 = new javax.swing.JToggleButton();
        F7 = new javax.swing.JToggleButton();
        F6 = new javax.swing.JToggleButton();
        F5 = new javax.swing.JToggleButton();
        F4 = new javax.swing.JToggleButton();
        F3 = new javax.swing.JToggleButton();
        E2 = new javax.swing.JToggleButton();
        E1 = new javax.swing.JToggleButton();
        E13 = new javax.swing.JToggleButton();
        E11 = new javax.swing.JToggleButton();
        E12 = new javax.swing.JToggleButton();
        E8 = new javax.swing.JToggleButton();
        E9 = new javax.swing.JToggleButton();
        E10 = new javax.swing.JToggleButton();
        E7 = new javax.swing.JToggleButton();
        E6 = new javax.swing.JToggleButton();
        E5 = new javax.swing.JToggleButton();
        E4 = new javax.swing.JToggleButton();
        E3 = new javax.swing.JToggleButton();
        B2 = new javax.swing.JToggleButton();
        B1 = new javax.swing.JToggleButton();
        B13 = new javax.swing.JToggleButton();
        B11 = new javax.swing.JToggleButton();
        B12 = new javax.swing.JToggleButton();
        B8 = new javax.swing.JToggleButton();
        B9 = new javax.swing.JToggleButton();
        B10 = new javax.swing.JToggleButton();
        B7 = new javax.swing.JToggleButton();
        B6 = new javax.swing.JToggleButton();
        B5 = new javax.swing.JToggleButton();
        B4 = new javax.swing.JToggleButton();
        B3 = new javax.swing.JToggleButton();
        A2 = new javax.swing.JToggleButton();
        A1 = new javax.swing.JToggleButton();
        A13 = new javax.swing.JToggleButton();
        A11 = new javax.swing.JToggleButton();
        A12 = new javax.swing.JToggleButton();
        A8 = new javax.swing.JToggleButton();
        A9 = new javax.swing.JToggleButton();
        A10 = new javax.swing.JToggleButton();
        A7 = new javax.swing.JToggleButton();
        A6 = new javax.swing.JToggleButton();
        A5 = new javax.swing.JToggleButton();
        A4 = new javax.swing.JToggleButton();
        A3 = new javax.swing.JToggleButton();
        D2 = new javax.swing.JToggleButton();
        D1 = new javax.swing.JToggleButton();
        C2 = new javax.swing.JToggleButton();
        C1 = new javax.swing.JToggleButton();
        C13 = new javax.swing.JToggleButton();
        C11 = new javax.swing.JToggleButton();
        C12 = new javax.swing.JToggleButton();
        C8 = new javax.swing.JToggleButton();
        C9 = new javax.swing.JToggleButton();
        C10 = new javax.swing.JToggleButton();
        C7 = new javax.swing.JToggleButton();
        C6 = new javax.swing.JToggleButton();
        C5 = new javax.swing.JToggleButton();
        C4 = new javax.swing.JToggleButton();
        C3 = new javax.swing.JToggleButton();
        D13 = new javax.swing.JToggleButton();
        D11 = new javax.swing.JToggleButton();
        D12 = new javax.swing.JToggleButton();
        D8 = new javax.swing.JToggleButton();
        D9 = new javax.swing.JToggleButton();
        D10 = new javax.swing.JToggleButton();
        D7 = new javax.swing.JToggleButton();
        D6 = new javax.swing.JToggleButton();
        D5 = new javax.swing.JToggleButton();
        D4 = new javax.swing.JToggleButton();
        D3 = new javax.swing.JToggleButton();
        K13 = new javax.swing.JToggleButton();
        K12 = new javax.swing.JToggleButton();
        K11 = new javax.swing.JToggleButton();
        K10 = new javax.swing.JToggleButton();
        K9 = new javax.swing.JToggleButton();
        K8 = new javax.swing.JToggleButton();
        K7 = new javax.swing.JToggleButton();
        K6 = new javax.swing.JToggleButton();
        K5 = new javax.swing.JToggleButton();
        K4 = new javax.swing.JToggleButton();
        K3 = new javax.swing.JToggleButton();
        K2 = new javax.swing.JToggleButton();
        K1 = new javax.swing.JToggleButton();

        setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/go-back-icon (1).png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(465, Short.MAX_VALUE))
        );

        add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel9.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("NOMOR KURSI");

        LTiket.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LTiket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LTiket.setText("0 TIKET");

        jPanel4.setBackground(java.awt.Color.lightGray);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel6.setBackground(java.awt.Color.green);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel4.setText("Tersedia");

        jPanel3.setBackground(java.awt.Color.darkGray);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel5.setText("Kursi Anda");

        LHarga.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LHarga.setForeground(java.awt.Color.red);
        LHarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LHarga.setText("Rp 0");

        jScrollPane1.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setBorder(null);

        LKursi.setEditable(false);
        LKursi.setBackground(new java.awt.Color(204, 255, 255));
        LKursi.setColumns(20);
        LKursi.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LKursi.setForeground(java.awt.Color.red);
        LKursi.setLineWrap(true);
        LKursi.setRows(1);
        LKursi.setTabSize(1);
        LKursi.setWrapStyleWord(true);
        LKursi.setBorder(null);
        LKursi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LKursi.setOpaque(false);
        jScrollPane1.setViewportView(LKursi);

        jLabel3.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel3.setText("Tidak Tersedia");

        jLabel10.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("KELAS");

        jLabel11.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Regular 2D");

        BLihatPesanan.setBackground(new java.awt.Color(51, 255, 102));
        BLihatPesanan.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        BLihatPesanan.setText("LIHAT PESANAN");
        BLihatPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLihatPesananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(5, 5, 5)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LTiket, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(BLihatPesanan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(LTiket)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LHarga)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BLihatPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("L A Y A R  F I L M");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        J13.setBackground(java.awt.Color.darkGray);
        J13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J13.setText("J13");
        J13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J13.setIconTextGap(0);
        J13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J13ActionPerformed(evt);
            }
        });

        J11.setBackground(java.awt.Color.darkGray);
        J11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J11.setText("J11");
        J11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J11.setIconTextGap(0);
        J11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J11ActionPerformed(evt);
            }
        });

        J12.setBackground(java.awt.Color.darkGray);
        J12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J12.setText("J12");
        J12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J12.setIconTextGap(0);
        J12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J12ActionPerformed(evt);
            }
        });

        J8.setBackground(java.awt.Color.darkGray);
        J8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J8.setText("J8");
        J8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J8.setIconTextGap(0);
        J8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J8ActionPerformed(evt);
            }
        });

        J9.setBackground(java.awt.Color.darkGray);
        J9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J9.setText("J9");
        J9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J9.setIconTextGap(0);
        J9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J9ActionPerformed(evt);
            }
        });

        J10.setBackground(java.awt.Color.darkGray);
        J10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J10.setText("J10");
        J10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J10.setIconTextGap(0);
        J10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J10ActionPerformed(evt);
            }
        });

        J7.setBackground(java.awt.Color.darkGray);
        J7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J7.setText("J7");
        J7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J7.setIconTextGap(0);
        J7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J7ActionPerformed(evt);
            }
        });

        J6.setBackground(java.awt.Color.darkGray);
        J6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J6.setText("J6");
        J6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J6.setIconTextGap(0);
        J6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J6ActionPerformed(evt);
            }
        });

        J5.setBackground(java.awt.Color.darkGray);
        J5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J5.setText("J5");
        J5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J5.setIconTextGap(0);
        J5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J5ActionPerformed(evt);
            }
        });

        J4.setBackground(java.awt.Color.darkGray);
        J4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J4.setText("J4");
        J4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J4.setIconTextGap(0);
        J4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J4ActionPerformed(evt);
            }
        });

        J3.setBackground(java.awt.Color.darkGray);
        J3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J3.setText("J3");
        J3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J3.setIconTextGap(0);
        J3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J3ActionPerformed(evt);
            }
        });

        J2.setBackground(java.awt.Color.darkGray);
        J2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J2.setText("J2");
        J2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J2.setIconTextGap(0);
        J2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J2ActionPerformed(evt);
            }
        });

        J1.setBackground(java.awt.Color.darkGray);
        J1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        J1.setText("J1");
        J1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        J1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        J1.setIconTextGap(0);
        J1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        J1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J1ActionPerformed(evt);
            }
        });

        I2.setBackground(java.awt.Color.darkGray);
        I2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I2.setText("I2");
        I2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I2.setIconTextGap(0);
        I2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I2ActionPerformed(evt);
            }
        });

        I1.setBackground(java.awt.Color.darkGray);
        I1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I1.setText("I1");
        I1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I1.setIconTextGap(0);
        I1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I1ActionPerformed(evt);
            }
        });

        I13.setBackground(java.awt.Color.darkGray);
        I13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I13.setText("I13");
        I13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I13.setIconTextGap(0);
        I13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I13ActionPerformed(evt);
            }
        });

        I11.setBackground(java.awt.Color.darkGray);
        I11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I11.setText("I11");
        I11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I11.setIconTextGap(0);
        I11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I11ActionPerformed(evt);
            }
        });

        I12.setBackground(java.awt.Color.darkGray);
        I12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I12.setText("I12");
        I12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I12.setIconTextGap(0);
        I12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I12ActionPerformed(evt);
            }
        });

        I8.setBackground(java.awt.Color.darkGray);
        I8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I8.setText("I8");
        I8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I8.setIconTextGap(0);
        I8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I8ActionPerformed(evt);
            }
        });

        I9.setBackground(java.awt.Color.darkGray);
        I9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I9.setText("I9");
        I9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I9.setIconTextGap(0);
        I9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I9ActionPerformed(evt);
            }
        });

        I10.setBackground(java.awt.Color.darkGray);
        I10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I10.setText("I10");
        I10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I10.setIconTextGap(0);
        I10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I10ActionPerformed(evt);
            }
        });

        I7.setBackground(java.awt.Color.darkGray);
        I7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I7.setText("I7");
        I7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I7.setIconTextGap(0);
        I7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I7ActionPerformed(evt);
            }
        });

        I6.setBackground(java.awt.Color.darkGray);
        I6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I6.setText("I6");
        I6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I6.setIconTextGap(0);
        I6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I6ActionPerformed(evt);
            }
        });

        I5.setBackground(java.awt.Color.darkGray);
        I5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I5.setText("I5");
        I5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I5.setIconTextGap(0);
        I5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I5ActionPerformed(evt);
            }
        });

        I4.setBackground(java.awt.Color.darkGray);
        I4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I4.setText("I4");
        I4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I4.setIconTextGap(0);
        I4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I4ActionPerformed(evt);
            }
        });

        I3.setBackground(java.awt.Color.darkGray);
        I3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        I3.setText("I3");
        I3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        I3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        I3.setIconTextGap(0);
        I3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        I3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I3ActionPerformed(evt);
            }
        });

        H2.setBackground(java.awt.Color.darkGray);
        H2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H2.setText("H2");
        H2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H2.setIconTextGap(0);
        H2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H2ActionPerformed(evt);
            }
        });

        H1.setBackground(java.awt.Color.darkGray);
        H1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H1.setText("H1");
        H1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H1.setIconTextGap(0);
        H1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H1ActionPerformed(evt);
            }
        });

        G2.setBackground(java.awt.Color.darkGray);
        G2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G2.setText("G2");
        G2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G2.setIconTextGap(0);
        G2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G2ActionPerformed(evt);
            }
        });

        G1.setBackground(java.awt.Color.darkGray);
        G1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G1.setText("G1");
        G1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G1.setIconTextGap(0);
        G1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G1ActionPerformed(evt);
            }
        });

        G13.setBackground(java.awt.Color.darkGray);
        G13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G13.setText("G13");
        G13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G13.setIconTextGap(0);
        G13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G13ActionPerformed(evt);
            }
        });

        G11.setBackground(java.awt.Color.darkGray);
        G11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G11.setText("G11");
        G11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G11.setIconTextGap(0);
        G11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G11ActionPerformed(evt);
            }
        });

        G12.setBackground(java.awt.Color.darkGray);
        G12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G12.setText("G12");
        G12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G12.setIconTextGap(0);
        G12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G12ActionPerformed(evt);
            }
        });

        G8.setBackground(java.awt.Color.darkGray);
        G8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G8.setText("G8");
        G8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G8.setIconTextGap(0);
        G8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G8ActionPerformed(evt);
            }
        });

        G9.setBackground(java.awt.Color.darkGray);
        G9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G9.setText("G9");
        G9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G9.setIconTextGap(0);
        G9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G9ActionPerformed(evt);
            }
        });

        G10.setBackground(java.awt.Color.darkGray);
        G10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G10.setText("G10");
        G10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G10.setIconTextGap(0);
        G10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G10ActionPerformed(evt);
            }
        });

        G7.setBackground(java.awt.Color.darkGray);
        G7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G7.setText("G7");
        G7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G7.setIconTextGap(0);
        G7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G7ActionPerformed(evt);
            }
        });

        G6.setBackground(java.awt.Color.darkGray);
        G6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G6.setText("G6");
        G6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G6.setIconTextGap(0);
        G6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G6ActionPerformed(evt);
            }
        });

        G5.setBackground(java.awt.Color.darkGray);
        G5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G5.setText("G5");
        G5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G5.setIconTextGap(0);
        G5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G5ActionPerformed(evt);
            }
        });

        G4.setBackground(java.awt.Color.darkGray);
        G4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G4.setText("G4");
        G4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G4.setIconTextGap(0);
        G4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G4ActionPerformed(evt);
            }
        });

        G3.setBackground(java.awt.Color.darkGray);
        G3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        G3.setText("G3");
        G3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        G3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G3.setIconTextGap(0);
        G3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        G3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G3ActionPerformed(evt);
            }
        });

        H13.setBackground(java.awt.Color.darkGray);
        H13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H13.setText("H13");
        H13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H13.setIconTextGap(0);
        H13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H13ActionPerformed(evt);
            }
        });

        H11.setBackground(java.awt.Color.darkGray);
        H11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H11.setText("H11");
        H11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H11.setIconTextGap(0);
        H11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H11ActionPerformed(evt);
            }
        });

        H12.setBackground(java.awt.Color.darkGray);
        H12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H12.setText("H12");
        H12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H12.setIconTextGap(0);
        H12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H12ActionPerformed(evt);
            }
        });

        H8.setBackground(java.awt.Color.darkGray);
        H8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H8.setText("H8");
        H8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H8.setIconTextGap(0);
        H8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H8ActionPerformed(evt);
            }
        });

        H9.setBackground(java.awt.Color.darkGray);
        H9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H9.setText("H9");
        H9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H9.setIconTextGap(0);
        H9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H9ActionPerformed(evt);
            }
        });

        H10.setBackground(java.awt.Color.darkGray);
        H10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H10.setText("H10");
        H10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H10.setIconTextGap(0);
        H10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H10ActionPerformed(evt);
            }
        });

        H7.setBackground(java.awt.Color.darkGray);
        H7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H7.setText("H7");
        H7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H7.setIconTextGap(0);
        H7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H7ActionPerformed(evt);
            }
        });

        H6.setBackground(java.awt.Color.darkGray);
        H6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H6.setText("H6");
        H6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H6.setIconTextGap(0);
        H6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H6ActionPerformed(evt);
            }
        });

        H5.setBackground(java.awt.Color.darkGray);
        H5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H5.setText("H5");
        H5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H5.setIconTextGap(0);
        H5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H5ActionPerformed(evt);
            }
        });

        H4.setBackground(java.awt.Color.darkGray);
        H4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H4.setText("H4");
        H4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H4.setIconTextGap(0);
        H4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H4ActionPerformed(evt);
            }
        });

        H3.setBackground(java.awt.Color.darkGray);
        H3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        H3.setText("H3");
        H3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        H3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        H3.setIconTextGap(0);
        H3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        H3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H3ActionPerformed(evt);
            }
        });

        F2.setBackground(java.awt.Color.darkGray);
        F2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F2.setText("F2");
        F2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F2.setIconTextGap(0);
        F2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F2ActionPerformed(evt);
            }
        });

        F1.setBackground(java.awt.Color.darkGray);
        F1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F1.setText("F1");
        F1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F1.setIconTextGap(0);
        F1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F1ActionPerformed(evt);
            }
        });

        F13.setBackground(java.awt.Color.darkGray);
        F13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F13.setText("F13");
        F13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F13.setIconTextGap(0);
        F13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F13ActionPerformed(evt);
            }
        });

        F11.setBackground(java.awt.Color.darkGray);
        F11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F11.setText("F11");
        F11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F11.setIconTextGap(0);
        F11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F11ActionPerformed(evt);
            }
        });

        F12.setBackground(java.awt.Color.darkGray);
        F12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F12.setText("F12");
        F12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F12.setIconTextGap(0);
        F12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F12ActionPerformed(evt);
            }
        });

        F8.setBackground(java.awt.Color.darkGray);
        F8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F8.setText("F8");
        F8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F8.setIconTextGap(0);
        F8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F8ActionPerformed(evt);
            }
        });

        F9.setBackground(java.awt.Color.darkGray);
        F9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F9.setText("F9");
        F9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F9.setIconTextGap(0);
        F9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F9ActionPerformed(evt);
            }
        });

        F10.setBackground(java.awt.Color.darkGray);
        F10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F10.setText("F10");
        F10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F10.setIconTextGap(0);
        F10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F10ActionPerformed(evt);
            }
        });

        F7.setBackground(java.awt.Color.darkGray);
        F7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F7.setText("F7");
        F7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F7.setIconTextGap(0);
        F7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F7ActionPerformed(evt);
            }
        });

        F6.setBackground(java.awt.Color.darkGray);
        F6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F6.setText("F6");
        F6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F6.setIconTextGap(0);
        F6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F6ActionPerformed(evt);
            }
        });

        F5.setBackground(java.awt.Color.darkGray);
        F5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F5.setText("F5");
        F5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F5.setIconTextGap(0);
        F5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F5ActionPerformed(evt);
            }
        });

        F4.setBackground(java.awt.Color.darkGray);
        F4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F4.setText("F4");
        F4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F4.setIconTextGap(0);
        F4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F4ActionPerformed(evt);
            }
        });

        F3.setBackground(java.awt.Color.darkGray);
        F3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        F3.setText("F3");
        F3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        F3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        F3.setIconTextGap(0);
        F3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F3ActionPerformed(evt);
            }
        });

        E2.setBackground(java.awt.Color.darkGray);
        E2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E2.setText("E2");
        E2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E2.setIconTextGap(0);
        E2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E2ActionPerformed(evt);
            }
        });

        E1.setBackground(java.awt.Color.darkGray);
        E1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E1.setText("E1");
        E1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E1.setIconTextGap(0);
        E1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E1ActionPerformed(evt);
            }
        });

        E13.setBackground(java.awt.Color.darkGray);
        E13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E13.setText("E13");
        E13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E13.setIconTextGap(0);
        E13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E13ActionPerformed(evt);
            }
        });

        E11.setBackground(java.awt.Color.darkGray);
        E11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E11.setText("E11");
        E11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E11.setIconTextGap(0);
        E11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E11ActionPerformed(evt);
            }
        });

        E12.setBackground(java.awt.Color.darkGray);
        E12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E12.setText("E12");
        E12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E12.setIconTextGap(0);
        E12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E12ActionPerformed(evt);
            }
        });

        E8.setBackground(java.awt.Color.darkGray);
        E8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E8.setText("E8");
        E8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E8.setIconTextGap(0);
        E8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E8ActionPerformed(evt);
            }
        });

        E9.setBackground(java.awt.Color.darkGray);
        E9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E9.setText("E9");
        E9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E9.setIconTextGap(0);
        E9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E9ActionPerformed(evt);
            }
        });

        E10.setBackground(java.awt.Color.darkGray);
        E10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E10.setText("E10");
        E10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E10.setIconTextGap(0);
        E10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E10ActionPerformed(evt);
            }
        });

        E7.setBackground(java.awt.Color.darkGray);
        E7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E7.setText("E7");
        E7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E7.setIconTextGap(0);
        E7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E7ActionPerformed(evt);
            }
        });

        E6.setBackground(java.awt.Color.darkGray);
        E6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E6.setText("E6");
        E6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E6.setIconTextGap(0);
        E6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E6ActionPerformed(evt);
            }
        });

        E5.setBackground(java.awt.Color.darkGray);
        E5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E5.setText("E5");
        E5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E5.setIconTextGap(0);
        E5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E5ActionPerformed(evt);
            }
        });

        E4.setBackground(java.awt.Color.darkGray);
        E4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E4.setText("E4");
        E4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E4.setIconTextGap(0);
        E4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E4ActionPerformed(evt);
            }
        });

        E3.setBackground(java.awt.Color.darkGray);
        E3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        E3.setText("E3");
        E3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        E3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E3.setIconTextGap(0);
        E3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        E3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E3ActionPerformed(evt);
            }
        });

        B2.setBackground(java.awt.Color.darkGray);
        B2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B2.setText("B2");
        B2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B2.setIconTextGap(0);
        B2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });

        B1.setBackground(java.awt.Color.darkGray);
        B1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B1.setText("B1");
        B1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B1.setIconTextGap(0);
        B1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });

        B13.setBackground(java.awt.Color.darkGray);
        B13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B13.setText("B13");
        B13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B13.setIconTextGap(0);
        B13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B13ActionPerformed(evt);
            }
        });

        B11.setBackground(java.awt.Color.darkGray);
        B11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B11.setText("B11");
        B11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B11.setIconTextGap(0);
        B11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B11ActionPerformed(evt);
            }
        });

        B12.setBackground(java.awt.Color.darkGray);
        B12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B12.setText("B12");
        B12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B12.setIconTextGap(0);
        B12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B12ActionPerformed(evt);
            }
        });

        B8.setBackground(java.awt.Color.darkGray);
        B8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B8.setText("B8");
        B8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B8.setIconTextGap(0);
        B8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });

        B9.setBackground(java.awt.Color.darkGray);
        B9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B9.setText("B9");
        B9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B9.setIconTextGap(0);
        B9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B9ActionPerformed(evt);
            }
        });

        B10.setBackground(java.awt.Color.darkGray);
        B10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B10.setText("B10");
        B10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B10.setIconTextGap(0);
        B10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B10ActionPerformed(evt);
            }
        });

        B7.setBackground(java.awt.Color.darkGray);
        B7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B7.setText("B7");
        B7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B7.setIconTextGap(0);
        B7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });

        B6.setBackground(java.awt.Color.darkGray);
        B6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B6.setText("B6");
        B6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B6.setIconTextGap(0);
        B6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });

        B5.setBackground(java.awt.Color.darkGray);
        B5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B5.setText("B5");
        B5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B5.setIconTextGap(0);
        B5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });

        B4.setBackground(java.awt.Color.darkGray);
        B4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B4.setText("B4");
        B4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B4.setIconTextGap(0);
        B4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });

        B3.setBackground(java.awt.Color.darkGray);
        B3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        B3.setText("B3");
        B3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        B3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        B3.setIconTextGap(0);
        B3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });

        A2.setBackground(java.awt.Color.darkGray);
        A2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A2.setText("A2");
        A2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A2.setIconTextGap(0);
        A2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A2ActionPerformed(evt);
            }
        });

        A1.setBackground(java.awt.Color.darkGray);
        A1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A1.setText("A1");
        A1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A1.setIconTextGap(0);
        A1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A1ActionPerformed(evt);
            }
        });

        A13.setBackground(java.awt.Color.darkGray);
        A13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A13.setText("A13");
        A13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A13.setIconTextGap(0);
        A13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A13ActionPerformed(evt);
            }
        });

        A11.setBackground(java.awt.Color.darkGray);
        A11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A11.setText("A11");
        A11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A11.setIconTextGap(0);
        A11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A11ActionPerformed(evt);
            }
        });

        A12.setBackground(java.awt.Color.darkGray);
        A12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A12.setText("A12");
        A12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A12.setIconTextGap(0);
        A12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A12ActionPerformed(evt);
            }
        });

        A8.setBackground(java.awt.Color.darkGray);
        A8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A8.setText("A8");
        A8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A8.setIconTextGap(0);
        A8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A8ActionPerformed(evt);
            }
        });

        A9.setBackground(java.awt.Color.darkGray);
        A9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A9.setText("A9");
        A9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A9.setIconTextGap(0);
        A9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A9ActionPerformed(evt);
            }
        });

        A10.setBackground(java.awt.Color.darkGray);
        A10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A10.setText("A10");
        A10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A10.setIconTextGap(0);
        A10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A10ActionPerformed(evt);
            }
        });

        A7.setBackground(java.awt.Color.darkGray);
        A7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A7.setText("A7");
        A7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A7.setIconTextGap(0);
        A7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A7ActionPerformed(evt);
            }
        });

        A6.setBackground(java.awt.Color.darkGray);
        A6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A6.setText("A6");
        A6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A6.setIconTextGap(0);
        A6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A6ActionPerformed(evt);
            }
        });

        A5.setBackground(java.awt.Color.darkGray);
        A5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A5.setText("A5");
        A5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A5.setIconTextGap(0);
        A5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A5ActionPerformed(evt);
            }
        });

        A4.setBackground(java.awt.Color.darkGray);
        A4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A4.setText("A4");
        A4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A4.setIconTextGap(0);
        A4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4ActionPerformed(evt);
            }
        });

        A3.setBackground(java.awt.Color.darkGray);
        A3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        A3.setText("A3");
        A3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        A3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        A3.setIconTextGap(0);
        A3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3ActionPerformed(evt);
            }
        });

        D2.setBackground(java.awt.Color.darkGray);
        D2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D2.setText("D2");
        D2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D2.setIconTextGap(0);
        D2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D2ActionPerformed(evt);
            }
        });

        D1.setBackground(java.awt.Color.darkGray);
        D1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D1.setText("D1");
        D1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D1.setIconTextGap(0);
        D1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D1ActionPerformed(evt);
            }
        });

        C2.setBackground(java.awt.Color.darkGray);
        C2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C2.setText("C2");
        C2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C2.setIconTextGap(0);
        C2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });

        C1.setBackground(java.awt.Color.darkGray);
        C1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C1.setText("C1");
        C1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C1.setIconTextGap(0);
        C1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });

        C13.setBackground(java.awt.Color.darkGray);
        C13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C13.setText("C13");
        C13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C13.setIconTextGap(0);
        C13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C13ActionPerformed(evt);
            }
        });

        C11.setBackground(java.awt.Color.darkGray);
        C11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C11.setText("C11");
        C11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C11.setIconTextGap(0);
        C11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C11ActionPerformed(evt);
            }
        });

        C12.setBackground(java.awt.Color.darkGray);
        C12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C12.setText("C12");
        C12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C12.setIconTextGap(0);
        C12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C12ActionPerformed(evt);
            }
        });

        C8.setBackground(java.awt.Color.darkGray);
        C8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C8.setText("C8");
        C8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C8.setIconTextGap(0);
        C8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });

        C9.setBackground(java.awt.Color.darkGray);
        C9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C9.setText("C9");
        C9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C9.setIconTextGap(0);
        C9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C9ActionPerformed(evt);
            }
        });

        C10.setBackground(java.awt.Color.darkGray);
        C10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C10.setText("C10");
        C10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C10.setIconTextGap(0);
        C10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C10ActionPerformed(evt);
            }
        });

        C7.setBackground(java.awt.Color.darkGray);
        C7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C7.setText("C7");
        C7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C7.setIconTextGap(0);
        C7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });

        C6.setBackground(java.awt.Color.darkGray);
        C6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C6.setText("C6");
        C6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C6.setIconTextGap(0);
        C6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });

        C5.setBackground(java.awt.Color.darkGray);
        C5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C5.setText("C5");
        C5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C5.setIconTextGap(0);
        C5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });

        C4.setBackground(java.awt.Color.darkGray);
        C4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C4.setText("C4");
        C4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C4.setIconTextGap(0);
        C4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });

        C3.setBackground(java.awt.Color.darkGray);
        C3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        C3.setText("C3");
        C3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        C3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        C3.setIconTextGap(0);
        C3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });

        D13.setBackground(java.awt.Color.darkGray);
        D13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D13.setText("D13");
        D13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D13.setIconTextGap(0);
        D13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D13ActionPerformed(evt);
            }
        });

        D11.setBackground(java.awt.Color.darkGray);
        D11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D11.setText("D11");
        D11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D11.setIconTextGap(0);
        D11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D11ActionPerformed(evt);
            }
        });

        D12.setBackground(java.awt.Color.darkGray);
        D12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D12.setText("D12");
        D12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D12.setIconTextGap(0);
        D12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D12ActionPerformed(evt);
            }
        });

        D8.setBackground(java.awt.Color.darkGray);
        D8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D8.setText("D8");
        D8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D8.setIconTextGap(0);
        D8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D8ActionPerformed(evt);
            }
        });

        D9.setBackground(java.awt.Color.darkGray);
        D9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D9.setText("D9");
        D9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D9.setIconTextGap(0);
        D9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D9ActionPerformed(evt);
            }
        });

        D10.setBackground(java.awt.Color.darkGray);
        D10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D10.setText("D10");
        D10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D10.setIconTextGap(0);
        D10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D10ActionPerformed(evt);
            }
        });

        D7.setBackground(java.awt.Color.darkGray);
        D7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D7.setText("D7");
        D7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D7.setIconTextGap(0);
        D7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D7ActionPerformed(evt);
            }
        });

        D6.setBackground(java.awt.Color.darkGray);
        D6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D6.setText("D6");
        D6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D6.setIconTextGap(0);
        D6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D6ActionPerformed(evt);
            }
        });

        D5.setBackground(java.awt.Color.darkGray);
        D5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D5.setText("D5");
        D5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D5.setIconTextGap(0);
        D5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D5ActionPerformed(evt);
            }
        });

        D4.setBackground(java.awt.Color.darkGray);
        D4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D4.setText("D4");
        D4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D4.setIconTextGap(0);
        D4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D4ActionPerformed(evt);
            }
        });

        D3.setBackground(java.awt.Color.darkGray);
        D3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        D3.setText("D3");
        D3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        D3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        D3.setIconTextGap(0);
        D3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        D3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D3ActionPerformed(evt);
            }
        });

        K13.setBackground(java.awt.Color.darkGray);
        K13.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K13.setText("K13");
        K13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K13.setIconTextGap(0);
        K13.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K13ActionPerformed(evt);
            }
        });

        K12.setBackground(java.awt.Color.darkGray);
        K12.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K12.setText("K12");
        K12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K12.setIconTextGap(0);
        K12.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K12ActionPerformed(evt);
            }
        });

        K11.setBackground(java.awt.Color.darkGray);
        K11.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K11.setText("K11");
        K11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K11.setIconTextGap(0);
        K11.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K11ActionPerformed(evt);
            }
        });

        K10.setBackground(java.awt.Color.darkGray);
        K10.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K10.setText("K10");
        K10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K10.setIconTextGap(0);
        K10.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K10ActionPerformed(evt);
            }
        });

        K9.setBackground(java.awt.Color.darkGray);
        K9.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K9.setText("K9");
        K9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K9.setIconTextGap(0);
        K9.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K9ActionPerformed(evt);
            }
        });

        K8.setBackground(java.awt.Color.darkGray);
        K8.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K8.setText("K8");
        K8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K8.setIconTextGap(0);
        K8.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K8ActionPerformed(evt);
            }
        });

        K7.setBackground(java.awt.Color.darkGray);
        K7.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K7.setText("K7");
        K7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K7.setIconTextGap(0);
        K7.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K7ActionPerformed(evt);
            }
        });

        K6.setBackground(java.awt.Color.darkGray);
        K6.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K6.setText("K6");
        K6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K6.setIconTextGap(0);
        K6.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K6ActionPerformed(evt);
            }
        });

        K5.setBackground(java.awt.Color.darkGray);
        K5.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K5.setText("K5");
        K5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K5.setIconTextGap(0);
        K5.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K5ActionPerformed(evt);
            }
        });

        K4.setBackground(java.awt.Color.darkGray);
        K4.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K4.setText("K4");
        K4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K4.setIconTextGap(0);
        K4.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K4ActionPerformed(evt);
            }
        });

        K3.setBackground(java.awt.Color.darkGray);
        K3.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K3.setText("K3");
        K3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K3.setIconTextGap(0);
        K3.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K3ActionPerformed(evt);
            }
        });

        K2.setBackground(java.awt.Color.darkGray);
        K2.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K2.setText("K2");
        K2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K2.setIconTextGap(0);
        K2.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K2ActionPerformed(evt);
            }
        });

        K1.setBackground(java.awt.Color.darkGray);
        K1.setFont(new java.awt.Font("Ebrima", 0, 8)); // NOI18N
        K1.setText("K1");
        K1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        K1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        K1.setIconTextGap(0);
        K1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        K1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                K1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(K13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(K7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(K1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(J13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(J7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(J1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(H13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(G13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(F13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(E13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(D13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(B13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(K13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(K1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(J13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(H13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(G13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(F13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(E13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(jPanel9, gridBagConstraints);

        add(jPanel8, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void J13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J13);
        selectedJTB(J13, "J13");
    }//GEN-LAST:event_J13ActionPerformed

    private void J11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J11);
        selectedJTB(J11, "J11");
    }//GEN-LAST:event_J11ActionPerformed

    private void J12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J12);
        selectedJTB(J12, "J12");
    }//GEN-LAST:event_J12ActionPerformed

    private void J8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J8);
        selectedJTB(J8, "J8");
    }//GEN-LAST:event_J8ActionPerformed

    private void J9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J9);
        selectedJTB(J9, "J9");
    }//GEN-LAST:event_J9ActionPerformed

    private void J10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J10);
        selectedJTB(J10, "J10");
    }//GEN-LAST:event_J10ActionPerformed

    private void J7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J7);
        selectedJTB(J7, "J7");
    }//GEN-LAST:event_J7ActionPerformed

    private void J6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J6);
        selectedJTB(J6, "J6");
    }//GEN-LAST:event_J6ActionPerformed

    private void J5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J5);
        selectedJTB(J5, "J5");
    }//GEN-LAST:event_J5ActionPerformed

    private void J4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J4);
        selectedJTB(J4, "J4");
    }//GEN-LAST:event_J4ActionPerformed

    private void J3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J3);
        selectedJTB(J3, "J3");
    }//GEN-LAST:event_J3ActionPerformed

    private void J2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J2);
        selectedJTB(J2, "J2");
    }//GEN-LAST:event_J2ActionPerformed

    private void J1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(J1);
        selectedJTB(J1, "J1");
    }//GEN-LAST:event_J1ActionPerformed

    private void I2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I2);
        selectedJTB(I2, "I2");
    }//GEN-LAST:event_I2ActionPerformed

    private void I1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I1);
        selectedJTB(I1, "I1");
    }//GEN-LAST:event_I1ActionPerformed

    private void I13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I13);
        selectedJTB(I13, "I13");
    }//GEN-LAST:event_I13ActionPerformed

    private void I11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I11);
        selectedJTB(I11, "I11");
    }//GEN-LAST:event_I11ActionPerformed

    private void I12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I12);
        selectedJTB(I12, "I12");
    }//GEN-LAST:event_I12ActionPerformed

    private void I8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I8);
        selectedJTB(I8, "I8");
    }//GEN-LAST:event_I8ActionPerformed

    private void I9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I9);
        selectedJTB(I9, "I9");
    }//GEN-LAST:event_I9ActionPerformed

    private void I10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I10);
        selectedJTB(I10, "I10");
    }//GEN-LAST:event_I10ActionPerformed

    private void I7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I7);
        selectedJTB(I7, "I7");
    }//GEN-LAST:event_I7ActionPerformed

    private void I6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I6);
        selectedJTB(I6, "I6");
    }//GEN-LAST:event_I6ActionPerformed

    private void I5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I5);
        selectedJTB(I5, "I5");
    }//GEN-LAST:event_I5ActionPerformed

    private void I4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I4);
        selectedJTB(I4, "I4");
    }//GEN-LAST:event_I4ActionPerformed

    private void I3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(I3);
        selectedJTB(I3, "I3");
    }//GEN-LAST:event_I3ActionPerformed

    private void H2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H2);
        selectedJTB(H2, "H2");
    }//GEN-LAST:event_H2ActionPerformed

    private void H1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H1);
        selectedJTB(H1, "H1");
    }//GEN-LAST:event_H1ActionPerformed

    private void G2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G2);
        selectedJTB(G2, "G2");
    }//GEN-LAST:event_G2ActionPerformed

    private void G1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G1);
        selectedJTB(G1, "G1");
    }//GEN-LAST:event_G1ActionPerformed

    private void G13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G13);
        selectedJTB(G13, "G13");
    }//GEN-LAST:event_G13ActionPerformed

    private void G11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G11);
        selectedJTB(G11, "G11");
    }//GEN-LAST:event_G11ActionPerformed

    private void G12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G12);
        selectedJTB(G12, "G12");
    }//GEN-LAST:event_G12ActionPerformed

    private void G8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G8);
        selectedJTB(G8, "G8");
    }//GEN-LAST:event_G8ActionPerformed

    private void G9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G9);
        selectedJTB(G9, "G9");
    }//GEN-LAST:event_G9ActionPerformed

    private void G10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G10);
        selectedJTB(G10, "G10");
    }//GEN-LAST:event_G10ActionPerformed

    private void G7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G7);
        selectedJTB(G7, "G7");
    }//GEN-LAST:event_G7ActionPerformed

    private void G6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G6);
        selectedJTB(G6, "G6");
    }//GEN-LAST:event_G6ActionPerformed

    private void G5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G5);
        selectedJTB(G5, "G5");
    }//GEN-LAST:event_G5ActionPerformed

    private void G4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G4);
        selectedJTB(G4, "G4");
    }//GEN-LAST:event_G4ActionPerformed

    private void G3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(G3);
        selectedJTB(G3, "G3");
    }//GEN-LAST:event_G3ActionPerformed

    private void H13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H13);
        selectedJTB(H13, "H13");
    }//GEN-LAST:event_H13ActionPerformed

    private void H11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H11);
        selectedJTB(H11, "H11");
    }//GEN-LAST:event_H11ActionPerformed

    private void H12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H12);
        selectedJTB(H12, "H12");
    }//GEN-LAST:event_H12ActionPerformed

    private void H8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H8);
        selectedJTB(H8, "H8");
    }//GEN-LAST:event_H8ActionPerformed

    private void H9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H9);
        selectedJTB(H9, "H9");
    }//GEN-LAST:event_H9ActionPerformed

    private void H10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H10);
        selectedJTB(H10, "H10");
    }//GEN-LAST:event_H10ActionPerformed

    private void H7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H7);
        selectedJTB(H7, "H7");
    }//GEN-LAST:event_H7ActionPerformed

    private void H6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H6);
        selectedJTB(H6, "H6");
    }//GEN-LAST:event_H6ActionPerformed

    private void H5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H5);
        selectedJTB(H5, "H5");
    }//GEN-LAST:event_H5ActionPerformed

    private void H4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H4);
        selectedJTB(H4, "H4");
    }//GEN-LAST:event_H4ActionPerformed

    private void H3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(H3);
        selectedJTB(H3, "H3");
    }//GEN-LAST:event_H3ActionPerformed

    private void F2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F2);
        selectedJTB(F2, "F2");
    }//GEN-LAST:event_F2ActionPerformed

    private void F1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F1);
        selectedJTB(F1, "F1");
    }//GEN-LAST:event_F1ActionPerformed

    private void F13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F13);
        selectedJTB(F13, "F13");
    }//GEN-LAST:event_F13ActionPerformed

    private void F11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F11);
        selectedJTB(F11, "F11");
    }//GEN-LAST:event_F11ActionPerformed

    private void F12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F12);
        selectedJTB(F12, "F12");
    }//GEN-LAST:event_F12ActionPerformed

    private void F8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F8);
        selectedJTB(F8, "F8");
    }//GEN-LAST:event_F8ActionPerformed

    private void F9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F9);
        selectedJTB(F9, "F9");
    }//GEN-LAST:event_F9ActionPerformed

    private void F10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F10);
        selectedJTB(F10, "F10");
    }//GEN-LAST:event_F10ActionPerformed

    private void F7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F7);
        selectedJTB(F7, "F7");
    }//GEN-LAST:event_F7ActionPerformed

    private void F6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F6);
        selectedJTB(F6, "F6");
    }//GEN-LAST:event_F6ActionPerformed

    private void F5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F5);
        selectedJTB(F5, "F5");
    }//GEN-LAST:event_F5ActionPerformed

    private void F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F4);
        selectedJTB(F4, "F4");
    }//GEN-LAST:event_F4ActionPerformed

    private void F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(F3);
        selectedJTB(F3, "F3");
    }//GEN-LAST:event_F3ActionPerformed

    private void E2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E2);
        selectedJTB(E2, "E2");
    }//GEN-LAST:event_E2ActionPerformed

    private void E1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E1);
        selectedJTB(E1, "E1");
    }//GEN-LAST:event_E1ActionPerformed

    private void E13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E13);
        selectedJTB(E13, "E13");
    }//GEN-LAST:event_E13ActionPerformed

    private void E11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E11);
        selectedJTB(E11, "E11");
    }//GEN-LAST:event_E11ActionPerformed

    private void E12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E12);
        selectedJTB(E12, "E12");
    }//GEN-LAST:event_E12ActionPerformed

    private void E8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E8);
        selectedJTB(E8, "E8");
    }//GEN-LAST:event_E8ActionPerformed

    private void E9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E9);
        selectedJTB(E9, "E9");
    }//GEN-LAST:event_E9ActionPerformed

    private void E10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E10);
        selectedJTB(E10, "E10");
    }//GEN-LAST:event_E10ActionPerformed

    private void E7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E7);
        selectedJTB(E7, "E7");
    }//GEN-LAST:event_E7ActionPerformed

    private void E6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E6);
        selectedJTB(E6, "E6");
    }//GEN-LAST:event_E6ActionPerformed

    private void E5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E5);
        selectedJTB(E5, "E5");
    }//GEN-LAST:event_E5ActionPerformed

    private void E4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E4);
        selectedJTB(E4, "E4");
    }//GEN-LAST:event_E4ActionPerformed

    private void E3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(E3);
        selectedJTB(E3, "E3");
    }//GEN-LAST:event_E3ActionPerformed

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B2);
        selectedJTB(B2, "B2");
    }//GEN-LAST:event_B2ActionPerformed

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B1);
        selectedJTB(B1, "B1");
    }//GEN-LAST:event_B1ActionPerformed

    private void B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B13);
        selectedJTB(B13, "B13");
    }//GEN-LAST:event_B13ActionPerformed

    private void B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B11);
        selectedJTB(B11, "B11");
    }//GEN-LAST:event_B11ActionPerformed

    private void B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B12);
        selectedJTB(B12, "B12");
    }//GEN-LAST:event_B12ActionPerformed

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B8);
        selectedJTB(B8, "B8");
    }//GEN-LAST:event_B8ActionPerformed

    private void B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B9);
        selectedJTB(B9, "B9");
    }//GEN-LAST:event_B9ActionPerformed

    private void B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B10);
        selectedJTB(B10, "B10");
    }//GEN-LAST:event_B10ActionPerformed

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B7);
        selectedJTB(B7, "B7");
    }//GEN-LAST:event_B7ActionPerformed

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B6);
        selectedJTB(B6, "B6");
    }//GEN-LAST:event_B6ActionPerformed

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B5);
        selectedJTB(B5, "B5");
    }//GEN-LAST:event_B5ActionPerformed

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B4);
        selectedJTB(B4, "B4");
    }//GEN-LAST:event_B4ActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(B3);
        selectedJTB(B3, "B3");
    }//GEN-LAST:event_B3ActionPerformed

    private void A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A2);
        selectedJTB(A2, "A2");
    }//GEN-LAST:event_A2ActionPerformed

    private void A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A1);
        selectedJTB(A1, "A1");
    }//GEN-LAST:event_A1ActionPerformed

    private void A13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A13);
        selectedJTB(A13, "A13");
    }//GEN-LAST:event_A13ActionPerformed

    private void A11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A11);
        selectedJTB(A11, "A11");
    }//GEN-LAST:event_A11ActionPerformed

    private void A12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A12);
        selectedJTB(A12, "A12");
    }//GEN-LAST:event_A12ActionPerformed

    private void A8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A8);
        selectedJTB(A8, "A8");
    }//GEN-LAST:event_A8ActionPerformed

    private void A9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A9);
        selectedJTB(A9, "A9");
    }//GEN-LAST:event_A9ActionPerformed

    private void A10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A10);
        selectedJTB(A10, "A10");
    }//GEN-LAST:event_A10ActionPerformed

    private void A7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A7);
        selectedJTB(A7, "A7");
    }//GEN-LAST:event_A7ActionPerformed

    private void A6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A6);
        selectedJTB(A6, "A6");
    }//GEN-LAST:event_A6ActionPerformed

    private void A5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A5);
        selectedJTB(A5, "A5");
    }//GEN-LAST:event_A5ActionPerformed

    private void A4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A4);
        selectedJTB(A4, "A4");
    }//GEN-LAST:event_A4ActionPerformed

    private void A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(A3);
        selectedJTB(A3, "A3");
    }//GEN-LAST:event_A3ActionPerformed

    private void D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D2);
        selectedJTB(D2, "D2");
    }//GEN-LAST:event_D2ActionPerformed

    private void D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D1);
        selectedJTB(D1, "D1");
    }//GEN-LAST:event_D1ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C2);
        selectedJTB(C2, "C2");
    }//GEN-LAST:event_C2ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C1);
        selectedJTB(C1, "C1");
    }//GEN-LAST:event_C1ActionPerformed

    private void C13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C13);
        selectedJTB(C13, "C13");
    }//GEN-LAST:event_C13ActionPerformed

    private void C11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C11);
        selectedJTB(C11, "C11");
    }//GEN-LAST:event_C11ActionPerformed

    private void C12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C12);
        selectedJTB(C12, "C12");
    }//GEN-LAST:event_C12ActionPerformed

    private void C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C8);
        selectedJTB(C8, "C8");
    }//GEN-LAST:event_C8ActionPerformed

    private void C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C9);
        selectedJTB(C9, "C9");
    }//GEN-LAST:event_C9ActionPerformed

    private void C10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C10);
        selectedJTB(C10, "C10");
    }//GEN-LAST:event_C10ActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C7);
        selectedJTB(C7, "C7");
    }//GEN-LAST:event_C7ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C6);
        selectedJTB(C6, "C6");
    }//GEN-LAST:event_C6ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C5);
        selectedJTB(C5, "C5");
    }//GEN-LAST:event_C5ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C4);
        selectedJTB(C4, "C4");
    }//GEN-LAST:event_C4ActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(C3);
        selectedJTB(C3, "C3");
    }//GEN-LAST:event_C3ActionPerformed

    private void D13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D13);
        selectedJTB(D13, "D13");
    }//GEN-LAST:event_D13ActionPerformed

    private void D11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D11);
        selectedJTB(D11, "D11");
    }//GEN-LAST:event_D11ActionPerformed

    private void D12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D12);
        selectedJTB(D12, "D12");
    }//GEN-LAST:event_D12ActionPerformed

    private void D8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D8);
        selectedJTB(D8, "D8");
    }//GEN-LAST:event_D8ActionPerformed

    private void D9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D9);
        selectedJTB(D9, "D9");
    }//GEN-LAST:event_D9ActionPerformed

    private void D10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D10);
        selectedJTB(D10, "D10");
    }//GEN-LAST:event_D10ActionPerformed

    private void D7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D7);
        selectedJTB(D7, "D7");
    }//GEN-LAST:event_D7ActionPerformed

    private void D6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D6);
        selectedJTB(D6, "D6");
    }//GEN-LAST:event_D6ActionPerformed

    private void D5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D5);
        selectedJTB(D5, "D5");
    }//GEN-LAST:event_D5ActionPerformed

    private void D4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D4);
        selectedJTB(D4, "D4");
    }//GEN-LAST:event_D4ActionPerformed

    private void D3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(D3);
        selectedJTB(D3, "D3");
    }//GEN-LAST:event_D3ActionPerformed

    private void K13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K13ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K13);
        selectedJTB(K13, "K13");
    }//GEN-LAST:event_K13ActionPerformed

    private void K12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K12ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K12);
        selectedJTB(K12, "K12");
    }//GEN-LAST:event_K12ActionPerformed

    private void K11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K11ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K11);
        selectedJTB(K11, "K11");
    }//GEN-LAST:event_K11ActionPerformed

    private void K10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K10ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K10);
        selectedJTB(K10, "K10");
    }//GEN-LAST:event_K10ActionPerformed

    private void K9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K9ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K9);
        selectedJTB(K9, "K9");
    }//GEN-LAST:event_K9ActionPerformed

    private void K8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K8ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K8);
        selectedJTB(K8, "K8");
    }//GEN-LAST:event_K8ActionPerformed

    private void K7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K7ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K7);
        selectedJTB(K7, "K7");
    }//GEN-LAST:event_K7ActionPerformed

    private void K6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K6ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K6);
        selectedJTB(K6, "K6");
    }//GEN-LAST:event_K6ActionPerformed

    private void K5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K5ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K5);
        selectedJTB(K5, "K5");
    }//GEN-LAST:event_K5ActionPerformed

    private void K4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K4ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K4);
        selectedJTB(K4, "K4");
    }//GEN-LAST:event_K4ActionPerformed

    private void K3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K3ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K3);
        selectedJTB(K3, "K3");
    }//GEN-LAST:event_K3ActionPerformed

    private void K2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K2ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K2);
        selectedJTB(K2, "K2");
    }//GEN-LAST:event_K2ActionPerformed

    private void K1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_K1ActionPerformed
        // TODO add your handling code here:
        setwarnaTB(K1);
        selectedJTB(K1, "K1");
    }//GEN-LAST:event_K1ActionPerformed

    private void BLihatPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLihatPesananActionPerformed
        try {
            // TODO add your handling code here:
            if(LKursi.getText().equals("")){
                JOptionPane.showMessageDialog(null,"PILIH KURSI ! \n\n");
            }else{
                 koneksi.updateData("DELETE FROM dummyKursi");
                 koneksi.updateData("INSERT INTO `dummyKursi` (`Kursi`, `Jumlah`) VALUES ('"+LKursi.getText()+"', '"+temp+"') ;");

                Menu.Tiket.panelTiketUtama.formpilihtiket.JS.setViewportView(new panelLihatPesanan());
            }
        } catch (SQLException ex) {
            Logger.getLogger(panelPilihKursi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BLihatPesananActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu.Tiket.panelTiketUtama.formpilihtiket.JS.setViewportView(new panelPilihJadwal());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton A1;
    private javax.swing.JToggleButton A10;
    private javax.swing.JToggleButton A11;
    private javax.swing.JToggleButton A12;
    private javax.swing.JToggleButton A13;
    private javax.swing.JToggleButton A2;
    private javax.swing.JToggleButton A3;
    private javax.swing.JToggleButton A4;
    private javax.swing.JToggleButton A5;
    private javax.swing.JToggleButton A6;
    private javax.swing.JToggleButton A7;
    private javax.swing.JToggleButton A8;
    private javax.swing.JToggleButton A9;
    private javax.swing.JToggleButton B1;
    private javax.swing.JToggleButton B10;
    private javax.swing.JToggleButton B11;
    private javax.swing.JToggleButton B12;
    private javax.swing.JToggleButton B13;
    private javax.swing.JToggleButton B2;
    private javax.swing.JToggleButton B3;
    private javax.swing.JToggleButton B4;
    private javax.swing.JToggleButton B5;
    private javax.swing.JToggleButton B6;
    private javax.swing.JToggleButton B7;
    private javax.swing.JToggleButton B8;
    private javax.swing.JToggleButton B9;
    private javax.swing.JButton BLihatPesanan;
    private javax.swing.JToggleButton C1;
    private javax.swing.JToggleButton C10;
    private javax.swing.JToggleButton C11;
    private javax.swing.JToggleButton C12;
    private javax.swing.JToggleButton C13;
    private javax.swing.JToggleButton C2;
    private javax.swing.JToggleButton C3;
    private javax.swing.JToggleButton C4;
    private javax.swing.JToggleButton C5;
    private javax.swing.JToggleButton C6;
    private javax.swing.JToggleButton C7;
    private javax.swing.JToggleButton C8;
    private javax.swing.JToggleButton C9;
    private javax.swing.JToggleButton D1;
    private javax.swing.JToggleButton D10;
    private javax.swing.JToggleButton D11;
    private javax.swing.JToggleButton D12;
    private javax.swing.JToggleButton D13;
    private javax.swing.JToggleButton D2;
    private javax.swing.JToggleButton D3;
    private javax.swing.JToggleButton D4;
    private javax.swing.JToggleButton D5;
    private javax.swing.JToggleButton D6;
    private javax.swing.JToggleButton D7;
    private javax.swing.JToggleButton D8;
    private javax.swing.JToggleButton D9;
    private javax.swing.JToggleButton E1;
    private javax.swing.JToggleButton E10;
    private javax.swing.JToggleButton E11;
    private javax.swing.JToggleButton E12;
    private javax.swing.JToggleButton E13;
    private javax.swing.JToggleButton E2;
    private javax.swing.JToggleButton E3;
    private javax.swing.JToggleButton E4;
    private javax.swing.JToggleButton E5;
    private javax.swing.JToggleButton E6;
    private javax.swing.JToggleButton E7;
    private javax.swing.JToggleButton E8;
    private javax.swing.JToggleButton E9;
    private javax.swing.JToggleButton F1;
    private javax.swing.JToggleButton F10;
    private javax.swing.JToggleButton F11;
    private javax.swing.JToggleButton F12;
    private javax.swing.JToggleButton F13;
    private javax.swing.JToggleButton F2;
    private javax.swing.JToggleButton F3;
    private javax.swing.JToggleButton F4;
    private javax.swing.JToggleButton F5;
    private javax.swing.JToggleButton F6;
    private javax.swing.JToggleButton F7;
    private javax.swing.JToggleButton F8;
    private javax.swing.JToggleButton F9;
    private javax.swing.JToggleButton G1;
    private javax.swing.JToggleButton G10;
    private javax.swing.JToggleButton G11;
    private javax.swing.JToggleButton G12;
    private javax.swing.JToggleButton G13;
    private javax.swing.JToggleButton G2;
    private javax.swing.JToggleButton G3;
    private javax.swing.JToggleButton G4;
    private javax.swing.JToggleButton G5;
    private javax.swing.JToggleButton G6;
    private javax.swing.JToggleButton G7;
    private javax.swing.JToggleButton G8;
    private javax.swing.JToggleButton G9;
    private javax.swing.JToggleButton H1;
    private javax.swing.JToggleButton H10;
    private javax.swing.JToggleButton H11;
    private javax.swing.JToggleButton H12;
    private javax.swing.JToggleButton H13;
    private javax.swing.JToggleButton H2;
    private javax.swing.JToggleButton H3;
    private javax.swing.JToggleButton H4;
    private javax.swing.JToggleButton H5;
    private javax.swing.JToggleButton H6;
    private javax.swing.JToggleButton H7;
    private javax.swing.JToggleButton H8;
    private javax.swing.JToggleButton H9;
    private javax.swing.JToggleButton I1;
    private javax.swing.JToggleButton I10;
    private javax.swing.JToggleButton I11;
    private javax.swing.JToggleButton I12;
    private javax.swing.JToggleButton I13;
    private javax.swing.JToggleButton I2;
    private javax.swing.JToggleButton I3;
    private javax.swing.JToggleButton I4;
    private javax.swing.JToggleButton I5;
    private javax.swing.JToggleButton I6;
    private javax.swing.JToggleButton I7;
    private javax.swing.JToggleButton I8;
    private javax.swing.JToggleButton I9;
    private javax.swing.JToggleButton J1;
    private javax.swing.JToggleButton J10;
    private javax.swing.JToggleButton J11;
    private javax.swing.JToggleButton J12;
    private javax.swing.JToggleButton J13;
    private javax.swing.JToggleButton J2;
    private javax.swing.JToggleButton J3;
    private javax.swing.JToggleButton J4;
    private javax.swing.JToggleButton J5;
    private javax.swing.JToggleButton J6;
    private javax.swing.JToggleButton J7;
    private javax.swing.JToggleButton J8;
    private javax.swing.JToggleButton J9;
    private javax.swing.JToggleButton K1;
    private javax.swing.JToggleButton K10;
    private javax.swing.JToggleButton K11;
    private javax.swing.JToggleButton K12;
    private javax.swing.JToggleButton K13;
    private javax.swing.JToggleButton K2;
    private javax.swing.JToggleButton K3;
    private javax.swing.JToggleButton K4;
    private javax.swing.JToggleButton K5;
    private javax.swing.JToggleButton K6;
    private javax.swing.JToggleButton K7;
    private javax.swing.JToggleButton K8;
    private javax.swing.JToggleButton K9;
    private javax.swing.JLabel LHarga;
    public javax.swing.JTextArea LKursi;
    private javax.swing.JLabel LTiket;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
