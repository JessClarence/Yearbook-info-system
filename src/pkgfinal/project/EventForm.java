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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import static pkgfinal.project.Dashboard.announcementBoard;


public class EventForm extends javax.swing.JFrame {

    /**
     * Creates new form EventForm
     */
    public EventForm() {
        initComponents();
        Connect();
    }

    
    Connection con;
    PreparedStatement pst;
    DefaultTableModel d;
    
    public void Connect()
    {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/yearbook_database","root", "");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EventForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);
            System.out.println("Connection Error");
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
        eventTitle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        eventDate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        eventAccomplishment = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        addEvent = new javax.swing.JButton();
        editEvent = new javax.swing.JButton();
        closeEvent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        eventDescription = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        eventReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(99, 83, 76));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(52, 86, 92));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(381, 502));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Announcement Board/Picture5.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 12, 85, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("EVENT PLANING");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 37, -1, 35));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Title of Event:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 121, -1, -1));

        eventTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eventTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventTitleActionPerformed(evt);
            }
        });
        jPanel1.add(eventTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 117, 218, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date of Event:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 166, -1, -1));

        eventDate.setForeground(new java.awt.Color(255, 255, 255));
        eventDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(eventDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 158, 166, 25));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date of Accomplishment:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 203, -1, -1));

        eventAccomplishment.setForeground(new java.awt.Color(255, 255, 255));
        eventAccomplishment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(eventAccomplishment, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 203, 160, 25));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Event Description:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 246, -1, -1));

        addEvent.setBackground(new java.awt.Color(34, 167, 240));
        addEvent.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        addEvent.setForeground(new java.awt.Color(255, 255, 255));
        addEvent.setText("Add Event");
        addEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventActionPerformed(evt);
            }
        });
        jPanel1.add(addEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        editEvent.setBackground(new java.awt.Color(153, 153, 153));
        editEvent.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        editEvent.setForeground(new java.awt.Color(255, 255, 255));
        editEvent.setText("Edit Event");
        editEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEventActionPerformed(evt);
            }
        });
        jPanel1.add(editEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, -1, -1));

        closeEvent.setBackground(new java.awt.Color(255, 51, 51));
        closeEvent.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        closeEvent.setText("X");
        closeEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeEventActionPerformed(evt);
            }
        });
        jPanel1.add(closeEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        eventDescription.setColumns(20);
        eventDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eventDescription.setRows(5);
        jScrollPane1.setViewportView(eventDescription);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 269, 298, 128));

        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel7MouseDragged(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 110));

        eventReset.setBackground(new java.awt.Color(255, 204, 0));
        eventReset.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        eventReset.setForeground(new java.awt.Color(255, 255, 255));
        eventReset.setText("Reset");
        eventReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventResetActionPerformed(evt);
            }
        });
        jPanel1.add(eventReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 462));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void eventTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventTitleActionPerformed

    private void closeEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeEventActionPerformed
        this.dispose();
       Dashboard.editEVENT.setEnabled(false);
       
    }//GEN-LAST:event_closeEventActionPerformed

    private void editEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEventActionPerformed
        
        try {
            
            d = (DefaultTableModel)announcementBoard.getModel();
            int selectIndex = announcementBoard.getSelectedRow();
            String title = d.getValueAt(selectIndex, 0).toString();
            
            pst = con.prepareStatement("UPDATE event_management SET EventTitle = ?, EventDate = ?, EventDateAccomplishment = ?, EventDescription = ? WHERE EventTitle = ?");
            pst.setString(1,eventTitle.getText());
            pst.setString(2,((JTextField)eventDate.getDateEditor().getUiComponent()).getText());
            pst.setString(3,((JTextField)eventAccomplishment.getDateEditor().getUiComponent()).getText());
            pst.setString(4,eventDescription.getText());
            pst.setString(5,title);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event Edited");
            
        eventTitle.setText(""); 
        ((JTextField)eventDate.getDateEditor().getUiComponent()).setText("");
        ((JTextField)eventAccomplishment.getDateEditor().getUiComponent()).setText("");
        eventDescription.setText("");
        
        this.dispose();
        Dashboard.editEVENT.setEnabled(false);
        Dashboard.eventRefresh.doClick();
            
        } catch (SQLException ex) {
            Logger.getLogger(PlanForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);
        }   
        
        
        
        
    }//GEN-LAST:event_editEventActionPerformed

    private void addEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventActionPerformed
      
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
         "Century Gothic", Font.BOLD, 14)));
        
        String eventDateFinal = ((JTextField)eventDate.getDateEditor().getUiComponent()).getText();
        String eventAccomplishmentFinal = ((JTextField)eventAccomplishment.getDateEditor().getUiComponent()).getText();
        
        
        if (eventTitle.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Title of Event is Empty!");
        }
        else if (eventDateFinal.isEmpty()){
            JOptionPane.showMessageDialog(null, "Date of Event is Empty!");
        }
        else if (eventAccomplishmentFinal.isEmpty()){
            JOptionPane.showMessageDialog(null, "Date of Accomplishment is Empty!");
        }
        else{
        
        int result = JOptionPane.showConfirmDialog(this, "Title Event: " + eventTitle.getText() 
                + "\nDate of Event: " + eventDateFinal 
                + "\nDate of Accomplishment: " + eventAccomplishmentFinal
                + "\nEvent Description: " + eventDescription.getText() , "Do you want to continue?", JOptionPane.YES_NO_OPTION);
         
        boolean yes, no;
        if (yes = (result == JOptionPane.YES_OPTION)){
        
        
        try {
            
            pst = con.prepareStatement("INSERT INTO event_management (EventTitle, EventDate, EventDateAccomplishment, EventDescription) VALUES (?,?,?,?)");
            pst.setString(1,eventTitle.getText());
            pst.setString(2,((JTextField)eventDate.getDateEditor().getUiComponent()).getText());
            pst.setString(3,((JTextField)eventAccomplishment.getDateEditor().getUiComponent()).getText());
            pst.setString(4,eventDescription.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event Added");
            
        eventTitle.setText(""); 
        ((JTextField)eventDate.getDateEditor().getUiComponent()).setText("");
        ((JTextField)eventAccomplishment.getDateEditor().getUiComponent()).setText("");
        eventDescription.setText("");
        
        this.dispose();
        Dashboard.eventRefresh.doClick();
        
            
        } catch (SQLException ex) {
            Logger.getLogger(PlanForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);
        }   
        
        }
        
        else if (no = (result == JOptionPane.NO_OPTION)){
            eventTitle.setText(""); 
            ((JTextField)eventDate.getDateEditor().getUiComponent()).setText("");
            ((JTextField)eventAccomplishment.getDateEditor().getUiComponent()).setText("");
            eventDescription.setText("");
        }
        
   }
    
        
    }//GEN-LAST:event_addEventActionPerformed

    private void jLabel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseDragged
       int kordinatX = evt.getXOnScreen();
       int kordinatY = evt.getYOnScreen();
        
       this.setLocation(kordinatX, kordinatY);
    }//GEN-LAST:event_jLabel7MouseDragged

    private void eventResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton addEvent;
    public static javax.swing.JButton closeEvent;
    public static javax.swing.JButton editEvent;
    public static com.toedter.calendar.JDateChooser eventAccomplishment;
    public static com.toedter.calendar.JDateChooser eventDate;
    public static javax.swing.JTextArea eventDescription;
    private javax.swing.JButton eventReset;
    public static javax.swing.JTextField eventTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
