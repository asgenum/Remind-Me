package application.model;

import application.controller.Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Vector;

public class Model {

	private int currentYear;
	private int currentMonth;
	private int currentDay;
	private int currentHour;
	private int currentMinutes;

	private int prevMinutes;
	private int nowMinutes;
	private int daysInMonths[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	private Controller controller;
	protected final Timeline timeLine = new Timeline();
	private String pathToSound = "";
	private MediaPlayer mediaPlayer;
	private boolean isPlaying = false;

	private Vector<Event> vectorOfEvents;


	public Model() {
		vectorOfEvents = new Vector<Event>();
		currentYear = LocalDateTime.now().getYear();
		currentMonth = LocalDateTime.now().getMonthValue();
		currentDay = LocalDateTime.now().getDayOfMonth();
		currentHour = LocalDateTime.now().getHour();
		currentMinutes = prevMinutes = nowMinutes = LocalDateTime.now().getMinute();
		currentHour += 1;

		timeLine.setCycleCount(Animation.INDEFINITE);
		KeyFrame oneStepOfGame = new KeyFrame(Duration.seconds(1),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						timeCalculation();
						controller.updateTime(currentYear, currentMonth, currentDay, currentHour, currentMinutes);
						for (int i = 0; i < vectorOfEvents.size(); i++) {
							if (checkEvent(i) == true) {
								eventHasDetected(i);
							}
						}
					}
				});
		timeLine.getKeyFrames().add(oneStepOfGame);
	}

	private int start() {
		timeLine.play();
		return 0;
	}

	private int stop() {
		timeLine.stop();
		return 0;
	}

	private int timeCalculation() {

		nowMinutes = LocalDateTime.now().getMinute();

		if ((nowMinutes - prevMinutes) != 0) {
			prevMinutes = LocalDateTime.now().getMinute();
			if (++currentMinutes == 60) {
				currentMinutes = 0;
				if (++currentHour == 24) {
					currentHour = 0;
					if (++currentDay == daysInMonths[currentMonth - 1]) {
						if (++currentMonth == 12) {
							currentMonth = 1;
							currentYear++;
						}
					}
				}
			}
		}
		return 0;
	}

	private boolean checkEvent(int indexOfEvent) {
		if (vectorOfEvents.elementAt(indexOfEvent).getYearOfEvent() == currentYear) {
			if (vectorOfEvents.elementAt(indexOfEvent).getMonthOfEvent() == currentMonth) {
				if (vectorOfEvents.elementAt(indexOfEvent).getDayOfEvent() == currentDay) {
					if (vectorOfEvents.elementAt(indexOfEvent).getHourOfEvent() == currentHour) {
						if (vectorOfEvents.elementAt(indexOfEvent).getMinuteOfEvent() == currentMinutes) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public int changeTime(int hour, int minutes) {
		this.currentHour = hour;
		this.currentMinutes = minutes;
		return 0;
	}

	public int changeDate(int year, int month, int day) {
		this.currentYear = year;
		this.currentMonth = month;
		this.currentDay = day;
		return 0;
	}

	public void setPathToSound(String pathToSound) {
		this.pathToSound = pathToSound;
        this.mediaPlayer = null;
        Media sound = new Media(new File(this.pathToSound).toURI().toString());
        this.mediaPlayer = new MediaPlayer(sound);
	}

	private void playWarningSound() {
		if (pathToSound != null && pathToSound.length() > 0) {
			if (this.isPlaying == false) {
				this.isPlaying = true;
				mediaPlayer.play();
			}
		}
	}

	private void stopWarningSound() {
		if (isPlaying == true) {
			this.mediaPlayer.stop();
			this.isPlaying = false;

		}
	}

	public int addEvent(int yearOfEvent, int monthOfEvent, int dayOfEvent, int hourOfEvent, int minuteOfEvent, String messageOfEvent, String additionalInfo) {
		//this.stop();
		this.vectorOfEvents.add(new Event(yearOfEvent, monthOfEvent, dayOfEvent, hourOfEvent, minuteOfEvent, messageOfEvent, additionalInfo));
		System.out.println("Add Event " + messageOfEvent);
		//this.start();
		return 0;
	}

	public void setController(Controller controller) {
		this.controller = controller;
		this.start();
	}

	private void eventHasDetected(int indexOfEvent) {
		this.playWarningSound();
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Event has fired");
		alert.setHeaderText("Time: " + vectorOfEvents.elementAt(indexOfEvent).getHourOfEvent() + ":" + vectorOfEvents.elementAt(indexOfEvent).getMinuteOfEvent()
				+ " Date: " + vectorOfEvents.elementAt(indexOfEvent).getDayOfEvent() + "." +
				vectorOfEvents.elementAt(indexOfEvent).getMonthOfEvent() + "." + vectorOfEvents.elementAt(indexOfEvent).getYearOfEvent());
		alert.setContentText("Message: " + vectorOfEvents.elementAt(indexOfEvent).getMessageOfEvent() + ". Additional Info: " + vectorOfEvents.elementAt(indexOfEvent).getAdditionalInfo());
		vectorOfEvents.remove(indexOfEvent);
		System.out.println(vectorOfEvents.size());
		alert.showAndWait();
		this.stopWarningSound();
	}
}
