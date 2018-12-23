
import com.sun.rowset.CachedRowSetImpl;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class handleMyDB {
    
    public static java.sql.Connection dbConnection;
    
    public static boolean openDBMySQlConnection() throws Exception {
        
        String db = "lbrydb";
        String userName = "root";
        String passWord = "e4r5t6y7";
        
        try {
            
            dbConnection=DriverManager.getConnection("jdbc:mysql://localhost/"+db,userName,passWord);
            return true;            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static CachedRowSetImpl queryDB(String queryCmd) throws Exception{
       
       CachedRowSetImpl crs = new CachedRowSetImpl();
        try {
            
            openDBMySQlConnection();
                    
            Statement stmt;
            stmt = handleMyDB.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(queryCmd);
            crs.populate(rs);
            dbConnection.close();
            
           return crs;
        } catch (Exception e) {
            System.out.println("Error On Fetching Record: "+ queryCmd + e.getMessage());
            return null;
        }
    }
    
    public static boolean updateDB(String updateCmd) throws Exception{
        try {
            
            openDBMySQlConnection();
                    
            PreparedStatement pst= handleMyDB.dbConnection.prepareStatement(updateCmd);
            pst.execute();
                       
            dbConnection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error On Adding Record: "+ updateCmd + e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(String.valueOf(openDBMySQlConnection()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
