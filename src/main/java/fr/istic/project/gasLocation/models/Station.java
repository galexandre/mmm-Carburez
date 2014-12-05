package fr.istic.project.gasLocation.models;

import java.sql.Date;
import java.util.ArrayList;

import android.R.string;


/**
 * Classe pour gérer les objets de type station
 * @author sylvain
 *
 */
public class Station {

	private double latitude;
	private double longitude;
	private int codePostal;
	private string adresse;
	private string ville;
	private Date heureDebut;
	private Date heureFin;
	private string saufJour;
	private string services;
	private ArrayList<Carburant> listeCarburants;
	
	

	public Station(double latitude, double longitude, int codePostal,
			string adresse, string ville, Date heureDebut, Date heureFin,
			string saufJour, string services,
			ArrayList<Carburant> listeCarburants) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.codePostal = codePostal;
		this.adresse = adresse;
		this.ville = ville;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.saufJour = saufJour;
		this.services = services;
		this.listeCarburants = listeCarburants;
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
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public string getAdresse() {
		return adresse;
	}
	public void setAdresse(string adresse) {
		this.adresse = adresse;
	}
	public string getVille() {
		return ville;
	}
	public void setVille(string ville) {
		this.ville = ville;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	public string getSaufJour() {
		return saufJour;
	}
	public void setSaufJour(string saufJour) {
		this.saufJour = saufJour;
	}
	public string getServices() {
		return services;
	}
	public void setServices(string services) {
		this.services = services;
	}

	
}
