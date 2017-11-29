package timo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class PackageInfoController implements Initializable {
	    
	    @FXML private Button createButton;
	    @FXML private Button cancelButton;
	    @FXML private RadioButton radioBtn1;
	    @FXML private RadioButton radioBtn2;
	    @FXML private RadioButton radioBtn3;

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    }
	    
	    @FXML public void createButtonClicked(ActionEvent event) {
	        PackageInfo.getInstance().setText("");
	        closeWindow(createButton);
	    }
	    
	    @FXML public void cancelButtonClicked(ActionEvent event) {
	        closeWindow(cancelButton);
	    }
	    
	    public void closeWindow(Button button) {
	        Stage scene = (Stage) button.getScene().getWindow();
	        scene.close();
	    }  
}
