/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


public class CourseForm extends javax.swing.JFrame {


    public CourseForm() {
        initComponents();
        Connect();
    }
    
    Connection con;
    PreparedStatement pst;
    
    public void Connect()
    {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/yearbook_database","root", "");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CourseForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addCourse = new javax.swing.JButton();
        courseReset = new javax.swing.JButton();
        course = new javax.swing.JTextField();
        courseCode = new javax.swing.JTextField();
        students = new javax.swing.JTextField();
        closeAddcourse = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(68, 66, 110));
        jPanel1.setPreferredSize(new java.awt.Dimension(326, 343));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Course Form ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Add Section Form/add (2).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 21, -1, 74));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student Course :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 36));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Batch Year:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 70, 40));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("# of Students :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 19));

        addCourse.setBackground(new java.awt.Color(34, 167, 240));
        addCourse.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        addCourse.setForeground(new java.awt.Color(255, 255, 255));
        addCourse.setText("Add");
        addCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseActionPerformed(evt);
            }
        });
        jPanel1.add(addCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 66, -1));

        courseReset.setBackground(new java.awt.Color(255, 204, 0));
        courseReset.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        courseReset.setForeground(new java.awt.Color(255, 255, 255));
        courseReset.setText("Reset");
        courseReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseResetActionPerformed(evt);
            }
        });
        jPanel1.add(courseReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        course.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseActionPerformed(evt);
            }
        });
        jPanel1.add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 150, -1));

        courseCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(courseCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 150, -1));

        students.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(students, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 150, -1));

        closeAddcourse.setBackground(new java.awt.Color(255, 51, 51));
        closeAddcourse.setForeground(new java.awt.Color(255, 255, 255));
        closeAddcourse.setText("X");
        closeAddcourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAddcourseActionPerformed(evt);
            }
        });
        jPanel1.add(closeAddcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 0, -1, -1));

        jLabel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel6MouseDragged(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 320));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseActionPerformed
        
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
         "Century Gothic", Font.BOLD, 14)));
        
        int result = JOptionPane.showConfirmDialog(this,"Section Number : " + course.getText()
                                        + "\nCourse : " + courseCode.getText()
                                        + "\nStudents : " + students.getText(),"Do you want to continue?", JOptionPane.YES_NO_OPTION);
        boolean yes, no;
        if (yes = (result == JOptionPane.YES_OPTION)){
        
        try {
            
            
            
            pst = con.prepareStatement("INSERT INTO section_management(Courses, CourseCode, Students) VALUES (?,?,?)");
            pst.setString(1, course.getText());
            pst.setString(2, courseCode.getText());
            pst.setString(3, students.getText());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Course Added");
            
        course.setText("");
        courseCode.setText("");
        students.setText("");
        
        } catch (SQLException ex) {
            Logger.getLogger(CourseForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
        
        }
        
    }//GEN-LAST:event_addCourseActionPerformed

    private void courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_courseActionPerformed

    private void courseResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseResetActionPerformed
        course.setText("");
        courseCode.setText("");
        students.setText("");
    }//GEN-LAST:event_courseResetActionPerformed

    private void closeAddcourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAddcourseActionPerformed
       this.dispose();
    }//GEN-LAST:event_closeAddcourseActionPerformed

    private void jLabel6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseDragged
       int kordinatX = evt.getXOnScreen();
       int kordinatY = evt.getYOnScreen();
        
       this.setLocation(kordinatX, kordinatY);
    }//GEN-LAST:event_jLabel6MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCourse;
    private javax.swing.JButton closeAddcourse;
    private javax.swing.JTextField course;
    private javax.swing.JTextField courseCode;
    private javax.swing.JButton courseReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField students;
    // End of variables declaration//GEN-END:variables
}