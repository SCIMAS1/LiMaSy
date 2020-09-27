/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connect.myconnec;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public class Return extends javax.swing.JFrame {
   Connection conn;
ResultSet rs;
PreparedStatement pst;
String re;
String aut;
String i1;
    /**
     * Creates new form A_ReturnBook
     * @param abc
     * @param def
     */
    public Return(String abc, String def) {
        super("LiMaSy - Developed By IT (R16) - Return");
        initComponents();
        setIcon();
        conn=myconnec.ConnecrDb();
        re=abc;
        aut=def;
        jTable1();
        jTable2();
        returndate();
        returndate2();
        radio();
    }
    
    private void radio(){
         jRadioButton1.setActionCommand("PAID");
        jRadioButton2.setActionCommand("PENDING");
         jRadioButton3.setActionCommand("PAID");
        jRadioButton4.setActionCommand("PENDING");
        jTextField11.setText("0");
        jTextField12.setText("0");
    }

private void jTable1(){
         try{
             
    String sql="select * from Students_Issues";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Table1","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        }

private void jTable2(){
         try{
             
    String sql="select * from Faculty_Issues";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Table2","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        }

public void smail() {  
        int row = jTable1.getSelectedRow();
        String iid = jTable1.getModel().getValueAt(row, 0).toString();
        String bi = jTable1.getModel().getValueAt(row, 1).toString();
        String bn = jTable1.getModel().getValueAt(row, 2).toString();
        String si = jTable1.getModel().getValueAt(row, 3).toString();
        String sn = jTable1.getModel().getValueAt(row, 4).toString();
        String doi = jTable1.getModel().getValueAt(row, 5).toString();
        String dd = jTable1.getModel().getValueAt(row, 6).toString();
        String fin = jTextField12.getText();
        String dor= ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText();
        String st = buttonGroup8.getSelection().getActionCommand();
        try{
        String sql = "select * from Students where STUDENT_ID='"+si+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();   
            if(rs.next()){
                String em = rs.getString("EMAIL_ID");
            
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Book Returned";
          String msg= "SCET - LiMaSy | Book Return Details \n\nHi "+sn+", book returned from your ID: "+si+
                        ".\nFollowing are the return details: \nIssue ID: "+iid+"\nBook ID: "+bi+"\nBook Name: "+bn+"\nDate Of Issue: "+doi
                        +"\nThe book was due for return on "+dd+"\nDate Of Return: "+dor+"\nFine of Rs."+fin+" is "+st
                        +" towards late return of book. \n\nIf the return was not made by you please notify the library incharge. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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
        int row = jTable2.getSelectedRow();
        String iid = jTable2.getModel().getValueAt(row, 0).toString();
        String bi = jTable2.getModel().getValueAt(row, 1).toString();
        String bn = jTable2.getModel().getValueAt(row, 2).toString();
        String fi = jTable2.getModel().getValueAt(row, 3).toString();
        String fn = jTable2.getModel().getValueAt(row, 4).toString();
        String doi = jTable2.getModel().getValueAt(row, 5).toString();
        String dd = jTable2.getModel().getValueAt(row, 6).toString();
        String fin = jTextField11.getText();
        String dor= ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
        String st = buttonGroup1.getSelection().getActionCommand();
        try{
        String sql = "select * from Faculty where FACULTY_ID='"+fi+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery(); 
            if(rs.next()){
                String em = rs.getString("EMAIL_ID");
            
          String from = "collegelibrary.scet@gmail.com";
          String password= "Library.scet123$";
          String to = em;
          String sub= "Book Returned";
          String msg= "SCET - LiMaSy | Book Return Details \n\nHi "+fn+", book returned from your ID: "+fi+
                        ".\nFollowing are the return details: \nIssue ID: "+iid+"\nBook ID: "+bi+"\nBook Name: "+bn+"\nDate Of Issue: "+doi
                        +"\nThe book was due for return on "+dd+"\nDate Of Return: "+dor+"\nFine of Rs."+fin+" is "+st
                        +" towards late return of book. \n\nIf the return was not made by you please notify the library incharge. \nIf this email is not intended for you to receive please delete it. \n\nThis message was sent to "+em+"."
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

public void search1(){
         
        String x=  jComboBox1.getSelectedItem().toString();
        String emp=jTextField7.getText();
        String i="ISSUE ID";
        String b="BOOK ID";
        String n="BOOK NAME";
        String s="STUDENT ID";
        String sn="STUDENT NAME";
        String d="DATE OF ISSUE";
        String dd="DUE DATE";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select * from Students_Issues";
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
                JOptionPane.showMessageDialog(null, "Please select a valid option","Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        else if(x.equals(i)){
         
                
        
            String sql="select * from Students_Issues where ISSUE_ID LIKE '%"+emp+"%'";
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
                
        
            String sql="select * from Students_Issues where BOOK_ID LIKE '%"+emp+"%'";
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
                
        
            String sql="select * from Students_Issues where BNAME LIKE '%"+emp+"%'";
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
         
               
            String sql="select * from Students_Issues where STUDENT_ID LIKE '%"+emp+"%'";
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
        else if(x.equals(sn)){
                
        
            String sql="select * from Students_Issues where SNAME LIKE '%"+emp+"%'";
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
         
               
            String sql="select * from Students_Issues where DATE_OF_ISSUE LIKE '%"+emp+"%'";
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
        else if(x.equals(dd)){
         
               
            String sql="select * from Students_Issues where DUE_DATE LIKE '%"+emp+"%'";
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

public void search2(){
         
        String x=  jComboBox2.getSelectedItem().toString();
        String emp=jTextField8.getText();
        String i="ISSUE ID";
        String b="BOOK ID";
        String n="BOOK NAME";
        String f="FACULTY ID";
        String fn="FACULTY NAME";
        String d="DATE OF ISSUE";
        String dd="DUE DATE";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select * from Faculty_Issues";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                              
                   
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
            

            }
            else{
                JOptionPane.showMessageDialog(null, "Please select a valid option","Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        else if(x.equals(i)){
         
                
        
            String sql="select * from Faculty_Issues where ISSUE_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(b)){
                
        
            String sql="select * from Faculty_Issues where BOOK_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(n)){
                
        
            String sql="select * from Faculty_Issues where BNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(f)){
         
               
            String sql="select * from Faculty_Issues where FACULTY_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(fn)){
                
        
            String sql="select * from Faculty_Issues where FNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(d)){
         
               
            String sql="select * from Faculty_Issues where DATE_OF_ISSUE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
        else if(x.equals(dd)){
         
               
            String sql="select * from Faculty_Issues where DUE_DATE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
}
   
    public void Return1(){
        int row = jTable1.getSelectedRow();
        int n= jTable1.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please select a return date","Error", JOptionPane.ERROR_MESSAGE);
        }
        
      
        else{
            if(jTextField12.getText().trim().isEmpty()){
            jTextField12.setText("0");
        }
        String id = jTable1.getModel().getValueAt(row, 0).toString();
        String book = jTable1.getModel().getValueAt(row, 1).toString();
        String st="AVAILABLE";
        String action="Returned student book";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        
            int p=JOptionPane.showConfirmDialog(null, "Do you really want to return","Return",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
        try{
        String sql="Select * from Students_Issues where ISSUE_ID='"+id+"'";
         pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("ISSUE_ID");
                    String add2=rs.getString("BOOK_ID");
                    String add3=rs.getString("BNAME");
                    String add4=rs.getString("STUDENT_ID");
                    String add5=rs.getString("SNAME");
                    String add6=rs.getString("DATE_OF_ISSUE");
                    String add7=rs.getString("DUE_DATE");
                    rs.close();
                    pst.close();
                    String sql2="insert into Students_Returns(ISSUE_ID,BOOK_ID,BNAME,STUDENT_ID,SNAME,DATE_OF_ISSUE,DUE_DATE,DATE_OF_RETURN,FINE,STATUS) values(?,?,?,?,?,?,?,?,?,?)";
        
            pst=conn.prepareStatement(sql2);
            pst.setString(1, add1);
            pst.setString(2, add2);
            pst.setString(3, add3);
            pst.setString(4, add4);
            pst.setString(5, add5);
            pst.setString(6, add6);
            pst.setString(7, add7);
            pst.setString(8, ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText());
            pst.setString(9, jTextField12.getText());
            pst.setString(10, buttonGroup8.getSelection().getActionCommand());
            pst.execute();
            
           String sql3="Update Books set STATUS='"+st+"' where BOOK_ID='"+book+"'";
           
            pst=conn.prepareStatement(sql3);
            pst.execute();
            
        
            
        String sql4 = "DELETE FROM Students_Issues WHERE ISSUE_ID='"+id+"'";
       
            pst=conn.prepareStatement(sql4);
            pst.execute();
              String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+re+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
        
        jTable1();
        JOptionPane.showMessageDialog(null, "Book Returned","Message", JOptionPane.INFORMATION_MESSAGE);
        
        smail();
        
                }
                  
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Student Return","Error", JOptionPane.ERROR_MESSAGE);
        }      
                }
        }
        }
    }
    
    public void Return2(){
        int row = jTable2.getSelectedRow();
        int n= jTable2.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
        } 
        else if(((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please select a return date","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(jTextField11.getText().trim().isEmpty()){
            jTextField11.setText("0");
        }
        else{
        String id = jTable2.getModel().getValueAt(row, 0).toString();
        String book = jTable2.getModel().getValueAt(row, 1).toString();
        String st="AVAILABLE";
        String action="Returned faculty book";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
       
            int p=JOptionPane.showConfirmDialog(null, "Do you really want to Return","Return",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
        try{
        String sql="Select * from Faculty_Issues where ISSUE_ID='"+id+"'";
         pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("ISSUE_ID");
                    String add2=rs.getString("BOOK_ID");
                    String add3=rs.getString("BNAME");
                    String add4=rs.getString("FACULTY_ID");
                    String add5=rs.getString("FNAME");
                    String add6=rs.getString("DATE_OF_ISSUE");
                    String add7=rs.getString("DUE_DATE");
                    
                    rs.close();
                    pst.close();
                    
                    String sql2="insert into Faculty_Returns(ISSUE_ID,BOOK_ID,BNAME,FACULTY_ID,FNAME,DATE_OF_ISSUE,DUE_DATE,DATE_OF_RETURN,FINE,STATUS) values(?,?,?,?,?,?,?,?,?,?)";
       
            pst=conn.prepareStatement(sql2);
            pst.setString(1, add1);
            pst.setString(2, add2);
            pst.setString(3, add3);
            pst.setString(4, add4);
            pst.setString(5, add5);
            pst.setString(6, add6);
            pst.setString(7, add7);
            pst.setString(8, ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText());
            pst.setString(9, jTextField11.getText());
            pst.setString(10, buttonGroup1.getSelection().getActionCommand());
            pst.execute();
            
           String sql3="Update Books set STATUS='"+st+"' where BOOK_ID='"+book+"'";
           
            pst=conn.prepareStatement(sql3);
            pst.execute();
            
        
            
        String sql4 = "DELETE FROM Faculty_Issues WHERE ISSUE_ID='"+id+"'";
        
            pst=conn.prepareStatement(sql4);
            pst.execute();
       String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+re+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
        
        jTable2();
        JOptionPane.showMessageDialog(null, "Book Returned","Message", JOptionPane.INFORMATION_MESSAGE);
        
        fmail();
        
       
                }
                     
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Faculty Return","Error", JOptionPane.ERROR_MESSAGE);
        }  
          }
        }
        }
    }
    
    public void clear1(){
        jTextField7.setText("");
            jComboBox1.setSelectedItem("NONE");
            jTextField12.setText("0");
            jRadioButton3.setSelected(true);
            jRadioButton4.setSelected(false);
            returndate();
    }
    
    public void clear2(){
        jTextField8.setText("");
            jComboBox2.setSelectedItem("NONE");
            jTextField11.setText("0");
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            returndate2();
    }
    
    public void back(){
        String b=re;
        String b2=aut;
         setVisible(false);
        Home ob=new Home(b,b2);
        ob.setVisible(true);
    }
    
    private void returndate(){
       
       Date d = new Date();

       jDateChooser4.setDate(d);
      i1=((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
   }

    private void returndate2(){
       
       Date d = new Date();

       jDateChooser3.setDate(d);
   }

public void tableClick1(){
         if(((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(null, "Please select return date","Error", JOptionPane.ERROR_MESSAGE);
             jDateChooser4.getDateEditor().getUiComponent().requestFocus();
             jTable1();
         }
         else{
        try{
        int row=jTable1.getSelectedRow();
                String Table_click=(jTable1.getModel().getValueAt(row, 0).toString());
        
                
                String sql="select * from Students_Issues where ISSUE_ID='"+Table_click+"' ";

                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                   String dd=rs.getString("DUE_DATE");
                  String dor= ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText();
                   SimpleDateFormat myFormat = new SimpleDateFormat("MMM dd, yyyy");
                   String dateBeforeString = dd;
                   String dateAfterString = dor;

	
	       Date dateBefore = myFormat.parse(dateBeforeString);
	       Date dateAfter = myFormat.parse(dateAfterString);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      
                float daysBetween = 
                         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                int v= (int) daysBetween;
                if(v<=0){
                    jTextField12.setText("0");
                }
                else{
                String is= Integer.toString(v);
                
	       jTextField12.setText(is);
                }
                jDateChooser4.requestFocus();
                    rs.close();
                    pst.close();
                }
            }
        catch (ParseException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error in tableClick","Error", JOptionPane.ERROR_MESSAGE);
            }    catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Row Empty","Error", JOptionPane.ERROR_MESSAGE);
            }catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, e);
            }
         }
    }

public void tableClick2(){
         if(((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(null, "Please select return date","Error", JOptionPane.ERROR_MESSAGE);
         }
         else{
        try{
        int row=jTable2.getSelectedRow();
                String Table_click=(jTable2.getModel().getValueAt(row, 0).toString());
        
                
                String sql="select * from Faculty_Issues where ISSUE_ID='"+Table_click+"' ";

                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                   String dd=rs.getString("DUE_DATE");
                  String dor= ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
                   SimpleDateFormat myFormat = new SimpleDateFormat("MMM dd, yyyy");
                   String dateBeforeString = dd;
                   String dateAfterString = dor;

	
	       Date dateBefore = myFormat.parse(dateBeforeString);
	       Date dateAfter = myFormat.parse(dateAfterString);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      
                float daysBetween = 
                         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                int v= (int) daysBetween;
                if(v<=0){
                    jTextField11.setText("0");
                }
                else{
                String is= Integer.toString(v);
                
	       jTextField11.setText(is);
                }
                jDateChooser3.requestFocus();
                    rs.close();
                    pst.close();
                }
            }
        catch (ParseException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error in tableClick","Error", JOptionPane.ERROR_MESSAGE);
            }    catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Row Empty","Error", JOptionPane.ERROR_MESSAGE);
            }catch(ArrayIndexOutOfBoundsException e){
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

        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jTextField12 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jTextField11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1270, 545));

        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dor.png"))); // NOI18N
        jLabel12.setText("DATE OF RETURN");

        jButton1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton1.setText("Back");
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

        jButton2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/returnb.png"))); // NOI18N
        jButton2.setText("Return");
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
        jTable1.setNextFocusableComponent(jDateChooser4);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel7.setText("Search by");

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(5);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ISSUE ID", "BOOK ID", "BOOK NAME", "STUDENT ID", "STUDENT NAME", "DATE OF ISSUE", "DUE DATE" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField7.setOpaque(false);
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fine.png"))); // NOI18N
        jLabel5.setText("FINE");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setText("Rs.");

        jDateChooser4.setDateFormatString("ddddthMM, yyyy");
        jDateChooser4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jDateChooser4.setNextFocusableComponent(jTextField12);
        jDateChooser4.setOpaque(false);
        jDateChooser4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jDateChooser4MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jDateChooser4MouseMoved(evt);
            }
        });
        jDateChooser4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDateChooser4FocusLost(evt);
            }
        });
        jDateChooser4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDateChooser4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDateChooser4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDateChooser4MouseReleased(evt);
            }
        });
        jDateChooser4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser4KeyPressed(evt);
            }
        });

        jTextField12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField12.setOpaque(false);
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField12MouseEntered(evt);
            }
        });
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel9.setText("STATUS");

        buttonGroup8.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("PAID");
        jRadioButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jRadioButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioButton3KeyPressed(evt);
            }
        });

        buttonGroup8.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jRadioButton4.setText("PENDING");
        jRadioButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jRadioButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioButton4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jButton1)
                        .addGap(180, 180, 180)
                        .addComponent(jButton4)
                        .addGap(180, 180, 180)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(221, 221, 221))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jRadioButton3)
                        .addComponent(jRadioButton4))
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton4});

        jTabbedPane1.addTab("STUDENT", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dor.png"))); // NOI18N
        jLabel13.setText("DATE OF RETURN");

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

        jButton6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/returnb.png"))); // NOI18N
        jButton6.setText("Return");
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

        jButton7.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton7.setText("Clear");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel8.setText("Search by");

        jComboBox2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox2.setMaximumRowCount(5);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ISSUE ID", "BOOK ID", "BOOK NAME", "FACULTY ID", "FACULTY NAME", "DATE OF ISSUE", "DUE DATE" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jTextField8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField8.setOpaque(false);
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fine.png"))); // NOI18N
        jLabel2.setText("FINE");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Rs.");

        jDateChooser3.setDateFormatString("ddddthMM, yyyy");
        jDateChooser3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jDateChooser3.setOpaque(false);
        jDateChooser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDateChooser3MouseExited(evt);
            }
        });
        jDateChooser3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser3KeyPressed(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField11.setOpaque(false);
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField11MouseEntered(evt);
            }
        });
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel1.setText("STATUS");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("PAID");
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jRadioButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioButton1KeyPressed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jRadioButton2.setText("PENDING");
        jRadioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jRadioButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioButton2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jButton5)
                        .addGap(180, 180, 180)
                        .addComponent(jButton7)
                        .addGap(180, 180, 180)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)))
                .addGap(222, 222, 222))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton5, jButton6, jButton7});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton6)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton5, jButton6, jButton7});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, jLabel2});

        jTabbedPane1.addTab("FACULTY", jPanel2);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:V
       back();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            back();
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton4.requestFocus();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Return1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           Return1();
        }
       if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jButton4.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jRadioButton4.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       clear1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            clear1();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jButton1.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton2.requestFocus();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            back();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton7.requestFocus();
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Return2();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           Return2();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jButton7.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jRadioButton2.requestFocus();
        }
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           clear2();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jButton5.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton6.requestFocus();
        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        search1();
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        jTable1();
        jTable2();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        tableClick1();
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:
        tableClick2();
    }//GEN-LAST:event_jTable2MousePressed

    private void jDateChooser3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser3KeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jDateChooser3KeyPressed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jRadioButton1.requestFocus();
        }
