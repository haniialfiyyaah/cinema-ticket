/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Class.Fungsi;
import static Main.formMenu.menu;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Hanii
 */
public class panelIdentitas extends javax.swing.JPanel {

    /**
     * Creates new form panelIdentitas
     */
    public static panelIdentitas panelidentitas = new panelIdentitas();
    private String username, idKaryawan, namaKasir, jabatan;
    Fungsi fungsi = new Fungsi();
    public panelIdentitas(){
        initComponents();
        setIdentitas();
        if(LJabatan.getText().equals("KASIR")){
            BBackup.setVisible(false);
            BRestore.setVisible(false);
            JS.setViewportView(new panelMenuKasir());
        }else{
            BBackup.setVisible(true);
            BRestore.setVisible(true);
            JS.setViewportView(new panelMenuStaff());
        }
    }
    
    private void setIdentitas(){
        try {
            String dtuser[] = new String[1];
            dtuser = koneksi.ambilData(1, "SELECT * FROM `dummyhelp`");
            username = dtuser[0];
            
            
            String dtIdK[] = new String[1];
            dtIdK = koneksi.ambilData(1, "SELECT `ID_Karyawan` FROM `karyawan.akunlogin` WHERE username='"+username+"'");
            idKaryawan = dtIdK[0];
            
            String dtNmKsr[] = new String[1];
            dtNmKsr = koneksi.ambilData(1, "SELECT `Nama_Lengkap` FROM `karyawan` WHERE `ID_Karyawan` = '"+idKaryawan+"'");
            namaKasir = dtNmKsr[0];
            
            String dtJabatan[] = new String[1];
            dtJabatan =koneksi.ambilData(1, "SELECT Jabatan FROM `karyawan` WHERE `ID_Karyawan` = '"+idKaryawan+"'");
            jabatan = dtJabatan[0];
            
            LID.setText(idKaryawan);
            LNamaKasir.setText(namaKasir);
            LJabatan.setText(jabatan);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Hereee"+ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        LNamaKasir = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        LJabatan = new javax.swing.JLabel();
        LID = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        BLogout = new javax.swing.JButton();
        BAkunSaya = new javax.swing.JButton();
        BMenu = new javax.swing.JButton();
        BBackup = new javax.swing.JButton();
        BRestore = new javax.swing.JButton();
        JS = new javax.swing.JScrollPane();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KARYAWAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Baskerville Old Face", 0, 12))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KARYAWAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ebrima", 0, 14))); // NOI18N

        LNamaKasir.setFont(new java.awt.Font("Broadway", 0, 12)); // NOI18N
        LNamaKasir.setText("NAMA KASIR");

        LJabatan.setFont(new java.awt.Font("Broadway", 0, 12)); // NOI18N
        LJabatan.setText("JABATAN");

        LID.setFont(new java.awt.Font("Broadway", 0, 12)); // NOI18N
        LID.setText("ID");

        BLogout.setFont(new java.awt.Font("Broadway", 0, 18)); // NOI18N
        BLogout.setText("LOGOUT");
        BLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLogoutActionPerformed(evt);
            }
        });

        BAkunSaya.setFont(new java.awt.Font("DialogInput", 0, 10)); // NOI18N
        BAkunSaya.setText("AKUN SAYA");
        BAkunSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAkunSayaActionPerformed(evt);
            }
        });

        BMenu.setFont(new java.awt.Font("DialogInput", 0, 10)); // NOI18N
        BMenu.setText("MENU");
        BMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMenuActionPerformed(evt);
            }
        });

        BBackup.setFont(new java.awt.Font("DialogInput", 0, 10)); // NOI18N
        BBackup.setText("BACKUP DATABASE");
        BBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBackupActionPerformed(evt);
            }
        });

        BRestore.setFont(new java.awt.Font("DialogInput", 0, 10)); // NOI18N
        BRestore.setText("RESTORE DATABASE");
        BRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRestoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LNamaKasir)
                            .addComponent(LJabatan)
                            .addComponent(LID))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(BAkunSaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BRestore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BAkunSaya)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BBackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BRestore)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.LINE_START);

        JS.setBorder(null);
        add(JS, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void BLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLogoutActionPerformed
        // TODO add your handling code here:
        menu.JS.setViewportView(new panelLogin());
        
    }//GEN-LAST:event_BLogoutActionPerformed

    private void BMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMenuActionPerformed
        // TODO add your handling code here:
        if(LJabatan.getText().equals("KASIR")){
                JS.setViewportView(new panelMenuKasir());
            }else{
                JS.setViewportView(new panelMenuStaff());
            }
    }//GEN-LAST:event_BMenuActionPerformed

    private void BAkunSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAkunSayaActionPerformed
        // TODO add your handling code here:
        JS.setViewportView(new panelAkunSaya());
    }//GEN-LAST:event_BAkunSayaActionPerformed

    private void BBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBackupActionPerformed
        try {
            // TODO add your handling code here:
            String id = null, tabelDBSQL = null;
            
            ResultSet res =null;
            res = koneksi.lihatData("select @@datadir");
            
            String Mysqlpath = "";
            while(res.next()){
                Mysqlpath=res.getString(1);
            }
            Mysqlpath = Mysqlpath.substring(0, Mysqlpath.length()-6);
            //gettanggal
            java.util.Date ys = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl=(dateFormat.format(ys));
            //endgettanggal
            String jam = fungsi.getwaktu().substring(0,2);
            String menit = fungsi.getwaktu().substring(3, 5);
            String detik = fungsi.getwaktu().substring(6, 8);
            ///
            
            String backupPath = koneksi.currentDir+"\\folder\\backup\\utama\\BACKUP(ALL)_"+tgl+"_"+jam+menit+detik+".sql";
            String backupCMD =Mysqlpath+"\\bin\\mysqldump.exe -uroot --add-drop-table "+koneksi.database+" -r \""+backupPath+"\"";
            Process runtimeProcess;
            runtimeProcess = Runtime.getRuntime().exec(backupCMD);
            int processComplete = runtimeProcess.waitFor();
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIBACKUP \n\n");
            } else {
                JOptionPane.showMessageDialog(null, "BACKUP ERROR \n\n");
            }
        } catch (SQLException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_BBackupActionPerformed

    private void BRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRestoreActionPerformed
        // TODO add your handling code here:
       fungsi.RestoreDatabase("utama");
    }//GEN-LAST:event_BRestoreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAkunSaya;
    private javax.swing.JButton BBackup;
    private javax.swing.JButton BLogout;
    private javax.swing.JButton BMenu;
    private javax.swing.JButton BRestore;
    public javax.swing.JScrollPane JS;
    public javax.swing.JLabel LID;
    public javax.swing.JLabel LJabatan;
    public javax.swing.JLabel LNamaKasir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
