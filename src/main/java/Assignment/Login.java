/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import Scan.QrCapture;
import Slide8.SendMail;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jason
 */
public class Login extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private static Login lg;
    private static SplashScreen splashScreen;
    private static SplashScreenAM splashScreenam;
    private static final long serialVersionUID = 6441489157408381878L;

    private Executor executor = Executors.newSingleThreadExecutor(this);

    private Webcam webcam = null;
    private WebcamPanel panel = null;
    public static String s, time_then;

    List<Account> list = new ArrayList<>();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        LoadData();
    }

    public void ChangeForm() {
        Registration rg = new Registration();
        rg.setVisible(true);
        this.dispose();
    }

    public void LoadData() {
        System.out.println("----------------------");
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/Account";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "select * from ListAccount";
            // Tạo đối tượng thực thi câu lệnh Select
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Thực thi
            // Nếu sách không tồn tại
            while (rs.next()) {

                Account acc = new Account();

                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setPhonenum(rs.getString("num"));
                acc.setRole(rs.getString("role"));

                list.add(acc);

            }
            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    JFrame wc = new JFrame();

    public void QR_Reader() {

        setTitle("Reading QR Code");
        Dimension size = WebcamResolution.VGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        wc.add(panel);
        wc.pack();
        wc.setVisible(true);
        wc.setResizable(false);
        executor.execute(this);
    }

    @Override
    public void run() {

        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            com.google.zxing.Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }

            if (result != null) {
                time_then = result.getText();
                System.out.println(time_then);//this is the text extracted from QR CODE
                char kyTu;
                int space = 0;
                for (int i = 0;
                        i < time_then.length();
                        i++) {
                    kyTu = time_then.charAt(i);
                    if (Character.isSpace(kyTu)) {
                        space = i;
                    }
                }

                System.out.println(space);

                txtUser.setText(time_then.substring(0, space));
                txtPass.setText(time_then.substring(space + 1, time_then.length()));
                webcam.close();
                wc.dispose();
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }

    public void Login() {
        String id = txtUser.getText();
        String pw = txtPass.getText();
        String role = "";
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(id)) {
                if (list.get(i).getPassword().equals(pw)) {
                    role = list.get(i).getRole();
                    check = true;
                } else {
                    check = false;
                }

            }
        }

        if (check == true) {
            this.dispose();
            if (role.equalsIgnoreCase("Lecturer")) {
                splashScreen = new SplashScreen();
            }
            if (role.equalsIgnoreCase("Manager Staff")) {
                splashScreenam = new SplashScreenAM();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        chkShow = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnForgot = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(153, 255, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 255));
        jLabel2.setText("Login");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        jLabel3.setBackground(new java.awt.Color(153, 255, 153));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 255));
        jLabel3.setText("Username");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, 40));

        jLabel1.setBackground(new java.awt.Color(153, 255, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 255));
        jLabel1.setText("Password");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, 40));
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 160, -1));

        txtPass.setText("123456");
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 160, -1));

        chkShow.setBackground(new java.awt.Color(204, 255, 204));
        chkShow.setForeground(new java.awt.Color(204, 255, 204));
        chkShow.setText("Show password!");
        chkShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowActionPerformed(evt);
            }
        });
        jPanel1.add(chkShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 140, -1));

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setText("Sign In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 110, 50));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 110, 50));

        jButton3.setBackground(new java.awt.Color(0, 255, 204));
        jButton3.setText("Login By QR Code");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 180, -1));

        btnForgot.setText("Forgot Password?");
        btnForgot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotActionPerformed(evt);
            }
        });
        jPanel1.add(btnForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 320, 180, 40));
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        // TODO add your handling code here:
        if (chkShow.isSelected()) {
            txtPass.setEchoChar((char) 0);
        } else {
            txtPass.setEchoChar('*');
        }
    }//GEN-LAST:event_chkShowActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ChangeForm();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        QR_Reader();
        if (!txtUser.getText().equals("") && !txtPass.getText().equals("")) {
            Login();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnForgotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotActionPerformed
        // TODO add your handling code here:
        String to = JOptionPane.showInputDialog("Enter your email to recover password!");
        String num = JOptionPane.showInputDialog("Enter your phone number to confirm this is your account!");
        String pw = "";
        boolean rec = false;
        for (Account account : list) {
            if (to.equals(account.getEmail()) && num.equals(account.getPhonenum())) {
                pw = account.getPassword();
                rec = true;
            } else {
                rec = false;
                JOptionPane.showMessageDialog(this, "Email or phone number doesnt match any account!");
            }
        }

        if (rec) {
            try {

                System.out.println(pw);
                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.port", 587);
                String accountName = "haitnps14692@fpt.edu.vn";
                String accountPassword = "Hai14031993";
                Session s = Session.getInstance(p,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(accountName, accountPassword);
                    }
                });
                String from = "haitnps14692@fpt.edu.vn";
                String subject = "Recover password from FPT PolyTechnic!";
                String body = "<html><b style=" + "color:blue>" + "Your old password is: " + pw + "<html><br>Please change it as soon as posible!";
                Message msg = new MimeMessage(s);
                msg.setFrom(new InternetAddress(from));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                msg.setSubject(subject);
                msg.setContent(body, "text/html; charset=utf-8");
                Transport.send(msg);
                JOptionPane.showMessageDialog(this, "Recover your password is successfully, please check you email!");
            } catch (MessagingException ex) {
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnForgotActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnForgot;
    private javax.swing.JCheckBox chkShow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

}
