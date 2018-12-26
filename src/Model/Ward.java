/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class Ward {
    private String wid;
    private String wname;
    private String wgender;
    private int wcapacity;

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
     * @return the wname
     */
    public String getWname() {
        return wname;
    }

    /**
     * @param wname the wname to set
     */
    public void setWname(String wname) {
        this.wname = wname;
    }

    /**
     * @return the wgender
     */
    public String getWgender() {
        return wgender;
    }

    /**
     * @param wgender the wgender to set
     */
    public void setWgender(String wgender) {
        this.wgender = wgender;
    }

    /**
     * @return the wcapacity
     */
    public int getWcapacity() {
        return wcapacity;
    }

    /**
     * @param wcapacity the wcapacity to set
     */
    public void setWcapacity(int wcapacity) {
        this.wcapacity = wcapacity;
    }
    
    public static String genorateID(){
        ResultSet rst = null;
        int count =0;  
        int temp;
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT COUNT(WID) FROM ward ";
                
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                temp = rst.getInt("COUNT(WID)");
                count+=temp;
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(Ward.class.getName()).log(Level.SEVERE, null, ex);
            }
            count+=1;
            String id="";
            if(count<10){
                id = "TM0"+count;
            }
            else{
                 id = "TM"+count;
            }
        return id;
   }
    
    
    public static boolean AddWard(String name, String type, int capacity){
        boolean result=false;
        String wid = genorateID();
            try{
            String sql1 = "INSERT INTO ward (wid,wname,wgender,wcapacity) VALUES (?,?,?,?)";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            //pstm.setObject(1, 1);
            pstm1.setObject(1, wid);
            pstm1.setObject(2, name);
            pstm1.setObject(3, type);
            pstm1.setObject(4, capacity);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean EditWard(String id, String name, String type, String capacity){
        boolean result=false;
        
            try{
            String sql1 = "UPDATE ward SET wName=?,wGender=?,wCapacity=? WHERE WID=?";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, name);
            pstm1.setObject(2, type);
            pstm1.setObject(3, capacity);
            pstm1.setObject(4, id);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ResultSet getWardIDs(){
        ResultSet rst = null;
            
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT WID FROM ward ";
                
                rst = stm.executeQuery(sql);
            }
            catch (SQLException ex) {
                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rst;
    }
    
    
}
