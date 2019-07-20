package sample.Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Controllers.HomePageController;
import sample.Controllers.LoginScreenController;
import sample.Controllers.SplashController;


public class MainModel {

    Stage stage;

    public MainModel(Stage stage) {
        this.stage = stage;
    }

    LoginScreenController LoginController;
    public void LoginScreenWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginScreen.fxml"));
            StackPane pane = loader.load();
            LoginController = loader.getController();
            LoginController.main(stage, this);
            Scene scene = new Scene(pane);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SplashWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SplashFxml.fxml"));
            AnchorPane pane = loader.load();
            SplashController controller = loader.getController();
            controller.main(stage, this);
            Scene scene = new Scene(pane);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Failed to Open Splash Stage !!");
        }
    }

    public void HomePageWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/HomePageFxml.fxml"));
            AnchorPane pane = loader.load();
            HomePageController controller = loader.getController();
            controller.main(stage, this);
            Scene scene = new Scene(pane);
            //scene.getStylesheets().addAll(getClass().getResource("@sample/Style.css").toExternalForm());
            stage.setScene(scene);
            //stage.show();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Failed to Open HomePage !!");
        }
    }
    public void UserHomePageWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/UserHomePageFxml.fxml"));
            AnchorPane pane = loader.load();
            HomePageController controller = loader.getController();
            controller.main(stage, this);
            Scene scene = new Scene(pane);
            //scene.getStylesheets().addAll(getClass().getResource("@sample/Style.css").toExternalForm());
            stage.setScene(scene);
            //stage.show();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Failed to Open HomePage !!");
        }
    }

    public AnchorPane MangeWindow(String fxml) {
        AnchorPane pane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/" + fxml));
            pane = loader.load();
        } catch (Exception e) {
            System.out.println("Failed to Open" + fxml + " window!!");
        }
        return pane;
    }

    public void closeStage() {
        stage.close();
    }

    public void minimizeStage() {
        stage.setIconified(true);
    }

    public void showMessage(String Message) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("ERROR"));
        layout.setBody(new Text(Message));
        JFXDialog dialog = new JFXDialog(LoginController.stackpane , layout, JFXDialog.DialogTransition.CENTER);
        JFXButton btnOk = new JFXButton("Okay");
        btnOk.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dialog.close();
            }
        });
        layout.setActions(btnOk);
        dialog.show();
    }

}
