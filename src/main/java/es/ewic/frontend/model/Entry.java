package es.ewic.frontend.model;

import java.io.Serializable;

public class Entry implements Serializable {

	private static final long serialVersionUID = 1176111580931748851L;

	private int entryNumber;
	private long duration;
	private String description;

	public Entry(int entryNumber, long duration, String description) {
		this.entryNumber = entryNumber;
		this.duration = duration;
		this.description = description;
	}

	public int getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(int entryNumber) {
		this.entryNumber = entryNumber;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
