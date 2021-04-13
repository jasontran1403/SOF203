/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide7;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Administrator
 */
public class UDPServer {

    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(6789);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {

            DatagramPacket receivePacket
                    = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());

            InetAddress IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length, IPAddress,port);

            serverSocket.send(sendPacket);
        }
    }
}
