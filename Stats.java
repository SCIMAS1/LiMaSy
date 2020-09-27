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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public final class Stats extends javax.swing.JFrame {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    String ta;
    String aut;

    /**
     * Creates new form A_Statistics
     * @param abc
     * @param def
     */
    
    public Stats(String abc, String def) {
        super("LiMaSy - Developed By IT (R16) - Stats");
        initComponents();
        conn = myconnec.ConnecrDb();
        setIcon();
        ta = abc;
        aut= def;
        jTable1();
        rc1();
        jTable2();
        rc2();
        jTable3();
        rc3();
        jTable4();
        rc4();
        jTable14();
        rc14();
        jTable15();
        rc15();
        jTable16();
        rc16();
        visible();
     
    }

    public void visible() {
       if(aut.equalsIgnoreCase(sa)){
        jTable5();
        rc5();
        jTable6();
        rc6();
        jTable7();
        rc7();
        jTable8();
        rc8();
        jTable9();
        rc9();
        jTable10();
        rc10();
        jTable11();
        rc11();
        }
        else if(aut.equalsIgnoreCase(ad)){
        jTable5();
        rc5();
        jTable6();
        rc6();
        jTable7();
        rc7();
        jTable8();
        rc8();
        jTable9();
        rc9();
        jTable10();
        rc10();
        jTable11();
        rc11();
        jButton8.setVisible(false);
        jButton10.setVisible(false);
        jButton16.setVisible(false);
        }
        else if(aut.equalsIgnoreCase(ed)){
        jTable5();
        rc5();
        jTable6();
        rc6();
        jTable7();
        rc7();
        jButton21.setVisible(false);
        jButton24.setVisible(false);
        jButton25.setVisible(false);
        jTabbedPane1.remove(7);
        jTabbedPane1.remove(3);
            
        }
        else if(aut.equalsIgnoreCase(us)){
        jButton21.setVisible(false);
        jButton24.setVisible(false);
        jButton25.setVisible(false);
        jTabbedPane1.remove(7);
        jTabbedPane1.remove(6);
        jTabbedPane1.remove(3);

        }
               
    }
    
    
    
    public void back(){
        String b=ta;
        String b2=aut;
        setVisible(false);
        Home ob = new Home(b,b2);
        ob.setVisible(true);
    }
    
    public void rc1(){
        int c=jTable1.getRowCount();
        jLabel15.setText("NUMBER OF BOOKS: "+c);
    }
    
    public void rc2(){
        int c=jTable2.getRowCount();
        jLabel16.setText("NUMBER OF STUDENTS: "+c);
    }
    
    public void rc3(){
        int c=jTable3.getRowCount();
        jLabel19.setText("NUMBER OF ISSUES: "+c);
    }
    
    public void rc4(){
        int c=jTable4.getRowCount();
        jLabel21.setText("NUMBER OF RETURNS: "+c);
    }
    
    public void rc5(){
        int c=jTable5.getRowCount();
        jLabel23.setText("NUMBER OF BOOKS: "+c);
    }
    
    public void rc6(){
        int c=jTable6.getRowCount();
        jLabel24.setText("NUMBER OF STUDENTS: "+c);
    }
    
    public void rc7(){
        int c=jTable7.getRowCount();
        jLabel25.setText("NUMBER OF FACULTY: "+c);
    }
    
    public void rc8(){
        int c=jTable8.getRowCount();
        jLabel26.setText("NUMBER OF BOOKS: "+c);
    }
    
    public void rc9(){
        int c=jTable9.getRowCount();
        jLabel27.setText("NUMBER OF STUDENTS: "+c);
    }
    
    public void rc10(){
        int c=jTable10.getRowCount();
        jLabel28.setText("NUMBER OF FACULTY: "+c);
    }
    
    public void rc11(){
        int c=jTable11.getRowCount();
        jLabel18.setText("NUMBER OF ACCOUNTS: "+c);
    }
    
    public void rc14(){
        int c=jTable14.getRowCount();
        jLabel17.setText("NUMBER OF FACULTY: "+c);
    }
    
    public void rc15(){
        int c=jTable15.getRowCount();
        jLabel20.setText("NUMBER OF ISSUES: "+c);
    }
    
    public void rc16(){
        int c=jTable16.getRowCount();
        jLabel22.setText("NUMBER OF RETURNS: "+c);
    }

    public void jTable1() {
        try {
            String sql = "select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable2() {
        try {
            String sql = "select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable3() {
        try {
            String sql = "select * from Students_Issues";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable4() {
        try {
            String sql = "select * from Students_Returns";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable5() {
        try {
            String sql = "select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable6() {
        try {
            String sql = "select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable7() {
        try {
            String sql = "select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable8() {
        try {
            String sql = "select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable8.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable9() {
        try {
            String sql = "select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable10() {
        try {
            String sql = "select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable10.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void jTable11() {
        if(aut.equalsIgnoreCase(sa)){
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable11.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
        else if(aut.equalsIgnoreCase(ad)){
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable11.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }

        
    public void jTable14() {
        try {
            String sql = "select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void jTable15() {
        try {
            String sql = "select * from Faculty_Issues ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void jTable16() {
        try {
            String sql = "select * from Faculty_Returns ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void search1(){
        String x=jComboBox1.getSelectedItem().toString();
        String emp=jTextField1.getText();
        String o="NONE";
        String b="ID";
        String n="NAME";
        String p="PUBLISHER";
        String a="AUTHOR";
        String i="ISBN";
        String r="RACK";
        String s="STATUS";
        
           if(x.equals(o)){
                    if("".equals(emp)){    
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books";
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
        else if(x.equals(b)){
                        
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where BOOK_ID LIKE '%"+emp+"%'";
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
                         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where NAME LIKE '%"+emp+"%'";
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
           else if(x.equals(p)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where PUBLISHER LIKE '%"+emp+"%'";
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
           else if(x.equals(a)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where AUTHOR LIKE '%"+emp+"%'";
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
           else if(x.equals(i)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where ISBN LIKE '%"+emp+"%'";
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
           else if(x.equals(r)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where RACK LIKE '%"+emp+"%'";
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
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books where STATUS LIKE '%"+emp+"%'";
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
        String emp=jTextField2.getText();
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
        else if(x.equals(s)){
         
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where STUDENT_ID LIKE '%"+emp+"%'";
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
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where NAME LIKE '%"+emp+"%'";
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
        else if(x.equals(h)){
         
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where BATCH LIKE '%"+emp+"%'";
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
        else if(x.equals(c)){
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where COURSE LIKE '%"+emp+"%'";
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
            
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where BRANCH LIKE '%"+emp+"%'";
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
                 
         else if(x.equals(y)){
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where YEAR LIKE '%"+emp+"%'";
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
         else if(x.equals(t)){
         
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students where STATUS LIKE '%"+emp+"%'";
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
     
     public void search3(){
         
        String x=  jComboBox3.getSelectedItem().toString();
        String emp=jTextField3.getText();
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
               jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
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
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         
        }
     
     public void search4(){
         
        String x=  jComboBox4.getSelectedItem().toString();
        String emp=jTextField4.getText();
        String u="USERNAME";
        String n="NAME";
        String d="DEPARTMENT";
        String de="DESIGNATION";
        String a="AUTHORITY";
        String o="NONE";
        if(aut.equalsIgnoreCase(sa)){
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable11.setModel(DbUtils.resultSetToTableModel(rs));
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
        else if(x.equals(u)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && USERNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(n)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(d)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && DEPARTMENT LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(de)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && DESIGNATION LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(a)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
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
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable11.setModel(DbUtils.resultSetToTableModel(rs));
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
        else if(x.equals(u)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && USERNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(n)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(d)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && DEPARTMENT LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(de)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && DESIGNATION LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(a)){
                 
            String sql="select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,AUTHORITY,PHONE_NO,EMAIL_ID,PASSWORD from Accounts where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' && AUTHORITY LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        }
     }
     
     public void search5(){
         
        String x=  jComboBox5.getSelectedItem().toString();
        String emp=jTextField5.getText();
        String i="ISSUE ID";
        String b="BOOK ID";
        String n="BOOK NAME";
        String s="STUDENT ID";
        String sn="STUDENT NAME";
        String d="DATE OF ISSUE";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select * from Students_Issues";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
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
               
                    jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
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
             
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
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
             
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
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
                jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
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
             
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
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
                jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
}

public void search6(){
         
        String x=  jComboBox6.getSelectedItem().toString();
        String emp=jTextField6.getText();
        String i="ISSUE ID";
        String b="BOOK ID";
        String n="BOOK NAME";
        String f="FACULTY ID";
        String fn="FACULTY NAME";
        String d="DATE OF ISSUE";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select * from Faculty_Issues";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable15.setModel(DbUtils.resultSetToTableModel(rs));
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
               
                    jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        
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
             
        jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        
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
             
        jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        
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
                jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        
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
             
        jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        
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
                jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
}

public void search7(){
         
        String x=  jComboBox7.getSelectedItem().toString();
        String emp=jTextField7.getText();
        String i="ISSUE ID";
        String b="BOOK ID";
        String n="BOOK NAME";
        String s="STUDENT ID";
        String sn="STUDENT NAME";
        String d="DATE OF ISSUE";
        String r="DATE OF RETURN";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select * from Students_Returns";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
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
         
                
        
            String sql="select * from Students_Returns where ISSUE_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(b)){
                
        
            String sql="select * from Students_Returns where BOOK_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(n)){
                
        
            String sql="select * from Students_Returns where BNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(s)){
         
               
            String sql="select * from Students_Returns where STUDENT_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(sn)){
                
        
            String sql="select * from Students_Returns where SNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(d)){
         
               
            String sql="select * from Students_Returns where DATE_OF_ISSUE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
        else if(x.equals(r)){
         
               
            String sql="select * from Students_Returns where DATE_OF_RETURN LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
}

public void search8(){
         
        String x=  jComboBox8.getSelectedItem().toString();
        String emp=jTextField8.getText();
        String i="ISSUE ID";
        String b="BOOK ID";
        String n="BOOK NAME";
        String f="FACULTY ID";
        String fn="FACULTY NAME";
        String d="DATE OF ISSUE";
        String r="DATE OF RETURN";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(emp)){
            String sql="select * from Faculty_Returns";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable16.setModel(DbUtils.resultSetToTableModel(rs));
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
         
                
        
            String sql="select * from Faculty_Returns where ISSUE_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(b)){
                
        
            String sql="select * from Faculty_Returns where BOOK_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(n)){
                
        
            String sql="select * from Faculty_Returns where BNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(f)){
         
               
            String sql="select * from Faculty_Returns where FACULTY_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x.equals(fn)){
                
        
            String sql="select * from Faculty_Returns where FNAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(d)){
         
               
            String sql="select * from Faculty_Returns where DATE_OF_ISSUE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
        else if(x.equals(r)){
         
               
            String sql="select * from Faculty_Returns where DATE_OF_RETURN LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable16.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }
}

public void search9(){
        String x=jComboBox9.getSelectedItem().toString();
        String emp=jTextField9.getText();
        String o="NONE";
        String b="ID";
        String n="NAME";
        String p="PUBLISHER";
        String a="AUTHOR";
        String i="ISBN";
        String r="RACK";
        String s="STATUS";
        
           if(x.equals(o)){
                    if("".equals(emp)){    
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
           }else{
                 JOptionPane.showMessageDialog(null, "Please select a valid option","Error", JOptionPane.ERROR_MESSAGE);
             } 
        }
        else if(x.equals(b)){
                        
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where BOOK_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
           else if(x.equals(n)){
                         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
         }
           else if(x.equals(p)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where PUBLISHER LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
               
                rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
           }
           else if(x.equals(a)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where AUTHOR LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
           else if(x.equals(i)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where ISBN LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
           else if(x.equals(r)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where RACK LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
               }
           else if(x.equals(s)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Update where STATUS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();
                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
               
     }
                      
        }
        
     public void search10(){
         
        String x=  jComboBox10.getSelectedItem().toString();
        String emp=jTextField10.getText();
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
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable6.setModel(DbUtils.resultSetToTableModel(rs));
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
        else if(x.equals(s)){
         
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where STUDENT_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(n)){
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(h)){
         
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where BATCH LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(c)){
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where COURSE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
               
                rs=pst.executeQuery();
               jTable6.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(b)){
            
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where BRANCH LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable6.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
                 
         else if(x.equals(y)){
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where YEAR LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable6.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(t)){
         
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Update where STATUS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
               
                rs=pst.executeQuery();
                jTable6.setModel(DbUtils.resultSetToTableModel(rs));
                rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
         
        }
     
     public void search11(){
         
        String x=  jComboBox11.getSelectedItem().toString();
        String emp=jTextField11.getText();
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
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update ";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
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
         
                String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where FACULTY_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(n)){
               
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
                  
         else if(x.equals(c)){
               
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where COURSE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(d)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where DEPARTMENT LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(de)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where DESIGNATION LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(su)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where SUBJECTS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(s)){
                
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Update where STATUS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         
        }
     
     public void search12(){
        String x=jComboBox12.getSelectedItem().toString();
        String emp=jTextField12.getText();
        String o="NONE";
        String b="ID";
        String n="NAME";
        String p="PUBLISHER";
        String a="AUTHOR";
        String i="ISBN";
        String r="RACK";
        String s="STATUS";
        
           if(x.equals(o)){
                    if("".equals(emp)){    
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
           }else{
                 JOptionPane.showMessageDialog(null, "Please select a valid option","Error", JOptionPane.ERROR_MESSAGE);
             } 
        }
        else if(x.equals(b)){
                        
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where BOOK_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
           else if(x.equals(n)){
                         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
         }
           else if(x.equals(p)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where PUBLISHER LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
               
                rs=pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
           }
           else if(x.equals(a)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where AUTHOR LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
           else if(x.equals(i)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where ISBN LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
           else if(x.equals(r)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where RACK LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
               }
           else if(x.equals(s)){
         
            String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books_Delete where STATUS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();
                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
               
     }
                      
        }
        
     public void search13(){
         
        String x=  jComboBox13.getSelectedItem().toString();
        String emp=jTextField13.getText();
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
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
        
        jTable9.setModel(DbUtils.resultSetToTableModel(rs));
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
        else if(x.equals(s)){
         
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where STUDENT_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               
                    jTable9.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                

            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(n)){
                
        
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
             
        jTable9.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
                
              }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
        else if(x.equals(h)){
         
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where BATCH LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
        
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(c)){
               
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where COURSE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
               
                rs=pst.executeQuery();
               jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(b)){
            
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where BRANCH LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
                 
         else if(x.equals(y)){
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where YEAR LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(t)){
         
                
            String sql="select STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS from Students_Delete where STATUS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
               
                rs=pst.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
         
        }
     
     public void search14(){
         
        String x=  jComboBox14.getSelectedItem().toString();
        String emp=jTextField14.getText();
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
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete ";
            try{
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
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
         
                String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where FACULTY_ID LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                    
                     
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        
     }
        else if(x.equals(n)){
               
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where NAME LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
                  
         else if(x.equals(c)){
               
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where COURSE LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
               jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(d)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where DEPARTMENT LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(de)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where DESIGNATION LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
                
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(su)){
            
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where SUBJECTS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         else if(x.equals(s)){
                
            String sql="select FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS from Faculty_Delete where STATUS LIKE '%"+emp+"%'";
            try{
                pst=conn.prepareStatement(sql);
                
                rs=pst.executeQuery();
                jTable10.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();

                    pst.close();
               
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        
         }
         
        }
    
    public void Restore1(){

        String action="Restored book";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        
        int ro = jTable8.getSelectedRow();
       int n= jTable8.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
         if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
             try {
             int[] indexs = jTable8.getSelectedRows();
       for (int i = 0; i < indexs.length; i++) {
        String bid = jTable8.getModel().getValueAt(indexs[i], 0).toString();
                 String sql="Select * from Books_Delete where BOOK_ID='"+bid+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){             
              
                    String add1=rs.getString("BOOK_ID");
                    String add2=rs.getString("NAME");
                    String add3=rs.getString("AUTHOR");
                    String add4=rs.getString("PUBLISHER");
                    String add5=rs.getString("EDITION");
                    String add6=rs.getString("YEAR");
                    String add7=rs.getString("PRICE");
                    String add8=rs.getString("PAGES");
                    String add9=rs.getString("ISBN");
                    String add10=rs.getString("RACK");
                    String add11=rs.getString("STATUS");
                    byte[] add12=rs.getBytes("PHOTO");
               
            String sql2 = "Insert into Books(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
         
                pst = conn.prepareStatement(sql2);
                pst.setString(1, add1);
                pst.setString(2, add2);
                pst.setString(3, add3);
                pst.setString(4, add4);
                pst.setString(5, add5);
                pst.setString(6, add6);
                pst.setString(7, add7);
                pst.setString(8, add8);
                pst.setString(9, add9);
                pst.setString(10, add10);
                pst.setString(11, add11);
                pst.setBytes(12, add12);
                pst.execute();
               
                
                 String sql3 = "Delete from Books_Delete where BOOK_ID='"+bid+"'";
          
                pst = conn.prepareStatement(sql3);

                pst.execute();
                
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
             
            }
        }
       
          jTable1();
        rc1();
        jTable8();
        rc8();
        JOptionPane.showMessageDialog(null, "Books Restored","Message", JOptionPane.INFORMATION_MESSAGE);
        
             } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        
        
         }
         }
    }
    
    public void Restore2(){
       
String action="Restored student";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        
        int ro=jTable9.getSelectedRow();
        
        int n= jTable9.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
            if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
                try {
         int[] indexs = jTable9.getSelectedRows();
       for (int i = 0; i < indexs.length; i++) {
        String sid = jTable9.getModel().getValueAt(indexs[i], 0).toString();
             String sql="Select * from Students_Delete where STUDENT_ID='"+sid+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){             
              
                    String add1=rs.getString("STUDENT_ID");
                    String add2=rs.getString("NAME");
                    String add3=rs.getString("FATHER_NAME");
                    String add4=rs.getString("BATCH");
                    String add5=rs.getString("COURSE");
                    String add6=rs.getString("BRANCH");
                    String add7=rs.getString("YEAR");
                    String add8=rs.getString("SEMESTER");
                    String add9=rs.getString("PHONE_NO");
                    String add10=rs.getString("EMAIL_ID");
                    String add11=rs.getString("STATUS");
                    byte[] add12=rs.getBytes("PHOTO");
             
            String sql2 = "insert into Students(STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
           
                pst = conn.prepareStatement(sql2);
                pst.setString(1, add1);
                pst.setString(2, add2);
                pst.setString(3, add3);
                pst.setString(4, add4);
                pst.setString(5, add5);
                pst.setString(6, add6);
                pst.setString(7, add7);
                pst.setString(8, add8);
                pst.setString(9, add9);
                pst.setString(10, add10);
                pst.setString(11, add11);
                pst.setBytes(12, add12);
                pst.execute();
          
            
            
            String sql3 = "delete from Students_Delete where STUDENT_ID='" +sid+ "' ";
         
                pst = conn.prepareStatement(sql3);

                pst.execute();
                
           
             String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    
            
            
        }
       }
        jTable2();
        rc2();
         jTable9();
            rc9();
       JOptionPane.showMessageDialog(null, "Students Restored","Message", JOptionPane.INFORMATION_MESSAGE);
       
        
             } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
      
       
            }
         }
    }
    
    public void Restore3(){
String action="Restored faculty";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
       
        int ro=jTable10.getSelectedRow();
        int n= jTable10.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
        
         if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
               try {
             int[] indexs = jTable10.getSelectedRows();
       for (int i = 0; i < indexs.length; i++) {
        String fid = jTable10.getModel().getValueAt(indexs[i], 0).toString();
             String sql="Select * from Faculty_Delete where FACULTY_ID='"+fid+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){             
              
                    String add1=rs.getString("Faculty_ID");
                    String add2=rs.getString("NAME");
                    String add3=rs.getString("FATHER_NAME");
                    String add4=rs.getString("QUALIFICATION");
                    String add5=rs.getString("COURSE");
                    String add6=rs.getString("DEPARTMENT");
                    String add7=rs.getString("DESIGNATION");
                    String add8=rs.getString("SUBJECTS");
                    String add9=rs.getString("PHONE_NO");
                    String add10=rs.getString("EMAIL_ID");
                    String add11=rs.getString("STATUS");
                    byte[] add12=rs.getBytes("PHOTO");
          
            String sql2 = "insert into Faculty(FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
            
                pst = conn.prepareStatement(sql2);
                 pst.setString(1, add1);
                pst.setString(2, add2);
                pst.setString(3, add3);
                pst.setString(4, add4);
                pst.setString(5, add5);
                pst.setString(6, add6);
                pst.setString(7, add7);
                pst.setString(8, add8);
                pst.setString(9, add9);
                pst.setString(10, add10);
                pst.setString(11, add11);
                pst.setBytes(12, add12);
                pst.execute();
                             
            String sql3 = "delete from Faculty_Delete where FACULTY_ID='" +fid+ "' ";
            
                pst = conn.prepareStatement(sql3);

                pst.execute();
            
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                      
         
            }
        }
          
           jTable14();
         rc14();
         jTable10();
            rc10();
             JOptionPane.showMessageDialog(null, "Faculty Restored","Message", JOptionPane.INFORMATION_MESSAGE);
             
             } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
      
         }
         }
    }
    
    public void empty1(){
        String action="Cleared deleted books record";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        int n= jTable8.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
        int p=JOptionPane.showConfirmDialog(null, "Do you really want to clear all records","Empty",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
          try {
         String sql9="Delete from Books_Delete";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    
                    String sql10="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql10);
                    pst.execute();
                       jTable8();
                       rc8();
                       JOptionPane.showMessageDialog(null, "Books record cleared","Message", JOptionPane.INFORMATION_MESSAGE);
                       
                        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
                }
         }
    }
    
    public void empty2(){
        String action="Cleared deleted students record";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        int n= jTable9.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
         int p=JOptionPane.showConfirmDialog(null, "Do you really want to clear all records","Empty",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
          try {
         String sql9="Delete from Students_Delete";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    String sql10="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql10);
                    pst.execute();
                       jTable9();
                       rc9();
                       JOptionPane.showMessageDialog(null, "Students record cleared","Message", JOptionPane.INFORMATION_MESSAGE);
                       
                        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
                }
         }
    }
  
    public void empty3(){
        String action="Cleared deleted faculty record";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        int n= jTable10.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
         int p=JOptionPane.showConfirmDialog(null, "Do you really want to clear all records","Empty",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
    
          try {
         String sql9="Delete from Faculty_Delete";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    String sql10="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql10);
                    pst.execute();
                        jTable10();
                       rc10();
                       JOptionPane.showMessageDialog(null, "Faculty record cleared","Message", JOptionPane.INFORMATION_MESSAGE);
                      
                        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
         }
    }
    
    public void StudentPaid() {
        int ro= jTable4.getSelectedRow();
          int n= jTable4.getRowCount();
          int co=0;
          String pa="PAID";
          String action="Updated Student Return Status";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
                    
           try{
               int[] row = jTable4.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
          
          String id = jTable4.getModel().getValueAt(row[i], 0).toString();
            String sta = jTable4.getModel().getValueAt(row[i], 9).toString();
        if(sta.equals(pa)){
            JOptionPane.showMessageDialog(null, "Due already paid for "+row[i]+"","Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
          
                    String sql1="update Students_Returns set STATUS='"+pa+"' where ISSUE_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
               String sql2="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql2);
                    pst.execute();
                    co=1;
                
        }
        
    }
                if(co>0){
                jTable4();
                JOptionPane.showMessageDialog(null, "Updated","Message", JOptionPane.INFORMATION_MESSAGE);
                }
                
     }catch(HeadlessException | SQLException | ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, e);
        }
               
           }
         }
    }
    
    public void FacultyPaid() {
        int ro= jTable16.getSelectedRow();
          int n= jTable16.getRowCount();
          int co=0;
          String pa="PAID";
          String action="Updated Faculty Return Status";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
                    
           try{
               int[] row = jTable16.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
          
          String id = jTable16.getModel().getValueAt(row[i], 0).toString();
            String sta = jTable16.getModel().getValueAt(row[i], 9).toString();
        if(sta.equals(pa)){
            JOptionPane.showMessageDialog(null, "Due already paid for "+row[i]+"","Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
          
                    String sql1="update Faculty_Returns set STATUS='"+pa+"' where ISSUE_ID='"+id+"' ";
           
               pst=conn.prepareStatement(sql1);
               pst.execute();
               String sql2="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql2);
                    pst.execute();
                co=1;
        }
        
    }
                if(co>0){
                 jTable16();
                JOptionPane.showMessageDialog(null, "Updated","Message", JOptionPane.INFORMATION_MESSAGE);
                }
                
     }catch(HeadlessException | SQLException | ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, e);
        }
               
           }
         }
    }
    
    public void BDelete(){
         int ro= jTable1.getSelectedRow();
          int n= jTable1.getRowCount();
          int co=0;
          String action="Deleted book";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
               int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
                    try{
        int[] row = jTable1.getSelectedRows();
         for (int i = 0; i < row.length; i++) {
        String id = jTable1.getModel().getValueAt(row[i], 0).toString();
        
        
         String sql="Select * from Students_Issues where BOOK_ID='"+id+"'";
     Statement stat=conn.createStatement();
    ResultSet rst=stat.executeQuery(sql);
    String sql1="Select * from Faculty_Issues where BOOK_ID='"+id+"'";
    Statement stats=conn.createStatement();
    ResultSet rsts=stats.executeQuery(sql1);
    if(rst.next()){
        JOptionPane.showMessageDialog(null, "Cannot Delete | Book "+id+" under issue","Error", JOptionPane.ERROR_MESSAGE);
    }
    else if(rsts.next()){
        JOptionPane.showMessageDialog(null, "Cannot Delete | Book "+id+" under issue","Error", JOptionPane.ERROR_MESSAGE);
    }
    else
    {   
        
            
             
            String sql2="Select * from Books where BOOK_ID='"+id+"'";
            pst=conn.prepareStatement(sql2);
             rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("BOOK_ID");
                    String add2=rs.getString("NAME");
                    String add3=rs.getString("AUTHOR");
                    String add4=rs.getString("PUBLISHER");
                    String add5=rs.getString("EDITION");
                    String add6=rs.getString("YEAR");
                    String add7=rs.getString("PRICE");
                    String add8=rs.getString("PAGES");
                    String add9=rs.getString("ISBN");
                    String add10=rs.getString("RACK");
                    String add11=rs.getString("STATUS");
                    imagedata3=rs.getBytes("PHOTO");
                    rs.close();
                    pst.close();
            String sql3="insert into Books_Delete(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql3);
            pst.setString(1, add1);
            pst.setString(2, add2);
            pst.setString(3, add3);
            pst.setString(4, add4);
            pst.setString(5, add5);
            pst.setString(6, add6);
            pst.setString(7, add7);
            pst.setString(8, add8);
            pst.setString(9, add9);
            pst.setString(10, add10);
            pst.setString(11, add11);
            pst.setBytes(12, imagedata3);
            pst.execute();
            
            String sql4 = "DELETE FROM Books WHERE BOOK_ID='"+id+"'";
            pst=conn.prepareStatement(sql4);
            pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
            
            co=1;
                }
    
                }
         }
       if(co>0){
           jTable1();
       JOptionPane.showMessageDialog(null, "Books Deleted","Message", JOptionPane.INFORMATION_MESSAGE);
       }
}catch(HeadlessException | SQLException | ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, e);
        }
                
           }
           }
         }
    }
    
    public void SDelete(){
           int ro= jTable2.getSelectedRow();
          int n= jTable2.getRowCount();
          int co=0;
          String action="Deleted student";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
               
               
           int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
                    
           try{
               int[] row = jTable2.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
          
          String id = jTable2.getModel().getValueAt(row[i], 0).toString();
            String sql="Select * from Students_Issues where STUDENT_ID='"+id+"'";
             Statement stat=conn.createStatement();
    ResultSet rst=stat.executeQuery(sql);
    if(rst.next()){
        JOptionPane.showMessageDialog(null, "Cannot Delete | Books issued to "+id+"","Error", JOptionPane.ERROR_MESSAGE);
    }
    else{
    
            String sql2="Select * from Students where STUDENT_ID='"+id+"'";
            pst=conn.prepareStatement(sql2);
             rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("STUDENT_ID");
                    String add2=rs.getString("NAME");
                    String add3=rs.getString("FATHER_NAME");
                    String add4=rs.getString("BATCH");
                    String add5=rs.getString("COURSE");
                    String add6=rs.getString("BRANCH");
                    String add7=rs.getString("YEAR");
                    String add8=rs.getString("SEMESTER");
                    String add9=rs.getString("PHONE_NO");
                    String add10=rs.getString("EMAIL_ID");
                    String add11=rs.getString("STATUS");
                    imagedata1=rs.getBytes("PHOTO");
                    rs.close();
                    pst.close();
            String sql3="insert into Students_Delete(STUDENT_ID,NAME,FATHER_NAME,BATCH,COURSE,BRANCH,YEAR,SEMESTER,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql3);
            pst.setString(1, add1);
            pst.setString(2, add2);
            pst.setString(3, add3);
            pst.setString(4, add4);
            pst.setString(5, add5);
            pst.setString(6, add6);
            pst.setString(7, add7);
            pst.setString(8, add8);
            pst.setString(9, add9);
            pst.setString(10, add10);
            pst.setString(11, add11);
            pst.setBytes(12, imagedata1);
            pst.execute();
            
            String sql4 = "DELETE FROM Students WHERE STUDENT_ID='"+id+"'";
            pst=conn.prepareStatement(sql4);
            pst.execute();
            
           String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                   
             co=1;
            
                }
              
    }
 
                }
               
        if(co>0){
       jTable2();
       JOptionPane.showMessageDialog(null, "Students Deleted","Message", JOptionPane.INFORMATION_MESSAGE);
       }
     }catch(HeadlessException | SQLException | ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, e);
        }
                }
           }
         }
      }       
    
    public void FDelete(){
         int ro= jTable14.getSelectedRow();
          int n= jTable14.getRowCount();
          int co=0;
          String action="Deleted faculty";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
               int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
                    try{
       int[] row = jTable14.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
        String id = jTable14.getModel().getValueAt(row[i], 0).toString();
        
         String sql="Select * from Faculty_Issues where Faculty_ID='"+id+"'";

    Statement stat=conn.createStatement();
    ResultSet rst=stat.executeQuery(sql);
    if(rst.next()){
        JOptionPane.showMessageDialog(null, "Cannot Delete | Books issued to "+id+"","Error", JOptionPane.ERROR_MESSAGE);
    }
    else
    {  
        
            
             
            String sql2="Select * from Faculty where FACULTY_ID='"+id+"'";
            pst=conn.prepareStatement(sql2);
             rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("FACULTY_ID");
                    String add2=rs.getString("NAME");
                    String add3=rs.getString("FATHER_NAME");
                    String add4=rs.getString("QUALIFICATION");
                    String add5=rs.getString("COURSE");
                    String add6=rs.getString("DEPARTMENT");
                    String add7=rs.getString("DESIGNATION");
                    String add8=rs.getString("SUBJECTS");
                    String add9=rs.getString("PHONE_NO");
                    String add10=rs.getString("EMAIL_ID");
                    String add11=rs.getString("STATUS");
                    imagedata2=rs.getBytes("PHOTO");
                    rs.close();
                    pst.close();
            String sql3="insert into Faculty_Delete(FACULTY_ID,NAME,FATHER_NAME,QUALIFICATION,COURSE,DEPARTMENT,DESIGNATION,SUBJECTS,PHONE_NO,EMAIL_ID,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql3);
            pst.setString(1, add1);
            pst.setString(2, add2);
            pst.setString(3, add3);
            pst.setString(4, add4);
            pst.setString(5, add5);
            pst.setString(6, add6);
            pst.setString(7, add7);
            pst.setString(8, add8);
            pst.setString(9, add9);
            pst.setString(10, add10);
            pst.setString(11, add11);
            pst.setBytes(12, imagedata2);
            pst.execute();
            
            String sql4 = "DELETE FROM Faculty WHERE FACULTY_ID='"+id+"'";
            pst=conn.prepareStatement(sql4);
            pst.execute();
         String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
            
           co=1;
        
             
                }
                }
            
       }
       
       if(co>0){
           jTable14();
       JOptionPane.showMessageDialog(null, "Faculty Deleted","Message", JOptionPane.INFORMATION_MESSAGE);
       }
       
}catch(HeadlessException | SQLException | ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, e);
        }
       
                }
           }
         }
    }
    
    public void ADelete(){
         int ro= jTable11.getSelectedRow();
          int n= jTable11.getRowCount();
          String action="Deleted account";
        Date da= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(da);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(da);
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
           if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
        int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
                     try{
       int[] row = jTable11.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
       String ur = jTable11.getModel().getValueAt(row[i], 0).toString();
        
        
       
            
            String sql1 = "DELETE FROM Accounts WHERE USERNAME='"+ur+"'";
            pst=conn.prepareStatement(sql1);
            pst.execute();
         String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+ta+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
       }
            jTable11();
       JOptionPane.showMessageDialog(null, "Accounts Deleted","Message", JOptionPane.INFORMATION_MESSAGE);
                }catch(HeadlessException | SQLException e){
                    JOptionPane.showMessageDialog(null, "Error in Delete","Error", JOptionPane.ERROR_MESSAGE);
                }
       
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

        jPanel17 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jTextField9 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jTextField10 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jTextField11 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jTextField12 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jTextField13 = new javax.swing.JTextField();
        jComboBox13 = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1370, 768));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel1.setText("Search by");

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField1.setOpaque(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(5);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "AUTHOR", "PUBLISHER", "ISBN", "RACK", "STATUS" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel15.setText("Row Count");

        jButton21.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton21.setText("Delete");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jButton21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton21KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton21KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel15)
                    .addComponent(jButton21))
                .addContainerGap())
        );

        jTabbedPane1.addTab("BOOKS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton2.setText("Back");
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

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
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
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jComboBox2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox2.setMaximumRowCount(5);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "BATCH", "COURSE", "BRANCH", "YEAR", "STATUS" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel2.setText("Search by");

        jTextField2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField2.setOpaque(false);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(385, 403, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel16.setText("Row Count");

        jButton24.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton24.setText("Delete");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jButton24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton24KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton24)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel16)
                    .addComponent(jButton24))
                .addContainerGap())
        );

        jTabbedPane1.addTab("STUDENTS", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton18.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton18.setText("Back");
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

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel3.setText("Search by");

        jTextField3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField3.setOpaque(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane14.setViewportView(jTable14);

        jComboBox3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox3.setMaximumRowCount(5);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "COURSE", "DEPARTMENT", "DESIGNATION", "SUBJECTS", "STATUS" }));
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
            .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel17.setText("Row Count");

        jButton25.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton25.setText("Delete");
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jButton25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton25KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25)
                .addGap(18, 18, 18)
                .addComponent(jButton18)
                .addContainerGap())
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jLabel17)
                    .addComponent(jButton25))
                .addContainerGap())
        );

        jTabbedPane1.addTab("FACULTY", jPanel7);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton6.setText("Back");
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

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jTextField4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField4.setOpaque(false);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTable11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable11KeyPressed(evt);
            }
        });
        jScrollPane11.setViewportView(jTable11);

        jComboBox4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox4.setMaximumRowCount(5);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "USERNAME", "NAME", "DEPARTMENT", "DESIGNATION", "AUTHORITY" }));
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel4.setText("Search by");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel18.setText("Row Count");

        jButton26.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton26.setText("Delete");
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jButton26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton26KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton26)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap())
            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jLabel18)
                    .addComponent(jButton26))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ACCOUNTS", jPanel6);

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTabbedPane4.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(1355, 623));

        jTextField5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField5.setOpaque(false);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel5.setText("Search by");

        jComboBox5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox5.setMaximumRowCount(5);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ISSUE ID", "BOOK ID", "BOOK NAME", "STUDENT ID", "STUDENT NAME", "DATE OF ISSUE", " " }));
        jComboBox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        jTable3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable3KeyPressed(evt);
            }
        });
        jScrollPane12.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
            .addComponent(jScrollPane12)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel19.setText("Row Count");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("STUDENTS", jPanel4);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jButton19.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton19.setText("Back");
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jButton19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton19KeyPressed(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel6.setText("Search by");

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(jTable15);

        jTextField6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField6.setOpaque(false);
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox6.setMaximumRowCount(5);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ISSUE ID", "BOOK ID", "BOOK NAME", "FACULTY ID", "FACULTY NAME", "DATE OF ISSUE" }));
        jComboBox6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(382, 401, Short.MAX_VALUE))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel20.setText("Row Count");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addContainerGap())
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        jTabbedPane4.addTab("FACULTY", jPanel15);

        jTabbedPane1.addTab("ISSUES", jTabbedPane4);

        jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane5.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTabbedPane5.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton4.setText("Back");
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

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel7.setText("Search by");

        jTextField7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField7.setOpaque(false);
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jTable4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable4KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jComboBox7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox7.setMaximumRowCount(5);
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ISSUE ID", "BOOK ID", "BOOK NAME", "STUDENT ID", "STUDENT NAME", "DATE OF ISSUE", "DATE OF RETURN" }));
        jComboBox7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox7KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel21.setText("Row Count");

        jButton22.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paid.png"))); // NOI18N
        jButton22.setText("Paid");
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jButton22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton22KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton22)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap())
            .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton22, jButton4});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel21)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton22, jButton4});

        jTabbedPane5.addTab("STUDENTS", jPanel5);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jButton20.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton20.setText("Back");
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jButton20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton20KeyPressed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane16.setViewportView(jTable16);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel8.setText("Search by");

        jTextField8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField8.setOpaque(false);
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jComboBox8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox8.setMaximumRowCount(5);
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ISSUE ID", "BOOK ID", "BOOK NAME", "STUDENT ID", "STUDENT NAME", "DATE OF ISSUE", "DATE OF RETURN" }));
        jComboBox8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox8KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel22.setText("Row Count");

        jButton23.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paid.png"))); // NOI18N
        jButton23.setText("Paid");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jButton23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton23KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton23)
                .addGap(18, 18, 18)
                .addComponent(jButton20)
                .addContainerGap())
            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton20, jButton23});

        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jLabel22)
                    .addComponent(jButton23))
                .addContainerGap())
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton20, jButton23});

        jTabbedPane5.addTab("FACULTY", jPanel16);

        jTabbedPane1.addTab("RETURNS", jTabbedPane5);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTabbedPane2.setOpaque(true);
        jTabbedPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane2KeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel9.setText("Search by");

        jComboBox9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox9.setMaximumRowCount(5);
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "AUTHOR", "PUBLISHER", "ISBN", "RACK", "STATUS" }));
        jComboBox9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox9KeyPressed(evt);
            }
        });

        jTable5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable5KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jTextField9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField9.setOpaque(false);
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel23.setText("Row Count");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
            .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel23))
                .addContainerGap())
        );

        jTabbedPane2.addTab("BOOKS", jPanel1);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton7.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton7.setText("Back");
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

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel10.setText("Search by");

        jComboBox10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox10.setMaximumRowCount(5);
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "BATCH", "COURSE", "BRANCH", "YEAR", "STATUS" }));
        jComboBox10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox10KeyPressed(evt);
            }
        });

        jTable6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable6KeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jTextField10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField10.setOpaque(false);
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel24.setText("Row Count");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jLabel24))
                .addContainerGap())
        );

        jTabbedPane2.addTab("STUDENTS", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel11.setText("Search by");

        jComboBox11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox11.setMaximumRowCount(5);
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "COURSE", "DEPARTMENT", "DESIGNATION", "SUBJECTS", "STATUS" }));
        jComboBox11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox11KeyPressed(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField11.setOpaque(false);
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });

        jTable7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable7KeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel25.setText("Row Count");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addContainerGap())
            .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jLabel25))
                .addContainerGap())
        );

        jTabbedPane2.addTab("FACULTY", jPanel9);

        jTabbedPane1.addTab("UPDATES", jTabbedPane2);

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTabbedPane3.setOpaque(true);
        jTabbedPane3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane3KeyPressed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

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

        jButton12.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/restore.png"))); // NOI18N
        jButton12.setText("Restore");
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

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel12.setText("Search by");

        jComboBox12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox12.setMaximumRowCount(5);
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "AUTHOR", "PUBLISHER", "ISBN", "RACK", "STATUS" }));
        jComboBox12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox12KeyPressed(evt);
            }
        });

        jTextField12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField12.setOpaque(false);
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        jTable8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jTable8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable8KeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel26.setText("Row Count");

        jButton8.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton8.setText("Empty");
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addContainerGap())
            .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton12, jButton8});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jLabel26)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton12, jButton8});

        jTabbedPane3.addTab("BOOKS", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

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

        jButton14.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/restore.png"))); // NOI18N
        jButton14.setText("Restore");
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

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jTextField13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField13.setOpaque(false);
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField13KeyReleased(evt);
            }
        });

        jComboBox13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox13.setMaximumRowCount(5);
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "BATCH", "COURSE", "BRANCH", "YEAR", "STATUS" }));
        jComboBox13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox13KeyPressed(evt);
            }
        });

        jTable9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable9KeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(jTable9);

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel13.setText("Search by");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel27.setText("Row Count");

        jButton10.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton10.setText("Empty");
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(18, 18, 18)
                .addComponent(jButton14)
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addContainerGap())
            .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton14});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jLabel27)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton14});

        jTabbedPane3.addTab("STUDENTS", jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jButton15.setText("Back");
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jButton17.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/restore.png"))); // NOI18N
        jButton17.setText("Restore");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jTable10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable10KeyPressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jComboBox14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox14.setMaximumRowCount(5);
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "COURSE", "DEPARTMENT", "DESIGNATION", "SUBJECTS", "STATUS" }));
        jComboBox14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox14KeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel14.setText("Search by");

        jTextField14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField14.setOpaque(false);
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel28.setText("Row Count");

        jButton16.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton16.setText("Empty");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jButton16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton16KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addGap(18, 18, 18)
                .addComponent(jButton17)
                .addGap(18, 18, 18)
                .addComponent(jButton15)
                .addContainerGap())
            .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton16, jButton17});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton17)
                    .addComponent(jLabel28)
                    .addComponent(jButton16))
                .addContainerGap())
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton16, jButton17});

        jTabbedPane3.addTab("FACULTY", jPanel12);

        jTabbedPane1.addTab("DELETES", jTabbedPane3);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(-8, 0, 1381, 773);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton21.getText();

        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton24.getText();

        }
        
    }//GEN-LAST:event_jButton2KeyPressed

    private void jTable3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            back();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jTable4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable4KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton22.getText();

        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jTable5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable5KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable5KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            back();

        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jTable6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable6KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable6KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
       back();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            back();
        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jTable7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable7KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable7KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           back();

        }
    }//GEN-LAST:event_jButton9KeyPressed

    private void jTabbedPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane2KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTabbedPane2KeyPressed

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable8MouseClicked

    private void jTable8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable8KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable8KeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton12.getText();

        }
    }//GEN-LAST:event_jButton11KeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
          Restore1();
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           Restore1();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton8.getText();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton11.getText();

        }
    }//GEN-LAST:event_jButton12KeyPressed

    private void jTable9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable9KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable9KeyPressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            back();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton14.getText();

        }
    }//GEN-LAST:event_jButton13KeyPressed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
       Restore2();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyPressed
        // TODO add your handling code here:
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           Restore2();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton10.getText();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton13.getText();

        }
    }//GEN-LAST:event_jButton14KeyPressed

    private void jTable10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyPressed
        // TODO add your handling code here:

        
    }//GEN-LAST:event_jTable10KeyPressed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
       back();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton15KeyPressed
        // TODO add your handling code here:
       

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton17.getText();

        }
    }//GEN-LAST:event_jButton15KeyPressed

    private void jTabbedPane3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane3KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTabbedPane3KeyPressed

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
       back();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           back();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton26.getText();

        }
    }//GEN-LAST:event_jButton6KeyPressed

    private void jTable11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable11KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable11KeyPressed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        Restore3();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        search2();
        rc2();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        search1();
        rc1();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        search3();
        rc14();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        search4();
        rc11();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
        search5();
        rc3();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        search6();
        rc15();
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        search7();
        rc4();
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        search8();
        rc16();
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        // TODO add your handling code here:
        search9();
        rc5();
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        // TODO add your handling code here:
        search10();
        rc6();
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // TODO add your handling code here:
        search11();
        rc7();
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
        search12();
        rc8();
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTextField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyReleased
        // TODO add your handling code here:
        search13();
        rc9();
    }//GEN-LAST:event_jTextField13KeyReleased

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        // TODO add your handling code here:
        search14();
        rc10();
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        empty1();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        empty2();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        empty3();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton18KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton25.getText();

        }
    }//GEN-LAST:event_jButton18KeyPressed

    private void jButton19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton19KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         back();

        }
    }//GEN-LAST:event_jButton19KeyPressed

    private void jButton20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton20KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         back();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton23.getText();

        }
    }//GEN-LAST:event_jButton20KeyPressed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          empty2();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton14.getText();

        }
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          empty1();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton12.getText();

        }
        
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton17KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Restore3();

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButton16.getText();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton15.getText();

        }
    }//GEN-LAST:event_jButton17KeyPressed

    private void jButton16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton16KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          empty3();

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton17.getText();

        }
    }//GEN-LAST:event_jButton16KeyPressed

    private void jComboBox14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox14KeyPressed
        // TODO add your handling code here:
       
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jComboBox14KeyPressed

    private void jComboBox13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox13KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField13.requestFocus();
        }
    }//GEN-LAST:event_jComboBox13KeyPressed

    private void jComboBox12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox12KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jComboBox12KeyPressed

    private void jComboBox11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox11KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField11.requestFocus();
        }
    }//GEN-LAST:event_jComboBox11KeyPressed

    private void jComboBox10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox10KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField10.requestFocus();
        }
    }//GEN-LAST:event_jComboBox10KeyPressed

    private void jComboBox9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jComboBox9KeyPressed

    private void jComboBox8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField8.requestFocus();
        }
    }//GEN-LAST:event_jComboBox8KeyPressed

    private void jComboBox7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jComboBox7KeyPressed

    private void jComboBox6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jComboBox6KeyPressed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jComboBox5KeyPressed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jComboBox4KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        jTable1();
        jTable2();
        jTable3();
        jTable4();
        jTable5();
        jTable6();
        jTable7();
        jTable8();
        jTable9();
        jTable10();
        jTable11();
        jTable14();
        jTable15();
        jTable16();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        StudentPaid();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton22KeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           StudentPaid();
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton4.requestFocus();
        }
    }//GEN-LAST:event_jButton22KeyPressed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        FacultyPaid();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton23KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            FacultyPaid();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton20.requestFocus();
        }
    }//GEN-LAST:event_jButton23KeyPressed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        BDelete();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton21KeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BDelete();
        }

        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton1.requestFocus();
        }
    }//GEN-LAST:event_jButton21KeyPressed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        SDelete();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton24KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            SDelete();
        }

        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton2.requestFocus();
        }
    }//GEN-LAST:event_jButton24KeyPressed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        FDelete();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton25KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            FDelete();
        }
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton18.requestFocus();
        }
    }//GEN-LAST:event_jButton25KeyPressed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        ADelete();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton26KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            ADelete();
        }

        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            jButton6.requestFocus();
        }

    }//GEN-LAST:event_jButton26KeyPressed

    private void jButton21KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton21KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21KeyReleased
    private void setIcon() {
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
            java.util.logging.Logger.getLogger(Stats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Stats(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
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
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
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
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";
String ed="EDITOR";
String us="LIBRARIAN";
byte[] imagedata1=null;
byte[] imagedata2=null;
byte[] imagedata3=null;
}
