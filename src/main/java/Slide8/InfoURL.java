/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide8;

import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author Administrator
 */
public class InfoURL {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://gv.poly.edu.vn/teacher/attendance/246193?action=edit&group_id=14384");
            System.out.println("Protocal: "+url.getProtocol());
            System.out.println("Host: "+url.getHost());
            System.out.println("Port: "+url.getPort());
            System.out.println("Path: "+url.getPath());
            System.out.println("File: "+url.getFile());
            System.out.println("Query: "+url.getQuery());
            System.out.println("Ref: "+url.getRef());
        }catch (MalformedURLException ex) {
                ex.printStackTrace();
        }
    }
}
