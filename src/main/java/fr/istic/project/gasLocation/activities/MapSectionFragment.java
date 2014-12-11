package fr.istic.project.gasLocation.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import fr.istic.project.gasLocation.R;

public class MapSectionFragment extends Fragment{

    public static final String ARG_SECTION_NUMBER = "section_number";
    
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section_map, container, false);
        
        map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        // affichage de l'utilisateur sur la map
        map.setMyLocationEnabled(true);
        
        return rootView;
    }
}
