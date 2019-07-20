package sample.DOA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Diseases;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiseasesDOA {
    Connection conn;
    private final String getAll_stmt = "SELECT * FROM disease";
    private final String get_stmt = "SELECT * FROM disease where name=? ";
    private final String insert_stmt = "INSERT INTO disease VALUES (?,?,?);";
    private final String update_stmt = "UPDATE disease SET Symptoms=?,level=? WHERE name=? " ;
    private final String delete_stmt = "DELETE FROM disease WHERE name=?";

    public DiseasesDOA() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projectdb", "root", "");
            System.out.println("Connecting Done");
        } catch (Exception ex){
            System.out.println("error in connect to DB : "+ ex);
        }

    }
    public void insert(Diseases d) {
        try(PreparedStatement pstmt = conn.prepareStatement(insert_stmt,Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1,d.getName());
            pstmt.setString(2,d.getSymptoms());
            pstmt.setString(3,d.getLevel());
            pstmt.executeUpdate();
            try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    d.setName(generatedKeys.getString(1));
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DiseasesDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public Diseases getDiseases(String name){
        Diseases d=new Diseases("","","");
        try(PreparedStatement pstmt = conn.prepareStatement(get_stmt)){
            pstmt.setString(1,name);
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    d.setSymptoms(rs.getString(2));
                    d.setLevel(rs.getString(3));
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(DiseasesDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return d;
    }

    public  ObservableList<Diseases> getAllDiseases(){
        ObservableList<Diseases> allDiseases = FXCollections.observableArrayList();
        try(PreparedStatement pstmt = conn.prepareStatement(getAll_stmt)){
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    Diseases d=new Diseases(rs.getString(1),rs.getString(2),rs.getString(3));
                    allDiseases.add(d);
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(DiseasesDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return allDiseases;
    }

    public void update(Diseases d){
        try(PreparedStatement pstmt = conn.prepareStatement(update_stmt)){
            pstmt.setString(1,d.getSymptoms());
            pstmt.setString(2,d.getLevel());
            pstmt.setString(3,d.getName());
            pstmt.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(DiseasesDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void Delete(String name){
        try(PreparedStatement pstmt = conn.prepareStatement(delete_stmt)){
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(DiseasesDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
