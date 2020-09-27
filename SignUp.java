/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLockException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import connect.myconnec;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author abdul
 */
public class SignUp extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
Webcam webcam;
boolean started = false;
    /**
     * Creates new form SignUp
     */
    public SignUp() {
       super("LiMaSy - Developed By IT (R16) - Sign Up");
        initComponents();
         setIcon();
        conn=myconnec.ConnecrDb();
         webcam = Webcam.getDefault();
          webcam.setViewSize(new Dimension(320,240));
    }
    
    public void recover1(){
        if(image1!=null){
            jLabel12.setIcon(image1);
        }
        else if(icon1!=null){
           jLabel12.setIcon(icon1);
        }
        
    }
    
     public void close1(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            recover1();
        
        }
    }
    
    public void Capture1(){
      if(!started){
          started=true;
          new CamVideo1().start();
      
      }
      else{
          started=false;
          try{
        Image image = webcam.getImage();
        image1= new ImageIcon(image);
                     jLabel12.setIcon(image1);
                     icon1=null;
                     File f = new File("New.jpg");
                     ImageIO.write((RenderedImage) image,"JPG",f);
                     filename=f.getPath();
                     
                File img=new File(filename);
                FileInputStream fis=new FileInputStream(img);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1; ) {
                    bos.write(buf,0,readNum);
                }
                cuser_image=bos.toByteArray();
              webcam.close();
                    
        }catch(IOException | WebcamLockException ex){
            JOptionPane.showMessageDialog(null, "Error in Capture1","Error", JOptionPane.ERROR_MESSAGE);
        }
      }
        
    }
    
      class CamVideo1 extends Thread{
        
        @Override
        
         public void run(){
             try{
              
               while(started){
                
                     webcam.open();
                     Image image = webcam.getImage();
                     jLabel12.setIcon(new ImageIcon(image));
                     Thread.sleep(40);
                 }
             }catch(HeadlessException | WebcamLockException | InterruptedException e){
            JOptionPane.showMessageDialog(null, "Error in CamVideo1","Error", JOptionPane.ERROR_MESSAGE);
        }
             
    }
    }
      
       public void Attach1(){
       if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            recover1();
        
        }
         JFileChooser chooser=new JFileChooser();
        
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & PNG Images", "jpg", "png");
         
    chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(SignUp.this, null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
         try{
        File f=chooser.getSelectedFile();
        
         icon1=new ImageIcon(f.toString());
         jLabel12.setIcon(icon1);
          image1=null;
                filename=f.getPath();
                File image=new File(filename);
                FileInputStream fis=new FileInputStream(image);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1; ) {
                    bos.write(buf,0,readNum);
                }
                auser_image=bos.toByteArray();
               
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Please select a valid file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(NullPointerException e){
                
            }
        
       }
    }
       
        public void check(){
         if(jTextField1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Username is required","Error", JOptionPane.ERROR_MESSAGE);
        }

        else{
        String user=jTextField1.getText();
String sql="Select * from Accounts where USERNAME='"+user+"'";
String sql1="Select * from Requests where USERNAME='"+user+"'";
try{
    Statement stat=conn.createStatement();
    Statement stat1=conn.createStatement();
    rs=stat.executeQuery(sql);
    ResultSet rs1=stat1.executeQuery(sql1);
    if(rs.next()){
        JOptionPane.showMessageDialog(null, "Username not available","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(rs1.next()){
        JOptionPane.showMessageDialog(null, "Username not available","Error", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
        
        jTextField2.requestFocus();
         
    }
}   catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error in check","Error", JOptionPane.ERROR_MESSAGE);
    }
}
    }
        
        public void back(){
        if(webcam.isOpen()){
            started=false;
        
                jLabel12.setIcon(null);
        webcam.close();
        }
        setVisible(false);
        Login ob=new Login();
        ob.setVisible(true);
    }
    
    public void clear1(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox1.setSelectedItem("NONE");
        if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
        jLabel12.setIcon(null);
        icon1=null;
            image1=null;
        imagedata1=null;
        auser_image=null;
        cuser_image=null;
        
    }
    
     public void mail(){  
        String user= jTextField1.getText();
        String em= jTextField4.getText();
        String pass= jTextField5.getText();
        String name= jTextField2.getText();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Request Details";
          String msg= "SCET - LiMaSy | SignUp Request \n\nHi "+name+","
                        +"\nYour signup request with Username:" +user+" and Password:" +pass+" has been sent to the admin."
                        +"\n\nThis mail is a notification that you have put a signup request. If you are not aware of this activity please contact the library incharge."
                        +"\nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
                        +"\nShadan College Of Engineering And Technology, Himayat Sagar Road, Peeranchuruvu, Hyderabad, Telangana - 500086, India.";
               
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });       
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.setFrom(new InternetAddress("Shadan College Of Engineering And Technology <collegelibrary.scet@gmail.com>"));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
           message.setSubject(sub);    
           message.setText(msg);   
           Transport.send(message);        
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
   
    public void Request(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            recover1();
        
        }
         String d=  jComboBox1.getSelectedItem().toString();
        
         String n="NONE";
        String user=jTextField1.getText();
        String pass=jTextField5.getText();
        String re=jTextField6.getText();
        String em="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(em,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField4.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField21.getText());
             
         if(jTextField1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Username required","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(jTextField2.getText().trim().isEmpty() || jTextField3.getText().trim().isEmpty()
            || jTextField4.getText().trim().isEmpty() || jTextField5.getText().trim().isEmpty() 
             || jTextField19.getText().trim().isEmpty()
            || jTextField20.getText().trim().isEmpty() || jTextField21.getText().trim().isEmpty()    ){
            JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else if(d.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Department","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else if( jTextField6.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(null, "Please re-enter the password","Error", JOptionPane.ERROR_MESSAGE);
        }

        else{
        
String sql="Select * from Accounts where USERNAME='"+user+"'";
String sql1="Select * from Requests where USERNAME='"+user+"'";
try{
    Statement stat= conn.createStatement();
    Statement stat1= conn.createStatement();
     rs=stat.executeQuery(sql);
     ResultSet rs1=stat1.executeQuery(sql1);
    if(rs.next()){
        JOptionPane.showMessageDialog(null, "Username not available","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(rs1.next()){
        JOptionPane.showMessageDialog(null, "Username not available","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(!mn.matches()){
            JOptionPane.showMessageDialog(null, "Invalid phone number","Error", JOptionPane.ERROR_MESSAGE);
        }
    else if(!me.matches()){
            JOptionPane.showMessageDialog(null, "Invalid email address","Error", JOptionPane.ERROR_MESSAGE);
        }
    else if(!re.equals(pass)){
            JOptionPane.showMessageDialog(null, "Password does not match | Please re-check","Error", JOptionPane.ERROR_MESSAGE);
        }
    
    else
    {
        try{
            String sql2 = "Insert into Requests(USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD,PHOTO) values(?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql2);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jTextField2.getText());
            pst.setString(3, jTextField3.getText());
            pst.setString(4, jTextField19.getText());
            pst.setString(5, (String) jComboBox1.getSelectedItem());
            pst.setString(6, jTextField20.getText());
            pst.setString(7, jTextField21.getText());
            pst.setString(8, jTextField4.getText());
            pst.setString(9, jTextField5.getText());
            
            if(cuser_image!=null){
                    pst.setBytes(10, cuser_image);
                    cuser_image=null;
                    }
                    else if(auser_image!=null){
                    pst.setBytes(10, auser_image);
                    auser_image=null;
                    }
            else if(jLabel12!=null){
                    pst.setBytes(11, imagedata1);
                }
                
            pst.execute();
            jLabel12.setIcon(null);
            JOptionPane.showMessageDialog(null, "Request sent","Message", JOptionPane.INFORMATION_MESSAGE);
                                     
                    mail();
                    }catch(MySQLIntegrityConstraintViolationException e){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}   catch (SQLException e) {
       JOptionPane.showMessageDialog(null, e);
    }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jTextField21 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/branch.png"))); // NOI18N
        jLabel6.setText("DEPARTMENT");

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/designation.png"))); // NOI18N
        jLabel7.setText("DESIGNATION");

        jTextField5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton13.setText("Back");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jButton13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton13KeyPressed(evt);
            }
        });

        jTextField21.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField21MouseEntered(evt);
            }
        });
        jTextField21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField21KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField21KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField21KeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel3.setText("NAME");

        jButton3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/request.png"))); // NOI18N
        jButton3.setText("Request");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel11.setText("PHOTO");

        jTextField4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("SIGN UP");

        jButton1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camera.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setIconTextGap(0);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/username.png"))); // NOI18N
        jLabel2.setText("USERNAME");

        jTextField3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/phone.png"))); // NOI18N
        jLabel9.setText("PHONE NO");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/email.png"))); // NOI18N
        jLabel10.setText("EMAIL ID");

        jTextField19.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
        });

        jTextField20.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField20KeyPressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/password.png"))); // NOI18N
        jLabel27.setText("PASSWORD");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/re-enter.png"))); // NOI18N
        jLabel4.setText("RE-ENTER");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.setOpaque(true);

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(5);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "BTECH LIBRARY", "MTECH LIBRARY", "IT", "CSE", "CIVIL", "MECH", "EEE", "ECE" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton12.setText("Clear");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton12KeyPressed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/attach.png"))); // NOI18N
        jButton2.setText("Attach");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qualification.png"))); // NOI18N
        jLabel28.setText("QUALIFICATION");

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fname.png"))); // NOI18N
        jLabel24.setText("FATHER NAME");

        jButton4.setFont(new java.awt.Font("Californian FB", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButton4.setText(" ");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setIconTextGap(0);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel1)
                .addGap(300, 300, 300))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel24)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel27)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jTextField19)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField20)
                    .addComponent(jTextField21)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addComponent(jTextField6))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel11)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addGap(42, 42, 42))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel27, jLabel28, jLabel3, jLabel4, jLabel6, jLabel7, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jTextField1, jTextField19, jTextField2, jTextField20, jTextField21, jTextField3, jTextField4, jTextField5, jTextField6});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton4});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton12, jButton13, jButton2, jButton3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel7)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel27, jLabel28, jLabel3, jLabel4, jLabel6, jLabel7, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jTextField1, jTextField19, jTextField2, jTextField20, jTextField21, jTextField3, jTextField4, jTextField5, jTextField6});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton12, jButton13, jButton2, jButton3, jButton4});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Request();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Capture1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyPressed
        // TODO add your handling code here:
        String num=jTextField21.getText();
        int len=num.length();
        char c=evt.getKeyChar();
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField4.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField20.requestFocus();
        }
        if(c>='0' && c<='9'){

            if(len<10){
                jTextField21.setEditable(true);

            }
            else{

                jTextField21.setEditable(false);

            }
        }

        else if(c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
            jTextField21.setEditable(true);
        }

        else{

            jTextField21.setEditable(false);

        }
      
    }//GEN-LAST:event_jTextField21KeyPressed

    private void jTextField21KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField21KeyReleased

    private void jTextField21KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField21KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Attach1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        clear1();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            check();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            check();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        back();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField21MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField21MouseClicked

    private void jTextField21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField21MouseEntered
        // TODO add your handling code here:
        jTextField21.setEditable(true);
    }//GEN-LAST:event_jTextField21MouseEntered

    private void jButton13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          back();
        }
              if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jTextField6.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton12.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jButton1.requestFocus();
        }
    }//GEN-LAST:event_jButton13KeyPressed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           clear1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jTextField5.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton3.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jButton13.requestFocus();
        }
    }//GEN-LAST:event_jButton12KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           Request();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jTextField4.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton2.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jButton12.requestFocus();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           Attach1();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             jTextField21.setEditable(true);
           jTextField21.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton1.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jButton3.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           Capture1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jTextField20.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jButton4.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jButton2.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton13.requestFocus();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         close1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           close1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jButton1.requestFocus();
        }
       
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jButton2.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton13.requestFocus();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField3.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField3.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jComboBox1.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jComboBox1.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField19KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField19.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField19.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField20.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField20.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField19.requestFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTextField20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyPressed
        // TODO add your handling code here:
          if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField21.setEditable(true);
              jTextField21.requestFocus();
           
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField21.setEditable(true);
             jTextField21.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jTextField20KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
          if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField5.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField21.setEditable(true);
           jTextField21.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
          if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jTextField6.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField6.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:
          if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jButton1.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField1.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
           jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void setIcon(){
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
}
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SignUp().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
byte[] imagedata1=null;
private ImageIcon image1=null;
private ImageIcon icon1=null;
String filename=null;
byte[] auser_image=null;
byte[] cuser_image=null;
}
