package fr.istic.project.gasLocation.models;

import java.sql.Date;

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
	private Date dateUpdate;

	@DatabaseField(canBeNull=false)
	private double price;
	
	@DatabaseField
	private char typeRupture;
	
	@DatabaseField
	private Date dateRupture;
	
	public Gas(){}
	
	public Gas(int idGas, Station station, String gasName, Date dateUpdate, double price, char typeRupture, Date dateRupture) {
		super();
		this.idGas = idGas;
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
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
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
	public Date getDateRupture() {
		return dateRupture;
	}
	public void setDateRupture(Date dateRupture) {
		this.dateRupture = dateRupture;
	}
	
	public int getIdGas() {
		return idGas;
	}
	public Station getStation() {
		return station;
	}
	
	
}
