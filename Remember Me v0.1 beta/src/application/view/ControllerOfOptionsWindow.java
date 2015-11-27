package application.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class ControllerOfOptionsWindow {

	@FXML
	private AnchorPane anchorPaneOfOptionsWindow;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<Integer> choiceBoxHour;
	@FXML
	private ChoiceBox<Integer> choiceBoxMinutes;
	@FXML
	private TextField textOfSoundPath;
	@FXML
	private Button buttonApply;
	@FXML
	private Button buttonCancel;
	@FXML
	private Button buttonChangeDirOfSound;

	@FXML
	protected void initialize(){
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

		choiceBoxHour.setItems(FXCollections.observableArrayList(
				0, 01, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
		choiceBoxMinutes.setItems(FXCollections.observableArrayList(
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
				31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));

	}

	public AnchorPane getAnchorPaneOfOptionsWindow() {
		return anchorPaneOfOptionsWindow;
	}
}
