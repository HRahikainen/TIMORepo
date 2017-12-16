package timo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class PackageInfoController implements Initializable {
	    /**
	     * Controller for package creation window
	     * */
		@FXML private Label errorLabel;
	    @FXML private Button createButton;
	    @FXML private Button cancelButton;
	    @FXML private RadioButton radioBtn1;
	    @FXML private RadioButton radioBtn2;
	    @FXML private RadioButton radioBtn3;
	    @FXML private ComboBox<String> destCityList;
	    @FXML private ComboBox<SmartPost> startSPList;
	    @FXML private ComboBox<SmartPost> destSPList;
	    @FXML private ComboBox<String> chooseItemList;
	    @FXML private ComboBox<String> startCityList;
	    

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	chooseItemList.getItems().add("Conscript");
	    	chooseItemList.getItems().add("DankMeme");
	    	chooseItemList.getItems().add("Pepe");
	    	chooseItemList.getItems().add("SamsungGalaxyNoteSeven");
	    	chooseItemList.getItems().add("Glasses");
	    	errorLabel.setVisible(false);
	    	
	    	for(SmartPost sp : SmartPostManager.getInstance().getMarkedPosts()) {
				// Add each city only once
				if(!destCityList.getItems().contains(sp.getCity())){
					destCityList.getItems().add(sp.getCity());
					startCityList.getItems().add(sp.getCity());
				}
			}
	    }
	    
	    @FXML public void createButtonClicked(ActionEvent event) {
	    	// Check if all package info is filled and create package and item accordingly
	    	Package p = null;
	    	String packageType = null;
	    	// These radio buttons are linked in a toggle group in FXML
	    	if(radioBtn1.isSelected()) {
	    		packageType = "1st class";
	    	}else if(radioBtn2.isSelected()) {
	    		packageType = "2nd class";
	    	}else if(radioBtn3.isSelected()) {
	    		packageType = "3rd class";
	    	}
	    	
	    	try {
	    		if((startSPList.getValue() == null) || (destSPList.getValue() == null) || (packageType == null) || (chooseItemList.getValue() == null)) {
		    		errorLabel.setVisible(true);
		    	} else {
		    		p = PackageFactory.getInstance().newPackage(packageType);
		    	}
			} catch (Exception e) {
				errorLabel.setVisible(true);
			}
	    
	    	if( p != null) {
	    		Item i = ItemFactory.getInstance().newItem(chooseItemList.getValue());
	    		if(i != null) {
	    			p.setInfo(i, startSPList.getValue(), destSPList.getValue(), p.getPackageClass());
	    			// Check if item fits the package limits
	    			if((p.getpItem().getMass() <= p.getWeightLimit()) && (p.getpItem().getSize() <= p.getSizeLimit())) {
	    				Storage.getInstance().add(p);
			    		closeWindow(createButton);
	    			} else {
	    				errorLabel.setText("Item's dimensions are too big for this package.");
	    				errorLabel.setVisible(true);
	    			}
	    		}
	    	}
	    }
	    
	    @FXML public void cancelButtonClicked(ActionEvent event) {
	        closeWindow(cancelButton);
	    }
	    
	    @FXML public void startCityChosed(ActionEvent event){
	    	// Parse cityName from startCityList and list city's SmartPosts
	    	startSPList.getItems().clear();
	    	String startCity = startCityList.getValue();
	    	for (SmartPost sp : SmartPostManager.getInstance().getMarkedPosts()) {
	    		if (sp.getCity().equals(startCity)) {
	    			startSPList.getItems().add(sp);
	    		}
	    	}
	    	
	    }
	    
	    @FXML public void destCityChosed(ActionEvent event){
	    	// Parse cityName from destCityList and list city's SmartPosts
	    	destSPList.getItems().clear();
	    	String destCity = destCityList.getValue();
	    	for (SmartPost sp : SmartPostManager.getInstance().getMarkedPosts()) {
	    		if (sp.getCity().equals(destCity)) {
	    			destSPList.getItems().add(sp);
	    		}
	    	}
	    }
	    
	    public void closeWindow(Button button) {
	        Stage scene = (Stage) button.getScene().getWindow();
	        scene.close();
	    }  
}
