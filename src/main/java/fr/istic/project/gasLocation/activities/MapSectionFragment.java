package fr.istic.project.gasLocation.activities;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.models.Station;
import fr.istic.project.gasLocation.services.LocalizationInterface;
import fr.istic.project.gasLocation.services.location.Localization;

public class MapSectionFragment extends Fragment implements OnMarkerClickListener{

	public static final String TAG = "MapSectionFragment";
	
    public static final String ARG_SECTION_NUMBER = "section_number";
    
    private GoogleMap map;
    
    /**
     * Stations which are currently near the user
     */
    private List<Station> currentStations;
    
    private Map<Marker, Station> mapMarkerStation;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_section_map, container, false);
        
        mapMarkerStation = new HashMap<Marker, Station>();
        
        currentStations = getArguments().getParcelableArrayList("currentStations");
        
        // récupération de l'objet de la carte
        map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        map.setOnMarkerClickListener(this);
        
        // affichage de l'utilisateur sur la map
        map.setMyLocationEnabled(true);
        
        addMarkersFromStations(currentStations);
        
        return rootView;
    }
    
    /**
     * Adding of marker stations 
     * Previously deleting of all the existing stations
     * @param stations
     */
    private void addMarkersFromStations(Collection<Station> stations){
    	map.clear();
    	
    	for(Station station : stations){
    		// create the new marker
            Marker marker = map.addMarker(new MarkerOptions()
        	.position(new LatLng(station.getLatitude(), station.getLongitude()))
        	.title(station.getAddress())
        	.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
            // associate it to a station
            mapMarkerStation.put(marker, station);
    	}
    }

	public boolean onMarkerClick(Marker marker) {
		Log.d(TAG,"Click on marker");
		
		Station station = mapMarkerStation.get(marker);
		if (station != null){
			Log.d(TAG,"Station clicked is "+station.getAddress());
			
			// send the station to StationActivity
			Intent intent = new Intent(getActivity(), StationActivity.class);
			// send data with it
			Bundle extras = intent.getExtras();
			
			startActivity(intent);
		}
		
		return false;
	}

}
