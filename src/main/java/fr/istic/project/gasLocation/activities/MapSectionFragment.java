package fr.istic.project.gasLocation.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.istic.project.gasLocation.R;

public class MapSectionFragment extends Fragment{

    public static final String ARG_SECTION_NUMBER = "section_number";
    
//    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section_map, container, false);
        
//        mapView = (MapView)rootView.findViewById(R.id.map);
        
        return rootView;
    }
}
