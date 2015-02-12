package fr.istic.project.gasLocation.activities;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.models.Station;
import fr.istic.project.gasLocation.services.LocalizationInterface;
import fr.istic.project.gasLocation.services.location.Localization;

public class MapSectionFragment extends Fragment{

    public static final String ARG_SECTION_NUMBER = "section_number";
    
    private GoogleMap map;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_section_map, container, false);
        
        // objet servant à la localisation
        LocalizationInterface localization = new Localization(getActivity());
        
        // récupération de l'objet de la carte
        map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        localization.setMap(map);
        
        // affichage de l'utilisateur sur la map
        map.setMyLocationEnabled(true);
        
        // add stations
        Station s = new Station();
        s.setLatitude(48.113737);
        s.setLongitude(-1.639225);
        s.setAddress("Total2");
        
        Collection<Station> stations = new ArrayList<Station>();
        stations.add(s);
        
        addMarkersFromStations(stations);
        
        return rootView;
    }
    
    /**
     * Adding of marker stations 
     * Previously deleting of all the existing stations
     * @param stations
     */
    private void addMarkersFromStations(Collection<Station> stations){
    	map.clear();
    	
    	for(Station s : stations){
            map.addMarker(new MarkerOptions()
        	.position(new LatLng(s.getLatitude(), s.getLongitude()))
        	.title(s.getAddress())
        	.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
    	}
    }

}
