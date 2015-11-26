package application.view;

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
	}

	public AnchorPane getAnchorPaneOfOptionsWindow() {
		return anchorPaneOfOptionsWindow;
	}
}
