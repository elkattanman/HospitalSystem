package sample.DOA;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.Model.User;

public class userDOA {

    Connection conn;
    private final String getAll_stmt = "select * from user";
    private final String get_stmt = "select * from user where username=? ";
    private final String insert_stmt = "INSERT INTO 'user' VALUES (?,?);";

    public userDOA() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projectdb", "root", "");
            System.out.println("connected");
        } catch (Exception ex) {
            System.out.println("error"+ ex);
        }

    }


    public User getUser(String name){
        User c=new User();
        try(PreparedStatement pstmt = conn.prepareStatement(get_stmt)){
            pstmt.setString(1,name);
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    c.setUsername(rs.getString(1));
                    c.setPassword(rs.getString(2));
                    c.setIsAdmin(rs.getString(3));
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(medicineDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return c;
    }
}
