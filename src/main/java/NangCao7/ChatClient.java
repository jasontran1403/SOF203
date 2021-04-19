/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NangCao7;

/**
 *
 * @author Admin
 */
public class ChatClient {

    public static void main(String[] args) {
        String IPServer = "10.82.153.65";
        String[] arguments = new String[]{IPServer};
        new ChatRoom().main(arguments);
        System.out.println(Login.username);
    }
}
