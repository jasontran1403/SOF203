/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NangCao2;

import static NangCao2.QR.createQR;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NC1 extends javax.swing.JFrame implements Runnable {

    private static int min = 29;
    private static int sec = 60;
    private static boolean RUN_TIMER = false;
    private static Thread runTimer;

    static List<Question> list = new ArrayList<>();
    static List<Question> list_10 = new ArrayList<>();
    static List<Integer> lstThuTuCauHoi = new ArrayList<>();
    static List<Choice> list_choice = new ArrayList();
    static List<Student> result_test = new ArrayList();
    boolean selected = false;

    /**
     * Creates new form NC1
     */
    public NC1() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    javax.swing.Timer t;
    public void JProgressBarTimer() {
        initComponents();
        ActionListener a = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Next();
          }
        };
        t = new javax.swing.Timer(1000, a);
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

    public void QRCode()
            throws WriterException, IOException,
            NotFoundException {

        // The data that the QR code will contain
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result_test.size(); i++) {
            sb.append("\nName: " + result_test.get(i).getFullname() + "\nID: " + result_test.get(i).getFpid() + "\nScore: " + result_test.get(i).getScore());
        }

        System.out.println(sb);

        // The path where the image will get saved
        String path = "test.png";

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

    @Override
    public void run() {
        while (RUN_TIMER) {
            try {
                sec--;
                if (sec == 0) {
                    min -= 1;
                    sec = 60;
                }

                if (min < 0) {
                    RUN_TIMER = false;
                    lblMin.setText("0");
                    lblSec.setText("0");
                    Timeout();

                } else {
                    lblMin.setText(String.valueOf(min));
                    lblSec.setText(String.valueOf(sec));
                }

                Thread.sleep(1000);

            } catch (Exception e) {

            }
        }
    }

    public void Timeout() {
        Mark();
        JOptionPane.showMessageDialog(this, "Fullname: " + result_test.get(0).getFullname() + "\nStudent ID: " + result_test.get(0).getFpid() + "\nYour score: " + result_test.get(0).getScore());
        try {
            QRCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void fillForm() {
        txaQuestion.setText(list_10.get(0).getQuestion());
        chkAnswer1.setText(list_10.get(0).getAnswer1());
        chkAnswer2.setText(list_10.get(0).getAnswer2());
        chkAnswer3.setText(list_10.get(0).getAnswer3());
        chkAnswer4.setText(list_10.get(0).getAnswer4());

    }

    public Object Open(String path) throws Exception {
        try (
                FileInputStream fin = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fin);) {
            Object obj = ois.readObject();
            return obj;
        }

    }

    public void Save(String path, Object obj) throws Exception {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(obj);
        }
    }

    public void Save() throws Exception {
        Save("question1.txt", list);
        JOptionPane.showMessageDialog(this, "Save completed!!");
    }

    public void Open() throws Exception {
        list = (List<Question>) Open("question1.txt");

    }

    public void Run() {
        try {
            Open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            lstThuTuCauHoi.add(i);
        }
        // xáo trộn danh sách
        Collections.shuffle(lstThuTuCauHoi);

        // lấy 10 vị trí đầu tiên  lstNangCao_CauHoi gán cho lst_10Q
        for (int i = 0; i < 3; i++) {
            Question list1 = new Question();
            list1.setQuestion(list.get(lstThuTuCauHoi.get(i)).getQuestion());
            list1.setAnswer1(list.get(lstThuTuCauHoi.get(i)).getAnswer1());
            list1.setAnswer2(list.get(lstThuTuCauHoi.get(i)).getAnswer2());
            list1.setAnswer3(list.get(lstThuTuCauHoi.get(i)).getAnswer3());
            list1.setAnswer4(list.get(lstThuTuCauHoi.get(i)).getAnswer4());
            list1.setAnswer4(list.get(lstThuTuCauHoi.get(i)).getAnswer4());
            list1.setRight(list.get(lstThuTuCauHoi.get(i)).getRight());
            list_10.add(list1);
        }
        fillForm();
    }

    boolean check = false;
    boolean scored = false;
    boolean trung = true;

    void Mark() {
        String choice = "";
        String q = "";

        if (chkAnswer1.isSelected()) {
            choice = chkAnswer1.getText();
            q = txaQuestion.getText();
            check = true;
        }
        if (chkAnswer2.isSelected()) {
            choice = chkAnswer2.getText();
            q = txaQuestion.getText();
            check = true;
        }
        if (chkAnswer3.isSelected()) {
            choice = chkAnswer3.getText();
            q = txaQuestion.getText();
            check = true;
        }
        if (chkAnswer4.isSelected()) {
            choice = chkAnswer4.getText();
            q = txaQuestion.getText();
            check = true;
        }

        Update();
        if (list_choice.size() == 0) {
            if (check) {
                Choice choice1 = new Choice();
                choice1.setQuestion(q);
                choice1.setChoice(choice);
                choice1.setSelected(true);
                choice1.setMarked("Notdone");
                list_choice.add(choice1);
            }
        } else {
            if (!list_choice.contains(txaQuestion.getText())) {
                if (check) {
                    Choice choice1 = new Choice();
                    choice1.setQuestion(q);
                    choice1.setChoice(choice);
                    choice1.setSelected(true);
                    choice1.setMarked("Notdone");
                    list_choice.add(choice1);
                }
            }
        }

//                if (question.getQuestion().equalsIgnoreCase(txaQuestion.getText())) {
//                    
//                }
        int score = 0;
        for (Question question : list_10) {
            for (Choice choice1 : list_choice) {
                if (choice1.getQuestion().equalsIgnoreCase(question.getQuestion())) {
                    if (choice1.getChoice().equals(question.getRight())) {
                        score += 1;
                    }
                }
            }
        }

        if (min < 0) {
            String fullname = JOptionPane.showInputDialog("Enter your fullname: ");
            while (fullname.length() == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your fullname!");
                fullname = JOptionPane.showInputDialog("Enter your fullname: ");
            }

            String fpid = JOptionPane.showInputDialog("Enter your student ID: ");
            while (fpid.length() == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your student ID!");
                fpid = JOptionPane.showInputDialog("Enter your student ID: ");
            }
            int score1 = score;

            Student stu = new Student();
            stu.setFullname(fullname);
            stu.setFpid(fpid);
            stu.setScore(score1);
            result_test.add(stu);
        }

        buttonGroup1.clearSelection();
        trung = true;
    }

    public void Update() {
        int index = 0;
        boolean isExisted = false;
        for (int i = 0; i < list_choice.size() - 1; i++) {
            if (list_choice.get(i).getQuestion().equalsIgnoreCase(txaQuestion.getText())) {
                index = i;
                isExisted = true;
            }

            if (!list_choice.get(index).getChoice().isEmpty() && list_choice.get(i).getQuestion().contains(txaQuestion.getText())) {
                list_choice.remove(index);

            }

        }
    }

    
    void Finish(int i) {
        int yesno = JOptionPane.showConfirmDialog(this, "Do you want to finish the test?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yesno == JOptionPane.YES_OPTION) {
            RUN_TIMER = false;
            runTimer.stop();
            btnFinish.setEnabled(false);

            
            int score = 0;

            for (Question question : list) {
                for (Choice choice1 : list_choice) {
                    if (question.getQuestion().equalsIgnoreCase(choice1.getQuestion())) {
                        if (question.getRight().equals(choice1.getChoice())) {
                            score += 1;
                            System.out.println("Câu đúng: " + question.getQuestion() + " " + choice1.getChoice());
                        }
                    }
                }
            }
            Update();
            String fullname = JOptionPane.showInputDialog("Enter your fullname: ");
            while (fullname.length() == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your fullname!");
                fullname = JOptionPane.showInputDialog("Enter your fullname: ");
            }

            String fpid = JOptionPane.showInputDialog("Enter your student ID: ");
            while (fpid.length() == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your student ID!");
                fpid = JOptionPane.showInputDialog("Enter your student ID: ");
            }

            int score1 = score;

            if (fullname.length() != 0 && fpid.length() != 0) {
                Student stu = new Student();
                stu.setFullname(fullname);
                stu.setFpid(fpid);
                stu.setScore(score1);
                result_test.add(stu);
            }

            int choice7 = JOptionPane.showConfirmDialog(this, "Are you sure?",
                    "Confirm", JOptionPane.OK_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (choice7 == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Fullname: " + result_test.get(0).getFullname() + "\nStudent ID: " + result_test.get(0).getFpid() + "\nYour score: " + result_test.get(0).getScore());
                try {
                    QRCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }

        }

    }

    public void First() {
        txaQuestion.setText(list_10.get(0).getQuestion());
        chkAnswer1.setText(list_10.get(0).getAnswer1());
        chkAnswer2.setText(list_10.get(0).getAnswer2());
        chkAnswer3.setText(list_10.get(0).getAnswer3());
        chkAnswer4.setText(list_10.get(0).getAnswer4());
    }

    public void Remember() {
        
        for (Choice ce : list_choice) {
            if(ce.isSelected()) {                
                if (ce.getQuestion().equals(txaQuestion.getText()) && ce.getChoice().equals(chkAnswer1.getText())) {
                    chkAnswer1.setSelected(true);
                }
                if (ce.getQuestion().equals(txaQuestion.getText()) && ce.getChoice().equals(chkAnswer2.getText())) {
                    chkAnswer2.setSelected(true);
                }
                if (ce.getQuestion().equals(txaQuestion.getText()) && ce.getChoice().equals(chkAnswer3.getText())) {
                    chkAnswer3.setSelected(true);
                }
                if (ce.getQuestion().equals(txaQuestion.getText()) && ce.getChoice().equals(chkAnswer4.getText())) {
                    chkAnswer4.setSelected(true);
                }
            }
        }
    }

    public void Next() {
        Mark();
         
        buttonGroup1.clearSelection();
        if (txaQuestion.getText().isEmpty()) {
            txaQuestion.setText(list_10.get(1).getQuestion());
            chkAnswer1.setText(list_10.get(1).getAnswer1());
            chkAnswer2.setText(list_10.get(1).getAnswer2());
            chkAnswer3.setText(list_10.get(1).getAnswer3());
            chkAnswer4.setText(list_10.get(1).getAnswer4());
        } else {
            int j = 0;
            for (int k = 0; k < list_10.size(); k++) {
                if (txaQuestion.getText().equalsIgnoreCase(list_10.get(k).getQuestion())) {
                    j = k + 1;
                    break;
                }

            }
            if (j > list_10.size() - 1) {

                j = 0;
            }
            txaQuestion.setText(list_10.get(j).getQuestion());
            chkAnswer1.setText(list_10.get(j).getAnswer1());
            chkAnswer2.setText(list_10.get(j).getAnswer2());
            chkAnswer3.setText(list_10.get(j).getAnswer3());
            chkAnswer4.setText(list_10.get(j).getAnswer4());
        }

        check = false;

        for (Choice g : list_choice) {
            System.out.println("Câu: " + g.getQuestion() + " " + g.getChoice() + " " + g.isSelected() + " " + g.getMarked());
            System.out.println("--------------------------------");
        }
        Remember();
    }

    public void Prev() {

        Mark();
        buttonGroup1.clearSelection();
        
        if (txaQuestion.getText().isEmpty()) {
            txaQuestion.setText(list_10.get(list_10.size() - 1).getQuestion());
            chkAnswer1.setText(list_10.get(list_10.size() - 1).getAnswer1());
            chkAnswer2.setText(list_10.get(list_10.size() - 1).getAnswer2());
            chkAnswer3.setText(list_10.get(list_10.size() - 1).getAnswer3());
            chkAnswer4.setText(list_10.get(list_10.size() - 1).getAnswer4());
        } else {
            int j = 0;
            for (int k = 0; k < list_10.size(); k++) {
                if (txaQuestion.getText().equalsIgnoreCase(list_10.get(k).getQuestion())) {
                    j = k - 1;
                    break;
                }

            }
            if (txaQuestion.getText().equalsIgnoreCase(list_10.get(0).getQuestion())) {
                j = list_10.size() - 1;
            }
            txaQuestion.setText(list_10.get(j).getQuestion());
            chkAnswer1.setText(list_10.get(j).getAnswer1());
            chkAnswer2.setText(list_10.get(j).getAnswer2());
            chkAnswer3.setText(list_10.get(j).getAnswer3());
            chkAnswer4.setText(list_10.get(j).getAnswer4());
        }
        check = false;
        for (Choice g : list_choice) {
            System.out.println("Câu: " + g.getQuestion() + " " + g.getChoice() + " " + g.isSelected() + " " + g.getMarked());
            System.out.println("--------------------------------");
        }
        Remember();
    }

    public void Last() {
        int last = list_10.size() - 1;
        txaQuestion.setText(list_10.get(last).getQuestion());
        chkAnswer1.setText(list_10.get(last).getAnswer1());
        chkAnswer2.setText(list_10.get(last).getAnswer2());
        chkAnswer3.setText(list_10.get(last).getAnswer3());
        chkAnswer4.setText(list_10.get(last).getAnswer4());
    }

    boolean click = true;

    public void Start(int i) {
        Run();
        First();
        fillForm();

        RUN_TIMER = true;
        runTimer = new Thread(this);
        runTimer.start();

        if (i > 1) {
            btnStart.setEnabled(false);
        }
    }

    public boolean Check() {
        for (Choice ch : list_choice) {
            for (Question qe : list_10) {
                if (ch.getQuestion().equals(qe.getQuestion())) {
                    System.out.println("Đã có");
                    return false;
                } else {
                    System.out.println("Chưa có");

                }
            }
        }
        return true;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txaQuestion = new javax.swing.JTextArea();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        Timer = new javax.swing.JPanel();
        lblMin = new javax.swing.JLabel();
        lblSec = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        chkAnswer1 = new javax.swing.JCheckBox();
        chkAnswer2 = new javax.swing.JCheckBox();
        chkAnswer3 = new javax.swing.JCheckBox();
        chkAnswer4 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        txaQuestion.setBackground(new java.awt.Color(204, 255, 204));
        txaQuestion.setColumns(20);
        txaQuestion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txaQuestion.setRows(5);
        jScrollPane1.setViewportView(txaQuestion);

        btnFirst.setBackground(new java.awt.Color(255, 204, 255));
        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 153, 102));
        btnFirst.setText("First");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(255, 204, 255));
        btnNext.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 153, 102));
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(255, 204, 255));
        btnPrev.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnPrev.setForeground(new java.awt.Color(255, 153, 102));
        btnPrev.setText("Prev");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(255, 204, 255));
        btnLast.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 153, 102));
        btnLast.setText("Last");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnFinish.setBackground(new java.awt.Color(255, 204, 255));
        btnFinish.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnFinish.setForeground(new java.awt.Color(255, 153, 102));
        btnFinish.setText("Finish");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        Timer.setBackground(new java.awt.Color(255, 153, 153));
        Timer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMin.setText("30");

        lblSec.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSec.setText("00");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Time left:");

        javax.swing.GroupLayout TimerLayout = new javax.swing.GroupLayout(Timer);
        Timer.setLayout(TimerLayout);
        TimerLayout.setHorizontalGroup(
            TimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblMin, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSec, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        TimerLayout.setVerticalGroup(
            TimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TimerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMin)
                    .addComponent(lblSec)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnStart.setBackground(new java.awt.Color(255, 255, 102));
        btnStart.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStart.setForeground(new java.awt.Color(153, 0, 153));
        btnStart.setText("START THE TEST");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        buttonGroup1.add(chkAnswer1);
        chkAnswer1.setText("Answer 1");

        buttonGroup1.add(chkAnswer2);
        chkAnswer2.setText("Answer 2");

        buttonGroup1.add(chkAnswer3);
        chkAnswer3.setText("Answer 3");

        buttonGroup1.add(chkAnswer4);
        chkAnswer4.setText("Answer 4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Timer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAnswer3)
                    .addComponent(chkAnswer2)
                    .addComponent(chkAnswer1)
                    .addComponent(chkAnswer4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnNext)
                        .addGap(63, 63, 63)
                        .addComponent(btnPrev)
                        .addGap(62, 62, 62)
                        .addComponent(btnLast)
                        .addGap(65, 65, 65)
                        .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Timer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkAnswer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAnswer2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAnswer3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAnswer4)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(btnLast)
                    .addComponent(btnFinish))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        Start(evt.getActionCommand().length());

    }//GEN-LAST:event_btnStartActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        // TODO add your handling code here:
        if (list_choice.size() != list_10.size()) {
            int choice = JOptionPane.showConfirmDialog(this, "You did not finish the test?",
                    "Confirm", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_NO_OPTION) {
                Finish(evt.getActionCommand().length());
            }
        } else {
            Finish(evt.getActionCommand().length());
        }

    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        First();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        Last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        Prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        t.start();       
    }//GEN-LAST:event_btnNextActionPerformed

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
            java.util.logging.Logger.getLogger(NC1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NC1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NC1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NC1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NC1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Timer;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnStart;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkAnswer1;
    private javax.swing.JCheckBox chkAnswer2;
    private javax.swing.JCheckBox chkAnswer3;
    private javax.swing.JCheckBox chkAnswer4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblSec;
    private javax.swing.JTextArea txaQuestion;
    // End of variables declaration//GEN-END:variables

}
