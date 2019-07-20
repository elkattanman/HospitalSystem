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
import sample.Model.Medicine;
import sample.DOA.medicineDOA;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class MedicineController implements Initializable{


    @FXML
    private JFXTreeTableView<Medicine> treeTableView;

    @FXML
    private TreeTableColumn<Medicine, String> codeCol , nameCol , amountCol ,priceCol ;

    @FXML
    private JFXTextField searchTF ,codeTF , nameTF , amountTF , priceTF ;

    ObservableList<Medicine> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String> >() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Medicine, String> param) {
                return param.getValue().getValue().name;
            }
        });


        amountCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Medicine, String> param) {
                return param.getValue().getValue().amount;
            }
        });

        priceCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Medicine, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Medicine, String> param) {
                return param.getValue().getValue().price;
            }
        });


        //list= FXCollections.observableArrayList();
        list= new medicineDOA().getAllMedicine();

        TreeItem<Medicine> root=new RecursiveTreeItem<Medicine>(list, RecursiveTreeObject ::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

//        list.addAll(new Medicine("alpha","100","15"));
//        list.addAll(new Medicine("beta","150","26"));
//        list.addAll(new Medicine("hexa","300","70"));

        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeTableView.setPredicate(new Predicate<TreeItem<Medicine>>() {
                    @Override
                    public boolean test(TreeItem<Medicine> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) ;
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
        Medicine m=new Medicine(nameTF.getText(),amountTF.getText(),priceTF.getText() );
        new medicineDOA().insert(m);
        list.addAll(m);
    }


    @FXML
    void  deleteAction(ActionEvent event){
        int index=treeTableView.getSelectionModel().getSelectedIndex();
        Medicine m=list.get(index);
        new medicineDOA().Delete(m.getName());
        list.remove(index);
        clearFields();
    }

    public  void  showDetails(TreeItem<Medicine> treeItem){
        nameTF.setText(treeItem.getValue().getName());
        amountTF.setText(treeItem.getValue().getAmount());
        priceTF.setText(treeItem.getValue().getPrice());
    }


    public void clearFields(){
        codeTF.setText("");
        nameTF.setText("");
        amountTF.setText("");
        priceTF.setText("");
    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }

    @FXML
    void editAction(ActionEvent event){

        TreeItem <Medicine> treeItem=treeTableView.getSelectionModel().getSelectedItem();
        Medicine m=new Medicine(nameTF.getText(),amountTF.getText(),priceTF.getText());
        new medicineDOA().update(m);
        treeItem.setValue(m);
    }

}
