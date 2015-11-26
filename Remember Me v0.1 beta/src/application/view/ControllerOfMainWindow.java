package application.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class ControllerOfMainWindow {

	@FXML
	private AnchorPane anchorPaneOfMainWindow;

	@FXML
	private Label labelCurrentDate;
	@FXML
	private Label labelCurrentTime;

	@FXML
	private MenuItem menuItemOpenListEvents;
	@FXML
	private MenuItem menuItemSaveListEvents;
	@FXML
	private MenuItem menuItemClose;
	@FXML
	private MenuItem menuItemAddEvent;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tabCurrentEvents;
	@FXML
	private Tab tabAddEvent;
	@FXML
	private Tab tabOptions;


	@FXML
	protected void initialize() {
		menuItemOpenListEvents.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open List Of Events");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("List Of Events", "*.ev"));
				File selectedFile = fileChooser.showOpenDialog(null);
				if (selectedFile != null) {
					//textOfSoundPath.setText(selectedFile.getPath());
				}
			}
		});
		menuItemSaveListEvents.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save List Of Events");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("List Of Events", "*.ev"));
				File selectedFile = fileChooser.showSaveDialog(null);
				if (selectedFile != null) {
					//textOfSoundPath.setText(selectedFile.getPath());
				}
			}
		});
		menuItemClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		menuItemAddEvent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
				selectionModel.select(1);
			}
		});
		menuItemAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Remember Me v0.1 beta");
				alert.setContentText("Developer: Ivan Greenfield, 350503, VMSiS, BSUIR");
				alert.showAndWait();
			}
		});

	}

	public AnchorPane getAnchorPaneOfMainWindow() {
		return anchorPaneOfMainWindow;
	}

	public int addWindowsInMainWindow(AnchorPane anchorPaneOfAddEventWindow, AnchorPane anchorPaneOfOptionsWindow) {
		this.tabAddEvent.setContent(anchorPaneOfAddEventWindow);
		this.tabOptions.setContent(anchorPaneOfOptionsWindow);

		return 0;
	}

	public int updateTime(int year, int month, int day, int hour, int minutes) {
		this.labelCurrentDate.setText("Current Date: " + day + "." + month + "." + year);
		this.labelCurrentTime.setText("Current Time: " + hour + ":" + minutes);
		return 0;
	}
}
