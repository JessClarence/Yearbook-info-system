
package pkgfinal.project;

import javax.swing.JOptionPane;


public class Screen extends javax.swing.JFrame {


    public Screen() {
        initComponents();
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
        Loadinglable = new javax.swing.JLabel();
        Loadingvalue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Loadingbar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Loadinglable.setBackground(new java.awt.Color(0, 0, 0));
        Loadinglable.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Loadinglable.setForeground(new java.awt.Color(0, 0, 0));
        Loadinglable.setText("Loading....");

        Loadingvalue.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        Loadingvalue.setForeground(new java.awt.Color(0, 0, 0));
        Loadingvalue.setText("0 %");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgfinal/project/Loading/1e3e3738d81b9db4f6b6505b236a996a.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Loadinglable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Loadingvalue)
                .addContainerGap())
            .addComponent(Loadingbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Loadingvalue)
                    .addComponent(Loadinglable))
                .addGap(0, 0, 0)
                .addComponent(Loadingbar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        
        
       Screen sp = new Screen();
       sp.setVisible(true);
       Yearbook_Info_System info = new Yearbook_Info_System();
       
        try {
            
            for (int i=0;i<=100;i++){
                Thread.sleep(100);
                sp.Loadingvalue.setText(i +"%");
                
                if(i==10){
                    sp.Loadinglable.setText("Turning On Modules...");
                }
                if(i==20){
                    sp.Loadinglable.setText("Loading Modules...");
                }
                if(i==50){
                    sp.Loadinglable.setText("Connecting to Database...");
                }
                if(i==70){ 
                    sp.Loadinglable.setText("Connection Successful !");
                    
                }
                if(i==80){
                    sp.Loadinglable.setText("Launching Application...");
                
                
                }
                sp.Loadingbar.setValue(i);

                
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Connection Error: Unable To Reach Databse", JOptionPane.ERROR_MESSAGE);            
        }
        sp.setVisible(false);
        info.setVisible(true);
        
        sp.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Loadingbar;
    private javax.swing.JLabel Loadinglable;
    private javax.swing.JLabel Loadingvalue;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
