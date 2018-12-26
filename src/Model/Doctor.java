/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Ward.genorateID;
import hospital_system.Hospital_System;
import static hospital_system.Hospital_System.connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thisura
 */
public class Doctor {
    private String did;
    private String dname;
    private String dtype;
    private String dContno;
    private String tid;

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

    /**
     * @return the dname
     */
    public String getDname() {
        return dname;
    }

    /**
     * @param dname the dname to set
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     * @return the dtype
     */
    public String getDtype() {
        return dtype;
    }

    /**
     * @param dtype the dtype to set
     */
    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    /**
     * @return the dContno
     */
    public String getdContno() {
        return dContno;
    }

    /**
     * @param dContno the dContno to set
     */
    public void setdContno(String dContno) {
        this.dContno = dContno;
    }
    
    /**
     * @return the tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    
    public static String genorateID(){
        ResultSet rst = null;
        int count =0;  
        int temp;
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT COUNT(DID) FROM doctor ";
                
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                temp = rst.getInt("COUNT(DID)");
                count+=temp;
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
            }
            count+=1;
            
            String id="";
            if(count<10){
                id = "DR0"+count;
            }
            else{
                 id = "DR"+count;
            }
        return id;
   }
    
    public static boolean AddDoctor(String name, String type, String conno, String tid){
        boolean result=false;
        String did = genorateID();
            try{
            String sql1 = "INSERT INTO doctor (DID,dName,dType,dConNO,TID) VALUES (?,?,?,?,?)";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            //pstm.setObject(1, 1);
            pstm1.setObject(1, did);
            pstm1.setObject(2, name);
            pstm1.setObject(3, type);
            pstm1.setObject(4, conno);
            pstm1.setObject(5, tid);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean EditDoctor(String id, String name, String type, String conno){
        boolean result=false;
        
            try{
            String sql1 = "UPDATE doctor SET dName=?,dType=?,dConNo=? WHERE DID=?";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, name);
            pstm1.setObject(2, type);
            pstm1.setObject(3, conno);
            pstm1.setObject(4, id);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ResultSet getDocNames(){
        ResultSet rst = null;
            
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT dName FROM doctor ";
                
                rst = stm.executeQuery(sql);
            }
            catch (SQLException ex) {
                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rst;
    }
    
    
     public static String getIDbyName(String name){
        String id="";
        ResultSet rst=null;
        try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT DID FROM doctor WHERE dName='"+name+"'";
                
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                id = rst.getString("DID");
                
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return id;
    }
    
}
