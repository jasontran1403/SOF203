/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Administrator
 */
public class UDPClient {

    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser
                = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        System.out.println("Nhap chuoi ky tu: ");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket
                = new DatagramPacket(sendData, sendData.length, IPAddress, 6789);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket
                = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence
                = new String(receivePacket.getData());

        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}

