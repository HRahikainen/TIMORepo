package timo;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
			AnchorPane root = loader.load();
			MainViewController mvController = loader.getController();
			Scene mainScene = new Scene(root);
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("TIMO Package Manager");
			primaryStage.setMinHeight(800);
			primaryStage.setMinWidth(800);
			primaryStage.setOnCloseRequest(e -> {mvController.logListEventHandler();Platform.exit();}); // Write logs and close application on main window close.
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
