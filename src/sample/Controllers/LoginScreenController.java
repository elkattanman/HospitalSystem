package sample.Controllers;
     

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Model.MainModel;
import sample.DOA.userDOA;
import sample.Model.User;

public class LoginScreenController {

    @FXML
    private FontAwesomeIconView closeButton;

    @FXML
    private FontAwesomeIconView minimizeButton;

    @FXML
    private JFXTextField txtFld;
    @FXML
    private JFXPasswordField txtPass;

    @FXML
    public StackPane stackpane;

    @FXML
    private void loginAction(){
        userDOA o=new userDOA();
        User u=o.getUser(txtFld.getText());
        if ( u.getUsername().equals(txtFld.getText()) && u.getPassword().equals(txtPass.getText()) &&u.getIsAdmin().equals("1")){
            System.out.println("------ LoginScreen Closed -------");
            main.HomePageWindow();
        }else if(u.getUsername().equals(txtFld.getText()) && u.getPassword().equals(txtPass.getText()) &&u.getIsAdmin().equals("0")){
            main.UserHomePageWindow();
        }else{ 
            main.showMessage("Please Enter A Valid UserName.");
        }

    }

    @FXML
    private void closeButtonAction(){ main.closeStage(); }

    @FXML
    private void minimizeButtonAction(){ main.minimizeStage(); }

    MainModel main;
    Stage stage;
    public void main(Stage stage,MainModel main){
        this.main=main;
        this.stage=stage;
    }

}
