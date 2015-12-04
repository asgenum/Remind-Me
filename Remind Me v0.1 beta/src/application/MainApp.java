package application;

import application.controller.Controller;
import application.model.Model;
import application.view.ControllerOfAddEventWindow;
import application.view.ControllerOfMainWindow;
import application.view.ControllerOfOptionsWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

	private Stage rootStage;
	private Scene rootScene;
	private AnchorPane anchorPaneOfRootWindow;

	private ControllerOfMainWindow controllerOfMainWindow;
	private AnchorPane anchorPaneOfMainWindow;

	private ControllerOfAddEventWindow controllerOfAddEventWindow;
	private AnchorPane anchorPaneOfAddEventWindow;

	private ControllerOfOptionsWindow controllerOfOptionsWindow;
	private AnchorPane anchorPaneOfOptionsWindow;

	private Controller controller;
	private Model model;

	@Override
	public void start(Stage primaryStage) throws Exception{
		this.rootStage = primaryStage;
		this.rootStage.setTitle("Remind Me v0.1 beta");

		this.initRootWindow();
		this.initMainWindow();
		this.initAddEventWindow();
		this.initOptionsWindow();
		this.model = new Model();
		this.controller = new Controller(controllerOfMainWindow, controllerOfAddEventWindow, controllerOfOptionsWindow, model);
        this.controllerOfAddEventWindow.setController(controller);
        this.controllerOfMainWindow.setController(controller);
        this.controllerOfOptionsWindow.setController(controller);
		model.setController(controller);
		//model.start();

		anchorPaneOfRootWindow.getChildren().add(anchorPaneOfMainWindow);

		rootScene = new Scene(anchorPaneOfRootWindow);
		rootStage.setScene(rootScene);
		rootStage.setMaxHeight(329);
		rootStage.setMaxWidth(400);
		rootStage.setResizable(false);
		rootStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

	private void initRootWindow(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootWindow.fxml"));
			anchorPaneOfRootWindow = (AnchorPane)loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initMainWindow(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainWindow.fxml"));
			anchorPaneOfMainWindow = (AnchorPane)loader.load();
            controllerOfMainWindow = loader.getController();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initAddEventWindow(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddEventWindow.fxml"));
			anchorPaneOfAddEventWindow = (AnchorPane)loader.load();
			controllerOfAddEventWindow = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initOptionsWindow(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/OptionsWindow.fxml"));
			anchorPaneOfOptionsWindow = (AnchorPane)loader.load();
			controllerOfOptionsWindow = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
