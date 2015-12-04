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
				if (datePicker.getEditor().getText().length() > 0) {
					if (choiceBoxHour.getValue() != null && choiceBoxMinutes.getValue() != null) {
						if (textFieldMessageOfEvent.getText() != null && textFieldAdditionalInfo.getText() != null) {
							if (textFieldMessageOfEvent.getText().length() > 0 && textFieldAdditionalInfo.getText().length() > 0) {
								String[] date = datePicker.getEditor().getText().split("[,;:.!?\\s]+");
								controller.addEvent(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]),
										Integer.parseInt(choiceBoxHour.getValue()), Integer.parseInt(choiceBoxMinutes.getValue()), textFieldMessageOfEvent.getText(), textFieldAdditionalInfo.getText());
								datePicker.setValue(null);
								choiceBoxHour.setValue(null);
								choiceBoxMinutes.setValue(null);
								textFieldMessageOfEvent.setText(null);
								textFieldAdditionalInfo.setText(null);

								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Add Event");
								alert.setHeaderText("Done!");
								alert.setContentText("Event has added :)");
								alert.showAndWait();
							}
						}
					}
				}
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
        choiceBoxHour.setItems(FXCollections.observableArrayList("",
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        choiceBoxMinutes.setItems(FXCollections.observableArrayList("",
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
