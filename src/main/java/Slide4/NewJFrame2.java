/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide4;

import javax.swing.JPopupMenu;

/**
 *
 * @author teo
 */
public class NewJFrame2 extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame2
     */
    public NewJFrame2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        tbrExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnFile = new javax.swing.JMenu();
        mnFileOpen = new javax.swing.JMenuItem();
        mnFileSave = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnFileExit = new javax.swing.JMenuItem();
        mnEdit = new javax.swing.JMenu();
        mnContact = new javax.swing.JMenu();

        jMenuItem1.setText("Open");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("New");
        jPopupMenu1.add(jMenuItem2);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/home.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/printer.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/telephone.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        tbrExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/shut_down.png"))); // NOI18N
        tbrExit.setFocusable(false);
        tbrExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbrExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbrExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbrExitActionPerformed(evt);
            }
        });
        jToolBar1.add(tbrExit);

        mnFile.setText("File");

        mnFileOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/add.png"))); // NOI18N
        mnFileOpen.setText("Open...");
        mnFile.add(mnFileOpen);

        mnFileSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/save.png"))); // NOI18N
        mnFileSave.setText("Save");
        mnFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFileSaveActionPerformed(evt);
            }
        });
        mnFile.add(mnFileSave);

        jMenuItem3.setText("jMenuItem3");
        mnFile.add(jMenuItem3);
        mnFile.add(jSeparator1);

        mnFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnFileExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Slide4/images/shut_down.png"))); // NOI18N
        mnFileExit.setText("Exit");
        mnFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFileExitActionPerformed(evt);
            }
        });
        mnFile.add(mnFileExit);

        jMenuBar1.add(mnFile);

        mnEdit.setText("Edit");
        jMenuBar1.add(mnEdit);

        mnContact.setText("Contact");
        jMenuBar1.add(mnContact);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 238, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFileExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mnFileExitActionPerformed

    private void mnFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFileSaveActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnFileSaveActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_formMouseReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void tbrExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbrExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_tbrExitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu mnContact;
    private javax.swing.JMenu mnEdit;
    private javax.swing.JMenu mnFile;
    private javax.swing.JMenuItem mnFileExit;
    private javax.swing.JMenuItem mnFileOpen;
    private javax.swing.JMenuItem mnFileSave;
    private javax.swing.JButton tbrExit;
    // End of variables declaration//GEN-END:variables
}
