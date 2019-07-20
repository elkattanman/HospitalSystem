package sample.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Diseases extends RecursiveTreeObject<Diseases> {


    public StringProperty  name ,symptoms , level;

    public Diseases(String name , String symptoms, String level  ){
        this.name=new SimpleStringProperty(name);
        this.symptoms=new SimpleStringProperty(symptoms);
        this.level=new SimpleStringProperty(level);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    } 

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSymptoms() {
        return symptoms.get();
    }

    public StringProperty symptomsProperty() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms.set(symptoms);
    }

    public String getLevel() {
        return level.get();
    }

    public StringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }
}
