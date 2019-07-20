/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mustafa Khaled
 */
public class User {
    public StringProperty username ,Password , isAdmin;

    public User(){
        this.username=new SimpleStringProperty("");
        this.Password=new SimpleStringProperty("");
        this.isAdmin=new SimpleStringProperty("");
    }
    public User( String username , String Password, String isAdmin  ){
        this.username=new SimpleStringProperty(username);
        this.Password=new SimpleStringProperty(Password);
        this.isAdmin=new SimpleStringProperty(isAdmin);
    }
    
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return Password.get();
    }

    public void setPassword(String Password) {
        this.Password.set(Password);
    }

    public String getIsAdmin() {
        return isAdmin.get();
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin.set(isAdmin);
    }
    
}
