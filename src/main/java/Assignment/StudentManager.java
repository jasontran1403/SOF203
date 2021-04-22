/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import Slide8.SendMail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class StudentManager extends javax.swing.JFrame {

    List<Student> liststu = new ArrayList<>();
    List<Result> listrs = new ArrayList<>();
    List<Result> search = new ArrayList<>();
    private String header[] = {"Student ID", "Fullname", "Java", "JavaScript", "HTML/CSS", "AVERAGE"};
    private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
    private String welcome;

    /**
     * Creates new form StudentManager
     */
    public StudentManager() {
        initComponents();
        setLocationRelativeTo(null);
        LoadList();
        LoadData();
        LoadStu();
        tblList.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblList.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblList.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblList.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblList.getModel();
        model.setRowCount(0);
        for (Result rs : listrs) {
            model.addRow(new Object[]{rs.getStuid(), rs.getFullname(), rs.getJava(), rs.getJavascript(), rs.getHtmlcss(), String.format("%.2f", rs.getAverage())});
        }
    }

    boolean ten = false;

    void Change() {

        txtStudentID.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                for (Result student : search) {
                    if (txtStudentID.getText().equals(student.getStuid())) {
                        System.out.println("Dung");
                        ten = true;
                        break;
                    } else {
                        System.out.println("Sai");
                        ten = false;
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                for (Result student : search) {
                    if (txtStudentID.getText().equals(student.getStuid())) {
                        System.out.println("Dung");
                        ten = true;
                        break;
                    } else {
                        System.out.println("Sai");
                        ten = false;
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Plain text components don't fire these events.
            }
        });

        System.out.println("Dung" + ten);
        if (ten) {
            JOptionPane.showMessageDialog(this, txtStudentID.getText());
        }
    }

    void ClickTable() {
        int j = tblList.getSelectedRow();
        DefaultTableModel model1 = (DefaultTableModel) tblList.getModel();

        txtFullname.setText(model1.getValueAt(j, 1).toString());
        txtStudentID.setText(model1.getValueAt(j, 0).toString());
        txtJava.setText(model1.getValueAt(j, 2).toString());
        txtJavaScript.setText(model1.getValueAt(j, 3).toString());
        txtHTMLCSS.setText(model1.getValueAt(j, 4).toString());

        lblAverage.setText(String.format("%.2f", listrs.get(j).getAverage()));
    }

    public void LoadData() {
        listrs.clear();
        System.out.println("----------------------");
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/Account";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "SELECT StudentResult.studentid AS ID, ListStudent.fullname AS name, java, javascript, htmlcss, average FROM StudentResult INNER JOIN ListStudent ON ListStudent.studentid = StudentResult.studentid order by AVERAGE DESC LIMIT 3";
            // Tạo đối tượng thực thi câu lệnh Select
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            Vector data = null;
            tblModel.setRowCount(0);

            // Thực thi
            // Nếu sách không tồn tại
            while (rs.next()) {
                data = new Vector();
                data.add(rs.getString("ID"));
                data.add(rs.getString("name"));
                data.add(rs.getDouble("java"));
                data.add(rs.getDouble("javascript"));
                data.add(rs.getDouble("htmlcss"));
                data.add(String.format("%.2f", rs.getDouble("average")));
                // Thêm một dòng vào table model
                tblModel.addRow(data);

                tblList.setModel(tblModel);

                Result list = new Result();

                list.setStuid(rs.getString("ID"));
                list.setFullname(rs.getString("name"));
                list.setJava(rs.getDouble("java"));
                list.setJavascript(rs.getDouble("javascript"));
                list.setHtmlcss(rs.getDouble("htmlcss"));
                list.setAverage(rs.getDouble("average"));

                listrs.add(list);

            }
            conn.close();
            st.close();
            rs.close();
            System.out.println(listrs.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        fillTable();
    }

    void LoadList() {
        listrs.clear();
        search.clear();
        System.out.println("----------------------");
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/Account";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "SELECT StudentResult.studentid AS ID, ListStudent.fullname AS name, java, javascript, htmlcss, average, ListStudent.email AS email FROM StudentResult INNER JOIN ListStudent ON ListStudent.studentid = StudentResult.studentid";
            // Tạo đối tượng thực thi câu lệnh Select
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Thực thi
            // Nếu sách không tồn tại
            while (rs.next()) {
                Result list2 = new Result();

                list2.setStuid(rs.getString("ID"));
                list2.setFullname(rs.getString("name"));
                list2.setJava(rs.getDouble("java"));
                list2.setJavascript(rs.getDouble("javascript"));
                list2.setHtmlcss(rs.getDouble("htmlcss"));
                list2.setAverage(rs.getDouble("average"));
                list2.setEmail(rs.getString("email"));

                search.add(list2);
            }
            conn.close();
            st.close();
            rs.close();
            System.out.println(listrs.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoadStu() {
        liststu.clear();
        System.out.println("----------------------");
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/Account";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            String sql = "select * from ListStudent";
            // Tạo đối tượng thực thi câu lệnh Select
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Thực thi
            // Nếu sách không tồn tại
            while (rs.next()) {
                Student list = new Student();

                list.setStudentID(rs.getString("studentid"));
                list.setFullname(rs.getString("fullname"));
                list.setEmail(rs.getString("email"));
                list.setPhonenum(rs.getString("phonenum"));
                list.setSex(rs.getString("sex"));
                list.setAdd(rs.getString("address"));
                list.setImagepath(rs.getString("imgpath"));
                liststu.add(list);
            }
            conn.close();
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void AddResult() {
        Connection conn = null;
        String sql1 = "INSERT INTO StudentResult (studentid, java, javascript, htmlcss, average) VALUES (?, ?, ?, ?, ?)";
        try {
            String stuid = txtStudentID.getText();
            String java = txtJava.getText();
            String javascript = txtJavaScript.getText();
            String htmlcss = txtHTMLCSS.getText();
            double avg = (Double.parseDouble(java) + Double.parseDouble(javascript) + Double.parseDouble(htmlcss)) / 3;

            String dbURL = "jdbc:mysql://localhost:3306/Account";
            String username = "root";
            String password = "Hai14031993";
            conn = DriverManager.getConnection(dbURL, username, password);

            // Kiểm tra trước khi thêm
            java.sql.Statement st = conn.createStatement();
            String sql = "select * from StudentResult";
            ResultSet rs = st.executeQuery(sql);

            // Trong khi chưa hết dữ liệu
            boolean check = true;
            while (rs.next()) {
                if (rs.getString("studentid").equals(txtStudentID.getText())) {
                    System.out.println("Username đã tồn tại");
                    check = false;
                    return;
                }
            }
            // Câu lệnh xem dữ liệu

            if (check) {
                // Tạo đối tượng thực thi câu lệnh Select
                PreparedStatement ps = conn.prepareStatement(sql1);
                ps.setString(1, stuid);
                ps.setString(2, java);
                ps.setString(3, javascript);
                ps.setString(4, htmlcss);
                ps.setString(5, String.valueOf(avg));

                System.out.println("Thêm thành công");

                int row = ps.executeUpdate();
                if (row != 0) {
                    LoadData();
                    fillTable();
                } else {
                    return;

                }

            }

            // Thực thi
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void DeleteResult() {
        Connection conn = null;
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to update?", "Do you want to update this student", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                String stuid = txtStudentID.getText();

                String dbURL = "jdbc:mysql://localhost:3306/Account";
                String username = "root";
                String password = "Hai14031993";
                conn = DriverManager.getConnection(dbURL, username, password);

                // Kiểm tra trước khi thêm
                java.sql.Statement st = conn.createStatement();
                String sql = "select * from StudentResult";
                ResultSet rs = st.executeQuery(sql);

                // Trong khi chưa hết dữ liệu
                while (rs.next()) {
                    if (rs.getString("studentid").equals(txtStudentID.getText())) {
                        PreparedStatement st1 = conn.prepareStatement("DELETE FROM StudentResult WHERE studentid=" + "'" + stuid + "'");

                        st1.executeUpdate();
                        LoadData();
                        fillTable();
                        JOptionPane.showMessageDialog(this, "Delete result this student successfully");
                    }
                }

                // Thực thi
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void Search() {
        LoadData();
        String stuid = txtStuID.getText();
        String IMG = "";
        for (Student student : liststu) {
            if (stuid.equals(student.getStudentID())) {
                IMG = student.getImagepath();
                System.out.println(IMG);
            }
        }
        if (stuid == null) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID!");
        }
        boolean con = false;
        for (Result student : search) {
            if (stuid.equals(student.getStuid())) {
                JOptionPane.showMessageDialog(this, "<html><b color=purple>Student ID: " + student.getStuid() + "\n<html><b>Fullname: " + student.getFullname()
                        + "\n<html><b>Java: " + student.getJava() + "\n<html><b>JavaScript: " + student.getJavascript()
                        + "\n<html><b>HTML/CSS: " + student.getHtmlcss() + "\n<html><b>Average: " + String.format("%.2f", student.getAverage()), "INFORMATION", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(((new ImageIcon(IMG).getImage()).
                                getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH))));
                con = true;
                break;
            }
        }
        if (con == false) {
            JOptionPane.showMessageDialog(this, "Your ID you entered is not found!");
        }
    }

    void Update() {
        Connection conn = null;
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to update?", "Do you want to update this student", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                String stuid = txtStudentID.getText();
                String java = txtJava.getText();
                String javascript = txtJavaScript.getText();
                String htmlcss = txtHTMLCSS.getText();
                double avg = (Double.parseDouble(java) + Double.parseDouble(javascript) + Double.parseDouble(htmlcss)) / 3;

                String dbURL = "jdbc:mysql://localhost:3306/Account";
                String username = "root";
                String password = "Hai14031993";
                conn = DriverManager.getConnection(dbURL, username, password);

                // Kiểm tra trước khi thêm
                java.sql.Statement st = conn.createStatement();
                String sql = "select * from StudentResult";
                ResultSet rs = st.executeQuery(sql);

                // Trong khi chưa hết dữ liệu
                String query = "UPDATE StudentResult SET java=?, javascript=?, htmlcss=?, average=? WHERE studentid=?";
                boolean check = false;
                while (rs.next()) {
                    if (rs.getString("studentid").equals(stuid)) {

                        PreparedStatement st1 = conn.prepareStatement(query);
                        st1.setString(1, java);
                        st1.setString(2, javascript);
                        st1.setString(3, htmlcss);
                        st1.setString(4, String.valueOf(avg));
                        st1.setString(5, stuid);
                        st1.executeUpdate();

                        check = true;
                    }
                }
                if (check) {
                    LoadList();
                    LoadData();
                    LoadStu();
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Update result this student successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot find this Student ID!");
                }

                // Thực thi
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    int i = 0;

    void First() {
        txtStudentID.setText(search.get(0).getStuid());
        txtFullname.setText(search.get(0).getFullname());
        txtJava.setText(String.valueOf(search.get(0).getJava()));
        txtJavaScript.setText(String.valueOf(search.get(0).getJavascript()));
        txtHTMLCSS.setText(String.valueOf((search.get(0).getHtmlcss())));
        lblAverage.setText(String.format("%.2f", search.get(0).getAverage()));
    }

    void Last() {
        txtStudentID.setText(search.get(search.size() - 1).getStuid());
        txtFullname.setText(search.get(search.size() - 1).getFullname());
        txtJava.setText(String.valueOf(search.get(search.size() - 1).getJava()));
        txtJavaScript.setText(String.valueOf(search.get(search.size() - 1).getJavascript()));
        txtHTMLCSS.setText(String.valueOf((search.get(search.size() - 1).getHtmlcss())));
        lblAverage.setText(String.format("%.2f", search.get(search.size() - 1).getAverage()));
    }

    void Next() {
        LoadList();
        if (txtStudentID.getText().isEmpty()) {
            txtStudentID.setText(search.get(0).getStuid());
            txtFullname.setText(search.get(0).getFullname());
            txtJava.setText(String.valueOf((search.get(0).getJava())));
            txtJavaScript.setText(String.valueOf((search.get(0).getJavascript())));
            txtHTMLCSS.setText(String.valueOf((search.get(0).getHtmlcss())));
            lblAverage.setText(String.format("%.2f", (search.get(0).getAverage())));
        } else {
            int j = 0;
            for (int k = 0; k < search.size(); k++) {
                if (txtStudentID.getText().equalsIgnoreCase(search.get(k).getStuid())) {
                    j = k + 1;
                    break;
                }

            }
            if (j > search.size() - 1) {

                j = 0;
            }
            txtStudentID.setText(search.get(j).getStuid());
            txtFullname.setText(search.get(j).getFullname());
            txtJava.setText(String.valueOf((search.get(j).getJava())));
            txtJavaScript.setText(String.valueOf((search.get(j).getJavascript())));
            txtHTMLCSS.setText(String.valueOf((search.get(j).getHtmlcss())));
            lblAverage.setText(String.format("%.2f", (search.get(j).getAverage())));
        }
    }

    void Prev() {
        if (txtStudentID.getText().isEmpty()) {
            txtStudentID.setText(search.get(search.size() - 1).getStuid());
            txtFullname.setText(search.get(search.size() - 1).getFullname());
            txtJava.setText(String.valueOf((search.get(search.size() - 1).getJava())));
            txtJavaScript.setText(String.valueOf((search.get(search.size() - 1).getJavascript())));
            txtHTMLCSS.setText(String.valueOf((search.get(search.size() - 1).getHtmlcss())));
            lblAverage.setText(String.format("%.2f", search.get(search.size() - 1).getAverage()));
        } else {
            int j = 0;
            for (int k = 0; k < search.size(); k++) {
                if (txtStudentID.getText().equalsIgnoreCase(search.get(k).getStuid())) {
                    j = k - 1;
                    break;
                }

            }
            if (txtStudentID.getText().equalsIgnoreCase(search.get(0).getStuid())) {
                j = search.size() - 1;
            }
            txtStudentID.setText(search.get(j).getStuid());
            txtFullname.setText(search.get(j).getFullname());
            txtJava.setText(String.valueOf((search.get(j).getJava())));
            txtJavaScript.setText(String.valueOf(search.get(j).getJavascript()));
            txtHTMLCSS.setText(String.valueOf((search.get(j).getHtmlcss())));
            lblAverage.setText(String.format("%.2f", search.get(j).getAverage()));
        }
    }

    void SendMail() {
        String to = "", name = "";
        try {
            for (Student stu : liststu) {
                if (txtStudentID.getText().equals(stu.getStudentID())) {
                    to = stu.getEmail();
                    name = stu.getFullname();
                }
            }
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
            String subject = "Result of Semester 3, Application Developer Major";
            String body = "<html><b style=" + "color:blue>" + "Congratulations to MR: " + name + " has earned Excellent in Semester 3 of Application Developer Major</b>" + "<html><br>Java: " + txtJava.getText() + "<html><br>JavaScript: " + txtJavaScript.getText() + "<html><br>HTML/CSS: " + txtHTMLCSS.getText() + "<html><br>Average: " + String.format("%.2f", (Double.parseDouble(txtJava.getText()) + Double.parseDouble(txtJavaScript.getText()) + Double.parseDouble(txtHTMLCSS.getText())) / 3);
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setContent(body, "text/html; charset=utf-8");
            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtStuID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtJava = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtJavaScript = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHTMLCSS = new javax.swing.JTextField();
        lblAverage = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Student Management");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 153));
        jLabel2.setText("Student ID");

        btnSearch.setBackground(new java.awt.Color(0, 255, 204));
        btnSearch.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\search.png")); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 510, 80));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(null);

        btnNew.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\new.png")); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel4.add(btnNew);
        btnNew.setBounds(20, 30, 70, 40);

        btnSave.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\add.png")); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave);
        btnSave.setBounds(20, 80, 70, 40);

        btnDelete.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\delete.png")); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete);
        btnDelete.setBounds(20, 130, 70, 40);

        btnUpdate.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\edit.png")); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel4.add(btnUpdate);
        btnUpdate.setBounds(20, 180, 70, 40);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 110, 260));

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 153));
        jLabel3.setText("Fullname");

        txtFullname.setBackground(new java.awt.Color(255, 204, 204));
        txtFullname.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 153));
        jLabel4.setText("Student ID");

        txtStudentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStudentIDKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 153));
        jLabel5.setText("Java");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 153));
        jLabel6.setText("JavaScript");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 153));
        jLabel7.setText("HTML/CSS");

        lblAverage.setBackground(new java.awt.Color(255, 204, 204));
        lblAverage.setFont(new java.awt.Font("Tahoma", 1, 27)); // NOI18N
        lblAverage.setForeground(new java.awt.Color(255, 102, 0));
        lblAverage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 153));
        jLabel9.setText("Average");

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\first.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\next.png")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\previous.png")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\last.png")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtJava, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtStudentID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        .addComponent(txtFullname, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(39, 39, 39)
                                        .addComponent(txtJavaScript, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtHTMLCSS, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAverage, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtJavaScript, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHTMLCSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAverage, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 580, 260));

        tblList.setBackground(new java.awt.Color(255, 255, 153));
        tblList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "S.ID", "Fullname", "Java", "JavaScript", "HTML/CSS", "AVERAGE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblList);
        if (tblList.getColumnModel().getColumnCount() > 0) {
            tblList.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblList.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblList.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblList.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblList.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 800, 90));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 102));
        jLabel8.setText("TOP 3 HIGHEST AVERAGE SCORE");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 320, -1));

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jason\\Desktop\\SOF203\\src\\main\\java\\Assignment\\bg.png")); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 600));

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DeleteResult();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        AddResult();
        SendMail();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        for (Result student : search) {
            System.out.println(student.getStuid() + " " + student.getFullname());
            System.out.println("------------------");
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        ClickTable();
    }//GEN-LAST:event_tblListMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Update();
        String to = "", name = "";
        try {
            for (Student stu : liststu) {
                if (txtStudentID.getText().equals(stu.getStudentID())) {
                    to = stu.getEmail();
                    name = stu.getFullname();
                }
            }
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
            String subject = "Update: Result of Semester 3, Application Developer Major";
            String body = "<html><b style=" + "color:blue>" + "Congratulations to MR: " + name + " has earned Excellent in Semester 3 of Application Developer Major</b>" + "<html><br>Java: " + txtJava.getText() + "<html><br>JavaScript: " + txtJavaScript.getText() + "<html><br>HTML/CSS: " + txtHTMLCSS.getText() + "<html><br>Average: " + String.format("%.2f", (Double.parseDouble(txtJava.getText()) + Double.parseDouble(txtJavaScript.getText()) + Double.parseDouble(txtHTMLCSS.getText())) / 3);
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setContent(body, "text/html; charset=utf-8");
            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        First();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Next();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Prev();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Last();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtStudentIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentIDKeyPressed
        // TODO add your handling code here:
        try {
            for (Result student : search) {
                if (txtStudentID.getText().equals(student.getStuid())) {
                    txtFullname.setText(student.getFullname());
                    break;
                }
                if (!txtStudentID.getText().equals(student.getStuid())) {
                    txtFullname.setText("");
                }
            }
        } catch (Exception e) {

        }


    }//GEN-LAST:event_txtStudentIDKeyPressed

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
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAverage;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtHTMLCSS;
    private javax.swing.JTextField txtJava;
    private javax.swing.JTextField txtJavaScript;
    private javax.swing.JTextField txtStuID;
    private javax.swing.JTextField txtStudentID;
    // End of variables declaration//GEN-END:variables
}
