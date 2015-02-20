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
}
