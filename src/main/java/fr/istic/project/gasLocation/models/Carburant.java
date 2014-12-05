package fr.istic.project.gasLocation.models;

import java.sql.Date;

import android.R.string;

public class Carburant {

	private string nomCarburant;
	private Date dateMAJ;
	private double prix;
	private char typeRupture;
	private Date dateRupture;
	
	public Carburant(string nomCarburant, Date dateMAJ, double prix, char typeRupture, Date dateRupture) {
		super();
		this.nomCarburant = nomCarburant;
		this.dateMAJ = dateMAJ;
		this.prix = prix;
		this.typeRupture = typeRupture;
		this.dateRupture = dateRupture;
	}
	public string getNomCarburant() {
		return nomCarburant;
	}
	public void setNomCarburant(string nomCarburant) {
		this.nomCarburant = nomCarburant;
	}
	public Date getDateMAJ() {
		return dateMAJ;
	}
	public void setDateMAJ(Date dateMAJ) {
		this.dateMAJ = dateMAJ;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
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
