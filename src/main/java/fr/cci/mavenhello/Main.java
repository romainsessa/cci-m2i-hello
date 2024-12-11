package fr.cci.mavenhello;

import java.net.URL;
import java.sql.Connection;

import fr.cci.mavenhello.controllers.impl.HomeController;
import fr.cci.mavenhello.repositories.db.MysqlManager;
import fr.cci.mavenhello.repositories.impl.DataRepository;
import fr.cci.mavenhello.repositories.interfaces.IDataRepository;
import fr.cci.mavenhello.services.impl.DataService;
import fr.cci.mavenhello.services.interfaces.IDataService;
import fr.cci.mavenhello.websocket.WebSocketService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Construction des objets pour l'injection de dépendances.
		Connection connection = MysqlManager.getInstance().getConnection();
		IDataRepository dataRepository = new DataRepository(connection);
		IDataService dataService = new DataService(dataRepository);
		
		// Chargement du FXML et du contrôleur associé
		FXMLLoader loader = new FXMLLoader(
				getClass().getClassLoader().getResource("fxml/home.fxml"));
		VBox root = (VBox) loader.load();
		
		// Récupération de l'instance du contrôleur et injection de 
		// du service dans le contrôleur
		HomeController  homeController = 
				(HomeController) loader.getController();
		homeController.setDataService(dataService);
		homeController.init();
		
		// Création de la scène grâce au FXML qui a été chargé
		Scene scene = new Scene(root);
		
		// Chargement du CSS et ajout à la scène
		URL cssFile = getClass().getClassLoader().getResource(
				"stylesheets/hello.css");
		scene.getStylesheets().add(cssFile.toString());
		
		// Chargement de la scène dans le stage
		primaryStage.setScene(scene);		
		
		// Configuration de la fenêtre
		primaryStage.setTitle("Hello Application");
		primaryStage.setWidth(300);
		primaryStage.setHeight(300);
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
	
	@Override
	public void stop() throws Exception {
		WebSocketService.getInsance().close();
		super.stop();
	}

}
