/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Bai1 {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public Bai1() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Bai1 Control = new Bai1();
        Control.showCheckBox();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Lab1 bai 1");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 2));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showCheckBox() {
        final JCheckBox chkApple = new JCheckBox("Apple");
        final JCheckBox chkMango = new JCheckBox("Mango");
        final JCheckBox chkPeer = new JCheckBox("Peer");

        String fruits[] = new String[3];
        fruits[0] = "";
        fruits[1] = "";
        fruits[2] = "";
        chkApple.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String st = "";
                if (e.getStateChange() == 1) {
                    fruits[0] = "Apple";
                }
                if (e.getStateChange() != 1) {
                    fruits[0] = "";
                }
                for (String item : fruits) {
                    st += item + " ";
                }
                statusLabel.setText(st + (e.getStateChange() == 1 ? "Checked" : "Checked"));
                boolean allun = true;
                for (String fruit : fruits) {
                    if (fruit.length() != 0) {
                        allun = false;
                        break;
                    }
                }
                if (allun) {
                    statusLabel.setText("Empty");
                }
            }
        });

        chkMango.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String st = "";
                if (e.getStateChange() == 1) {
                    fruits[1] = "Magno";
                }
                if (e.getStateChange() != 1) {
                    fruits[1] = "";
                }
                for (String item : fruits) {
                    st += item + " ";
                }
                statusLabel.setText(st + (e.getStateChange() == 1 ? "Checked" : "Checked"));
                boolean allun = true;
                for (String fruit : fruits) {
                    if (fruit.length() != 0) {
                        allun = false;
                        break;
                    }
                }
                if (allun) {
                    statusLabel.setText("Empty");
                }
            }
        });
        chkPeer.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String st = "";
                if (e.getStateChange() == 1) {
                    fruits[2] = "Peer";
                }
                if (e.getStateChange() != 1) {
                    fruits[2] = "";
                }
                for (String item : fruits) {
                    st += item + " ";
                }
                statusLabel.setText(st + (e.getStateChange() == 1 ? "Checked" : "Checked"));
                boolean allun = true;
                for (String fruit : fruits) {
                    if (fruit.length() != 0) {
                        allun = false;
                        break;
                    }
                }
                if (allun) {
                    statusLabel.setText("Empty");
                }
            }
        });

        controlPanel.add(chkApple);
        controlPanel.add(chkMango);
        controlPanel.add(chkPeer);
        mainFrame.setVisible(true);
    }
}
