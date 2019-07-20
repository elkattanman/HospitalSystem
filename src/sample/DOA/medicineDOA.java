package sample.DOA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Medicine;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class medicineDOA {
    Connection conn;
    private final String getAll_stmt = "select * from medicine";
    private final String get_stmt = "select * from medicine where name=? ";
    private final String insert_stmt = "INSERT INTO medicine VALUES (?,?,?);";
    private final String update_stmt = "UPDATE medicine SET  amount=? , price=? WHERE  name=? " ;
    private final String delete_stmt = "DELETE FROM medicine WHERE name=?";

    public medicineDOA() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projectdb", "root", "");
            System.out.println("connected");
        } catch (Exception ex) {
            System.out.println("error"+ ex);
        }

    }

    public void insert(Medicine c) {
        try(PreparedStatement pstmt = conn.prepareStatement(insert_stmt,Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, c.getName());
            pstmt.setInt(2 , Integer.parseInt(c.getAmount()));
            pstmt.setInt(3,Integer.parseInt(c.getPrice()));
            pstmt.executeUpdate();
            try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setName(generatedKeys.getString(1));
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(medicineDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public Medicine getMedicine(String name){
        Medicine c=new Medicine();
        try(PreparedStatement pstmt = conn.prepareStatement(get_stmt)){
            pstmt.setString(1,name);
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    c.setAmount(rs.getString(2));
                    c.setPrice(rs.getString(3));
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(medicineDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return c;
    }

    public ObservableList<Medicine> getAllMedicine(){
        ObservableList<Medicine> allMedicine = FXCollections.observableArrayList();
        try(PreparedStatement pstmt = conn.prepareStatement(getAll_stmt)){
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    Medicine c=new Medicine(rs.getString(1),rs.getString(2),rs.getString(3));
                    allMedicine.add(c);
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(medicineDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return allMedicine;
    }

    public void update(Medicine c){
        try(PreparedStatement pstmt = conn.prepareStatement(update_stmt)){
            pstmt.setString(1,c.getAmount());
            pstmt.setString(2,c.getPrice());
            pstmt.setString(3,c.getName());
            pstmt.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(medicineDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void Delete(String name){
        try(PreparedStatement pstmt = conn.prepareStatement(delete_stmt)){
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(medicineDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
