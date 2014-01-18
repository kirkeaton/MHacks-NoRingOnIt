package com.example.mhacks_noringonit;

public class CalEvent {

	private String title;
	private long start;
	private long end;

	public CalEvent() {
		// TODO Auto-generated constructor stub
	}

	public CalEvent(String title, long start, long end) {
		this.title = title;
		this.start = start;
		this.end = end;
	}
	
	public CalEvent(CalEvent ce)
	{
		this.title = ce.title;
		this.start = ce.start;
		this.end = ce.end;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getStartTime() {
		return this.start;
	}

	public void setStartTime(long start) {
		this.start = start;
	}

	public long getEndTime() {
		return this.end;
	}

	public void setEndTime(long end) {
		this.end = end;
	}
}
