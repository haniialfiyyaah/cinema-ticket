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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Hanii
 */
public class panelEditKaryawan extends javax.swing.JPanel {

    /**
     * Creates new form panelEditKaryawan
     */
    Fungsi fungsi = new Fungsi();
    private String tampilan = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(tampilan);
    private String tempPassword = "ddMMyyyy";
    SimpleDateFormat formatPassword = new SimpleDateFormat(tempPassword);
    
    private String statusAktif, SQL,username,password;
    private String idKaryawan, namaKaryawan, noTlpK, tmptLahirK, tglLahirK, jenisKlmnK, jalanK,
                noRumahK,provinsiK,kabKotaK,kecamatanK,kelurahanK, rtK,rwK, statusKawinK, tglKerjaK,jabatanK,fotoK;
    DefaultTableModel tableModel;
    JFileChooser jfc = new JFileChooser();
    File file;
    int row = 0;
    
    public panelEditKaryawan() {
        initComponents();
        TampilDataKaryawan();
    }
    
    public void setAkses() throws SQLException{
            String data[] = new String[1];
            data =   koneksi.ambilData(1,"SELECT * FROM dummyHelp");
            this.idKaryawan = data[0];
        
    }
    private void TampilDataKaryawan(){
        try {
            setAkses();
            String data[] = new String[20];
            data =   koneksi.ambilData(20, "SELECT * FROM karyawan WHERE ID_KARYAWAN='"+idKaryawan+"' ");
            Date tglLahir,tglKerja;
            String tanggalLahir = data[4];
            tglLahir = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalLahir);
            String tanggalKerja = data[15];
            tglKerja = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalKerja);
            LIDKaryawan3.setText(data[0]);
            TFNamaKaryawan3.setText(data[1]); //clear tambah film
            TFNoTelponK3.setText(data[2]);
            TFTempatLahirK3.setText(data[3]);
            TFTanggalLahir3.setDate(tglLahir);
            CBJenisKelamin3.setSelectedItem(data[5]);
            TFJalan3.setText(data[6]);
            TFNoRumah3.setText(data[7]);
            TFProvinsi3.setText(data[8]);
            TFKabKota3.setText(data[9]);
            TFKecamatan3.setText(data[10]);
            TFKelurahan3.setText(data[11]);
            TFRT3.setText(data[12]);
            TFRW3.setText(data[13]);
            CBStatusKawin3.setSelectedItem(data[14]);
            TFTanggalKerja.setDate(tglKerja);
            CBJabatanKerja.setSelectedItem(data[16]);
            jLabelGambar.setIcon(  fungsi.gtGambar("FotoKaryawan", "SELECT Foto FROM karyawan WHERE ID_Karyawan = '"+idKaryawan+"'",151,177));
            if(data[19]==null){
                fotoK="";
            }else{
                fotoK =data[19];
            }
            file=new File(fotoK);
        } catch (SQLException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        }
    public void getValueKaryawan(){
        idKaryawan      = LIDKaryawan3.getText();
        namaKaryawan    = TFNamaKaryawan3.getText();
        noTlpK          = TFNoTelponK3.getText();
        tmptLahirK      = TFTempatLahirK3.getText();
        tglLahirK       = String.valueOf(format.format(TFTanggalLahir3.getDate()));
        jenisKlmnK      = (String) CBJenisKelamin3.getSelectedItem();
        jalanK          = TFJalan3.getText();
        noRumahK        = TFNoRumah3.getText();
        provinsiK       = TFProvinsi3.getText();
        kabKotaK        = TFKabKota3.getText();
        kecamatanK      = TFKecamatan3.getText();
        kelurahanK      = TFKelurahan3.getText();
        rtK             = TFRT3.getText();
        rwK             = TFRW3.getText();            
        statusKawinK    = (String) CBStatusKawin3.getSelectedItem();
        tglKerjaK       = String.valueOf(format.format(TFTanggalKerja.getDate()));
        jabatanK        = (String) CBJabatanKerja.getSelectedItem();
        
        username        = idKaryawan;
        password        = String.valueOf(formatPassword.format(TFTanggalLahir3.getDate()));
       // statusAktif     = "AKTIF";
        
    }
    private void EditDataKaryawan(){
            try {
                getValueKaryawan();
                //datafilm.setFoto(TAAktorEdit.getText());
                SQL =   "UPDATE `karyawan` set "
                        + "`Nama_Lengkap` = '"+namaKaryawan+"', "
                        + "`No_Telepon` = '"+noTlpK+"', "
                        + "`Tempat_Lahir`='"+tmptLahirK+"',"
                        + "`Tanggal_Lahir`= '"+tglLahirK+"',"
                        + "`Jenis_Kelamin` = '"+jenisKlmnK+"', "
                        + "`Jalan`= '"+jalanK+"',"
                        + " `No`= '"+noRumahK+"',"
                        + " `Provinsi`='"+provinsiK+"',"
                        + " `Kab_Kota`='"+kabKotaK+"',"
                        + " `Kecamatan`='"+kecamatanK+"',"
                        + " `Kelurahan`='"+kelurahanK+"',"
                        + " `RT` = '"+rtK+"' ,"
                        + " `RW` = '"+rwK+"',"
                        + " `Status_Kawin` = '"+statusKawinK+"',"
                        + " `Tanggal_Masuk_Kerja` = '"+tglKerjaK+"', "
                        + "`Jabatan`='"+jabatanK+"' ,"
                        + " `Foto` = '"+fotoK+"'"
                        + "WHERE ID_Karyawan = '"+idKaryawan+"' ";
                koneksi.updateData(SQL);
                try {
                    //String path=new File(".").getCanonicalPath();
                    //File fileLama = new File("./src/Image/Poster/"+file);
                    //fileLama.delete();
                    FileUtils.copyFileToDirectory(file, new File(koneksi.currentDir+"/folder/foto/FotoKaryawan"));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    // Logger.getLogger(panelInputFilm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "EDIT DATA BERHASIL","EDIT",JOptionPane.INFORMATION_MESSAGE);
                panelidentitas.JS.setViewportView(new panelDeskripsiK());
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
        jPanel37 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        LIDKaryawan3 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        TFNamaKaryawan3 = new javax.swing.JTextField();
        TFNoTelponK3 = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        TFTempatLahirK3 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        TFRT3 = new javax.swing.JTextField();
        TFRW3 = new javax.swing.JTextField();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        CBStatusKawin3 = new javax.swing.JComboBox<>();
        TFNoRumah3 = new javax.swing.JTextField();
        jLabel204 = new javax.swing.JLabel();
        TFJalan3 = new javax.swing.JTextField();
        jLabel205 = new javax.swing.JLabel();
        TFProvinsi3 = new javax.swing.JTextField();
        TFKabKota3 = new javax.swing.JTextField();
        TFKecamatan3 = new javax.swing.JTextField();
        TFKelurahan3 = new javax.swing.JTextField();
        TFTanggalLahir3 = new com.toedter.calendar.JDateChooser();
        CBJenisKelamin3 = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        CBJabatanKerja = new javax.swing.JComboBox<>();
        TFTanggalKerja = new com.toedter.calendar.JDateChooser();
        jLabelGambar = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();

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
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel37.setBackground(new java.awt.Color(204, 255, 255));

        jPanel23.setBackground(new java.awt.Color(204, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BIODATA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Centaur", 0, 14))); // NOI18N

        LIDKaryawan3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        LIDKaryawan3.setForeground(new java.awt.Color(255, 51, 51));
        LIDKaryawan3.setText("ID KARYAWAN");

        jLabel123.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel123.setText("ID KARYAWAN");

        jLabel124.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel124.setText("NAMA LENGKAP");

        TFNamaKaryawan3.setDocument(fungsi.getToUpperCase());
        TFNamaKaryawan3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFNoTelponK3.setDocument(fungsi.getsNumber());
        TFNoTelponK3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel125.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel125.setText("NO TELEPON");

        jLabel126.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel126.setText("TEMPAT LAHIR");

        TFTempatLahirK3.setDocument(fungsi.getToUpperCase());
        TFTempatLahirK3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel127.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel127.setText("TANGGAL LAHIR");

        jLabel128.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel128.setText("JENIS KELAMIN");
        jLabel128.setToolTipText("");
        jLabel128.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel171.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel171.setText("PROVINSI");

        jLabel172.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel172.setText("KAB/KOTA");

        jLabel173.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel173.setText("KECAMATAN");

        jLabel174.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel174.setText("KELURAHAN");

        TFRT3.setDocument(fungsi.getsNumber());
        TFRT3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFRW3.setDocument(fungsi.getsNumber());
        TFRW3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel175.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel175.setText("RT/RW");

        jLabel176.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel176.setText("STATUS KAWIN");

        CBStatusKawin3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBStatusKawin3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MENIKAH", "BELUM MENIKAH", "CERAI" }));
        CBStatusKawin3.setSelectedIndex(-1);

        TFNoRumah3.setDocument(fungsi.getsNumber());
        TFNoRumah3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel204.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel204.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel204.setText("No");

        TFJalan3.setDocument(fungsi.getToUpperCase());
        TFJalan3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        jLabel205.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel205.setText("ALAMAT/JALAN");

        TFProvinsi3.setDocument(fungsi.getToUpperCase());
        TFProvinsi3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFKabKota3.setDocument(fungsi.getToUpperCase());
        TFKabKota3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFKecamatan3.setDocument(fungsi.getToUpperCase());
        TFKecamatan3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFKelurahan3.setDocument(fungsi.getToUpperCase());
        TFKelurahan3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N

        TFTanggalLahir3.setDateFormatString("yyyy-MM-dd");

        CBJenisKelamin3.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBJenisKelamin3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PEREMPUAN", "LAKI-LAKI" }));
        CBJenisKelamin3.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFTanggalLahir3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel173, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(TFRT3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TFRW3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(210, 210, 210))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TFKecamatan3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFKelurahan3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel171, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TFProvinsi3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel172, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TFKabKota3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFJalan3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel204)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFNoRumah3))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFNoTelponK3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFTempatLahirK3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBJenisKelamin3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LIDKaryawan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TFNamaKaryawan3, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBStatusKawin3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123)
                    .addComponent(LIDKaryawan3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel124)
                    .addComponent(TFNamaKaryawan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125)
                    .addComponent(TFNoTelponK3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(TFTempatLahirK3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel127)
                    .addComponent(TFTanggalLahir3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel128)
                    .addComponent(CBJenisKelamin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel205)
                    .addComponent(TFJalan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel204)
                    .addComponent(TFNoRumah3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel171)
                    .addComponent(TFProvinsi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel172)
                    .addComponent(TFKabKota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel173)
                    .addComponent(TFKecamatan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel174)
                    .addComponent(TFKelurahan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel175)
                    .addComponent(TFRT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFRW3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel176)
                    .addComponent(CBStatusKawin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanel24.setBackground(new java.awt.Color(204, 255, 255));

        jLabel129.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel129.setText("TANGGAL MASUK KERJA");

        jLabel177.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel177.setText("JABATAN KERJA");

        CBJabatanKerja.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        CBJabatanKerja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STAFF ADMINISTRASI", "KASIR" }));
        CBJabatanKerja.setSelectedIndex(-1);

        TFTanggalKerja.setDateFormatString("yyyy-MM-dd");

        jLabelGambar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelGambar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton5.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jButton5.setText("PILIH FOTO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/edit-user-icon (1).png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel11.setText("FOTO");

        jLabel179.setFont(new java.awt.Font("Baskerville Old Face", 2, 12)); // NOI18N
        jLabel179.setText("<HTML>*) akun login dibuat otomatis dengan username=ID_Pegawai dan password=tgl_lahir(ddmmyyyy)</HTML>");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBJabatanKerja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel129)
                            .addComponent(jLabel177))
                        .addGap(0, 184, Short.MAX_VALUE))
                    .addComponent(TFTanggalKerja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(0, 40, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(jLabel179, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel129)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFTanggalKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel177)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CBJabatanKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabelGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel179, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel37, java.awt.BorderLayout.CENTER);

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
        if (TFNamaKaryawan3.getText().isEmpty() || TFNoTelponK3.getText().isEmpty() ||TFTempatLahirK3.getText().isEmpty() || TFTanggalLahir3.getDate()==null || CBJenisKelamin3.getSelectedItem()==null || TFJalan3.getText().isEmpty() || TFNoRumah3.getText().isEmpty() || TFKabKota3.getText().isEmpty() || TFProvinsi3.getText().isEmpty() || TFKecamatan3.getText().isEmpty() ||TFKelurahan3.getText().isEmpty() ||TFRT3.getText().isEmpty() ||TFRW3.getText().isEmpty() ||CBStatusKawin3.getSelectedItem().equals(null) || TFTanggalKerja.getDate().equals(null) || jLabelGambar.getIcon()==null){
            JOptionPane.showMessageDialog(null, "DATA BELUM LENGKAP \n\n LENGKAPI DATA");
            TFNamaKaryawan3.requestFocus();
        }else{
            EditDataKaryawan();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        panelidentitas.JS.setViewportView(new panelDeskripsiK());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBJabatanKerja;
    private javax.swing.JComboBox<String> CBJenisKelamin3;
    private javax.swing.JComboBox<String> CBStatusKawin3;
    private javax.swing.JLabel LIDKaryawan3;
    private javax.swing.JTextField TFJalan3;
    private javax.swing.JTextField TFKabKota3;
    private javax.swing.JTextField TFKecamatan3;
    private javax.swing.JTextField TFKelurahan3;
    private javax.swing.JTextField TFNamaKaryawan3;
    private javax.swing.JTextField TFNoRumah3;
    private javax.swing.JTextField TFNoTelponK3;
    private javax.swing.JTextField TFProvinsi3;
    private javax.swing.JTextField TFRT3;
    private javax.swing.JTextField TFRW3;
    private com.toedter.calendar.JDateChooser TFTanggalKerja;
    private com.toedter.calendar.JDateChooser TFTanggalLahir3;
    private javax.swing.JTextField TFTempatLahirK3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabelGambar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
