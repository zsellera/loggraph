package org.zs.audit.model;

import java.util.Date;

public class LogMessage implements Comparable<LogMessage> {
	private final Date date;
	private final String message;
	
	public LogMessage(String message) {
		super();
		this.date = new Date();
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int compareTo(LogMessage that) {
		return this.date.compareTo(that.date);
	}
}
