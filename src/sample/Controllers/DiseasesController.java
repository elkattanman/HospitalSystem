package sample.Controllers;

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
import sample.Model.Diseases;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import sample.DOA.DiseasesDOA;

public class DiseasesController implements Initializable{


    @FXML
    private JFXTreeTableView<Diseases> treeTableView;

    @FXML
    private TreeTableColumn<Diseases, String>  nameCol , symptomsCol ,levelCol ;

    @FXML
    private JFXTextField searchTF  , nameTF , symptomsTF , levelTF ;

    ObservableList<Diseases> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diseases, String>, ObservableValue<String> >() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diseases, String> param) {
                return param.getValue().getValue().name;
            }
        });


        symptomsCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diseases, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diseases, String> param) {
                return param.getValue().getValue().symptoms;
            }
        });

        levelCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Diseases, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Diseases, String> param) {
                return param.getValue().getValue().level;
            }
        });


        //list= FXCollections.observableArrayList();
        list= new DiseasesDOA().getAllDiseases();

        TreeItem<Diseases> root=new RecursiveTreeItem<Diseases>(list, RecursiveTreeObject ::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

//        list.addAll(new Diseases("cold","sleepy","2"));
//        list.addAll(new Diseases("alpha","ksl","3"));
//        list.addAll(new Diseases("x","noom","4"));

        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeTableView.setPredicate(new Predicate<TreeItem<Diseases>>() {
                    @Override
                    public boolean test(TreeItem<Diseases> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue)  ;
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
        Diseases d=new Diseases(nameTF.getText(),symptomsTF.getText(),levelTF.getText() );
        new DiseasesDOA().insert(d);
        list.addAll(d);
    }


    @FXML
    void  deleteAction(ActionEvent event){
        int index=treeTableView.getSelectionModel().getSelectedIndex();
        Diseases d=list.get(index);
        new DiseasesDOA().Delete(d.getName());
        list.remove(index);
        clearFields();
    }

    public  void  showDetails(TreeItem<Diseases> treeItem){
        nameTF.setText(treeItem.getValue().getName());
        symptomsTF.setText(treeItem.getValue().getSymptoms());
        levelTF.setText(treeItem.getValue().getLevel());
    }


    public void clearFields(){
        nameTF.setText("");
        symptomsTF.setText("");
        levelTF.setText("");
    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }

    @FXML
    void editAction(ActionEvent event){

        TreeItem <Diseases> treeItem=treeTableView.getSelectionModel().getSelectedItem();
        Diseases m=new Diseases(nameTF.getText(),symptomsTF.getText(),levelTF.getText());
        new DiseasesDOA().update(m);
        treeItem.setValue(m);

    }

}
