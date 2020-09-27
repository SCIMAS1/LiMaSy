/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import connect.myconnec;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public final class Requests extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
String re;
String aut;
String req;
String user;
    /**
     * Creates new form Requests
     * @param abc
     * @param def
     * @param ghi
     */
    public Requests(String abc, String def, String ghi) {
        super("LiMaSy - Developed By IT (R16) - Requests");
        initComponents();
         setIcon();
        conn=myconnec.ConnecrDb();
        re=abc;
        aut=def;
        req=ghi;
         jTable1();
         rc();
    }
    
     public void rc(){
        int c=jTable1.getRowCount();
        jLabel15.setText("NUMBER OF REQUESTS: "+c);
    }
    
    public void Accept(){
         int ro= jTable1.getSelectedRow();
        int n= jTable1.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
              if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
        try{
       int row = jTable1.getSelectedRow();
       user = jTable1.getModel().getValueAt(row, 0).toString();
        String action="Accepted signup";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
             String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+re+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
        
           String a=re,au=aut,u=user;
           setVisible(false);
            Accounts ob=new Accounts(a,au,u);
            ob.setVisible(true);
                       
        }catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Please select data to accept","Error", JOptionPane.ERROR_MESSAGE);
     } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
              }
         }
    }
    
    public void Decline(){
         int ro= jTable1.getSelectedRow();
        int n= jTable1.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
              if(ro==-1){
               JOptionPane.showMessageDialog(null, "Please select a row","Error", JOptionPane.ERROR_MESSAGE);
           }
           else{
                   int p=JOptionPane.showConfirmDialog(null, "Do you really want to decline","Decline",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
         try{
       int row = jTable1.getSelectedRow();
       String u = jTable1.getModel().getValueAt(row, 0).toString();
       String action="Declined signup";
        Date d= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        String date = sdf.format(d);
      
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
        String time = sdf1.format(d);
      
                    
        String sql = "DELETE from Requests where USERNAME='"+u+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            String sql9="Insert into Activity(USERNAME,AUTHORITY,ACTION,DATE,TIME) values('"+re+"','"+aut+"','"+action+"','"+date+"','"+time+"')";
                    pst=conn.prepareStatement(sql9);
                    pst.execute();
           jTable1();
                rc();
                                
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
         catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Please select data to decline","Error", JOptionPane.ERROR_MESSAGE);
     }
              }
              }
         }
    }
    
    private void jTable1(){
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD from Requests";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        
     public void back(){
         if(req.equalsIgnoreCase("null")){
        String b=re;
        String b2=aut;
          setVisible(false);
            Home ob=new Home(b,b2);
            ob.setVisible(true);
         }
         else if(req.equalsIgnoreCase("not null")){
             String b=re;
             String b2=aut;
             String b3="null";
          setVisible(false);
            Accounts ob=new Accounts(b,b2,b3);
            ob.setVisible(true);
         }
         }
         
     public void search2(){
         
        String x=  jComboBox3.getSelectedItem().toString();
        String emp=jTextField16.getText();
        String u="USERNAME";
        String n="NAME";
        
        String d="DEPARTMENT";
        String de="DESIGNATION";
        String o="NONE";
        
        if(x.equals(o)){
             if("".equals(emp)){
            
        try {
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD from Requests";
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
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD from Requests where USERNAME LIKE '%"+emp+"%'";
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
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD from Requests where NAME LIKE '%"+emp+"%'";
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
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD from Requests where DEPARTMENT LIKE '%"+emp+"%'";
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
            String sql = "select USERNAME,NAME,FATHER_NAME,QUALIFICATION,DEPARTMENT,DESIGNATION,PHONE_NO,EMAIL_ID,PASSWORD from Requests where DESIGNATION LIKE '%"+emp+"%'";
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField16 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/verify.png"))); // NOI18N
        jButton2.setText("Accept");
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

        jButton3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/decline.png"))); // NOI18N
        jButton3.setText("Decline");
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

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel15.setText("Row Count");

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel30.setText("Search by");

        jComboBox3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox3.setMaximumRowCount(5);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "USERNAME", "NAME", "DEPARTMENT", "DESIGNATION" }));
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jTextField16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField16.setOpaque(false);
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField16KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Accept();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Decline();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
          back();
        }
         if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jButton2.requestFocus();
        }
         
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         Accept();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jButton3.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton1.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         Decline();
        }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton2.requestFocus();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField16KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyReleased
        // TODO add your handling code here:
        search2();
    }//GEN-LAST:event_jTextField16KeyReleased

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jTextField16.requestFocus();
        }
    }//GEN-LAST:event_jComboBox3KeyPressed

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
            java.util.logging.Logger.getLogger(Requests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            String m3=null;
            new Requests(m,m2,m3).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField16;
    // End of variables declaration//GEN-END:variables
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";
}
