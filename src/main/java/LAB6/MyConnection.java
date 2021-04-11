/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAB6;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tran Trung Nghia
 */
public class MyConnection {

        public static Connection dbConnection(String serverName , int port , String databaseName , String userName , String password){
        Connection conn = null;
        try{
            String url = "jdbc:mysql://"+serverName+":"+port+"/"+databaseName;
            String user = userName;
            String pass = password;
            conn = DriverManager.getConnection(url, userName, pass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

}
