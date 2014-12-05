package model;

import java.sql.Date;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.ForeignKey;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

import android.R.string;

@Table(name="Gas")
public class Gas {

	@PrimaryKey
	@Column(name="idGas")
	private int idGas;
	
	@ForeignKey(tableReference = "Station", columnReference="idStation")
	@Column(name="idStation")
	private int idStation;
	
	@Column(name="gasName")
	private string gasName;
	
	@Column(name="dateUpdate")
	private Date dateUpdate;

	@Column(name="price")
	private double price;
	
	@Column(name="rupture")
	private char typeRupture;
	
	@Column(name="dateRupture")
	private Date dateRupture;
	
	public Gas(string gasName, Date dateUpdate, double price, char typeRupture, Date dateRupture) {
		super();
		this.gasName = gasName;
		this.dateUpdate = dateUpdate;
		this.price = price;
		this.typeRupture = typeRupture;
		this.dateRupture = dateRupture;
	}
	public string getGasName() {
		return gasName;
	}
	public void setGasName(string gasName) {
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
	
	
}
