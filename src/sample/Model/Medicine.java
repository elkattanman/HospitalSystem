package sample.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Medicine extends RecursiveTreeObject<Medicine> {


    public StringProperty name ,amount , price;
    public Medicine(){
        this.name=new SimpleStringProperty("");
        this.amount=new SimpleStringProperty("");
        this.price=new SimpleStringProperty("");
    }
    public Medicine( String name , String amount, String price  ){

        this.name=new SimpleStringProperty(name);
        this.amount=new SimpleStringProperty(amount);
        this.price=new SimpleStringProperty(price);
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

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
