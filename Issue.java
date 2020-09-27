/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import connect.myconnec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author abdul
 */
public class Issue extends javax.swing.JFrame {
 Connection conn;
ResultSet rs;
PreparedStatement pst;
Statement s;
byte[] imagedata2;
byte[] imagedata1;
String is;
String aut;
    /**
     * Creates new form A_IssueBook
     * @param abc
     * @param def
     */
    public Issue(String abc, String def) {
        super("LiMaSy - Developed By IT (R16) - Issue");
        initComponents();
        setIcon();
        conn=myconnec.ConnecrDb();
        is=abc;
        aut=def;
        Issueid();
        Issueid2();
        issuedate();
        issuedate2();
        duedate();
        duedate2();
        
    }
        
    private void Issueid() {
        try{
        String sql="select ISSUE_ID from Students_Issues order by ISSUE_ID DESC LIMIT 1 ";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String iss=rs.getString("ISSUE_ID");
        int l= iss.length();
        String txt=iss.substring(0,2);
        String num=iss.substring(2,l);
        int n=Integer.parseInt(num);
        n++;
        String stnum= Integer.toString(n);
        String ftxt=txt+"0"+stnum;
        jTextField11.setText(ftxt);
        }
        else{
            jTextField11.setText("IS01");
        }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error in Student Issue ID","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void Issueid2() {
         try{
        String sql="select ISSUE_ID from Faculty_Issues order by ISSUE_ID DESC LIMIT 1";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String iss=rs.getString("ISSUE_ID");
        int l= iss.length();
        String txt=iss.substring(0,2);
        String num=iss.substring(2,l);
        int n=Integer.parseInt(num);
        n++;
        String stnum= Integer.toString(n);
        String ftxt=txt+"0"+stnum;
        jTextField25.setText(ftxt);
        }
        else{
            jTextField25.setText("IF01");
        }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error in Faculty Issue ID","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public void smail() {  
          String iid= jTextField11.getText();
          String bi= jTextField1.getText();
          String bn= jTextField2.getText();
        String si= jTextField6.getText();
        String sn= jTextField7.getText();
        String da= ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        String dd= ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
        try{
        String sql = "select * from Students where STUDENT_ID='"+si+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery(); 
            if(rs.next()){
                String em = rs.getString("EMAIL_ID");
            
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Book Issued";
          String msg= "SCET - LiMaSy | Book Issue Details \n\nHi "+sn+", book issued on your ID: "+si+
                        ".\nFollowing are the issue details: \nIssue ID: "+iid+"\nBook ID: "+bi+"\nBook Name: "+bn+"\nDate Of Issue: "+da
                        +"\nThe issued book is due for return on "+dd+". \n\nIf not returned on time you may be charged with fine accordingly."
                        +"\nIf the issue was not made by you please notify the library incharge. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
              
           MimeMessage message = new MimeMessage(session);    
           message.setFrom(new InternetAddress("Shadan College Of Engineering And Technology <collegelibrary.scet@gmail.com>"));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
           message.setSubject(sub);    
           message.setText(msg);   
           Transport.send(message);   
            }
            else{
                JOptionPane.showMessageDialog(null, "Student ID not found","Error", JOptionPane.ERROR_MESSAGE);
            }
          } catch (MessagingException e) {throw new RuntimeException(e);} catch (SQLException ex) {    
         Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, ex);
     }    
             
    }  
     
     public void fmail() {  
          String iid= jTextField25.getText();
          String bi= jTextField26.getText();
          String bn= jTextField17.getText();
        String fi= jTextField27.getText();
        String fn= jTextField20.getText();
        String da= ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
        String dd= ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText();
        try{
        String sql = "select * from Faculty where FACULTY_ID='"+fi+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();     
            if(rs.next()){
                String em = rs.getString("EMAIL_ID");
            
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Book Issued";
          String msg= "SCET - LiMaSy | Book Issue Details \n\nHi "+fn+", book issued on your ID: "+fi+
                        ".\nFollowing are the issue details: \nIssue ID: "+iid+"\nBook ID: "+bi+"\nBook Name: "+bn+"\nDate Of Issue: "+da
                        +"\nThe issued book is due for return on "+dd+". \n\nIf not returned on time you may be charged with fine accordingly."
                        +"\nIf the issue was not made by you please notify the library incharge. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
              
           MimeMessage message = new MimeMessage(session);    
           message.setFrom(new InternetAddress("Shadan College Of Engineering And Technology <collegelibrary.scet@gmail.com>"));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
           message.setSubject(sub);    
           message.setText(msg);   
           Transport.send(message);   
            }
            else{
                JOptionPane.showMessageDialog(null, "Faculty ID not found","Error", JOptionPane.ERROR_MESSAGE);
            }
          } catch (MessagingException e) {throw new RuntimeException(e);} catch (SQLException ex) {    
         Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, ex);
     }    
             
    }  
   
public void Search(){
    String pen = "PENDING";
    String sql="select * from Students where STUDENT_ID=?";
    String sql2="SELECT STUDENT_ID, COUNT(*) AS COUNT \n" +"FROM Students_Issues\n" +"WHERE STUDENT_ID=?";
    String sql3="SELECT STUDENT_ID, COUNT(*) AS COUNT \n" +"FROM Students_Returns\n" +"WHERE STUDENT_ID=? && STATUS='"+pen+"'";
        try{
            pst=conn.prepareStatement(sql2);
            pst.setString(1, jTextField6.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String i=rs.getString("COUNT");
                pst=conn.prepareStatement(sql3);
                pst.setString(1, jTextField6.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String p=rs.getString("COUNT");
                pst=conn.prepareStatement(sql);
                pst.setString(1, jTextField6.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("NAME");
                jTextField7.setText(add1);
                String add2=rs.getString("COURSE");
                jTextField8.setText(add2);
                String add3=rs.getString("BRANCH");
                jTextField9.setText(add3);
                String add4=rs.getString("YEAR");
                jTextField10.setText(add4);
                String add5=rs.getString("STATUS");
                jTextField13.setText(add5+" "+"|"+" "+i+" "+"ISSUED"+" "+"|"+" "+p+" "+"PENDING");
                byte[] person_image=rs.getBytes("PHOTO");
               ImageIcon format2=new ImageIcon(person_image);
                 jLabel15.setIcon(format2);
                imagedata1=person_image;
                jButton4.requestFocus();
                
                rs.close();
                pst.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Student ID not found","Error", JOptionPane.ERROR_MESSAGE);
            }
            }
            }
            
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        }
}

public void Search2(){
   String val=jTextField1.getText();
     String sql="select * from Books where BOOK_ID='"+val+"' || ISBN='"+val+"'";
        try{
            pst=conn.prepareStatement(sql);
            
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("NAME");
                jTextField2.setText(add1);
                String add2=rs.getString("AUTHOR");
                jTextField3.setText(add2);
                String add3=rs.getString("PUBLISHER");
                jTextField4.setText(add3);
                String add4=rs.getString("EDITION");
                jTextField5.setText(add4);
                String add5=rs.getString("STATUS");
                jTextField12.setText(add5);
                 byte[] book_image=rs.getBytes("PHOTO");
                 ImageIcon format1=new ImageIcon(book_image);
               
                 jLabel17.setIcon(format1);
                 imagedata2=book_image;
                 jTextField6.requestFocus();
                 
                rs.close();
               
                pst.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Book ID not found","Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }catch(HeadlessException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error in Book Search","Error", JOptionPane.ERROR_MESSAGE);
    }finally{
            try{
                rs.close();
                pst.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error in Book Search","Error", JOptionPane.ERROR_MESSAGE);
        }
        }
}

public void Search3(){
String pen = "PENDING";
    String sql="select * from Faculty where FACULTY_ID=?";
    String sql2="SELECT FACULTY_ID, COUNT(*) AS COUNT \n" +"FROM FACULTY_Issues\n" +"WHERE FACULTY_ID=?";
    String sql3="SELECT FACULTY_ID, COUNT(*) AS COUNT \n" +"FROM FACULTY_Returns\n" +"WHERE FACULTY_ID=? && STATUS='"+pen+"'";
        try{
            pst=conn.prepareStatement(sql2);
            pst.setString(1, jTextField27.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String i=rs.getString("COUNT");
                pst=conn.prepareStatement(sql3);
                pst.setString(1, jTextField27.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String p=rs.getString("COUNT");
                pst=conn.prepareStatement(sql);
                pst.setString(1, jTextField27.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("NAME");
                jTextField20.setText(add1);
                String add2=rs.getString("COURSE");
                jTextField23.setText(add2);
                String add3=rs.getString("DEPARTMENT");
                jTextField21.setText(add3);
                String add4=rs.getString("DESIGNATION");
                jTextField22.setText(add4);
                  String add5=rs.getString("STATUS");
                jTextField24.setText(add5+" "+"|"+" "+i+" "+"ISSUED"+" "+"|"+" "+p+" "+"PENDING");
                byte[] person_image=rs.getBytes("PHOTO");
               ImageIcon format2=new ImageIcon(person_image);
                 jLabel39.setIcon(format2);
                imagedata1=person_image;
                jButton10.requestFocus();
                
                rs.close();
                pst.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Faculty ID not found","Error", JOptionPane.ERROR_MESSAGE);
            }
            }
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Faculty Search","Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error in Faculty Search","Error", JOptionPane.ERROR_MESSAGE);
        }
        }
}

public void Search4(){
     String sql="select * from Books where BOOK_ID=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, jTextField26.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("NAME");
                jTextField17.setText(add1);
                String add2=rs.getString("AUTHOR");
                jTextField15.setText(add2);
                String add3=rs.getString("PUBLISHER");
                jTextField16.setText(add3);
                String add4=rs.getString("EDITION");
                jTextField18.setText(add4);
                String add5=rs.getString("STATUS");
                jTextField19.setText(add5);
                 byte[] book_image=rs.getBytes("PHOTO");
                 ImageIcon format1=new ImageIcon(book_image);
               
                 jLabel31.setIcon(format1);
                 imagedata2=book_image;
                 jTextField27.requestFocus();
                 
                rs.close();
               
                pst.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Book ID not found","Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }catch(HeadlessException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error in Book Search","Error", JOptionPane.ERROR_MESSAGE);
    }finally{
            try{
                rs.close();
                pst.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error in Book Search","Error", JOptionPane.ERROR_MESSAGE);
        }
        }
}

