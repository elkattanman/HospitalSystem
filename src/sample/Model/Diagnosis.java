package sample.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Diagnosis  extends RecursiveTreeObject<Diagnosis> {
    public StringProperty id ,national_id, disease_name,medicine_name,amount;

    public Diagnosis(){
        this.id=new SimpleStringProperty("");
        this.national_id=new SimpleStringProperty("");
        this.disease_name=new SimpleStringProperty("");
        this.medicine_name=new SimpleStringProperty("");
        this.amount=new SimpleStringProperty("");
    }
    public Diagnosis(String id,String national_id,String  disease_name,String medicine_name,String amount){
        this.id=new SimpleStringProperty(id);
        this.national_id=new SimpleStringProperty(national_id);
        this.disease_name=new SimpleStringProperty(disease_name);
        this.medicine_name=new SimpleStringProperty(medicine_name);
        this.amount=new SimpleStringProperty(amount);
    }

    public String getNational_id() {
        return national_id.get();
    }

    public StringProperty national_idProperty() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id.set(national_id);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getDisease_name() {
        return disease_name.get();
    }

    public StringProperty disease_nameProperty() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name.set(disease_name);
    }

    public String getMedicine_name() {
        return medicine_name.get();
    }

    public StringProperty medicine_nameProperty() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name.set(medicine_name);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }
}
