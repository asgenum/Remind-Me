package application.view;

import application.controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class ControllerOfOptionsWindow {

	@FXML
	private AnchorPane anchorPaneOfOptionsWindow;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<String> choiceBoxHour;
	@FXML
	private ChoiceBox<String> choiceBoxMinutes;
	@FXML
	private TextField textOfSoundPath;
	@FXML
	private Button buttonApply;
	@FXML
	private Button buttonCancel;
	@FXML
	private Button buttonChangeDirOfSound;

	private Controller controller;

	@FXML
	protected void initialize() {
		buttonChangeDirOfSound.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Replay File");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Sound Files", "*.mp3"));
				File selectedFile = fileChooser.showOpenDialog(null);
				if (selectedFile != null) {
					textOfSoundPath.setText(selectedFile.getPath());
				}
			}
		});

		choiceBoxHour.setItems(FXCollections.observableArrayList("",
				"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
		choiceBoxMinutes.setItems(FXCollections.observableArrayList("",
				"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53", "54", "55", "56", "57", "58", "59"));

		buttonApply.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				boolean isChangedSettings = false;
				if (datePicker.getEditor().getText().length() > 0) {
					String[] date = datePicker.getEditor().getText().split("[,;:.!?\\s]+");
					controller.changeDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
					isChangedSettings = true;
				}
				if (choiceBoxHour.getValue() != null && choiceBoxMinutes.getValue() != null) {
					if (choiceBoxHour.getValue().length() > 0 && choiceBoxMinutes.getValue().length() > 0) {
						controller.changeTime(Integer.parseInt(choiceBoxHour.getValue()), Integer.parseInt(choiceBoxMinutes.getValue()));
						isChangedSettings = true;
					}
				}
				if (textOfSoundPath.getText() != null && textOfSoundPath.getText().length() > 0) {
					controller.setPathToSound(textOfSoundPath.getText());
					isChangedSettings = true;
				}
				if (isChangedSettings == true) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Settings");
					alert.setHeaderText(null);
					alert.setContentText("Done!");
					alert.showAndWait();

					datePicker.setValue(null);
					choiceBoxHour.setValue(null);
					choiceBoxMinutes.setValue(null);
					textOfSoundPath.setText(null);
				}
			}
		});

		buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				datePicker.setValue(null);
				choiceBoxHour.setValue(null);
				choiceBoxMinutes.setValue(null);
				textOfSoundPath.setText(null);
			}
		});

	}

	public AnchorPane getAnchorPaneOfOptionsWindow() {
		return anchorPaneOfOptionsWindow;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
