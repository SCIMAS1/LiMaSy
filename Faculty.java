/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLockException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public class Faculty extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
Webcam webcam;
boolean started = false;
String fa;
String aut;
    /**
     * Creates new form Students
     * @param abc
     * @param def
     */
    public Faculty(String abc, String def) {
        super("LiMaSy - Developed By IT (R16) - Faculty");
        initComponents();
        conn=myconnec.ConnecrDb();
        setIcon();
        fa=abc;
        aut=def;
        jTable1();
        webcam = Webcam.getDefault();
          webcam.setViewSize(new Dimension(320,240));
    }
    
     public void mail(){  
        String id= jTextField1.getText();
        String na= jTextField2.getText();
        String fc= jTextField3.getText();
        String qu= jTextField11.getText();
        String ds= jTextField16.getText();
        String sb= jTextField17.getText();
        String ph= jTextField4.getText();
        String em= jTextField5.getText();
        String co= (String) jComboBox1.getSelectedItem();
        String de= (String) jComboBox2.getSelectedItem();
        String su= (String) jComboBox13.getSelectedItem();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Faculty Registered";
          String msg= "SCET - LiMaSy | Faculty Details \n\nHi "+na+", your details as per provided data are:"
                        +"\nID: "+id+"\nName: " +na+"\nFather Name: "+fc+"\nQualification: "+qu+"\nCourse: "+co+"\nDepartment: "+de+"\nDesignation: "+ds+"\nSubjects: "+sb+"\nPhone number: "+ph+"\nEmail ID: "+em+"\nStatus: "+su
                        +"\n\nThese details will be used for library work purpose. If there are any changes to be made please notify the library incharge.\nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
        String id= jTextField1.getText();
        String na= jTextField2.getText();
        String fn= jTextField3.getText();
        String qu= jTextField11.getText();
        String ds= jTextField16.getText();
        String sb= jTextField17.getText();
        String ph= jTextField4.getText();
        String em= jTextField5.getText();
        String co= (String) jComboBox1.getSelectedItem();
        String de= (String) jComboBox2.getSelectedItem();
        String su= (String) jComboBox13.getSelectedItem();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Faculty Updated";
          String msg= "SCET - LiMaSy | Faculty Updated Details \n\nHi "+na+", your updated details are:"
                        +"\nID: "+id+"\nName: " +na+"\nFather Name: "+fn+"\nQualification: "+qu+"\nCourse: "+co+"\nDepartment: "+de+"\nDesignation: "+ds+"\nSubjects: "+sb+"\nPhone number: "+ph+"\nEmail ID: "+em+"\nStatus: "+su
                        +"\n\nThese details will be used for library work purpose. If there are any changes to be made please inform the library incharge. If you didn't ask for these changes please notify the library incharge. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
   
   
    public void Register(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            recover1();
        
        }
        String c=  jComboBox1.getSelectedItem().toString();
        String d=  jComboBox2.getSelectedItem().toString();
        String s=  jComboBox13.getSelectedItem().toString();
        String n="NONE";
        String em="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(em,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField5.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField4.getText());
        String action="Registered faculty";
        Date da= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(da);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(da);
        if(jTextField1.getText().trim().isEmpty() || jTextField2.getText().trim().isEmpty() 
                || jTextField3.getText().trim().isEmpty() || jTextField4.getText().trim().isEmpty() 
                || jTextField5.getText().trim().isEmpty() || jTextField11.getText().trim().isEmpty()
                || jTextField16.getText().trim().isEmpty() || jTextField17.getText().trim().isEmpty()){
                         JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
                         
                    }
        else if(c.equals(n) && d.equals(n) && s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Course, Department, and Status","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(c.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Course","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(d.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Department","Error", JOptionPane.ERROR_MESSAGE);
        }
            else if(s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Status","Error", JOptionPane.ERROR_MESSAGE);
        }
            
        else{
         String faculty=jTextField1.getText();
String sql="Select * from Faculty where FACULTY_ID='"+faculty+"'";
try{
    Statement stat=conn.createStatement();
     rs=stat.executeQuery(sql);
    if(rs.next()){
        JOptionPane.showMessageDialog(null, "Faculty ID already exists","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(!mn.matches()){
            JOptionPane.showMessageDialog(null, "Invalid phone number","Error", JOptionPane.ERROR_MESSAGE);
        }
    else if(!me.matches()){
            JOptionPane.showMessageDialog(null, "Invalid email address","Error", JOptionPane.ERROR_MESSAGE);
        }
    else
    {
        String sql1="insert into Faculty(FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
            pst=conn.prepareStatement(sql1);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jTextField2.getText());
            pst.setString(3, jTextField3.getText());
            pst.setString(4, jTextField11.getText());
            pst.setString(5, (String) jComboBox1.getSelectedItem());
            pst.setString(6, (String) jComboBox2.getSelectedItem());
            pst.setString(7, jTextField16.getText());
            pst.setString(8, jTextField17.getText());
            pst.setString(9, jTextField4.getText());
            pst.setString(10, jTextField5.getText());
            pst.setString(11, (String) jComboBox13.getSelectedItem());
            if(cfaculty_image!=null){
                    pst.setBytes(12, cfaculty_image);
                    cfaculty_image=null;
                    }
                    else if(afaculty_image!=null){
                    pst.setBytes(12, afaculty_image);
                    afaculty_image=null;
                    }
            else if(jLabel12!=null){
                    pst.setBytes(12, imagedata1);
                }
                    pst.execute();
                    String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+fa+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    jLabel12.setIcon(null);
                    jTable1();
                    
                    JOptionPane.showMessageDialog(null, "New Faculty Registered","Message", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    mail();
    }
    }catch(MySQLIntegrityConstraintViolationException e){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
}catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
}
        }
    }
    
    public void Update(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel30.setIcon(null);
            recover2();
        }
         String c=  jComboBox10.getSelectedItem().toString();
        String d=  jComboBox6.getSelectedItem().toString();
        String s=  jComboBox12.getSelectedItem().toString();
        String n="NONE";
        String em="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(em,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField10.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField9.getText());
        String action="Updated faculty";
        Date da= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(da);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(da);
        if(jTextField18.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Faculty ID is required","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else if(jTextField7.getText().trim().isEmpty() || jTextField8.getText().trim().isEmpty() 
                || jTextField13.getText().trim().isEmpty() || jTextField14.getText().trim().isEmpty()
                || jTextField15.getText().trim().isEmpty() || jTextField9.getText().trim().isEmpty()
                || jTextField10.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
                         
                    }
        else if(c.equals(n) && d.equals(n) && s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Course, Department, and Status","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(c.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Course","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(d.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Department","Error", JOptionPane.ERROR_MESSAGE);
        }
            else if(s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Status","Error", JOptionPane.ERROR_MESSAGE);
        }
            else if(!mn.matches()){
            JOptionPane.showMessageDialog(null, "Invalid phone number","Error", JOptionPane.ERROR_MESSAGE);
        }
    else if(!me.matches()){
            JOptionPane.showMessageDialog(null, "Invalid email address","Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                String value1= jTextField18.getText();
                           
             
    String sql="select * from Faculty where FACULTY_ID='"+value1+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
        
                
                int p=JOptionPane.showConfirmDialog(null, "Altered data will be updated","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
                    String sql1="update Faculty set NAME=?,FATHER_NAME=?,QUALIFICATION=?,COURSE=?,DEPARTMENT=?,DESIGNATION=?,SUBJECTS=?,PHONE_NO=?,EMAIL_ID=?,STATUS=?,PHOTO=? where FACULTY_ID='"+value1+"' ";
                    pst=conn.prepareStatement(sql1);
                    
                pst.setString(1, jTextField7.getText());
                pst.setString(2, jTextField8.getText());
                pst.setString(3, jTextField13.getText());
                pst.setString(4, (String) jComboBox10.getSelectedItem());
                pst.setString(5, (String) jComboBox6.getSelectedItem());
                pst.setString(6, jTextField14.getText());
                pst.setString(7, jTextField15.getText());
                pst.setString(8, jTextField9.getText());
                pst.setString(9, jTextField10.getText());
                pst.setString(10, (String) jComboBox12.getSelectedItem());
                if(cfaculty_image2!=null){
                    pst.setBytes(11, cfaculty_image2);
                    imagedata1=null;
                    }
                else if(afaculty_image2!=null){
                    pst.setBytes(11, afaculty_image2);
                    imagedata1=null;
                    }
                else if(jLabel30!=null){
                    pst.setBytes(11, imagedata1);
                }
                
                
                    pst.execute();

                
                 jTable1();
            
            
            String sql2="insert into Faculty_Update(FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
                pst=conn.prepareStatement(sql2);
                pst.setString(1, jTextField18.getText());
                pst.setString(2, jTextField7.getText());
                pst.setString(3, jTextField8.getText());
                pst.setString(4, jTextField13.getText());
                pst.setString(5, (String) jComboBox10.getSelectedItem());
                pst.setString(6, (String) jComboBox6.getSelectedItem());
                pst.setString(7, jTextField14.getText());
                pst.setString(8, jTextField15.getText());
                pst.setString(9, jTextField9.getText());
                pst.setString(10, jTextField10.getText());
                pst.setString(11, (String) jComboBox12.getSelectedItem());
                if(cfaculty_image2!=null){
                    pst.setBytes(12, cfaculty_image2);
                    cfaculty_image2=null;
                    imagedata1=null;
                    }
                else if(afaculty_image2!=null){
                    pst.setBytes(12, afaculty_image2);
                    afaculty_image2=null;
                    imagedata1=null;
                    }
                else if(jLabel30!=null){
                    pst.setBytes(12, imagedata1);
                    
                }
                               
                pst.execute();
          String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+fa+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                JOptionPane.showMessageDialog(null, "Updated","Message", JOptionPane.INFORMATION_MESSAGE);
                upmail();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Faculty ID does not exists","Error", JOptionPane.ERROR_MESSAGE);
            }
            }catch(MySQLIntegrityConstraintViolationException e){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error in Update","Error", JOptionPane.ERROR_MESSAGE);
            }finally{
                try{
                    rs.close();
                    pst.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error in Update","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
           
        }
    }
    
     public void recover1(){
        if(image1!=null){
            jLabel12.setIcon(image1);
        }
        else if(icon1!=null){
           jLabel12.setIcon(icon1);
        }
        
    }
    
    public void recover2(){
        if(image2!=null){
            jLabel30.setIcon(image2);
        }
        else if(icon2!=null){
           jLabel30.setIcon(icon2);
        }
        else if(format1!=null){
            jLabel30.setIcon(format1);
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
            jLabel30.setIcon(null);
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
                cfaculty_image=bos.toByteArray();
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
                     jLabel30.setIcon(image2);
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
                cfaculty_image2=bos.toByteArray();
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
                     jLabel30.setIcon(new ImageIcon(image));
                     Thread.sleep(40);
               }
                       }catch(HeadlessException | WebcamLockException | InterruptedException e){
            JOptionPane.showMessageDialog(null, "Error in CamVideo2","Error", JOptionPane.ERROR_MESSAGE);
        }
             
         }
    }
    
       
    public void back(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            jLabel30.setIcon(null);
        
        }
        String b=fa;
        String b2=aut;
         setVisible(false);
         Home ob=new Home(b,b2);
         ob.setVisible(true);
    }
    
     public void clear1(){
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField11.setText("");
            jTextField16.setText("");
            jTextField17.setText("");
            jComboBox1.setSelectedItem("NONE");
            jComboBox2.setSelectedItem("NONE");
            jComboBox13.setSelectedItem("NONE");
            if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
            jLabel12.setIcon(null);
            icon1=null;
            image1=null;
            imagedata1=null;
            afaculty_image=null;
            cfaculty_image=null;
    }
     
     public void clear2(){
           jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jTextField15.setText("");
            jTextField18.setText("");
            jComboBox10.setSelectedItem("NONE");
            jComboBox6.setSelectedItem("NONE");
            jComboBox12.setSelectedItem("NONE");
            if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
            jLabel30.setIcon(null);
            icon2=null;
            image2=null;
            format1=null;
            imagedata1=null;
            afaculty_image2=null;
            cfaculty_image2=null;
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
        int returnVal = chooser.showDialog(Faculty.this, null);
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
                afaculty_image=bos.toByteArray();
               
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
            jLabel30.setIcon(null);
            recover2();
        }
       JFileChooser chooser=new JFileChooser();
        
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & PNG Images", "jpg", "png");
         
    chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(Faculty.this, null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
         try{
        File f=chooser.getSelectedFile();
        
        icon2=new ImageIcon(f.toString());
         jLabel30.setIcon(icon2);
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
                afaculty_image2=bos.toByteArray();
               
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Please select a valid file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(NullPointerException e){
                
            }
        
       }
    }
    
    public void search(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            recover1();
        
        }
        String i=jTextField1.getText();
        if("".equals(i)){
            JOptionPane.showMessageDialog(null, "Faculty ID required to search!");
        }
       else if(i!=null){
            String sql="select * from Faculty where FACULTY_ID='"+i+"' ";
                try{
                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("FACULTY_ID");
                    jTextField1.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField2.setText(add2);
                    String add3=rs.getString("FATHER_NAME");
                    jTextField3.setText(add3);
                    String add4=rs.getString("QUALIFICATION");
                    jTextField11.setText(add4);
                    String add5=rs.getString("COURSE");
                    jComboBox1.setSelectedItem(add5);
                    String add6=rs.getString("DEPARTMENT");
                    jComboBox2.setSelectedItem(add6);
                    String add7=rs.getString("DESIGNATION");
                    jTextField16.setText(add7);
                    String add8=rs.getString("SUBJECTS");
                    jTextField17.setText(add8);
                    String add9=rs.getString("PHONE_NO");
                    jTextField4.setText(add9);
                    String add10=rs.getString("EMAIL_ID");
                    jTextField5.setText(add10);
                    String add11=rs.getString("STATUS");
                    jComboBox13.setSelectedItem(add11);
                   
                    rs.close();
                    pst.close();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Faculty ID not found","Error", JOptionPane.ERROR_MESSAGE);
                }
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error in Search","Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        
        
    }
    
     public void search1(){
         
        String x=  jComboBox9.getSelectedItem().toString();
        String emp=jTextField6.getText();
        String f="ID";
        String n="NAME";
        String c="COURSE";
        String d="DEPARTMENT";
        String de="DESIGNATION";
        String su="SUBJECTS";
        String s="STATUS";
        String o="NONE";
        
        if(x.equals(o)){
             if("".equals(emp)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty ";
            try{
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
        else if(x.equals(f)){
         
                String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where FACULTY_ID LIKE '%"+emp+"%'";
            try{
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
               
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
                  
         else if(x.equals(c)){
               
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where COURSE LIKE '%"+emp+"%'";
            try{
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
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where DEPARTMENT LIKE '%"+emp+"%'";
            try{
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
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where DESIGNATION LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(su)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where SUBJECTS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(s)){
                
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty where STATUS LIKE '%"+emp+"%'";
            try{
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
     
     private void jTable1(){
         try{
             
    String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Table1","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        }
     
      public void tableClick(){
          if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel30.setIcon(null);
            recover2();
        }
        try{
        int row=jTable1.getSelectedRow();
                String Table_click=(jTable1.getModel().getValueAt(row, 0).toString());
        
                
                String sql="select * from Faculty where FACULTY_ID='"+Table_click+"' ";

                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("FACULTY_ID");
                    jTextField18.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField7.setText(add2);
                    String add3=rs.getString("FATHER_NAME");
                    jTextField8.setText(add3);
                    String add4=rs.getString("QUALIFICATION");
                    jTextField13.setText(add4);
                    String add5=rs.getString("COURSE");
                    jComboBox10.setSelectedItem(add5);
                    String add6=rs.getString("DEPARTMENT");
                    jComboBox6.setSelectedItem(add6);
                    String add7=rs.getString("DESIGNATION");
                    jTextField14.setText(add7);
                    String add8=rs.getString("SUBJECTS");
                    jTextField15.setText(add8);
                    String add9=rs.getString("PHONE_NO");
                    jTextField9.setText(add9);
                    String add10=rs.getString("EMAIL_ID");
                    jTextField10.setText(add10);
                    String add11=rs.getString("STATUS");
                    jComboBox12.setSelectedItem(add11);
                    imagedata1=rs.getBytes("PHOTO");
                    format1=new ImageIcon(imagedata1);
                    jLabel30.setIcon(format1);
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
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
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
        jLabel29 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(240, 240, 240));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jComboBox13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox13.setMaximumRowCount(3);
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "TEACHING", "NON-TEACHING" }));
        jComboBox13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox13KeyPressed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jComboBox2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox2.setMaximumRowCount(5);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "IT", "CSE", "CIVIL", "MECH", "ECE", "EEE" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

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
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "BTECH", "MTECH", "MBA" }));
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

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel25.setText("STATUS");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/subjects.png"))); // NOI18N
        jLabel8.setText("SUBJECTS");

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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sid.png"))); // NOI18N
        jLabel2.setText("FACULTY ID");

        jButton5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton5.setText("Back");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fname.png"))); // NOI18N
        jLabel4.setText("FATHER NAME");

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/qualification.png"))); // NOI18N
        jLabel24.setText("QUALIFICATION");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel3.setText("NAME");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("FACULTY INFO");

        jButton3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/register.png"))); // NOI18N
        jButton3.setText("Register");
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

        jTextField11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField11.setOpaque(false);
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/phone.png"))); // NOI18N
        jLabel9.setText("PHONE NO");

        jTextField4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField4.setOpaque(false);
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField4MouseEntered(evt);
            }
        });
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

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/course.png"))); // NOI18N
        jLabel5.setText("COURSE");

        jTextField16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField16.setOpaque(false);
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
        });

        jTextField17.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField17.setOpaque(false);
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField17KeyPressed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ssearch.png"))); // NOI18N
        jButton6.setText("Search");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Californian FB", 0, 12)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButton14.setText(" ");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.setIconTextGap(0);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25))
                .addGap(75, 75, 75)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(82, 82, 82))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(60, 60, 60))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel25, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton5, jButton6});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox13, jComboBox2, jTextField1, jTextField11, jTextField16, jTextField17, jTextField2, jTextField3, jTextField4, jTextField5});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton14});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel6)
                    .addComponent(jButton14))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton3)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(27, 27, 27))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox13, jComboBox2, jTextField1, jTextField11, jTextField16, jTextField17, jTextField2, jTextField3, jTextField4, jTextField5});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton14, jButton2, jButton3, jButton4, jButton5, jButton6});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel25, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ADD FACULTY", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane1.setViewportView(jTable1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel27.setText("STATUS");

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/subjects.png"))); // NOI18N
        jLabel19.setText("SUBJECTS");

        jTextField8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField8.setOpaque(false);
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
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/course.png"))); // NOI18N
        jLabel16.setText("COURSE");

        jTextField9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField9.setOpaque(false);
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField9MouseEntered(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField6.setOpaque(false);
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox6.setMaximumRowCount(5);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "IT", "CSE", "CIVIL", "MECH", "ECE", "EEE" }));
        jComboBox6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox6KeyPressed(evt);
            }
        });

        jComboBox12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox12.setMaximumRowCount(5);
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "TEACHING", "NON-TEACHING" }));
        jComboBox12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox12KeyPressed(evt);
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
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/branch.png"))); // NOI18N
        jLabel17.setText("DEPARTMENT");

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

        jTextField10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField10.setOpaque(false);
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton7KeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel13.setText("Search by ");

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/email.png"))); // NOI18N
        jLabel21.setText("EMAIL ID");

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
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "COURSE", "DEPARTMENT", "DESIGNATION", "SUBJECTS", "STATUS" }));
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
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/designation.png"))); // NOI18N
        jLabel18.setText("DESIGNATION");

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/phone.png"))); // NOI18N
        jLabel20.setText("PHONE NO");

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fname.png"))); // NOI18N
        jLabel15.setText("FATHER NAME");

        jComboBox10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox10.setMaximumRowCount(5);
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "BTECH", "MTECH", "MBA" }));
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
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sid.png"))); // NOI18N
        jLabel29.setText("FACULTY ID");

        jTextField18.setEditable(false);
        jTextField18.setFocusable(false);
        jTextField18.setOpaque(false);
        jTextField18.setRequestFocusEnabled(false);

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel30.setOpaque(true);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.setIconTextGap(0);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jButton15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton15KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jButton11)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox6, 0, 200, Short.MAX_VALUE)
                                            .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(175, 175, 175)
                                        .addComponent(jButton10)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField14))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField15))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel22))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel26});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel20, jLabel21, jLabel27});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox10, jComboBox6, jTextField13, jTextField7, jTextField8});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox12, jTextField10, jTextField14, jTextField15, jTextField9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField18, jTextField6});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton15, jButton7});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton8, jButton9});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton10)
                        .addComponent(jButton9)
                        .addComponent(jButton8)
                        .addComponent(jButton7))
                    .addComponent(jButton15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel26, jLabel27});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton15, jButton7, jButton8, jButton9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox12, jTextField10, jTextField14, jTextField15, jTextField9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox10, jComboBox6, jTextField13, jTextField7, jTextField8});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField18, jTextField6});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("UPDATE FACULTY", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

        setBounds(-8, -3, 1381, 776);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Capture1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Attach1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   Register();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
  back();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel12.setIcon(null);
            jLabel30.setIcon(null);
        
        }
