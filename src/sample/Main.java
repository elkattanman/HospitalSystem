package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Model.MainModel;

public class Main extends Application {

    Stage stage;
    MainModel main;
    @Override
    public void start(Stage primaryStage){
        this.stage=primaryStage;
        main=new MainModel(stage);
        main.SplashWindow();
    }

    public static void main(String[] args) { launch(args); }
}
