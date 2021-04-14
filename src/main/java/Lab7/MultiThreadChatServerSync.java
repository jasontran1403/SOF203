package Lab7;

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

/**
 *
 * @author Jason
 */

public class MultiThreadChatServerSync {

    private static ServerSocket serverSocket = null;

    private static Socket clientSocket = null;

    private static final int maxClientsCount = 10;
    private static final clientThread[] threads = new clientThread[maxClientsCount];

    public static void main(String args[]) {

        int portNumber = 6789;
        if (args.length < 1) {
            System.out.println(portNumber);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }

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
                        (threads[i] = new clientThread(clientSocket, threads)).start();
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
