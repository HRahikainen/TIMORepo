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
	    
		@FXML private Label errorLabel;
	    @FXML private Button createButton;
	    @FXML private Button cancelButton;
	    @FXML private RadioButton radioBtn1;
	    @FXML private RadioButton radioBtn2;
	    @FXML private RadioButton radioBtn3;
	    @FXML private ComboBox<String> destCityList;
	    @FXML private ComboBox<String> startSPList;
	    @FXML private ComboBox<String> destSPList;
	    @FXML private ComboBox<String> chooseItemList;
	    @FXML private ComboBox<String> startCityList;
	    private String destCity;
	    private String startCity;
	    

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	chooseItemList.getItems().add("Conscript");
	    	chooseItemList.getItems().add("DankMeme");
	    	chooseItemList.getItems().add("Pepe");
	    	chooseItemList.getItems().add("SamsungGalaxyNoteSeven");
	    	chooseItemList.getItems().add("Glasses");
	    	errorLabel.setVisible(false);
	    	
	    	for(SmartPost sp : SmartPostManager.getInstance().getPosts()) {
				// Add each city only once
				if(!destCityList.getItems().contains(sp.getCity())){
					destCityList.getItems().add(sp.getCity());
					startCityList.getItems().add(sp.getCity());
				}
			}
	    }
	    
	    @FXML public void createButtonClicked(ActionEvent event) {
	    	Package p = null;
	    	String packageType = null;
	    	
	    	if(radioBtn1.isSelected()) {
	    		packageType = "1.luokka";
	    	}else if(radioBtn2.isSelected()) {
	    		packageType = "2.luokka";
	    	}else if(radioBtn3.isSelected()) {
	    		packageType = "3.luokka";
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
	    		// Possibly redundant if-clause
	    		if(i != null) {
	    			// Search for start and end SmartPost objects, move this to Manager class later
	    			/*for(SmartPost sp : SmartPostManager.getInstance().getPosts()) {
	    				if(sp.getCity().equals(startCityList.getValue()) && sp.getAddress().equals(startSPList.getValue())) {
	    					
	    				}
	    			}*/
	    			p.setInfo(i, startCityList.getValue() +  " " + startSPList.getValue(), destCityList.getValue() +  " " + destSPList.getValue(), p.getClass().getSimpleName());
		    		p.printInfo();
		    		Storage.getInstance().getPackages().add(p);
		    		closeWindow(createButton);
	    		}
	    		
	    	}
	    }
	    
	    @FXML public void cancelButtonClicked(ActionEvent event) {
	        closeWindow(cancelButton);
	    }
	    
	    @FXML public void startCityChosed(ActionEvent event){
	    	// Parse cityName from startCityList and list city's SmartPosts
	    	startSPList.getItems().clear();
	    	startCity = startCityList.getValue();
	    	System.out.println("Valitsit lähtökaupungiksi: " + startCity);
	    	for (SmartPost sp : SmartPostManager.getInstance().getPosts()) {
	    		if (sp.getCity().equals(startCity)) {
	    			System.out.println(sp.getAddress());
	    			startSPList.getItems().add(sp.getAddress());
	    		}
	    	}
	    	
	    }
	    
	    @FXML public void destCityChosed(ActionEvent event){
	    	// Parse cityName from destCityList and list city's SmartPosts
	    	destSPList.getItems().clear();
	    	destCity = destCityList.getValue();
	    	System.out.println("Valitsit määränpääkaupungiksi: " + destCity);
	    	for (SmartPost sp : SmartPostManager.getInstance().getPosts()) {
	    		if (sp.getCity().equals(destCity)) {
	    			System.out.println(sp.getAddress());
	    			destSPList.getItems().add(sp.getAddress());
	    		}
	    	}
	    }
	    
	    public void closeWindow(Button button) {
	        Stage scene = (Stage) button.getScene().getWindow();
	        scene.close();
	    }  
}
