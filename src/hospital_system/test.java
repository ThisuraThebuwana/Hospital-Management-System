package hospital_system;


import static hospital_system.Hospital_System.connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thisura
 */
public class test {
    
    public test(){
    
    }
    
    public static boolean sendtodb(){
        
        boolean result = false;
        try{
            String sql1 = "INSERT INTO ward (wid,wname,wgender,wcapacity) VALUES (?,?,?,?)";
            
            PreparedStatement pstm1 = connect.prepareStatement(sql1);
            //pstm.setObject(1, 1);
            pstm1.setObject(1, "WD03");
            pstm1.setObject(2, "WBBBBB");
            pstm1.setObject(3, "Male");
            pstm1.setObject(4, 25);
            
            int affectedRows1 = pstm1.executeUpdate();
            
            result = (affectedRows1>0);
        
        
        
        }   
        catch (SQLException ex) {
            Logger.getLogger(Hospital_System.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static void Showdb() {
        try {
            ResultSet rst = null;
            
            try {
                
                Statement stm = connect.createStatement();
                String sql=null;
                
                sql = "SELECT * FROM ward ";
                
                rst = stm.executeQuery(sql);
            }
            catch (SQLException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            while(rst.next()){
                String wid = rst.getString("wid");
                String wname = rst.getString("wname");
                String wgender = rst.getString("wgender");
                int wcapacity = rst.getInt("wcapacity");
                
                System.out.println("ID : "+wid+"\nName : "+wname+"\nGender : "+wgender+"\nCapacity : "+wcapacity+"\n");
            }
            
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
    
