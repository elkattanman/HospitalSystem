package sample.Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Patient extends RecursiveTreeObject<Patient> {


    public StringProperty national_id , name ,age ,gender , address , hosptial , phone;

    public Patient(){
        this.national_id=new SimpleStringProperty("");
        this.name=new SimpleStringProperty("");
        this.age=new SimpleStringProperty("");
        this.gender=new SimpleStringProperty("");
        this.address=new SimpleStringProperty("");
        this.hosptial=new SimpleStringProperty("");
        this.phone=new SimpleStringProperty("");
    }
    public Patient( String national_id,String name ,String gender ,String address , String age , String phone , String hosptial ){
        this.national_id=new SimpleStringProperty(national_id);
        this.name=new SimpleStringProperty(name);
        this.age=new SimpleStringProperty(age);
        this.gender=new SimpleStringProperty(gender);
        this.address=new SimpleStringProperty(address);
        this.hosptial=new SimpleStringProperty(hosptial);
        this.phone=new SimpleStringProperty(phone);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getHosptial() {
        return hosptial.get();
    }

    public StringProperty hosptialProperty() {
        return hosptial;
    }

    public void setHosptial(String hosptial) {
        this.hosptial.set(hosptial);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.hosptial.set(phone);
    }

    @Override
    public String toString() {
        return "Patient{" + "national_id=" + national_id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address + ", hosptial=" + hosptial + ", phone=" + phone + '}';
    }

    
    
    
}
