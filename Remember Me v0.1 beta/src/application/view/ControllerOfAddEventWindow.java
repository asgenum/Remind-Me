package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ControllerOfAddEventWindow {

	@FXML
	private AnchorPane anchorPaneOfAddEventWindow;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<Integer> choiceBoxHour;
	@FXML
	private ChoiceBox<Integer> choiceBoxMinutes;
	@FXML
	private TextField textFieldMessageOfEvent;
	@FXML
	private TextField textFieldAdditionalInfo;
	@FXML
	private Button buttonAddEvent;
	@FXML
	private Button buttonCancel;

	@FXML
	protected void initialize(){
	}

	public AnchorPane getAnchorPaneOfAddEventWindow() {
		return anchorPaneOfAddEventWindow;
	}
}
