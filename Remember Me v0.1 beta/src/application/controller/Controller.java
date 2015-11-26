package application.controller;

import application.model.Model;
import application.view.ControllerOfAddEventWindow;
import application.view.ControllerOfMainWindow;
import application.view.ControllerOfOptionsWindow;

public class Controller {
	private ControllerOfMainWindow controllerOfMainWindow;
	private ControllerOfAddEventWindow controllerOfAddEventWindow;
	private ControllerOfOptionsWindow controllerOfOptionsWindow;
	private Model model;

	public Controller(ControllerOfMainWindow controllerOfMainWindow, ControllerOfAddEventWindow controllerOfAddEventWindow, ControllerOfOptionsWindow controllerOfOptionsWindow, Model model) {
		this.controllerOfMainWindow = controllerOfMainWindow;
		this.controllerOfAddEventWindow = controllerOfAddEventWindow;
		this.controllerOfOptionsWindow = controllerOfOptionsWindow;

		this.model = model;

		this.controllerOfMainWindow.addWindowsInMainWindow(this.controllerOfAddEventWindow.getAnchorPaneOfAddEventWindow(), this.controllerOfOptionsWindow.getAnchorPaneOfOptionsWindow());

	}

	public int updateTime(int year, int month, int day, int hour, int minutes) {
		this.controllerOfMainWindow.updateTime(year, month, day, hour, minutes);
		return 0;
	}

}
