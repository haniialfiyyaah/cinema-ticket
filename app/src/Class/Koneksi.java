/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Main.formMenu.menu;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author me
 */
public class Koneksi {
    
    public String data[]= new String[0];
    public String user,password,database,port,host,url,message="",Mysqlpath = "";
    private String[] dt = null;
    private Connection koneksi = null;
    private Statement st = null;
    private ResultSet res =null;
    public static formGanti formganti = new formGanti();
    String dir = System.getProperty("user.dir");   
    public String currentDir ;
    
    public Koneksi(){        
        if(dir.substring(dir.length()-4, dir.length()).equals("dist")){
            currentDir = dir.substring(0, dir.length()-5);
        }else{
            currentDir = System.getProperty("user.dir");
        }        
        setKoneksi();
    }
    public void setKoneksi(){
        try {            
            labell:
            while(koneksi==null){
                Properties prop = new Properties();
                prop.loadFromXML(new FileInputStream( currentDir+"/folder/settingDB/database.xml"));
                user = prop.getProperty("USER");
                password = prop.getProperty("PAS");
                database = prop.getProperty("DATABASE");
                port = prop.getProperty("PORT");
                host = prop.getProperty("HOST");
                url = "jdbc:mysql://"+prop.getProperty("HOST")+":"+prop.getProperty("PORT")+"/"+prop.getProperty("DATABASE")
                        +"?zeroDateTimeBegavior=convertToNull&autoReconnect=true";
                Class.forName("com.mysql.jdbc.Driver");
                
                try {
                    koneksi = DriverManager.getConnection(url, user, password);
                    message = "Koneksi OK";
                    st = koneksi.createStatement();
                    //get sql path
                    res = lihatData("select @@datadir");
                    while(res.next()){
                        Mysqlpath=res.getString(1);
                    }
                    Mysqlpath = Mysqlpath.substring(0, Mysqlpath.length()-6);
                    //end get sqlpath
                    
                    menu.show();
                } catch (SQLException ex) {
                    //JOptionPane.showMessageDialog(null,"xxxxx"+ex.getMessage());
                    this.message = ex.getMessage().substring(0, 46);
                    if(message.equals("Could not create connection to database server")){
                        Restoredbfromsql();
                        continue labell;
                    }else {
                        formganti.show();
                        break;
                    }
                }
            }//endwhile
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "xxFile Tidak Ditemukan! \n "+ex.getMessage());
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "yyFile Tidak Ditemukan! \n "+ex.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "yyDriver Tidak Ditemukan! \n "+ex.getMessage());
            System.exit(0);
        }
    }
    
    public void Restoredbfromsql() {
        try {
            url = "jdbc:mysql://"+host+":"+port;
            koneksi = DriverManager.getConnection (url);
            st = koneksi.createStatement();
            res = st.executeQuery("select @@datadir");
            //get sql path
            while(res.next()){
                Mysqlpath=res.getString(1);
            }
            Mysqlpath = Mysqlpath.substring(0, Mysqlpath.length()-6);
            //end get sqlpath
                    
            String restorePath =  currentDir+"/folder/settingDB/database.sql";
            String[] restoreCMD = new String[]{Mysqlpath+"\\bin\\mysql.exe","--user="+user,"--password="+password,"-e","source "+restorePath};
            Process runtimeProcess;
            runtimeProcess = Runtime.getRuntime().exec(restoreCMD);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Successfully restored from SQL");
           //     JOptionPane.showMessageDialog(null, "Successfully restored from SQL");
            } else {
                System.out.println("Error at restoring");
          //      JOptionPane.showMessageDialog(null, "Error at restoring");
            }
            koneksi=null;
        } catch (IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql \n "+ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Server Error \n Perikasa Koneksi SQL \n "+ex.getMessage());
            System.exit(0);
        }
    }
    
    public Connection bukaKoneksi() throws SQLException {
        koneksi = DriverManager.getConnection(url, user, password);
        return koneksi;
    }

    public Connection getKoneksi() {
        return koneksi;
    }
    //useless
    public void setKoneksi(Connection koneksi) {
        this.koneksi = koneksi;
    }

    public Statement getSt() {
        return st;
    }
    //useless
    public void setSt(Statement st) {
        this.st = st;
    }

    public void tutupKoneksi() throws SQLException {
        if (koneksi != null) {
            koneksi.close();
        }
    }

    public int updateData(String sql) throws SQLException {
        int i = 0;
        i = st.executeUpdate(sql);
        return i;
    }

    public ResultSet lihatData(String sql) throws SQLException {
        ResultSet rs = null;
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public String[] ambilData(Integer n , String sql) throws SQLException{
        ResultSet rs = null;
        rs = st.executeQuery(sql);
        data = new String[n];
        while (rs.next()){
            for (int i = 0; i < data.length; i++) {
                data[i]= rs.getString(i+1);
            }
            this.dt=data;
        }      
        return dt;
    }
    
    public void executeData(String sql) throws SQLException {
        st.execute(sql);
    }
    //useless
    public String getStatuc() throws SQLException {
        return koneksi.getWarnings().toString();
    }
    
}


