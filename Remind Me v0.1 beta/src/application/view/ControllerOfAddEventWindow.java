package application.view;

import application.controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ControllerOfAddEventWindow {

	@FXML
	private AnchorPane anchorPaneOfAddEventWindow;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<String> choiceBoxHour;
	@FXML
	private ChoiceBox<String> choiceBoxMinutes;
	@FXML
	private TextField textFieldMessageOfEvent;
	@FXML
	private TextField textFieldAdditionalInfo;
	@FXML
	private Button buttonAddEvent;
	@FXML
	private Button buttonCancel;

    private Controller controller;

	@FXML
	protected void initialize(){
        buttonAddEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //add action
            }
        });
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                datePicker.setValue(null);
                choiceBoxHour.setValue(null);
                choiceBoxMinutes.setValue(null);
                textFieldMessageOfEvent.setText(null);
                textFieldAdditionalInfo.setText(null);
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
	}

	public AnchorPane getAnchorPaneOfAddEventWindow() {
		return anchorPaneOfAddEventWindow;
	}

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
