package fr.istic.project.gasLocation.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.models.Station;

public class ListSectionFragment extends ListFragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	
	public ListView list_stations;
	private TextView stationName;
	private TextView stationAdress;
	private TextView gazolePrice;
	private TextView essencePrice;
	private ImageView stationImage;
	
	final String TAG_PICTURE = "thumbnail";
	final String TAG_STATIONNAME = "stationName";
	final String TAG_STATIONADRESS = "stationAdress";
	final String TAG_GAZOLEPRICE = "gazolePrice";
	final String TAG_ESSENCEPRICE = "essencePrice";
	
	private List<Station> currentStations;
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	ArrayList<HashMap<String, Object>> stationsList = new ArrayList<HashMap<String, Object>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_list, container, false);
		currentStations = getArguments().getParcelableArrayList("currentStations");
        
		stationImage = (ImageView) getActivity().findViewById(R.id.thumbnail);
		stationName = (TextView) getActivity().findViewById(R.id.stationName);
		stationAdress = (TextView) getActivity().findViewById(R.id.stationAdress);
		gazolePrice = (TextView) getActivity().findViewById(R.id.gazolePrice);
		essencePrice = (TextView) getActivity().findViewById(R.id.essencePrice);
		
		//image.setImageResource(R.drawable.total.);

		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayList<String> stationNames = new ArrayList<String>();
		ArrayList<String> stationAdresss = new ArrayList<String>();
		//String gazolePrices[];
		//String essencePrices[];
		if (!(currentStations.isEmpty())){
			for(Station s : currentStations){
				stationNames.add(s.getTown());
				stationAdresss.add(s.getAddress());
			}
		} else {
			stationNames.add("Total Access");stationNames.add("E.Leclerc");stationNames.add("Carrefour");stationNames.add("ELF");stationNames.add("Esso");stationNames.add("ELF");
			stationAdresss.add("11 rue du Général Leclerc");stationAdresss.add("6 rue de la gare");stationAdresss.add("11 rue de l'Alma");stationAdresss.add("20 Avenue Janvier");stationAdresss.add("1 Place Rosa Parks");
		}
		String gazolePrices[] = {"1,08 €", "1,10 €", "1,11 €" , "1,12 €", "1,08 €", "1,10 €", "1,11 €" , "1,12 €"};
		String essencePrices[] = {"1,10 €", "1, 09 €","1,11 €" , "1,12 €", "1,08 €", "1,10 €", "1,11 €" , "1,12 €"};
		int pictures[] = {R.drawable.access,R.drawable.leclerc,R.drawable.carrefour,R.drawable.elf,R.drawable.esso,R.drawable.total,R.drawable.access,R.drawable.elf};
		
		for (int j=0 ; j<currentStations.size() ; j++){
			String stationNameS = stationNames.get(j);
			String stationAdressS = stationAdresss.get(j);
			String gazolePriceS = "1,10 €";
			String essencePriceS = "1,32 €";
			
			Random r = new Random();
			int valeur = 0 + r.nextInt(7 - 0);
			int pictureS = pictures[valeur];
				
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(TAG_PICTURE, pictureS);
			map.put(TAG_STATIONNAME, stationNameS);
			map.put(TAG_STATIONADRESS, stationAdressS);
			map.put(TAG_GAZOLEPRICE, gazolePriceS);
			map.put(TAG_ESSENCEPRICE, essencePriceS);
			stationsList.add(map);
			list_stations = getListView();
			ListAdapter adapter = new SimpleAdapter(getActivity(), stationsList,
					R.layout.list_pattern,
					new String[] {TAG_PICTURE, TAG_STATIONNAME,TAG_STATIONADRESS, TAG_GAZOLEPRICE, TAG_ESSENCEPRICE }, new int[] {
					R.id.thumbnail, R.id.stationName,R.id.stationAdress, R.id.gazolePrice, R.id.essencePrice});
			

			list_stations.setAdapter(adapter);
		}
			
					
			list_stations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
				}
			});
		
	};

	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  
		Toast toast = Toast.makeText(super.getActivity().getApplicationContext(), "A cliqué !", Toast.LENGTH_SHORT);
		toast.show();   
	}   
}
