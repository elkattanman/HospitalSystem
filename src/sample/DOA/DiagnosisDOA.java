package sample.DOA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Diagnosis;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiagnosisDOA {
    Connection conn;
    private final String getAll_stmt = "select f.id , national_id , s.disease_name , s.medicine_name ,s.amount from diagnosis_id f Join diagnosis s where f.id=s.id";
    private final String get_stmt = "select f.id , national_id , s.disease_name , s.medicine_name ,s.amount from diagnosis_id f Join diagnosis s where f.id=s.id and f.id=?";
    private final String insert_stmt = "INSERT INTO diagnosis_id (`national_id`) VALUES(?);";
    private final String insert_stmt2 = "INSERT INTO diagnosis VALUES (?,?,?,?);";
    private final String update_stmt = "UPDATE diagnosis SET disease_name=?,medicine_name=?,amount=? WHERE id=?" ;
    //private final String delete_stmt = "DELETE FROM diagnosis_id WHERE national_id=?";

    public DiagnosisDOA(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projectdb", "root", "");
            System.out.println("connected");
        } catch (Exception ex) {
            System.out.println("error :"+ ex);
        }
    }
    public void insert(Diagnosis c) throws SQLException {
        try(PreparedStatement pstmt = conn.prepareStatement(insert_stmt, Statement.RETURN_GENERATED_KEYS)){
            //pstmt.setInt(1, Integer.parseInt(c.getId()));
            pstmt.setInt(1, Integer.parseInt(c.getNational_id()));
            pstmt.executeUpdate();
            try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setId(generatedKeys.getString(1));
                }
            }
        }catch(SQLException ex){
            //Logger.getLogger(DiagnosisDOA.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
            throw ex;
        }
        try(PreparedStatement pstmt = conn.prepareStatement(insert_stmt2, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, Integer.parseInt(c.getId()));
            pstmt.setString(2, c.getDisease_name());
            pstmt.setString(3, c.getMedicine_name());
            pstmt.setInt(4, Integer.parseInt(c.getAmount()));
            pstmt.executeUpdate();
            try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setId(generatedKeys.getString(1));
                }
            }
        }catch(SQLException ex){
            //Logger.getLogger(DiagnosisDOA.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
        }
    }

    public Diagnosis getDiagnosis(int id){
        Diagnosis c=new Diagnosis();
        try(PreparedStatement pstmt = conn.prepareStatement(get_stmt)){
            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    c.setNational_id(rs.getString(2));
                    c.setDisease_name(rs.getString(3));
                    c.setMedicine_name(rs.getString(4));
                    c.setAmount(rs.getString(5));
                }
            }
        }catch (SQLException ex){
            //Logger.getLogger(DiagnosisDOA.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
        }
        return c;
    }

    public ObservableList<Diagnosis> getAllDiagnoses(){
        ObservableList<Diagnosis> allDiagnoses = FXCollections.observableArrayList();
        try(PreparedStatement pstmt = conn.prepareStatement(getAll_stmt)){
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    Diagnosis c=new Diagnosis(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                    allDiagnoses.add(c);
                }
            }
        }catch (SQLException ex){
            //Logger.getLogger(DiagnosisDOA.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
        }
        return allDiagnoses;
    }

    public void update(Diagnosis c){
        try(PreparedStatement pstmt = conn.prepareStatement(update_stmt)){
            pstmt.setString(1,c.getDisease_name());
            pstmt.setString(2,c.getMedicine_name());
            pstmt.setString(3,c.getAmount());
            pstmt.setInt(4,Integer.parseInt(c.getId()));
            pstmt.executeUpdate();
        }catch (SQLException ex){
            //Logger.getLogger(DiagnosisDOA.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex);
        }
    }
    /**public void Delete(int national_id){
        try(PreparedStatement pstmt = conn.prepareStatement(delete_stmt)){
            pstmt.setInt(1,national_id);
        }catch (SQLException ex){
            Logger.getLogger(DiagnosisDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }*/

}
