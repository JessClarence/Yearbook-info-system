/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.util.Date;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        Connect();
        FillComboBox();
        User_Load();
        Student_Count();
        Plan_Load();
        Event_Load();
        Claim_Load();
        Fee_Load();
        this.setLocationRelativeTo(null);
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;
    String getselectedImage;
    String id;
    String yearbookFEE;
    
   
    public void Connect()
    {
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/yearbook_database","root", "");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);
            
        }       
    }
    

    public void User_Load(){
        int c;
        try {
            pst = con.prepareStatement("SELECT StudentID, FName, MName, SName, Extensions, Course, Email, ContactNumber, BirthDate, Address, Solicitation, Quote, Graduate, Honor, Paid, Clubs, Attachment FROM student_details");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();
            
            d =  (DefaultTableModel)StudentYearbookTable.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for (int i = 1; i<=c; i++){
                    v2.add(rs.getString("StudentID"));
                    v2.add(rs.getString("FName"));
                    v2.add(rs.getString("MName"));
                    v2.add(rs.getString("SName"));
                    v2.add(rs.getString("Extensions"));
                    v2.add(rs.getString("Course"));
                    v2.add(rs.getString("Email"));
                    v2.add(rs.getString("ContactNumber"));
                    v2.add(rs.getString("BirthDate"));
                    v2.add(rs.getString("Address"));
                    v2.add(rs.getString("Solicitation"));
                    v2.add(rs.getString("Quote"));
                    v2.add(rs.getString("Graduate"));
                    v2.add(rs.getString("Honor"));
                    v2.add(rs.getString("Paid"));
                    v2.add(rs.getString("Clubs"));
                    v2.add(rs.getString("Attachment"));
                }
                
                d.addRow(v2);
                Dashboard.yearbookAdmin.setEnabled(false);
                Dashboard.positionAdmin.setEnabled(false);
            }
            
        } catch (SQLException ex) {
             Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   public void Student_Count(){
       
       try {
        pst = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM student_details");
        rs = pst.executeQuery();
        
        rs.next();
        int count = rs.getInt("rowcount");
        
        rs.close();
        
        ENROLLED.setText(String.valueOf(count));
        
       
       } catch (SQLException ex) {
           Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
       
        try {
        pst = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM student_details WHERE Graduate = 'YES'");
        rs = pst.executeQuery();
        
        rs.next();
        int count = rs.getInt("rowcount");
        
        rs.close();
        
        GRADUATES.setText(String.valueOf(count));
        
       } catch (SQLException ex) {
           Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
       }
        
       try {
        pst = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM student_details WHERE Honor = 'YES'");
        rs = pst.executeQuery();
        
        rs.next();
        int count = rs.getInt("rowcount");
        
        rs.close();
        
        HONORS.setText(String.valueOf(count));
        
       } catch (SQLException ex) {
           Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
       }
    
       try {
        pst = con.prepareStatement("SELECT COUNT(*) AS rowcount FROM student_details WHERE Paid = 'YES'");
        rs = pst.executeQuery();
        
        rs.next();
        int count = rs.getInt("rowcount");
        
        rs.close();
        
        PAID.setText(String.valueOf(count));
        
       } catch (SQLException ex) {
           Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
       }
 
   }
   
   
        
   public void Plan_Load(){
        int c;
        Dashboard.editplanButton.setEnabled(false);
        Dashboard.deleteplanButton.setEnabled(false);
        
        try {
            pst = con.prepareStatement("SELECT Title, DatePlanned, DateAccomplishment, PlanDescription, Attachment FROM plan_management");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();
            d = (DefaultTableModel)planTable.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=c; i++){
                    
                    v2.add(rs.getString("Title"));
                    v2.add(rs.getString("DatePlanned"));
                    v2.add(rs.getString("DateAccomplishment"));
                    v2.add(rs.getString("PlanDescription"));
                    v2.add(rs.getString("Attachment"));
                    
                    
                }
                
                d.addRow(v2);
            
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
            
    } 
   
    public void Event_Load(){
        int c;
        editEVENT.setEnabled(false);
        deleteEVENT.setEnabled(false);
        
        try {
            pst = con.prepareStatement("SELECT EventTitle, EventDate, EventDateAccomplishment, EventDescription FROM event_management ");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();
            d = (DefaultTableModel)announcementBoard.getModel();
            d.setRowCount(0);
            
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=c; i++){
                    v2.add(rs.getString("EventTitle"));
                    v2.add(rs.getString("EventDate"));
                    v2.add(rs.getString("EventDateAccomplishment"));
                    v2.add(rs.getString("EventDescription"));
                }
                
                d.addRow(v2);
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
     public void Claim_Load(){
        int c;
        printButton.setEnabled(false);
        try {
            pst = con.prepareStatement("SELECT StudentID, FULLNAME, Eligibility, ClaimStatus FROM student_details");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();
            d = (DefaultTableModel)Claiming_Area.getModel();
            d.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=c; i++){
                    
                    v2.add(rs.getString("StudentID"));
                    v2.add(rs.getString("FULLNAME"));
                    v2.add(rs.getString("Eligibility"));    
                    v2.add(rs.getString("ClaimStatus"));
                }
                
                d.addRow(v2);
            
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
            
    } 
     
  public void Fee_Load(){
      yearbookfee.setText(null);
      
      try{
       pst = con.prepareStatement("SELECT * FROM yearbook_fee");
       rs = pst.executeQuery();
       
       while(rs.next()){

      yearbookfee.setText(rs.getString("YearbookFee"));
      
       }
       
    
       } catch(SQLException ex){
         Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
       }
      
  }
     
   public void FillComboBox(){
       

       try{
       pst = con.prepareStatement("SELECT * FROM section_management");
       rs = pst.executeQuery();
       
       int itemCount = studentCourse.getItemCount();

        for(int i=1;i<itemCount;i++){
            studentCourse.removeItemAt(1);
         }  
       
       
       int itemCount2 = selectCourse.getItemCount();

        for(int i=1;i<itemCount2;i++){
            selectCourse.removeItemAt(1);
         } 
        
 
       while(rs.next()){

           studentCourse.addItem(rs.getString("Courses"));
           selectCourse.addItem(rs.getString("Courses"));
      
       }
  
       } catch(SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void SearchClaim(String str){
       d = (DefaultTableModel)Claiming_Area.getModel();
       TableRowSorter<DefaultTableModel> search = new TableRowSorter<>(d);
       Claiming_Area.setRowSorter(search);
       search.setRowFilter(RowFilter.regexFilter(str));
   }
   
     public void SearchStudent(String str){
       d = (DefaultTableModel)StudentYearbookTable.getModel();
       TableRowSorter<DefaultTableModel> search = new TableRowSorter<>(d);
       StudentYearbookTable.setRowSorter(search);
       search.setRowFilter(RowFilter.regexFilter(str));
   }
      
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        yearbookAdmin = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        positionAdmin = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        studentEnrolled = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addAdmin = new javax.swing.JButton();
        HONORS = new javax.swing.JLabel();
        ENROLLED = new javax.swing.JLabel();
        PAID = new javax.swing.JLabel();
        GRADUATES = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Yellow = new javax.swing.JLabel();
        Green = new javax.swing.JLabel();
        Red = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selectProfile = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        JLabelPicture = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Mname = new javax.swing.JTextField();
        Sname = new javax.swing.JTextField();
        Extensions = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        studentIDNum = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        studentEmail = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        studentNum = new javax.swing.JTextField();
        studentGraduated = new javax.swing.JComboBox<>();
        studentHonor = new javax.swing.JComboBox<>();
        studentPaid = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentQuote = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        addStudent = new javax.swing.JButton();
        clearForm = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        studentAddress = new javax.swing.JTextField();
        studentSoli = new javax.swing.JTextField();
        birthdate = new com.toedter.calendar.JDateChooser();
        studentCourse = new javax.swing.JComboBox<>();
        Fname = new javax.swing.JTextField();
        studentClubs = new javax.swing.JTextField();
        addClub = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        addPlan = new javax.swing.JButton();
        deleteplanButton = new javax.swing.JButton();
        editplanButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        planTable = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        planRefresh = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addEvent = new javax.swing.JButton();
        editEVENT = new javax.swing.JButton();
        deleteEVENT = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        announcementBoard = new javax.swing.JTable();
        eventRefresh = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        selectCourse = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        StudentYearbookTable = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        updateStudent = new javax.swing.JButton();
        addSection = new javax.swing.JButton();
        deleteStudent = new javax.swing.JButton();
        studentRefresh = new javax.swing.JButton();
        searchStudentButton = new javax.swing.JButton();
        searchStudent = new javax.swing.JTextField();
        courseRefresh = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        claimSearchText = new javax.swing.JTextField();
        SearchClaimButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Claiming_Area = new javax.swing.JTable();
        eligibleButton = new javax.swing.JButton();
        claimedButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        claimRefresh = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        yearbookfee = new javax.swing.JTextField();

        jTextField3.setText("jTextField3");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton3.setText("jButton2");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Address:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(209, 209, 209));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(29, 27, 27));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/profile (1).png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 41, -1, 165));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NAME:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 224, -1, -1));

        yearbookAdmin.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        yearbookAdmin.setForeground(new java.awt.Color(255, 255, 255));
        yearbookAdmin.setBorder(null);
        yearbookAdmin.setOpaque(false);
        yearbookAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearbookAdminActionPerformed(evt);
            }
        });
        jPanel1.add(yearbookAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 251, 280, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 278, 280, 10));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("POSITION:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 294, -1, -1));

        positionAdmin.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        positionAdmin.setForeground(new java.awt.Color(255, 255, 255));
        positionAdmin.setBorder(null);
        positionAdmin.setOpaque(false);
        positionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionAdminActionPerformed(evt);
            }
        });
        jPanel1.add(positionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 317, 282, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 344, 282, 10));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/graduation-hat.png"))); // NOI18N
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, -1, -1));

        studentEnrolled.setBackground(new java.awt.Color(255, 255, 255));
        studentEnrolled.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        studentEnrolled.setForeground(new java.awt.Color(255, 255, 255));
        studentEnrolled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/elearning.png"))); // NOI18N
        jPanel1.add(studentEnrolled, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/star.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/letter.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, -1));

        addAdmin.setBackground(new java.awt.Color(88, 114, 140));
        addAdmin.setForeground(new java.awt.Color(255, 255, 255));
        addAdmin.setText("Add Admin");
        addAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminActionPerformed(evt);
            }
        });
        jPanel1.add(addAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        HONORS.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        HONORS.setForeground(new java.awt.Color(255, 255, 255));
        HONORS.setText("0");
        jPanel1.add(HONORS, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 640, -1, -1));

        ENROLLED.setBackground(new java.awt.Color(222, 222, 222));
        ENROLLED.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ENROLLED.setForeground(new java.awt.Color(255, 255, 255));
        ENROLLED.setText("0");
        jPanel1.add(ENROLLED, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, -1));

        PAID.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        PAID.setForeground(new java.awt.Color(255, 255, 255));
        PAID.setText("0");
        jPanel1.add(PAID, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, -1, -1));

        GRADUATES.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        GRADUATES.setForeground(new java.awt.Color(255, 255, 255));
        GRADUATES.setText("0");
        jPanel1.add(GRADUATES, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 640, -1, -1));
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1190, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 370, 756));

        jPanel4.setBackground(new java.awt.Color(209, 209, 209));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Yellow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                YellowMouseClicked(evt);
            }
        });
        Yellow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                YellowKeyPressed(evt);
            }
        });
        jPanel4.add(Yellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 20, 30));

        Green.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GreenMouseClicked(evt);
            }
        });
        Green.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GreenKeyPressed(evt);
            }
        });
        jPanel4.add(Green, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 20, 30));

        Red.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RedMouseClicked(evt);
            }
        });
        Red.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RedKeyPressed(evt);
            }
        });
        jPanel4.add(Red, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 30, 30));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Macbook Design Inspired/Untitled design (24).png"))); // NOI18N
        jLabel37.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel37MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel37MouseMoved(evt);
            }
        });
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 970, 50));

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PROFILE");

        selectProfile.setBackground(new java.awt.Color(255, 204, 0));
        selectProfile.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        selectProfile.setForeground(new java.awt.Color(255, 255, 255));
        selectProfile.setText("Select Profile");
        selectProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectProfileActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("First Name     :");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Middle Name :");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Surname:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Extensions:");

        Mname.setBackground(new java.awt.Color(108, 122, 137));
        Mname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mname.setForeground(new java.awt.Color(255, 255, 255));

        Sname.setBackground(new java.awt.Color(108, 122, 137));
        Sname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sname.setForeground(new java.awt.Color(255, 255, 255));
        Sname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SnameActionPerformed(evt);
            }
        });

        Extensions.setBackground(new java.awt.Color(108, 122, 137));
        Extensions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Extensions.setForeground(new java.awt.Color(255, 255, 255));
        Extensions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtensionsActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ID Number: ");

        studentIDNum.setBackground(new java.awt.Color(108, 122, 137));
        studentIDNum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentIDNum.setForeground(new java.awt.Color(255, 255, 255));
        studentIDNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIDNumActionPerformed(evt);
            }
        });
        studentIDNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentIDNumKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Student Course:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Email:");

        studentEmail.setBackground(new java.awt.Color(108, 122, 137));
        studentEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentEmail.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Contact Number: ");

        studentNum.setBackground(new java.awt.Color(108, 122, 137));
        studentNum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentNum.setForeground(new java.awt.Color(255, 255, 255));
        studentNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentNumKeyTyped(evt);
            }
        });

        studentGraduated.setBackground(new java.awt.Color(108, 122, 137));
        studentGraduated.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        studentGraduated.setForeground(new java.awt.Color(255, 255, 255));
        studentGraduated.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TBD", "YES", "NO" }));
        studentGraduated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentGraduatedActionPerformed(evt);
            }
        });

        studentHonor.setBackground(new java.awt.Color(108, 122, 137));
        studentHonor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        studentHonor.setForeground(new java.awt.Color(255, 255, 255));
        studentHonor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TBD", "YES", "NO" }));

        studentPaid.setBackground(new java.awt.Color(108, 122, 137));
        studentPaid.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        studentPaid.setForeground(new java.awt.Color(255, 255, 255));
        studentPaid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TBD", "YES", "NO" }));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GRADUATED:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Quote:");

        studentQuote.setBackground(new java.awt.Color(108, 122, 137));
        studentQuote.setColumns(20);
        studentQuote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentQuote.setForeground(new java.awt.Color(255, 255, 255));
        studentQuote.setRows(5);
        jScrollPane1.setViewportView(studentQuote);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("HONOR STUDENT: ");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("YEARBOOK PAID:");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/Picture106.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/Picture107.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Pictures/Picture108.png"))); // NOI18N

        addStudent.setBackground(new java.awt.Color(34, 167, 240));
        addStudent.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        addStudent.setForeground(new java.awt.Color(255, 255, 255));
        addStudent.setText("Add Student ");
        addStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentActionPerformed(evt);
            }
        });

        clearForm.setBackground(new java.awt.Color(192, 57, 43));
        clearForm.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        clearForm.setForeground(new java.awt.Color(255, 255, 255));
        clearForm.setText("Clear Form");
        clearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFormActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Birthdate:");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Address:");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Solicitation:");

        studentAddress.setBackground(new java.awt.Color(108, 122, 137));
        studentAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentAddress.setForeground(new java.awt.Color(255, 255, 255));

        studentSoli.setBackground(new java.awt.Color(108, 122, 137));
        studentSoli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentSoli.setForeground(new java.awt.Color(255, 255, 255));
        studentSoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentSoliKeyTyped(evt);
            }
        });

        birthdate.setToolTipText("");
        birthdate.setDateFormatString("MM/dd/yyyy");
        birthdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        studentCourse.setBackground(new java.awt.Color(108, 122, 137));
        studentCourse.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        studentCourse.setForeground(new java.awt.Color(255, 255, 255));
        studentCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Course" }));
        studentCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentCourseActionPerformed(evt);
            }
        });

        Fname.setBackground(new java.awt.Color(108, 122, 137));
        Fname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fname.setForeground(new java.awt.Color(255, 255, 255));

        studentClubs.setBackground(new java.awt.Color(108, 122, 137));
        studentClubs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentClubs.setForeground(new java.awt.Color(255, 255, 255));

        addClub.setBackground(new java.awt.Color(102, 102, 102));
        addClub.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        addClub.setForeground(new java.awt.Color(255, 255, 255));
        addClub.setText("ADD CLUBS");
        addClub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fname, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mname, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sname, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Extensions, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(selectProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel16)
                .addGap(124, 124, 124)
                .addComponent(studentIDNum, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel25)
                .addGap(35, 35, 35)
                .addComponent(birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel17)
                .addGap(86, 86, 86)
                .addComponent(studentCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel27)
                .addGap(42, 42, 42)
                .addComponent(studentAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel18)
                .addGap(173, 173, 173)
                .addComponent(studentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(studentSoli, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(addClub, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentNum, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentClubs, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel15)
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel24)
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(78, 78, 78)
                .addComponent(studentGraduated, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel22)
                .addGap(19, 19, 19)
                .addComponent(jLabel20)
                .addGap(31, 31, 31)
                .addComponent(studentHonor, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel21)
                .addGap(40, 40, 40)
                .addComponent(studentPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(clearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(Fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(Mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(Sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(Extensions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(selectProfile)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(studentIDNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel25)
                    .addComponent(birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel17))
                    .addComponent(studentCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(studentAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel28)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentSoli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(30, 30, 30)
                        .addComponent(addClub))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(studentNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(studentClubs, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(studentGraduated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(studentHonor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(studentPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Student Registration", jPanel2);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setForeground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addPlan.setBackground(new java.awt.Color(0, 0, 0));
        addPlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Yearbook Planning/calendar.png"))); // NOI18N
        addPlan.setBorder(null);
        addPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlanActionPerformed(evt);
            }
        });
        jPanel6.add(addPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 23, 190, 179));

        deleteplanButton.setBackground(new java.awt.Color(0, 0, 0));
        deleteplanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Yearbook Planning/trash.png"))); // NOI18N
        deleteplanButton.setBorder(null);
        deleteplanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteplanButtonActionPerformed(evt);
            }
        });
        jPanel6.add(deleteplanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(653, 23, 190, 179));

        editplanButton.setBackground(new java.awt.Color(0, 0, 0));
        editplanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Yearbook Planning/system.png"))); // NOI18N
        editplanButton.setBorder(null);
        editplanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editplanButtonActionPerformed(evt);
            }
        });
        jPanel6.add(editplanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 23, 190, 179));

        planTable.setAutoCreateRowSorter(true);
        planTable.setBackground(new java.awt.Color(204, 204, 204));
        planTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        planTable.setForeground(new java.awt.Color(0, 0, 0));
        planTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title of Plan", "Date Planned", "Date of Accomplishment", "Plan Description", "Attachment"
            }
        ));
        planTable.setGridColor(new java.awt.Color(255, 255, 255));
        planTable.setIntercellSpacing(new java.awt.Dimension(5, 5));
        planTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                planTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(planTable);
        if (planTable.getColumnModel().getColumnCount() > 0) {
            planTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            planTable.getColumnModel().getColumn(1).setPreferredWidth(85);
            planTable.getColumnModel().getColumn(2).setPreferredWidth(85);
            planTable.getColumnModel().getColumn(4).setPreferredWidth(300);
        }

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 249, 807, 330));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("ADD PLAN");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 208, -1, -1));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("EDIT PLAN");
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 208, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("DELETE PLAN");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 208, -1, -1));

        planRefresh.setBackground(new java.awt.Color(0, 0, 0));
        planRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Announcement Board/refresh.png"))); // NOI18N
        planRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planRefreshActionPerformed(evt);
            }
        });
        jPanel6.add(planRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 590, 46, 42));

        jTabbedPane1.addTab("Yearbook Planning", jPanel6);

        jPanel7.setBackground(new java.awt.Color(44, 62, 80));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("C A L E N D A R ");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 56, -1, 55));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Announcement Board/Untitled design (14).png"))); // NOI18N
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 129, -1, -1));

        addEvent.setBackground(new java.awt.Color(34, 167, 240));
        addEvent.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        addEvent.setForeground(new java.awt.Color(255, 255, 255));
        addEvent.setText("Add Event");
        addEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventActionPerformed(evt);
            }
        });
        jPanel7.add(addEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 418, 201, -1));

        editEVENT.setBackground(new java.awt.Color(255, 204, 0));
        editEVENT.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        editEVENT.setForeground(new java.awt.Color(255, 255, 255));
        editEVENT.setText("Edit Event");
        editEVENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEVENTActionPerformed(evt);
            }
        });
        editEVENT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editEVENTKeyPressed(evt);
            }
        });
        jPanel7.add(editEVENT, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 485, 201, -1));

        deleteEVENT.setBackground(new java.awt.Color(192, 57, 43));
        deleteEVENT.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        deleteEVENT.setForeground(new java.awt.Color(255, 255, 255));
        deleteEVENT.setText("Delete");
        deleteEVENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEVENTActionPerformed(evt);
            }
        });
        jPanel7.add(deleteEVENT, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 554, 201, -1));

        announcementBoard.setAutoCreateRowSorter(true);
        announcementBoard.setBackground(new java.awt.Color(204, 204, 204));
        announcementBoard.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        announcementBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title of Event", "Date of Event", "Date of Accomplishment", "Event Description"
            }
        ));
        announcementBoard.setGridColor(new java.awt.Color(255, 255, 255));
        announcementBoard.setIntercellSpacing(new java.awt.Dimension(5, 5));
        announcementBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                announcementBoardMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(announcementBoard);
        if (announcementBoard.getColumnModel().getColumnCount() > 0) {
            announcementBoard.getColumnModel().getColumn(0).setResizable(false);
            announcementBoard.getColumnModel().getColumn(0).setPreferredWidth(10);
            announcementBoard.getColumnModel().getColumn(1).setResizable(false);
            announcementBoard.getColumnModel().getColumn(1).setPreferredWidth(10);
            announcementBoard.getColumnModel().getColumn(2).setResizable(false);
            announcementBoard.getColumnModel().getColumn(2).setPreferredWidth(10);
            announcementBoard.getColumnModel().getColumn(3).setResizable(false);
            announcementBoard.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 56, 602, 530));

        eventRefresh.setBackground(new java.awt.Color(0, 0, 0));
        eventRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Announcement Board/refresh.png"))); // NOI18N
        eventRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventRefreshActionPerformed(evt);
            }
        });
        jPanel7.add(eventRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 50, 41));

        jTabbedPane1.addTab("Announcement Board", jPanel7);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("LIST OF COURSES");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 30, 290, 37));

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Course Section/folder.png"))); // NOI18N
        jPanel9.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 119));

        selectCourse.setBackground(new java.awt.Color(240, 195, 60));
        selectCourse.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        selectCourse.setForeground(new java.awt.Color(255, 255, 255));
        selectCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Course" }));
        selectCourse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectCourseItemStateChanged(evt);
            }
        });
        selectCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCourseActionPerformed(evt);
            }
        });
        selectCourse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                selectCourseKeyReleased(evt);
            }
        });
        jPanel9.add(selectCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 260, -1));

        StudentYearbookTable.setAutoCreateRowSorter(true);
        StudentYearbookTable.setBackground(new java.awt.Color(204, 204, 204));
        StudentYearbookTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        StudentYearbookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Number", "First Name", "Middle Name", "Last Name", "Extensions", "Course", "Email", "Contact Number", "Birthdate", "Address", "Solicitation", "Quote", "Graduate", "Honor", "Paid", "Clubs", "Attachment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        StudentYearbookTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        StudentYearbookTable.setGridColor(new java.awt.Color(255, 255, 255));
        StudentYearbookTable.setIntercellSpacing(new java.awt.Dimension(5, 5));
        StudentYearbookTable.setMinimumSize(new java.awt.Dimension(180, 0));
        StudentYearbookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentYearbookTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(StudentYearbookTable);

        jPanel9.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 591, 520));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Course Section/real-time.png"))); // NOI18N
        jPanel9.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 77, 73));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Course Section/add-file.png"))); // NOI18N
        jPanel9.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, 73));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Course Section/delete.png"))); // NOI18N
        jPanel9.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, 80));

        updateStudent.setBackground(new java.awt.Color(255, 204, 0));
        updateStudent.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        updateStudent.setForeground(new java.awt.Color(255, 255, 255));
        updateStudent.setText("Update Student");
        updateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStudentActionPerformed(evt);
            }
        });
        jPanel9.add(updateStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, -1));

        addSection.setBackground(new java.awt.Color(34, 167, 240));
        addSection.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        addSection.setForeground(new java.awt.Color(255, 255, 255));
        addSection.setText("Add Section");
        addSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSectionActionPerformed(evt);
            }
        });
        jPanel9.add(addSection, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 130, -1));

        deleteStudent.setBackground(new java.awt.Color(192, 57, 43));
        deleteStudent.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        deleteStudent.setForeground(new java.awt.Color(255, 255, 255));
        deleteStudent.setText("Delete Student");
        deleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStudentActionPerformed(evt);
            }
        });
        jPanel9.add(deleteStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 130, -1));

        studentRefresh.setBackground(new java.awt.Color(0, 0, 0));
        studentRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Announcement Board/refresh.png"))); // NOI18N
        studentRefresh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        studentRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentRefreshActionPerformed(evt);
            }
        });
        jPanel9.add(studentRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 590, 42, 39));

        searchStudentButton.setBackground(new java.awt.Color(108, 122, 137));
        searchStudentButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        searchStudentButton.setForeground(new java.awt.Color(255, 255, 255));
        searchStudentButton.setText("Search");
        searchStudentButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        searchStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStudentButtonActionPerformed(evt);
            }
        });
        jPanel9.add(searchStudentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 80, 20));

        searchStudent.setBackground(new java.awt.Color(108, 122, 137));
        searchStudent.setForeground(new java.awt.Color(255, 255, 255));
        searchStudent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 55), 0));
        searchStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        searchStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStudentActionPerformed(evt);
            }
        });
        searchStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchStudentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStudentKeyReleased(evt);
            }
        });
        jPanel9.add(searchStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 200, 20));

        courseRefresh.setBackground(new java.awt.Color(102, 102, 102));
        courseRefresh.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        courseRefresh.setForeground(new java.awt.Color(255, 255, 255));
        courseRefresh.setText("Refresh");
        courseRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseRefreshActionPerformed(evt);
            }
        });
        jPanel9.add(courseRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jTabbedPane1.addTab("Course Sections", jPanel9);

        jPanel10.setBackground(new java.awt.Color(44, 62, 80));
        jPanel10.setFocusTraversalPolicyProvider(true);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        claimSearchText.setBackground(new java.awt.Color(108, 122, 137));
        claimSearchText.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        claimSearchText.setForeground(new java.awt.Color(255, 255, 255));
        claimSearchText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claimSearchText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));
        claimSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claimSearchTextActionPerformed(evt);
            }
        });
        claimSearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                claimSearchTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                claimSearchTextKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claimSearchTextKeyTyped(evt);
            }
        });
        jPanel10.add(claimSearchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 260, 40));

        SearchClaimButton.setBackground(new java.awt.Color(108, 122, 137));
        SearchClaimButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        SearchClaimButton.setForeground(new java.awt.Color(255, 255, 255));
        SearchClaimButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Macbook Design Inspired/search.png"))); // NOI18N
        SearchClaimButton.setText("Search...");
        SearchClaimButton.setToolTipText("");
        SearchClaimButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));
        SearchClaimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchClaimButtonActionPerformed(evt);
            }
        });
        jPanel10.add(SearchClaimButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 100, 40));

        Claiming_Area.setAutoCreateRowSorter(true);
        Claiming_Area.setBackground(new java.awt.Color(204, 204, 204));
        Claiming_Area.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Claiming_Area.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Number", "Name", "Eligibility For Claiming", "Claimed"
            }
        ));
        Claiming_Area.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Claiming_Area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Claiming_AreaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Claiming_Area);
        if (Claiming_Area.getColumnModel().getColumnCount() > 0) {
            Claiming_Area.getColumnModel().getColumn(0).setResizable(false);
            Claiming_Area.getColumnModel().getColumn(0).setPreferredWidth(10);
            Claiming_Area.getColumnModel().getColumn(1).setResizable(false);
            Claiming_Area.getColumnModel().getColumn(1).setPreferredWidth(10);
            Claiming_Area.getColumnModel().getColumn(2).setResizable(false);
            Claiming_Area.getColumnModel().getColumn(2).setPreferredWidth(10);
            Claiming_Area.getColumnModel().getColumn(3).setResizable(false);
            Claiming_Area.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 890, 450));

        eligibleButton.setBackground(new java.awt.Color(0, 0, 0));
        eligibleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Macbook Design Inspired/decision (2).png"))); // NOI18N
        eligibleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eligibleButtonActionPerformed(evt);
            }
        });
        jPanel10.add(eligibleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, 80));

        claimedButton.setBackground(new java.awt.Color(0, 0, 0));
        claimedButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Macbook Design Inspired/ballot (1).png"))); // NOI18N
        claimedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claimedButtonActionPerformed(evt);
            }
        });
        jPanel10.add(claimedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, 80));

        printButton.setBackground(new java.awt.Color(0, 0, 0));
        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Macbook Design Inspired/printing (2).png"))); // NOI18N
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        printButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                printButtonKeyPressed(evt);
            }
        });
        jPanel10.add(printButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 100, 80));

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("ELIGIBLE FOR CLAIMING");
        jPanel10.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, 20));

        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("ALREADY CLAIMED");
        jPanel10.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText(" PRINT RECEIPT");
        jPanel10.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 100, -1));

        claimRefresh.setBackground(new java.awt.Color(0, 0, 0));
        claimRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Announcement Board/refresh.png"))); // NOI18N
        claimRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claimRefreshActionPerformed(evt);
            }
        });
        jPanel10.add(claimRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 50, 40));

        jLabel43.setFont(new java.awt.Font("Century Gothic", 3, 10)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Yearbook Fee (PHP) :");
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 610, 110, -1));

        yearbookfee.setBackground(new java.awt.Color(108, 122, 137));
        yearbookfee.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        yearbookfee.setForeground(new java.awt.Color(255, 255, 255));
        yearbookfee.setText("0");
        yearbookfee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        yearbookfee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearbookfeeActionPerformed(evt);
            }
        });
        jPanel10.add(yearbookfee, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 610, 110, -1));

        jTabbedPane1.addTab("Claiming Area", jPanel10);

        jPanel4.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 930, 680));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 0, 950, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void positionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionAdminActionPerformed

    private void yearbookAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearbookAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearbookAdminActionPerformed

    private void ExtensionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtensionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExtensionsActionPerformed

    private void SnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SnameActionPerformed

    private void selectProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectProfileActionPerformed
       
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("4 Extensions Supported", "jpg", "png", "jpeg", "gif");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(this);
        if(selected == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            getselectedImage = file.getAbsolutePath();
            //JOptionPane.showMessageDialog(null, getselectedImage);
            ImageIcon imIco = new ImageIcon(getselectedImage);
            //make image fit
            Image imFit = imIco.getImage();
            Image imgFit = imFit.getScaledInstance(JLabelPicture.getWidth(), JLabelPicture.getHeight(), Image.SCALE_SMOOTH);
            JLabelPicture.setIcon(new ImageIcon(imgFit));

        }
  
    }//GEN-LAST:event_selectProfileActionPerformed

    private void addStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentActionPerformed
        
        
        if (Fname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "First name is empty");
        }
        else if (Sname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Surname is empty");
        }
        else if (studentIDNum.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "ID Number is empty");
        }
        else if (studentCourse.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Course/Year Level is empty");
        }
        else if (studentNum.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Contact Number is empty");
        }
        else if (studentAddress.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Address is empty");
        }
        else {
        
        if(JLabelPicture.getIcon() == null){
            getselectedImage = "";
        }
                
        String birthFinal = ((JTextField)birthdate.getDateEditor().getUiComponent()).getText();
        
        
        int result = JOptionPane.showConfirmDialog(null,"Name: " + "                         " +Fname.getText() + " " + Mname.getText() + " " + Sname.getText() + " " + Extensions.getText() + "\n"
            + "Birthdate: " + "                   " + birthFinal + "\n" 
            + "Address: " + "                     " + studentAddress.getText() + "\n" 
            + "ID Number: " + "                 " + studentIDNum.getText() + "\n" 
            + "Course | Year Level: " + studentCourse.getSelectedItem() + "\n" 
            + "Email: " + "                          " + studentEmail.getText() + "\n" 
            + "Contact Number: " + "      " + studentNum.getText() + "\n"
            + "Solicitation: " + "                " + studentSoli.getText() + "\n\n" 
            + "Quote: \n" + studentQuote.getText() + "\n\n"
            + "Clubs:" + studentClubs.getText() +  "\n"       
            + "Graduated:" + "           " + studentGraduated.getSelectedItem() + "\n"
            + "Honor Student: " + "   " + studentHonor.getSelectedItem() + "\n"
            + "Yearbook Paid: " + "   " + studentPaid.getSelectedItem() +"\n"
            ," Please check if the details are correct!" , JOptionPane.YES_NO_OPTION );

        boolean yes, no;
        if (yes = (result == JOptionPane.YES_OPTION)){
        
        String Eligibility;
        String ClaimStat = "NOT CLAIMED";
    
        if (studentGraduated.getSelectedItem().toString().equals("YES") && studentPaid.getSelectedItem().toString().equals("YES")){
           Eligibility = "YES"; 
        }
        else{
           Eligibility = "NO";
        }
           
        try {
            
            pst = con.prepareStatement("INSERT INTO student_details(FName, MName, SName, Extensions, FULLNAME, StudentID, Course, Email, ContactNumber, BirthDate, Address, Solicitation, Quote, Graduate, Honor, Paid, Clubs, Attachment, ClaimStatus, Eligibility) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,Fname.getText());
            pst.setString(2,Mname.getText());
            pst.setString(3,Sname.getText()); 
            pst.setString(4,Extensions.getText());
            pst.setString(5,Sname.getText() + ", " + Fname.getText() + ", " + Mname.getText() + " " + Extensions.getText());
            pst.setString(6,(studentIDNum.getText()));
            pst.setString(7,(studentCourse.getSelectedItem().toString()));
            pst.setString(8,studentEmail.getText());
            pst.setString(9,studentNum.getText());
            pst.setString(10,((JTextField)birthdate.getDateEditor().getUiComponent()).getText());
            pst.setString(11,studentAddress.getText());
            pst.setString(12,studentSoli.getText());
            pst.setString(13,studentQuote.getText());
            pst.setString(14,(studentGraduated.getSelectedItem().toString()));
            pst.setString(15,(studentHonor.getSelectedItem().toString()));
            pst.setString(16,(studentPaid.getSelectedItem().toString()));
            pst.setString(17,studentClubs.getText());
            pst.setString(18,getselectedImage);
            pst.setString(19,ClaimStat);
            pst.setString(20,Eligibility);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Added");
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }     
            
        
   
        int FinalstatEnrolled = Integer.parseInt(ENROLLED.getText());
        int FinalstatPaid = Integer.parseInt(PAID.getText());
        int FinalstatGraduates = Integer.parseInt(GRADUATES.getText());
        int FinalstatHonors = Integer.parseInt(HONORS.getText());
        
        FinalstatEnrolled++;
        
        if(studentGraduated.getSelectedIndex() == 1){
           FinalstatGraduates++; 
        }
        if(studentPaid.getSelectedIndex() == 1){
           FinalstatPaid++; 
        }
        if(studentHonor.getSelectedIndex() == 1){
           FinalstatHonors++; 
        }
        
        
        this.ENROLLED.setText(String.valueOf(FinalstatEnrolled));    
        this.PAID.setText(String.valueOf(FinalstatPaid));  
        this.GRADUATES.setText(String.valueOf(FinalstatGraduates));  
        this.HONORS.setText(String.valueOf(FinalstatHonors)); 
        
        JLabelPicture.setIcon(null);
        Fname.setText("");
        Mname.setText("");
        Sname.setText("");
        Extensions.setText("");
        studentIDNum.setText("");
        studentCourse.setSelectedIndex(0);
        studentEmail.setText("");
        studentNum.setText("");
        birthdate.setDate(null);
        studentAddress.setText("");
        studentSoli.setText("");
        studentQuote.setText("");
        studentClubs.setText("");
        studentGraduated.setSelectedIndex(0);
        studentHonor.setSelectedIndex(0);
        studentPaid.setSelectedIndex(0);  
        
        User_Load();
        Claim_Load();
        Student_Count();
        }
        else if (no = (result == JOptionPane.NO_OPTION)){
            
        }
            
        }


        
        
        
        
    }//GEN-LAST:event_addStudentActionPerformed

    private void addPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlanActionPerformed

       PlanForm open = new PlanForm();
       open.editPlan.setEnabled(false);
       open.setVisible(true);
       
       
    }//GEN-LAST:event_addPlanActionPerformed

    private void addEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventActionPerformed
       
       EventForm open = new EventForm();
       open.setVisible(true);
       
       EventForm.editEvent.setEnabled(false);
       

    }//GEN-LAST:event_addEventActionPerformed

    private void editplanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editplanButtonActionPerformed
        
        
        try {
            
            
            d = (DefaultTableModel)planTable.getModel();
            int selectIndex = planTable.getSelectedRow();
            String title = d.getValueAt(selectIndex, 0).toString();
            
            pst = con.prepareStatement("UPDATE plan_management SET Title = ?, DatePlanned = ?, DateAccomplishment = ?, PlanDescription = ?, Attachment = ? WHERE Title = ?");
            pst.setString(1,PlanForm.planTitle.getText());
            pst.setString(2,((JTextField)PlanForm.planDate.getDateEditor().getUiComponent()).getText());
            pst.setString(3,((JTextField)PlanForm.planAccomplishment.getDateEditor().getUiComponent()).getText());
            pst.setString(4,PlanForm.planDescription.getText());
            pst.setString(5,PlanForm.filePath.getText());
            pst.setString(6,title);
            pst.executeUpdate();
            System.out.println(PlanForm.planDescription.getText());
            JOptionPane.showMessageDialog(this, "Plan Edited");
            
            
            PlanForm.planTitle.setText(""); 
            ((JTextField)PlanForm.planDate.getDateEditor().getUiComponent()).setText("");
            ((JTextField)PlanForm.planAccomplishment.getDateEditor().getUiComponent()).setText("");
            PlanForm.planDescription.setText("");
            PlanForm.filePath.setText("");
            
            Plan_Load();
            PlanForm.closePlan.doClick();

            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        } 
    
    }//GEN-LAST:event_editplanButtonActionPerformed

    private void addAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminActionPerformed
       AdminForm open = new AdminForm();
       open.setVisible(true);
    }//GEN-LAST:event_addAdminActionPerformed

    private void studentIDNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentIDNumKeyTyped
        
        char a = evt.getKeyChar();
        
        if (!Character.isDigit(a)){
            evt.consume();
        }
    }//GEN-LAST:event_studentIDNumKeyTyped

    private void studentNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentNumKeyTyped
        
        
        char b = evt.getKeyChar();
        
        if (!Character.isDigit(b)){
            evt.consume();
        }
    }//GEN-LAST:event_studentNumKeyTyped

    private void studentSoliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentSoliKeyTyped
        
        char c = evt.getKeyChar();
        
        if (!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_studentSoliKeyTyped

    private void clearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFormActionPerformed
        
        JLabelPicture.setIcon(null);
        Fname.setText("");
        Mname.setText("");
        Sname.setText("");
        Extensions.setText("");
        studentIDNum.setText("");
        studentCourse.setSelectedIndex(0);
        studentEmail.setText("");
        studentNum.setText("");
        birthdate.setDate(null);
        studentAddress.setText("");
        studentSoli.setText("");
        studentQuote.setText("");
        studentClubs.setText("");
        studentGraduated.setSelectedIndex(0);
        studentHonor.setSelectedIndex(0);
        studentPaid.setSelectedIndex(0);
        
    }//GEN-LAST:event_clearFormActionPerformed

    private void updateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStudentActionPerformed
        
        if(pkgfinal.project.EditForm.fname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "First Name is Empty!");
        }
        else if(pkgfinal.project.EditForm.sname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Surname is Empty!");
        }
        else if (pkgfinal.project.EditForm.idnumber.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "ID Number is Empty!");
        }
        else if (pkgfinal.project.EditForm.email.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Email is Empty!");
        }
        else if (pkgfinal.project.EditForm.mobilenum.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Mobile Number is Empty!");
        }
        else if (pkgfinal.project.EditForm.address.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Address is Empty!");
        }
        else{
            
        try {
            d = (DefaultTableModel)StudentYearbookTable.getModel();
            int selectIndex = StudentYearbookTable.getSelectedRow();
      
            String SelectedID = d.getValueAt(selectIndex, 0).toString();
            System.out.println(getselectedImage);
            
            pst = con.prepareStatement("UPDATE student_details SET FName = ?, MName = ?, SName = ?, Extensions = ?, StudentID = ?, Course = ?, Email = ?, ContactNumber = ?, BirthDate = ?, Address = ?, Solicitation = ?, Quote = ?, Clubs = ?, Graduate = ?, Honor = ?, Paid = ?, Attachment = ?, Eligibility = ? WHERE StudentID = ?");
            pst.setString(1,EditForm.fname.getText());
            pst.setString(2,EditForm.mname.getText());
            pst.setString(3,EditForm.sname.getText());
            pst.setString(4,EditForm.extension.getText());
            pst.setString(5,EditForm.idnumber.getText());
            pst.setString(6,(EditForm.yearlevel.getSelectedItem().toString()));
            pst.setString(7,EditForm.email.getText());
            pst.setString(8,EditForm.mobilenum.getText());
            pst.setString(9,((JTextField)EditForm.BIRTHdate.getDateEditor().getUiComponent()).getText());
            pst.setString(10,EditForm.address.getText());
            pst.setString(11,EditForm.soli.getText());
            pst.setString(12,EditForm.qt.getText());
            pst.setString(13,EditForm.editClubs.getText());
            pst.setString(14,(EditForm.graduated.getSelectedItem().toString()));
            pst.setString(15,(EditForm.HonorStudent.getSelectedItem().toString()));
            pst.setString(16,(EditForm.YearbookPaid.getSelectedItem().toString()));
            pst.setString(17,EditForm.FILEPATH.getText());
            
            String Eligibility;
            if (EditForm.graduated.getSelectedItem().toString().equals("YES") && EditForm.YearbookPaid.getSelectedItem().toString().equals("YES")){
                 Eligibility = "YES";
            }
            else{
                 Eligibility = "NO";
            }

            pst.setString(18,Eligibility);
            pst.setString(19,SelectedID);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Edited");
            
            EditForm.closeEditForm.doClick();
            claimRefresh.doClick();
            User_Load();
            Student_Count();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }  
       
        }  
        
    }//GEN-LAST:event_updateStudentActionPerformed

    private void addSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSectionActionPerformed
     SectionForm open = new SectionForm();
     open.setVisible(true);
    }//GEN-LAST:event_addSectionActionPerformed

    private void StudentYearbookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentYearbookTableMouseClicked
       
       EditForm open = new EditForm();
       open.setVisible(true);
       
       d = (DefaultTableModel)StudentYearbookTable.getModel();
       int selectIndex = StudentYearbookTable.getSelectedRow();
       
       id = d.getValueAt(selectIndex, 0).toString();
       
       EditForm.idnumber.setText(d.getValueAt(selectIndex, 0).toString());  
       EditForm.fname.setText(d.getValueAt(selectIndex, 1).toString());
       EditForm.mname.setText(d.getValueAt(selectIndex, 2).toString());
       EditForm.sname.setText(d.getValueAt(selectIndex, 3).toString());
       EditForm.extension.setText(d.getValueAt(selectIndex, 4).toString());
       EditForm.yearlevel.setSelectedItem(d.getValueAt(selectIndex, 5).toString());
       EditForm.email.setText(d.getValueAt(selectIndex, 6).toString());
       EditForm.mobilenum.setText(d.getValueAt(selectIndex, 7).toString());
       ((JTextField)EditForm.BIRTHdate.getDateEditor().getUiComponent()).setText(d.getValueAt(selectIndex, 8).toString());
       EditForm.address.setText(d.getValueAt(selectIndex, 9).toString());
       EditForm.soli.setText(d.getValueAt(selectIndex, 10).toString());
       EditForm.qt.setText(d.getValueAt(selectIndex, 11).toString());
       EditForm.graduated.setSelectedItem(d.getValueAt(selectIndex, 12).toString());
       EditForm.HonorStudent.setSelectedItem(d.getValueAt(selectIndex, 13).toString());
       EditForm.YearbookPaid.setSelectedItem(d.getValueAt(selectIndex, 14).toString());
       EditForm.editClubs.setText(d.getValueAt(selectIndex, 15).toString());
       EditForm.FILEPATH.setText(d.getValueAt(selectIndex, 16).toString()); 
       
       getselectedImage = EditForm.FILEPATH.getText();
       ImageIcon imIco = new ImageIcon(getselectedImage);
       Image imFit = imIco.getImage();
       Image imgFit = imFit.getScaledInstance(EditForm.EditPic.getWidth(), EditForm.EditPic.getHeight(), Image.SCALE_SMOOTH);
       EditForm.EditPic.setIcon(new ImageIcon(imgFit));  
       
       
       
       
    }//GEN-LAST:event_StudentYearbookTableMouseClicked

    private void planTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_planTableMouseClicked
        
       editplanButton.setEnabled(true);
       deleteplanButton.setEnabled(true);
       
       PlanForm open = new PlanForm();
       PlanForm.addPlan.setEnabled(false);
    
       
       d = (DefaultTableModel)planTable.getModel();
       int selectIndex = planTable.getSelectedRow();    

       PlanForm.planTitle.setText(d.getValueAt(selectIndex, 0).toString());
       ((JTextField)PlanForm.planDate.getDateEditor().getUiComponent()).setText(d.getValueAt(selectIndex, 1).toString());
       ((JTextField)PlanForm.planAccomplishment.getDateEditor().getUiComponent()).setText(d.getValueAt(selectIndex, 2).toString());
       PlanForm.planDescription.setText(d.getValueAt(selectIndex, 3).toString());
       PlanForm.filePath.setText(d.getValueAt(selectIndex, 4).toString());
       
       open.setVisible(true);
       
       

       

    }//GEN-LAST:event_planTableMouseClicked

    private void announcementBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_announcementBoardMouseClicked
       addEvent.setEnabled(false);
       editEVENT.setEnabled(true);
       deleteEVENT.setEnabled(true); 
       
       EventForm open = new EventForm();
       EventForm.addEvent.setEnabled(false);
       
       d = (DefaultTableModel)announcementBoard.getModel();
       int selectIndex = announcementBoard.getSelectedRow();
       String title = d.getValueAt(selectIndex, 0).toString();
       

       EventForm.eventTitle.setText(d.getValueAt(selectIndex, 0).toString());
       ((JTextField)EventForm.eventDate.getDateEditor().getUiComponent()).setText(d.getValueAt(selectIndex, 1).toString());
       ((JTextField)EventForm.eventAccomplishment.getDateEditor().getUiComponent()).setText(d.getValueAt(selectIndex, 2).toString());
       EventForm.eventDescription.setText(d.getValueAt(selectIndex, 3).toString());
       
       open.setVisible(true);
    }//GEN-LAST:event_announcementBoardMouseClicked

    private void editEVENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEVENTActionPerformed
    
    try {
            
            d = (DefaultTableModel)announcementBoard.getModel();
            int selectIndex = announcementBoard.getSelectedRow();
            String title = d.getValueAt(selectIndex, 0).toString();
            
            pst = con.prepareStatement("UPDATE event_management SET EventTitle = ?, EventDate = ?, EventDateAccomplishment = ?, EventDescription = ? WHERE EventTitle = ?");
            pst.setString(1,EventForm.eventTitle.getText());
            pst.setString(2,((JTextField)EventForm.eventDate.getDateEditor().getUiComponent()).getText());
            pst.setString(3,((JTextField)EventForm.eventAccomplishment.getDateEditor().getUiComponent()).getText());
            pst.setString(4,EventForm.eventDescription.getText());
            pst.setString(5,title);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event Edited");
            
        EventForm.eventTitle.setText(""); 
        ((JTextField)EventForm.eventDate.getDateEditor().getUiComponent()).setText("");
        ((JTextField)EventForm.eventAccomplishment.getDateEditor().getUiComponent()).setText("");
        EventForm.eventDescription.setText("");
        
        Event_Load();
        
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_editEVENTActionPerformed

    private void planRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planRefreshActionPerformed
        
     Plan_Load();
    }//GEN-LAST:event_planRefreshActionPerformed

    private void deleteplanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteplanButtonActionPerformed
        
