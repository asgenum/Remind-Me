package application.view;

import application.controller.Controller;
import application.model.Event;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.util.Vector;

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
	private TableView<String> tableOfEvents;
	@FXML
	private TableColumn<String, String> tableColumn;
	@FXML
	private TextField dateOfEvent;
	@FXML
	private TextField timeOfEvent;
	@FXML
	private TextField messageOfEvent;
	@FXML
	private TextField additionalInfo;
	@FXML
	private Button buttonDeleteEvent;

	private ObservableList<String> listOfEvents = FXCollections.observableArrayList();

    private Controller controller;

	private int lastSelectedEvent;


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
                    controller.readEventListToFile(selectedFile.getPath());
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
					controller.writeEventListToFile(selectedFile.getPath());
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
		tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> stringStringCellDataFeatures) {
				return new ReadOnlyStringWrapper(stringStringCellDataFeatures.getValue());
			}
		});

		tableOfEvents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observableValue, String oldValue, String newValue) {
				if (tableOfEvents.getSelectionModel().getSelectedItem() != null) {
					TableView.TableViewSelectionModel selectionModel = tableOfEvents.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					String val = (String) tablePosition.getTableColumn().getCellData(newValue);
					//System.out.println("Selected Value" + val);
					System.out.println("Pos " + tablePosition.getRow());
					controller.getEventInfo(tablePosition.getRow());
					lastSelectedEvent = tablePosition.getRow();
				}
			}
		});

		buttonDeleteEvent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (lastSelectedEvent >= 0) {
					controller.deleteEvent(lastSelectedEvent);
				}
			}
		});

		tableColumn.setSortable(false);
		lastSelectedEvent = -1;
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
	public int updateListOfEvents(Vector<Event> vectorOfEvents) {
		this.listOfEvents.clear();
		this.clearFields();
		String stringDay, stringMonth, stringHour, stringMinutes, stringDateTime;

		for (int i = 0; i < vectorOfEvents.size(); i++) {
			stringDay = (vectorOfEvents.elementAt(i).getDayOfEvent() < 10) ? ("0" + vectorOfEvents.elementAt(i).getDayOfEvent()) : (Integer.toString(vectorOfEvents.elementAt(i).getDayOfEvent()));
			stringMonth = (vectorOfEvents.elementAt(i).getMonthOfEvent() < 10) ? ("0" + vectorOfEvents.elementAt(i).getMonthOfEvent()) : (Integer.toString(vectorOfEvents.elementAt(i).getMonthOfEvent()));
			stringHour = (vectorOfEvents.elementAt(i).getHourOfEvent() < 10) ? ("0" + vectorOfEvents.elementAt(i).getHourOfEvent()) : (Integer.toString(vectorOfEvents.elementAt(i).getHourOfEvent()));
			stringMinutes = (vectorOfEvents.elementAt(i).getMinuteOfEvent() < 10) ? ("0" + vectorOfEvents.elementAt(i).getMinuteOfEvent()) : (Integer.toString(vectorOfEvents.elementAt(i).getMinuteOfEvent()));

			stringDateTime = stringDay + "." + stringMonth + "." + vectorOfEvents.elementAt(i).getYearOfEvent() + " " + stringHour + ":" + stringMinutes;
			this.listOfEvents.add(stringDateTime);
		}

		this.tableOfEvents.setItems(this.listOfEvents);

		return 0;
	}
	public int setEventInfo(Event event) {
		String stringDay, stringMonth, stringHour, stringMinutes, stringDate, stringTime;
		stringDay = (event.getDayOfEvent() < 10) ? ("0" + event.getDayOfEvent()) : (Integer.toString(event.getDayOfEvent()));
		stringMonth = (event.getMonthOfEvent() < 10) ? ("0" + event.getMonthOfEvent()) : (Integer.toString(event.getMonthOfEvent()));
		stringHour = (event.getHourOfEvent() < 10) ? ("0" + event.getHourOfEvent()) : (Integer.toString(event.getHourOfEvent()));
		stringMinutes = (event.getMinuteOfEvent() < 10) ? ("0" + event.getMinuteOfEvent()) : (Integer.toString(event.getMinuteOfEvent()));
		stringDate = stringDay + "." + stringMonth + "." + event.getYearOfEvent();
		stringTime = stringHour + ":" + stringMinutes;

		this.dateOfEvent.setText(stringDate);
		this.timeOfEvent.setText(stringTime);
		this.messageOfEvent.setText(event.getMessageOfEvent());
		this.additionalInfo.setText(event.getAdditionalInfo());

		return 0;
	}

	private void clearFields() {
		dateOfEvent.clear();
		timeOfEvent.clear();
		messageOfEvent.clear();
		additionalInfo.clear();
		lastSelectedEvent = -1;
	}

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
