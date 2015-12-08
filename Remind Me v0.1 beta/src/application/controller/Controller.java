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

    public int changeTime(int hour, int minutes) {
        return model.changeTime(hour, minutes);
    }
    public int changeDate(int year, int month, int day) {
        return model.changeDate(year, month, day);
    }
	public void setPathToSound(String pathToSound) {
		model.setPathToSound(pathToSound);
	}
	public int addEvent(int yearOfEvent, int monthOfEvent, int dayOfEvent, int hourOfEvent, int minuteOfEvent, String messageOfEvent, String additionalInfo) {
		model.addEvent(yearOfEvent, monthOfEvent, dayOfEvent, hourOfEvent, minuteOfEvent, messageOfEvent, additionalInfo);
		this.updateListOfEvents();
		return 0;
	}
	public int updateListOfEvents() {
		controllerOfMainWindow.updateListOfEvents(model.getVectorOfEvents());
		return 0;
	}
	public int getEventInfo(int index) {
		controllerOfMainWindow.setEventInfo(model.getEvent(index));
		return 0;
	}
	public int deleteEvent(int index) {
		return model.deleteEvent(index);
	}
	public int writeEventListToFile(String pathToFile) {
		return model.writeEventListToFile(pathToFile);
	}
	public int readEventListToFile(String pathToFile) {
		return model.readEventListFromFile(pathToFile);
	}
}
