package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import sample.DOA.PatientDOA;
import sample.DOA.Statistic;

/**
 *
 * @author Mustafa Khaled
 */
public class StatisticController implements Initializable{
     @FXML
    private Label patientlbl;

    @FXML
    private Label totallbl;

    @FXML
    private ProgressIndicator percentage;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int patient=new Statistic().getPatientCount();
        int total=(new PatientDOA().getAllPatients().size());
        totallbl.setText(""+total);
        patientlbl.setText(""+patient);
        percentage.setProgress((double)patient/total);
        
    }
    
    
}
