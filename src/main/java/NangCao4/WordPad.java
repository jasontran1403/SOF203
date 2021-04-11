/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NangCao4;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

/**
 *
 * @author jason
 */
public class WordPad extends javax.swing.JFrame {

    UndoManager manager = new UndoManager();

    /**
     * Creates new form WordPad
     */
    public WordPad() {
        initComponents();
        setLocationRelativeTo(null);
        initCboSort();
        btnUndo.show(true);
        cboSize.setSelectedIndex(7);
        cboFont.setSelectedItem("Arial");
        manager = new UndoManager();
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.undo();
                } catch (Exception ex) {
                }
            }
        });
        edPage.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });
        pack();
    }

    String lstFont[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    public void initCboSort() {
        String lstFont[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        cboFont.removeAllItems();
        for (String string : lstFont) {
            cboFont.addItem(string);
        }
    }

    JEditorPane edit = new JEditorPane();

    public void AllignCenter() {
        String content = edPage.getText();
        try {
            edPage.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
            StyledDocument doc = (StyledDocument) edPage.getDocument();
            doc.insertString(0, content, attrs);
            doc.setParagraphAttributes(0, doc.getLength(), attrs, false);
        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }

    public void AllignLeft() {
        String content = edPage.getText();
        try {
            edPage.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_LEFT);
            StyledDocument doc = (StyledDocument) edPage.getDocument();
            doc.insertString(0, content, attrs);
            doc.setParagraphAttributes(0, doc.getLength(), attrs, false);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void AllignRight() {
        String content = edPage.getText();
        try {
            edPage.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_RIGHT);
            StyledDocument doc = (StyledDocument) edPage.getDocument();
            doc.insertString(0, content, attrs);
            doc.setParagraphAttributes(0, doc.getLength(), attrs, false);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void ChangeStyle() {
        int a = cboFont.getSelectedIndex();
        int vt = 0;
        for (int i = 0; i < lstFont.length; i++) {
            if (a == i) {
                vt = a;
            }
        }
        String font = lstFont[vt].toString();
        String vl = cboSize.getSelectedItem().toString();
        int value = Integer.valueOf(vl);
        edPage.setFont(new Font(font, Font.PLAIN, value));

    }

    public void Style() {
        int a = cboFont.getSelectedIndex();
        int vt = 0;
        for (int i = 0; i < lstFont.length; i++) {
            if (a == i) {
                vt = a;
            }
        }
        String font = lstFont[vt].toString();
        String vl = cboSize.getSelectedItem().toString();
        int value = Integer.valueOf(vl);
        edPage.setFont(new Font(font, Font.PLAIN, value));
        // 1 0 0
        if (b % 2 != 0 && i % 2 == 0 && u == 0) {
            edPage.setFont(new Font(font, Font.BOLD, value));
        }
        // 1 1 0
        if (b % 2 != 0 && i % 2 != 0 && u == 0) {
            edPage.setFont(new Font(font, Font.BOLD | Font.ITALIC, value));
            System.out.println("kaka");
        }
        // 1 1 1
        if (b % 2 != 0 && i % 2 != 0 && u != 0) {
            edPage.setFont(new Font(font, Font.BOLD | Font.ITALIC, value));
            Underline();
        }
        // 0 1 1
        if (b % 2 == 0 && i % 2 != 0 && u != 0) {
            edPage.setFont(new Font(font, Font.ITALIC, value));
            Underline();
        }
        // 0 0 1
        if (b % 2 == 0 && i % 2 == 0 && u != 0) {
            edPage.setFont(new Font(font, Font.PLAIN, value));
            Underline();
        }
        // 1 0 1
        if (b % 2 != 0 && i % 2 == 0 && u != 0) {
            edPage.setFont(new Font(font, Font.BOLD, value));
            Underline();
        }
        // 0 0 0
        if (b % 2 == 0 && i % 2 == 0 && u == 0) {
            edPage.setFont(new Font(font, Font.PLAIN, value));
        }

    }

    public void Underline() {
        Font font = edPage.getFont();
        Map attributes = font.getAttributes();
        if (u % 2 != 0) {
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        } else {
            attributes.put(TextAttribute.UNDERLINE, -1);
        }

        edPage.setFont(font.deriveFont(attributes));
    }

    public void Color() {
        String color = cboColor.getSelectedItem().toString();
        if (color.equalsIgnoreCase("red")) {
            edPage.setForeground(Color.red);
        }
        if (color.equalsIgnoreCase("blue")) {
            edPage.setForeground(Color.blue);
        }
        if (color.equalsIgnoreCase("green")) {
            edPage.setForeground(Color.green);
        }
        if (color.equalsIgnoreCase("yellow")) {
            edPage.setForeground(Color.yellow);
        }
        if (color.equalsIgnoreCase("black")) {
            edPage.setForeground(Color.black);
        }
        if (color.equalsIgnoreCase("white")) {
            edPage.setForeground(Color.white);
        }
        if (color.equalsIgnoreCase("pink")) {
            edPage.setForeground(Color.pink);
        }
        if (color.equalsIgnoreCase("orange")) {
            edPage.setForeground(Color.orange);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpHome = new javax.swing.JPanel();
        cboFont = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cboSize = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cboColor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnB = new javax.swing.JButton();
        btnI = new javax.swing.JButton();
        btnU = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edPage = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuFileSave = new javax.swing.JMenuItem();
        mnuFileOpen = new javax.swing.JMenuItem();
        mnuFileExit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnUndo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboFont.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cboFont.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFontItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setText("Font");

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "24", "28", "32", "36", "40", "45", "50", "60", "70", "80", "100", "120" }));
        cboSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSizeItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setText("Size");

        cboColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Black", "White", "Yellow", "Red", "Orange", "Blue", "Green", "Pink" }));
        cboColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboColorItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setText("Color");

        btnB.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnB.setText("B");
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        btnI.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        btnI.setText("I");
        btnI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIActionPerformed(evt);
            }
        });

        btnU.setText("<html><span style=\"text-allign:center; font-size: 14;\"><u>U</u></span></html>");
        btnU.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jButton4.setText("L");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setText("Style");

        jButton5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jButton5.setText("C");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jButton6.setText("R");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel5.setText("Allignment");

        javax.swing.GroupLayout jpHomeLayout = new javax.swing.GroupLayout(jpHome);
        jpHome.setLayout(jpHomeLayout);
        jpHomeLayout.setHorizontalGroup(
            jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHomeLayout.createSequentialGroup()
                .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHomeLayout.createSequentialGroup()
                                .addComponent(cboFont, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpHomeLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel1)
                                .addGap(95, 95, 95)
                                .addComponent(jLabel2)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel3))))
                    .addGroup(jpHomeLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHomeLayout.createSequentialGroup()
                                .addComponent(btnI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpHomeLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel5)))))
                .addContainerGap(672, Short.MAX_VALUE))
        );
        jpHomeLayout.setVerticalGroup(
            jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnB)
                        .addComponent(btnI)
                        .addComponent(jButton4)
                        .addComponent(jButton5)
                        .addComponent(jButton6))
                    .addComponent(btnU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(edPage);

        jMenu1.setText("File");

        mnuFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_DOWN_MASK));
        mnuFileSave.setText("Save");
        mnuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileSaveActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFileSave);

        mnuFileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_DOWN_MASK));
        mnuFileOpen.setText("Open");
        mnuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileOpenActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFileOpen);

        mnuFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.META_DOWN_MASK));
        mnuFileExit.setText("Exit");
        mnuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFileExit);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Home");

        btnUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.META_DOWN_MASK));
        btnUndo.setText("Undo");
        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoActionPerformed(evt);
            }
        });
        jMenu3.add(btnUndo);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mnuFileExitActionPerformed

    private void mnuFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileOpenActionPerformed
        // TODO add your handling code here:
        JFileChooser fchooser = new JFileChooser();
        int result = fchooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                edPage.setPage("file:///" + fchooser.getSelectedFile().getPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_mnuFileOpenActionPerformed

    private void mnuFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileSaveActionPerformed
        // TODO add your handling code here:
        JFileChooser fChooser = new JFileChooser();
        int result = fChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fname = fChooser.getSelectedFile();
            try {
                PrintWriter pw = new PrintWriter(fname);
                pw.println(edPage.getText());
                pw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_mnuFileSaveActionPerformed

    private void cboFontItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFontItemStateChanged
        // TODO add your handling code here:
        ChangeStyle();
    }//GEN-LAST:event_cboFontItemStateChanged

    private void cboSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSizeItemStateChanged
        // TODO add your handling code here:
        ChangeStyle();
    }//GEN-LAST:event_cboSizeItemStateChanged

    private void cboColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboColorItemStateChanged
        // TODO add your handling code here:
        Color();
    }//GEN-LAST:event_cboColorItemStateChanged

    int u = 0;
    private void btnUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUActionPerformed
        // TODO add your handling code here:
        u++;
        Style();
        System.out.println(u);
        if (u % 2 != 0) {
            btnU.setBackground(Color.green);
        } else {
            btnU.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btnUActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AllignLeft();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        AllignCenter();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        AllignRight();
    }//GEN-LAST:event_jButton6ActionPerformed

    int b = 0;
    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        b++;
        Style();

        if (b % 2 != 0) {
            btnB.setBackground(Color.green);
        } else {
            btnB.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btnBActionPerformed

    int i = 0;
    private void btnIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIActionPerformed
        // TODO add your handling code here:
        i++;
        Style();

        if (i % 2 != 0) {
            btnI.setBackground(Color.green);
        } else {
            btnI.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btnIActionPerformed

    private void btnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnUndoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WordPad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WordPad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WordPad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WordPad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WordPad().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnI;
    private javax.swing.JButton btnU;
    private javax.swing.JMenuItem btnUndo;
    private javax.swing.JComboBox<String> cboColor;
    private javax.swing.JComboBox<String> cboFont;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JEditorPane edPage;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpHome;
    private javax.swing.JMenuItem mnuFileExit;
    private javax.swing.JMenuItem mnuFileOpen;
    private javax.swing.JMenuItem mnuFileSave;
    // End of variables declaration//GEN-END:variables
}
