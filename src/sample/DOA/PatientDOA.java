package sample.DOA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Patient;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatientDOA {
    Connection conn;
    private final String getAll_stmt = "select * from patient";
    private final String get_stmt = "select * from patient where national_id=? ";
    private final String insert_stmt = "INSERT INTO patient VALUES (?,?,?,?,?,?,?);";
    private final String update_stmt = "UPDATE patient SET Name=?,gender=?,address=?,Age=?,Phone=?,hospital=? WHERE national_id=?" ;
    private final String delete_stmt = "DELETE FROM patient WHERE national_id=?";

    public PatientDOA() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projectdb", "root", "");
            System.out.println("connected");
        } catch (Exception ex) {
            System.out.println("error :"+ ex);
        }

    }

    public void insert(Patient c) {
        try(PreparedStatement pstmt = conn.prepareStatement(insert_stmt, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, Integer.parseInt(c.getNational_id()));
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getGender()) ;
            pstmt.setString(4, c.getAddress());
            pstmt.setInt(5, Integer.parseInt(c.getAge()));
            pstmt.setString(6, c.getPhone());
            pstmt.setString(7, c.getHosptial());
            pstmt.executeUpdate();
            try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setName(generatedKeys.getString(1));
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(PatientDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        System.out.println("Done");
    }

    public Patient getPatient(int national_id){
        Patient c=new Patient();
        try(PreparedStatement pstmt = conn.prepareStatement(get_stmt)){
            pstmt.setInt(1,national_id);
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    c.setName(rs.getString(2));
                    c.setGender(rs.getString(3));
                    c.setAddress(rs.getString(4));
                    c.setAge(rs.getString(5));
                    c.setPhone(rs.getString(6));
                    c.setHosptial(rs.getString(7));
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(PatientDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return c;
    }

    public ObservableList<Patient> getAllPatients(){
        ObservableList<Patient> allPatients = FXCollections.observableArrayList();
        try(PreparedStatement pstmt = conn.prepareStatement(getAll_stmt)){
            try(ResultSet rs=pstmt.executeQuery()) {
                while (rs.next()) {
                    Patient c=new Patient(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                    allPatients.add(c);
                }
            }
        }catch (SQLException ex){
            Logger.getLogger(PatientDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
        return allPatients;
    }

    public void update(Patient c){
        try(PreparedStatement pstmt = conn.prepareStatement(update_stmt)){
            pstmt.setString(1,c.getName());
            pstmt.setString(2,c.getGender());
            pstmt.setString(3,c.getAddress());
            pstmt.setInt(4,Integer.parseInt(c.getAge()));
            pstmt.setString(5,c.getPhone());
            pstmt.setString(6,c.getHosptial());
            pstmt.setInt(7,Integer.parseInt(c.getNational_id()));
            pstmt.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(PatientDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void Delete(int national_id){
        try(PreparedStatement pstmt = conn.prepareStatement(delete_stmt)){
            pstmt.setInt(1,national_id);
            pstmt.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(PatientDOA.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
