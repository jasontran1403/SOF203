/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jason
 */
public class Bai2 extends javax.swing.JFrame {

    int count = 0;
    List<Student> list = new ArrayList<>();

    /**
     * Creates new form Bai2
     */
    public Bai2() {
        initComponents();
        setLocationRelativeTo(null);
        LoadData();
        fillTable(0);
    }

    public void LoadData() {
        list.clear();
        System.out.println("----------------------");
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/quanlysinhvien";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "select * from Student";
            // Tạo đối tượng thực thi câu lệnh Select
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Thực thi
            // Nếu sách không tồn tại
            while (rs.next()) {
                Student stu = new Student();

                stu.setStuID(rs.getString("MaSV"));
                stu.setFullname(rs.getString("HoTen"));
                stu.setEmail(rs.getString("Email"));
                stu.setPhonenum(rs.getString("SoDT"));
                stu.setSex(rs.getString("GioiTinh"));
                stu.setAddress(rs.getString("DiaChi"));

                list.add(stu);
            }
            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fillTable(int i) {
        Student stu = list.get(i);
        txtID.setText(stu.getStuID());
        txtName.setText(stu.getFullname());
        txtEmail.setText(stu.getEmail());
        txtNum.setText(stu.getPhonenum());
        if (stu.getSex().equals("Nữ")) {
            rdoFemale.setSelected(true);
        } else {
            rdoMale.setSelected(true);
        }
        txaAdd.setText(stu.getAddress());
    }

    void SaveStudent() {
        Connection conn = null;
        String sql1 = "INSERT INTO Student (MaSV, HoTen, Email, SoDT, GioiTinh, DiaChi) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            String stuid = txtID.getText();
            String fullname = txtName.getText();
            String email = txtEmail.getText();
            String phonenum = txtNum.getText();
            String sex = "";
            if (rdoMale.isSelected()) {
                sex = "'Nam'";
            } else {
                sex = "N'Nữ'";
            }
            String address = txaAdd.getText();
            String dbURL = "jdbc:mysql://localhost:3306/quanlysinhvien";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            // Kiểm tra trước khi thêm
            java.sql.Statement st = conn.createStatement();
            String sql = "select * from Student";
            ResultSet rs = st.executeQuery(sql);

            // Trong khi chưa hết dữ liệu
            boolean check = true;
            while (rs.next()) {
                if (rs.getString("MaSV").equals(txtID.getText())) {
                    JOptionPane.showMessageDialog(this, "Username đã tồn tại");
                    check = false;
                    return;
                }
            }
            // Câu lệnh xem dữ liệu

            if (check) {

                // Tạo đối tượng thực thi câu lệnh Select
                PreparedStatement ps = conn.prepareStatement(sql1);
                ps.setString(1, stuid);
                ps.setString(2, fullname);
                ps.setString(3, email);
                ps.setString(4, phonenum);
                ps.setString(5, sex);
                ps.setString(6, address);

                System.out.println("Thêm thành công");

                int row = ps.executeUpdate();
                if (row != 0) {
                    LoadData();
                    fillTable(count);
                } else {
                    return;
                }
            }

            // Thực thi
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void Search() {
        String stuid = JOptionPane.showInputDialog("Input ID:");
        if (stuid == null) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID!");
        }
        boolean con = false;
        for (Student student : list) {
            if (stuid.equals(student.getStuID())) {
                JOptionPane.showMessageDialog(this, "<html><font color=purple>Student ID: " + student.getStuID() + "\n<html><font color=green>Fullname: " + student.getFullname()
                                            + "\n<html><font color=red>Email: " + student.getEmail() + "\n<html><font color=blue>Phone Number: " + student.getPhonenum()
                                            + "\n<html><font color=orange>Sex: " + student.getSex() + "\n<html><font color=aqua>Address: " + student.getAddress(), "Information", HEIGHT, new ImageIcon("/Users/jason/Desktop/multiple-choice-question/SOF203/src/main/resources/search.png"));
                con = true;
                break;
            }
        }
        if (con == false) {
            JOptionPane.showMessageDialog(this, "Your ID you entered is not found!");
        }
    }
   

    void DeleteStudent() {
        Connection conn = null;
        try {
            String stuid = txtID.getText();

            String dbURL = "jdbc:mysql://localhost:3306/quanlysinhvien";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            // Kiểm tra trước khi thêm
            java.sql.Statement st = conn.createStatement();
            String sql = "select * from Student";
            ResultSet rs = st.executeQuery(sql);

            // Trong khi chưa hết dữ liệu
            while (rs.next()) {
                if (rs.getString("MaSV").equals(stuid)) {
                    PreparedStatement st1 = conn.prepareStatement("DELETE FROM Student WHERE MaSV=" + "'" + stuid + "'");
                    st1.executeUpdate();
                    LoadData();
                    fillTable(count);
                }
            }

            // Thực thi
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void UpdateStudent() {
        Connection conn = null;
        try {
            String stuid = txtID.getText();

            String dbURL = "jdbc:mysql://localhost:3306/quanlysinhvien";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            // Kiểm tra trước khi thêm
            java.sql.Statement st = conn.createStatement();
            String sql = "select * from Student";
            ResultSet rs = st.executeQuery(sql);

            // Trong khi chưa hết dữ liệu
            boolean check = false;
            while (rs.next()) {
                if (rs.getString("MaSV").equals(stuid)) {
                    String query = "UPDATE Student SET HoTen=?, Email=?, SoDT=?, DiaChi=?, "
                            + "GioiTinh=? WHERE MaSV=?";
                    PreparedStatement st1 = conn.prepareStatement(query);
                    st1.setString(1, txtName.getText());
                    st1.setString(2, txtEmail.getText());
                    st1.setString(3, txtNum.getText());
                    st1.setString(4, txaAdd.getText());
                    boolean gioitinh;
                    if (rdoMale.isSelected()) {
                        st1.setString(5, "Nam");
                    } else {
                        st1.setString(5, "Nữ");
                    }
                    st1.setString(6, txtID.getText());
                    st1.executeUpdate();
                    LoadData();
                    fillTable(count);
                    check = true;
                }
            }
            if (check) {
                JOptionPane.showMessageDialog(this, "Update successfully!");
            }else {
                JOptionPane.showMessageDialog(this, "Cannot find this Student ID!");
            }

            // Thực thi
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void AddStudent() {
        txtID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtNum.setText("");
        rdoMale.setSelected(true);
        txaAdd.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaAdd = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblRecords = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ USER");

        jLabel2.setText("Stu ID");

        jLabel3.setText("Fullname");

        jLabel4.setText("Email");

        jLabel5.setText("Phone Num");

        jLabel6.setText("Sex");

        buttonGroup1.add(rdoMale);
        rdoMale.setText("Male");

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Female");

        jLabel7.setText("Address");

        txaAdd.setColumns(20);
        txaAdd.setRows(5);
        jScrollPane1.setViewportView(txaAdd);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 150, 60));

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 60));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 150, 60));

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new.png"))); // NOI18N
        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 60));

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 150, 60));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/next.png"))); // NOI18N
        btnNext.setText("NEXT");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/first.png"))); // NOI18N
        btnFirst.setText("FIRST");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/previous.png"))); // NOI18N
        btnPrev.setText("PREV");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/last.png"))); // NOI18N
        btnLast.setText("LAST");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(217, 217, 217)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoMale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoFemale))
                                    .addComponent(txtNum, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblRecords, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdoMale)
                            .addComponent(rdoFemale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        AddStudent();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DeleteStudent();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        UpdateStudent();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        count = 0;
        fillTable(0);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        count = list.size() - 1;
        fillTable(count);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        count++;
        if (count > list.size() -1) {
            count = 0;
            fillTable(count);
            return;
        }
        fillTable(count);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        count--;
        if (count < 0) {
            count = list.size() - 1;
            fillTable(count);
            return;
        }
        fillTable(count);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        SaveStudent();
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bai2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRecords;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTextArea txaAdd;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNum;
    // End of variables declaration//GEN-END:variables
}
