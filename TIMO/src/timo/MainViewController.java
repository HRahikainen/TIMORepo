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
import javafx.scene.control.TabPane;

public class MainViewController implements Initializable{

    @FXML private Tab mapTab;
    @FXML private Button addSPBtn;
    @FXML private Button newPackageBtn;
    @FXML private Button sendPackageBtn;
    @FXML private Tab logTab;
    @FXML private Button clearMapBtn;
    @FXML private Label packageErrorLabel;
    @FXML private ListView<String> logListView;
    @FXML private Label packageCountLabel;
    @FXML private ComboBox<String> chooseCityList;
	@FXML private WebView wv;
	@FXML private ComboBox<Package> choosePackageList;
	
	@FXML private Button freshBtn;
	@FXML private Button recoveryBtn;
	@FXML private Tab recoveryTab;
	@FXML private TabPane tabPane;
	
	private boolean infoWindowActive = false;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Initialize package count
		logTab.setOnSelectionChanged(e -> packageCountLabel.setText("Packages through system: "
									+  Storage.getInstance().getCount() +  " Packages in store: " 
									+ Storage.getInstance().getPackages().size()));
		packageErrorLabel.setVisible(false);
		// Read past logs and packages into session
		logListView.getItems().addAll(LogHandler.readLogStrings());
		// Load WebView and parse XML
		wv.getEngine().load(getClass().getResource("index.html").toExternalForm());
		SmartPostManager.getInstance().setPosts(Xml2DataBuilder.parsePostData());
		for(SmartPost sp : SmartPostManager.getInstance().getPosts()) {
			// Add each city only once
			if(!chooseCityList.getItems().contains(sp.getCity())){
				chooseCityList.getItems().add(sp.getCity());
			}
		}
	}
	
	@FXML void recoverStorage(ActionEvent event) {
		// Restores last storage packages
		mapTab.setDisable(false);
		logTab.setDisable(false);
		tabPane.getSelectionModel().selectNext();
		
		LogHandler.readPackages();
		Storage.getInstance().setCount();
		fillPackageBox();
		recoveryTab.setDisable(true);
	}
	@FXML void startFresh(ActionEvent event) {
		// starts program without any packages
		mapTab.setDisable(false);
		logTab.setDisable(false);
		tabPane.getSelectionModel().selectNext();
		recoveryTab.setDisable(true);

	}
	
	
	
	@FXML void addMapMarkers(ActionEvent event) {
		// Add markers via JavaScript method.
		packageErrorLabel.setVisible(false);
		ArrayList<SmartPost> tmpList = new ArrayList<SmartPost>();
		try {
			String city = chooseCityList.getValue();
			for(SmartPost sp : SmartPostManager.getInstance().getPosts()) {
				if(city.equals(sp.getCity())) {
					String searchString = sp.getAddress() + "," + sp.getCode() + " " + city;
					String infoString = sp.getPostoffice() + " " + sp.getAvailability();
					String color = "green";
					String scriptString = "document.goToLocation(" + "'" + searchString + "'" + "," +  "'" + infoString + "'"  + "," + "'" + color +  "'" + ")";
					wv.getEngine().executeScript(scriptString);
					tmpList.add(sp);
				}
			}
		} catch(NullPointerException e) {
			packageErrorLabel.setText("Choose a city from the list first!");
			packageErrorLabel.setVisible(true);
		}
		// Add marked posts to list if not in there, check is to avoid button spam problems
		for(SmartPost sp : tmpList) {
			if(!SmartPostManager.getInstance().getMarkedPosts().contains(sp)) {
				SmartPostManager.getInstance().getMarkedPosts().add(sp);
			}
		}
		tmpList.clear();
	}
	
	@FXML void deletePaths(ActionEvent event) {
		wv.getEngine().executeScript("document.deletePaths()");
    }
	
	@FXML void sendPackage(ActionEvent event) {
		// Get start and end points for currently chosen package and check distance and other Package-specific logic
		packageErrorLabel.setVisible(false);
		ArrayList<Float> coords = new ArrayList<Float>(); 
		try {
			Package chosenPackage = choosePackageList.valueProperty().getValue();
			SmartPost sp1 = chosenPackage.getStartPoint();
			SmartPost sp2 = chosenPackage.getDestination();
			Collections.addAll(coords, sp1.getGp().getLat(), sp1.getGp().getLng(), sp2.getGp().getLat(), sp2.getGp().getLng());
			// A custom JavaScript function to only return the distance between start and destination, see index.html
			double dist = (double) wv.getEngine().executeScript("document.routeLength(" +  coords + ")");
			// Check if FirstClassPackage is being sent too far
			if((dist > 150) && (chosenPackage.getPackageClass() == 1)) {
				packageErrorLabel.setText("First class package can't travel over 150km");
				packageErrorLabel.setVisible(true);
				logListView.getItems().add(chosenPackage + " was not delivered because FirstClassPackages can't go beyond 150 km.");
			}else {
				dist = (double) wv.getEngine().executeScript("document.createPath(" + coords + ", 'red'," + chosenPackage.getPackageClass()+ ")");
				// Private method where fragility is checked
				determineBreak(chosenPackage);
				logListView.getItems().add(chosenPackage + " was delivered.\n" 
						+ chosenPackage.getpItem().getClass().getSimpleName() 
						+  (chosenPackage.getpItem().getBroken() ? " was broken in delivery" : " was delivered safely") 
						+ "\nDistance travelled: " + dist + "km");
			}
			
		}catch (NullPointerException e) {
			packageErrorLabel.setText("Choose or create a package first!");
			packageErrorLabel.setVisible(true);
		}catch (ClassCastException e) {
			// Integer to Double error when start == end?
			packageErrorLabel.setText("Starting point and destination are the same. Package not sent.");
			packageErrorLabel.setVisible(true);
			logListView.getItems().add(choosePackageList.valueProperty().getValue() + " was not delivered because start and destination are the same.");
		}
		// Remove from list unless already empty
		Storage.getInstance().getPackages().remove(choosePackageList.valueProperty().getValue());
		choosePackageList.getItems().remove(choosePackageList.valueProperty().getValue());
	}
	
	@FXML void fillPackageBox() {
		// Check to see if Storage has new packages when the infoWindow is closed.
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
	            packageInfoStage.setTitle("Package Information");
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
	
	private void determineBreak(Package p) {
		// First class and fragile always breaks, 3rd class and too small and fragile breaks
		if((p.getPackageClass() == 1) && (p.getpItem().getFragile())) {
			p.getpItem().breakItem();
		}else if((p.getPackageClass() == 3) && (p.getpItem().getFragile()) && ((p.getpItem().getMass() <= (0.5 * p.getWeightLimit())) && (p.getpItem().getSize() <= (0.5 * p.getSizeLimit())))) {
			p.getpItem().breakItem();
		}
		
		if(p.getpItem().getClass().getSimpleName().equals("SamsungGalaxyNoteSeven")) {
			// Always breaks and burns, makes a sound when burning
			p.getpItem().breakItem();
			PlayBreakingAudio.play();
		}
	}

	public void logListEventHandler(){
		// Called when program exits to write logs
		logListView.getItems().add("Packages through system: "
									+ Storage.getInstance().getCount() +  " Packages in store: " 
									+ Storage.getInstance().getPackages().size());
		LogHandler.writeLog(logListView.getItems());
		LogHandler.writeLogStrings(logListView.getItems());
		LogHandler.writePackages(Storage.getInstance().getPackages());
	}
}
