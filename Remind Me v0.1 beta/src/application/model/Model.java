package application.model;

import application.controller.Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.time.LocalDateTime;

public class Model{

	private int currentYear;
	private int currentMonth;
	private int currentDay;
	private int currentHour;
	private int currentMinutes;

	private int prevMinutes;
	private int nowMinutes;
	int daysInMonths[] = {31,28,31,30,31,30,31,31,30,31,30,31};

	private Controller controller;
	protected final Timeline timeLine = new Timeline();

	public Model() {
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
					}
				});
		timeLine.getKeyFrames().add(oneStepOfGame);
	}

	public int start() {
		timeLine.play();
		return 0;
	}

	public int stop() {
		timeLine.stop();
		return 0;
	}

	private int timeCalculation() {

		nowMinutes = LocalDateTime.now().getMinute();

		if((nowMinutes - prevMinutes) != 0){
			prevMinutes = LocalDateTime.now().getMinute();
			if (++currentMinutes == 60) {
				currentMinutes = 0;
				if (++currentHour== 24) {
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

    public void setController(Controller controller) {
		this.controller = controller;
	}
}
