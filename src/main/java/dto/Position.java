package dto;

import javax.security.auth.login.LoginException;

/**
 * La classe position contient une longitude et une latitude
 * @author corentin
 *
 */
public class Position {

	/**
	 * la longitude
	 */
	private double longitud;
	
	/**
	 * la latitude
	 */
	private double latitude;
	
	/**
	 * Constructeur de la position
	 * @param longitud La longitude
	 * @param latitude La latitude
	 */
	public Position(double longitud, double latitude){
		this.longitud=longitud;
		this.latitude=latitude;
	}

	/**
	 * Retourne la logitude
	 * @return la logitude
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Retourne la latitude
	 * @return la latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	
	
}
