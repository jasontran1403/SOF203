/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide7;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Administrator
 */
public class Demo2 {
    public static void main(String[] args) {        
        try{
            InetAddress []address = InetAddress.getAllByName("facebook.com");
            for(int i=0;i<address.length;i++){
                System.out.println("Address "+(i+1)+": "+address[i]);
            }           
        }catch(UnknownHostException ex){
            ex.printStackTrace();
        }       
    }
}
