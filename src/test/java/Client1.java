
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        try {

            Socket socketConnection = new Socket("127.0.0.1", 11111);

            //QUERY PASSING
            DataOutputStream outToServer = new DataOutputStream(socketConnection.getOutputStream());

            
            String SQL = sc.nextLine();
            outToServer.writeUTF(SQL);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
