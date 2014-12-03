package service;

import android.location.Location;

/**
 * Interface gérant la localisation de l'utilisateur
 * @author corentin
 *
 */
public interface LocalizationInterface {
	/**
	 * Retourne la position actuelle
	 * @return La position sous forme d'objet Position
	 */
	Location getCurrentPosition();
	
	/**
	 * Retourne l'état du GPS à comparer avec les constantes de LocationProvider
	 * @return l'état du GPS
	 */
	int getStatus();
}
