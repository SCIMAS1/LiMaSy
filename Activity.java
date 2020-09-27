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
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author abdul
 */
public class Activity extends javax.swing.JFrame {
Connection conn;
ResultSet rs;
PreparedStatement pst;
String ac;
String aut;
    /**
     * Creates new form Activity
     * @param abc
     * @param def
     */
    public Activity(String abc, String def) {
       super("LiMaSy - Developed By IT (R16) - Activity");
        initComponents();
         setIcon();
        conn=myconnec.ConnecrDb();
        ac=abc;
        aut=def;
        jTable1();
    }
    
     private void jTable1(){
         try{
             if(aut.equals(sa)){
    String sql="SELECT * FROM Activity ORDER BY DATE DESC, TIME DESC";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }
             else{
                 jButton2.setVisible(false);
                  String sql="select * from Activity where AUTHORITY!='"+sa+"' && AUTHORITY!='"+ad+"' UNION ALL select * from Activity where USERNAME='"+ac+"' ORDER BY DATE DESC, TIME DESC";
        
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                 
             }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        }
     
     public void back(){
         String b=ac;
             String b2=aut;
          setVisible(false);
            Home ob=new Home(b,b2);
            ob.setVisible(true);
     }
     
     public void reset(){
         int n= jTable1.getRowCount();
         if(n==0){
             JOptionPane.showMessageDialog(null, "No records available","Error", JOptionPane.ERROR_MESSAGE);
         }else{
            try{
             int p=JOptionPane.showConfirmDialog(null, "Do you want to clear all records","Reset",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(p==0){
    String sql="delete from Activity";
        
            pst=conn.prepareStatement(sql);
            pst.execute();
            jTable1();
            JOptionPane.showMessageDialog(null, "All records cleared","Message", JOptionPane.INFORMATION_MESSAGE);
                }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
         }  
     }
     
     public void search(){
         
        String x=  jComboBox5.getSelectedItem().toString();
        String user=jTextField12.getText();
        String u="USERNAME";
        String a="AUTHORITY";
        String at="ACTION";
        String d="DATE";
        String t="TIME";
        String o="NONE";
        
        if(x.equals(o)){
           
            if("".equals(user)){
            String sql="select * from Activity ORDER BY DATE DESC, TIME DESC";
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
                JOptionPane.showMessageDialog(null, "Please select a valid option!");
                
            }
        }
        else if(x.equals(u)){
         
                
        
            String sql="select * from Activity where USERNAME LIKE '%"+user+"%' ORDER BY DATE DESC, TIME DESC";
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
                
        
            String sql="select * from Activity where AUTHORITY LIKE '%"+user+"%' ORDER BY DATE DESC, TIME DESC";
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
        else if(x.equals(at)){
         
               
            String sql="select * from Activity where ACTION LIKE '%"+user+"%' ORDER BY DATE DESC, TIME DESC";
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
               
            String sql="select * from Activity where DATE LIKE '%"+user+"%' ORDER BY DATE DESC, TIME DESC";
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
            
            String sql="select * from Activity where TIME LIKE '%"+user+"%' ORDER BY DATE DESC, TIME DESC";
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTextField12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField12.setOpaque(false);
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        jLabel28.setText("Search by");

        jComboBox5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox5.setMaximumRowCount(5);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "USERNAME", "AUTHORITY", "ACTION", "DATE", "TIME" }));
        jComboBox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox5, 0, 150, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset.png"))); // NOI18N
        jButton2.setText("Reset");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

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
          jButton2.requestFocus();
        }
        
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          reset();
        }
       if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
          jButton1.requestFocus();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
          jTextField12.requestFocus();
        }
    }//GEN-LAST:event_jComboBox5KeyPressed
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Activity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Activity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Activity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Activity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String m=null;
            String m2=null;
            new Activity(m,m2).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField12;
    // End of variables declaration//GEN-END:variables
String sa="SUPER ADMIN";
String ad="ADMINISTRATOR";   
}
