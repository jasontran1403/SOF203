
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jason
 */
public class ClientHandler extends Thread{

    private DataInputStream in;
    private DataOutputStream out;
    private String ClientName;
    private boolean login;
    private Socket Socket;
    Scanner sc = new Scanner(System.in);


    ClientHandler(Socket s,String name) throws Exception{
        this.in = new DataInputStream(s.getInputStream());
        this.out = new DataOutputStream(s.getOutputStream());
        this.login = true;
        this.ClientName = name;
        this.Socket = s;
    }
    public void run() {

        while(true) {
            try {
                String received = in.readUTF();
                if(received.equalsIgnoreCase("logout")) {
                    this.login = false;
                    this.out.writeUTF("logout");
                    int i;
                    for(i = 0; i < Server.AllClients.size(); i++) {
                        if(this.ClientName.equals(Server.AllClients.get(i).ClientName))
                            break;
                    }
                    Server.AllClients.remove(i);
                    System.out.println(this.ClientName+" logged out");
                    this.Socket.close();
                    break;
                }
                if(received.equalsIgnoreCase("getlist")) {
                    for(int i = 0; i < Server.AllClients.size(); i++) {
                        out.writeUTF(i+1 +", "+Server.AllClients.get(i).ClientName);
                    }
                    continue;
                }
                if(received.contains(",")) {
                    String[] Message = received.split(",");
                    for(ClientHandler c : Server.AllClients) {
                        if(c.ClientName.equalsIgnoreCase(Message[1]) && c.login) {
                            c.out.writeUTF(this.ClientName +" : "+ Message[0]);
                            c.out.flush();
                            break;
                        }
                    }
                }

            }catch(Exception e) {
                System.out.println("Error :"+e.getMessage());
            }
        }
        try {
            this.in.close();
            this.out.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
