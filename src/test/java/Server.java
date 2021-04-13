
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jason
 */
public class Server {
    static Vector<ClientHandler> AllClients = new Vector<ClientHandler>();
    public static void main(String args[]) throws Exception {

        try {
            ServerSocket ss = new ServerSocket(1111);
            System.out.println("Server Started");
            while(true) {
                Socket s = ss.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String clientname = dis.readUTF();
                System.out.println("Connected With : "+clientname);
                ClientHandler client = new ClientHandler(s,clientname);
                Thread t = new Thread(client);
                AllClients.add(client);
                t.start();
                System.out.println("Ready to accept connections...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
