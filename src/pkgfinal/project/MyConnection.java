
package pkgfinal.project;

import java.sql.Connection;
import java.sql.DriverManager;


public class MyConnection {
    
    static final String url = "jdbc:mysql://localhost/yearbook_database";
    static final String USER = "root";
    static final String PASS = "";
    public static Connection connectDB(){
    Connection conn = null;
    
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,USER,PASS);
            return conn;
        }catch(Exception ex){
            System.out.println("There were errors while connection to db.");
            return null;
        }
        
    }
}


