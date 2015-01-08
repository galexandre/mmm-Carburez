package fr.istic.project.gasLocation.activities;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

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
        
        ((Localization)localization).setMap(map);
        
        // affichage de l'utilisateur sur la map
        map.setMyLocationEnabled(true);
        
//        map.addMarker(new MarkerOptions().position(TutorialsPoint).title("TutorialsPoint"));
        
        
        return rootView;
    }

	public void onLocationChanged(Location location) {
		Toast.makeText(getActivity(), "ça change!", Toast.LENGTH_SHORT).show();
		if (map != null) {
	        CameraPosition cameraPosition = new CameraPosition.Builder()
	                .target(new LatLng(location.getLatitude(),
	                		location.getLongitude())).zoom(14.0f).build();
	        CameraUpdate cameraUpdate = CameraUpdateFactory
	                .newCameraPosition(cameraPosition);
	        map.moveCamera(cameraUpdate);
	    }
		Log.i("TestCorentin", "Changement de location");
	}


}
