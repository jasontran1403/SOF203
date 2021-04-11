/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide2;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Administrator
 */
public class Demo4RadioButton {
    public static void main(String[] args) {
        JFrame frame = new JFrame("RadioButton");        
        frame.setLocation(200,200);                  
        frame.setSize(200,170);         
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                            
        frame.setVisible(true);         
        JPanel panelGroup=new JPanel();
        panelGroup.setBorder(new TitledBorder("Ý kiến của bạn:"));
        panelGroup.setLayout(new GridLayout(4, 1));
        JRadioButton rad1=new JRadioButton("Lập trình Java rất dễ");
        JRadioButton rad2=new JRadioButton("Design GUI Java rất dễ");
        JRadioButton rad3=new JRadioButton("Viết web trên Java rất dễ");
        JRadioButton rad4=new JRadioButton("Tất cả đều khó");
        ButtonGroup group=new ButtonGroup();
        group.add(rad1);
        group.add(rad2);
        group.add(rad3);
        group.add(rad4);
        panelGroup.add(rad1);
        panelGroup.add(rad2);
        panelGroup.add(rad3);
        panelGroup.add(rad4);
        frame.add(panelGroup);
    }
}
