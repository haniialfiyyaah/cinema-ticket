/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Karyawan;

import Class.Fungsi;
import static Main.panelIdentitas.panelidentitas;
import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
/**
 *
 * @author Hanii
 */
public class panelInputKaryawan extends javax.swing.JPanel {

    /**
     * Creates new form panelInputKaryawan
     */
    Fungsi fungsi = new Fungsi();
    private String tampilan = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(tampilan);
    private String tempPassword = "ddMMyyyy";
    SimpleDateFormat formatPassword = new SimpleDateFormat(tempPassword);
    
    File file;
    JFileChooser jfc;
    private String SQL;
    private String idKaryawan, namaKaryawan, noTlpK, tmptLahirK, tglLahirK, jenisKlmnK, jalanK,
            noRumahK,provinsiK,kabKotaK,kecamatanK,kelurahanK, rtK,rwK, statusKawinK, tglKerjaK,jabatanK,fotoK="";
    private String username,password, statusAktif;
    
    public panelInputKaryawan(){
        initComponents();
        TFNamaKaryawan.requestFocus();
        getIDKaryawan();
    }
    public void getIDKaryawan(){
        try {
            LIDKaryawan.setText(  fungsi.setId("SELECT MAX(RIGHT(ID_Karyawan,7)) AS NO FROM karyawan", "KRY0000001"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void getValueFilm(){
        idKaryawan      = LIDKaryawan.getText();
        namaKaryawan    = TFNamaKaryawan.getText();
        noTlpK          = TFNoTelponK.getText();
        tmptLahirK      = TFTempatLahirK.getText();
        tglLahirK       = String.valueOf(format.format(TFTanggalLahir.getDate()));
        jenisKlmnK      = (String) CBJenisKelamin.getSelectedItem();
        jalanK          = TFJalan.getText();
        noRumahK        = TFNoRumah.getText();
        provinsiK       = TFProvinsi.getText();
        kabKotaK        = TFKabKota.getText();
        kecamatanK      = TFKecamatan.getText();
        kelurahanK      = TFKelurahan.getText();
        rtK             = TFRT.getText();
        rwK             = TFRW.getText();            
        statusKawinK    = (String) CBStatusKawin.getSelectedItem();
        tglKerjaK       = String.valueOf(format.format(TFTanggalKerja.getDate()));
        jabatanK        = (String) CBJabatanKerja.getSelectedItem();
        
        username        = idKaryawan;
        password        = String.valueOf(formatPassword.format(TFTanggalLahir.getDate()));
        statusAktif     = "AKTIF";
    }
    public void inputKaryawan(){
            try {
                getValueFilm();
                SQL =   "INSERT INTO `karyawan` (`ID_Karyawan`, `Nama_Lengkap`, `No_Telepon`, `Tempat_Lahir`, `Tanggal_Lahir`, `Jenis_Kelamin`, `Jalan`, `No`, `Provinsi`, `Kab_Kota`, `Kecamatan`, `Kelurahan`, `RT`, `RW`, `Status_Kawin`, `Tanggal_Masuk_Kerja`, `Jabatan`, Status_Aktif, Foto) "
                        + "VALUES ('"+idKaryawan+"', "
                        + "'"+namaKaryawan+"',"
                        + "'"+noTlpK+"', "
                        + "'"+tmptLahirK+"', "
                        + "'"+tglLahirK+"', "
                        + "'"+jenisKlmnK+"', "
                        + "'"+jalanK+"', "
                        + "'"+noRumahK+"', "
                        + "'"+provinsiK+"', "
                        + "'"+kabKotaK+"', "
                        + "'"+kecamatanK+"', "
                        + "'"+kelurahanK+"', "
                        + "'"+rtK+"', "
                        + "'"+rwK+"', "
                        + "'"+statusKawinK+"', "
                        + "'"+tglKerjaK+"', "
                        + "'"+jabatanK+"',"
                        + "'"+statusAktif+"',"
                        + "'"+fotoK+"')";
                koneksi.updateData(SQL);
                koneksi.updateData("INSERT INTO `karyawan.akunlogin` (username, password, id_karyawan) values "
                        + "('"+username+"','"+password+"','"+idKaryawan+"')");
                try {
                    //String path=new File(".").getCanonicalPath();
                    FileUtils.copyFileToDirectory(file, new File(koneksi.currentDir+"/folder/foto/FotoKaryawan"));
                } catch (IOException ex) {
                    Logger.getLogger(panelInputKaryawan.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int gnt= JOptionPane.showOptionDialog(this, new Object[] {"DATA BERHASIL DITAMBAHKAN"}, "JADWAL TAYANG", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"LIHAT TABEL", "TAMBAH DATA LAGI"}, null);
                if(gnt==JOptionPane.OK_OPTION){
                    panelidentitas.JS.setViewportView(new panelTabelKaryawan());
                }else{
                    clearKaryawan();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
    }
    private void clearKaryawan(){
        getIDKaryawan();
        TFNamaKaryawan.requestFocus();
        TFNamaKaryawan.setText(""); //clear tambah film
        TFNoTelponK.setText("");
        TFTempatLahirK.setText("");
        TFTanggalLahir.setDate(null);
        TFJalan.setText("");
        TFNoRumah.setText("");
        TFProvinsi.setText("");
        TFKabKota.setText("");
        TFKecamatan.setText("");
        TFKelurahan.setText("");
        TFRT.setText("");
        TFRW.setText("");
        CBStatusKawin.setSelectedItem(null);
        TFTanggalKerja.setDate(null);
        CBJenisKelamin.setSelectedItem(null);
        CBJabatanKerja.setSelectedItem(null);
        jLabelGambar.setIcon(null);
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
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        LIDKaryawan = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        TFNamaKaryawan = new javax.swing.JTextField();
        TFNoTelponK = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        TFTempatLahirK = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        TFRT = new javax.swing.JTextField();
        TFRW = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        CBStatusKawin = new javax.swing.JComboBox<>();
        TFNoRumah = new javax.swing.JTextField();
        jLabel198 = new javax.swing.JLabel();
        TFJalan = new javax.swing.JTextField();
        jLabel199 = new javax.swing.JLabel();
        TFProvinsi = new javax.swing.JTextField();
        TFKabKota = new javax.swing.JTextField();
        TFKecamatan = new javax.swing.JTextField();
        TFKelurahan = new javax.swing.JTextField();
        TFTanggalLahir = new com.toedter.calendar.JDateChooser();
        CBJenisKelamin = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        CBJabatanKerja = new javax.swing.JComboBox<>();
        jLabel161 = new javax.swing.JLabel();
        TFTanggalKerja = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabelGambar = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/go-back-icon (1).png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(377, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jPanel20.setBackground(new java.awt.Color(204, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BIODATA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Centaur", 0, 14))); // NOI18N

        LIDKaryawan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LIDKaryawan.setForeground(new java.awt.Color(255, 51, 51));
        LIDKaryawan.setText("ID KARYAWAN");

        jLabel105.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel105.setText("ID KARYAWAN");

        jLabel107.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel107.setText("NAMA LENGKAP");

        TFNamaKaryawan.setDocument(fungsi.getToUpperCase());
        TFNamaKaryawan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFNoTelponK.setDocument(fungsi.getsNumber());
        TFNoTelponK.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel108.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel108.setText("NO TELEPON");

        jLabel109.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel109.setText("TEMPAT LAHIR");

        TFTempatLahirK.setDocument(fungsi.getToUpperCase());
        TFTempatLahirK.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel111.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel111.setText("TANGGAL LAHIR");

        jLabel112.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel112.setText("JENIS KELAMIN");
        jLabel112.setToolTipText("");
        jLabel112.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel153.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel153.setText("PROVINSI");

        jLabel154.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel154.setText("KAB/KOTA");

        jLabel155.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel155.setText("KECAMATAN");

        jLabel156.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel156.setText("KELURAHAN");

        TFRT.setDocument(fungsi.getsNumber());
        TFRT.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFRW.setDocument(fungsi.getsNumber());
        TFRW.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel157.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel157.setText("RT/RW");

        jLabel158.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel158.setText("STATUS KAWIN");

        CBStatusKawin.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBStatusKawin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MENIKAH", "BELUM MENIKAH", "CERAI" }));
        CBStatusKawin.setSelectedIndex(-1);

        TFNoRumah.setDocument(fungsi.getsNumber());
        TFNoRumah.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel198.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel198.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel198.setText("No");

        TFJalan.setDocument(fungsi.getToUpperCase());
        TFJalan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel199.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel199.setText("ALAMAT/JALAN");

        TFProvinsi.setDocument(fungsi.getToUpperCase());
        TFProvinsi.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFKabKota.setDocument(fungsi.getToUpperCase());
        TFKabKota.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFKecamatan.setDocument(fungsi.getToUpperCase());
        TFKecamatan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFKelurahan.setDocument(fungsi.getToUpperCase());
        TFKelurahan.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFTanggalLahir.setDateFormatString("yyyy-MM-dd");

        CBJenisKelamin.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PEREMPUAN", "LAKI-LAKI" }));
        CBJenisKelamin.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel199, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFJalan, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel198)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFNoRumah))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(TFRT, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TFRW, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(210, 210, 210))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TFKecamatan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFKelurahan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TFProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TFKabKota, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFNoTelponK, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFTempatLahirK, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LIDKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                    .addComponent(TFNamaKaryawan)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBStatusKawin, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(LIDKaryawan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(TFNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(TFNoTelponK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(TFTempatLahirK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel111)
                    .addComponent(TFTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(CBJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel199)
                    .addComponent(TFJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel198)
                    .addComponent(TFNoRumah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel153)
                    .addComponent(TFProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel154)
                    .addComponent(TFKabKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155)
                    .addComponent(TFKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel156)
                    .addComponent(TFKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel157)
                    .addComponent(TFRT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFRW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel158)
                    .addComponent(CBStatusKawin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(204, 255, 255));

        jLabel110.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel110.setText("TANGGAL MASUK KERJA");

        jLabel159.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel159.setText("JABATAN KERJA");

        CBJabatanKerja.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBJabatanKerja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STAFF ADMINISTRASI", "KASIR" }));
        CBJabatanKerja.setSelectedIndex(-1);

        jLabel161.setFont(new java.awt.Font("Baskerville Old Face", 2, 12)); // NOI18N
        jLabel161.setText("<HTML>*) akun login dibuat otomatis dengan username=ID_Pegawai dan password=tgl_lahir(ddmmyyyy)</HTML>");

        TFTanggalKerja.setDateFormatString("yyyy-MM-dd");

        jLabel11.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel11.setText("FOTO");

        jLabelGambar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelGambar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton5.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jButton5.setText("PILIH FOTO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add-user-icon.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBJabatanKerja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TFTanggalKerja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel110)
                            .addComponent(jLabel159))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addContainerGap())))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFTanggalKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel159)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CBJabatanKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (TFNamaKaryawan.getText().isEmpty() || TFNoTelponK.getText().isEmpty() ||TFTempatLahirK.getText().isEmpty() || TFTanggalLahir.getDate()==null || CBJenisKelamin.getSelectedItem()==null || TFJalan.getText().isEmpty() || TFNoRumah.getText().isEmpty() || TFKabKota.getText().isEmpty() || TFProvinsi.getText().isEmpty() || TFKecamatan.getText().isEmpty() ||TFKelurahan.getText().isEmpty() ||TFRT.getText().isEmpty() ||TFRW.getText().isEmpty() ||CBStatusKawin.getSelectedItem()==null || TFTanggalKerja.getDate()==null || jLabelGambar.getIcon()==null ){
            JOptionPane.showMessageDialog(null, "DATA BELUM LENGKAP \n\n LENGKAPI DATA");
            TFNamaKaryawan.requestFocus();
        }else{
            inputKaryawan(); //  Logger.getLogger(panelInputKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jfc=new JFileChooser();
        if(jfc.showOpenDialog(jLabelGambar)==JFileChooser.APPROVE_OPTION){
            
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imagedResized=image.getScaledInstance(138, 166, Image.SCALE_DEFAULT);
            ImageIcon imageIcon=new ImageIcon(imagedResized);
            jLabelGambar.setIcon(imageIcon);
            this.fotoK = jfc.getSelectedFile().getName();
            file=new File(jfc.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelKaryawanUtama());
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBJabatanKerja;
    private javax.swing.JComboBox<String> CBJenisKelamin;
    private javax.swing.JComboBox<String> CBStatusKawin;
    private javax.swing.JLabel LIDKaryawan;
    private javax.swing.JTextField TFJalan;
    private javax.swing.JTextField TFKabKota;
    private javax.swing.JTextField TFKecamatan;
    private javax.swing.JTextField TFKelurahan;
    private javax.swing.JTextField TFNamaKaryawan;
    private javax.swing.JTextField TFNoRumah;
    private javax.swing.JTextField TFNoTelponK;
    private javax.swing.JTextField TFProvinsi;
    private javax.swing.JTextField TFRT;
    private javax.swing.JTextField TFRW;
    private com.toedter.calendar.JDateChooser TFTanggalKerja;
    private com.toedter.calendar.JDateChooser TFTanggalLahir;
    private javax.swing.JTextField TFTempatLahirK;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabelGambar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
