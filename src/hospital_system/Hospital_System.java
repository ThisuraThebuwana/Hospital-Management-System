/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_system;

import View.MainFrame;
import java.sql.Connection;
import hospital_system.test;

/**
 *
 * @author Thisura
 */
public class Hospital_System {

    
    public static Connection connect = DBConnection.connect();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      //  test ts = new test();
      //  System.out.println(ts.sendtodb());
      //  ts.Showdb();
      
      MainFrame mf = new MainFrame();
      mf.setVisible(true);
    }
    
    
}