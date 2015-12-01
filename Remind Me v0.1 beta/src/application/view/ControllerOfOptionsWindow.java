package application.view;

import application.controller.Controller;
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
				"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
		choiceBoxMinutes.setItems(FXCollections.observableArrayList(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
                "51", "52", "53", "54", "55", "56", "57", "58", "59"));

        buttonApply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(datePicker.getEditor().getText().length() > 0){
                    //datePicker.getEditor().getText().split(".");
                    System.out.println(datePicker.getEditor().getText().split("."));
                    //controller.changeDate();
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
