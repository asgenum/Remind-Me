package application;

import application.controller.Controller;
import application.model.Model;
import application.view.ControllerOfAddEventWindow;
import application.view.ControllerOfMainWindow;
import application.view.ControllerOfOptionsWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
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

	private boolean firstTime;
	private boolean iconIsCreated;
	private TrayIcon trayIcon;
	final SystemTray tray = SystemTray.getSystemTray();

	@Override
	public void start(final Stage primaryStage) throws Exception {
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


		firstTime = true;
		iconIsCreated = false;
		Platform.setImplicitExit(false);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				if (iconIsCreated == false) {
					createTrayIcon(primaryStage);
					iconIsCreated = true;
				}

				hide(primaryStage);

				try {
					tray.add(trayIcon);
				} catch (AWTException e) {
					System.err.println(e);
				}
			}
		});

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

	private void initRootWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootWindow.fxml"));
			anchorPaneOfRootWindow = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initMainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainWindow.fxml"));
			anchorPaneOfMainWindow = (AnchorPane) loader.load();
			controllerOfMainWindow = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initAddEventWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AddEventWindow.fxml"));
			anchorPaneOfAddEventWindow = (AnchorPane) loader.load();
			controllerOfAddEventWindow = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initOptionsWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/OptionsWindow.fxml"));
			anchorPaneOfOptionsWindow = (AnchorPane) loader.load();
			controllerOfOptionsWindow = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createTrayIcon(final Stage stage) {
		if (SystemTray.isSupported()) {

			java.awt.Image image = null;
			try {
				//URL url = .toString();

				image = ImageIO.read(this.getClass().getResource("resources/calendar.png"));
			} catch (IOException ex) {
				System.out.println(ex);
			}

			final ActionListener closeListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			};

			ActionListener showListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							stage.show();
							tray.remove(trayIcon);
						}
					});
				}
			};

			PopupMenu popup = new PopupMenu();

			MenuItem showItem = new MenuItem("Show App");
			showItem.addActionListener(showListener);
			popup.add(showItem);

			MenuItem closeItem = new MenuItem("Exit");
			closeItem.addActionListener(closeListener);
			popup.add(closeItem);

			trayIcon = new TrayIcon(image, "Remind Me v0.1 beta", popup);

			trayIcon.addActionListener(showListener);

		}
	}

	public void showProgramIsMinimizedMsg() {
		if (firstTime) {
			trayIcon.displayMessage("Remind Me v0.1 beta.",
					"Application has hidden.",
					TrayIcon.MessageType.INFO);
			firstTime = false;
		}
	}

	private void hide(final Stage stage) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (SystemTray.isSupported()) {
					stage.hide();
					showProgramIsMinimizedMsg();
				} else {
					System.exit(0);
				}
			}
		});
	}

}
