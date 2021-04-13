/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide7;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TCPServerTong {

    public static void main(String[] args) throws IOException {
//        String s;
//        String capitalizedSentence;
//        ServerSocket welcomeSocket = new ServerSocket(6789);
//        while(true) {
//            Socket connectionSocket = welcomeSocket.accept();
//            BufferedReader inFromClient = new BufferedReader(
//                    new InputStreamReader(connectionSocket.getInputStream()));
//            DataOutputStream outToClient = new DataOutputStream(
//                    connectionSocket.getOutputStream());
//            s = inFromClient.readLine();
//            double t = 0;
            
//            for(int i=0;i<s.length();i++){
//                char x1 = s.charAt(0);
//                char x2 = s.charAt(1);
//                char x3 = s.charAt(2);
//                String n1 = String.valueOf(x1);
//                String n2 = String.valueOf(x2);
//                String n3 = String.valueOf(x3);
//                if (n2.equals("+")) {
//                    t = Double.parseDouble(n1) + Double.parseDouble(n3); 
//                }
//                if (n2.equals("-")) {
//                    t = Double.parseDouble(n1) - Double.parseDouble(n3); 
//                }
//                if (n2.equals("*")) {
//                    t = Double.parseDouble(n1) * Double.parseDouble(n3); 
//                }
//                if (n2.equals("/")) {
//                    t = Double.parseDouble(n1) / Double.parseDouble(n3); 
//                }
//            } 
            String s = "2323+12312";
            int index = 0;
            for(int i=0;i<s.length();i++){
                char x = s.charAt(i);
                String n = String.valueOf(x);
                if (n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/")) {
                    index = i;
                }
            }
            System.out.println(index);

//            outToClient.writeDouble(t);
//            outToClient.flush();
        
    }
}
