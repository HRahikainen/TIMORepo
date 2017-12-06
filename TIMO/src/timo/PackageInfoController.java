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
	    	String packageType = null;
	    	// Should these be hardcoded Strings or fetched from the UI? Which is more reliable?
	    	if(radioBtn1.isSelected()) {
	    		packageType = "1.luokka";
	    	}else if(radioBtn2.isSelected()) {
	    		packageType = "2.luokka";
	    	}else if(radioBtn3.isSelected()) {
	    		packageType = "3.luokka";
	    	}
	    	Package p = PackageFactory.getInstance().newPackage(packageType); // Send params here or via setter?
	    	if( p != null) {
	    		Storage.getInstance().getPackages().add(p);
	    	}
	        //System.out.println(Storage.getInstance().getPackages());
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