try {
            
            
            d = (DefaultTableModel)planTable.getModel();
            int selectIndex = planTable.getSelectedRow();
            String title = d.getValueAt(selectIndex, 0).toString();
            
            pst = con.prepareStatement("DELETE FROM plan_management WHERE Title = ?");
            pst.setString(1,title);
            
            pst.executeUpdate();
            System.out.println(PlanForm.planDescription.getText());
            JOptionPane.showMessageDialog(this, "Plan Deleted");
            
            
            PlanForm.planTitle.setText(""); 
            ((JTextField)PlanForm.planDate.getDateEditor().getUiComponent()).setText("");
            ((JTextField)PlanForm.planAccomplishment.getDateEditor().getUiComponent()).setText("");
            PlanForm.planDescription.setText("");
            PlanForm.filePath.setText("");
            
            Plan_Load();
            PlanForm.closePlan.doClick();
                 
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        } 

    }//GEN-LAST:event_deleteplanButtonActionPerformed

    private void eventRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventRefreshActionPerformed
      Event_Load();
    }//GEN-LAST:event_eventRefreshActionPerformed

    private void deleteEVENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEVENTActionPerformed
    
          try {
            
            d = (DefaultTableModel)announcementBoard.getModel();
            int selectIndex = announcementBoard.getSelectedRow();
            String title = d.getValueAt(selectIndex, 0).toString();
            
            pst = con.prepareStatement("DELETE FROM event_management WHERE EventTitle = ?");
            pst.setString(1,title);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event Deleted");
            
            EventForm.closeEvent.doClick();
            
        EventForm.eventTitle.setText(""); 
        ((JTextField)EventForm.eventDate.getDateEditor().getUiComponent()).setText("");
        ((JTextField)EventForm.eventAccomplishment.getDateEditor().getUiComponent()).setText("");
        EventForm.eventDescription.setText("");
        
        
        
        Event_Load();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }   
          
    }//GEN-LAST:event_deleteEVENTActionPerformed

    private void deleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStudentActionPerformed
       
    try {
            d = (DefaultTableModel)StudentYearbookTable.getModel();
            int selectIndex = StudentYearbookTable.getSelectedRow();
      
            String id = d.getValueAt(selectIndex, 0).toString();
            System.out.println(id);
            System.out.println(getselectedImage);
            
            pst = con.prepareStatement("DELETE FROM student_details WHERE StudentID = ?");
            pst.setString(1,id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Deleted");
            
            User_Load();
            Student_Count();
              
            EditForm.closeEditForm.doClick();  
            claimRefresh.doClick();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }  
        
        
    }//GEN-LAST:event_deleteStudentActionPerformed

    private void YellowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YellowKeyPressed
      
    }//GEN-LAST:event_YellowKeyPressed

    private void YellowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_YellowMouseClicked
        this.setState(1);
         System.out.println("System Minimized");
    }//GEN-LAST:event_YellowMouseClicked

    private void jLabel37MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseDragged
      int kordinatX = evt.getXOnScreen();
      int kordinatY = evt.getYOnScreen();
        
      this.setLocation(kordinatX, kordinatY);
    }//GEN-LAST:event_jLabel37MouseDragged

    private void jLabel37MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseMoved

    }//GEN-LAST:event_jLabel37MouseMoved

    private void SearchClaimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchClaimButtonActionPerformed
      SearchClaim(claimSearchText.getText());
    }//GEN-LAST:event_SearchClaimButtonActionPerformed

    private void claimSearchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claimSearchTextKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_claimSearchTextKeyPressed

    private void claimSearchTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claimSearchTextKeyTyped
        char a = evt.getKeyChar();
        
        if (!Character.isDigit(a)){
            evt.consume();
        }
    }//GEN-LAST:event_claimSearchTextKeyTyped

    private void claimSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claimSearchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claimSearchTextActionPerformed

    private void studentIDNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIDNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentIDNumActionPerformed

    private void studentCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentCourseActionPerformed

    private void claimRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claimRefreshActionPerformed
       Claim_Load();
       User_Load();
       SearchClaim("");
    }//GEN-LAST:event_claimRefreshActionPerformed

    private void searchStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStudentButtonActionPerformed
        SearchStudent(searchStudent.getText());
        
    }//GEN-LAST:event_searchStudentButtonActionPerformed

    private void searchStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStudentActionPerformed
      
    }//GEN-LAST:event_searchStudentActionPerformed

    private void searchStudentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchStudentKeyPressed

    }//GEN-LAST:event_searchStudentKeyPressed

    private void searchStudentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchStudentKeyReleased
    if (searchStudent.getText().isEmpty()){
          SearchStudent("");
        }
    }//GEN-LAST:event_searchStudentKeyReleased

    private void claimSearchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claimSearchTextKeyReleased
      if (claimSearchText.getText().isEmpty()){
          SearchClaim("");
        }
    }//GEN-LAST:event_claimSearchTextKeyReleased

    private void eligibleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eligibleButtonActionPerformed
        SearchClaim("YES");
    }//GEN-LAST:event_eligibleButtonActionPerformed

    private void claimedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claimedButtonActionPerformed
        SearchClaim("Already");
    }//GEN-LAST:event_claimedButtonActionPerformed

    private void studentRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentRefreshActionPerformed
        User_Load();
        Student_Count();
        SearchStudent("");
    }//GEN-LAST:event_studentRefreshActionPerformed

    private void courseRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseRefreshActionPerformed
   
     FillComboBox();
    }//GEN-LAST:event_courseRefreshActionPerformed

    private void selectCourseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectCourseItemStateChanged
    
    String courseSelected = selectCourse.getSelectedItem().toString();
    if (courseSelected.equals("Select Course")){
    courseSelected = "";
    }
    
    SearchStudent(courseSelected);
    }//GEN-LAST:event_selectCourseItemStateChanged

    private void selectCourseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selectCourseKeyReleased
       
    }//GEN-LAST:event_selectCourseKeyReleased

    private void selectCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCourseActionPerformed

  
    }//GEN-LAST:event_selectCourseActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
      if (PrintReceipt.MONEY.getText().equals("Not Eligible") || PrintReceipt.PAIDSTATUS.getText().equals("Not Eligible")){
          JOptionPane.showMessageDialog(null, "Not Eligible For Claiming", "Not Eligible", JOptionPane.ERROR_MESSAGE);
      }
      else{
         
           if (PrintReceipt.MONEY.getText().equals("Set Amount") || PrintReceipt.PAIDSTATUS.getText().equals("Set Amount")){
               JOptionPane.showMessageDialog(null, "Please input amount", "Error Amount", JOptionPane.ERROR_MESSAGE);
           }
           else{
               
           if (PrintReceipt.DATE.getText().equals("Set Date")){
                JOptionPane.showMessageDialog(null, "Please set a date", "No Date", JOptionPane.ERROR_MESSAGE);
           
           }
           else{
               
           int money = Integer.parseInt(PrintReceipt.MONEY.getText());
           int totaldue = Integer.parseInt(PrintReceipt.TOTALDUE.getText());
           int amountpaid = Integer.parseInt(PrintReceipt.PAIDSTATUS.getText());
           int change = 0;
           change = money - amountpaid;
           
           if (amountpaid < totaldue){
           JOptionPane.showMessageDialog(null, "Amount Paid is less than the Total Due", "Not Accepted", JOptionPane.ERROR_MESSAGE);
           }
           else{
           
           if (money < amountpaid || money < totaldue){
              JOptionPane.showMessageDialog(null, "Amount does not exceed the Due", "Not Accepted", JOptionPane.ERROR_MESSAGE);
           }
           else{
 
           PrintReceipt.CHANGE.setText(String.valueOf(change));
           
        try {
            
            pst = con.prepareStatement("INSERT INTO account_management(StudentID, StudentName, TotalDue, AmountPaid, AmountReceive, AmountChange, Date) VALUES (?,?,?,?,?,?,?)");
            pst.setString(1,PrintReceipt.IDNUM.getText());
            pst.setString(2,PrintReceipt.ISSUEDTO.getText());
            pst.setString(3,PrintReceipt.TOTALDUE.getText());
            pst.setString(4,PrintReceipt.PAIDSTATUS.getText());
            pst.setString(5,PrintReceipt.MONEY.getText());
            pst.setString(6,PrintReceipt.CHANGE.getText());
            pst.setString(7,PrintReceipt.DATE.getText());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Receipt Recorded");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }  
           
           try {
            d = (DefaultTableModel)Claiming_Area.getModel();
            int selectIndex = Claiming_Area.getSelectedRow();
      
            String SELECTEDID = d.getValueAt(selectIndex, 0).toString();

            pst = con.prepareStatement("UPDATE student_details SET ClaimStatus = ? WHERE StudentID = ?");
            pst.setString(1,"Already Claimed");
            pst.setString(2,SELECTEDID);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Successfully Claimed, Thank You!");

            User_Load();
            Claim_Load();
            
            Receipt info = new Receipt();
            info.setVisible(true);
 
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"******************************************************\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"\t    YEARBOOK PUBLICATION RECEIPT\n");                    
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"\t  UNIVERSITY OF MINDANAO, DAVAO CITY\n");                
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"\t      E-GENERATED RECEIPT\n"); 
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"******************************************************\n");
            
            Date obj = new Date();
            String date=obj.toString();
            
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"\n"+date+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"ID Number       :"+ PrintReceipt.IDNUM.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"Name            :"+ PrintReceipt.ISSUEDTO.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"Total Due       :"+ PrintReceipt.TOTALDUE.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"Amount Paid     :"+ PrintReceipt.PAIDSTATUS.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"Amount Received :"+ PrintReceipt.MONEY.getText()+"\n");
            Receipt.frameReceipt.setText(Receipt.frameReceipt.getText()+"Change          :"+ PrintReceipt.CHANGE.getText()+"\n");
  
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }  
           }
         }
        }
       }        
      }
      
    }//GEN-LAST:event_printButtonActionPerformed

    private void Claiming_AreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Claiming_AreaMouseClicked
      
    PrintReceipt open = new PrintReceipt();
    printButton.setEnabled(true);
    
       d = (DefaultTableModel)Claiming_Area.getModel();
       int selectIndex = Claiming_Area.getSelectedRow();    

       PrintReceipt.IDNUM.setText(d.getValueAt(selectIndex, 0).toString());
       PrintReceipt.ISSUEDTO.setText(d.getValueAt(selectIndex, 1).toString());
       PrintReceipt.PAIDSTATUS.setText(d.getValueAt(selectIndex, 2).toString());
       
       if(d.getValueAt(selectIndex, 2).toString().equals("NO")){
           JOptionPane.showMessageDialog(null, "Unable to Generate Receipt", "Not Eligible For Claiming", JOptionPane.INFORMATION_MESSAGE);
       }
       
       else{
           
       
       if (d.getValueAt(selectIndex, 3).toString().equals("Already Claimed")){
          JOptionPane.showMessageDialog(null, "Unable to Generate Receipt", "Already Claimed", JOptionPane.INFORMATION_MESSAGE);
       }
       else{
       
       PrintReceipt.IDNUM.setEnabled(false);
       
       
       if (PrintReceipt.PAIDSTATUS.getText().equals("NO")){
           PrintReceipt.PAIDSTATUS.setEnabled(false);
           PrintReceipt.TOTALDUE.setEnabled(false);
           
           PrintReceipt.PAIDSTATUS.setText("NOT ELIGIBLE");
           PrintReceipt.TOTALDUE.setText(yearbookFEE);
           PrintReceipt.MONEY.setText("NOT ELIGIBLE");
           
       }
       else{
           
           PrintReceipt.TOTALDUE.setText(yearbookfee.getText());
           PrintReceipt.TOTALDUE.setEnabled(false);
           PrintReceipt.PAIDSTATUS.setText("Set Amount");
           PrintReceipt.MONEY.setText("Set Amount");
           PrintReceipt.DATE.setText("Set Date");

       }
    
       open.setVisible(true);
       
      }
    }
    }//GEN-LAST:event_Claiming_AreaMouseClicked

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
    yearbookFEE = (JOptionPane.showInputDialog(null, "Yearbook Fee", "Set Fee", JOptionPane.PLAIN_MESSAGE));
        
    try {
            
            pst = con.prepareStatement("INSERT INTO yearbook_fee(YearbookFee) VALUES (?)");
            pst.setString(1,yearbookFEE);
            
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Yearbook Fee Updated");
            
            Fee_Load();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Connection Error", JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_jLabel43MouseClicked

    private void printButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonKeyPressed

    private void studentGraduatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentGraduatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentGraduatedActionPerformed

    private void editEVENTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editEVENTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_editEVENTKeyPressed

    private void addClubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClubActionPerformed
       Club open = new Club();
       open.setVisible(true);
    }//GEN-LAST:event_addClubActionPerformed

    private void GreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GreenMouseClicked
        this.setExtendedState(NORMAL);
        System.out.println("System Normalized");
    }//GEN-LAST:event_GreenMouseClicked

    private void GreenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GreenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GreenKeyPressed

    private void RedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RedMouseClicked
      this.dispose();
      System.out.println("System Closed");
    }//GEN-LAST:event_RedMouseClicked

    private void RedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RedKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RedKeyPressed

    private void yearbookfeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearbookfeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearbookfeeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Claiming_Area;
    private javax.swing.JLabel ENROLLED;
    private javax.swing.JTextField Extensions;
    private javax.swing.JTextField Fname;
    private javax.swing.JLabel GRADUATES;
    private javax.swing.JLabel Green;
    private javax.swing.JLabel HONORS;
    private javax.swing.JLabel JLabelPicture;
    private javax.swing.JTextField Mname;
    private javax.swing.JLabel PAID;
    private javax.swing.JLabel Red;
    private javax.swing.JButton SearchClaimButton;
    private javax.swing.JTextField Sname;
    public static javax.swing.JTable StudentYearbookTable;
    private javax.swing.JLabel Yellow;
    private javax.swing.JButton addAdmin;
    public static javax.swing.JButton addClub;
    private javax.swing.JButton addEvent;
    private javax.swing.JButton addPlan;
    private javax.swing.JButton addSection;
    private javax.swing.JButton addStudent;
    public static javax.swing.JTable announcementBoard;
    private com.toedter.calendar.JDateChooser birthdate;
    public static javax.swing.JButton claimRefresh;
    private javax.swing.JTextField claimSearchText;
    private javax.swing.JButton claimedButton;
    private javax.swing.JButton clearForm;
    private javax.swing.JButton courseRefresh;
    public static javax.swing.JButton deleteEVENT;
    private javax.swing.JButton deleteStudent;
    public static javax.swing.JButton deleteplanButton;
    public static javax.swing.JButton editEVENT;
    public static javax.swing.JButton editplanButton;
    private javax.swing.JButton eligibleButton;
    public static javax.swing.JButton eventRefresh;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    public static javax.swing.JButton planRefresh;
    public static javax.swing.JTable planTable;
    public static javax.swing.JTextField positionAdmin;
    public static javax.swing.JButton printButton;
    private javax.swing.JTextField searchStudent;
    private javax.swing.JButton searchStudentButton;
    private javax.swing.JComboBox<String> selectCourse;
    private javax.swing.JButton selectProfile;
    private javax.swing.JTextField studentAddress;
    public static javax.swing.JTextField studentClubs;
    public static javax.swing.JComboBox<String> studentCourse;
    private javax.swing.JTextField studentEmail;
    private javax.swing.JLabel studentEnrolled;
    private javax.swing.JComboBox<String> studentGraduated;
    private javax.swing.JComboBox<String> studentHonor;
    private javax.swing.JTextField studentIDNum;
    private javax.swing.JTextField studentNum;
    private javax.swing.JComboBox<String> studentPaid;
    private javax.swing.JTextArea studentQuote;
    public static javax.swing.JButton studentRefresh;
    private javax.swing.JTextField studentSoli;
    private javax.swing.JButton updateStudent;
    public static javax.swing.JTextField yearbookAdmin;
    private javax.swing.JTextField yearbookfee;
    // End of variables declaration//GEN-END:variables

    private void EditForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
