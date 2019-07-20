
package sample.DOA;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mustafa Khaled
 */
public class Statistic {
    Connection conn;
    public Statistic(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projectdb", "root", "");
            System.out.println("connected");
        } catch (Exception ex) {
            System.out.println("error :"+ ex);
        }
    }
    private final String getCount = "SELECT COUNT(DISTINCT(national_id) ) from diagnosis_id";
   
    
    public int getPatientCount(){
        int ret=0;
        try(PreparedStatement pstmt = conn.prepareStatement(getCount)){
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    ret=rs.getInt(1);
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(PatientDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return ret;
    }
    public static void main(String[] args) {
        System.out.println(new Statistic().getPatientCount());
    }
}
