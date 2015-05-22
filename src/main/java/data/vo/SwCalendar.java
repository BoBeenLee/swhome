package data.vo;

import java.sql.Date;

public class SwCalendar {
	private int calendarId;
	private Date startDate;
	private Date lastDate;
	private String title;
	
	public SwCalendar() {
	}
	public SwCalendar(Date startDate, Date lastDate,
			String title) {
		this.startDate = startDate;
		this.lastDate = lastDate;
		this.title = title;
	}
	
	public int getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public String getTitle() {
		return title;
	}
}