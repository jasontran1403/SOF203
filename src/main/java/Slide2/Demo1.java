/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Administrator
 */
public class Demo1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My frame");
        frame.setLocation(200, 200);
        frame.setSize(550, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon usIcon = new ImageIcon("america-usa.png");
        ImageIcon amIcon = new ImageIcon("united-kingdom.png");
        ImageIcon vnIcon = new ImageIcon("VietNam.png");
        JPanel p = new JPanel();
        JButton b1 = new JButton("Click it", usIcon);
        JButton b2 = new JButton("Click it", amIcon);
        JButton b3 = new JButton("Click it", vnIcon);
        b1.setPressedIcon(amIcon);
        b1.setRolloverIcon(vnIcon);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {   
                JOptionPane.showMessageDialog(null, "Ban chon Viet Nam");
            }
        });
        p.add(b1);  p.add(b2);  p.add(b3);
        p.setBorder(new TitledBorder(new LineBorder(Color.BLUE), "Demo"));
        frame.add(p);
        frame.setVisible(true);
    }
}
