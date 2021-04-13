/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class Client {
    static DataInputStream dis;
    static DataOutputStream dos;
    static Socket s;
    public static void main(String args[])throws Exception {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Cient Name : ");
            String name = sc.nextLine();
            s = new Socket("localhost",1111);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(name);
                Thread sendMessage = new Thread(new Runnable()  
                { 
                    @Override
                    public void run() { 
                        while (true) { 

                            String msg = sc.nextLine(); 

                            try {
                                dos.writeUTF(msg);
                                if(msg.equalsIgnoreCase("logout")) {
                                    System.out.println("Logged out");
                                    break;
                                }
                            } catch (IOException e) { 
                                System.out.println("Error in send method :"+e.toString());
                            } 
                        } 
                    } 
                }); 


                Thread readMessage = new Thread(new Runnable()  
                { 
                    @Override
                    public void run() { 

                        while (true) { 
                            try {
                                String msg = dis.readUTF();
                                if(msg.equalsIgnoreCase("logout")) {
                                    System.out.println("Logged out");
                                    break;
                                }
                                System.out.println(msg);

                            } catch (IOException e) { 

                                System.out.println("Error in read method :"+e.getMessage());
                            } 
                        } 
                    } 
                }); 

                sendMessage.start(); 
                readMessage.start(); 


    }


}

