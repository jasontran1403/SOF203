/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class Demo2Swing extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(frame);
        frame.setVisible(true);
    }
}
// Button btnYellow,btnRed;Label label = new Label();
//    public Demo1AWT(String msg){  
//        setTitle(msg);
//        setLayout(new FlowLayout());
//        btnYellow = new Button("Yellow");
//        btnRed = new Button("Red");
//        add(btnYellow);
//        add(btnRed);
//        add(label);
//        btnYellow.addActionListener(this);
//        btnRed.addActionListener(this);      
//    }    
//    JFrame frame;  
//    public static void main(String[] args){
//    ShowDialogBox db = new ShowDialogBox();  
//    }  
//            public ShowDialogBox(){    
//            frame = new JFrame("Show Message Dialog");    
//            JButton button = new JButton("Click Me");    
//            button.addActionListener(new MyAction());    
//            frame.add(button);    frame.setSize(400, 400);    
//            frame.setVisible(true);    
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//}  
//    public class MyAction implements ActionListener{    
//    public void actionPerformed(ActionEvent e){      
//    JOptionPane.showMessageDialog(frame,"Roseindia.net");    }  }
//}
