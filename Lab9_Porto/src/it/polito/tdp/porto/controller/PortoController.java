package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class PortoController {

	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<Creator> boxAutore1;

	@FXML
	private ChoiceBox<Creator> boxAutore2;

	@FXML
	private Button btnVisualizzaCluster;

	@FXML
	private Button btnVisualizzaCoautori;

	@FXML
	private Button btnVisualizzaArticoli;

	@FXML
	private TextArea txtRisultato;

	@FXML
	private Button btnReset;

	public void setModel(Model model) {
		this.model = model;
		model.creaGrafo();
		List<Creator> autori = model.getAllCreators();
		Collections.sort(autori);
		autori.add(0, null);
		boxAutore1.getItems().addAll(autori);
		boxAutore2.getItems().addAll(autori);
	}

	@FXML
	void doReset(ActionEvent event) {
		txtRisultato.clear();
		boxAutore1.getSelectionModel().selectFirst();
		boxAutore2.getSelectionModel().selectFirst();
	}

	@FXML
	void doVisualizzaArticoli(ActionEvent event) {
		txtRisultato.clear();
		Creator c1 = boxAutore1.getValue();
		Creator c2 = boxAutore2.getValue();
		if (c1 != null && c2 != null) {
			List<Article> collaborazioni = model.getCollaborazioni(c1, c2);
			if (!collaborazioni.isEmpty()) {
				txtRisultato.appendText(c1 + " e " + c2 + " hanno collaborato insieme a:\n");
				for (Article a : collaborazioni) {
					txtRisultato.appendText("\t" + a + "\n");
				}
			} else
				txtRisultato.appendText(c1 + " e " + c2 + " non hanno collaborato insieme.");
		} else
			txtRisultato.appendText("Selezionare due autori.");
	}

	@FXML
	void doVisualizzaCluster(ActionEvent event) {
		txtRisultato.clear();
		if (boxAutore1.getValue() == null && boxAutore2.getValue() == null) {
			List<Creator> autori = model.getAllCreators();
			for (Creator c1 : autori) {
				if (c1 != null) {
					List<Creator> coautori = model.getCoautori(c1);
					Collections.sort(coautori);
					if (!coautori.isEmpty()) {
						txtRisultato.appendText("I coautori di " + c1 + " sono:\n");
						for (Creator c2 : coautori) {
							txtRisultato.appendText("\t" + c2 + "\n");
						}
						txtRisultato.appendText("\n");
					} else
						txtRisultato.appendText(c1 + " non ha collaborato con nessuno.\n\n");
				}
			}
		} else
			txtRisultato.setText("Deselezionare gli autori.");
	}

	@FXML
	void doVisualizzaCoautori(ActionEvent event) {
		txtRisultato.clear();
		Creator autore = null;
		if (boxAutore2.getValue() == null) {
			autore = boxAutore1.getValue();
		} else if (boxAutore1.getValue() == null)
			autore = boxAutore2.getValue();
		if (autore != null) {
			List<Creator> coautori = model.getCoautori(autore);
			Collections.sort(coautori);
			if (!coautori.isEmpty()) {
				txtRisultato.setText("I coautori di " + autore + " sono:\n");
				for (Creator c : coautori)
					txtRisultato.appendText("\t" + c + "\n");
			} else
				txtRisultato.setText(autore + " non ha collaborato con nessuno.");
		} else
			txtRisultato.setText("Selezionare un autore.");
	}

	@FXML
	void initialize() {
		assert boxAutore1 != null : "fx:id=\"boxAutore1\" was not injected: check your FXML file 'Porto.fxml'.";
		assert boxAutore2 != null : "fx:id=\"boxAutore2\" was not injected: check your FXML file 'Porto.fxml'.";
		assert btnVisualizzaCluster != null : "fx:id=\"btnVisualizzaCluster\" was not injected: check your FXML file 'Porto.fxml'.";
		assert btnVisualizzaCoautori != null : "fx:id=\"btnVisualizzaCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
		assert btnVisualizzaArticoli != null : "fx:id=\"btnVisualizzaArticoli\" was not injected: check your FXML file 'Porto.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Porto.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Porto.fxml'.";
		txtRisultato.setEditable(false);
	}
}
