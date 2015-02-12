package fr.istic.project.gasLocation.models;

import java.sql.Date;




import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;



/**
 * Classe to manage station objects
 * @author sylvain
 *
 */

@DatabaseTable(tableName="Station")
public class Station{
	
	@DatabaseField(generatedId=true)
	private int idStation;
	
	@DatabaseField(canBeNull=false)
	private double latitude;
	
	@DatabaseField(canBeNull=false)
	private double longitude;
	
	@DatabaseField(canBeNull=false)
	private int postalCode;
	
	@DatabaseField(canBeNull=false)
	private String address;
	
	@DatabaseField(canBeNull=false)
	private String town;
	
	@DatabaseField(canBeNull=false)
	private String startHour;
	
	@DatabaseField(canBeNull=false)
	private String endHours;
	
	@DatabaseField(canBeNull=true)
	private String exceptDays;
	
	@DatabaseField(canBeNull=true)
	private String services;
	
	//private ArrayList<Gas> gasList = new ArrayList<Gas>();
	
	public Station(){}

	public Station(double latitude, double longitude,
			int postalCode, String address, String town, String startHour,
			String endHours, String exceptDays, String services) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.postalCode = postalCode;
		this.address = address;
		this.town = town;
		this.startHour = startHour;
		this.endHours = endHours;
		this.exceptDays = exceptDays;
		this.services = services;
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
	public int getPostalCode() {
		return postalCode;
	}
	public void setCodePostal(int codePostal) {
		this.postalCode = codePostal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHours() {
		return endHours;
	}
	public void setEndHours(String endHours) {
		this.endHours = endHours;
	}
	public String getExceptDays() {
		return exceptDays;
	}
	public void setExceptDays(String exceptDays) {
		this.exceptDays = exceptDays;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}

	
	
}
