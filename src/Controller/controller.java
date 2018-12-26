/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


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
public class controller {
    
    public static String getIDbyName(String name, String table){
        String id="";
        ResultSet rst=null;
        try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                if(table=="patient"){
                sql = "SELECT PID FROM patient WHERE pName='"+name+"'";
                }
                if(table=="doctor"){
                sql = "SELECT DID FROM doctor WHERE dName='"+name+"'";
                }
                if(table=="ward"){
                sql = "SELECT WID FROM ward WHERE wName='"+name+"'";
                }
                if(table=="team"){
                sql = "SELECT TID FROM team WHERE tName='"+name+"'";
                }
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                id = rst.getString(1);
                
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return id;
    }
    
    
    public static String getNamebyID(String id, String table){
        String name="";
        ResultSet rst=null;
        try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                if(table=="patient"){
                sql = "SELECT pName FROM patient WHERE PID='"+id+"'";
                }
                if(table=="doctor"){
                sql = "SELECT dName FROM doctor WHERE DID='"+id+"'";
                }
                if(table=="ward"){
                sql = "SELECT wName FROM ward WHERE WID='"+id+"'";
                }
                if(table=="team"){
                sql = "SELECT tName FROM team WHERE TID='"+id+"'";
                }
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                name = rst.getString(1);
                
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return name;
    }
    
    
    public static boolean removeRow(String id, String table){
        boolean result=false;
        String sql1="";
        try{
        if(table=="patient"){
                sql1 = "DELETE FROM patient WHERE PID=?";
                }
                if(table=="doctor"){
                sql1 = "DELETE FROM doctor WHERE DID=?";
                }
                if(table=="ward"){
                sql1 = "DELETE FROM ward WHERE WID=?";
                }
                if(table=="team"){
                sql1 = "DELETE FROM team WHERE TID=?";
                }
            
            
            
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, id);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }
    
    public static ResultSet getDetailsbyDB(String id, String table){
        
        ResultSet rst=null;
        try {
            Statement stm = connect.createStatement();
            String sql=null;
            if(table=="patient"){
                sql = "SELECT * FROM patient p, patienttreatment pt WHERE p.PID=pt.PID AND p.PID='"+id+"'";
            }
            if(table=="doctor"){
                sql = "SELECT * FROM doctor WHERE DID='"+id+"'";
            }
            if(table=="ward"){
                sql = "SELECT * FROM ward WHERE WID='"+id+"'";
            }
            if(table=="team"){
                sql = "SELECT * FROM team WHERE TID='"+id+"'";
            }
            rst = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return rst;
    }
    
    public static ResultSet docTblLoad(){
        
        ResultSet rst=null;
        try {
            Statement stm = connect.createStatement();
            String sql=null;
            
                sql = "SELECT * FROM doctor";
            
            rst = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return rst;
    }
    
}
