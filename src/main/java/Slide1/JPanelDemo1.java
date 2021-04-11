/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide1;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class JPanelDemo1 {
    public static void main(String[] args) {
        JFrame f = new JFrame("JPanel - Fpoly");
        f.setLocation(200, 200);
        f.setSize(400, 200);
        //JPanel p = new JPanel();
        JPanel p = new JPanel(new GridLayout(2, 2));        
        JButton buttons[] = new JButton[9];
        for(int i=0;i<9;i++){
            buttons[i]=new JButton("Button "+(i+1));
            p.add(buttons[i]);
        } 
        f.add(p);
        f.setVisible(true);
    }
}

//
////JPanel p = new JPanel(new GridLayout(3, 3));