char c=evt.getKeyChar();
           
          if(c>='0' && c<='9'){
                 
                            
                jTextField11.setEditable(true);
                             
            }
          else if( c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
                jTextField11.setEditable(true);
                              
            }
              else{
                
                jTextField11.setEditable(false);
                            
            }
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jDateChooser4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser4KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jDateChooser4KeyPressed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
        // TODO add your handling code here:
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jRadioButton3.requestFocus();
        }
        char c=evt.getKeyChar();
           
          if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ){
                 
                            
                jTextField12.setEditable(true);
                             
            }
          
              else{
                
                jTextField12.setEditable(false);
                            
            }
                     
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jDateChooser4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MousePressed
        // TODO add your handling code here:
      
   
    }//GEN-LAST:event_jDateChooser4MousePressed

    private void jTextField12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseEntered
        // TODO add your handling code here:
        jTextField12.setEditable(true);
    }//GEN-LAST:event_jTextField12MouseEntered

    private void jTextField11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseEntered
        // TODO add your handling code here:
          jTextField11.setEditable(true);
    }//GEN-LAST:event_jTextField11MouseEntered

    private void jDateChooser3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser3MouseExited
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jDateChooser3MouseExited

    private void jDateChooser4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MouseExited
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jDateChooser4MouseExited

    private void jDateChooser4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jDateChooser4MouseClicked

    private void jDateChooser4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MouseReleased
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jDateChooser4MouseReleased

    private void jDateChooser4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jDateChooser4MouseEntered

    private void jDateChooser4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MouseMoved
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jDateChooser4MouseMoved

    private void jDateChooser4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser4MouseDragged
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jDateChooser4MouseDragged

    private void jDateChooser4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser4FocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jDateChooser4FocusLost

    private void jRadioButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton3KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jRadioButton3.setSelected(true);
           jButton2.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
             jTextField12.setEditable(true);
          jTextField12.requestFocus();
          
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jRadioButton4.requestFocus();
        }
    }//GEN-LAST:event_jRadioButton3KeyPressed

    private void jRadioButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton4KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jRadioButton4.setSelected(true);
            jButton2.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jRadioButton3.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton2.requestFocus();
        }
    }//GEN-LAST:event_jRadioButton4KeyPressed

    private void jRadioButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton1KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jRadioButton1.setSelected(true);
           jButton6.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
             jTextField11.setEditable(true);
        jTextField11.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jRadioButton2.requestFocus();
        }
    }//GEN-LAST:event_jRadioButton1KeyPressed

    private void jRadioButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButton2KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           jRadioButton2.setSelected(true);
           jButton6.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jRadioButton1.requestFocus();
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton6.requestFocus();
        }
    }//GEN-LAST:event_jRadioButton2KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jTextField8.requestFocus();
        }
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField7KeyPressed
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
            java.util.logging.Logger.getLogger(Return.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       //</editor-fold>
       //</editor-fold>
       
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Return(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