public void back(){
    String b=is;
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
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jLabel15.setIcon(null);
        jLabel17.setIcon(null);
        Issueid();
       duedate();
        issuedate();
}

public void clear2(){
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
        jTextField26.setText("");
        jTextField27.setText("");
        jLabel31.setIcon(null);
        jLabel39.setIcon(null);
        Issueid2();
      duedate2();
        issuedate2();
}

private void issuedate(){
       
       Date d = new Date();

       jDateChooser1.setDate(d);
   }

private void issuedate2(){
       
       Date d = new Date();

       jDateChooser2.setDate(d);
   }

private void duedate(){
       
       Date current = new Date();
       Calendar c = Calendar.getInstance();
       c.setTime(current);
       c.add(Calendar.DATE,15);
       Date due=c.getTime();
       jDateChooser3.setDate(due);
   }

private void duedate2(){
      Date current = new Date();
       Calendar c = Calendar.getInstance();
       c.setTime(current);
       c.add(Calendar.DATE,15);
       Date due=c.getTime();
       jDateChooser4.setDate(due);
   }



public void Issue1(){
    String st1=jTextField12.getText();
    String st2="UNAVAILABLE";
    if(jTextField1.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please search for a book","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(jTextField6.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please search for a student","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(jTextField11.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Issue ID required","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please select an issue date","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please select the due date","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(st1.equals(st2)){
    JOptionPane.showMessageDialog(null, "Book unavailable","Error", JOptionPane.ERROR_MESSAGE);
    }
    else{
        String book=jTextField1.getText();
        String nbook=jTextField2.getText();
        
        String student=jTextField6.getText();
        String st="ISSUED";
        String action="Issued book to faculty";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        String sql="select * from Students_Issues where BOOK_ID='"+book+"' ";
        try{
            Statement stat= conn.createStatement();
            rs= stat.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Book under issue","Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String sql2="select * from Faculty_Issues where BOOK_ID='"+book+"' ";
        
            Statement stat2= conn.createStatement();
            rs= stat2.executeQuery(sql2);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Book under issue","Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                 String sql3="select * from Students_Issues where BNAME='"+nbook+"' and STUDENT_ID='"+student+"' ";
        
            Statement stats= conn.createStatement();
            rs= stats.executeQuery(sql3);
            if(rs.next()){
                int p=JOptionPane.showConfirmDialog(null, "Same book already issued on this Student ID | Do you want to continue","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
             
                String sql4="insert into Students_Issues(ISSUE_ID,BOOK_ID,BNAME,STUDENT_ID,SNAME,DATE_OF_ISSUE,DUE_DATE) values(?,?,?,?,?,?,?)";
               
                try{
                    pst=conn.prepareStatement(sql4);
                    pst.setString(1,jTextField11.getText());
                    pst.setString(2, jTextField1.getText());
                    pst.setString(3, jTextField2.getText());
                    
                    pst.setString(4, jTextField6.getText());
                    pst.setString(5, jTextField7.getText());
                    pst.setString(6, ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
                    pst.setString(7, ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText());
                    pst.execute();
                    
                    String sql5="Update Books set STATUS='"+st+"' where BOOK_ID='"+book+"'";
                    pst=conn.prepareStatement(sql5);
                    pst.execute();
                    
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+is+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Book Issued","Message", JOptionPane.INFORMATION_MESSAGE);
                   
                Issueid();
                
                smail();
                
                }catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null, e);
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Issue ID already in use","Error", JOptionPane.ERROR_MESSAGE);
                }
                }
                
            } else{
                 try{
                  String sql7="insert into Students_Issues(ISSUE_ID,BOOK_ID,BNAME,STUDENT_ID,SNAME,DATE_OF_ISSUE,DUE_DATE) values(?,?,?,?,?,?,?)";
               

                    pst=conn.prepareStatement(sql7);
                    pst.setString(1,jTextField11.getText());
                    pst.setString(2, jTextField1.getText());
                    pst.setString(3, jTextField2.getText());
                    
                    pst.setString(4, jTextField6.getText());
                    pst.setString(5, jTextField7.getText());
                    pst.setString(6, ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
                    pst.setString(7, ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText());
                    pst.execute();
                    
                    String sql8="Update Books set STATUS='"+st+"' where BOOK_ID='"+book+"'";
                    pst=conn.prepareStatement(sql8);
                    pst.execute();
                    
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+is+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Book Issued","Message", JOptionPane.INFORMATION_MESSAGE);
                    
                    Issueid();
                    
                   
                    smail();
                    
                }catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null, e);
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Issue ID already in use","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            }
            }
                    
            }catch(HeadlessException | SQLException e){
                    JOptionPane.showMessageDialog(null, "Error in Student Issue","Error", JOptionPane.ERROR_MESSAGE);
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

public void Issue2(){
     String st1=jTextField19.getText();
    String st2="UNAVAILABLE";
     if(jTextField26.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please search for a book","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(jTextField27.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please search for a faculty","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(jTextField25.getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Issue ID required","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please select an issue date","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
    JOptionPane.showMessageDialog(null, "Please select the due date","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(st1.equals(st2)){
    JOptionPane.showMessageDialog(null, "Book unavailable","Error", JOptionPane.ERROR_MESSAGE);
    }
    else{
        String book=jTextField26.getText();
        String nbook=jTextField17.getText();
        
        String faculty=jTextField27.getText();
        String st="ISSUED";
        String action="Issued book to faculty";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);

        String sql="select * from Faculty_Issues where BOOK_ID='"+book+"' ";
        try{
            Statement stat= conn.createStatement();
            rs= stat.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Book under issue","Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String sql2="select * from Students_Issues where BOOK_ID='"+book+"' ";
       
            Statement stat2= conn.createStatement();
            rs= stat2.executeQuery(sql2);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Book under issue","Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else{
        String sql3="select * from Faculty_Issues where BNAME='"+nbook+"' and FACULTY_ID='"+faculty+"' ";
        
            Statement stats= conn.createStatement();
            rs= stats.executeQuery(sql3);
            if(rs.next()){
                
            int p=JOptionPane.showConfirmDialog(null, "Same book already issued on this Faculty ID | Do you want to continue","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
                    
                String sql4="insert into Faculty_Issues(ISSUE_ID,BOOK_ID,BNAME,FACULTY_ID,FNAME,DATE_OF_ISSUE,DUE_DATE) values(?,?,?,?,?,?,?)";
                try{

                    pst=conn.prepareStatement(sql4);
                    pst.setString(1,jTextField25.getText());
                    pst.setString(2, jTextField26.getText());
                    pst.setString(3, jTextField17.getText());
                    
                    pst.setString(4, jTextField27.getText());
                    pst.setString(5, jTextField20.getText());
                    pst.setString(6, ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText());
                    pst.setString(7, ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText());
                    pst.execute();
                    
                    String sql5="Update Books set STATUS='"+st+"' where BOOK_ID='"+book+"'";
                    pst=conn.prepareStatement(sql5);
                    pst.execute();
                    
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+is+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Book Issued","Message", JOptionPane.INFORMATION_MESSAGE);
                    
                    Issueid2();
                    
                    fmail();
                    
                }catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null, e);
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Issue ID already in use","Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            }
            else{
                
                String sql7="insert into Faculty_Issues(ISSUE_ID,BOOK_ID,BNAME,FACULTY_ID,FNAME,DATE_OF_ISSUE,DUE_DATE) values(?,?,?,?,?,?,?)";
                try{

                    pst=conn.prepareStatement(sql7);
                    pst.setString(1,jTextField25.getText());
                    pst.setString(2, jTextField26.getText());
                    pst.setString(3, jTextField17.getText());
                    
                    pst.setString(4, jTextField27.getText());
                    pst.setString(5, jTextField20.getText());
                    pst.setString(6, ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText());
                    pst.setString(7, ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText());
                    pst.execute();
                    
                    String sql8="Update Books set STATUS='"+st+"' where BOOK_ID='"+book+"'";
                    pst=conn.prepareStatement(sql8);
                    pst.execute();
                    
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+is+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Book Issued","Message", JOptionPane.INFORMATION_MESSAGE);
                    Issueid2();
                    
                    fmail();
                    
                }catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null, e);
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Issue ID already in use","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            }
    }
        
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Faculty Issue","Error", JOptionPane.ERROR_MESSAGE);
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextField20 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTabbedPane1.setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel19.setText("BOOK ISSUE DETAILS");

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/doi.png"))); // NOI18N
        jLabel13.setText("DATE OF ISSUE");

        jDateChooser1.setDateFormatString("ddddthMM, yyyy");
        jDateChooser1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jDateChooser1.setOpaque(false);
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton3.setText("Back");
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

        jButton4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/issueb.png"))); // NOI18N
        jButton4.setText("Issue");
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField3.setFocusable(false);
        jTextField3.setOpaque(false);
        jTextField3.setRequestFocusEnabled(false);

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField4.setFocusable(false);
        jTextField4.setOpaque(false);
        jTextField4.setRequestFocusEnabled(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("BOOK DETAILS");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField2.setFocusable(false);
        jTextField2.setOpaque(false);
        jTextField2.setRequestFocusEnabled(false);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/publisher.png"))); // NOI18N
        jLabel5.setText("PUBLISHER");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edition.png"))); // NOI18N
        jLabel4.setText("EDITION");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bname.png"))); // NOI18N
        jLabel2.setText("NAME");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/author.png"))); // NOI18N
        jLabel3.setText("AUTHOR");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField5.setFocusable(false);
        jTextField5.setOpaque(false);
        jTextField5.setRequestFocusEnabled(false);

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel16.setText("PHOTO");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel17.setOpaque(true);

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel20.setText("STATUS");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField12.setFocusable(false);
        jTextField12.setOpaque(false);
        jTextField12.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(54, 54, 54))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField12, jTextField2, jTextField3, jTextField4, jTextField5});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel20, jLabel3, jLabel4, jLabel5});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField2, jTextField3, jTextField4, jTextField5});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel20, jLabel3, jLabel4, jLabel5});

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField7.setFocusable(false);
        jTextField7.setOpaque(false);
        jTextField7.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel7.setText("NAME");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel11.setText("STATUS");

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("STUDENT DETAILS");

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField9.setFocusable(false);
        jTextField9.setOpaque(false);
        jTextField9.setRequestFocusEnabled(false);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/year.png"))); // NOI18N
        jLabel10.setText("YEAR");

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField10.setFocusable(false);
        jTextField10.setOpaque(false);
        jTextField10.setRequestFocusEnabled(false);

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/branch.png"))); // NOI18N
        jLabel9.setText("BRANCH");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField8.setFocusable(false);
        jTextField8.setOpaque(false);
        jTextField8.setRequestFocusEnabled(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel14.setText("PHOTO");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel15.setOpaque(true);

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/course.png"))); // NOI18N
        jLabel21.setText("COURSE");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField13.setFocusable(false);
        jTextField13.setOpaque(false);
        jTextField13.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(54, 54, 54)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField10, jTextField13, jTextField7, jTextField8, jTextField9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel21, jLabel7, jLabel9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField10, jTextField13, jTextField7, jTextField8, jTextField9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel21, jLabel7, jLabel9});

        jButton5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton5.setText("Clear");
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

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iid.png"))); // NOI18N
        jLabel18.setText("ISSUE ID");

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField11.setFocusable(false);
        jTextField11.setOpaque(false);
        jTextField11.setRequestFocusEnabled(false);
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

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bid.png"))); // NOI18N
        jLabel1.setText("BOOK ID");

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField1.setOpaque(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sid.png"))); // NOI18N
        jLabel6.setText("STUDENT ID");

        jButton1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bsearch.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField6.setOpaque(false);
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ssearch.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
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

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dd.png"))); // NOI18N
        jLabel22.setText("DUE DATE");

        jDateChooser3.setDateFormatString("ddddthMM, yyyy");
        jDateChooser3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jDateChooser3.setOpaque(false);
        jDateChooser3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(556, 556, 556)
                        .addComponent(jLabel19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(526, 526, 526)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel18, jLabel22});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4, jButton5});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDateChooser1, jDateChooser3, jTextField11});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel19)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton3))
                .addGap(30, 30, 30))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, jLabel18, jLabel22});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jButton4, jButton5});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDateChooser1, jDateChooser3, jTextField11});

        jTabbedPane1.addTab("STUDENT", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel23.setText("BOOK ISSUE DETAILS");

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/doi.png"))); // NOI18N
        jLabel24.setText("DATE OF ISSUE");

        jDateChooser2.setDateFormatString("ddddthMM, yyyy");
        jDateChooser2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jDateChooser2.setOpaque(false);
        jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser2KeyPressed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton9.setText("Back");
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

        jButton10.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/issueb.png"))); // NOI18N
        jButton10.setText("Issue");
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField15.setFocusable(false);
        jTextField15.setOpaque(false);
        jTextField15.setRequestFocusEnabled(false);

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField16.setFocusable(false);
        jTextField16.setOpaque(false);
        jTextField16.setRequestFocusEnabled(false);
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("BOOK DETAILS");

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField17.setFocusable(false);
        jTextField17.setOpaque(false);
        jTextField17.setRequestFocusEnabled(false);

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/publisher.png"))); // NOI18N
        jLabel26.setText("PUBLISHER");

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edition.png"))); // NOI18N
        jLabel27.setText("EDITION");

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bname.png"))); // NOI18N
        jLabel28.setText("NAME");

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/author.png"))); // NOI18N
        jLabel29.setText("AUTHOR");

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField18.setFocusable(false);
        jTextField18.setOpaque(false);
        jTextField18.setRequestFocusEnabled(false);

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel30.setText("PHOTO");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel31.setOpaque(true);

        jLabel32.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel32.setText("STATUS");

        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField19.setFocusable(false);
        jTextField19.setOpaque(false);
        jTextField19.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel30))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel25)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField15, jTextField16, jTextField17, jTextField18, jTextField19});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel26, jLabel27, jLabel28, jLabel29, jLabel32});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField15, jTextField16, jTextField17, jTextField18, jTextField19});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel26, jLabel27, jLabel28, jLabel29, jLabel32});

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField20.setFocusable(false);
        jTextField20.setOpaque(false);
        jTextField20.setRequestFocusEnabled(false);

        jLabel33.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel33.setText("NAME");

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel34.setText("STATUS");

        jLabel35.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("FACULTY DETAILS");

        jTextField21.setEditable(false);
        jTextField21.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField21.setFocusable(false);
        jTextField21.setOpaque(false);
        jTextField21.setRequestFocusEnabled(false);
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/designation.png"))); // NOI18N
        jLabel36.setText("DESIGNATION");

        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField22.setFocusable(false);
        jTextField22.setOpaque(false);
        jTextField22.setRequestFocusEnabled(false);

        jLabel37.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/branch.png"))); // NOI18N
        jLabel37.setText("DEPARTMENT");

        jTextField23.setEditable(false);
        jTextField23.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField23.setFocusable(false);
        jTextField23.setOpaque(false);
        jTextField23.setRequestFocusEnabled(false);
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel38.setText("PHOTO");

        jLabel39.setBackground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel39.setOpaque(true);

        jLabel40.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/course.png"))); // NOI18N
        jLabel40.setText("COURSE");

        jTextField24.setEditable(false);
        jTextField24.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField24.setFocusable(false);
        jTextField24.setOpaque(false);
        jTextField24.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel38)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField20, jTextField21, jTextField22, jTextField23, jTextField24});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel33, jLabel34, jLabel36, jLabel37, jLabel40});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField20, jTextField21, jTextField22, jTextField23, jTextField24});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel33, jLabel34, jLabel36, jLabel37, jLabel40});

        jButton11.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton11.setText("Clear");
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

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iid.png"))); // NOI18N
        jLabel41.setText("ISSUE ID");

        jTextField25.setEditable(false);
        jTextField25.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField25.setFocusable(false);
        jTextField25.setOpaque(false);
        jTextField25.setRequestFocusEnabled(false);
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });
        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField25KeyPressed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bid.png"))); // NOI18N
        jLabel42.setText("BOOK ID");

        jTextField26.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField26.setOpaque(false);
        jTextField26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField26KeyPressed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sid.png"))); // NOI18N
        jLabel43.setText("FACULTY ID");

        jButton12.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bsearch.png"))); // NOI18N
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
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

        jTextField27.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField27.setOpaque(false);
        jTextField27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField27KeyPressed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ssearch.png"))); // NOI18N
        jButton18.setBorder(null);
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jButton18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton18KeyPressed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dd.png"))); // NOI18N
        jLabel44.setText("DUE DATE");

        jDateChooser4.setDateFormatString("ddddthMM, yyyy");
        jDateChooser4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jDateChooser4.setOpaque(false);
        jDateChooser4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(556, 556, 556)
                                .addComponent(jLabel23))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(526, 526, 526)
                                .addComponent(jButton9)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(jLabel42)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(330, 330, 330)
                                .addComponent(jLabel43)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(503, 503, 503)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel44)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel41)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton9});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel24, jLabel41, jLabel44});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDateChooser2, jDateChooser4, jTextField25});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel23)
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton9))
                .addGap(30, 30, 30))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton9});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel24, jLabel41, jLabel44});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDateChooser2, jDateChooser4, jTextField25});

        jTabbedPane1.addTab("FACULTY", jPanel4);

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

        setBounds(-8, 0, 1381, 773);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search();
            
        }
       
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search();

        }
        
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search2();
            
        }
        
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Search2();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search2();

        }
       
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           clear1();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jButton3.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jButton4.requestFocus();
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       clear1();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           Issue1();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jButton5.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField6.requestFocus();
        }
        
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
           Issue1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           back();
        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jTextField1.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jButton5.requestFocus();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       back();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void jDateChooser2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
              back();

        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jTextField26.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jButton11.requestFocus();
        }
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        Issue2();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
              Issue2();

        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jButton11.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField27.requestFocus();
        }
    }//GEN-LAST:event_jButton10KeyPressed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             clear2();

        }
        if(evt.getKeyCode()==KeyEvent.VK_LEFT){
           jButton9.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jButton10.requestFocus();
        }
    }//GEN-LAST:event_jButton11KeyPressed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jTextField25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField25KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25KeyPressed

    private void jTextField26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField26KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search4();

        }
    }//GEN-LAST:event_jTextField26KeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        Search4();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search4();

        }
    }//GEN-LAST:event_jButton12KeyPressed

    private void jTextField27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField27KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search3();

        }
    }//GEN-LAST:event_jTextField27KeyPressed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        Search3();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton18KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Search3();

        }
    }//GEN-LAST:event_jButton18KeyPressed

    private void jDateChooser3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser3KeyPressed

    private void jDateChooser4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser4KeyPressed
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
            java.util.logging.Logger.getLogger(Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     //</editor-fold>
     //</editor-fold>
     
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Issue(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
