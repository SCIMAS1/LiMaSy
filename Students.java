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
import java.net.URL;
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
public class Students extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
Webcam webcam;
boolean started = false;
String st;
String aut;
    /**
     * Creates new form Students
     * @param abc
     * @param def
     */
    
    public Students(String abc, String def) {
        super("LiMaSy - Developed By IT (R16) - Students");
        initComponents();
        conn=myconnec.ConnecrDb();
        setIcon();
         st=abc;
         aut=def;
         jTable1();
          webcam = Webcam.getDefault();
          webcam.setViewSize(new Dimension(320,240));
        
    }
    
        public void mail(){  
          String id= jTextField1.getText();
        String na= jTextField2.getText();
        String fn= jTextField3.getText();
        String ba= jTextField11.getText();
        String ph= jTextField4.getText();
        String em= jTextField5.getText();
        String co= (String) jComboBox1.getSelectedItem();
        String br= (String) jComboBox2.getSelectedItem();
        String yr= (String) jComboBox3.getSelectedItem();
        String se= (String) jComboBox4.getSelectedItem();
        String su= (String) jComboBox13.getSelectedItem();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Student Registered";
          String msg= "SCET - LiMaSy | Student Details \n\nHi "+na+", your details as per provided data are:"
                        +"\nID: "+id+"\nName: " +na+"\nFather Name: "+fn+"\nBatch: "+ba+"\nCourse: "+co+"\nBranch: "+br+"\nYear: "+yr+"\nSemester: "+se+"\nPhone number: "+ph+"\nEmail ID: "+em+"\nStatus: "+su
                        +"\n\nThese details will be used for library work purpose. \nIf there are any changes to be made please notify the library incharge.\nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
         String id= jTextField14.getText();
        String na= jTextField7.getText();
        String fn= jTextField8.getText();
        String ba= jTextField13.getText();
        String ph= jTextField9.getText();
        String em= jTextField10.getText();
        String co= (String) jComboBox10.getSelectedItem();
        String br= (String) jComboBox6.getSelectedItem();
        String yr= (String) jComboBox7.getSelectedItem();
        String se= (String) jComboBox8.getSelectedItem();
        String su= (String) jComboBox12.getSelectedItem();
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Student Updated";
          String msg= "SCET - LiMaSy | Student Updated Details \n\nHi "+na+", your updated details are:"
                        +"\nID: "+id+"\nName: " +na+"\nFather Name: "+fn+"\nBatch: "+ba+"\nCourse: "+co+"\nBranch: "+br+"\nYear: "+yr+"\nSemester: "+se+"\nPhone number: "+ph+"\nEmail ID: "+em+"\nStatus: "+su
                        +"\n\nThese details will be used for library work purpose. \nIf there are any changes to be made please inform the library incharge. \nIf you didn't ask for these changes please notify the library incharge.\nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
        String b=  jComboBox2.getSelectedItem().toString();
        String y=  jComboBox3.getSelectedItem().toString();
        String s=  jComboBox4.getSelectedItem().toString();
        String t=  jComboBox13.getSelectedItem().toString();
        String n="NONE";
        String em="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(em,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField5.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField4.getText());
        String action="Registered student";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        if(jTextField1.getText().trim().isEmpty() || jTextField2.getText().trim().isEmpty() 
                || jTextField3.getText().trim().isEmpty() || jTextField11.getText().trim().isEmpty() 
                || jTextField4.getText().trim().isEmpty() 
                || jTextField5.getText().trim().isEmpty()){
                         JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                         
                    }
        else if(c.equals(n) && b.equals(n) && y.equals(n) && s.equals(n) && t.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Course, Branch, Year, Semester and Status", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(c.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Course", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(b.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Branch", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(y.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Year", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Semester", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(t.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Status", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else{
         String student=jTextField1.getText();
String sql="Select * from Students where STUDENT_ID='"+student+"'";
try{
    Statement stat=conn.createStatement();
    ResultSet rst=stat.executeQuery(sql);
    if(rst.next()){
        JOptionPane.showMessageDialog(null, "Student ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(!mn.matches()){
            JOptionPane.showMessageDialog(null, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    else if(!me.matches()){
            JOptionPane.showMessageDialog(null, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
        }
    else
    {
        String sql1="insert into Students(STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
            pst=conn.prepareStatement(sql1);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jTextField2.getText());
            pst.setString(3, jTextField3.getText());
            pst.setString(4, jTextField11.getText());
            pst.setString(5, (String) jComboBox1.getSelectedItem());
            pst.setString(6, (String) jComboBox2.getSelectedItem());
            pst.setString(7, (String) jComboBox3.getSelectedItem());
            pst.setString(8, (String) jComboBox4.getSelectedItem());
            pst.setString(9, jTextField4.getText());
            pst.setString(10, jTextField5.getText());
            pst.setString(11, (String) jComboBox13.getSelectedItem());
            if(cstudent_image!=null){
                    pst.setBytes(12, cstudent_image);
                    cstudent_image=null;
                    }
            else if(astudent_image!=null){
                    pst.setBytes(12, astudent_image);
                    astudent_image=null;
                    }
            else if(jLabel12!=null){
                    pst.setBytes(12, imagedata1);
                    imagedata1=null;
                }
                
                
                    pst.execute();
                 String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    jLabel12.setIcon(null);
                    jTable1();
                    JOptionPane.showMessageDialog(null, "New Student Registered", "Message", JOptionPane.INFORMATION_MESSAGE);
                                                           
                    mail();
    }
}catch(MySQLIntegrityConstraintViolationException e){
    JOptionPane.showMessageDialog(null, "Photo cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
}catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Register", "Error", JOptionPane.ERROR_MESSAGE);
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
        String x=  jComboBox11.getSelectedItem().toString();
         String sb= "Search by";
        String ub= "Bulk update";
        String action="Updated student";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        if(x.equalsIgnoreCase(sb)){
         String c= jComboBox10.getSelectedItem().toString();
        String b=  jComboBox6.getSelectedItem().toString();
        String y=  jComboBox7.getSelectedItem().toString();
        String s=  jComboBox8.getSelectedItem().toString();
        String t=  jComboBox12.getSelectedItem().toString();
        String n="NONE";
        String em="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ep = Pattern.compile(em,Pattern.CASE_INSENSITIVE);
        Matcher me=ep.matcher(jTextField10.getText());
         String num="^[6-9][0-9]{9}$";
        Pattern np = Pattern.compile(num);
        Matcher mn=np.matcher(jTextField9.getText());
        
        if(jTextField14.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Student ID required", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else if(jTextField7.getText().trim().isEmpty() || jTextField8.getText().trim().isEmpty() || jTextField13.getText().trim().isEmpty() 
                || jTextField9.getText().trim().isEmpty() || jTextField10.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                         
                    }
        else if(c.equals(n) && b.equals(n) && y.equals(n) && s.equals(n) && t.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Course, Branch, Year, Semester and Status", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(c.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Course", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(b.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Branch", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(y.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Year", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Semester", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(t.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Status", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(!mn.matches()){
            JOptionPane.showMessageDialog(null, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    else if(!me.matches()){
            JOptionPane.showMessageDialog(null, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else{
            try{
                String value1= jTextField14.getText();
               String sql="select * from Students where STUDENT_ID='"+value1+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){             
                
                int p=JOptionPane.showConfirmDialog(null, "Altered data will be updated","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
                    String sql1="update Students set NAME=?,FATHER_NAME=?,BATCH=?,COURSE=?,BRANCH=?,YEAR=?,SEMESTER=?,PHONE_NO=?,EMAIL_ID=?,STATUS=?,PHOTO=? where STUDENT_ID='"+value1+"' ";
                    pst=conn.prepareStatement(sql1);
                    
                pst.setString(1, jTextField7.getText());
                pst.setString(2, jTextField8.getText());
                pst.setString(3, jTextField13.getText());
                pst.setString(4, (String) jComboBox10.getSelectedItem());
                pst.setString(5, (String) jComboBox6.getSelectedItem());
                pst.setString(6, (String) jComboBox7.getSelectedItem());
                pst.setString(7, (String) jComboBox8.getSelectedItem());
                pst.setString(8, jTextField9.getText());
                pst.setString(9, jTextField10.getText());
                pst.setString(10, (String) jComboBox12.getSelectedItem());
                if(cstudent_image2!=null){
                    pst.setBytes(11, cstudent_image2);
                    imagedata1=null;
                    }
                else if(astudent_image2!=null){
                    pst.setBytes(11, astudent_image2);
                    imagedata1=null;
                }
                else if(jLabel30!=null){
                    pst.setBytes(11, imagedata1);
                }
                
                
                    pst.execute();

                
            
             jTable1();
            String sql2="insert into Students_Update(STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
                pst=conn.prepareStatement(sql2);
                pst.setString(1, jTextField14.getText());
                pst.setString(2, jTextField7.getText());
                pst.setString(3, jTextField8.getText());
                pst.setString(4, jTextField13.getText());
                pst.setString(5, (String) jComboBox10.getSelectedItem());
                pst.setString(6, (String) jComboBox6.getSelectedItem());
                pst.setString(7, (String) jComboBox7.getSelectedItem());
                pst.setString(8, (String) jComboBox8.getSelectedItem());
                pst.setString(9, jTextField9.getText());
                pst.setString(10, jTextField10.getText());
                pst.setString(11, (String) jComboBox12.getSelectedItem());
                if(cstudent_image2!=null){
                    pst.setBytes(12, cstudent_image2);
                    cstudent_image2=null;
                    imagedata1=null;
                    }
                else if(astudent_image2!=null){
                    pst.setBytes(12, astudent_image2);
                    astudent_image2=null;
                    imagedata1=null;
                    }
                else if(jLabel30!=null){
                    pst.setBytes(12, imagedata1);
                   
                }
                
                
                pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                JOptionPane.showMessageDialog(null, "Updated", "Message", JOptionPane.INFORMATION_MESSAGE);
                upmail();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Student ID does not exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }catch(MySQLIntegrityConstraintViolationException e){
    JOptionPane.showMessageDialog(null, "Please select an image file", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error in Update", "Error", JOptionPane.ERROR_MESSAGE);
            }finally{
                try{
                    rs.close();
                    pst.close();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
                     
        }
        }
        else if(x.equalsIgnoreCase(ub)){
            String co=  jComboBox10.getSelectedItem().toString();
            String br=  jComboBox6.getSelectedItem().toString();
            String yr=  jComboBox7.getSelectedItem().toString();
            String se=  jComboBox8.getSelectedItem().toString();
            String su=  jComboBox12.getSelectedItem().toString();
            String no="NONE";
                   
             int ro= jTable1.getSelectedRow();
          int n= jTable1.getRowCount();
          try{
               if(n==0){
             JOptionPane.showMessageDialog(null, "No records available", "Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
            if(!co.equalsIgnoreCase(no)){
               
        
               if(!br.equalsIgnoreCase(no) || !yr.equalsIgnoreCase(no) || !se.equalsIgnoreCase(no) || !su.equalsIgnoreCase(no)){
                   JOptionPane.showMessageDialog(null, "Please select one option at a time", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else{
               int p=JOptionPane.showConfirmDialog(null, "Are you sure you want to update","Update",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
       int[] row = jTable1.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
        String id = jTable1.getModel().getValueAt(row[i], 0).toString();
         String sql1="update Students set COURSE='"+co+"' where STUDENT_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
               
                    
                
       }
       String actions="Bulk update: Students course";
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
       jTable1();
       clear2();
            }
           }
           }
         
         
            else if(!br.equalsIgnoreCase(no)){
               
               if(!co.equalsIgnoreCase(no) || !yr.equalsIgnoreCase(no) || !se.equalsIgnoreCase(no) || !su.equalsIgnoreCase(no)){
                   JOptionPane.showMessageDialog(null, "Please select one option at a time", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else{
               int p=JOptionPane.showConfirmDialog(null, "Are you sure you want to update","Update",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
       int[] row = jTable1.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
        String id = jTable1.getModel().getValueAt(row[i], 0).toString();
         String sql1="update Students set BRANCH='"+br+"' where STUDENT_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
           
                    
                
       }
       String actions="Bulk update: Students branch";
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
       jTable1();
       clear2();
            }
           }
           }
         
            
            else if(!yr.equalsIgnoreCase(no)){
                
               if(!co.equalsIgnoreCase(no) || !br.equalsIgnoreCase(no) || !se.equalsIgnoreCase(no) || !su.equalsIgnoreCase(no)){
                   JOptionPane.showMessageDialog(null, "Please select one option at a time", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else{
               int p=JOptionPane.showConfirmDialog(null, "Are you sure you want to update","Update",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
       int[] row = jTable1.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
        String id = jTable1.getModel().getValueAt(row[i], 0).toString();
         String sql1="update Students set YEAR='"+yr+"' where STUDENT_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
          
                    
                
       } 
        String actions="Bulk update: Students year";
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
       jTable1();
       clear2();
            }
           }
           }
         
            
            else if(!se.equalsIgnoreCase(no)){
              
               if(!co.equalsIgnoreCase(no) || !br.equalsIgnoreCase(no) || !yr.equalsIgnoreCase(no) || !su.equalsIgnoreCase(no)){
                   JOptionPane.showMessageDialog(null, "Please select one option at a time", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else{
               int p=JOptionPane.showConfirmDialog(null, "Are you sure you want to update","Update",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
       int[] row = jTable1.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
        String id = jTable1.getModel().getValueAt(row[i], 0).toString();
         String sql1="update Students set SEMESTER='"+se+"' where STUDENT_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
           
                    
                
       } 
       String actions="Bulk update: Students semester";
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
       jTable1();
       clear2();
            }
           }
           }
         
              
            
            else if(!su.equalsIgnoreCase(no)){
               
               if(!co.equalsIgnoreCase(no) || !br.equalsIgnoreCase(no) || !yr.equalsIgnoreCase(no) || !se.equalsIgnoreCase(no)){
                   JOptionPane.showMessageDialog(null, "Please select one option at a time", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else{
               int p=JOptionPane.showConfirmDialog(null, "Are you sure you want to update","Update",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
       int[] row = jTable1.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
        String id = jTable1.getModel().getValueAt(row[i], 0).toString();
         String sql1="update Students set STATUS='"+su+"' where STUDENT_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
          
                 
                
       }
          String actions="Bulk update: Students status";
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+st+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
       jTable1();
       clear2();
            }
           }
           }
         
            
            else{
                JOptionPane.showMessageDialog(null, "Please select an option to update", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
                cstudent_image=bos.toByteArray();
            
                     webcam.close();
                    
        }catch(IOException | WebcamLockException e){
            JOptionPane.showMessageDialog(null, "Error in Capture1", "Error", JOptionPane.ERROR_MESSAGE);
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
                cstudent_image2=bos.toByteArray();
            
                     webcam.close();
                    
        }catch(IOException | WebcamLockException e){
            JOptionPane.showMessageDialog(null, "Error in Capture2", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Error in CamVideo1", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Error in CamVideo2", "Error", JOptionPane.ERROR_MESSAGE);
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
             String b=st;
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
            jComboBox1.setSelectedItem("NONE");
            jComboBox2.setSelectedItem("NONE");
            jComboBox3.setSelectedItem("NONE");
            jComboBox4.setSelectedItem("NONE");
            jComboBox13.setSelectedItem("NONE");
            if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
            jLabel12.setIcon(null);
            icon1=null;
            image1=null;
            imagedata1=null;
            astudent_image=null;
            cstudent_image=null;
    }
     
     public void clear2(){
           jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField13.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField14.setText("");
            jComboBox10.setSelectedItem("NONE");
            jComboBox6.setSelectedItem("NONE");
            jComboBox7.setSelectedItem("NONE");
            jComboBox8.setSelectedItem("NONE");
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
            astudent_image2=null;
            cstudent_image2=null;
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
        int returnVal = chooser.showDialog(Students.this, null);
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
                astudent_image=bos.toByteArray();
               
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Please select a valid file", "Error", JOptionPane.ERROR_MESSAGE);
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
        int returnVal = chooser.showDialog(Students.this, null);
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
                astudent_image2=bos.toByteArray();
               
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Please select a valid file", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Student ID required to search", "Error", JOptionPane.ERROR_MESSAGE);
        }
       else if(i!=null){
            String sql="select * from Students where STUDENT_ID='"+i+"' ";
                try{
                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("STUDENT_ID");
                    jTextField1.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField2.setText(add2);
                    String add3=rs.getString("FATHER_NAME");
                    jTextField3.setText(add3);
                    String add4=rs.getString("BATCH");
                    jTextField11.setText(add4);
                    String add5=rs.getString("COURSE");
                    jComboBox1.setSelectedItem(add5);
                    String add6=rs.getString("BRANCH");
                    jComboBox2.setSelectedItem(add6);
                    String add7=rs.getString("YEAR");
                    jComboBox3.setSelectedItem(add7);
                    String add8=rs.getString("SEMESTER");
                    jComboBox4.setSelectedItem(add8);
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
                    JOptionPane.showMessageDialog(null, "Student ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error in Search", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        
        
    }
    
     public void search1(){
        
        String x=  jComboBox9.getSelectedItem().toString();
        String emp=jTextField6.getText();
        String s="ID";
        String n="NAME";
        String h="BATCH";
        String c="COURSE";
        String b="BRANCH";
        String y="YEAR";
        String t="STATUS";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students";
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
            else{
                JOptionPane.showMessageDialog(null, "Please select a valid option", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        else if(x.equals(s)){
         
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where STUDENT_ID LIKE '%"+emp+"%'";
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
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where NAME LIKE '%"+emp+"%'";
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
        else if(x.equals(h)){
         
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where BATCH LIKE '%"+emp+"%'";
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
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where COURSE LIKE '%"+emp+"%'";
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
         else if(x.equals(b)){
            
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where BRANCH LIKE '%"+emp+"%'";
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
                 
         else if(x.equals(y)){
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where YEAR LIKE '%"+emp+"%'";
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
         else if(x.equals(t)){
         
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where STATUS LIKE '%"+emp+"%'";
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
             
    String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Table1", "Error", JOptionPane.ERROR_MESSAGE);
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
        
                
                String sql="select * from Students where STUDENT_ID='"+Table_click+"' ";

                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("STUDENT_ID");
                    jTextField14.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField7.setText(add2);
                    String add3=rs.getString("FATHER_NAME");
                    jTextField8.setText(add3);
                    String add4=rs.getString("BATCH");
                    jTextField13.setText(add4);
                    String add5=rs.getString("COURSE");
                    jComboBox10.setSelectedItem(add5);
                    String add6=rs.getString("BRANCH");
                    jComboBox6.setSelectedItem(add6);
                    String add7=rs.getString("YEAR");
                    jComboBox7.setSelectedItem(add7);
                    String add8=rs.getString("SEMESTER");
                    jComboBox8.setSelectedItem(add8);
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
                JOptionPane.showMessageDialog(null, "Error in tableClick", "Error", JOptionPane.ERROR_MESSAGE);
            }    catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Row Empty", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, e);
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
        jComboBox3 = new javax.swing.JComboBox<>();
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
        jComboBox4 = new javax.swing.JComboBox<>();
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
        jComboBox8 = new javax.swing.JComboBox<>();
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
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();

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

        jComboBox3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "1", "2", "3", "4" }));
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jComboBox13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox13.setMaximumRowCount(5);
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "REGULAR", "LATERAL", "DETAINED", "DROP OUT", "PASSED OUT" }));
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
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "IT", "CSE", "CIVIL", "MECH", "ECE", "EEE" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/year.png"))); // NOI18N
        jLabel7.setText("YEAR");

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
        jLabel6.setText("BRANCH");

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel25.setText("STATUS");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/semester.png"))); // NOI18N
        jLabel8.setText("SEMESTER");

        jComboBox4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "1", "2", "3", "4" }));
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
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
        jLabel2.setText("STUDENT ID");

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
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/batch.png"))); // NOI18N
        jLabel24.setText("BATCH");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel3.setText("NAME");

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("STUDENT INFO");

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
        jTextField4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTextField4MouseMoved(evt);
            }
        });
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextField4MouseReleased(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Californian FB", 0, 16)); // NOI18N
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
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(82, 82, 82))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(59, 59, 59))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel25, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox13, jComboBox2, jComboBox3, jComboBox4, jTextField1, jTextField11, jTextField2, jTextField3, jTextField4, jTextField5});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton14});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton5, jButton6});

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
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton3))
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

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox13, jComboBox2, jComboBox3, jComboBox4, jTextField1, jTextField11, jTextField2, jTextField3, jTextField4, jTextField5});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton14, jButton2, jButton3, jButton4, jButton5, jButton6});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel24, jLabel25, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ADD STUDENTS", jPanel2);

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
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/semester.png"))); // NOI18N
        jLabel19.setText("SEMESTER");

        jTextField8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField8.setOpaque(false);
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField8MouseEntered(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
        });

        jTextField13.setOpaque(false);
        jTextField13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField13MouseEntered(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });

        jComboBox8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox8.setMaximumRowCount(5);
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "1", "2", "3", "4" }));
        jComboBox8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox8KeyPressed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField7.setOpaque(false);
        jTextField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField7MouseEntered(evt);
            }
        });
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
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
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
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "REGULAR", "LATERAL", "DETAINED", "DROP OUT", "PASSED OUT" }));
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
        jLabel17.setText("BRANCH");

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/batch.png"))); // NOI18N
        jLabel26.setText("BATCH");

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
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField10MouseEntered(evt);
            }
        });
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

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/email.png"))); // NOI18N
        jLabel21.setText("EMAIL ID");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel14.setText("NAME");

        jComboBox7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox7.setMaximumRowCount(5);
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "1", "2", "3", "4" }));
        jComboBox7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox7KeyPressed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/attach.png"))); // NOI18N
        jButton8.setText("Attach");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
        });
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
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "BATCH", "COURSE", "BRANCH", "YEAR", "STATUS" }));
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
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/year.png"))); // NOI18N
        jLabel18.setText("YEAR");

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

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sid.png"))); // NOI18N
        jLabel29.setText("STUDENT ID");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField14.setFocusable(false);
        jTextField14.setOpaque(false);
        jTextField14.setRequestFocusEnabled(false);

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel30.setOpaque(true);

        jButton17.setFont(new java.awt.Font("Californian FB", 0, 16)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.setIconTextGap(0);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jButton17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton17KeyPressed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Californian FB", 0, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camera.png"))); // NOI18N
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setIconTextGap(0);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });
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

        jComboBox11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jComboBox11.setMaximumRowCount(2);
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search by", "Bulk update" }));
        jComboBox11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBox11MousePressed(evt);
            }
        });
        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(210, 210, 210)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel26});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel20, jLabel21, jLabel27});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox10, jComboBox6, jTextField13, jTextField7, jTextField8});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox12, jComboBox7, jComboBox8, jTextField10, jTextField9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField14, jTextField6});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton17, jButton7});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox11, jComboBox9});

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
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19))
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
                            .addComponent(jLabel27)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton10)
                        .addComponent(jButton9)
                        .addComponent(jButton8))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel26, jLabel27});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton17, jButton7, jButton8, jButton9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox12, jComboBox7, jComboBox8, jTextField10, jTextField9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox10, jComboBox6, jTextField13, jTextField7, jTextField8});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField14, jTextField6});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox11, jComboBox9, jLabel28});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("UPDATE STUDENTS", jPanel3);

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
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String x=  jComboBox11.getSelectedItem().toString();
        String y= "Search by";
        if(x.equalsIgnoreCase(y)){
             Attach2();
        }
        
       
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
String x=  jComboBox11.getSelectedItem().toString();
        String y= "Search by";
        if(x.equalsIgnoreCase(y)){
            tableClick();
        }
               
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton6ActionPerformed

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
         jComboBox4.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

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
            
             else if(c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
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
         jComboBox8.requestFocus();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseMoved
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField4MouseMoved

    private void jTextField4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField4MousePressed

    private void jTextField4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField4MouseReleased

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        // TODO add your handling code here:
        jTextField4.setEditable(true);
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Capture1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        close1();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        close2();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String x=  jComboBox11.getSelectedItem().toString();
        String y= "Search by";
        if(x.equalsIgnoreCase(y)){
            Capture2();
        }
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseEntered
        // TODO add your handling code here:
        
         String x=  jComboBox11.getSelectedItem().toString();
        String y= "Bulk update";
        if(x.equalsIgnoreCase(y)){
            jTextField9.setEditable(false);
        }
        else {
            jTextField9.setEditable(true);
        }
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
         jComboBox7.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox10.requestFocus();
        }
    }//GEN-LAST:event_jComboBox6KeyPressed

    private void jComboBox7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox7KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox8.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox8.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox6.requestFocus();
        }
    }//GEN-LAST:event_jComboBox7KeyPressed

    private void jComboBox8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox8KeyPressed
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
         jComboBox7.requestFocus();
        }
    }//GEN-LAST:event_jComboBox8KeyPressed

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

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         back();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
         jButton17.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox6.requestFocus();
        }
    }//GEN-LAST:event_jButton11KeyPressed

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
         jButton17.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox12.requestFocus();
        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton17KeyPressed
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
    }//GEN-LAST:event_jButton17KeyPressed

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
         jComboBox3.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox3.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox4.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox2.requestFocus();
        }
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
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
         jComboBox3.requestFocus();
        }
    }//GEN-LAST:event_jComboBox4KeyPressed

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
        jComboBox3.requestFocus();
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
        jComboBox4.requestFocus();
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

    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ActionPerformed

    private void jComboBox11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox11MouseClicked
        // TODO add your handling code here:
       clear2();
    }//GEN-LAST:event_jComboBox11MouseClicked

    private void jTextField7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7MouseEntered
        // TODO add your handling code here:
         String x=  jComboBox11.getSelectedItem().toString();
        String y= "Bulk update";
        if(x.equalsIgnoreCase(y)){
            jTextField7.setEditable(false);
        }
        else {
            jTextField7.setEditable(true);
        }
    }//GEN-LAST:event_jTextField7MouseEntered

    private void jTextField8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseEntered
        // TODO add your handling code here:
         String x=  jComboBox11.getSelectedItem().toString();
        String y= "Bulk update";
        if(x.equalsIgnoreCase(y)){
            jTextField8.setEditable(false);
        }
        else {
            jTextField8.setEditable(true);
        }
    }//GEN-LAST:event_jTextField8MouseEntered

    private void jTextField13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseEntered
        // TODO add your handling code here:
         String x=  jComboBox11.getSelectedItem().toString();
        String y= "Bulk update";
        if(x.equalsIgnoreCase(y)){
            jTextField13.setEditable(false);
        }
        else {
            jTextField13.setEditable(true);
        }
    }//GEN-LAST:event_jTextField13MouseEntered

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jTextField10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseEntered
        // TODO add your handling code here:
         String x=  jComboBox11.getSelectedItem().toString();
        String y= "Bulk update";
        if(x.equalsIgnoreCase(y)){
            jTextField10.setEditable(false);
        }
        else {
            jTextField10.setEditable(true);
        }
    }//GEN-LAST:event_jTextField10MouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton8MouseEntered

    private void jComboBox11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox11MousePressed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jComboBox11MousePressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_ENTER){

            tableClick();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed
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
            java.util.logging.Logger.getLogger(Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Students(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton17;
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
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
byte[] astudent_image=null;
byte[] cstudent_image=null;
byte[] astudent_image2=null;
byte[] cstudent_image2=null;
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";
String ed="EDITOR";
}
