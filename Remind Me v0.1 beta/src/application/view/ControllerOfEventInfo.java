package application.view;

import application.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerOfEventInfo {

	private AnchorPane anchorPane;
	private DatePicker datePicker;
	private ChoiceBox<String> choiceBoxHour;
	private ChoiceBox<String> choiceBoxMinutes;
	private TextField textFieldMessageOfEvent;
	private TextField textFieldAdditionalInfo;
	private Button buttonAddEvent;
	private Button buttonCancel;

	private Controller controller;

	public ControllerOfEventInfo(Controller controller, int yearOfEvent, int monthOfEvent, int dayOfEvent, int hourOfEvent, int minuteOfEvent, String messageOfEvent, String additionalInfo){
		this.controller = controller;
		anchorPane = new AnchorPane();
        anchorPane.setMaxSize(385, 250);
	}
}
