/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.DataFilm;

import Class.Fungsi;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author dadan
 */
public class panelGenre extends javax.swing.JPanel {

    /**
     * Creates new form panelGenre
     */
    Fungsi fungsi = new Fungsi();
        public int row = 0, kolom = 0,gnt =0;
        public String SQL, inputData, idFilm;
        public Vector listNm,listID;
        ResultSet res =null;
    private TableRowSorter SorterGenre;
    private final DefaultTableModel ModelTabelGenre=getDefaultTableGenre();
    private DefaultTableModel getDefaultTableGenre(){
        return new javax.swing.table.DefaultTableModel(
                new Object[][][][][][][]{},
                new String[] {"ID_GENRE","GENRE"}
        ){
            boolean[] canEdit = new boolean[]{
                false,false,false,false,false,false
            };
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    Statement stat =  koneksi.getSt();
    
    public panelGenre() throws SQLException {
        initComponents();
        setTabelGenre();
    }
    
    public void simpan(){
        if (TFGenre.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan dilengkapi");
        }
        else{
            try{
                String id =  fungsi.setId("SELECT MAX(RIGHT(ID_GENRE,7)) AS NO FROM `film.genre`", "GNR0000001");
                SQL = "INSERT INTO `film.genre` (`ID_GENRE`, `NAMA_GENRE`) VALUES ('"+id+"', '"+TFGenre.getText()+"'); ";
                stat.executeUpdate(SQL);
                String data[]=new String[2];
                data[0]=id;
                data[1]=TFGenre.getText();
                ModelTabelGenre.insertRow(0, data);
                clearGenre();
                row = tabelGenre.getRowCount();
                LJumlahGenre.setText(String.valueOf(row));
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, "GENRE SUDAH ADA");
                clearGenre();
            }
        }
    }
    
    public void setTabelGenre() throws SQLException{
         fungsi.getDataTabel(2, "SELECT * from `film.genre` order by ID_GENRE desc", ModelTabelGenre);
        cariGenre();
        tabelGenre.setModel(ModelTabelGenre);
        row = tabelGenre.getRowCount();
        LJumlahGenre.setText(String.valueOf(row));
    }
    
    public void cariGenre(){
        TFGenre.setDocument( fungsi.getToUpperCase());
        SorterGenre = new TableRowSorter(ModelTabelGenre);
        tabelGenre.setRowSorter(SorterGenre);
        TFGenre.getDocument().addDocumentListener(new DocumentListener() {
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
                String filter = TFGenre.getText();
                if (filter == null) {
                    SorterGenre.setRowFilter(RowFilter.regexFilter(null));
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
                        SorterGenre.setRowFilter(RowFilter.regexFilter(regex));
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
    private void clearGenre(){
            BSave.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
            TFGenre.setText("");
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
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TFGenre = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        TFCariGenre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelGenre = new javax.swing.JTable();
        BDelete = new javax.swing.JButton();
        BNew = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        LJumlahGenre = new javax.swing.JLabel();
        BEdit = new javax.swing.JButton();
        BNew1 = new javax.swing.JButton();
        BNew2 = new javax.swing.JButton();
        BSave = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/go-back-icon (1).png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(451, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Genre");

        TFGenre.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        TFGenre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFGenreKeyTyped(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TFCariGenre.setFont(new java.awt.Font("Calisto MT", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Calisto MT", 0, 14)); // NOI18N
        jLabel10.setText("Cari");

        tabelGenre.setAutoCreateRowSorter(true);
        tabelGenre.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        tabelGenre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelGenre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelGenreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelGenre);

        BDelete.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        BNew.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        BNew.setText("NEW");
        BNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNewActionPerformed(evt);
            }
        });

        jLabel2.setText("Jumlah Genre");

        LJumlahGenre.setForeground(new java.awt.Color(255, 51, 51));
        LJumlahGenre.setText("Jml");

        BEdit.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BNew1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        BNew1.setText("BACKUP");
        BNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNew1ActionPerformed(evt);
            }
        });

        BNew2.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        BNew2.setText("RESTORE");
        BNew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNew2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(BNew1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BNew2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BDelete))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(LJumlahGenre)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TFCariGenre))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TFCariGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LJumlahGenre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BDelete)
                    .addComponent(BNew)
                    .addComponent(BEdit)
                    .addComponent(BNew1)
                    .addComponent(BNew2))
                .addContainerGap())
        );

        BSave.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        BSave.setText("SAVE");
        BSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFGenre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BSave)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TFGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabelGenreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelGenreMouseClicked
     row = tabelGenre.getSelectedRow();
        row = tabelGenre.convertRowIndexToModel(row);
        TFGenre.setText((String) ModelTabelGenre.getValueAt(row, 1));
        BSave.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);

    }//GEN-LAST:event_tabelGenreMouseClicked

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        row = tabelGenre.getSelectedRow();
        row = tabelGenre.convertRowIndexToModel(row);
        int konfirmasi=JOptionPane.showConfirmDialog(null, "Hapus Genre "+ModelTabelGenre.getValueAt(row, 1)+"?", "Hapus Genre", JOptionPane.YES_NO_OPTION);
        if(konfirmasi==JOptionPane.YES_OPTION){
            try{
                String SQL ="DELETE FROM `film.genre` WHERE ID_GENRE = '"+ModelTabelGenre.getValueAt(row, 0)+"'";
                stat.executeUpdate(SQL);
                //    koneksi.updateDatabase("DELETE FROM genre WHERE GENRE = '"+ModelTabelGenre.getValueAt(row, 0)+"'");
                ModelTabelGenre.removeRow(row);
                row = tabelGenre.getRowCount();
                LJumlahGenre.setText(String.valueOf(row));
                JOptionPane.showMessageDialog(null, "Genre Berhasil Dihapus","Hapus Genre",JOptionPane.INFORMATION_MESSAGE);
                // getComboBoxFilm();
                
                clearGenre();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Tidak Bisa Menghapus Genre "+ModelTabelGenre.getValueAt(row, 0)+"!! \n Genre Terpakai Pada Data Film \n Edit Genre Pada Film Untuk Menghapus /n/n","Tidak Bisa Menghapus",JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(null, ex.getMessage(),"Gagal!",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNewActionPerformed
        // TODO add your handling code here:
        clearGenre();
    }//GEN-LAST:event_BNewActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        if (TFGenre.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan dilengkapi");
        }
        else{
                try {
                    SQL = "UPDATE `film.genre` set "
                    + "NAMA_GENRE = '"+TFGenre.getText()+"'"
                    + "WHERE ID_GENRE = '"+ModelTabelGenre.getValueAt(row, 0)+"'";
                    stat.executeUpdate(SQL);
                    String data[]=new String[2];
                    data[0]=(String) ModelTabelGenre.getValueAt(row, 0);
                    data[1]=TFGenre.getText();
                    ModelTabelGenre.removeRow(row);
                    ModelTabelGenre.insertRow(0, data);

                    clearGenre();
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "NAMA GENRE SUDAH ADA! ");
                    clearGenre();
                }
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void BSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSaveActionPerformed
        // TODO add your handling code here:
        simpan();
    }//GEN-LAST:event_BSaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelFilm());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TFGenreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFGenreKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(karakter==KeyEvent.VK_ENTER){
            simpan();
        }
    }//GEN-LAST:event_TFGenreKeyTyped

    private void BNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNew1ActionPerformed
        // TODO add your handling code here:
        fungsi.BackupDatabase("genre", "BACKUP(GNR)_", "film.genre");
    }//GEN-LAST:event_BNew1ActionPerformed

    private void BNew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNew2ActionPerformed
            try {
                // TODO add your handling code here:
                 koneksi.updateData("DELETE FROM dummykursi");
                 koneksi.updateData("insert into `dummykursi` SELECT ID_Genre, `Nama_Genre` FROM `film.genre` join`film` USING(`ID_Genre`);");
                ///restore
                fungsi.RestoreDatabase("genre");
                //masukan data
                   listID = new Vector();
                   listNm = new Vector();
                String data[] = new String[2];
                int temp = 0;
                res = koneksi.lihatData("SELECT * FROM `dummykursi`");
                while(res.next()){
                    temp = temp+1;
                    data[0]=res.getString(1);
                    data[1]=res.getString(2);
                    listID.add(data[0]);
                    listNm.add(data[1]);
                }
                for(int i = 0; i <temp;i++){
                    String id =  fungsi.setId("SELECT MAX(RIGHT(ID_GENRE,7)) AS NO FROM `film.genre`", "GNR0000001");
                    koneksi.updateData("DELETE FROM `film.genre` where nama_genre = '"+listNm.get(i)+"'");
                    koneksi.updateData("INSERT INTO `film.genre`(ID_Genre,Nama_Genre) values('"+id+"','"+listNm.get(i)+"') ");
                    koneksi.updateData("UPDATE `film` set ID_Genre = (select ID_genre from `film.genre` where nama_genre = '"+listNm.get(i)+"') where ID_Genre = '"+listID.get(i)+"'");
                }
                
                panelidentitas.JS.setViewportView(new panelGenre());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(panelGenre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }//GEN-LAST:event_BNew2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BNew;
    private javax.swing.JButton BNew1;
    private javax.swing.JButton BNew2;
    private javax.swing.JButton BSave;
    private javax.swing.JLabel LJumlahGenre;
    private javax.swing.JTextField TFCariGenre;
    private javax.swing.JTextField TFGenre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelGenre;
    // End of variables declaration//GEN-END:variables
}
