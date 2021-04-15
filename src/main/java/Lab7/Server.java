/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7;

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
public class Server {

    public static void main(String[] args) throws IOException {
        String s;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream());
            s = inFromClient.readLine();
            double t = 0;

            String operator = "";
            int index = 0;
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char x = s.charAt(i);
                String n = String.valueOf(x);

                if (n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/")) {
                    operator = String.valueOf(x);
                    index = i;
                }
            }
            for (int i = 0; i < index; i++) {
                char x = s.charAt(i);
                String num1 = String.valueOf(x);
                sb1.append(num1);
            }
            for (int i = index + 1; i < s.length(); i++) {
                char x = s.charAt(i);
                String num2 = String.valueOf(x);
                sb2.append(num2);
            }
            System.out.println(sb1);
            System.out.println(sb2);
            System.out.println(operator);
            if (operator.equals("+")) {
                t = Double.parseDouble(String.valueOf(sb1)) + Double.parseDouble(String.valueOf(sb2));
            }
            if (operator.equals("-")) {
                t = Double.parseDouble(String.valueOf(sb1)) - Double.parseDouble(String.valueOf(sb2));
            }
            if (operator.equals("*")) {
                t = Double.parseDouble(String.valueOf(sb1)) * Double.parseDouble(String.valueOf(sb2));
            }
            if (operator.equals("/")) {
                t = Double.parseDouble(String.valueOf(sb1)) / Double.parseDouble(String.valueOf(sb2));
            }

            outToClient.writeDouble(t);
            outToClient.flush();
        }
    }
}