jTable1();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:
       
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        search1();
        
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        back();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Attach2();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Capture2();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        tableClick();
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        // TODO add your handling code here:
        String num=jTextField9.getText();
        int len=num.length();
        char c=evt.getKeyChar();
           
          if(c>='0' && c<='9'){
                  
            if(len<10){
                
                jTextField9.setEditable(true);
                             
            }
              else{
                
                jTextField9.setEditable(false);
                            
            }
            }
            
             else if( c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
                jTextField9.setEditable(true);
                              
            }
             
             else{
                
                jTextField9.setEditable(false);
                 
            }
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField10.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField15.requestFocus();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
        String num=jTextField4.getText();
        int len=num.length();
        char c=evt.getKeyChar();
           
          if(c>='0' && c<='9'){
                  
            if(len<10){
                
                jTextField4.setEditable(true);
                             
            }
              else{
                
                jTextField4.setEditable(false);
                            
            }
            }
            
             else if( c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
                jTextField4.setEditable(true);
                              
            }
             
             else{
                
                jTextField4.setEditable(false);
                 
            }
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField5.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField17.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        close1();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        close2();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        // TODO add your handling code here:
        jTextField4.setEditable(true);
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jTextField9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseEntered
        // TODO add your handling code here:
        jTextField9.setEditable(true);
    }//GEN-LAST:event_jTextField9MouseEntered

    private void jComboBox9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField6.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jComboBox9KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField8.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField8.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField6.requestFocus();
        }
          
    }//GEN-LAST:event_jTextField7KeyPressed

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

    private void jComboBox10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox10KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox6.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox6.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField13.requestFocus();
        }
    }//GEN-LAST:event_jComboBox10KeyPressed

    private void jComboBox6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField14.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox10.requestFocus();
        }
    }//GEN-LAST:event_jComboBox6KeyPressed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField15.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField15.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox6.requestFocus();
        }
    }//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             jTextField9.setEditable(true);
         jTextField9.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField9.setEditable(true);
              jTextField9.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox12.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox12.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField9.setEditable(true);
         jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jTextField10KeyPressed

    private void jComboBox12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox12KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jButton9.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton9.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField10.requestFocus();
        }
    }//GEN-LAST:event_jComboBox12KeyPressed

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
         jComboBox12.requestFocus();
        }
    }//GEN-LAST:event_jButton9KeyPressed

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
         jComboBox6.requestFocus();
        }
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         back();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
         jButton15.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox6.requestFocus();
        }
    }//GEN-LAST:event_jButton11KeyPressed

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
         jComboBox12.requestFocus();
        }
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         Capture2();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
         jButton8.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton15.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox12.requestFocus();
        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton15KeyPressed
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
         jComboBox12.requestFocus();
        }
    }//GEN-LAST:event_jButton15KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField2.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField2.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox13.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

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

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField11.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField11.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
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
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox2.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox2.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField11.requestFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField16.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField16.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField17.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField17.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox2.requestFocus();
        }
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jTextField17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             jTextField4.setEditable(true);
         jTextField4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField4.setEditable(true);
             jTextField4.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField16.requestFocus();
        }
    }//GEN-LAST:event_jTextField17KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
         jComboBox13.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         
             jComboBox13.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField4.setEditable(true);
         jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jComboBox13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox13KeyPressed
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
    }//GEN-LAST:event_jComboBox13KeyPressed

    private void jButton7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7KeyReleased

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        Capture1();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton2.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton14.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jComboBox2.requestFocus();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        close1();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton2.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jButton1.requestFocus();
        }
    }//GEN-LAST:event_jButton14KeyPressed

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
        jTextField16.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        Register();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton6.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton2.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jTextField17.requestFocus();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        search();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton4.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton3.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        clear1();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton5.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton6.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        back();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton1.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jComboBox13.requestFocus();
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_ENTER){

            tableClick();
        }
    }//GEN-LAST:event_jTable1KeyReleased
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
            java.util.logging.Logger.getLogger(Faculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    //</editor-fold>
    
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Faculty(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
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
byte[] afaculty_image=null;
byte[] cfaculty_image=null;
byte[] afaculty_image2=null;
byte[] cfaculty_image2=null;
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";
String ed="EDITOR";
}
