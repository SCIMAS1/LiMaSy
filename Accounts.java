package Library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdul
 */
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLockException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import connect.myconnec;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import javax.mail.PasswordAuthentication;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Accounts extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
String ac;
String aut;
String req;
Webcam webcam;
boolean started = false;


    /**
     * Creates new form SignUp
     * @param abc
     * @param def
     * @param ghi
     */
       
    public Accounts(String abc, String def, String ghi){
         super("LiMaSy - Developed By IT (R16) - Accounts");
        initComponents();
        setIcon();
        conn=myconnec.ConnecrDb();
        ac=abc;
        aut=def;
        req=ghi;
        jTable1();
        if(!req.equalsIgnoreCase("null")){
        req();
        }
        
        webcam = Webcam.getDefault();
          webcam.setViewSize(new Dimension(320,240));
    }
    
    private void req(){
         String user=req;
          try{
            String sql="select * from Requests where USERNAME='"+user+"' ";
            
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                  String add1=rs.getString("USERNAME");
                    jTextField1.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField2.setText(add2);
                    String add3=rs.getString("FATHER_NAME");
                    jTextField3.setText(add3);
                    String add4=rs.getString("QUALIFICATION");
                    jTextField19.setText(add4);
                    String add5=rs.getString("DEPARTMENT");
                    jComboBox1.setSelectedItem(add5);
                    String add6=rs.getString("DESIGNATION");
                    jTextField20.setText(add6);
                    String add7=rs.getString("PHONE_NO");
                    jTextField21.setText(add7);
                    String add8=rs.getString("EMAIL_ID");
                    jTextField4.setText(add8);
                    String add9=rs.getString("PASSWORD");
                    jTextField5.setText(add9);
                    imagedata1=rs.getBytes("PHOTO");
                    format1=new ImageIcon(imagedata1);
                    jLabel12.setIcon(format1);
                    icon1=null;
                    image1=null;
                    rs.close();
                    pst.close();
                }
                }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Error in Home","Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
     public void mail(){  
       String user= jTextField1.getText();
        String em= jTextField4.getText();
        String pass= jTextField5.getText();
        String name= jTextField2.getText();
        String fname= jTextField3.getText();
        String qua= jTextField19.getText();
        String des= jTextField20.getText();
        String pno= jTextField21.getText();
        String dept= (String) jComboBox1.getSelectedItem();
        String auth = (String) jComboBox13.getSelectedItem();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Login Credentials";
          String msg= "SCET - LiMaSy | Account details \n\nHi "+name+", you have been appointed as "+auth+" for LiMaSy. Following are your details:"
                        +"\nUsername: "+user+"\nPassword: "+pass+"\nName: "+name+"\nFather Name: "+fname+"\nQualification: "+qua+"\nDepartment: "
                        +dept+"\nDesignation: "+des+"\nPhone Number: "+pno+"\nEmail ID: "+em+" \nYou can change your password after logging in if required."
                        +"\n\nThis mail contains the username and password for LiMaSy login. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
     
     public void upmail(){  
        String user= jTextField17.getText();
        String em= jTextField9.getText();
        String pass= jTextField11.getText();
        String name= jTextField7.getText();
        String fname= jTextField8.getText();
        String qua= jTextField13.getText();
        String des= jTextField14.getText();
        String pno= jTextField15.getText();
        String dept= (String) jComboBox10.getSelectedItem();
        String auth = (String) jComboBox6.getSelectedItem();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Updated Credentials";
          String msg= "SCET - LiMaSy | Account Updated Details \n\nHi "+name+", your updated account details are:"
                        +"\nUsername: "+user+"\nPassword: "+pass+"\nName: "+name+"\nFather Name: "+fname+"\nQualification: "+qua+"\nDepartment: "
                        +dept+"\nDesignation: "+des+"\nAuthority: "+auth+"\nPhone Number: "+pno+"\nEmail ID: "+em+" \nPlease note if there is a change in your Authority and Passowrd."
                        +"\n\nThis mail contains the username and password for LiMaSy login. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
   
     public void recover1(){
        if(image1!=null){
            jLabel12.setIcon(image1);
        }
        else if(icon1!=null){
           jLabel12.setIcon(icon1);
        }
        else if(format1!=null){
            jLabel12.setIcon(format1);
        }
        
    }
    
    public void recover2(){
        if(image2!=null){
            jLabel25.setIcon(image2);
        }
        else if(icon2!=null){
           jLabel25.setIcon(icon2);
        }
        else if(format1!=null){
            jLabel25.setIcon(format1);
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
    
    public void close2(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel25.setIcon(null);
            recover2();
        
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
                     format1=null;
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
    
    public void Capture2(){
        if(!started){
          started=true;
          new CamVideo2().start();
      
      }
      else{
          started=false;
          try{
        Image image = webcam.getImage();
        image2= new ImageIcon(image);
                     jLabel25.setIcon(image2);
                     format1=null;
                     icon2=null;
                     File f = new File("Updated.jpg");
                     ImageIO.write((RenderedImage) image,"JPG",f);
                     filename=f.getPath();
                    
                File img=new File(filename);
                FileInputStream fis=new FileInputStream(img);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1; ) {
                    bos.write(buf,0,readNum);
                }
                cuser_image2=bos.toByteArray();
               webcam.close();
                    
        }catch(IOException | WebcamLockException ex){
            JOptionPane.showMessageDialog(null, "Error in Capture2","Error", JOptionPane.ERROR_MESSAGE);
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
    
    class CamVideo2 extends Thread{
        
        @Override
        
         public void run(){
             try{
              
               while(started){
                 
                     webcam.open();
                     Image image = webcam.getImage();
                     jLabel25.setIcon(new ImageIcon(image));
                     Thread.sleep(40);
               }
                       }catch(HeadlessException | WebcamLockException | InterruptedException e){
            JOptionPane.showMessageDialog(null, "Error in CamVideo2","Error", JOptionPane.ERROR_MESSAGE);
        }
             
         }
    }
    
    public void Create(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            recover1();
        
        }
        String d=  jComboBox1.getSelectedItem().toString();
        String a=  jComboBox13.getSelectedItem().toString();
         String n="NONE";
        String user=jTextField1.getText();
        String pass=jTextField5.getText();
        String re=jTextField6.getText();
        String ema="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(ema,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField4.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField21.getText());
          String action="Created account";
        Date da= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(da);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(da);
         if(jTextField1.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Username is required","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(jTextField2.getText().trim().isEmpty() || jTextField3.getText().trim().isEmpty()
            || jTextField4.getText().trim().isEmpty() || jTextField5.getText().trim().isEmpty() 
             || jTextField19.getText().trim().isEmpty()
            || jTextField20.getText().trim().isEmpty() || jTextField21.getText().trim().isEmpty()    ){
            JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(d.equals(n) && a.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Department and Authority","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(d.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Department","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(a.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select an Authority","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if( jTextField6.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(null, "Please re-enter the password","Error", JOptionPane.ERROR_MESSAGE);
        }

        else{
        
String sql="Select * from Accounts where USERNAME='"+user+"'";
try{
    Statement stat= conn.createStatement();
     rs=stat.executeQuery(sql);
    if(rs.next()){
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
            String sql1 = "Insert into Accounts(USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD,PHOTO) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql1);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jTextField2.getText());
            pst.setString(3, jTextField3.getText());
            pst.setString(4, jTextField19.getText());
            pst.setString(5, (String) jComboBox1.getSelectedItem());
            pst.setString(6, jTextField20.getText());
            pst.setString(7, (String) jComboBox13.getSelectedItem());
            pst.setString(8, jTextField21.getText());
            pst.setString(9, jTextField4.getText());
            pst.setString(10, jTextField5.getText());
            
            if(cuser_image!=null){
                    pst.setBytes(11, cuser_image);
                    cuser_image=null;
                    }
                    else if(auser_image!=null){
                    pst.setBytes(11, auser_image);
                    auser_image=null;
                    }
            else if(jLabel12!=null){
                    pst.setBytes(11, imagedata1);
                }
                
            pst.execute();
       String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ac+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
             jLabel12.setIcon(null);
             jTable1();
            upreq();
           
            JOptionPane.showMessageDialog(null, "New Account Created","Message", JOptionPane.INFORMATION_MESSAGE);
               
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
    
    public void upreq(){
        String user=req;
          try{
            String sql="Delete from Requests where USERNAME='"+user+"' ";
            
                pst=conn.prepareStatement(sql);
                pst.execute();
          }catch (SQLException e) {
       JOptionPane.showMessageDialog(null, e);
    }
    }
    
    public void Update(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel25.setIcon(null);
            recover2();
        }
         String d=  jComboBox10.getSelectedItem().toString();
        String a=  jComboBox6.getSelectedItem().toString();
         String n="NONE";
        String user=jTextField17.getText();
        String pass=jTextField11.getText();
        String re=jTextField12.getText();
        String ema="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(ema,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField9.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField15.getText());
             String action="Updated account";
        Date da= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(da);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(da);
         if(jTextField17.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Username is required","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(jTextField7.getText().trim().isEmpty() || jTextField8.getText().trim().isEmpty()
            || jTextField9.getText().trim().isEmpty() || jTextField11.getText().trim().isEmpty() 
             || jTextField13.getText().trim().isEmpty()
            || jTextField14.getText().trim().isEmpty() || jTextField15.getText().trim().isEmpty()    ){
            JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(jTextField12.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(null, "Please re-enter the password","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(d.equals(n) && a.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Department and Authority","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(d.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Department","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(a.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select an Authority","Error", JOptionPane.ERROR_MESSAGE);
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
        else if(user!=null){
        
String sql="Select * from Accounts where USERNAME='"+user+"'";
try{
    pst=conn.prepareStatement(sql);
     rs=pst.executeQuery();
     
    if(rs.next()){
       
        int p=JOptionPane.showConfirmDialog(null, "Altered data will be updated","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
        try{
            String sql1 = "Update Accounts set NAME=?,FATHER_NAME=?,QUALIFICATION=?,DEPARTMENT=?,DESIGNATION=?,AUTHORITY=?,PHONE_NO=?,EMAIL_ID=?,PASSWORD=?,PHOTO=? where USERNAME='"+user+"'";
            pst=conn.prepareStatement(sql1);
            pst.setString(1, jTextField7.getText());
            pst.setString(2, jTextField8.getText());
            pst.setString(3, jTextField13.getText());
            pst.setString(4, (String) jComboBox10.getSelectedItem());
            pst.setString(5, jTextField14.getText());
            pst.setString(6, (String) jComboBox6.getSelectedItem());
            pst.setString(7, jTextField15.getText());
            pst.setString(8, jTextField9.getText());
            pst.setString(9, jTextField11.getText());
            
            
            if(cuser_image2!=null){
                    pst.setBytes(10, cuser_image2);
                    cuser_image2=null;
                    }
                    else if(auser_image2!=null){
                    pst.setBytes(10, auser_image2);
                    auser_image2=null;
                    }
            else if(jLabel25!=null){
                    pst.setBytes(10, imagedata1);
                }
                
            pst.execute();
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ac+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
            jTextField12.setText("");
                   jTable1();
           JOptionPane.showMessageDialog(null, "Account Updated","Message", JOptionPane.INFORMATION_MESSAGE);
                                    
            upmail();
            }catch(MySQLIntegrityConstraintViolationException e){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    }
    else
    {
         JOptionPane.showMessageDialog(null, "Username does not exists","Error", JOptionPane.ERROR_MESSAGE);
    }
}   catch (SQLException e) {
       JOptionPane.showMessageDialog(null, e);
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
        int returnVal = chooser.showDialog(Accounts.this, null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
         try{
        File f=chooser.getSelectedFile();
        
        icon1=new ImageIcon(f.toString());
         jLabel12.setIcon(icon1);
         image1=null;
         format1=null;
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
    
    public void Attach2(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel25.setIcon(null);
            recover2();
        }
        JFileChooser chooser=new JFileChooser();
        
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & PNG Images", "jpg", "png");
         
    chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(Accounts.this, null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
         try{
        File f=chooser.getSelectedFile();
        
        icon2=new ImageIcon(f.toString());
         jLabel25.setIcon(icon2);
         format1=null;
         image2=null;
                filename=f.getPath();
                File image=new File(filename);
                FileInputStream fis=new FileInputStream(image);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1; ) {
                    bos.write(buf,0,readNum);
                }
                auser_image2=bos.toByteArray();
               
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
try{
    Statement stat=conn.createStatement();
    rs=stat.executeQuery(sql);
    if(rs.next()){
        JOptionPane.showMessageDialog(null, "Username not available","Error", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
        
        jTextField2.requestFocus();
         
    }
}   catch (SQLException e) {
        JOptionPane.showMessageDialog(null,e);
    }
}
    }
    
     public void search1(){
         
        String x=  jComboBox9.getSelectedItem().toString();
        String emp=jTextField10.getText();
        String u="USERNAME";
        String n="NAME";
        String a="AUTHORITY";
        String d="DEPARTMENT";
        String de="DESIGNATION";
        String o="NONE";
        if(aut.equalsIgnoreCase(sa)){
        if(x.equals(o)){
             if("".equals(emp)){
           
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
             }else{
                 JOptionPane.showMessageDialog(null, "Please select a valid option","Error", JOptionPane.ERROR_MESSAGE);
             }
        }
        else if(x.equals(u)){
         
              
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && USERNAME LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(n)){
         
              
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && NAME LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(d)){
         
               
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && DEPARTMENT LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(de)){
         
              
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && DESIGNATION LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(a)){
         
               
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        }
        else if(aut.equalsIgnoreCase(ad)){
        if(x.equals(o)){
             if("".equals(emp)){
           
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
             }else{
                 JOptionPane.showMessageDialog(null, "Please select a valid option","Error", JOptionPane.ERROR_MESSAGE);
             }
        }
        else if(x.equals(u)){
         
              
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && USERNAME LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(n)){
         
              
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && NAME LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(d)){
         
               
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && DEPARTMENT LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(de)){
         
              
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && DESIGNATION LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(a)){
         
               
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && AUTHORITY LIKE '%"+emp+"%'";
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        }
     }
     
    private void jTable1() {
        if(aut.equalsIgnoreCase(sa)){
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
        else if(aut.equalsIgnoreCase(ad)){
            jComboBox13.removeItem("ADMINISTRATOR");
            try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' and AUTHORITY!='"+ad+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }
    
    public void back(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            jLabel25.setIcon(null);
        
        }
        String b=ac;
        String b2=aut;
        setVisible(false);
        Home ob=new Home(b,b2);
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
        jComboBox13.setSelectedItem("NONE");
        if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
        jLabel12.setIcon(null);
        icon1=null;
            image1=null;
            format1=null;
        imagedata1=null;
        auser_image=null;
        cuser_image=null;
        
    }
    
    public void clear2(){
        jTextField17.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField10.setText("");
        jComboBox6.removeAllItems();
        jComboBox6.addItem("NONE");
        jComboBox6.addItem("ADMINISTRATOR");
        jComboBox6.addItem("EDITOR");
        jComboBox6.addItem("USER");
        jComboBox6.setSelectedItem("NONE");
        jComboBox10.setSelectedItem("NONE");
        if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
        jLabel25.setIcon(null);
        icon2=null;
            image2=null;
            format1=null;
        imagedata1=null;
        auser_image2=null;
        cuser_image2=null;
    }
    
     public void tableClick(){
         if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel25.setIcon(null);
            recover2();
        }
         if(aut.equalsIgnoreCase(sa)){
            
           
             jTextField11.setEditable(true);
        }
        try{
        int row=jTable1.getSelectedRow();
                String Table_click=(jTable1.getModel().getValueAt(row, 0).toString());
        
                
                String sql="select * from Accounts where USERNAME='"+Table_click+"' ";

                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("USERNAME");
                    jTextField17.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField7.setText(add2);
                    String add3=rs.getString("FATHER_NAME");
                    jTextField8.setText(add3);
                    String add4=rs.getString("QUALIFICATION");
                    jTextField13.setText(add4);
                    String add5=rs.getString("DEPARTMENT");
                    jComboBox10.setSelectedItem(add5);
                    String add6=rs.getString("DESIGNATION");
                    jTextField14.setText(add6);
                    String add7=rs.getString("AUTHORITY");
                    if(add7.equalsIgnoreCase(sa)){
                    jComboBox6.removeAllItems();
                    jComboBox6.addItem("NONE");
                    jComboBox6.addItem("SUPER ADMIN");
                    jComboBox6.setSelectedItem(add7);
                    jTextField11.setEditable(false);
                    }else {
                    jComboBox6.removeAllItems();
                    jComboBox6.addItem("NONE");
                    jComboBox6.addItem("ADMINISTRATOR");
                    jComboBox6.addItem("EDITOR");
                    jComboBox6.addItem("LIBRARIAN");
                    jComboBox6.setSelectedItem(add7);    
                    }
                    String add8=rs.getString("PHONE_NO");
                    jTextField15.setText(add8);
                    String add9=rs.getString("EMAIL_ID");
                    jTextField9.setText(add9);
                    String add10=rs.getString("PASSWORD");
                    jTextField11.setText(add10);
                    imagedata1=rs.getBytes("PHOTO");
                    format1=new ImageIcon(imagedata1);
                    jLabel25.setIcon(format1);
                    icon2=null;
                    image2=null;
                    rs.close();
                    pst.close();
                }
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error in tableClick","Error", JOptionPane.ERROR_MESSAGE);
            }    catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Row Empty","Error", JOptionPane.ERROR_MESSAGE);
            }catch(ArrayIndexOutOfBoundsException e){
                
            }
    }
     
     public void Requests(){
         if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            
        
        }
        String re=ac;
        String re2=aut;
        String re3="not null";
        setVisible(false);
        Requests ob;
        ob = new Requests(re,re2,re3);
        ob.setVisible(true);
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField19 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(420, 350));

        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(240, 240, 240));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jComboBox13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox13.setMaximumRowCount(3);
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ADMINISTRATOR", "EDITOR", "LIBRARIAN" }));
        jComboBox13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox13KeyPressed(evt);
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

        jTextField5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField5.setOpaque(false);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField1.setOpaque(false);
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

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel11.setText("PHOTO");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/designation.png"))); // NOI18N
        jLabel7.setText("DESIGNATION");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/email.png"))); // NOI18N
        jLabel10.setText("EMAIL ID");

        jTextField2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField2.setOpaque(false);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(5);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "BTECH LIBRARY", "MTECH LIBRARY", "IT", "CSE", "CIVIL", "MECH", "EEE", "ECE" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/branch.png"))); // NOI18N
        jLabel6.setText("DEPARTMENT");

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/password.png"))); // NOI18N
        jLabel27.setText("PASSWORD");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/authority.png"))); // NOI18N
        jLabel8.setText("AUTHORITY");

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

        jTextField3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField3.setOpaque(false);
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

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/username.png"))); // NOI18N
        jLabel2.setText("USERNAME");

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

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fname.png"))); // NOI18N
        jLabel24.setText("FATHER NAME");

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qualification.png"))); // NOI18N
        jLabel28.setText("QUALIFICATION");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel3.setText("NAME");

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("NEW ACCOUNT");

        jButton3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/register.png"))); // NOI18N
        jButton3.setText("Create");
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

        jTextField19.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField19.setOpaque(false);
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/phone.png"))); // NOI18N
        jLabel9.setText("PHONE NO");

        jTextField4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField4.setOpaque(false);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

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

        jTextField20.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField20.setOpaque(false);
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField20KeyPressed(evt);
            }
        });

        jTextField21.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField21.setOpaque(false);
        jTextField21.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jButton14.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/request.png"))); // NOI18N
        jButton14.setText("Requests");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jButton14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton14KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/re-enter.png"))); // NOI18N
        jLabel4.setText("RE-ENTER");

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField6.setOpaque(false);
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel24)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel27)
                    .addComponent(jLabel4))
                .addGap(75, 75, 75)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField19)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox13, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField20)
                    .addComponent(jTextField21)
                    .addComponent(jTextField4)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(82, 82, 82))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(63, 63, 63))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel27, jLabel28, jLabel3, jLabel4, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox13, jTextField1, jTextField19, jTextField2, jTextField20, jTextField21, jTextField3, jTextField4, jTextField5, jTextField6});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton12, jButton13, jButton14, jButton2, jButton3});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton4});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel7)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jLabel4)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel27, jLabel28, jLabel3, jLabel4, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox13, jTextField1, jTextField19, jTextField2, jTextField20, jTextField21, jTextField3, jTextField4, jTextField5, jTextField6});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton12, jButton13, jButton14, jButton2, jButton3, jButton4});

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ADD ACCOUNT", jPanel6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/re-enter.png"))); // NOI18N
        jLabel29.setText("RE-ENTER");

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/phone.png"))); // NOI18N
        jLabel19.setText("PHONE_NO");

        jTextField8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField8.setOpaque(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
        });

        jTextField13.setOpaque(false);
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField7.setOpaque(false);
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/branch.png"))); // NOI18N
        jLabel16.setText("DEPARTMENT");

        jTextField9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField9.setOpaque(false);
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField10.setOpaque(false);
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox6.setMaximumRowCount(5);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ADMINISTRATOR", "EDITOR", "LIBRARIAN" }));
        jComboBox6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox6KeyPressed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton11.setText("Back");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton11KeyPressed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        jButton9.setText("Update");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/designation.png"))); // NOI18N
        jLabel17.setText("DESIGNATION");

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qualification.png"))); // NOI18N
        jLabel26.setText("QUALIFICATION");

        jButton10.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton10.setText("Clear");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField11.setOpaque(false);
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camera.png"))); // NOI18N
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setIconTextGap(0);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel13.setText("Search by ");

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/password.png"))); // NOI18N
        jLabel21.setText("PASSWORD");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel14.setText("NAME");

        jButton8.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/attach.png"))); // NOI18N
        jButton8.setText("Attach");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });

        jComboBox9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox9.setMaximumRowCount(5);
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "USERNAME", "NAME", "DEPARTMENT", "DESIGNATION", "AUTHORITY" }));
        jComboBox9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox9KeyPressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel22.setText("PHOTO");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/authority.png"))); // NOI18N
        jLabel18.setText("AUTHORITY");

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/email.png"))); // NOI18N
        jLabel20.setText("EMAIL_ID");

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fname.png"))); // NOI18N
        jLabel15.setText("FATHER NAME");

        jComboBox10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox10.setMaximumRowCount(5);
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "BTECH LIBRARY", "MTECH LIBRARY", "IT", "CSE", "CIVIL", "MECH", "EEE", "ECE" }));
        jComboBox10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox10KeyPressed(evt);
            }
        });

        jTextField14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField14.setOpaque(false);
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
        });

        jTextField15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField15.setOpaque(false);
        jTextField15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField15MouseEntered(evt);
            }
        });
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
        });

        jTextField12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField12.setOpaque(false);
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/username.png"))); // NOI18N
        jLabel5.setText("USERNAME");

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField17.setFocusable(false);
        jTextField17.setOpaque(false);
        jTextField17.setRequestFocusEnabled(false);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel25.setOpaque(true);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setIconTextGap(0);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox6, 0, 400, Short.MAX_VALUE)))
                                .addGap(5, 5, 5))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel22))))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel26, jLabel29});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox10, jComboBox6, jTextField11, jTextField12, jTextField13, jTextField14, jTextField15, jTextField7, jTextField8, jTextField9});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField10, jTextField17});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton5, jButton7});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton8, jButton9});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel17)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(jButton5)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton10)
                        .addComponent(jButton9)
                        .addComponent(jButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel26, jLabel29});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox10, jComboBox6, jTextField11, jTextField12, jTextField13, jTextField14, jTextField15, jTextField7, jTextField8, jTextField9});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton5, jButton7, jButton8, jButton9});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField10, jTextField17});

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1363, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1363, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("UPDATE ACCOUNT", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setBounds(-8, -3, 1381, 776);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            jLabel25.setIcon(null);

        }
        jTable1();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_ENTER){

            tableClick();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        tableClick();
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            close2();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton7.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton11.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        close2();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jButton9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField11.requestFocus();
        }
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
        // TODO add your handling code here:
        String num=jTextField15.getText();
        int len=num.length();
        char c=evt.getKeyChar();
        if(c>='0' && c<='9'){

            if(len<10){
                jTextField15.setEditable(true);

            }
            else{

                jTextField15.setEditable(false);

            }
        }

        else if(c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
            jTextField15.setEditable(true);
        }

        else{

            jTextField15.setEditable(false);

        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jComboBox6.requestFocus();
        }
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField15MouseEntered
        // TODO add your handling code here:
        jTextField15.setEditable(true);
    }//GEN-LAST:event_jTextField15MouseEntered

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jComboBox6.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton10.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jComboBox10.requestFocus();
        }
    }//GEN-LAST:event_jTextField14KeyPressed

    private void jComboBox10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox10KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField14.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField14.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField13.requestFocus();
        }
    }//GEN-LAST:event_jComboBox10KeyPressed

    private void jComboBox9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jTextField10.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jComboBox9KeyPressed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Attach2();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton7.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Attach2();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Capture2();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton8.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton5.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Capture2();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField12.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField12.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            clear2();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton11.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Update();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton10.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton8.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            back();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton5.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton10.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jButton11KeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        back();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jComboBox6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField15.setEditable(true);
            jTextField15.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField15.setEditable(true);
            jTextField15.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jComboBox6KeyPressed

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        // TODO add your handling code here:
        search1();

    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField11.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField11.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField15.setEditable(true);
            jTextField15.requestFocus();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField8.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField8.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField10.requestFocus();
        }
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jComboBox10.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jComboBox10.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField8.requestFocus();
        }
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            close1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton2.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jButton1.requestFocus();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        close1();
    }//GEN-LAST:event_jButton4ActionPerformed

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

    private void jButton14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            Requests();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton12.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton3.requestFocus();
        }

        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jButton14KeyPressed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        Requests();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField21KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField21KeyTyped

    private void jTextField21KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField21KeyReleased

    private void jTextField21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyPressed
        // TODO add your handling code here:
        String num=jTextField21.getText();
        int len=num.length();
        char c=evt.getKeyChar();
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
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField4.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField4.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jComboBox13.requestFocus();
        }
    }//GEN-LAST:event_jTextField21KeyPressed

    private void jTextField21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField21MouseEntered
        // TODO add your handling code here:
        jTextField21.setEditable(true);
    }//GEN-LAST:event_jTextField21MouseEntered

    private void jTextField20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jComboBox13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jComboBox13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jTextField20KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            Capture1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton2.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton4.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jTextField20.requestFocus();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Capture1();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            Create();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton14.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton2.requestFocus();
        }

        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jTextField21.setEditable(true);
            jTextField21.requestFocus();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Create();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            back();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton1.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton12.requestFocus();
        }

        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jButton13KeyPressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        back();
    }//GEN-LAST:event_jButton13ActionPerformed

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

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            Attach1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton3.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton1.requestFocus();
        }

        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jComboBox13.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Attach1();
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            check();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTextField2.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            clear1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton13.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            jButton14.requestFocus();
        }

        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jButton12KeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        clear1();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jComboBox13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox13KeyPressed
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
            jTextField20.requestFocus();
        }
    }//GEN-LAST:event_jComboBox13KeyPressed
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
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            String m3=null;                       
            new Accounts(m,m2,m3).setVisible(true);
          
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
private ImageIcon format1=null;
private ImageIcon image1=null;
private ImageIcon image2=null;
private ImageIcon icon1=null;
private ImageIcon icon2=null;
byte[] imagedata1=null;
String filename=null;
byte[] auser_image=null;
byte[] cuser_image=null;
byte[] auser_image2=null;
byte[] cuser_image2=null;
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";
}
