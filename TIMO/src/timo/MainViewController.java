package timo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

public class MainViewController implements Initializable{

	@FXML private WebView wv;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		wv.getEngine().load(getClass().getResource("index.html").toExternalForm());
	}
	
}
