/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLockException;
import com.mysql.jdbc.MysqlDataTruncation;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public class Books extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
Webcam webcam;
boolean started = false;
String bo;
String aut;
    /**
     * Creates new form Books
     * @param abc
     * @param def
     */
    public Books(String abc, String def) {
        super("LiMaSy - Developed By IT (R16) - Books");
        initComponents();
        conn=myconnec.ConnecrDb();
        setIcon();
        bo=abc;
        aut=def;
        jTable1();
          webcam = Webcam.getDefault();
          webcam.setViewSize(new Dimension(320,240));
              
            }
    
    public void recover1(){
        if(image1!=null){
            jLabel9.setIcon(image1);
        }
        else if(icon1!=null){
           jLabel9.setIcon(icon1);
        }
        else if(format1!=null){
            jLabel9.setIcon(format1);
        }
    }
    
    public void recover2(){
        if(image2!=null){
            jLabel23.setIcon(image2);
        }
        else if(icon2!=null){
           jLabel23.setIcon(icon2);
        }
        else if(format2!=null){
            jLabel23.setIcon(format2);
        }
    }
    
    public void close1(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel9.setIcon(null);
            recover1();
        
        }
    }
    
    public void close2(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel23.setIcon(null);
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
                     jLabel9.setIcon(image1);
                     format1=null;
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
                cbook_image=bos.toByteArray();
                abook_image=null;
                f.deleteOnExit();
                     webcam.close();
                    
        }catch(IOException | WebcamLockException e){
            JOptionPane.showMessageDialog(null, e);
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
                     jLabel23.setIcon(image2);
                     format2=null;
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
                cbook_image2=bos.toByteArray();
                
              f.deleteOnExit();
                     webcam.close();
                   
        }catch(IOException | WebcamLockException e){
            JOptionPane.showMessageDialog(null, e);
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
                     jLabel9.setIcon(new ImageIcon(image));
                     Thread.sleep(40);
                 
             }
             }catch(HeadlessException | WebcamLockException | InterruptedException e){
            JOptionPane.showMessageDialog(null, e);
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
                     jLabel23.setIcon(new ImageIcon(image));
                     Thread.sleep(40);
                 
             }
             }catch(HeadlessException | WebcamLockException | InterruptedException e){
            JOptionPane.showMessageDialog(null, e);
        }
             
    }
    }
    public void Add(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel9.setIcon(null);
            recover1();
        
        }
        String e=jComboBox1.getSelectedItem().toString();
        String s=jComboBox2.getSelectedItem().toString();
        String n="NONE";
        String action="Added book";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        if(jTextField1.getText().trim().isEmpty() || jTextField2.getText().trim().isEmpty() 
                || jTextField3.getText().trim().isEmpty()|| jTextField12.getText().trim().isEmpty() || jTextField4.getText().trim().isEmpty() 
                || jTextField5.getText().trim().isEmpty() || jTextField6.getText().trim().isEmpty() 
                || jTextField7.getText().trim().isEmpty() || jTextField17.getText().trim().isEmpty()){
                         JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
                  }
        else if(e.equals(n) && s.equals(n)){
             JOptionPane.showMessageDialog(null, "PLease select Edition and Status","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(e.equals(n)){
             JOptionPane.showMessageDialog(null, "PLease select Edition","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(s.equals(n)){
             JOptionPane.showMessageDialog(null, "PLease select Status","Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
         String book=jTextField1.getText();
        String sql="Select * from Books where BOOK_ID='"+book+"'";
        try{
            Statement stat=conn.createStatement();
             rs=stat.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Book ID already in use","Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String sql1="insert into Books(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                
                    pst=conn.prepareStatement(sql1);
                    pst.setString(1, jTextField1.getText());
                    pst.setString(2, jTextField2.getText());
                    pst.setString(3, jTextField3.getText());
                    pst.setString(4, jTextField12.getText());
                    pst.setString(5, (String) jComboBox1.getSelectedItem());
                    pst.setString(6, jTextField17.getText());
                    pst.setString(7, jTextField4.getText());
                    pst.setString(8, jTextField5.getText());
                    pst.setString(9, jTextField6.getText());
                    pst.setString(10, jTextField7.getText());
                    pst.setString(11, (String) jComboBox2.getSelectedItem());
                    if(cbook_image!=null){
                    pst.setBytes(12, cbook_image);
                    
                    
                    }
                    else if(abook_image!=null){
                    pst.setBytes(12, abook_image);
                    
                          }
                    else if(jLabel9!=null){
                        pst.setBytes(12, imagedata1);
                                             
                    }
                       pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+bo+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                     jTable1();
                    JOptionPane.showMessageDialog(null, "New Book Added","Message", JOptionPane.INFORMATION_MESSAGE);
                   
            }
            }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Photo cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
            }
        catch( MysqlDataTruncation ex){
                    JOptionPane.showMessageDialog(null, ex);
                 }catch( SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                 }
            }
        
            }
    public void Attach1(){
      if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel9.setIcon(null);
            recover1();
        
        }
      
        JFileChooser chooser=new JFileChooser();
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & PNG Images", "jpg", "png");
         
    chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(Books.this, null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
         try{
        File f=chooser.getSelectedFile();
        
        icon1=new ImageIcon(f.toString());
         jLabel9.setIcon(icon1);
         cbook_image=null;
         format1=null;
         image1=null;
                filename=f.getPath();
                File image=new File(filename);
                FileInputStream fis=new FileInputStream(image);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1; ) {
                    bos.write(buf,0,readNum);
                }
                abook_image=bos.toByteArray();
               
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
            jLabel23.setIcon(null);
            recover2();
        }
        JFileChooser chooser=new JFileChooser();
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & PNG Images", "jpg", "png");
    chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(Books.this, null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
         try{
        File f=chooser.getSelectedFile();
        
         icon2=new ImageIcon(f.toString());
         jLabel23.setIcon(icon2);
         format2=null;
         image2=null;
                filename=f.getPath();
                File image=new File(filename);
                FileInputStream fis=new FileInputStream(image);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1; ) {
                    bos.write(buf,0,readNum);
                }
                abook_image2=bos.toByteArray();
               
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Please select a valid file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(NullPointerException e){
                
            }
        
       }
    }
    
     public void clear1(){
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField12.setText("");
            jTextField17.setText("");
            jComboBox1.setSelectedItem("NONE");
            jComboBox2.setSelectedItem("NONE");
            if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
            jLabel9.setIcon(null);
            icon1=null;
            image1=null;
            format1=null;
            imagedata1=null;
            abook_image=null;
            cbook_image=null;
    }
     
     public void clear2(){
            jTextField8.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jTextField15.setText("");
            jTextField16.setText("");
            jTextField18.setText("");
            jTextField19.setText("");
            jTextField20.setText("");
            jComboBox3.setSelectedItem("NONE");
            jComboBox4.removeAllItems();
            jComboBox4.addItem("NONE");
            jComboBox4.addItem("AVAILABLE");
            jComboBox4.addItem("UNAVAILABLE");
            jComboBox4.setSelectedItem("NONE");
            if(webcam.isOpen()){
            started=false;
            webcam.close();
        }
            jLabel23.setIcon(null);
            icon2=null;
            image2=null;
            format2=null;
            imagedata2=null;
            abook_image2=null;
            cbook_image2=null;
    }
     
    
    public void back(){
        String b=bo;
        String b2=aut;
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel9.setIcon(null);
            jLabel23.setIcon(null);
        
        }
         setVisible(false);
            Home ob=new Home(b,b2);
            ob.setVisible(true);
    }
    
      
   
    private void jTable1(){
    String sql="select BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS from Books";
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in Table1","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        }
    
    public void search(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel9.setIcon(null);
            recover1();
        
        }
        String i=jTextField1.getText();
        if("".equals(i)){
            JOptionPane.showMessageDialog(null, "Book ID required to search","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(i!=null){
            String sql="select * from Books where BOOK_ID='"+i+"' ";
                try{
                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("BOOK_ID");
                    jTextField1.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField2.setText(add2);
                    String add3=rs.getString("AUTHOR");
                    jTextField3.setText(add3);
                    String add4=rs.getString("PUBLISHER");
                    jTextField12.setText(add4);
                    String add5=rs.getString("EDITION");
                    jComboBox1.setSelectedItem(add5);
                    String add6=rs.getString("YEAR");
                    jTextField17.setText(add6);
                    String add7=rs.getString("PRICE");
                    jTextField4.setText(add7);
                    String add8=rs.getString("PAGES");
                    jTextField5.setText(add8);
                    String add9=rs.getString("ISBN");
                    jTextField6.setText(add9);
                    String add10=rs.getString("RACK");
                    jTextField7.setText(add10);
                    String add11=rs.getString("STATUS");
                    jComboBox2.setSelectedItem(add11);
                    imagedata1=rs.getBytes("PHOTO");
                    format1=new ImageIcon(imagedata1);
                    jLabel9.setIcon(format1);
                    icon1=null;
                    image1=null;
                    rs.close();
                    pst.close();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Book ID not found","Error", JOptionPane.ERROR_MESSAGE);
                }
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error in Search","Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        
    }
        
        public void search1(){
        String x=jComboBox5.getSelectedItem().toString();
        String emp=jTextField8.getText();
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
        
    public void tableClick(){
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel23.setIcon(null);
            recover2();
        }
        try{
        int row=jTable1.getSelectedRow();
                String Table_click=(jTable1.getModel().getValueAt(row, 0).toString());
        
                
                String sql="select * from Books where BOOK_ID='"+Table_click+"' ";

                pst=conn.prepareStatement(sql);

                rs=pst.executeQuery();
                if(rs.next()){
                    String add1=rs.getString("BOOK_ID");
                    jTextField20.setText(add1);
                    String add2=rs.getString("NAME");
                    jTextField9.setText(add2);
                    String add3=rs.getString("AUTHOR");
                    jTextField10.setText(add3);
                    String add4=rs.getString("PUBLISHER");
                    jTextField18.setText(add4);
                    String add5=rs.getString("EDITION");
                    jComboBox3.setSelectedItem(add5);
                    String add6=rs.getString("YEAR");
                    jTextField19.setText(add6);
                    String add7=rs.getString("PRICE");
                    jTextField13.setText(add7);
                    String add8=rs.getString("PAGES");
                    jTextField14.setText(add8);
                    String add9=rs.getString("ISBN");
                    jTextField15.setText(add9);
                    String add10=rs.getString("RACK");
                    jTextField16.setText(add10);
                    String add11=rs.getString("STATUS");
                    if(add11.equalsIgnoreCase("ISSUED")){
                        jComboBox4.removeAllItems();
                        jComboBox4.addItem("ISSUED");
                        jComboBox4.setSelectedItem(add11);
                    }
                    else{
                        jComboBox4.removeAllItems();
                        jComboBox4.addItem("NONE");
                        jComboBox4.addItem("AVAILABLE");
                        jComboBox4.addItem("UNAVAILABLE");
                        jComboBox4.setSelectedItem(add11);
                    }
                    imagedata2=rs.getBytes("PHOTO");
                    format2=new ImageIcon(imagedata2);
                    jLabel23.setIcon(format2);
                    icon2=null;
                    image2=null;
                    rs.close();
                    pst.close();
                }

            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error in tableClick","Error", JOptionPane.ERROR_MESSAGE);
            }    catch(NullPointerException | ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
 
    public void Update(){
         if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel23.setIcon(null);
            recover2();
        }
        String x=  jComboBox7.getSelectedItem().toString();
         String sb= "Search by";
        String ub= "Bulk update";
         String actions="Books bulk update";
        String action="Updated book";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
        if(x.equalsIgnoreCase(sb)){
        String e=  jComboBox3.getSelectedItem().toString();
        String s=  jComboBox4.getSelectedItem().toString();
        String n="NONE";
        String i="ISSUED";
        
         if(jTextField20.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Book ID is required","Error", JOptionPane.ERROR_MESSAGE);
        }
         
        else if(jTextField9.getText().trim().isEmpty() || jTextField10.getText().trim().isEmpty()
            || jTextField13.getText().trim().isEmpty() || jTextField14.getText().trim().isEmpty()
            || jTextField15.getText().trim().isEmpty() || jTextField16.getText().trim().isEmpty()
            || jTextField18.getText().trim().isEmpty() || jTextField19.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(e.equals(n) && s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Edition and Status","Error", JOptionPane.ERROR_MESSAGE);
        }
else if(e.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select an Edition","Error", JOptionPane.ERROR_MESSAGE);
        }
else if(s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Status","Error", JOptionPane.ERROR_MESSAGE);
        }

        else{
            try{
                String value1= jTextField20.getText();
                 String sql="select * from Books where BOOK_ID='"+value1+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){             
               st=rs.getString("STATUS");
               if(st.equalsIgnoreCase(i)){
                   if(!s.equals(st)){
                       JOptionPane.showMessageDialog(null, "Book under issue! Status cannot be changed","Error", JOptionPane.ERROR_MESSAGE);
                   }else{
                       
        try{
        
          int p=JOptionPane.showConfirmDialog(null, "Altered data will be updated","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
                    
                    String sql2="update Books set NAME=?,AUTHOR=?,PUBLISHER=?,EDITION=?,YEAR=?,PRICE=?,PAGES=?,ISBN=?,RACK=?,STATUS=?,PHOTO=? where BOOK_ID='"+value1+"' ";
                    pst=conn.prepareStatement(sql2);
                    
                pst.setString(1, jTextField9.getText());
                pst.setString(2, jTextField10.getText());
                pst.setString(3, jTextField18.getText());
                pst.setString(4, (String) jComboBox3.getSelectedItem());
                pst.setString(5, jTextField19.getText());
                pst.setString(6, jTextField13.getText());
                pst.setString(7, jTextField14.getText());
                pst.setString(8, jTextField15.getText());
                pst.setString(9, jTextField16.getText());
                pst.setString(10, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(11, cbook_image2);
                    imagedata2=null;
                    }
                else if(abook_image2!=null){
                    pst.setBytes(11, abook_image2);
                    imagedata2=null;
                    }
                else if(jLabel23!=null){
                    pst.setBytes(11, imagedata2);
                }
                
                
                    pst.execute();

                
            
             
            String sql3="insert into Books_Update(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
                pst=conn.prepareStatement(sql3);
                pst.setString(1, jTextField20.getText());
                pst.setString(2, jTextField9.getText());
                pst.setString(3, jTextField10.getText());
                pst.setString(4, jTextField18.getText());
                pst.setString(5, (String) jComboBox3.getSelectedItem());
                pst.setString(6, jTextField19.getText());
                pst.setString(7, jTextField13.getText());
                pst.setString(8, jTextField14.getText());
                pst.setString(9, jTextField15.getText());
                pst.setString(10, jTextField16.getText());
                pst.setString(11, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(12, cbook_image2);
                    cbook_image2=null;
                    imagedata2=null;
                    }
                else if(abook_image2!=null){
                    pst.setBytes(12, abook_image2);
                    abook_image2=null;
                    imagedata2=null;
                    }
                else if(jLabel23!=null){
                    pst.setBytes(12, imagedata2);
                    
                }
                
                
                pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+bo+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    jTable1();
                JOptionPane.showMessageDialog(null, "Updated","Message", JOptionPane.INFORMATION_MESSAGE);
                 
                }
            }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
                
                   }
               }
               else{
                   try{
                   int p=JOptionPane.showConfirmDialog(null, "Altered data will be updated","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
                   
                    String sql2="update Books set NAME=?,AUTHOR=?,PUBLISHER=?,EDITION=?,YEAR=?,PRICE=?,PAGES=?,ISBN=?,RACK=?,STATUS=?,PHOTO=? where BOOK_ID='"+value1+"' ";
                    pst=conn.prepareStatement(sql2);
                    
                pst.setString(1, jTextField9.getText());
                pst.setString(2, jTextField10.getText());
                pst.setString(3, jTextField18.getText());
                pst.setString(4, (String) jComboBox3.getSelectedItem());
                pst.setString(5, jTextField19.getText());
                pst.setString(6, jTextField13.getText());
                pst.setString(7, jTextField14.getText());
                pst.setString(8, jTextField15.getText());
                pst.setString(9, jTextField16.getText());
                pst.setString(10, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(11, cbook_image2);
                    imagedata2=null;
                    }
                else if(abook_image2!=null){
                    pst.setBytes(11, abook_image2);
                    imagedata2=null;
                    }
                else if(jLabel23!=null){
                    pst.setBytes(11, imagedata2);
                }
                
                
                    pst.execute();

                
            
             
            String sql3="insert into Books_Update(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
                pst=conn.prepareStatement(sql3);
                pst.setString(1, jTextField20.getText());
                pst.setString(2, jTextField9.getText());
                pst.setString(3, jTextField10.getText());
                pst.setString(4, jTextField18.getText());
                pst.setString(5, (String) jComboBox3.getSelectedItem());
                pst.setString(6, jTextField19.getText());
                pst.setString(7, jTextField13.getText());
                pst.setString(8, jTextField14.getText());
                pst.setString(9, jTextField15.getText());
                pst.setString(10, jTextField16.getText());
                pst.setString(11, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(12, cbook_image2);
                    cbook_image2=null;
                    imagedata2=null;
                    }
                else if(abook_image2!=null){
                    pst.setBytes(12, abook_image2);
                    abook_image2=null;
                    imagedata2=null;
                    }
                else if(jLabel23!=null){
                    pst.setBytes(12, imagedata2);
                    
                }
                
                
                pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+bo+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                     jTable1();
                JOptionPane.showMessageDialog(null, "Updated","Message", JOptionPane.INFORMATION_MESSAGE);
               
                }
                 }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
                
               }
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Book ID does not exists","Error", JOptionPane.ERROR_MESSAGE);
            }
            }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }finally{
                try{
                    rs.close();
                    pst.close();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
            
           
        }
        }
         else if(x.equalsIgnoreCase(ub)){
             String e=  jComboBox3.getSelectedItem().toString();
        String s=  jComboBox4.getSelectedItem().toString();
        String n="NONE";
        String is="ISSUED";
      int ro= jTable1.getSelectedRow();
          int no= jTable1.getRowCount();
         
               if(no==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }
               else if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
               else if(jTextField9.getText().trim().isEmpty() || jTextField10.getText().trim().isEmpty()
            || jTextField13.getText().trim().isEmpty() || jTextField14.getText().trim().isEmpty()
            || jTextField15.getText().trim().isEmpty() || jTextField16.getText().trim().isEmpty()
            || jTextField18.getText().trim().isEmpty() || jTextField19.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Fields cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(e.equals(n) && s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select Edition and Status","Error", JOptionPane.ERROR_MESSAGE);
        }
else if(e.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select an Edition","Error", JOptionPane.ERROR_MESSAGE);
        }
else if(s.equals(n)){
            JOptionPane.showMessageDialog(null, "Please select a Status","Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
      int p=JOptionPane.showConfirmDialog(null, "Altered data will be updated","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                if(p==0){
            try{
                int[] row = jTable1.getSelectedRows();
       for (int i = 0; i < row.length; i++) {
            String id = jTable1.getModel().getValueAt(row[i], 0).toString();
       
                 String sql="select * from Books where BOOK_ID='"+id+"'";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){             
               st=rs.getString("STATUS");
               if(!st.equalsIgnoreCase(is)){
                   if(s.equals(is)){
                       JOptionPane.showMessageDialog(null, "Book status cannot be change to ISSUED","Error", JOptionPane.ERROR_MESSAGE);
                   }else{
                    try{
                    String sql2="update Books set NAME=?,AUTHOR=?,PUBLISHER=?,EDITION=?,YEAR=?,PRICE=?,PAGES=?,ISBN=?,RACK=?,STATUS=?,PHOTO=? where BOOK_ID='"+id+"' ";
                    pst=conn.prepareStatement(sql2);
                    
                pst.setString(1, jTextField9.getText());
                pst.setString(2, jTextField10.getText());
                pst.setString(3, jTextField18.getText());
                pst.setString(4, (String) jComboBox3.getSelectedItem());
                pst.setString(5, jTextField19.getText());
                pst.setString(6, jTextField13.getText());
                pst.setString(7, jTextField14.getText());
                pst.setString(8, jTextField15.getText());
                pst.setString(9, jTextField16.getText());
                pst.setString(10, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(11, cbook_image2);
                    
                    }
                else if(abook_image2!=null){
                    pst.setBytes(11, abook_image2);
                    
                    }
                else if(jLabel23!=null){
                    pst.setBytes(11, imagedata2);
                }
                
                
                    pst.execute();

                    
             
            String sql3="insert into Books_Update(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
                pst=conn.prepareStatement(sql3);
                pst.setString(1, id);
                pst.setString(2, jTextField9.getText());
                pst.setString(3, jTextField10.getText());
                pst.setString(4, jTextField18.getText());
                pst.setString(5, (String) jComboBox3.getSelectedItem());
                pst.setString(6, jTextField19.getText());
                pst.setString(7, jTextField13.getText());
                pst.setString(8, jTextField14.getText());
                pst.setString(9, jTextField15.getText());
                pst.setString(10, jTextField16.getText());
                pst.setString(11, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(12, cbook_image2);
                   
                    }
                else if(abook_image2!=null){
                    pst.setBytes(12, abook_image2);
                   
                    }
                else if(jLabel23!=null){
                    pst.setBytes(12, imagedata2);
                    
                }
                
                
                pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+bo+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
       }
      
                 
                }
               
               
               
               else{
                 try{
                    String sql2="update Books set NAME=?,AUTHOR=?,PUBLISHER=?,EDITION=?,YEAR=?,PRICE=?,PAGES=?,ISBN=?,RACK=?,STATUS=?,PHOTO=? where BOOK_ID='"+id+"' ";
                    pst=conn.prepareStatement(sql2);
                    
                pst.setString(1, jTextField9.getText());
                pst.setString(2, jTextField10.getText());
                pst.setString(3, jTextField18.getText());
                pst.setString(4, (String) jComboBox3.getSelectedItem());
                pst.setString(5, jTextField19.getText());
                pst.setString(6, jTextField13.getText());
                pst.setString(7, jTextField14.getText());
                pst.setString(8, jTextField15.getText());
                pst.setString(9, jTextField16.getText());
                pst.setString(10, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(11, cbook_image2);
                   
                    }
                else if(abook_image2!=null){
                    pst.setBytes(11, abook_image2);
                    
                    }
                else if(jLabel23!=null){
                    pst.setBytes(11, imagedata2);
                }
                
                
                    pst.execute();

                    
             
            String sql3="insert into Books_Update(BOOK_ID,NAME,AUTHOR,PUBLISHER,EDITION,YEAR,PRICE,PAGES,ISBN,RACK,STATUS,PHOTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
                pst=conn.prepareStatement(sql3);
                pst.setString(1, id);
                pst.setString(2, jTextField9.getText());
                pst.setString(3, jTextField10.getText());
                pst.setString(4, jTextField18.getText());
                pst.setString(5, (String) jComboBox3.getSelectedItem());
                pst.setString(6, jTextField19.getText());
                pst.setString(7, jTextField13.getText());
                pst.setString(8, jTextField14.getText());
                pst.setString(9, jTextField15.getText());
                pst.setString(10, jTextField16.getText());
                pst.setString(11, (String) jComboBox4.getSelectedItem());
                if(cbook_image2!=null){
                    pst.setBytes(12, cbook_image2);
                    
                    }
                else if(abook_image2!=null){
                    pst.setBytes(12, abook_image2);
                   
                    }
                else if(jLabel23!=null){
                    pst.setBytes(12, imagedata2);
                    
                }
                
                
                pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+bo+"','"+aut+"','"+actions+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
                    }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
       }
            }     
              else{
                JOptionPane.showMessageDialog(null, "Book ID does not exists","Error", JOptionPane.ERROR_MESSAGE);
            }  
            }
            
            }catch(MySQLIntegrityConstraintViolationException ex){
    JOptionPane.showMessageDialog(null, "Please select an image file","Error", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }finally{
                try{
                    rs.close();
                    pst.close();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
            jTable1();
             cbook_image2=null;
              abook_image2=null;
                    imagedata2=null;
            
                
        
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField7.setOpaque(false);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pages.png"))); // NOI18N
        jLabel6.setText("PAGES");

        jTextField12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField12.setOpaque(false);
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/publisher.png"))); // NOI18N
        jLabel25.setText("PUBLISHER");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fine.png"))); // NOI18N
        jLabel5.setText("PRICE");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel11.setText("STATUS");

        jTextField17.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField17.setOpaque(false);
        jTextField17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField17MouseEntered(evt);
            }
        });
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField17KeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/attach.png"))); // NOI18N
        jButton3.setText("Attach");
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

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/name.png"))); // NOI18N
        jLabel2.setText("NAME");

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
        });

        jComboBox2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox2.setMaximumRowCount(5);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "AVAILABLE", "UNAVAILABLE" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.setOpaque(false);
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bid.png"))); // NOI18N
        jLabel1.setText("BOOK ID");

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField1.setOpaque(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField3.setOpaque(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
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

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rack.png"))); // NOI18N
        jLabel10.setText("RACK");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edition.png"))); // NOI18N
        jLabel3.setText("EDITION");

        jButton5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camera.png"))); // NOI18N
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

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("BOOK INFO");

        jTextField4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField4.setOpaque(false);
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField4MouseEntered(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel13.setText("PHOTO");

        jTextField2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField2.setOpaque(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        jButton2.setText("Add");
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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/author.png"))); // NOI18N
        jLabel4.setText("AUTHOR");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel9.setFocusable(false);
        jLabel9.setOpaque(true);

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

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/year.png"))); // NOI18N
        jLabel26.setText("YEAR");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/isbn.png"))); // NOI18N
        jLabel8.setText("ISBN");

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(5);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "REVISED", "FIRST", "SECOND", "THIRD", "FOURTH", "FIFTH", "SIXTH", "SEVENTH", "EIGHTH", "NINETH", "TENTH", "ELEVENTH", "TWELFTH", "THIRTEENTH", "FOURTHEENTH", "FIFTEENTH", "SIXTEENTH", "SEVENTEENTH", "EIGHTEENTH", "NINETEENTH", "TWENTIETH" }));
        jComboBox1.setAutoscrolls(true);
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.setOpaque(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField5.setOpaque(false);
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField5MouseEntered(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bsearch.png"))); // NOI18N
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

        jButton12.setFont(new java.awt.Font("Californian FB", 0, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jButton12.setText(" ");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.setIconTextGap(0);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel25))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel26))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(71, 71, 71))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(60, 60, 60))))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jTextField1, jTextField12, jTextField17, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel13, jLabel2, jLabel25, jLabel26, jLabel3, jLabel4, jLabel5, jLabel6, jLabel8});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton12, jButton5});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton6});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2, jTextField1, jTextField12, jTextField17, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton12, jButton2, jButton3, jButton4, jButton5, jButton6});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel13, jLabel2, jLabel25, jLabel26, jLabel3, jLabel4, jLabel5, jLabel6, jLabel8});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ADD BOOKS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status.png"))); // NOI18N
        jLabel21.setText("STATUS");

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

        jComboBox3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox3.setMaximumRowCount(5);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "REVISED", "FIRST", "SECOND", "THIRD", "FOURTH", "FIFTH", "SIXTH", "SEVENTH", "EIGHTH", "NINETH", "TENTH", "ELEVENTH", "TWELFTH", "THIRTEENTH", "FOURTHEENTH", "FIFTEENTH", "SIXTEENTH", "SEVENTEENTH", "EIGHTEENTH", "NINETEENTH", "TWENTIETH" }));
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox3.setOpaque(false);
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fine.png"))); // NOI18N
        jLabel17.setText("PRICE");

        jTextField9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField9.setOpaque(false);
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel23.setOpaque(true);

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

        jComboBox4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox4.setMaximumRowCount(5);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "AVAILABLE", "UNAVAILABLE" }));
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox4.setOpaque(false);
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
            }
        });

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

        jTextField14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField14.setOpaque(false);
        jTextField14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField14MouseEntered(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bname.png"))); // NOI18N
        jLabel14.setText("NAME");

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/author.png"))); // NOI18N
        jLabel15.setText("AUTHOR");

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/isbn.png"))); // NOI18N
        jLabel19.setText("ISBN");

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/photo.png"))); // NOI18N
        jLabel22.setText("PHOTO");

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/publisher.png"))); // NOI18N
        jLabel27.setText("PUBLISHER");

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rack.png"))); // NOI18N
        jLabel20.setText("RACK");

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/year.png"))); // NOI18N
        jLabel28.setText("YEAR");

        jComboBox5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox5.setMaximumRowCount(5);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "ID", "NAME", "AUTHOR", "PUBLISHER", "ISBN", "RACK", "STATUS" }));
        jComboBox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        jButton10.setText("clear");
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jTextField18.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField18.setOpaque(false);
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField18KeyPressed(evt);
            }
        });

        jTextField13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
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

        jTextField19.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField19.setOpaque(false);
        jTextField19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField19MouseEntered(evt);
            }
        });
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
        });

        jTextField15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField15.setOpaque(false);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
        });

        jTextField16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField16.setOpaque(false);
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField10.setOpaque(false);
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pages.png"))); // NOI18N
        jLabel18.setText("PAGES");

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edition.png"))); // NOI18N
        jLabel16.setText("EDITION");

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bid.png"))); // NOI18N
        jLabel29.setText("BOOK ID");

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField20.setFocusable(false);
        jTextField20.setOpaque(false);
        jTextField20.setRequestFocusEnabled(false);

        jButton16.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camera.png"))); // NOI18N
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

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
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

        jComboBox7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jComboBox7.setMaximumRowCount(2);
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search by", "Bulk update" }));
        jComboBox7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox7MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBox7MousePressed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)
                        .addComponent(jButton9)
                        .addGap(205, 205, 205)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField16))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField15))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField14))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(77, 77, 77))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel27, jLabel28});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox3, jComboBox4, jTextField10, jTextField13, jTextField14, jTextField15, jTextField16, jTextField18, jTextField19, jTextField9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField20, jTextField8});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton16, jButton17});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton8, jButton9});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton10)
                        .addComponent(jButton9)
                        .addComponent(jButton8))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel27, jLabel28});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton8, jButton9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox3, jComboBox4, jTextField10, jTextField13, jTextField14, jTextField15, jTextField16, jTextField18, jTextField19, jTextField20, jTextField8, jTextField9});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton16, jButton17});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox7, jLabel24});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1358, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("UPDATE BOOKS", jPanel3);

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

        setBounds(-8, -3, 1381, 794);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        if(webcam.isOpen()){
            started=false;
            webcam.close();
            jLabel9.setIcon(null);
            jLabel23.setIcon(null);

        }
        jTable1();

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        close2();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton16KeyPressed
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
         jComboBox4.requestFocus();
        }
    }//GEN-LAST:event_jButton16KeyPressed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        Capture2();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField18.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField18.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox4.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField15.requestFocus();
        }
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField16.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField16.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField14.setEditable(true);
         jTextField14.requestFocus();
        }
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();

        if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){

            jTextField19.setEditable(true);

        }

        else{

            jTextField19.setEditable(false);

        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField13.setEditable(true);
         jTextField13.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox3.requestFocus();
        }
    }//GEN-LAST:event_jTextField19KeyPressed

    private void jTextField19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField19MouseEntered
        // TODO add your handling code here:
        jTextField19.setEditable(true);
    }//GEN-LAST:event_jTextField19MouseEntered

    private void jTextField19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19MouseClicked

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
        char c=evt.getKeyChar();

        if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){

            jTextField13.setEditable(true);

        }

        else{

            jTextField13.setEditable(false);

        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField14.setEditable(true);
         jTextField14.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
              jTextField14.setEditable(true);
         jTextField14.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField19.setEditable(true);
         jTextField19.requestFocus();
        }

    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseEntered
        // TODO add your handling code here:
        jTextField13.setEditable(true);
    }//GEN-LAST:event_jTextField13MouseEntered

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        search1();
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
 if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jTextField8KeyPressed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed

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
             jTextField19.setEditable(true);
         jTextField19.requestFocus();
        }
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        clear2();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        char c=evt.getKeyChar();

        if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){

            jTextField14.setEditable(true);

        }

        else{

            jTextField14.setEditable(false);

        }
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField15.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField15.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField13.setEditable(true);
         jTextField13.requestFocus();
        }
    }//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField14MouseEntered
        // TODO add your handling code here:
        jTextField14.setEditable(true);
    }//GEN-LAST:event_jTextField14MouseEntered

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Attach2();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
         jButton9.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton16.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox4.requestFocus();
        }
    }//GEN-LAST:event_jButton8KeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Attach2();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jButton9.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton9.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField16.requestFocus();
        }
    }//GEN-LAST:event_jComboBox4KeyPressed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed

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
         jTextField19.requestFocus();
        }
    }//GEN-LAST:event_jButton11KeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        back();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField10.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField10.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField8.requestFocus();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
    jTextField19.setEditable(true);
         jTextField19.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
              jTextField19.setEditable(true);
         jTextField19.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField18.requestFocus();
        }
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed

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
         jComboBox4.requestFocus();
        }
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Update();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_ENTER){

            tableClick();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        tableClick();
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        close1();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();

        if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){

            jTextField5.setEditable(true);

        }

        else{

            jTextField5.setEditable(false);

        }
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField6.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField6.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField4.setEditable(true);
         jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseEntered
        // TODO add your handling code here:
        jTextField5.setEditable(true);
    }//GEN-LAST:event_jTextField5MouseEntered

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
    jTextField17.setEditable(true);
         jTextField17.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
             jTextField17.setEditable(true);
         jTextField17.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            back();
        }
