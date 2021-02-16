/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Tiket.PilihTiket;

import Class.Fungsi;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dadan
 */
public class panelCetak extends javax.swing.JPanel {

    /**
     * Creates new form panelCetak
     */
    Fungsi fungsi = new Fungsi();
    String idStruk,kembali;
    public panelCetak() {
        try {
            initComponents();
            setAkses();
            LKembali.setText("Rp. "+kembali);
        } catch (SQLException ex) {
            Logger.getLogger(panelCetak.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setAkses() throws SQLException{
        String data[] = new String[1];
        data = koneksi.ambilData(1, "SELECT * FROM dummyHelp");
        this.idStruk = data[0];
        
        data = koneksi.ambilData(1, "SELECT `Change` FROM strukpembayaran where ID_Struk='"+this.idStruk+"'");
        this.kembali = data[0];
        
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        LKembali = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/go-back-icon (1).png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        LKembali.setFont(new java.awt.Font("Vijaya", 0, 36)); // NOI18N
        LKembali.setForeground(new java.awt.Color(255, 51, 0));
        LKembali.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LKembali.setText("Rp. 0,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 140, 0, 0);
        jPanel3.add(LKembali, gridBagConstraints);

        jLabel39.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Kembalian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 162, 0, 0);
        jPanel3.add(jLabel39, gridBagConstraints);

        jButton5.setBackground(new java.awt.Color(51, 255, 102));
        jButton5.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jButton5.setText("CETAK TIKET");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 261;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 11, 10);
        jPanel3.add(jButton5, gridBagConstraints);

        jButton4.setBackground(new java.awt.Color(51, 255, 102));
        jButton4.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jButton4.setText("CETAK STRUK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 253;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(56, 10, 0, 10);
        jPanel3.add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel2.add(jPanel3, gridBagConstraints);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT\n" +
"     strukpembayaran.`ID_Struk` AS strukpembayaran_ID_Struk,\n" +
"     strukpembayaran.`Tanggal_Beli` AS strukpembayaran_Tanggal_Beli,\n" +
"     strukpembayaran.`Jam_Beli` AS strukpembayaran_Jam_Beli,\n" +
"     strukpembayaran.`Jumlah_Item` AS strukpembayaran_Jumlah_Item,\n" +
"     strukpembayaran.`Total` AS strukpembayaran_Total,\n" +
"     strukpembayaran.`Diskon` AS strukpembayaran_Diskon,\n" +
"     strukpembayaran.`Total_Bayar` AS strukpembayaran_Total_Bayar,\n" +
"     strukpembayaran.`Payment` AS strukpembayaran_Payment,\n" +
"     strukpembayaran.`Change` AS strukpembayaran_Change,\n" +
"     strukpembayaran.`Nama_Kasir` AS strukpembayaran_Nama_Kasir,\n" +
"     relasi_pesantiket.`ID_Tiket` AS relasi_pesantiket_ID_Tiket,\n" +
"     relasi_pesantiket.`Judul_Film` AS relasi_pesantiket_Judul_Film,\n" +
"     relasi_pesantiket.`Tanggal` AS relasi_pesantiket_Tanggal,\n" +
"     relasi_pesantiket.`Jam` AS relasi_pesantiket_Jam,\n" +
"     relasi_pesantiket.`Harga` AS relasi_pesantiket_Harga,\n" +
"     relasi_pesantiket.`Studio` AS relasi_pesantiket_Studio\n" +
"FROM\n" +
"     `strukpembayaran` strukpembayaran INNER JOIN `relasi_pesantiket` relasi_pesantiket ON strukpembayaran.`ID_Struk` = relasi_pesantiket.`ID_Struk` WHERE strukpembayaran.`ID_Struk`='"+idStruk+"'";
       
                fungsi.Cetak(sql, "StrukPembayaran",2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu.Tiket.panelTiketUtama.formpilihtiket.jPanel2.setVisible(true);
        Menu.Tiket.panelTiketUtama.formpilihtiket.JS.setViewportView(new panelPilihFilm());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         String sql = "SELECT\n" +
"     relasi_pesantiket.`ID_Tiket` AS ID_Tiket,\n" +
"     relasi_pesantiket.`ID_Struk` AS ID_Struk,\n" +
"     relasi_pesantiket.`ID_Jadwal` AS ID_Jadwal,\n" +
"     relasi_pesantiket.`ID_Karyawan` AS ID_Karyawan,\n" +
"     relasi_pesantiket.`Judul_Film` AS Judul_Film,\n" +
"     relasi_pesantiket.`Tanggal` AS Tanggal,\n" +
"     relasi_pesantiket.`Jam` AS Jam,\n" +
"     relasi_pesantiket.`Harga` AS Harga,\n" +
"     relasi_pesantiket.`Studio` AS Studio,\n" +
"     `Kursi` AS Kursi,\n" +
"     `strukpembayaran`.`Tanggal_Beli` AS Tanggal_Beli,\n" +
"     `strukpembayaran`.`Jam_Beli` AS Jam_Beli\n" +
"FROM\n" +
"     `relasi_pesantiket` relasi_pesantiket JOIN \n" +
"     `relasi_pesantiket.kursi` relasi_pesantiket_kursi USING (`ID_Tiket`) JOIN\n" +
"     `strukpembayaran` strukpembayaran USING (`ID_Struk`) WHERE `ID_Struk`='"+idStruk+"'";
       
                fungsi.Cetak(sql, "Tiket",2);
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LKembali;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
