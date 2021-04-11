package Slide1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author user
 */
public class Demo1AWT extends Frame implements ActionListener{
    Button btnYellow,btnRed;Label label = new Label();
    public Demo1AWT(String msg){  
        setTitle(msg);
        setLayout(new FlowLayout());
        btnYellow = new Button("Yellow");
        btnRed = new Button("Red");
        add(btnYellow);
        add(btnRed);
        add(label);
        btnYellow.addActionListener(this);
        btnRed.addActionListener(this);      
    }    
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if(str.equals("Yellow")){
            label.setText("Ban nhan button mau vang");
            this.setBackground(Color.yellow);
        }
        if(str.equals("Red")){
            label.setText("Ban nhan button mau do");
            this.setBackground(Color.RED);
        }
    }
    public static void main(String[] args) {
        Demo1AWT ab = new Demo1AWT("AWT Fpoly");
        ab.setSize(450, 200);
        ab.show();
    }   
}
