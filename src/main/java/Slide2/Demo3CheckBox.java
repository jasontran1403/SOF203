/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide2;

import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class Demo3CheckBox {
    public static void main(String[] args) {
            JFrame frame = new JFrame("My frame");         
            frame.setLocation(200,200);                 
            frame.setSize(150,150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                            
            frame.setVisible(true);      
            JPanel panelCheck=new JPanel();
            panelCheck.setLayout(new GridLayout(2, 2));
            JCheckBox chk1=new JCheckBox("C/C++");
            JCheckBox chk2=new JCheckBox("C#");
            JCheckBox chk3=new JCheckBox("PHP");
            JCheckBox chk4=new JCheckBox("Java");
            panelCheck.add(chk1);
            panelCheck.add(chk2);
            panelCheck.add(chk3);
            panelCheck.add(chk4);           
            frame.add(panelCheck); 
    }
}
