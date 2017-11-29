package timo;

import java.io.IOException;
import java.net.URL;
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
    @FXML private Tab logTab;
    @FXML private Button clearMapBtn;
    @FXML private ComboBox<?> choosePackageList;
    @FXML private ListView<?> logListView;
    @FXML private ComboBox<?> chooseCityList;
	@FXML private WebView wv;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		wv.getEngine().load(getClass().getResource("index.html").toExternalForm());
	}
	
	@FXML public void packageInfoWindow(ActionEvent event) {
        try {
            Stage packageInfoStage = new Stage();
            Parent page =
            		FXMLLoader.load(getClass().getResource("PackageInfoView.fxml"));
            Scene packageInfoScene = new Scene(page);
            packageInfoStage.setScene(packageInfoScene);
            packageInfoStage.setTitle("Pakettitiedot");
            packageInfoStage.show();
        } catch (IOException ex) {
            System.err.println("Error occured.");
        }
    }

	
}
