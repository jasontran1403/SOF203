/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import static Assignment.AdminManager.take;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Registration extends javax.swing.JFrame {

    /**
     * Creates new form Registration
     */
    public Registration() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void ChangeForm() {
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }

    List<Account> list = new ArrayList<>();
    String id, pw1, pw2, fullname, address, phonenum, email, jobpos;

    String IU = null;

    void SignUp() {
        if (txtID.getText().equals("") && txtPass1.getText().equals("") && txtPass2.getText().equals("")
                && txtEmail.getText().equals("") && txtAdd.getText().equals("") && txtPhoneNum.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please do not leave blank information!!", "OK", JOptionPane.QUESTION_MESSAGE);
            return;
        }

        StringBuilder error = new StringBuilder();

        if (txtID.getText().equals("")) {
            error.append("Please enter your ID!!!\n");
        }

        if (txtPass1.getText().equals("")) {
            error.append("Please enter your Password!!!\n");
        }

        if (txtPass2.getText().equals("")) {
            error.append("Please confirm your Password!!!\n");
        }

        if (txtFullname.getText().equals("")) {
            error.append("Please enter your Fullname!!!\n");
        }

        if (txtAdd.getText().equals("")) {
            error.append("Please enter your Address!!!\n");
        }

        if (txtPhoneNum.getText().equals("")) {
            error.append("Please enter your Phone Number!!!\n");
        }

        if (txtEmail.getText().equals("")) {
            error.append("Please enter your Email!!!\n");
        }

        if (error.length() > 0) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        id = txtID.getText();
        pw1 = txtPass1.getText();
        pw2 = txtPass2.getText();
        fullname = txtFullname.getText();
        address = txtAdd.getText();
        phonenum = txtPhoneNum.getText();
        email = txtEmail.getText();
        jobpos = cboJob.getSelectedItem().toString();

        EmailValidator validator = new EmailValidator();
        try {
            if (!validator.validate(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address!!!");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (IU == null) {
            JOptionPane.showMessageDialog(this, "Please upload your photo.");
            return;
        }

        boolean ok = true;
        if (ok) {
            if (id.length() != 0 && pw1.length() != 0 && pw2.length() != 0 && fullname.length() != 0 && address.length() != 0 && phonenum.length() != 0 && email.length() != 0) {
                for (Account employee : list) {
                    if (id.equalsIgnoreCase(employee.getUsername())) {
                        JOptionPane.showMessageDialog(this, "This ID was already created.");
                        return;
                    }
                }
                try {
                    QRCode();
                    System.out.println("QR COde thành công");
                } catch (Exception e) {

                }
                Connection conn = null;
                System.out.println("----------------------");
                try {
                    String dbURL = "jdbc:mysql://localhost:3306/Account";
                    String username = "root";
                    String password = "Hai14031993";
                    conn = DriverManager.getConnection(dbURL, username, password);

                    // Kiểm tra trước khi thêm
                    java.sql.Statement st = conn.createStatement();
                    String sql = "select * from ListAccount";
                    ResultSet rs = st.executeQuery(sql);

                    // Trong khi chưa hết dữ liệu
                    boolean check = true;
                    while (rs.next()) {
                        if (rs.getString("username").equals(id)) {
                            JOptionPane.showMessageDialog(this, "This username is existed!");
                            check = false;
                            return;
                        }
                    }
                    // Câu lệnh xem dữ liệu

                    if (check) {
                        String sql1 = "INSERT INTO ListAccount VALUES ('" + id + "', '" + pw1 + "', '" + email + "', '" + phonenum + "', '" + jobpos + "');";
                        // Tạo đối tượng thực thi câu lệnh Select
                        java.sql.Statement st1 = conn.createStatement();
                        int rs1 = st1.executeUpdate(sql1);
                        System.out.println("Thêm thành công");
                    }
                    // Thực thi
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Add employee sucessfully!!!", "OK", JOptionPane.OK_OPTION);
            }

            txtID.setText("");
            txtPass1.setText("");
            txtPass2.setText("");
            txtFullname.setText("");
            txtAdd.setText("");
            txtPhoneNum.setText("");
            txtEmail.setText("");
            lblImg.setIcon(null);
            IU = null;
            ok = false;
        } else {
            IU = null;
            ok = true;
        }
    }

    JFileChooser img = new JFileChooser();

    void Resize(String pathImg) {
        try {
            ImageIcon imageIcon = new ImageIcon(pathImg);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(140, 170, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            lblImg.setIcon(new ImageIcon(newimg));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }

    void Load() {
        img.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = img.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File imcpick = img.getSelectedFile();
            String pathImg = imcpick.getAbsolutePath();
            Resize(pathImg);
            IU = pathImg;
        }
    }

    void Remove() {
        lblImg.setIcon(null);
        IU = null;
    }

    public static void createQR(String data, String path,
            String charset, Map hashMap,
            int height, int width)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }

    boolean invalid = false;

    public void QRCode()
            throws WriterException, IOException,
            NotFoundException {

        // The data that the QR code will contain
        StringBuilder sb = new StringBuilder();
        sb.append(txtID.getText() + " " + txtPass1.getText());

        System.out.println(sb);

        // The path where the image will get saved
        String path = "C:\\Users\\Jason\\Desktop\\SOF203\\images\\Login\\" + txtID.getText() + ".png";

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        createQR(String.valueOf(sb), path, charset, hashMap, 300, 300);
        System.out.println("QR Code Generated!!! ");
    }

    void Reset() {
        txtID.setText("");
        txtPass1.setText("");
        txtPass2.setText("");
        txtFullname.setText("");
        txtAdd.setText("");
        txtPhoneNum.setText("");
        txtEmail.setText("");
        lblImg.setIcon(null);
    }

    boolean pic = false;

    public static boolean take = false;

    String picker = "";

    void GetPath() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            picker = String.valueOf(fc.getSelectedFile()) + "\\" + txtID.getText() + ".png";
        }
    }

    void TakePic() {
        pic = false;
        com.github.sarxos.webcam.Webcam webcam = com.github.sarxos.webcam.Webcam.getDefault();
        JFrame frame = new JFrame("Webcam");
        frame.setPreferredSize(new Dimension(600, 480));
        frame.setLocationRelativeTo(this);
        if (txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID before taking picture!");
            txtID.requestFocus();
        } else {

            WebcamPanel panel = new WebcamPanel(webcam);
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            panel.setMirrored(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton btn1 = new JButton("Take Pics");
            btn1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        BufferedImage image = webcam.getImage();
                        GetPath();
                        IU = picker;
                        System.out.println(IU + " -- " + picker);
                        ImageIO.write(image, "PNG", new File(IU));
                        lblImg.setIcon(new ImageIcon(((new ImageIcon(IU).getImage()).
                                getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), java.awt.Image.SCALE_SMOOTH))));

//                        IU = picker;
                        frame.dispose();
                        webcam.close();
                    } catch (Exception err) {

                    }

                }
            });

            panel.add(btn1);

            frame.add(panel);

            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtPass1 = new javax.swing.JPasswordField();
        txtPass2 = new javax.swing.JPasswordField();
        btnSignUp = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSignIn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboJob = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAdd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPhoneNum = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        btnTakePic = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 51));
        jLabel1.setText("Registration");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 51));
        jLabel2.setText("Employee ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 51));
        jLabel3.setText("Password");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 255, 51));
        jLabel4.setText("Confirm Pw");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 210, -1));
        jPanel1.add(txtPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 210, -1));
        jPanel1.add(txtPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 210, -1));

        btnSignUp.setText("Sign Up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        jPanel1.add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 90, 40));

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 80, 40));

        btnSignIn.setText("Sign In");
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });
        jPanel1.add(btnSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 90, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 255, 51));
        jLabel5.setText("Job Position");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        cboJob.setBackground(new java.awt.Color(255, 204, 204));
        cboJob.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager Staff", "Lecturer" }));
        jPanel1.add(cboJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 140, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 255, 51));
        jLabel6.setText("Fullname");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));
        jPanel1.add(txtFullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 210, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 255, 51));
        jLabel7.setText("Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));
        jPanel1.add(txtAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 210, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 255, 51));
        jLabel8.setText("Phone Num");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));
        jPanel1.add(txtPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 210, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 255, 51));
        jLabel9.setText("Email");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, 50));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 210, 30));

        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 70, -1));

        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        jPanel1.add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 70, -1));

        lblImg.setBackground(new java.awt.Color(255, 255, 255));
        lblImg.setForeground(new java.awt.Color(255, 255, 255));
        lblImg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(lblImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 140, 170));

        btnTakePic.setText("Take Pic");
        btnTakePic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakePicActionPerformed(evt);
            }
        });
        jPanel1.add(btnTakePic, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 320, 100, 40));

        Background.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\registration.png")); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 609, 569));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        // TODO add your handling code here:
        SignUp();
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        Load();
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        Remove();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignInActionPerformed
        // TODO add your handling code here:
        ChangeForm();
    }//GEN-LAST:event_btnSignInActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnTakePicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakePicActionPerformed
        // TODO add your handling code here:
        TakePic();
    }//GEN-LAST:event_btnTakePicActionPerformed

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
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSignIn;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JButton btnTakePic;
    private javax.swing.JButton btnUpload;
    private javax.swing.JComboBox<String> cboJob;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImg;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtID;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtPhoneNum;
    // End of variables declaration//GEN-END:variables
}
