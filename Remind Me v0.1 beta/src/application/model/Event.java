package application.model;

import java.io.Serializable;

public class Event implements Serializable{
	private int yearOfEvent;
	private int monthOfEvent;
	private int dayOfEvent;
	private int hourOfEvent;
	private int minuteOfEvent;

	private String messageOfEvent;
	private String additionalInfo;

	public Event(int yearOfEvent, int monthOfEvent, int dayOfEvent, int hourOfEvent, int minuteOfEvent, String messageOfEvent, String additionalInfo) {
		this.yearOfEvent = yearOfEvent;
		this.monthOfEvent = monthOfEvent;
		this.dayOfEvent = dayOfEvent;
		this.hourOfEvent = hourOfEvent;
		this.minuteOfEvent = minuteOfEvent;
		this.messageOfEvent = messageOfEvent;
		this.additionalInfo = additionalInfo;
	}

	public int getYearOfEvent() {
		return yearOfEvent;
	}

	public int getMonthOfEvent() {
		return monthOfEvent;
	}

	public int getDayOfEvent() {
		return dayOfEvent;
	}

	public int getHourOfEvent() {
		return hourOfEvent;
	}

	public int getMinuteOfEvent() {
		return minuteOfEvent;
	}

	public String getMessageOfEvent() {
		return messageOfEvent;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}
}
