/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author jason
 */
public class Bai1 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/quanlysinhvien";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "select * from Student";
            
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("-------------------------------------");
                System.out.println("Mã SV: " + rs.getString("MaSV"));
                System.out.println("Họ tên: " + rs.getString("HoTen"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Số ĐT: " + rs.getString("SoDT"));
                
                if (rs.getString("GioiTinh").equals("0")) {
                    System.out.println("Giới tính: Nữ");
                }else {
                    System.out.println("Giới tính: Nam");
                }
            }
        }catch (Exception e) {
            
        }
    }
}
