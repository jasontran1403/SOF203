/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Bai1 extends javax.swing.JFrame {

        /**
         * Creates new form Bai1
         */
        public Bai1() {
                initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRed = new javax.swing.JButton();
        btnGreen = new javax.swing.JButton();
        btnYellow = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnWest = new javax.swing.JButton();
        btnNorth = new javax.swing.JButton();
        btnSouth = new javax.swing.JButton();
        btnEast = new javax.swing.JButton();
        btnCenter = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtComment = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnRed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRed.setForeground(new java.awt.Color(255, 0, 0));
        btnRed.setText("RED");
        btnRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedActionPerformed(evt);
            }
        });
        jPanel1.add(btnRed);

        btnGreen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGreen.setForeground(new java.awt.Color(51, 255, 51));
        btnGreen.setText("GREEN");
        btnGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGreenActionPerformed(evt);
            }
        });
        jPanel1.add(btnGreen);

        btnYellow.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnYellow.setForeground(new java.awt.Color(255, 255, 0));
        btnYellow.setText("YELLOW");
        btnYellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYellowActionPerformed(evt);
            }
        });
        jPanel1.add(btnYellow);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        btnWest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnWest.setText("WEST");
        btnWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWestActionPerformed(evt);
            }
        });
        jPanel2.add(btnWest, java.awt.BorderLayout.LINE_START);

        btnNorth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNorth.setText("NORTH");
        btnNorth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNorthActionPerformed(evt);
            }
        });
        jPanel2.add(btnNorth, java.awt.BorderLayout.PAGE_START);

        btnSouth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSouth.setText("SOUTH");
        btnSouth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSouthActionPerformed(evt);
            }
        });
        jPanel2.add(btnSouth, java.awt.BorderLayout.PAGE_END);

        btnEast.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEast.setText("EAST");
        btnEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEastActionPerformed(evt);
            }
        });
        jPanel2.add(btnEast, java.awt.BorderLayout.LINE_END);

        btnCenter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCenter.setText("CENTER");
        btnCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCenterActionPerformed(evt);
            }
        });
        jPanel2.add(btnCenter, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtComment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtComment.setForeground(new java.awt.Color(0, 0, 204));
        txtComment.setText("PS14820 - Trần Trung Nghĩa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtComment, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 102, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new java.awt.GridLayout(2, 5, 5, 5));
        jPanel4.add(btn1);
        jPanel4.add(btn2);
        jPanel4.add(btn3);
        jPanel4.add(btn4);
        jPanel4.add(btn5);
        jPanel4.add(btn6);
        jPanel4.add(btn7);
        jPanel4.add(btn8);
        jPanel4.add(btn9);
        jPanel4.add(btn10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void btnRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedActionPerformed
                // TODO add your handling code here:
                jPanel1.setBackground(Color.red);
        }//GEN-LAST:event_btnRedActionPerformed

        private void btnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGreenActionPerformed
                // TODO add your handling code here:
                jPanel1.setBackground(Color.green);
        }//GEN-LAST:event_btnGreenActionPerformed

        private void btnYellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYellowActionPerformed
                // TODO add your handling code here:
                jPanel1.setBackground(Color.yellow);
        }//GEN-LAST:event_btnYellowActionPerformed

        private void btnNorthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNorthActionPerformed
                // TODO add your handling code here:
                txtComment.setVerticalAlignment(SwingConstants.TOP);
        }//GEN-LAST:event_btnNorthActionPerformed

        private void btnCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCenterActionPerformed
                // TODO add your handling code here:
                txtComment.setHorizontalAlignment(SwingConstants.CENTER);
        }//GEN-LAST:event_btnCenterActionPerformed

        private void btnWestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWestActionPerformed
                // TODO add your handling code here:
                txtComment.setHorizontalAlignment(SwingConstants.LEFT);
        }//GEN-LAST:event_btnWestActionPerformed

        private void btnEastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEastActionPerformed
                // TODO add your handling code here:
                txtComment.setHorizontalAlignment(SwingConstants.RIGHT);
        }//GEN-LAST:event_btnEastActionPerformed

        private void btnSouthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSouthActionPerformed
                // TODO add your handling code here:
                txtComment.setVerticalAlignment(SwingConstants.BOTTOM);
        }//GEN-LAST:event_btnSouthActionPerformed

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
                        java.util.logging.Logger.getLogger(Bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(Bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(Bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(Bai1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new Bai1().setVisible(true);
                        }
                });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnCenter;
    private javax.swing.JButton btnEast;
    private javax.swing.JButton btnGreen;
    private javax.swing.JButton btnNorth;
    private javax.swing.JButton btnRed;
    private javax.swing.JButton btnSouth;
    private javax.swing.JButton btnWest;
    private javax.swing.JButton btnYellow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel txtComment;
    // End of variables declaration//GEN-END:variables
}
