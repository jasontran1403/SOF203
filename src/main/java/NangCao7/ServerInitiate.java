package NangCao7;

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerInitiate {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;

    private static final int maxClientsCount = 10;
    private static final Client[] threads = new Client[maxClientsCount];

    public static void main(String args[]) {

        int portNumber = 6789;

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new Client(clientSocket, threads)).start();
                        
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream OutStream = new PrintStream(clientSocket.getOutputStream());
                    OutStream.println("Server too busy. Try later.");
                    OutStream.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
