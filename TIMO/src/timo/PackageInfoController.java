package timo;

import java.net.URL;
import java.util.ArrayList;
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
	    
	    private ArrayList<SmartPost> smartPostList = new ArrayList<SmartPost>();

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	chooseItemList.getItems().add("Conscript");
	    	chooseItemList.getItems().add("DankMeme");
	    	chooseItemList.getItems().add("Pepe");
	    	chooseItemList.getItems().add("SamsungGalaxyNoteSeven");
	    	chooseItemList.getItems().add("Glasses");
	    	errorLabel.setVisible(false);
	    	
	    	smartPostList = Xml2DataBuilder.parsePostData();
	    	//TODO: maybe get ArrayList from MainViewController instead of reloading from file?
	    	
	    	for(SmartPost sp : smartPostList) {
				// Add each city only once
				if(!destCityList.getItems().contains(sp.getCity())){
					destCityList.getItems().add(sp.getCity());
					startCityList.getItems().add(sp.getCity());
				}
			}
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
	    	}else {
	    		// TODO: Handle empty selection here
	    		errorLabel.setVisible(true);
	    		
	    	}
	    	Package p = PackageFactory.getInstance().newPackage(packageType);
	    	// TODO:Check these before creation of objects!!!!!!!!!!!!!!!!!!
	    	if( p != null) {
	    		Item i = ItemFactory.getInstance().newItem(chooseItemList.getValue());
	    		if(i != null) {
	    			p.setInfo(i, startCityList.getValue() +  " " + startSPList.getValue(), destCityList.getValue() +  " " + destSPList.getValue());
		    		p.printInfo();
		    		Storage.getInstance().getPackages().add(p);
		    		closeWindow(createButton);
	    		}
	    		
	    	}
	        //System.out.println(Storage.getInstance().getPackages());
	        
	    }
	    
	    @FXML public void cancelButtonClicked(ActionEvent event) {
	        closeWindow(cancelButton);
	    }
	    
	    @FXML public void destCityChosed(ActionEvent event){
	    	// Parse cityName from startCityList and list city's SmartPosts
	    }
	    
	    public void closeWindow(Button button) {
	        Stage scene = (Stage) button.getScene().getWindow();
	        scene.close();
	    }  
}
