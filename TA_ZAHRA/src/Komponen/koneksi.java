/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Komponen;

/**
 *
 * @author Aries Pirnando
 */

import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author EkoNinja
 */
public class koneksi {
private static Connection koneksi;
   
public static Connection GetConnection()throws SQLException{
       try {
           if (koneksi==null){
           new Driver();
           koneksi=DriverManager.getConnection("jdbc:mysql://localhost:3306/ta_zahra","root","");
       }
    } catch (Exception e) {
           System.out.printf("no data base selected");
    }
       return koneksi;
   }
   
   
}
