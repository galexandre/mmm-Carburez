package fr.istic.project.gasLocation.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.services.LocalizationInterface;
import fr.istic.project.gasLocation.services.location.Localization;

public class MapSectionFragment extends Fragment{

    public static final String ARG_SECTION_NUMBER = "section_number";
    
    private GoogleMap map;

//    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    
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
        
//        map.addMarker(new MarkerOptions().position(TutorialsPoint).title("TutorialsPoint"));
        
        
        return rootView;
    }

}
