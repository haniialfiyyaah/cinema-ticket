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
import javax.swing.DefaultListModel;
import javax.swing.JList;
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
public class panelJadwal extends javax.swing.JPanel {

    /**
     * Creates new form panelJadwal
     */
    private Vector listFilm;
    private String idJadwal, studioJadwal, filmJadwal, hargaJadwal;
    private Integer jam1,menit1,jam2,menit2,selisih,durasi;
    private String SQL,inputJam,inputMenit;
    private Boolean cek;
   // int row = 0;
    Statement statement;
    DefaultListModel modelList = new DefaultListModel();
    private final DefaultTableModel ModelTabelJadwal=getDefaultTableFilm();
    private DefaultTableModel getDefaultTableFilm(){
        return new javax.swing.table.DefaultTableModel(
                new Object[][][][][]{},
                new String[] {"ID FILM","FILM","STUDIO","HARGA"}
        ){
            boolean[] canEdit = new boolean[]{
                false,false,false,false
            };
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    Fungsi fungsi = new Fungsi();
    String data[] = new String[0];
    JList list = new JList();
    Vector listV = new Vector();
        public int row = 0, kolom = 0,gnt =0;
        public String inputData;
        public Vector listBCFilm,listTampilJadwal;
    private TableRowSorter SorterJadwal;
    ResultSet res;
    
    public panelJadwal() {
            initComponents();
            tampil();
    }
    
    private void tampil(){
        try {
            saringKarakterJadwal();
            //table
            fungsi.getDataTabel(4, "SELECT ID_Jadwal,Judul_Film, Studio, Harga  FROM `jadwal`,`film` WHERE `jadwal`.`ID_Film`=`film`.`ID_Film` ORDER BY `ID_Jadwal`", ModelTabelJadwal);
            tabelJadwal.setModel(ModelTabelJadwal);
            cariJadwal();
            clearText();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    //input
    public void inputJadwal(){
        getValueJadwal();
        statement = koneksi.getSt();
        try {
            SQL = "INSERT INTO `jadwal` (`ID_Jadwal`, `ID_Film`, `Studio`, `Harga`) "
            + "VALUES ('"+idJadwal+"', (SELECT ID_Film from film WHERE Judul_Film = '"+filmJadwal+"'), '"+studioJadwal+"', '"+hargaJadwal+"'); ";
            statement.executeUpdate(SQL);
            for (int i = 0; i < ListJamTayang1.getModel().getSize(); i++) {
                String jadwalList = ListJamTayang1.getModel().getElementAt(i);
                SQL = "INSERT INTO `jadwal.jam` (`ID_Jadwal`, `Jam_Tayang`) VALUES ('"+idJadwal+"', '"+jadwalList+"')";
                koneksi.updateData(SQL);
            }
            
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DITAMBAHKAN");
            clearText();
            panelidentitas.JS.setViewportView(new panelJadwal());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "STUDIO SUDAH TERISI! \n\n GANTI STUDIO!");
        }
    }
    public void getValueJadwal(){
        idJadwal = LIDJadwal1.getText();
        studioJadwal = (String) CBStudio1.getSelectedItem();
        filmJadwal = (String) CBFilm1.getSelectedItem();
        hargaJadwal = TFHarga1.getText();
    }
    private void saringKarakterJadwal(){
        TFHarga1.setDocument( fungsi.getsNumber());
        Jam2.setDocument( fungsi.getsNumber());
        Jam3.setDocument( fungsi.getsNumber());
        Jam2.setDocument(new Limit(2));
        Jam3.setDocument(new Limit(2));
    }
    private void clearText(){
        try {
            LIDJadwal1.setText( fungsi.setId("SELECT MAX(RIGHT(ID_Jadwal,7)) AS NO FROM jadwal", "JDL0000001"));
            CBStudio1.setSelectedItem(null);
            CBFilm1.setSelectedItem(null);
            TFHarga1.setText("");
            Jam2.setText("");
            Jam3.setText("");
            modelList.removeAllElements();
            CBStudio1.requestFocus();
            jButton13.setEnabled(false);
            jButton10.setEnabled(false);
            jButton9.setEnabled(true);
            
            fungsi.setComboBox("SELECT Judul_Film FROM film", CBFilm1, listFilm);
            LIDJadwal1.setText( fungsi.setId("SELECT MAX(RIGHT(ID_Jadwal,7)) AS NO FROM jadwal", "JDL0000001"));
            row = tabelJadwal.getRowCount();    
            LJumlahJadwal.setText(String.valueOf(row));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void cariJadwal(){
        TFCariJadwal.setDocument( fungsi.getToUpperCase());
        SorterJadwal = new TableRowSorter(ModelTabelJadwal);
        tabelJadwal.setRowSorter(SorterJadwal);
        TFCariJadwal.getDocument().addDocumentListener(new DocumentListener() {
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
                String filter = TFCariJadwal.getText();
                if (filter == null) {
                    SorterJadwal.setRowFilter(RowFilter.regexFilter(null));
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
                        SorterJadwal.setRowFilter(RowFilter.regexFilter(regex));
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + ex.getMessage());
                    }
                }
            }
        });
    }
    
    //edit
    public void tampilJadwal() throws SQLException{
        getJam();
        data = new String[4];
        SQL ="SELECT `ID_Jadwal`, `Judul_Film` , `Studio`,`Harga` FROM `jadwal` JOIN `film` USING (`ID_Film`) WHERE `ID_Jadwal`='"+ModelTabelJadwal.getValueAt(row, 0)+"'";
        data = koneksi.ambilData(4, SQL);
        LIDJadwal1.setText(data[0]);
        CBFilm1.setSelectedItem(data[1]);
        CBStudio1.setSelectedItem(data[2]);
        TFHarga1.setText(data[3]);
    }
    public void getJam() throws SQLException{
        modelList = new DefaultListModel();
        data = new String[1];
        SQL = "SELECT `Jam_Tayang` FROM `jadwal.jam` WHERE ID_Jadwal = '"+ModelTabelJadwal.getValueAt(row, 0)+"'";
            res = koneksi.lihatData(SQL);
            while (res.next()){
                data[0]= res.getString(1).substring(0, 5);
                modelList.addElement(data[0]);
            }
            ListJamTayang1.setModel(modelList);
     }
    public void editFilm() throws SQLException{
        getValueJadwal();
        statement = koneksi.getSt();
        SQL = "UPDATE `jadwal` SET `ID_Film` = (select id_film from film where judul_film='"+filmJadwal+"') , `STUDIO` = '"+studioJadwal+"' , `Harga` = '"+hargaJadwal+"' WHERE `ID_Jadwal` = '"+idJadwal+"'; ";
        try {
            statement.executeUpdate(SQL);
            koneksi.updateData(" DELETE FROM `jadwal.jam` WHERE `ID_Jadwal` = '"+idJadwal+"'");
            for (int i = 0; i < ListJamTayang1.getModel().getSize(); i++) {
                String aktorList = ListJamTayang1.getModel().getElementAt(i);
                SQL = "INSERT INTO `jadwal.jam` (ID_Jadwal, Jam_Tayang) VALUES ('"+idJadwal+"','"+aktorList+"');";
                koneksi.updateData(SQL);
            }
            
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI EDIT");
            panelidentitas.JS.setViewportView(new panelJadwal());
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
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
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        Clear1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LIDJadwal1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TFHarga1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CBStudio1 = new javax.swing.JComboBox<>();
        CBFilm1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        Jam2 = new javax.swing.JTextField();
        Jam3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListJamTayang1 = new javax.swing.JList<>();
        jSeparator6 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        LJumlahJadwal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TFCariJadwal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelJadwal = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

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
                .addContainerGap(447, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jLabel6.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel6.setText("HARGA");

        jLabel4.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel4.setText("STUDIO");

        LIDJadwal1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LIDJadwal1.setForeground(new java.awt.Color(255, 51, 51));
        LIDJadwal1.setText("ID JADWAL");

        jLabel8.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel8.setText("ID JADWAL");

        TFHarga1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel9.setText("FILM");

        CBStudio1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBStudio1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STUDIO 1", "STUDIO 2", "STUDIO 3", "STUDIO 4", "STUDIO 5" }));
        CBStudio1.setSelectedIndex(-1);
        CBStudio1.setSelectedItem(null);

        CBFilm1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBFilm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel13.setText("JAM TAYANG");

        Jam2.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        Jam3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        Jam3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jamKey(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText(":");

        ListJamTayang1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jScrollPane4.setViewportView(ListJamTayang1);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/document_add_256_icon-icons.com_75994.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/document_delete_256_icon-icons.com_75995.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/movie-track-add-icon.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon (1).png"))); // NOI18N
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit-validated-icon.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 28, Short.MAX_VALUE)
                                .addComponent(CBStudio1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TFHarga1)
                                    .addComponent(CBFilm1, 0, 366, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(Jam2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Jam3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9))))
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Clear1)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(31, 31, 31)
                                        .addComponent(LIDJadwal1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LIDJadwal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CBStudio1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CBFilm1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Jam2)
                        .addComponent(Jam3)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Clear1))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, jLabel6});

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        LJumlahJadwal.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LJumlahJadwal.setForeground(new java.awt.Color(255, 51, 51));
        LJumlahJadwal.setText("Jumlah Film");

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel5.setText("Jumlah Film Tayang");

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel2.setText("Cari");

        TFCariJadwal.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N

        tabelJadwal.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelJadwalMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelJadwal);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        jButton12.setText("HAPUS SEMUA");
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        jButton13.setContentAreaFilled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LJumlahJadwal)
                                        .addGap(0, 143, Short.MAX_VALUE))
                                    .addComponent(TFCariJadwal)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(6, 6, 6))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFCariJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LJumlahJadwal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(CBFilm1.getSelectedItem()==null){
            JOptionPane.showMessageDialog(null, "UNTUK MENGISI JAM TAYANG, FILM HARUS TERISI \n\n PILIH FILM");
        }else{
            if(Jam2.getText().isEmpty() || Jam3.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "JAM TAYANG BELUM TERISI \n\n ISI DATA");
            }else{
                inputJam = Jam2.getText();inputMenit= Jam3.getText();
                int jm = Integer.valueOf(inputJam);
                int mnt = Integer.valueOf(inputMenit);
                if(jm > 23 || mnt > 59 ){
                    JOptionPane.showMessageDialog(null, "FORMAT JAM YANG ANDA MASUKAN SALAH \n\n HARAP PERBAIKI");
                    Jam2.setText("");Jam3.setText("");
                }else{
                    /////
                    if(ListJamTayang1.getModel().getSize()!=0){
                        for(int i = 0; i < ListJamTayang1.getModel().getSize(); i++){
                            jam1 = Integer.valueOf(Jam2.getText());
                            menit1 = Integer.valueOf(Jam3.getText());
                            jam2 = Integer.valueOf(ListJamTayang1.getModel().getElementAt(i).substring(0, 2));
                            menit2 = Integer.valueOf(ListJamTayang1.getModel().getElementAt(i).substring(3, 5));

                            this.selisih = fungsi.getSelisihJam(jam1, menit1, jam2, menit2);
                            try {
                                this.durasi = fungsi.getDurasi("SELECT `DurasiFilm` FROM `film` WHERE `ID_Film` = (SELECT ID_Film FROM film WHERE Judul_Film = '"+CBFilm1.getSelectedItem()+"')");
                            } catch (SQLException ex) {
                                Logger.getLogger(panelJadwal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(selisih < durasi){
                                this.cek=false;
                                break;
                            }else{
                                this.cek=true;
                            }
                        }
                        if(cek==true){
                            modelList.addElement(inputJam+":"+inputMenit);
                            ListJamTayang1.setModel(modelList);
                            Jam2.setText("");Jam3.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "JAM YANG ANDA MASUKAN AKAN BENTROK DENGAN JAM LAIN \n\n DURASI UNTUK FILM INI ADALAH "+durasi+" menit");
                        }
                        //jLabel4.setText(String.valueOf(this.durasi));
                    }else{
                        modelList.addElement(inputJam+":"+inputMenit);
                        ListJamTayang1.setModel(modelList);
                        Jam2.setText("");Jam3.setText("");
                    }

                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        row = ListJamTayang1.getSelectedIndex();
        modelList.remove(row);
        ListJamTayang1.setModel(modelList);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (CBStudio1.getSelectedItem() == null ||CBFilm1.getSelectedItem() == null ||TFHarga1.getText().isEmpty() || ListJamTayang1.getModel().getSize()==0 ){
            JOptionPane.showMessageDialog(null, "DATA BELUM LENGKAP \n\n LENGKAPI DATA");
        }else{
            inputJadwal();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tabelJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelJadwalMouseClicked
        try {
            // TODO add your handling code here:
            row = tabelJadwal.getSelectedRow();
            row = tabelJadwal.convertRowIndexToModel(row);
            kolom = tabelJadwal.getSelectedColumn();
            kolom = tabelJadwal.convertColumnIndexToModel(kolom);
            jButton13.setEnabled(true);
        jButton10.setEnabled(true);
        jButton9.setEnabled(false);
            tampilJadwal();
        } catch (SQLException ex) {
            Logger.getLogger(panelJadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_tabelJadwalMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        clearText();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if (CBStudio1.getSelectedItem() == null ||CBFilm1.getSelectedItem() == null ||TFHarga1.getText().isEmpty() || ListJamTayang1.getModel().getSize()==0 ){
            JOptionPane.showMessageDialog(null, "DATA BELUM LENGKAP \n\n LENGKAPI DATA");
        }else{
            try {
                editFilm();
            } catch (SQLException ex) {
                Logger.getLogger(panelJadwal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelUtamaDataFilm());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jamKey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKey
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter>='0') && (karakter <= '9') || karakter == KeyEvent.VK_BACK_SPACE || (karakter == KeyEvent.VK_DELETE)|| karakter==KeyEvent.VK_ENTER))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jamKey

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        row = tabelJadwal.getSelectedRow();
        row = tabelJadwal.convertRowIndexToModel(row);
        int konfirmasi=JOptionPane.showConfirmDialog(null, "YAKIN INGIN MENGHAPUS JADWAL INI? "+ModelTabelJadwal.getValueAt(row, 1)+"?", "Hapus Jadwal", JOptionPane.YES_NO_OPTION);
        if(konfirmasi==JOptionPane.YES_OPTION){
            SQL ="DELETE FROM jadwal WHERE ID_Jadwal = '"+ModelTabelJadwal.getValueAt(row, 0)+"'";
            try {
                koneksi.updateData(SQL);
            } catch (SQLException ex) {
                Logger.getLogger(panelJadwal.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearText();
            ModelTabelJadwal.removeRow(row);
            JOptionPane.showMessageDialog(null, "JADWAL BERHASIL DIHAPUS \n\n","Hapus Jadwal",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
         int konfirm=JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Semua Data?", "Hapus Semua Data (Not Recomended)", JOptionPane.YES_NO_OPTION);
        if(konfirm==JOptionPane.YES_OPTION)
        {
            try {
                koneksi.updateData("DELETE FROM jadwal");
                row = tabelJadwal.getRowCount();
                for(int i=0;i<row;i++){
                    ModelTabelJadwal.removeRow(0);
                }
                JOptionPane.showMessageDialog(null, "Semua Jadwal Telah Dihapus \n\n ","Hapus Semua Jadwal",JOptionPane.INFORMATION_MESSAGE);
                clearText();
            } catch (SQLException ex) {
                Logger.getLogger(panelFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBFilm1;
    private javax.swing.JComboBox<String> CBStudio1;
    private javax.swing.JLabel Clear1;
    private javax.swing.JTextField Jam2;
    private javax.swing.JTextField Jam3;
    private javax.swing.JLabel LIDJadwal1;
    private javax.swing.JLabel LJumlahJadwal;
    private javax.swing.JList<String> ListJamTayang1;
    private javax.swing.JTextField TFCariJadwal;
    private javax.swing.JTextField TFHarga1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable tabelJadwal;
    // End of variables declaration//GEN-END:variables
}
