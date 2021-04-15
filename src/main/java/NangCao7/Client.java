package NangCao7;

import java.io.*;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    private String clientName;

    private Socket socket;
    private final Client[] threads;
    private int maxClientsCount;

    private BufferedReader inStream;
    private PrintWriter outStream;

    public Client(Socket socket, Client[] threads) {
        this.socket = socket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        Client[] threads = this.threads;

        try {

            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outStream = new PrintWriter(socket.getOutputStream(), true);

            String username;
            while (true) {               
                username = inStream.readLine().trim();
                if (username.indexOf('@') == -1) {
                    break;
                }
            }

            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] == this) {
                        clientName = "@" + username;
                        break;
                    }
                }
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].outStream.println(" " + username
                                + " has entered the chat");
                    }
                }
            }

            while (true) {
                String line = inStream.readLine();
                if (line.startsWith("/leave")) {
                    break;
                }
               
                if (line.startsWith("@")) {
                    String[] words = line.split("\\s", 2);
                    if (words.length > 1 && words[1] != null) {
                        words[1] = words[1].trim();
                        if (!words[1].isEmpty()) {
                            synchronized (this) {
                                for (int i = 0; i < maxClientsCount; i++) {
                                    if (threads[i] != null && threads[i] != this
                                            && threads[i].clientName != null
                                            && threads[i].clientName.equals(words[0])) {
                                        threads[i].outStream.println(" <" + username + "> " + threads[i].clientName + " " + words[1]);
                                        
                                        this.outStream.println(" <" + username + "> " + threads[i].clientName + " " + words[1]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                   
                    synchronized (this) {
                        for (int i = 0; i < maxClientsCount; i++) {
                            if (threads[i] != null && threads[i].clientName != null) {
                                threads[i].outStream.println(" <" + username + "> " + line);
                            }
                        }
                    }
                }
            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this
                            && threads[i].clientName != null) {
                        threads[i].outStream.println(" " + username
                                + " has left the chat");
                    }
                }
            }

            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                        maxClientsCount--;
                    }
                }
            }
            
            inStream.close();
            outStream.close();
            socket.close();
        } catch (IOException e) {
        }
    }
}

