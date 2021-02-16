/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.DataFilm;

import Class.Fungsi;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import org.apache.commons.io.FileUtils;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.ImageIcon;
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
public class panelFilm extends javax.swing.JPanel {

    /**
     * Creates new form panelFilm
     */
    
    String data[];
    ResultSet res = null;
    Fungsi fungsi = new Fungsi();
    private final DefaultTableModel ModelTabelFilm=getDefaultTableFilm();
    private DefaultTableModel getDefaultTableFilm(){
        return new javax.swing.table.DefaultTableModel(
                new Object[][][][][][][]{},
                new String[] {"ID FILM","JUDUL"}
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
    Statement statment = koneksi.getSt();
    private int row = 0, kolom=0,gnt = 0;
  //  private String SQL, inputData, idFilm;
    private Vector list;
    private TableRowSorter SorterFilm;
    
    private String idFilm, judulFilm, tahunFilm, genreFilm, durasiFilm, sutradaraFilm, aktorFilm, deskripsiFilm, gambar=null;
    private String SQL;
    private Vector listGenre;
  //  private int row =0;
    Statement statement;
    DefaultListModel modelList = new DefaultListModel();
    File file;
    JFileChooser jfc;
    
    public panelFilm() {
        initComponents();
        saringKarakterFilm();
        tabelFilm.setModel(ModelTabelFilm);
        getTable();cariFilm();
            //input
        clearText();
    }
    //table
    private void getTable(){
        try {
            res = koneksi.lihatData("SELECT ID_Film,Judul_Film FROM `film` ORDER BY ID_Film");
            data = new String[2];
            while (res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                ModelTabelFilm.addRow(data);                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    private void cariFilm(){
        TFCariFilm.setDocument(fungsi.getToUpperCase());
        SorterFilm = new TableRowSorter(ModelTabelFilm);
        tabelFilm.setRowSorter(SorterFilm);
        TFCariFilm.getDocument().addDocumentListener(new DocumentListener() {
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
                String filter = TFCariFilm.getText();
                if (filter == null) {
                    SorterFilm.setRowFilter(RowFilter.regexFilter(null));
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
                        SorterFilm.setRowFilter(RowFilter.regexFilter(regex));
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + ex.getMessage());
                    }
                }
            }
        });
    }
    //input
    public void getValueFilm(){
        idFilm = LIDFilm.getText();
        judulFilm = TFJudulFilm.getText();
        tahunFilm = String.valueOf(TFTahun.getYear());
        genreFilm = (String) CBGenre.getSelectedItem();
        durasiFilm = TFDurasi.getText();
        sutradaraFilm = TFSutradara.getText();
        deskripsiFilm = TADeskripsi.getText();
        deskripsiFilm = deskripsiFilm.replaceAll("'", "`");
    }
    public void inputFilm(){
        getValueFilm();
        statement = koneksi.getSt();
            SQL = "INSERT INTO `film` (`ID_Film`, `Judul_Film`,`Tahun_Film`, `ID_Genre`, `DurasiFilm`, `Sutradara`, `Deskripsi`,`Poster`)"
                + "VALUES ('"+idFilm+"', '"+judulFilm+"','"+tahunFilm+"', (SELECT ID_GENRE FROM `film.genre` WHERE Nama_Genre = '"+genreFilm+"'), '"+durasiFilm+"', '"+sutradaraFilm+"', '"+deskripsiFilm+"','"+gambar+"'); ";
            System.out.println(SQL);
            try {
            statement.executeUpdate(SQL);
            for (int i = 0; i < ListAktor.getModel().getSize(); i++) {
                String aktorList = ListAktor.getModel().getElementAt(i);
                SQL = "INSERT INTO `film.aktor` (ID_Film, Aktor) VALUES ('"+LIDFilm.getText()+"','"+aktorList+"');";
                koneksi.updateData(SQL);
            }
            try {
                //String path=new File(".").getCanonicalPath();
                FileUtils.copyFileToDirectory(file, new File(koneksi.currentDir+"/folder/foto/Poster"));
            } catch (IOException ex) {
                //Logger.getLogger(panelInputFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            ///opytion
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DITAMBAHKAN");
            clearText();
            panelidentitas.JS.setViewportView(new panelFilm());
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "FILM INI SUDAH ADA! \n\n MASUKAN FILM LAIN!"+ex.getMessage());
        }
    }
    private void clearText(){
        try {
            TFJudulFilm.setText("");
            TFTahun.setYear(2018);
            CBGenre.setSelectedItem(null);
            TFDurasi.setText("");
            TFSutradara.setText("");
            TADeskripsi.setText("");
            TFAktor.setText("");
            LIDFilm.setText(fungsi.setId("SELECT MAX(RIGHT(ID_FILM,7)) AS NO FROM film", "FLM0000001"));
            modelList.removeAllElements();
            TFJudulFilm.requestFocus();
            jLabelGambar.setIcon(null);
            jButton4.setEnabled(true);
            this.gambar=null;
            
            //menu
            fungsi.setComboBox("SELECT Nama_Genre FROM `film.genre`", CBGenre, listGenre);
            LIDFilm.setText(fungsi.setId("SELECT MAX(RIGHT(ID_FILM,7)) AS NO FROM film", "FLM0000001"));
            //tabel
            
            row = tabelFilm.getRowCount();
            LJumlahFilm.setText(String.valueOf(row));
            jButton8.setEnabled(false);
            jButton10.setEnabled(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    private void saringKarakterFilm(){
        TFJudulFilm.setDocument(fungsi.getToUpperCase());
        TFDurasi.setDocument(fungsi.getsNumber());
        TFSutradara.setDocument(fungsi.getToUpperCase());
        TFAktor.setDocument(fungsi.getToUpperCase());
    }
    
    //edit
    public void tampilFilm() throws SQLException{
        getAktor();
        data = new String[8];
        SQL = "SELECT ID_Film, Judul_Film, Tahun_Film, Nama_Genre, DurasiFilm, Sutradara, Deskripsi, Poster  FROM `film`, `film.genre` "
            + "WHERE ID_Film = '"+ModelTabelFilm.getValueAt(row, 0)+"' AND `film`.`ID_Genre`=`film.genre`.`ID_Genre` ORDER BY ID_Film";
        data =  koneksi.ambilData(8, SQL);
        LIDFilm.setText(data[0]);
        TFJudulFilm.setText(data[1]);
        TFTahun.setYear(Integer.parseInt(data[2].substring(0, 4)));
        CBGenre.setSelectedItem(data[3]);
        TFDurasi.setText(data[4]);
        TFSutradara.setText(data[5]);
        TADeskripsi.setText(data[6]);
        jLabelGambar.setIcon( fungsi.gtGambar("Poster", "SELECT Poster FROM film WHERE ID_Film = '"+idFilm+"'",138,166));
        gambar =data[7];
        this.file=new File(gambar);
    }
    public void getAktor() throws SQLException{
        modelList = new DefaultListModel();
        data = new String[1];
        SQL = "SELECT Aktor FROM `film.aktor` WHERE ID_Film = '"+ModelTabelFilm.getValueAt(row, 0)+"'";
            res =  koneksi.lihatData(SQL);
            while (res.next()){
                data[0]= res.getString(1);
                modelList.addElement(data[0]);
            }
            ListAktor.setModel(modelList);
     }
    public void editFilm() throws SQLException{
        getValueFilm();
        statement =  koneksi.getSt();
        SQL = "UPDATE `film` SET `Judul_Film` = '"+judulFilm+"' , `Tahun_Film` = '"+tahunFilm+"' , `ID_Genre` = (SELECT ID_GENRE FROM `film.genre` WHERE Nama_Genre = '"+genreFilm+"') , `DurasiFilm` = '"+durasiFilm+"' , `Sutradara` = '"+sutradaraFilm+"' , `Deskripsi` = '"+deskripsiFilm+"' , `Poster` = '"+gambar+"' WHERE `ID_Film` = '"+idFilm+"'; ";
        try {
            statement.executeUpdate(SQL);
             koneksi.updateData(" DELETE FROM `film.aktor` WHERE `ID_Film` = '"+idFilm+"'");
            for (int i = 0; i < ListAktor.getModel().getSize(); i++) {
                String aktorList = ListAktor.getModel().getElementAt(i);
                SQL = "INSERT INTO `film.aktor` (ID_Film, Aktor) VALUES ('"+LIDFilm.getText()+"','"+aktorList+"');";
                 koneksi.updateData(SQL);
            }
            try {
                //String path=new File(".").getCanonicalPath();
                //File fileLama = new File("./src/Image/Poster/"+file);
                //fileLama.delete();
                FileUtils.copyFileToDirectory(file, new File(koneksi.currentDir+"/folder/foto/Poster"));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
               // Logger.getLogger(panelInputFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI EDIT");
            panelidentitas.JS.setViewportView(new panelFilm());
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "FILM INI SUDAH ADA! \n\n MASUKAN FILM LAIN!");
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
        jPanel5 = new javax.swing.JPanel();
        TFSutradara = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TFTahun = new com.toedter.calendar.JYearChooser();
        LIDFilm = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        CBGenre = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        TFAktor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TADeskripsi = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        TFDurasi = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListAktor = new javax.swing.JList<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelGambar = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        TFJudulFilm = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        LJumlahFilm = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelFilm = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TFCariFilm = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

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
                .addContainerGap(479, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        TFSutradara.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel10.setText("SUTRADARA");

        LIDFilm.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LIDFilm.setForeground(new java.awt.Color(255, 51, 51));
        LIDFilm.setText("ID FILM");

        jLabel11.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel11.setText("POSTER");

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel2.setText("JUDUL FILM");

        jButton5.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jButton5.setText("PILIH GAMBAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        CBGenre.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel4.setText("GENRE");

        TFAktor.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        TFAktor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFAktorKeyTyped(evt);
            }
        });

        TADeskripsi.setColumns(20);
        TADeskripsi.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        TADeskripsi.setLineWrap(true);
        TADeskripsi.setRows(1);
        TADeskripsi.setTabSize(1);
        jScrollPane2.setViewportView(TADeskripsi);

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel5.setText("DURASI");

        TFDurasi.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/viewmagplus_view_search_find_11034.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel1.setText("ID FILM");

        jLabel8.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel8.setText("DESKRIPSI");

        jLabel9.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel9.setText("Menit");

        jLabel3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel3.setText("TAHUN");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/movie-track-add-icon.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/document_delete_256_icon-icons.com_75995.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/document_add_256_icon-icons.com_75994.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel7.setText("AKTOR");

        ListAktor.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(ListAktor);

        jLabelGambar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelGambar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TFJudulFilm.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit-validated-icon.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon (1).png"))); // NOI18N
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(66, 66, 66)
                            .addComponent(LIDFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton10)
                                    .addGap(0, 0, 0)
                                    .addComponent(jButton4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jButton11))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TFAktor)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(73, 73, 73))
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                                    .addComponent(TFDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(jLabel9))
                                                                .addComponent(CBGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(TFTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(TFSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(TFJudulFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)))
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(114, 114, 114)))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LIDFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TFJudulFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(TFTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CBGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(TFDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFSutradara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TFAktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(jLabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(462, 500));

        jLabel6.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel6.setText("Jumlah Film");

        LJumlahFilm.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LJumlahFilm.setForeground(new java.awt.Color(255, 51, 51));
        LJumlahFilm.setText("Jumlah Film");

        tabelFilm.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        tabelFilm.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelFilmMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelFilm);

        jLabel13.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel13.setText("Cari");

        jLabel14.setFont(new java.awt.Font("Baskerville Old Face", 0, 12)); // NOI18N
        jLabel14.setText("*) Klik 2x Untuk Lihat Deskripsi");

        TFCariFilm.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tw Cen MT", 0, 10)); // NOI18N
        jButton12.setText("BACKUP");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tw Cen MT", 0, 10)); // NOI18N
        jButton13.setText("RESTORE");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        jButton7.setText("HAPUS SEMUA");
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(LJumlahFilm))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(TFCariFilm))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8))
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel13))
                    .addComponent(TFCariFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(LJumlahFilm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addComponent(jButton8))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jfc=new JFileChooser();
        if(jfc.showOpenDialog(jLabelGambar)==JFileChooser.APPROVE_OPTION){

            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imagedResized=image.getScaledInstance(138, 166, Image.SCALE_DEFAULT);
            ImageIcon imageIcon=new ImageIcon(imagedResized);
            jLabelGambar.setIcon(imageIcon);
            this.gambar = jfc.getSelectedFile().getName();

            file=new File(jfc.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TFAktorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFAktorKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(karakter==KeyEvent.VK_ENTER){
            if(TFAktor.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "DATA AKTOR BELUM TERISI \n\n ISI DATA");
            }else{
                modelList.addElement(TFAktor.getText());
                ListAktor.setModel(modelList);
                TFAktor.setText("");
                TFAktor.requestFocus();
            }
        }else if(karakter==KeyEvent.VK_TAB){
            TADeskripsi.requestFocus();
        }
    }//GEN-LAST:event_TFAktorKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            panelidentitas.JS.setViewportView(new panelGenre());
        } catch (SQLException ex) {
            Logger.getLogger(panelFilm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (TFJudulFilm.getText().isEmpty() || CBGenre.getSelectedItem() == null || TFDurasi.getText().isEmpty() || TFSutradara.getText().isEmpty() || ListAktor.getModel().getSize()==0 ||   TADeskripsi.getText().isEmpty() || jLabelGambar.getIcon()==null){
            JOptionPane.showMessageDialog(null, "DATA BELUM LENGKAP \n\n LENGKAPI DATA");
            TFJudulFilm.requestFocus();
        }else{
            inputFilm();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        row = ListAktor.getSelectedIndex();
        modelList.remove(row);
        ListAktor.setModel(modelList);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(TFAktor.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "DATA AKTOR BELUM TERISI \n\n ISI DATA");
        }else{
            String aktor = TFAktor.getText();
            String replaceAktor = aktor.replaceAll("'", "`");
            modelList.addElement(replaceAktor);
            ListAktor.setModel(modelList);
            TFAktor.setText("");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tabelFilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelFilmMouseClicked
        try {
            // TODO add your handling code here:
            row = tabelFilm.getSelectedRow();
            row = tabelFilm.convertRowIndexToModel(row);
            kolom = tabelFilm.getSelectedColumn();
            kolom = tabelFilm.convertColumnIndexToModel(kolom);
            idFilm = (String) ModelTabelFilm.getValueAt(row, 0);
            jButton10.setEnabled(true);
            jButton8.setEnabled(true);
            jButton4.setEnabled(false);
            //tampil film
             try {
                 koneksi.updateData("DELETE FROM dummyHelp");
                 koneksi.updateData("INSERT INTO `dummyHelp` (`akses`) VALUES ('"+idFilm+"');");
            } catch (SQLException ex) {
              //  Logger.getLogger(panelTabelFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
            tampilFilm();
            ////////////////////////////////////////
            if(evt.getClickCount()==2){
                try {
                    panelidentitas.JS.setViewportView(new panelDeskripsiFilm());
                } catch (SQLException ex) {
               //     Logger.getLogger(panelTabelFilm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(panelFilm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tabelFilmMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if (TFJudulFilm.getText().isEmpty() || CBGenre.getSelectedItem() == null || TFDurasi.getText().isEmpty() || TFSutradara.getText().isEmpty() || ListAktor.getModel().getSize()==0 ||   TADeskripsi.getText().isEmpty() || jLabelGambar.getIcon()==null){
            JOptionPane.showMessageDialog(null, "DATA BELUM LENGKAP \n\n LENGKAPI DATA");
            TFJudulFilm.requestFocus();
        }else{
            try {
                editFilm();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelUtamaDataFilm());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        clearText();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        fungsi.BackupDatabase("film", "BACKUP(FLM)_", "film film.aktor");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        fungsi.RestoreDatabase("film");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         int konfirmasi=JOptionPane.showConfirmDialog(null, "YAKIN INGIN MENGHAPUS FILM INI? "+tabelFilm.getValueAt(row, 1)+"?", "Hapus Film", JOptionPane.YES_NO_OPTION);
        if(konfirmasi==JOptionPane.YES_OPTION){
            SQL ="DELETE FROM film WHERE ID_FILM = '"+ModelTabelFilm.getValueAt(row, 0)+"'";
            try {
                statment.executeUpdate(SQL);
                //    koneksi.updateDatabase("DELETE FROM genre WHERE GENRE = '"+ModelTabelGenre.getValueAt(row, 0)+"'");
                ModelTabelFilm.removeRow(row);
                row = tabelFilm.getRowCount();
                LJumlahFilm.setText(String.valueOf(row));
                JOptionPane.showMessageDialog(null, "FILM BERHASIL DIHAPUS \n\n","Hapus Film",JOptionPane.INFORMATION_MESSAGE);
                clearText();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "FILM INI SEDANG DALAM JADWAL TAYANG \n\n GANTI JADWAL TAYANG UNTUK MENGHAPUS FILM INI","TIDAK BISA MENGHAPUS FILM "+ModelTabelFilm.getValueAt(row, 0),JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         int konfirm=JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Semua Data? \n\n Data Akan Di Backup Otomatis", "Hapus Semua Data (Not Recomended)", JOptionPane.YES_NO_OPTION);
        if(konfirm==JOptionPane.YES_OPTION)
        {
            try {
                koneksi.updateData("DELETE FROM film");
                //backup
                row = tabelFilm.getRowCount();
                for(int i=0;i<row;i++){
                    ModelTabelFilm.removeRow(0);
                }
                JOptionPane.showMessageDialog(null, "Semua Data Film Telah Dihapus \n\n dan Otomatis Backup","Hapus Semua Data Film",JOptionPane.INFORMATION_MESSAGE);
                clearText();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "SALAH SATU FILM SEDANG DALAM JADWAL TAYANG \n\n HAPUS JADWAL TAYANG UNTUK MENGHAPUS FILM INI","TIDAK BISA MENGHAPUS FILM "+ModelTabelFilm.getValueAt(row, 0),JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBGenre;
    private javax.swing.JLabel LIDFilm;
    private javax.swing.JLabel LJumlahFilm;
    private javax.swing.JList<String> ListAktor;
    private javax.swing.JTextArea TADeskripsi;
    private javax.swing.JTextField TFAktor;
    private javax.swing.JTextField TFCariFilm;
    private javax.swing.JTextField TFDurasi;
    private javax.swing.JTextField TFJudulFilm;
    private javax.swing.JTextField TFSutradara;
    private com.toedter.calendar.JYearChooser TFTahun;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelGambar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable tabelFilm;
    // End of variables declaration//GEN-END:variables
}
