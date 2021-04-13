/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide7;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Administrator
 */
public class TCPClientTong {

    public static void main(String[] args) throws IOException {
        String sentence;
        Double modifiedSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("127.0.0.1", 6789);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        System.out.println("Nhap chuoi ky tu: ");
        sentence = inFromUser.readLine();

        outToServer.writeBytes(sentence + '\n');
        
        modifiedSentence = inputStream.readDouble();
                

        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();

    }
}
