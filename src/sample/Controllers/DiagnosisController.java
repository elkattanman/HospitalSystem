package sample.Controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import sample.Model.Diagnosis;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import sample.DOA.DiagnosisDOA;

public class DiagnosisController implements Initializable{


    @FXML
    private JFXTreeTableView<Diagnosis> treeTableView;

    @FXML
    private TreeTableColumn<Diagnosis, String> nationalidCol ,diagnosisCol ,diseaseCol ,medicinnameCol, medamountCol;

    @FXML
    private JFXTextField searchTF , nationalidTF ,diagnosisidTF ,medicinnameTF ,medamountTF ,diseasenameTF;

    ObservableList<Diagnosis> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nationalidCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diagnosis, String>, ObservableValue<String> >() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diagnosis, String> param) {
                return param.getValue().getValue().national_id;
            }
        });


        diagnosisCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diagnosis, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diagnosis, String> param) {
                return param.getValue().getValue().id;
            }
        });

        diseaseCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diagnosis, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diagnosis, String> param) {
                return param.getValue().getValue().disease_name;
            }
        });
        medicinnameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diagnosis, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diagnosis, String> param) {
                return param.getValue().getValue().medicine_name;
            }
        });

        medamountCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diagnosis, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diagnosis, String> param) {
                return param.getValue().getValue().amount;
            }
        });

        //list= FXCollections.observableArrayList();
        list= new DiagnosisDOA().getAllDiagnoses();

        TreeItem<Diagnosis> root=new RecursiveTreeItem<Diagnosis>(list, RecursiveTreeObject ::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeTableView.setPredicate(new Predicate<TreeItem<Diagnosis>>() {
                    @Override
                    public boolean test(TreeItem<Diagnosis> modelTreeItem) {
                        return modelTreeItem.getValue().id.getValue().contains(newValue) | modelTreeItem.getValue().national_id.getValue().contains(newValue);
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
        Diagnosis d=new Diagnosis("",nationalidTF.getText(),diseasenameTF.getText(),medicinnameTF.getText(),medamountTF.getText());
        try{
            new DiagnosisDOA().insert(d);
            list.addAll(d);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }


    @FXML
    void  deleteAction(ActionEvent event){
        int index=treeTableView.getSelectionModel().getSelectedIndex();
        Diagnosis d=list.get(index);
        //new DiagnosisDOA().delete();
        list.remove(index);
        clearFields();
    }

    public  void  showDetails(TreeItem<Diagnosis> treeItem){
        nationalidTF.setText(treeItem.getValue().getNational_id());
        diagnosisidTF.setText(treeItem.getValue().getId());
        diseasenameTF.setText(treeItem.getValue().getDisease_name());
        medicinnameTF.setText(treeItem.getValue().getMedicine_name());
        medamountTF.setText(treeItem.getValue().getMedicine_name());
    }


    public void clearFields(){
        nationalidTF.setText("");
        diagnosisidTF.setText("");
        diseasenameTF.setText("");
        medicinnameTF.setText("");
        medamountTF.setText("");
    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }

    @FXML
    void editAction(ActionEvent event){

        TreeItem <Diagnosis> treeItem=treeTableView.getSelectionModel().getSelectedItem();
        Diagnosis m=new Diagnosis(diagnosisidTF.getText(),nationalidTF.getText(),diseasenameTF.getText(),medicinnameTF.getText(),medamountTF.getText());
        new DiagnosisDOA().update(m);
        treeItem.setValue(m);

    }

}
