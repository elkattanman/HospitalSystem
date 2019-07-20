package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{
    MainModel main;
    Stage stage;

    public void main(Stage stage, MainModel main){
        this.main=main;
        this.stage=stage;
    }

    @FXML
    private AnchorPane pane1 ,pane2,pane3,pane4 , opacityPane,drawerPane,mainPane;

    @FXML
    private JFXButton homeBTN , homeBTN0 , statisticsBTN , patientBTN , medicineBTN , DiseasesBTN , diagnosisBTN , aboutBTN;
    @FXML
    private JFXButton statisticsBTN0 , patientBTN0 , medicineBTN0 , DiseasesBTN0 , diagnosisBTN0 , aboutBTN0,logoutBTN0;

    @FXML
    private ImageView drawerImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        opacityPane.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();


        pane1.setStyle("-fx-background-image: url(\"/sample/image/11.jpg\")");
        pane2.setStyle("-fx-background-image: url(\"/sample/image/22.jpg\")");
        pane3.setStyle("-fx-background-image: url(\"/sample/image/33.jpg\")");
        pane4.setStyle("-fx-background-image: url(\"/sample/image/44.jpg\")");

        Animation();


        drawerImage.setOnMouseClicked(event -> {

            opacityPane.setVisible(true);

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });

        opacityPane.setOnMouseClicked(event -> {



            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });


            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });

    }
    public  void  Animation(){


        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(3),pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(3),pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {

                FadeTransition fadeTransition2=new FadeTransition(Duration.seconds(3),pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                    FadeTransition fadeTransition00=new FadeTransition(Duration.seconds(3),pane2);
                    fadeTransition00.setFromValue(0);
                    fadeTransition00.setToValue(1);
                    fadeTransition00.play();


                    fadeTransition00.setOnFinished(event3 -> {
                        FadeTransition fadeTransition11=new FadeTransition(Duration.seconds(3),pane3);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {
                            FadeTransition fadeTransition22=new FadeTransition(Duration.seconds(3),pane4);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                Animation();
                            });
                        });


                    });
                });

            });




        });



    }

    @FXML
    public void BTN_Action(ActionEvent event) {
        System.out.println("------ HomePage Changed ------");
        AnchorPane newPane=null;
        if(event.getSource()==patientBTN ||event.getSource()==patientBTN0 ){
            newPane=main.MangeWindow("Patient.fxml");
            mainPane.getChildren().setAll(newPane);
        }else if(event.getSource()==medicineBTN ||event.getSource()==medicineBTN0){
            newPane=main.MangeWindow("Medicine.fxml");
            mainPane.getChildren().setAll(newPane);
        }else if(event.getSource()==aboutBTN ||event.getSource()==aboutBTN0){
            newPane=main.MangeWindow("AboutFxml.fxml");
            mainPane.getChildren().setAll(newPane);
        }else if(event.getSource()==DiseasesBTN ||event.getSource()==DiseasesBTN0){
            newPane=main.MangeWindow("Diseases.fxml");
            mainPane.getChildren().setAll(newPane);
        }else if(event.getSource()== statisticsBTN ||event.getSource()==statisticsBTN0){
            newPane=main.MangeWindow("StatisticsFxml.fxml");
            mainPane.getChildren().setAll(newPane);
        }else if(event.getSource()==homeBTN){
            main.HomePageWindow();
        }else if(event.getSource()==homeBTN0){
            main.UserHomePageWindow();
        }else if(event.getSource()== diagnosisBTN ||event.getSource()==diagnosisBTN0){
            newPane=main.MangeWindow("Diagnosis.fxml");
            mainPane.getChildren().setAll(newPane);
        }else if(event.getSource()== logoutBTN0)
            main.LoginScreenWindow();
    }
    @FXML
    private void closeButtonAction(){ main.closeStage(); }

    @FXML
    private void minimizeButtonAction(){ main.minimizeStage(); }
}
