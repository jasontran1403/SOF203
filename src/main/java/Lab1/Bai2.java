/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Bai2 extends JFrame implements ActionListener {

    private JButton bt1, bt2, bt3, bt4;
    private JTextField tf1, tf2, tf3;
    private double result;
    private Container cont;
    private JPanel panel1, panel2;

    public Bai2(String s) {
        super(s);
        cont = this.getContentPane();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLabel num1 = new JLabel("Num 1: ");
        tf1 = new JTextField();
        JLabel num2 = new JLabel("Num 2: ");
        tf2 = new JTextField();
        JLabel result = new JLabel("Result: ");
        tf3 = new JTextField();
        tf3.setEditable(false);

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 2));
        panel1.add(num1);
        panel1.add(tf1);
        panel1.add(num2);
        panel1.add(tf2);
        panel1.add(result);
        panel1.add(tf3);
        bt1 = new JButton("+");
        bt2 = new JButton("-");
        bt3 = new JButton("*");
        bt4 = new JButton("/");
        panel2 = new JPanel();
        panel2.add(bt1);
        panel2.add(bt2);
        panel2.add(bt3);
        panel2.add(bt4);

        cont.add(panel1);
        cont.add(panel2, "South");

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        this.pack();
        this.setVisible(true);
    }

    boolean checkEmpty() {
        if (tf1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DO NOT LEAVE EMPTY NUMBER!!!");
            tf1.requestFocus();
            return false;
        } else if (tf2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DO NOT LEAVE EMPTY NUMBER!!!");
            tf2.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void doPlus() {
        result = Double.parseDouble(tf1.getText()) + Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void doMinus() {
        result = Double.parseDouble(tf1.getText()) - Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void doMult() {
        result = Double.parseDouble(tf1.getText()) * Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void doDiv() {
        result = Double.parseDouble(tf1.getText()) / Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void actionPerformed(ActionEvent e) {
        if (checkEmpty()) {
            if (e.getActionCommand() == "+") {
                doPlus();
            }
            if (e.getActionCommand() == "-") {
                doMinus();
            }
            if (e.getActionCommand() == "*") {
                doMult();
            }
            if (e.getActionCommand() == "/") {
                doDiv();
            }
        }

    }

    public static void main(String args[]) {
        Bai2 Calculator = new Bai2("Calculator");
        Calculator.setLocationRelativeTo(null);
        Calculator.setSize(400, 200);
    }
}
