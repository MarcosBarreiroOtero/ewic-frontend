package es.ewic.frontend.model;

import java.io.Serializable;

public class Shop implements Serializable {

	private static final long serialVersionUID = 136754906037202107L;

	private int idShop;
	private String name;
	private double latitude;
	private double longitude;
	private String location;
	private int maxCapacity;
	private int actualCapacity;
	private String type;
	private boolean allowEntries;
	private int idSeller;
	private String timetable;

	public Shop(int idShop, String name, double latitude, double longitude, String location, int maxCapacity,
			int actualCapacity, String type, boolean allowEntries, int idSeller, String timetable) {
		this.idShop = idShop;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
		this.maxCapacity = maxCapacity;
		this.actualCapacity = actualCapacity;
		this.type = type;
		this.allowEntries = allowEntries;
		this.idSeller = idSeller;
		this.timetable = timetable;
	}

	public int getIdShop() {
		return idShop;
	}

	public void setIdShop(int idShop) {
		this.idShop = idShop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getActualCapacity() {
		return actualCapacity;
	}

	public void setActualCapacity(int actualCapacity) {
		this.actualCapacity = actualCapacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAllowEntries() {
		return allowEntries;
	}

	public void setAllowEntries(boolean allowEntries) {
		this.allowEntries = allowEntries;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public String getTimetable() {
		return timetable;
	}

	public void setTimetable(String timetable) {
		this.timetable = timetable;
	}

}
