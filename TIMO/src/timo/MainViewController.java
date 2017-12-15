package timo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class MainViewController implements Initializable{

    @FXML private Tab mapTab;
    @FXML private Button addSPBtn;
    @FXML private Button newPackageBtn;
    @FXML private Button sendPackageBtn;
    @FXML private Tab logTab;
    @FXML private Button clearMapBtn;
    @FXML private Label packageErrorLabel;
    @FXML private ListView<String> logListView;
    @FXML private ComboBox<String> chooseCityList;
	@FXML private WebView wv;
	@FXML private ComboBox<Package> choosePackageList;
	
	private boolean infoWindowActive = false;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		packageErrorLabel.setVisible(false);
		wv.getEngine().load(getClass().getResource("index.html").toExternalForm());
		SmartPostManager.getInstance().setPosts(Xml2DataBuilder.parsePostData());
		for(SmartPost sp : SmartPostManager.getInstance().getPosts()) {
			// Add each city only once
			if(!chooseCityList.getItems().contains(sp.getCity())){
				chooseCityList.getItems().add(sp.getCity());
			}
		}
	}
	
	@FXML void addMapMarkers(ActionEvent event) {
		String city = chooseCityList.getValue();
		for(SmartPost sp : SmartPostManager.getInstance().getPosts()) {
			if(city.equals(sp.getCity())) {
				String searchString = sp.getAddress() + "," + sp.getCode() + " " + city;
				String infoString = sp.getPostoffice() + " " + sp.getAvailability();
				String color = "green";
				String scriptString = "document.goToLocation(" + "'" + searchString + "'" + "," +  "'" + infoString + "'"  + "," + "'" + color +  "'" + ")";
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
		// Move parsing elsewhere, possibly straight from xml? And catch exceptions!
		// Get start and end points for currently chosen package and check distance and other Package-specific logic
		packageErrorLabel.setVisible(false);
		ArrayList<Float> coords = new ArrayList<Float>(); 
		try {
			SmartPost sp1 = choosePackageList.valueProperty().getValue().getStartPoint();
			SmartPost sp2 = choosePackageList.valueProperty().getValue().getDestination();
			Collections.addAll(coords, Float.parseFloat(sp1.getGp().getLat()), Float.parseFloat(sp1.getGp().getLng()), Float.parseFloat(sp2.getGp().getLat()), Float.parseFloat(sp2.getGp().getLng()));
			// A custom JavaScript function to only return the distance between start and destination, see index.html
			double dist = (double) wv.getEngine().executeScript("document.routeLength(" +  coords + ")");
			// Check if FirstClassPackage is being sent too far
			if((dist > 150) && (choosePackageList.valueProperty().getValue().getPackageClass() == 1)) {
				packageErrorLabel.setText("Ensimmäisen luokan paketti ei voi kulkea yli 150 km matkaa");
				packageErrorLabel.setVisible(true);
				logListView.getItems().add(choosePackageList.valueProperty().getValue() + " was not delivered because FirstClassPackages can't go beyond 150 km.");
			}else {
				dist = (double) wv.getEngine().executeScript("document.createPath(" + coords + ", 'red'," + choosePackageList.valueProperty().getValue().getPackageClass()+ ")");
				logListView.getItems().add(choosePackageList.valueProperty().getValue() + " was delivered. " 
						+ choosePackageList.valueProperty().getValue().getpItem().getClass().getSimpleName() 
						+  (choosePackageList.valueProperty().getValue().getpItem().getBroken() ? " was broken in delivery" : " was delivered safely"));
			}
			System.out.println(dist);
			
		}catch (NullPointerException e) {
			packageErrorLabel.setText("Valitse tai luo ensin paketti!");
			packageErrorLabel.setVisible(true);
		}catch (ClassCastException e) {
			// Integer to Double error when start == end
			packageErrorLabel.setText("Lähtö- ja päätepiste sama. Ei lähetetä.");
			packageErrorLabel.setVisible(true);
			logListView.getItems().add(choosePackageList.valueProperty().getValue() + " was not delivered because start and destination are the same.");
		}
		
		
	}
	
	@FXML void fillPackageBox() {
		for(Package p: Storage.getInstance().getPackages()) {
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
	            packageInfoScene.getStylesheets().add(getClass().getResource("info.css").toExternalForm());
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
