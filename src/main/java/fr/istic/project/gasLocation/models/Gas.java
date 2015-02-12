package fr.istic.project.gasLocation.models;


import com.j256.ormlite.field.*;
import com.j256.ormlite.table.*;

@DatabaseTable(tableName="Gas")
public class Gas{

	
	@DatabaseField(generatedId=true)
	private int idGas;
	
	
	
	@DatabaseField(canBeNull=false, foreign = true)
	private Station station;
	
	@DatabaseField(canBeNull=false)
	private String gasName;
	
	@DatabaseField(canBeNull=false)
	private String dateUpdate;

	@DatabaseField(canBeNull=false)
	private double price;
	
	@DatabaseField
	private char typeRupture;
	
	@DatabaseField
	private String dateRupture;
	
	public Gas(){}
	
	public Gas(Station station, String gasName, String dateUpdate, double price, char typeRupture, String dateRupture) {
		super();
		this.station = station;
		this.gasName = gasName;
		this.dateUpdate = dateUpdate;
		this.price = price;
		this.typeRupture = typeRupture;
		this.dateRupture = dateRupture;
	}
	public String getGasName() {
		return gasName;
	}
	public void setGasName(String gasName) {
		this.gasName = gasName;
	}
	public String getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public char getTypeRupture() {
		return typeRupture;
	}
	public void setTypeRupture(char typeRupture) {
		this.typeRupture = typeRupture;
	}
	public String getDateRupture() {
		return dateRupture;
	}
	public void setDateRupture(String dateRupture) {
		this.dateRupture = dateRupture;
	}
	
	public Station getStation() {
		return station;
	}
	
	public void setStation(Station station) {
		this.station = station;
	}
	
	
}
