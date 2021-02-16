/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Karyawan;

import Class.Fungsi;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Hanii
 */
public class panelTabelKaryawan extends javax.swing.JPanel {

    /**
     * Creates new form panelTabelKaryawan
     */
    Fungsi fungsi = new Fungsi();
    private TableRowSorter SorterKaryawan;
        private Vector list;
        private String SQL,idKaryawan,inputData;
        private String ID_KARYAWAN,NAMA_KARYAWAN,No_Telepon, Tempat_Tanggal_Lahir , Jenis_Kelamin , Alamat , Status_Kawin ,  Jabatan , Tanggal_Masuk_Kerja ,  Status_Aktif ;
        private int row = 0, kolom = 0, gnt = 0;
        public int getrow;
        ResultSet res = null;
    public DefaultTableModel ModelTabelKaryawan=getDefaultTableKaryawan();
    private DefaultTableModel getDefaultTableKaryawan(){
        return new javax.swing.table.DefaultTableModel(
                new Object[][][][][][][][][][][]{},
                new String[] {"ID KARYAWAN","NAMA KARYAWAN","No Telepon","Tempat Tanggal Lahir","Jenis Kelamin","Alamat","Status Kawin", "Jabatan","Tanggal Masuk Kerja", "Status Aktif"}
        ){
            boolean[] canEdit = new boolean[]{
                false,false,false,false,false,false,false,false,false,false
            };
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    public panelTabelKaryawan() {
        initComponents();
        setTabelKaryawan();
    }
    public void setTabelKaryawan(){
        getDataTabel();
        cariKaryawan();
        jButton1.setEnabled(false);
        tabelKaryawan.setModel(ModelTabelKaryawan);
        row = tabelKaryawan.getRowCount();
        LJumlahKaryawan.setText(String.valueOf(row));
    }
    public void getDataTabel() { //<-- untuk ambil data
        ModelTabelKaryawan = new DefaultTableModel();
        ModelTabelKaryawan = getDefaultTableKaryawan();
        SQL = "SELECT * from KARYAWAN order by id_karyawan desc"; 
        String data[] = new String[10];
        try {
                res =   koneksi.lihatData(SQL);
                    while (res.next()){
                        data[0] = ID_KARYAWAN = res.getString("ID_Karyawan");
                        data[1] = NAMA_KARYAWAN = res.getString("Nama_Lengkap");
                        data[2] = No_Telepon = res.getString("No_Telepon");
                        data[3] = Tempat_Tanggal_Lahir = res.getString("Tempat_Lahir")+", "+res.getString("Tanggal_Lahir");
                        data[4] = Jenis_Kelamin = res.getString("Jenis_Kelamin");
                        data[5] = Alamat = res.getString("Jalan")+" "+res.getString("No")+" "+res.getString("Kab_Kota");
                        data[6] = Status_Kawin = res.getString("Status_Kawin");
                        data[7] = Jabatan = res.getString("Jabatan");
                        data[8] = Tanggal_Masuk_Kerja = res.getString("Tanggal_Masuk_Kerja");
                        data[9] = Status_Aktif = res.getString("Status_Aktif");
                        ModelTabelKaryawan.addRow(data);
                        
                    }            
                    
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + se);
        }
    }
    public void cariKaryawan(){
        TFCariKaryawan.setDocument(  fungsi.getToUpperCase());
        SorterKaryawan = new TableRowSorter(ModelTabelKaryawan);
        tabelKaryawan.setRowSorter(SorterKaryawan);
        TFCariKaryawan.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                String filter = TFCariKaryawan.getText();
                if (filter == null) {
                    SorterKaryawan.setRowFilter(RowFilter.regexFilter(null));
                } else {
                    char[] charArray = filter.toCharArray();
                    String[] stringArray = new String[charArray.length];
 
                    for (int i = 0; i < stringArray.length; i++) {
                        stringArray[i] = "[" + Character.toUpperCase(charArray[i])
                                + Character.toLowerCase(charArray[i]) + "]";
                    }
                    String regex = "";
                    for (String string : stringArray) {
                        regex += string;
                    }
                    try {
                        SorterKaryawan.setRowFilter(RowFilter.regexFilter(regex));
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + ex.getMessage());
                    }
                }
            }
        });
    }
    public void setAkses(String SQL) throws SQLException{
          koneksi.updateData(SQL);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TFCariKaryawan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKaryawan = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        LJumlahKaryawan = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/go-back-icon (1).png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
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
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(356, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel2.setText("Cari");

        TFCariKaryawan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        tabelKaryawan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        tabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKaryawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKaryawan);

        jLabel4.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel4.setText("Jumlah Film");

        LJumlahKaryawan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LJumlahKaryawan.setForeground(new java.awt.Color(255, 51, 51));
        LJumlahKaryawan.setText("Jumlah Film");

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 0, 12)); // NOI18N
        jLabel5.setText("*) Klik 2x Untuk Edit Data dan Lihat Deskripsi Karyawan");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jButton3.setText("BACKUP");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jButton4.setText("RESTORE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(LJumlahKaryawan)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TFCariKaryawan)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFCariKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LJumlahKaryawan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addComponent(jButton1))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabelKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKaryawanMouseClicked
        try {
            // TODO add your handling code here:
            jButton1.setEnabled(true);
            row = tabelKaryawan.getSelectedRow();
            row = tabelKaryawan.convertRowIndexToModel(row);
            kolom = tabelKaryawan.getSelectedColumn();
            kolom = tabelKaryawan.convertColumnIndexToModel(row);
            
            idKaryawan = (String) ModelTabelKaryawan.getValueAt(row, 0);
            setAkses("DELETE FROM dummyHelp;");
            setAkses("INSERT INTO `dummyHelp` (`akses`) VALUES ('"+idKaryawan+"');");
            this.getrow = row;
            if (evt.getClickCount()==2){
                panelidentitas.JS.setViewportView(new panelDeskripsiK());
                //     deskripsiKaryawan.show();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_tabelKaryawanMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelKaryawanUtama());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        row = tabelKaryawan.getSelectedRow();
        row = tabelKaryawan.convertRowIndexToModel(row);
        if(ModelTabelKaryawan.getValueAt(row, 9).equals("AKTIF")){
            JOptionPane.showMessageDialog(null, "Tidak Bisa Menghapus Data Karyawan \n Dengan Status Aktif / Masih Bekerja \n\n Pecat Karyawan Di Menu Deskripsi","Hapus Karyawan",JOptionPane.INFORMATION_MESSAGE);
        }else if(ModelTabelKaryawan.getValueAt(row, 0).equals(Main.panelIdentitas.panelidentitas.LID)){
            JOptionPane.showMessageDialog(null, "AKUN INI DALAM KEADAAN LOGIN","HAPUS KARYAWAN",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int konfirmasi=JOptionPane.showConfirmDialog(null, "Hapus Karyawan "+ModelTabelKaryawan.getValueAt(row, 1)+"? \n U Will Lost The Data About "+ModelTabelKaryawan.getValueAt(row, 1)+" Keep It To Remembered ", "Hapus Data Karyawan (Not Recomended)", JOptionPane.YES_NO_OPTION);
            if(konfirmasi==JOptionPane.YES_OPTION){
                try {
                    koneksi.updateData("DELETE FROM karyawan WHERE ID_Karyawan = '"+ModelTabelKaryawan.getValueAt(row, 0)+"'");
                    ModelTabelKaryawan.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Data Karyawan Telah Dihapus","Hapus Karyawan",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        fungsi.BackupDatabase("karyawan", "BACKUP(KRY)_", "karyawan karyawan.akunlogin");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        fungsi.RestoreDatabase("karyawan");
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LJumlahKaryawan;
    private javax.swing.JTextField TFCariKaryawan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabelKaryawan;
    // End of variables declaration//GEN-END:variables
}
