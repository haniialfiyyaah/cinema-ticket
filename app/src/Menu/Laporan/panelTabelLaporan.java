/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Laporan;

import Class.Fungsi;
import Main.formMenu;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Hanii
 */
public class panelTabelLaporan extends javax.swing.JPanel {

    /**
     * Creates new form panelTabelLaporan
     */
    Fungsi fungsi = new Fungsi();
    dialogCetak dialogcetak = new dialogCetak(formMenu.menu, true);
    private TableRowSorter SorterTransaksi;
    private int row = 0, total = 0;
    String data[] = new String[0];
    String idStruk,kursi;
     public DefaultTableModel ModelTabelTransaksi=getDefaultTableTransaksi();
    private DefaultTableModel getDefaultTableTransaksi(){
        return new javax.swing.table.DefaultTableModel(
                new Object[][][][][][]{},
                new String[] {"ID STRUK","TANGGAL BELI","JUMLAH ITEM","TOTAL BAYAR","NAMA KASIR"}
        ){
            boolean[] canEdit = new boolean[]{
                false,false,false,false,false
            };
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    public panelTabelLaporan() {
        try {
            initComponents();
            setTabelTransaksi();
            jPanel5.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(panelTabelLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTabelTransaksi() throws SQLException{
        fungsi.getDataTabel(5, "SELECT `ID_Struk`,`Tanggal_Beli`,`Jumlah_Item`,`Total_Bayar`,`Nama_Kasir` FROM `strukpembayaran`", ModelTabelTransaksi);
        BHapusTransaksi.setEnabled(false);
        tabelTransaksi.setModel(ModelTabelTransaksi);
        cariTransaksi();setLabel();
        
    }
    
    public  void totalTransaski() throws SQLException{
        total = 0;
        ResultSet res = koneksi.lihatData("SELECT `Total_Bayar` FROM `strukpembayaran`");
        data = new String[1];
        try{
            while(res.next()){
                if(total==0){
                    this.total = res.getInt(1);
                }else{
                    this.total = total + res.getInt(1);
                }
            }
        }catch(Exception e){
            
        }
        
    }
    public void setLabel() throws SQLException{
        totalTransaski();
        row = tabelTransaksi.getRowCount();
        LJumlahTransaksi.setText(String.valueOf(row));
        LJumlahTransaksi1.setText("Rp. "+String.valueOf(total));
    }
    public void cariTransaksi(){
        TFCariTransaksi.setDocument(fungsi.getToUpperCase());
        SorterTransaksi = new TableRowSorter(ModelTabelTransaksi);
        tabelTransaksi.setRowSorter(SorterTransaksi);
        TFCariTransaksi.getDocument().addDocumentListener(new DocumentListener() {
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
                String filter = TFCariTransaksi.getText();
                if (filter == null) {
                    SorterTransaksi.setRowFilter(RowFilter.regexFilter(null));
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
                        SorterTransaksi.setRowFilter(RowFilter.regexFilter(regex));
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + ex.getMessage());
                    }
                }
            }
        });
    }
   
    public void setTampil() throws SQLException{
        jPanel5.setVisible(true);
        String data[] = new String[11];
        data = koneksi.ambilData(10,"SELECT * FROM `strukpembayaran` where id_struk='"+ModelTabelTransaksi.getValueAt(row, 0)+"'");
        
        String datas[] = new String[5];
        datas = koneksi.ambilData(9,"SELECT * FROM `relasi_pesantiket` WHERE ID_Struk='"+ModelTabelTransaksi.getValueAt(row, 0)+"';");
        
        LNoFaktur.setText(data[0]);
        LKasir.setText(data[9]);
        TglSkrng.setText(data[1]);
        JamSkrng.setText(data[2]);
        LJumlahTiket.setText(data[3]+" TIKET");
        LTotal.setText("Rp. "+data[4]);
        LDiskon.setText(data[5]+" %");
        LTotalBayar.setText("Rp. "+data[6]);
        LBayar.setText("Rp. "+data[7]);
        LKembali.setText("Rp. "+data[8]);
        
        LJudulFilm.setText(datas[4]);
        LHarga.setText(datas[7]);
        LTanggalFlm.setText(datas[5]);
        LJamFlm.setText(datas[6]);
        LStudio.setText(datas[8]);
        
            String SQL ="SELECT `Kursi` FROM `relasi_pesantiket.kursi` WHERE `ID_Tiket`=(select id_tiket from `relasi_pesantiket` where id_struk ='"+ModelTabelTransaksi.getValueAt(row, 0)+"')";
            
            ResultSet res = koneksi.lihatData(SQL);
            kursi="";
            while(res.next()){
                if(kursi.equals("")){
                    this.kursi = res.getString(1);
                }else{
                    this.kursi = kursi+", "+res.getString(1);
                }
            }
        LKursi.setText(kursi);
        
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
        TFCariTransaksi = new javax.swing.JTextField();
        LJumlahTransaksi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LJumlahTransaksi1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        BHapusTransaksi = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        LNoFaktur = new javax.swing.JLabel();
        TglSkrng = new javax.swing.JLabel();
        JamSkrng = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        LKasir = new javax.swing.JLabel();
        LJudulFilm = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LTanggalFlm = new javax.swing.JLabel();
        LJamFlm = new javax.swing.JLabel();
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
        jSeparator6 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        LTotalBayar = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        LKembali = new javax.swing.JLabel();
        LDiskon = new javax.swing.JLabel();
        LBayar = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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
                .addContainerGap(489, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        TFCariTransaksi.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        LJumlahTransaksi.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LJumlahTransaksi.setForeground(new java.awt.Color(255, 51, 51));
        LJumlahTransaksi.setText("Jumlah Film");

        jLabel4.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel4.setText("Jumlah Transaksi");

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelTransaksi);

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 0, 16)); // NOI18N
        jLabel2.setText("Cari");

        jLabel6.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel6.setText("Total Pemasukan");

        LJumlahTransaksi1.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LJumlahTransaksi1.setForeground(new java.awt.Color(255, 51, 51));
        LJumlahTransaksi1.setText("Jumlah Film");

        jButton6.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jButton6.setText("BACKUP");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jButton7.setText("RESTORE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jButton8.setText("CETAK DATA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        BHapusTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        BHapusTransaksi.setContentAreaFilled(false);
        BHapusTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHapusTransaksiActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Actions-edit-clear-icon.png"))); // NOI18N
        jButton12.setText("HAPUS SEMUA");
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LJumlahTransaksi)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LJumlahTransaksi1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TFCariTransaksi)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BHapusTransaksi)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton6, jButton7});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TFCariTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LJumlahTransaksi1)
                    .addComponent(jLabel4)
                    .addComponent(LJumlahTransaksi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton12)
                        .addComponent(BHapusTransaksi)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        jLabel3.setText("No Transaksi");

        LNoFaktur.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        LNoFaktur.setForeground(new java.awt.Color(255, 0, 51));
        LNoFaktur.setText("jLabel3");

        TglSkrng.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        TglSkrng.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TglSkrng.setText("Tanggal Pembelian");

        JamSkrng.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        JamSkrng.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JamSkrng.setText("Jam Beli");

        jLabel5.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        jLabel5.setText("Kasir");

        LKasir.setFont(new java.awt.Font("Calisto MT", 0, 12)); // NOI18N
        LKasir.setForeground(new java.awt.Color(255, 0, 51));
        LKasir.setText("LKasir");

        LJudulFilm.setFont(new java.awt.Font("Ebrima", 0, 15)); // NOI18N
        LJudulFilm.setText("Judul Film");

        jLabel13.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        jLabel13.setText("Tanggal");

        LTanggalFlm.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LTanggalFlm.setText("Tanggal");

        LJamFlm.setFont(new java.awt.Font("Ebrima", 0, 10)); // NOI18N
        LJamFlm.setText("Jam");

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

        jLabel23.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel23.setText("Total Bayar :");

        LTotalBayar.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LTotalBayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LTotalBayar.setText("Total");

        jLabel25.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel25.setText("Payment     :");

        jLabel26.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel26.setText("Kembalian  :");

        LKembali.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LKembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LKembali.setText("Total");

        LDiskon.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LDiskon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LDiskon.setText("Diskon %");

        LBayar.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        LBayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LBayar.setText("Tunai");

        jButton4.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        jButton4.setText("SAVE AS PDF");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        jButton5.setText("CETAK TIKET");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LKasir)
                            .addComponent(LNoFaktur))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(JamSkrng))
                            .addComponent(TglSkrng)))
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LTotal))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LDiskon))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LTotalBayar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LBayar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LKembali))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LJudulFilm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LTanggalFlm)
                                    .addComponent(LJamFlm)
                                    .addComponent(LKursi)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(LStudio))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(LHarga)
                                .addGap(18, 18, 18)
                                .addComponent(LJumlahTiket))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton5});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TglSkrng)
                    .addComponent(jLabel3)
                    .addComponent(LNoFaktur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JamSkrng)
                    .addComponent(jLabel5)
                    .addComponent(LKasir))
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(LJudulFilm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LHarga)
                    .addComponent(LJumlahTiket))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(LTanggalFlm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LJamFlm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LKursi)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(LStudio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(LTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(LDiskon))
                .addGap(12, 12, 12)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(LTotalBayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(LBayar))
                .addGap(24, 24, 24)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(LKembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(443, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMouseClicked
        try {
            // TODO add your handling code here:
            BHapusTransaksi.setEnabled(true);
            row = tabelTransaksi.getSelectedRow();
            row = tabelTransaksi.convertRowIndexToModel(row);
            
            setTampil();
            
        } catch (SQLException ex) {
            Logger.getLogger(panelTabelLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tabelTransaksiMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelLaporanUtama());
    }//GEN-LAST:event_jButton1ActionPerformed

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
"     `strukpembayaran` strukpembayaran INNER JOIN `relasi_pesantiket` relasi_pesantiket ON strukpembayaran.`ID_Struk` = relasi_pesantiket.`ID_Struk` WHERE strukpembayaran.`ID_Struk`='"+LNoFaktur.getText()+"'";
       
                fungsi.savePDF("StrukPembayaran", sql);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void BHapusTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusTransaksiActionPerformed
        // TODO add your handling code here:
         row = tabelTransaksi.getSelectedRow();
        row = tabelTransaksi.convertRowIndexToModel(row);

        int konfirmasi=JOptionPane.showConfirmDialog(null, "Hapus Transaksi "+ModelTabelTransaksi.getValueAt(row, 1)+"? \n U Will Lost The Data About "+ModelTabelTransaksi.getValueAt(row, 1)+" Keep It To Remembered ", "Hapus Data Transaksi", JOptionPane.YES_NO_OPTION);
        if(konfirmasi==JOptionPane.YES_OPTION){
            try {
                koneksi.updateData("DELETE FROM `strukpembayaran` WHERE ID_Struk = '"+ModelTabelTransaksi.getValueAt(row, 0)+"'");
                koneksi.updateData("DELETE FROM `relasi_pesantiket` WHERE ID_Struk = '"+ModelTabelTransaksi.getValueAt(row, 1)+"'");

                ModelTabelTransaksi.removeRow(row);
                setLabel();
                JOptionPane.showMessageDialog(null, "Data Transaksi Telah Dihapus","Hapus Transaksi",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(panelTabelLaporan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BHapusTransaksiActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int konfirm=JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Semua Data?", "Hapus Semua Data (Not Recomended)", JOptionPane.YES_NO_OPTION);
        if(konfirm==JOptionPane.YES_OPTION)
        {
            try {
                koneksi.updateData("DELETE FROM `strukpembayaran`");
                koneksi.updateData("DELETE FROM `relasi_pesantiket`");
                row = tabelTransaksi.getRowCount();
                for(int i=0;i<row;i++){
                    ModelTabelTransaksi.removeRow(0);
                }
                setLabel();
                JOptionPane.showMessageDialog(null, "Semua Data Transaksi Telah Dihapus","Hapus Semua Data Transaksi",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(panelTabelLaporan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        fungsi.BackupDatabase("laporan", "BACKUP(SNF)_", "strukpembayaran relasi_pesantiket relasi_pesantiket.kursi");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        fungsi.RestoreDatabase("laporan");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        dialogcetak.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

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
"     `strukpembayaran` strukpembayaran USING (`ID_Struk`) WHERE `ID_Struk`='"+LNoFaktur.getText()+"'";
       
                fungsi.Cetak(sql, "Tiket",2);
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BHapusTransaksi;
    private javax.swing.JLabel JamSkrng;
    private javax.swing.JLabel LBayar;
    private javax.swing.JLabel LDiskon;
    private javax.swing.JLabel LHarga;
    private javax.swing.JLabel LJamFlm;
    private javax.swing.JLabel LJudulFilm;
    private javax.swing.JLabel LJumlahTiket;
    private javax.swing.JLabel LJumlahTransaksi;
    private javax.swing.JLabel LJumlahTransaksi1;
    private javax.swing.JLabel LKasir;
    private javax.swing.JLabel LKembali;
    private javax.swing.JLabel LKursi;
    private javax.swing.JLabel LNoFaktur;
    private javax.swing.JLabel LStudio;
    private javax.swing.JLabel LTanggalFlm;
    private javax.swing.JLabel LTotal;
    private javax.swing.JLabel LTotalBayar;
    private javax.swing.JTextField TFCariTransaksi;
    private javax.swing.JLabel TglSkrng;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable tabelTransaksi;
    // End of variables declaration//GEN-END:variables
}
