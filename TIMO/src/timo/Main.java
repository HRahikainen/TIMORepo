package timo;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainView.fxml"));
			Scene mainScene = new Scene(root);
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("TIMO Package Manager");
			primaryStage.setMinHeight(800);
			primaryStage.setMinWidth(800);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for(SmartPost sp : Xml2DataBuilder.parsePostData()) {
			System.out.println(sp.getCity() + " " + sp.getAddress());
		}
		DBHandler.createNewDatabase("test.db");
		launch(args);
	}
}
