package service;

import dto.Position;

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
	Position getCurrentPosition();
	
	
}
