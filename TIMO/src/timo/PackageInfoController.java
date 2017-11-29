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
	    
	    @FXML private Button okButton;
	    @FXML private Button cancelButton;
	    @FXML private RadioButton radioBtn;

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    }
	    
	    @FXML public void okButtonClicked(ActionEvent event) {
	        PackageInfo.getInstance().setText("");
	        closeWindow(okButton);
	    }
	    
	    @FXML public void cancelButtonClicked(ActionEvent event) {
	        closeWindow(cancelButton);
	    }
	    
	    public void closeWindow(Button button) {
	        Stage scene = (Stage) button.getScene().getWindow();
	        scene.close();
	    }  
}
