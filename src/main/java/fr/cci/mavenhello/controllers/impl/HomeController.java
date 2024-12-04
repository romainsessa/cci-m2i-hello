package fr.cci.mavenhello.controllers.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.cci.mavenhello.model.DataModel;
import fr.cci.mavenhello.services.interfaces.IDataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
	
	/** Accès au service */
	private IDataService dataService;
	
	public void setDataService(IDataService dataService) {
		this.dataService = dataService;
	}
	
	/** Objet graphique */
	@FXML
	private Label label;
	@FXML
	private TextField field;
	@FXML 
	private VBox labels;
	
	/** Model de données */
	private List<DataModel> dataModels = new ArrayList<DataModel>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void init() {
		// Appel au service pour récupérer les données en BDD
		dataModels = dataService.getAll();
		// Mise à jour du Label principal
		updateLabel();
		// Ajout des données dans la VBox labels
		updateVBoxLabels();	
	}
	
	public void handleSave(ActionEvent event) {
		//Récupération de la saisie utilisateur
		String valueFromUser = field.getText();
		//Création d'un objet dataModel
		DataModel dataModel = new DataModel();
		dataModel.setLabel(valueFromUser);
		//Appel du service pour sauvegarde en BDD
		dataModel = dataService.save(dataModel);
		// Si sauvegarde réussi, MAJ du model et de la vue
		if(dataModel != null) {
			//Maj du model
			dataModels.add(dataModel);
			//Maj de la vue
			updateLabel();
			updateVBoxLabel(dataModel);	
		}
	}
	
	private void updateLabel() {
		label.setText(dataModels.size() + " label(s) en BDD");
	}
	
	private void updateVBoxLabel(DataModel dataModel) {
		labels.getChildren().add(
				new Label(dataModel.toString())
				);
	}
	
	private void updateVBoxLabels() {
		dataModels.forEach(
				dataModel -> updateVBoxLabel(dataModel)			
				);	
	}
}