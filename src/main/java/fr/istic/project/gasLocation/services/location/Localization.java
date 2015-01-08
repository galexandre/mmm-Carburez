package fr.istic.project.gasLocation.services.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import fr.istic.project.gasLocation.services.LocalizationInterface;

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
	 * La manager de location
	 */
	LocationManager locationManager;
	
	/**
	 * la carte
	 */
	GoogleMap map;
	
	/**
	 * Le constructeur
	 * @param context le context
	 */
	public Localization(Context context){
		this.mContext = context;
		LocationManager locationManager = (LocationManager)this.mContext.getSystemService(mContext.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		this.lastLocation = new Location(LocationManager.GPS_PROVIDER);
		Log.d(TAG, "Localization initialized");
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
		Log.d(TAG, "GPS LocationChanged");
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		Log.d(TAG, "GPS request " + String.valueOf(lat) + "," + String.valueOf(lng));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng) , 18.0f) );
	}

	
	public void onStatusChanged(String provider, int status, Bundle extras) {}
	public void onProviderEnabled(String provider) {}
	public void onProviderDisabled(String provider){}

	public GoogleMap getMap() {
		return map;
	}

	public void setMap(GoogleMap map) {
		Log.d(TAG, "Map init");
		this.map = map;
	}
	
	
}
