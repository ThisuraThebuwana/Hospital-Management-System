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
public class Team {
    private String tid;
    private String tname;
    private String tconsaltant;
    private int tsize;

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

    /**
     * @return the tname
     */
    public String getTname() {
        return tname;
    }

    /**
     * @param tname the tname to set
     */
    public void setTname(String tname) {
        this.tname = tname;
    }

    /**
     * @return the tconsaltant
     */
    public String getTconsaltant() {
        return tconsaltant;
    }

    /**
     * @param tconsaltant the tconsaltant to set
     */
    public void setTconsaltant(String tconsaltant) {
        this.tconsaltant = tconsaltant;
    }

    /**
     * @return the tsize
     */
    public int getTsize() {
        return tsize;
    }

    /**
     * @param tsize the tsize to set
     */
    public void setTsize(int tsize) {
        this.tsize = tsize;
    }
    
    public static String genorateID(){
        ResultSet rst = null;
        int count =0;  
        int temp;
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT COUNT(TID) FROM team ";
                
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                temp = rst.getInt("COUNT(TID)");
                count+=temp;
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static boolean AddTeam(String name, String consaltant){
        boolean result=false;
        String wid = genorateID();
            try{
            String sql1 = "INSERT INTO team (TID,tNAme,tConsaltant) VALUES (?,?,?)";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            //pstm.setObject(1, 1);
            pstm1.setObject(1, wid);
            pstm1.setObject(2, name);
            pstm1.setObject(3, consaltant);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean EditTeam(String id, String name, String consaltant){
        boolean result=false;
        
            try{
            String sql1 = "UPDATE team SET tName=?,tConsaltant=? WHERE TID=?";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            
            pstm1.setObject(1, name);
            pstm1.setObject(2, consaltant);
            pstm1.setObject(3, id);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static ResultSet getTeamNames(){
        ResultSet rst = null;
            
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT tName FROM team ";
                
                rst = stm.executeQuery(sql);
            }
            catch (SQLException ex) {
                Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rst;
    }
    
    public static String getIDbyName(String name){
        String id="";
        ResultSet rst=null;
        try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT TID FROM team WHERE tName='"+name+"'";
                
                rst = stm.executeQuery(sql);
            
            
            
            
            
            while(rst.next()){
                
                id = rst.getString("TID");
                
                
            }
            }
            catch (SQLException ex) {
                Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return id;
    }
    
    public static void increaseSize(String tid){
        int s=0;
        try {
            ResultSet rst=null;
            
            Statement stm = connect.createStatement();
            String sql1=null;
            String sql2=null;
            sql1 = "SELECT tSize From team WHERE TID='"+tid+"'";
            
            rst = stm.executeQuery(sql1);
            
            while(rst.next()){
                s = rst.getInt("tSize");
            }
            
            s+=1;
            
            sql2 = "UPDATE team Set tSize=? WHERE TID=?";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql2);
            
            pstm1.setObject(1, s);
            pstm1.setObject(2, tid);
            
            int affectedRows1 = pstm1.executeUpdate();
            
        
            
        } catch (SQLException ex) {
            Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
