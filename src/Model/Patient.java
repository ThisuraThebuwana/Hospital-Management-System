/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Team.genorateID;
import hospital_system.Hospital_System;
import static hospital_system.Hospital_System.connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author Thisura
 */
public class Patient {
    
    private String pid;
    private String pname;
    private String paddress;
    private int page;
    private String pgender;
    private String pcontno;
    private String wid;
    private String did;

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname the pname to set
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return the paddress
     */
    public String getPaddress() {
        return paddress;
    }

    /**
     * @param paddress the paddress to set
     */
    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the pgender
     */
    public String getPgender() {
        return pgender;
    }

    /**
     * @param pgender the pgender to set
     */
    public void setPgender(String pgender) {
        this.pgender = pgender;
    }

    /**
     * @return the pcontno
     */
    public String getPcontno() {
        return pcontno;
    }

    /**
     * @param pcontno the pcontno to set
     */
    public void setPcontno(String pcontno) {
        this.pcontno = pcontno;
    }

    /**
     * @return the wid
     */
    public String getWid() {
        return wid;
    }

    /**
     * @param wid the wid to set
     */
    public void setWid(String wid) {
        this.wid = wid;
    }

    /**
     * @return the did
     */
    public String getDid() {
        return did;
    }

    /**
     * @param did the did to set
     */
    public void setDid(String did) {
        this.did = did;
    }
    
    
    public static String genorateID(){
        ResultSet rst = null;
        int count =0;  
        int temp;
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT COUNT(PID) FROM patient ";
                
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                temp = rst.getInt("COUNT(PID)");
                count+=temp;
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
            }
            count+=1;
            
            String id="";
            if(count<10){
                id = "PT0"+count;
            }
            else{
                 id = "PT"+count;
            }
        return id;
   }
    
    public static String AddPatient(String name, String address, int age, String gender, String conno, String wid, String did){
        boolean result=false;
        String pid = genorateID();
            try{
            String sql1 = "INSERT INTO patient (PID,pName, pAddress, pAge, pGender, pConNo, WID,DID) VALUES (?,?,?,?,?,?,?,?)";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, pid);
            pstm1.setObject(2, name);
            pstm1.setObject(3, address);
            pstm1.setObject(4, age);
            pstm1.setObject(5, gender);
            pstm1.setObject(6, conno);
            pstm1.setObject(7, wid);
            pstm1.setObject(8, did);
            int affectedRows1 = pstm1.executeUpdate();
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(result==true){
                return pid;
            }
            else{
                return "null";
            }
            
            
    }
    public static boolean AddPatientTreatment(String id, String treatment, String date){
        boolean result=false;
        
            try{
            String sql1 = "INSERT INTO patienttreatment (PID,treatment, date) VALUES (?,?,?)";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, id);
            pstm1.setObject(2, treatment);
            pstm1.setObject(3, date);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static boolean EditPatient(String id, String name, String address, int age, String gender, String conno, String ward, String doc){
        
         boolean result=false;
        
            try{
            String sql1 = "UPDATE patient SET pName=?,pAddress=?,pAge=?,pGender=?,pConNo=?,WID=?,DID=? WHERE PID=?";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, name);
            pstm1.setObject(2, address);
            pstm1.setObject(3, age);
            pstm1.setObject(4, gender);
            pstm1.setObject(5, conno);
            pstm1.setObject(6, ward);
            pstm1.setObject(7, doc);
            pstm1.setObject(8, id);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
        
    }
}
