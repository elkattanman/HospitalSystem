package sample.Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import sample.Model.Patient;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import sample.DOA.PatientDOA;

public class PatientController implements Initializable{

    @FXML
    private JFXTreeTableView<Patient> treeTableView;

    @FXML
    private TreeTableColumn<Patient, String> nationalidCol , nameCol , ageCol ,genderCol
            , addressCol ,  phoneCol , hospitalCol ;

    @FXML
    private JFXTextField searchTF ,nationalidTF , nameTF , ageTF , addressTF , hospitalTF ,phoneTF;

    @FXML
    private JFXComboBox<String> genderCB;

    ObservableList<Patient> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        genderCB.getItems().addAll("Male","Female");

        nationalidCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().national_id;
            }
        });

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String> >() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().name;
            }
        });


        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().age;
            }
        });

        genderCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().gender;
            }
        });

        addressCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().address;
            }
        });
        hospitalCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().hosptial;
            }
        });

        phoneCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Patient, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Patient, String> param) {
                return param.getValue().getValue().phone;
            }
        });


//        list= FXCollections.observableArrayList();
        list= new PatientDOA().getAllPatients();

        TreeItem<Patient> root=new RecursiveTreeItem<Patient>(list, RecursiveTreeObject ::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

//        list.addAll(new Patient("29807291702416","ali","male","samaly","20","01026686241","asr"));
//        list.addAll(new Patient("29807291702416","Mohammed","male","samaly","20","01026686241","asr"));
//        list.addAll(new Patient("29807291702416","samir","male","samaly","20","01026686241","asr"));

        //search
        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeTableView.setPredicate(new Predicate<TreeItem<Patient>>() {
                    @Override
                    public boolean test(TreeItem<Patient> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) | modelTreeItem.getValue().national_id.getValue().contains(newValue) ;
                    }
                });
            }
        });


        treeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails(newValue);
        });

    }


    @FXML
    void  addAction(ActionEvent event){
        Patient p=new Patient(nationalidTF.getText(),nameTF.getText(),genderCB.getSelectionModel().getSelectedItem(),addressTF.getText(),ageTF.getText(),phoneTF.getText(),hospitalTF.getText());
        new PatientDOA().insert(p);
        list.addAll(p);
    }


    @FXML
    void  deleteAction(ActionEvent event){
        int index=treeTableView.getSelectionModel().getSelectedIndex();
        Patient p=list.get(index);
        new PatientDOA().Delete(Integer.parseInt(p.getNational_id()));
        list.remove(index);
        clearFields();
    }

    public  void  showDetails(TreeItem<Patient> treeItem){
        nationalidTF.setText(treeItem.getValue().getNational_id());
        nameTF.setText(treeItem.getValue().getName());
        ageTF.setText(treeItem.getValue().getAge());
        genderCB.getSelectionModel().select(treeItem.getValue().getGender());
        addressTF.setText(treeItem.getValue().getAddress());
        hospitalTF.setText(treeItem.getValue().getHosptial());
        phoneTF.setText(treeItem.getValue().getPhone());
    }


    public void clearFields(){
        nationalidTF.setText("");
        nameTF.setText("");
        ageTF.setText("");
        genderCB.getSelectionModel().select("");
        addressTF.setText("");
        hospitalTF.setText("");
        phoneTF.setText("");
    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }

    @FXML
    void editAction(ActionEvent event){

        TreeItem <Patient> treeItem=treeTableView.getSelectionModel().getSelectedItem();

        Patient m=new Patient(nationalidTF.getText(),nameTF.getText(),genderCB.getSelectionModel().getSelectedItem(),addressTF.getText(),ageTF.getText(),phoneTF.getText(),hospitalTF.getText());
        
        new PatientDOA().update(m);
        treeItem.setValue(m);

    }

}
