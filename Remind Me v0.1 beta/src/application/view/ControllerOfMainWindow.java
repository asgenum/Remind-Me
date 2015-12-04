package application.view;

import application.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
	private ScrollPane scroll;
	@FXML
	private VBox stackedTitledPanes;

    private Controller controller;


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
                alert.setHeaderText("Remind Me v0.1 beta");
                alert.setContentText("Developer: Ivan Greenfield, 350503, VMSiS, BSUIR");
                alert.showAndWait();
            }
        });
		scroll.setMaxWidth(395);
		stackedTitledPanes.setMaxWidth(380);
        stackedTitledPanes.setex
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setPrefSize(380, 250);
		TitledPane titledPane = new TitledPane("Pane 199",  anchorPane);
        titledPane.setExpanded(false);
		//titledPane.setPrefSize(380, 250);
		stackedTitledPanes.getChildren().add(titledPane);
		stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
		stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));
        stackedTitledPanes.getChildren().add(new TitledPane("Pane 1",  new AnchorPane()));


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
        String stringDay, stringMonth, stringHour, stringMinutes;

        stringDay = (day < 10) ? ("0" + day) : (Integer.toString(day));
        stringMonth = (month < 10) ? ("0" + month) : (Integer.toString(month));
        stringHour = (hour < 10) ? ("0" + hour) : (Integer.toString(hour));
        stringMinutes = (minutes < 10) ? ("0" + minutes) : (Integer.toString(minutes));

        this.labelCurrentDate.setText("Current Date: " + stringDay + "." + stringMonth + "." + year);
        this.labelCurrentTime.setText("Current Time: " + stringHour + ":" + stringMinutes);
        return 0;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
