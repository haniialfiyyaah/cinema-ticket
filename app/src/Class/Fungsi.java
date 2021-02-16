/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static aplikasitiketbioskop.AplikasiTiketBioskop.koneksi;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;


/**
 *
 * @author me
 */
public class Fungsi {
    private ResultSet res =null;
    private Integer jam1,menit1,jam2,menit2,selisih, durasi; 
    private ImageIcon icon;
        
    public void setComboBox(String SQL, JComboBox ComboBox, Vector list){
        try {
            res = koneksi.lihatData(SQL);
            list = new Vector();
            String data[] = new String[1];
            while (res.next()){
                data[0]= res.getString(1);
                list.add(data[0]);
                ComboBox.setModel(new DefaultComboBoxModel(list));
                ComboBox.setSelectedIndex(-1);
                ComboBox.setEditable(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR \n"+ex.getMessage());
        }
    }
    
    public void getDataTabel(Integer n , String SQL, DefaultTableModel tabel) throws SQLException{ //<-- untuk ambil data
        String data[] = new String[n];
        res = koneksi.lihatData(SQL);
        while (res.next()){
            for (int i = 0; i < data.length; i++) {
                data[i]= res.getString(i+1);
            }
            tabel.addRow(data);
        }                
    }
    
    public void gettimer(JLabel LJam){
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                //Date dt = new Date();
                java.util.Date dateTime = new java.util.Date();
               
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();
                
                if (nilai_jam<=9) nol_jam ="0";
                if (nilai_menit<=9) nol_menit ="0";
                if (nilai_detik<=9)nol_detik ="0";
                 
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                  
                LJam.setText(jam+":"+ menit+":"+ detik);
            }
        };
        new javax.swing.Timer(1000, task).start();
    }
    
    public String setId(String SQL,String ID) throws SQLException{
        res = koneksi.lihatData(SQL);
        while(res.next()){
            if(res.first()==false){
                ID=ID;
            }
            else{
                res.last();
                int auto = res.getInt(1)+1;
                String no = String.valueOf(auto);
                int noLong = no.length();
                for(int a=0;a<7-noLong;a++){
                    no = "0"+no;
                }
                //LID.setText(ID.substring(0, 3)+no);
                ID = (ID.substring(0, 3)+no);
            }
        }
        return ID;
    }
    
    public ImageIcon gtGambar(String path, String SQL, int x,int y){
        try {
            koneksi.data= new String[1];
            koneksi.data = koneksi.ambilData(1, SQL);
            String gambar = koneksi.data[0];
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(koneksi.currentDir+"/folder/foto/"+path+"/"+gambar);
            Image imagedResized=image.getScaledInstance(x, y, Image.SCALE_DEFAULT);
            icon=new ImageIcon(imagedResized);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR \n"+ex.getMessage());
        }
        return icon;
    }
    
