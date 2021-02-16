/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Tiket.PilihTiket;

import Class.Fungsi;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import com.placeholder.PlaceHolder;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dadan
 */
public class panelLihatPesanan extends javax.swing.JPanel {

    /**
     * Creates new form panelLihatPesanan
     */
    String judulFilm, studio,tanggal,jam,harga,namaKasir,kursi,jumlah,total,
            diskon,idTiket,kursiP;
    String[] listKursi;
    Fungsi fungsi = new Fungsi();
    public panelLihatPesanan() {
        try {
            initComponents();
            tampil();
            //jLabel18.setText(Menu.Tiket.PilihTiket.panelPilihJadwal.panelpilihKursi.LKursi.getText());
        } catch (SQLException ex) {
            Logger.getLogger(panelLihatPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void tampil() throws SQLException{
        getAkses();
        LNoFaktur.setText( fungsi.setId("SELECT MAX(RIGHT(ID_Struk,7)) AS NO FROM strukpembayaran", "SNF0000001"));
        LKasir.setText(namaKasir);
        LJudul.setText(judulFilm);
        LStudio.setText(studio);
        LTanggal.setText(tanggal);
        LJam.setText(jam);
        LHarga.setText(harga);
        LKursi.setText(kursi);
        LJumlahTiket.setText(jumlah+" TIKET");
        LTotal.setText("Rp. "+total);
        LTotalBayar.setText("Rp. "+total);
        LKembalian.setText("Rp. 0,00-");
        TFDiskon.setDocument(new Limit(2));
        TFPayment.setDocument(fungsi.getsNumber());
        PlaceHolder diskon = new PlaceHolder(TFDiskon, "MAX 99%");
        PlaceHolder bayar = new PlaceHolder(TFPayment, "DALAM RUPIAH");
    }
    
    public void getAkses(){
        try {
            String data[] = new String[5];
            data =  koneksi.ambilData(5,"SELECT * FROM `dummytikethelp`");
            this.judulFilm = data[0];
            this.studio = data[1];
            this.tanggal = data[2];
            this.jam = data[3];
            this.harga = data[4];
            this.namaKasir = panelidentitas.LNamaKasir.getText();
            data =  koneksi.ambilData(2, "SELECT * FROM `dummyKursi`");
            this.kursi = data[0];
            this.jumlah = data[1];
            int x = Integer.valueOf(jumlah);
            int y = Integer.valueOf(harga);
            int jml = x*y;
            this.total = String.valueOf(jml);
            this.idTiket = fungsi.setId("SELECT MAX(RIGHT(ID_Tiket,7)) AS NO FROM `relasi_pesantiket.kursi`", "TKT0000001");
                    
        } catch (SQLException ex) {
            Logger.getLogger(panelPilihKursi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Bayar() throws SQLException{
         koneksi.updateData("DELETE FROM dummyHelp");
                 koneksi.updateData("INSERT INTO `dummyHelp` (`akses`) VALUES ('"+LNoFaktur.getText()+"');");
        
                if(TFDiskon.getText().isEmpty() ||TFDiskon.getText().equals("")){
                    this.diskon="0";
                }else{
                    this.diskon=TFDiskon.getText();
                }
                String SQL ;
                SQL = "INSERT INTO `strukpembayaran` (`ID_Struk`, `Tanggal_Beli`, `Jam_Beli`, `Jumlah_Item`, `Total`, `Diskon`, `Total_Bayar`, `Payment`, `Change`, `Nama_Kasir`) "
                        + "VALUES ('"+LNoFaktur.getText()+"', '"+fungsi.gettanggal()+"', '"+fungsi.getwaktu()+"', '"+jumlah+"', '"+fungsi.filter(LTotal.getText())+"', '"+diskon+"', '"+fungsi.filter(LTotalBayar.getText())+"', '"+fungsi.filter(TFPayment.getText())+"', '"+fungsi.filter(LKembalian.getText())+"', '"+namaKasir+"');";
                 koneksi.updateData(SQL);
                SQL = "INSERT INTO `relasi_pesantiket` (`ID_Tiket`, `ID_Struk`, `ID_Jadwal`, `ID_Karyawan`, `Judul_Film`, `Tanggal`, `Jam`,`Harga`,`Studio`) "
                        + "VALUES ('"+idTiket+"', '"+LNoFaktur.getText()+"', (SELECT ID_Jadwal FROM jadwal WHERE STUDIO = '"+studio+"'), '"+panelidentitas.LID.getText()+"', '"+judulFilm+"', '"+tanggal+"', '"+jam+"','"+harga+"','"+studio+"'); ";
                 koneksi.updateData(SQL);
                kursiP = LKursi.getText();
                listKursi = kursiP.split(", ");
                for (String listKursi1 : listKursi) {
                    SQL = "INSERT INTO `relasi_pesantiket.kursi` (`ID_Tiket`, `Kursi`) VALUES ('"+idTiket+"', '" + listKursi1 + "'); ";
                     koneksi.updateData(SQL);
                }
                Menu.Tiket.panelTiketUtama.formpilihtiket.JS.setViewportView(new panelCetak());
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

        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        LNoFaktur = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        LKasir = new javax.swing.JLabel();
        LJudul = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LTanggal = new javax.swing.JLabel();
        LJam = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        LHarga = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        LKursi = new javax.swing.JLabel();
        LStudio = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        LJumlahTiket = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        LTotal = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        TFDiskon = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        LTotalBayar = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        TFPayment = new javax.swing.JTextField();
        BBayar = new javax.swing.JButton();
        BBatal = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        LKembalian = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/go-back-icon (1).png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap(464, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        jLabel2.setText("No Transaksi");

        LNoFaktur.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        LNoFaktur.setForeground(new java.awt.Color(255, 0, 51));
        LNoFaktur.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        jLabel3.setText("Kasir");

        LKasir.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        LKasir.setForeground(new java.awt.Color(255, 0, 51));
        LKasir.setText("jLabel3");

        LJudul.setFont(new java.awt.Font("Ebrima", 0, 15)); // NOI18N
        LJudul.setText("Judul Film");

        jLabel13.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel13.setText("Tanggal");

        LTanggal.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LTanggal.setText("Tanggal");

        LJam.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LJam.setText("Jam");

        jLabel16.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel16.setText("Kursi");

        jLabel17.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Studio");

        LHarga.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LHarga.setText("Harga");

        LKursi.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LKursi.setText("Kursi");

        LStudio.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LStudio.setText("studio");

        LJumlahTiket.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LJumlahTiket.setText("XX TIKET");

        jLabel20.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel20.setText("Total          :");

        LTotal.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LTotal.setText("Total");

        jLabel22.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel22.setText("Diskon (%) :");

        TFDiskon.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        TFDiskon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TFDiskon.setToolTipText("MAX 99%");
        TFDiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFDiskonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFDiskonKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel23.setText("Total Bayar :");

        LTotalBayar.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LTotalBayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LTotalBayar.setText("Total");

        jLabel25.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel25.setText("Payment     :");

        TFPayment.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        TFPayment.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TFPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFPaymentKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFPaymentKeyTyped(evt);
            }
        });

        BBayar.setBackground(new java.awt.Color(51, 255, 102));
        BBayar.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        BBayar.setText("BAYAR");
        BBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBayarActionPerformed(evt);
            }
        });

        BBatal.setBackground(new java.awt.Color(51, 255, 102));
        BBatal.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        BBatal.setText("BATAL");
        BBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBatalActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel26.setText("Kembalian  :");

        LKembalian.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LKembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LKembalian.setText("Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(LNoFaktur))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LKasir))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LJudul)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(LHarga)
                        .addGap(18, 18, 18)
                        .addComponent(LJumlahTiket))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LTanggal))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(LJam))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LKursi))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(41, 41, 41)
                        .addComponent(LStudio))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(LTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(17, 17, 17)
                        .addComponent(TFDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(LTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(17, 17, 17)
                        .addComponent(TFPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(BBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(BBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(LKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(LNoFaktur))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(LKasir))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(LJudul)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LHarga)
                    .addComponent(LJumlahTiket))
                .addGap(6, 6, 6)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(LTanggal))
                .addGap(6, 6, 6)
                .addComponent(LJam)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(LKursi))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(LStudio))
                .addGap(11, 11, 11)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(LTotal))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(TFDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(LTotalBayar))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(TFPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(LKembalian))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel1.add(jPanel3, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void TFDiskonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFDiskonKeyReleased
        // TODO add your handling code here:
        String b;
        b = TFDiskon.getText();
        DecimalFormat dec = new DecimalFormat("#");
        double diskon;
        double tot  = Integer.valueOf(fungsi.filter(LTotal.getText()));
        if (b.isEmpty()){
            diskon = 0;
        }else{
            diskon = Integer.valueOf(TFDiskon.getText());
        }
        double subtot = tot-((diskon/100)*tot);

        LTotalBayar.setText("Rp. "+dec.format(subtot));
    }//GEN-LAST:event_TFDiskonKeyReleased

    private void TFDiskonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFDiskonKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter>='0') && (karakter <= '9') || karakter == KeyEvent.VK_BACK_SPACE || (karakter == KeyEvent.VK_DELETE)|| karakter==KeyEvent.VK_ENTER))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_TFDiskonKeyTyped

    private void TFPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFPaymentKeyReleased
        // TODO add your handling code here:
        String b;
        b = TFPayment.getText();
        DecimalFormat dec = new DecimalFormat("#");
        double bayar;
        double tot  = Double.valueOf(fungsi.filter(LTotalBayar.getText()));
        if (b.isEmpty()){
            bayar = 0;
        }else{
            bayar = Double.valueOf(TFPayment.getText());
        }
        double subtot = bayar-tot;

        LKembalian.setText("Rp. "+dec.format(subtot));
    }//GEN-LAST:event_TFPaymentKeyReleased

    private void BBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBayarActionPerformed
        // TODO add your handling code here:
        if(TFPayment.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Silahkan Masukan Nominal Bayar");
        }else if(Integer.parseInt(fungsi.filter(TFPayment.getText()))<Integer.parseInt(fungsi.filter(LKembalian.getText()))){
            JOptionPane.showMessageDialog(null, "Pembayaran Anda Kurang");
            TFPayment.requestFocus();
        }
        else{
            try {
                Bayar();
            } catch (SQLException ex) {
                Logger.getLogger(panelLihatPesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
       
    }//GEN-LAST:event_BBayarActionPerformed

    private void BBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBatalActionPerformed
        // TODO add your handling code here:
        Menu.Tiket.panelTiketUtama.formpilihtiket.jPanel2.setVisible(true);
        Menu.Tiket.panelTiketUtama.formpilihtiket.JS.setViewportView(new panelPilihFilm());
    }//GEN-LAST:event_BBatalActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Menu.Tiket.panelTiketUtama.formpilihtiket.JS.setViewportView(Menu.Tiket.PilihTiket.panelPilihKursi.panelpilihkursi);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TFPaymentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFPaymentKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(karakter==KeyEvent.VK_ENTER){
            if(TFPayment.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Silahkan Masukan Nominal Bayar");
        }else if(Integer.parseInt(fungsi.filter(TFPayment.getText()))<Integer.parseInt(fungsi.filter(LKembalian.getText()))){
            JOptionPane.showMessageDialog(null, "Pembayaran Anda Kurang");
            TFPayment.requestFocus();
        }
        else{
            try {
                Bayar();
            } catch (SQLException ex) {
                Logger.getLogger(panelLihatPesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }//GEN-LAST:event_TFPaymentKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBatal;
    private javax.swing.JButton BBayar;
    private javax.swing.JLabel LHarga;
    private javax.swing.JLabel LJam;
    private javax.swing.JLabel LJudul;
    private javax.swing.JLabel LJumlahTiket;
    private javax.swing.JLabel LKasir;
    private javax.swing.JLabel LKembalian;
    private javax.swing.JLabel LKursi;
    private javax.swing.JLabel LNoFaktur;
    private javax.swing.JLabel LStudio;
    private javax.swing.JLabel LTanggal;
    private javax.swing.JLabel LTotal;
    private javax.swing.JLabel LTotalBayar;
    private javax.swing.JTextField TFDiskon;
    private javax.swing.JTextField TFPayment;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
