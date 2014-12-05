package service.localization;

import service.LocalizationInterface;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class Localization implements LocalizationInterface, LocationListener{

	/**
	 * Le tag pour les log
	 */
	private static final String TAG = "LocalizationCorentin";
	
	// Le contexte
	Context mContext;
	
	/**
	 * La position
	 */
	Location lastLocation;
	
	/**
	 * Status du GPS
	 */
	int status;
	
	/**
	 * La manager de location
	 */
	LocationManager locationManager;
	
	/**
	 * Le constructeur
	 * @param context le context
	 */
	public Localization(Context context){
		this.mContext = context;
		LocationManager locationManager = (LocationManager)this.mContext.getSystemService(mContext.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		this.lastLocation = new Location(LocationManager.GPS_PROVIDER);
	}
	
	/**
	 * Retourne la position actuelle
	 * A enlever
	 */
	public Location getCurrentPosition() {
		return this.lastLocation;
	}

	public void onLocationChanged(Location location) {
		this.lastLocation = location;
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		this.status = status;
	}

	public void onProviderEnabled(String provider) {}

	public void onProviderDisabled(String provider){}

	public int getStatus() {
		return this.status;
	}
}
