package fr.istic.project.gasLocation.services;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

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
	 * Retourne la map associé à la localization
	 * @return la map
	 */
	public GoogleMap getMap();

	/**
	 * Associe la map
	 * @param map la map à associer
	 */
	public void setMap(GoogleMap map);
	
}
