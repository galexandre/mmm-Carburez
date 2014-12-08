package fr.istic.project.gasLocation.models;

import java.sql.Date;
import java.util.ArrayList;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

import android.R.string;


/**
 * Classe to manage station objects
 * @author sylvain
 *
 */

@Table(name="Station")
public class Station {
	
	@PrimaryKey
	@Column(name="idStation")
	private int idStation;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="postalCode")
	private int postalCode;
	
	@Column(name="address")
	private string address;
	
	@Column(name="town")
	private string town;
	
	@Column(name="startHour")
	private Date startHour;
	
	@Column(name="endHour")
	private Date endHours;
	
	@Column(name="exceptDays")
	private string exceptDays;
	
	@Column(name="services")
	private string services;
	
	//private ArrayList<Gas> gasList = new ArrayList<Gas>();
	
	

	public Station(int idStation, double latitude, double longitude,
			int postalCode, string address, string town, Date startHour,
			Date endHours, string exceptDays, string services,
			ArrayList<Gas> gasList) {
		super();
		this.idStation = idStation;
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
	public string getAddress() {
		return address;
	}
	public void setAddress(string address) {
		this.address = address;
	}
	public string getTown() {
		return town;
	}
	public void setTown(string town) {
		this.town = town;
	}
	public Date getStartHour() {
		return startHour;
	}
	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}
	public Date getEndHours() {
		return endHours;
	}
	public void setEndHours(Date endHours) {
		this.endHours = endHours;
	}
	public string getExceptDays() {
		return exceptDays;
	}
	public void setExceptDays(string exceptDays) {
		this.exceptDays = exceptDays;
	}
	public string getServices() {
		return services;
	}
	public void setServices(string services) {
		this.services = services;
	}
	public int getIdStation() {
		return idStation;
	}
	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}

	
	
}
