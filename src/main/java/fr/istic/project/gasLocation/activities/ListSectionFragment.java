package fr.istic.project.gasLocation.activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
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

public class ListSectionFragment extends ListFragment {

	public ListView list_stations;
	private TextView stationName;
	private TextView stationAdress;
	private TextView gazolePrice;
	private TextView essencePrice;
	final String TAG_STATIONNAME = "stationName";
	final String TAG_STATIONADRESS = "stationAdress";
	final String TAG_GAZOLEPRICE = "gazolePrice";
	final String TAG_ESSENCEPRICE = "essencePrice";
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	ArrayList<HashMap<String, Object>> stationsList = new ArrayList<HashMap<String, Object>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_list, container, false);
		
		stationName = (TextView) getActivity().findViewById(R.id.stationName);
		stationAdress = (TextView) getActivity().findViewById(R.id.stationAdress);
		gazolePrice = (TextView) getActivity().findViewById(R.id.gazolePrice);
		essencePrice = (TextView) getActivity().findViewById(R.id.essencePrice);
		
		ImageView image = (ImageView) getActivity().findViewById(R.id.thumbnail);
		//image.setImageResource(R.drawable.total.);

		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		

		String stationNames[] = {"Total Access", "Super U", "Carrefour" , "E.Leclerc", "Total Access", "ELF", "Avia", "Total"};
		String stationAdresss[] = {"11 rue du Général Leclerc", "6 rue de la gare", "11 rue de l'Alma" , "12 avenue Janvier",
									"7 rue Léon.B", "8 rue de la roseraie", "20 avenue Janvier", "1 place rosa parks"};
		String gazolePrices[] = {"1,08 €", "1,10 €", "1,11 €" , "1,12 €", "1,08 €", "1,10 €", "1,11 €" , "1,12 €"};
		String essencePrices[] = {"1,10 €", "1, 09 €","1,11 €" , "1,12 €", "1,08 €", "1,10 €", "1,11 €" , "1,12 €"};
		
		for (int i=0 ; i<8 ; i++){
			String stationNameS = stationNames[i];
			String stationAdressS = stationAdresss[i];
			String gazolePriceS = gazolePrices[i];
			String essencePriceS = essencePrices[i];
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(TAG_STATIONNAME, stationNameS);
			map.put(TAG_STATIONADRESS, stationAdressS);
			map.put(TAG_GAZOLEPRICE, gazolePriceS);
			map.put(TAG_ESSENCEPRICE, essencePriceS);
			stationsList.add(map);
			list_stations = getListView();
			ListAdapter adapter = new SimpleAdapter(getActivity(), stationsList,
					R.layout.list_pattern,
					new String[] { TAG_STATIONNAME,TAG_STATIONADRESS, TAG_GAZOLEPRICE, TAG_ESSENCEPRICE }, new int[] {
					R.id.stationName,R.id.stationAdress, R.id.gazolePrice, R.id.essencePrice});
			

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
