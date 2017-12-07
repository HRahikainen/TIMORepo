package timo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class MainViewController implements Initializable{

    @FXML private Tab mapTab;
    @FXML private Button addSPBtn;
    @FXML private Button newPackageBtn;
    @FXML private Button sendPackageBtn;
    @FXML private Tab logTab;
    @FXML private Button clearMapBtn;
    @FXML private ComboBox<Package> choosePackageList;
    @FXML private ListView<?> logListView;
    @FXML private ComboBox<String> chooseCityList;
	@FXML private WebView wv;
	
	private ArrayList<SmartPost> smartPostList = new ArrayList<SmartPost>();
	private boolean infoWindowActive = false;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		wv.getEngine().load(getClass().getResource("index.html").toExternalForm());
		smartPostList = Xml2DataBuilder.parsePostData();
		for(SmartPost sp : smartPostList) {
			// Add each city only once
			if(!chooseCityList.getItems().contains(sp.getCity())){
				chooseCityList.getItems().add(sp.getCity());
			}
		}
	}
	
	@FXML void addMapMarkers(ActionEvent event) {
		String city = chooseCityList.getValue();
		for(SmartPost sp : smartPostList) {
			if(city.equals(sp.getCity())) {
				String searchString = sp.getAddress() + "," + sp.getCode() + " " + city;
				String infoString = sp.getPostoffice() + " " + sp.getAvailability();
				String color = "green";
				String scriptString = "document.goToLocation(" + "'" + searchString + "'" + "," +  "'" + infoString + "'"  + "," + "'" + color +  "'" + ")";
				System.out.println(scriptString);
				wv.getEngine().executeScript(scriptString);
				// Surprise, surprise, Hanko is missing
			}
		}
	}
	
	@FXML void deletePaths(ActionEvent event) {
		wv.getEngine().executeScript("document.deletePaths()");
		// Also remove calculated paths?
    }
	
	@FXML void sendPackage(ActionEvent event) {
		//wv.getEngine().executeScript("document.createPath(arraylist [start lat, start lon, end lat, end lon], String color, int packageClass)");
	}
	
	@FXML void fillPackageBox() {
		ArrayList<Package> packages = Storage.getInstance().getPackages();
		for(Package p : packages) {
			// Add each city only once
			if(!choosePackageList.getItems().contains(p)){
				choosePackageList.getItems().add(p);
			}
		}
	}
	
	@FXML public void packageInfoWindow(ActionEvent event) {
		// Prevent duplicates of this window
		if(!infoWindowActive) {
	        try {
	            Stage packageInfoStage = new Stage();
	            Parent page =
	            		FXMLLoader.load(getClass().getResource("PackageInfoView.fxml"));
	            Scene packageInfoScene = new Scene(page);
	            packageInfoStage.setScene(packageInfoScene);
	            packageInfoStage.setTitle("Pakettitiedot");
	            packageInfoStage.setMinHeight(600);
	            packageInfoStage.setMinWidth(600);
	            packageInfoStage.setOnHidden(e -> {fillPackageBox(); infoWindowActive = false;}); // See when to update list
	            packageInfoStage.show();
	            infoWindowActive = true;
	        } catch (IOException ex) {
	            System.err.println("Error occured.");
	       }
		}
    }

	
}