    public PlainDocument getToUpperCase() {
        PlainDocument filterUpper = new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws
            BadLocationException {
                char[] upper = str.toCharArray();
                for (int i = 0; i < upper.length; i++) {
                    // Menjadi upper case
                    upper[i] = Character.toUpperCase(upper[i]);
                }
                super.insertString(offs, new String(upper), a);
            }
        };
        return filterUpper;
    }
    
    public PlainDocument getsNumber(){
    PlainDocument filterNumb = new PlainDocument(){
        private  static  final long serialVersionUID = 1L;
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if(str.matches("[0-9]*")){
                super.insertString(offs, str, a); //To change body of generated methods, choose Tools | Templates.
            }else{
            }
        }
    };
    return filterNumb;
    
}
    
    public Integer getSelisihJam (Integer jamInput, Integer menitInput, Integer jamSedia, Integer menitSedia ){
        if(jamInput < jamSedia){
            this.jam1 = jamSedia;this.menit1 = menitSedia;
            this.jam2 = jamInput;this.menit2 = menitInput;            
        }else{
            this.jam2 = jamSedia;this.menit2 = menitSedia;
            this.jam1 = jamInput;this.menit1 = menitInput;  
        }
        
        if(menit1 < menit2){
            this.menit1 = menit1 + 60;
            this.jam1 = jam1 - 1;
        }
        int jamJadi = jam1-jam2;
        jamJadi = jamJadi*60;
        int menitJadi = menit1-menit2;
        this.selisih = jamJadi+menitJadi;
        return selisih;
    }
    
    public Integer getDurasi(String SQL) throws SQLException{
        koneksi.data = new String[1];
        koneksi.data = koneksi.ambilData(1, SQL);
        String setDurasi = koneksi.data[0];
        durasi = Integer.valueOf(setDurasi);
        setDurasi.substring(setDurasi.length()-2,setDurasi.length());
        Integer y = Integer.valueOf(setDurasi.substring(setDurasi.length()-1,setDurasi.length()));
        Integer x = 10 - y;
        this.durasi = durasi + x;
        return durasi;
    } 
    
    public String gettanggal(){
        java.util.Date ys = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String LTanggal=(dateFormat.format(ys));
        return LTanggal;
    }
    
    public String getwaktu(){
        String nol_jam = "";
        String nol_menit = "";
        String nol_detik = "";
        //Date dt = new Date();
        java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
        if (nilai_jam<=9) nol_jam ="0";
        if (nilai_menit<=9) nol_menit ="0";
        if (nilai_detik<=9)nol_detik ="0";
            String jam = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
        String waktu = jam+":"+ menit + ":" + detik;
        return waktu;
        }
    
    public String filter(String text){
            String res;
            int len=0, i =0;
            boolean isDigit;
            
            char[] data = text.toCharArray();
            while (i<data.length){
                isDigit = Character.isDigit(data[i]);
                if (isDigit==true){
                    data[len]=data[i];
                    len++;
                }
                i++;
            }
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(data, 0, len);
            return strBuf.toString();
        }
     
    
    ////database
    public void BackupDatabase(String dirfile, String id, String tabelDBSQL){
        int ok = JOptionPane.showOptionDialog(null, "Ingin Membackup Data?", "Restore", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"BACKUP", "TIDAK"}, null);
        if(ok==JOptionPane.OK_OPTION){
        //dirfile = folder file menu, id = id dari folder
        try {
            //gettanggal
            String tgl= gettanggal();
            //endgettanggal
            String jam = getwaktu().substring(0,2);
            String menit = getwaktu().substring(3, 5);
            String detik = getwaktu().substring(6, 8);
            ///
            String backupPath = koneksi.currentDir+"\\folder\\backup\\"+dirfile+"\\"+id+tgl+"_"+jam+menit+detik+".sql";
            String backupCMD =koneksi.Mysqlpath+"\\bin\\mysqldump.exe -uroot --add-drop-table "+koneksi.database+" --tables "+tabelDBSQL+" -r "+backupPath;
            System.out.println(backupCMD);
            Process runtimeProcess;
            runtimeProcess = Runtime.getRuntime().exec(backupCMD);
            int processComplete = runtimeProcess.waitFor();
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIBACKUP \n\n");
            } else {
                JOptionPane.showMessageDialog(null, "BACKUP ERROR \n\n");
            }
        } catch (IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }
        }
    }
    
    public void RestoreDatabase(String dirfile){
        try {
            JComboBox JCB = new JComboBox();
            //JFileChooser chooser = new JFileChooser();
            String backupPath = koneksi.currentDir+"\\folder\\backup\\"+dirfile;
            File workingD = new File(backupPath);
            //
            File[] fil = workingD.listFiles();
            Vector list = new Vector();
            for (File file : fil) {
                list.add(file.getName());
            }
            JCB.setModel(new DefaultComboBoxModel(list));
            JCB.setSelectedIndex(-1);
            JCB.setEditable(false);
            String fileNm;
            int ok = JOptionPane.showOptionDialog(null, new Object[] {"Pilih File Backup Berikut", JCB,"\n Pilihan Tersebut Di Ambil Dari\n Data Yang Telah DiBackup"}, "Restore", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"OK", "CANCEL"}, null);
            if(ok==JOptionPane.OK_OPTION){
                if(JCB.getSelectedItem()!=null){
                fileNm=(String) JCB.getSelectedItem();
            String restorePath=backupPath+"\\"+fileNm;
            restorePath = restorePath.replace("\\", "//");
            String[] restoreCMD = new String[]{koneksi.Mysqlpath+"\\bin\\mysql.exe","--user="+koneksi.user,"--password="+koneksi.password,"--database="+koneksi.database,"-e","source "+restorePath};
            Process runtimeProcess;
            runtimeProcess = Runtime.getRuntime().exec(restoreCMD);
            runtimeProcess.waitFor();
            int processComplete = runtimeProcess.waitFor();
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIRESTORE \n\n");
            } else {
                JOptionPane.showMessageDialog(null, "RESTORE ERROR \n\n");
            }
           //opsi karyawan
            if(dirfile.equals("karyawan")){
                JOptionPane.showMessageDialog(null, "RESTART");
               //  formMenu.menu.JS.setViewportView(new panelLogin());
            }//endoprikaryawn
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERROR "+ex.getMessage());
        } catch (IOException | InterruptedException ex) {
           JOptionPane.showMessageDialog(null, "ERROR "+ex.getMessage());
        }
        
    }
    
    public void Cetak(String SQL,String fileName,Integer n){
        try {
            res = koneksi.lihatData(SQL);
            JasperPrint jasperPrint;       
            JRResultSetDataSource jrRS = new JRResultSetDataSource (res);            
            JasperReport jasperReport = JasperCompileManager.compileReport(koneksi.currentDir+"/folder/iReport/"+fileName+".jrxml");          
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrRS);
            JRViewer aViewer = new JRViewer(jasperPrint);                  
            aViewer.setZoomRatio(n);
            JDialog viewer = new JDialog();  
            viewer.setAlwaysOnTop(true);
            viewer.setModal(true);
            viewer.setTitle(".: LAPORAN :.");             
           // viewer.setAlwaysOnTop(true);
            viewer.getContentPane().add(aViewer);                  
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
            viewer.setBounds(0,0,screenSize.width-150, screenSize.height-150);
            viewer.setVisible(true);          
        } catch (HeadlessException | SecurityException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Laporan gak ada "+e.getMessage());
        }
    }
    
    public void savePDF(String fileName, String SQL){
        try {
            res = koneksi.lihatData(SQL);
            JasperPrint jasperPrint;       
            JRResultSetDataSource jrRS = new JRResultSetDataSource (res);            
            JasperReport jasperReport = JasperCompileManager.compileReport(koneksi.currentDir+"/folder/iReport/"+fileName+".jrxml");          
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrRS);
            
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");   
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.pdf","pdf"));
            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String savefile = fileToSave.getAbsolutePath()+".pdf";
                JasperExportManager.exportReportToPdfFile(jasperPrint, savefile);
                JOptionPane.showMessageDialog(parentFrame, "DATA BERHASIL DISIMPAN DI "+savefile);
            }
        } catch (SQLException | JRException ex) {
            JOptionPane.showMessageDialog(null, "Laporan gak ada "+ex.getMessage());
        }
    }
}