if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton5.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jComboBox2.requestFocus();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Add();
        }
if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton6.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton3.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             jTextField5.setEditable(true);
        jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Add();
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:

        char c=evt.getKeyChar();

        if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){

            jTextField4.setEditable(true);

        }

        else{

            jTextField4.setEditable(false);

        }
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
    jTextField5.setEditable(true);
         jTextField5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
             jTextField5.setEditable(true);
         jTextField5.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField17.setEditable(true);
         jTextField17.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseEntered
        // TODO add your handling code here:
        jTextField4.setEditable(true);
    }//GEN-LAST:event_jTextField4MouseEntered

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField4MouseClicked

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Capture1();
        }
  
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton3.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton1.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton12.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             jTextField17.setEditable(true);
        jTextField17.requestFocus();
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Capture1();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            clear1();
        }
 if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton1.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton6.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clear1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField12.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField12.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField2.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField2.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox2.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jButton5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField1.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField7.requestFocus();
        }
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jTextField7.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jTextField7.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
             jTextField5.setEditable(true);
         jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Attach1();
        }

         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton2.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton5.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             jTextField4.setEditable(true);
        jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        Attach1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();

        if(c>='0' && c<='9' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){

            jTextField17.setEditable(true);

        }

        else{

            jTextField17.setEditable(false);

        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField4.setEditable(true);
         jTextField4.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
             jTextField4.setEditable(true);
         jTextField4.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jTextField17KeyPressed

    private void jTextField17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField17MouseEntered
        // TODO add your handling code here:
        jTextField17.setEditable(true);
    }//GEN-LAST:event_jTextField17MouseEntered

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        // TODO add your handling code here:
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox2.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox2.requestFocus();
        }
       
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
           jTextField8.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jComboBox5KeyPressed

    private void jTextField18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         jComboBox3.requestFocus();
        }
          if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jComboBox3.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jTextField10.requestFocus();
        }
    }//GEN-LAST:event_jTextField18KeyPressed

    private void jButton17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton17KeyPressed
        // TODO add your handling code here:
           if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            close2();
        }
            if(evt.getKeyCode()==KeyEvent.VK_LEFT){
         jButton16.requestFocus();
        }
            if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
         jButton11.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_UP){
         jComboBox4.requestFocus();
        }
    }//GEN-LAST:event_jButton17KeyPressed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        close1();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton3.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton1.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jButton5.requestFocus();
        }
    }//GEN-LAST:event_jButton12KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             
        search();
        }
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
         jButton4.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_UP){
           jButton2.requestFocus();
        }
         if(evt.getKeyCode()==KeyEvent.VK_LEFT){
        jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jButton6KeyPressed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
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
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jComboBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox7MouseClicked
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jComboBox7MouseClicked

    private void jComboBox7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox7MousePressed
        // TODO add your handling code here:
        clear2();
    }//GEN-LAST:event_jComboBox7MousePressed

    private void jComboBox7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox7MouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jComboBox7MouseExited
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
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Books(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
 String filename=null;
byte[] abook_image=null;
byte[] cbook_image=null;
byte[] abook_image2=null;
byte[] cbook_image2=null;
private ImageIcon image1=null;
private ImageIcon image2=null;
private ImageIcon format1=null;
private ImageIcon format2=null;
private ImageIcon icon1=null;
private ImageIcon icon2=null;
byte[] imagedata1=null;
byte[] imagedata2=null;
byte[] imagedata3=null;
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";
String ed="EDITOR";
String st=null;
}
