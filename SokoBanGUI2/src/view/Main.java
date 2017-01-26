package view;

import controller.sokoban.MyController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			BorderPane root = loader.load(getClass().getResource("Sample.fxml").openStream());

			SampleController view = loader.getController();
		
			Scene scene = new Scene(root,600,600);

			MyModel model = new MyModel();
			MyController controller = new MyController(model, view);

			model.addObserver(controller);
			view.addObserver(controller);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//view.start();

			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
