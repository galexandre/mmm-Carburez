package fr.istic.project.gasLocation.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
	
	// ELEMENTS GRAPHIQUES DE L'ACTIVITE
	public ListView list_stations;
	private TextView stationName;
	private TextView stationAdress;
	private TextView gazolePrice;
	private TextView sp95Price;
	private ImageView stationImage;
	int logoStations[] = {R.drawable.access,R.drawable.leclerc,R.drawable.carrefour,R.drawable.elf,R.drawable.esso,R.drawable.total,R.drawable.access,R.drawable.elf};
	
	// TAGS UTILISES POUR LE HASHMAP
	final String TAG_STATIONID = "id";
	final String TAG_PICTURE = "thumbnail";
	final String TAG_STATIONNAME = "stationName";
	final String TAG_STATIONADRESS = "stationAdress";
	final String TAG_GAZOLEPRICE = "gazolePrice";
	final String TAG_SP98PRICE = "sp98Price";
	final String TAG_SP95PRICE = "sp95Price";
	final String TAG_E85PRICE = "e85Price";
	
	private ArrayList<Station> currentStations;
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
		sp95Price = (TextView) getActivity().findViewById(R.id.sp95Price);
		return rootView;
	}

	
	// CREATION DE LA LISTE SEULEMENT UNE FOIS L'ACTIVITE CREE (OBLIGATOIRE)
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ArrayList<String> stationIds = new ArrayList<String>();
		ArrayList<String> stationNames = new ArrayList<String>();
		ArrayList<String> stationAdresss = new ArrayList<String>();
		ArrayList<Map<String, Double>> hashMapGases = new ArrayList<Map<String, Double>>();
		if (!(currentStations.isEmpty())){
			for(Station s : currentStations){
				stationIds.add(String.valueOf(s.getIdStation()));
				stationNames.add(s.getTown());
				stationAdresss.add(s.getPostalCode()+" "+s.getAddress());
				hashMapGases.add(s.getGases());
			}
			
			// SI ON NE PARVIENT PAS A RECUPERER LES STATIONS, ON Y MET TEMPORAIREMENT DES STATIONS BIDONS
		} else {
			stationNames.add("Total Access");stationNames.add("E.Leclerc");stationNames.add("Carrefour");stationNames.add("ELF");stationNames.add("Esso");stationNames.add("ELF");
			stationAdresss.add("11 rue du Général Leclerc");stationAdresss.add("6 rue de la gare");stationAdresss.add("11 rue de l'Alma");stationAdresss.add("20 Avenue Janvier");stationAdresss.add("1 Place Rosa Parks");
		}

		
		for (int j=0 ; j<currentStations.size() ; j++){
			String stationIdS = stationIds.get(j);
			String stationNameS = stationNames.get(j);
			String stationAdressS = stationAdresss.get(j);			
			String gazolePriceS = "NC";
			String sp98PriceS = "NC";
			String sp95PriceS = "NC";
			String e85PriceS = "NC";
			
			Map<String, Double> gasesPrices = hashMapGases.get(j);
			for (Map.Entry<String,Double> e : gasesPrices.entrySet()){
				if (e.getKey().equals("Gazole")){
					gazolePriceS = e.getValue().toString();
				}
				else if (e.getKey().equals("SP98")){
					sp98PriceS = e.getValue().toString();
				}
				else if (e.getKey().equals("SP95")){
					sp95PriceS = e.getValue().toString();
				}
				else if (e.getKey().equals("E85")){
					e85PriceS = e.getValue().toString();
				}
			}
			
			// ON PREND TEMPORAIREMENT UN LOGO D'ENSEIGNE AU HASARD
			Random r = new Random();
			int valeur = 0 + r.nextInt(7 - 0);
			int pictureS = logoStations[valeur];
			
			// AJOUT DE TOUTES LES INFOS DES STATIONS DANS UN HASHMAP
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(TAG_PICTURE, pictureS);
			map.put(TAG_STATIONID, stationIdS);
			map.put(TAG_STATIONNAME, stationNameS);
			map.put(TAG_STATIONADRESS, stationAdressS);
			map.put(TAG_GAZOLEPRICE, gazolePriceS);
			map.put(TAG_SP98PRICE, sp98PriceS);
			map.put(TAG_SP95PRICE, sp95PriceS);
			map.put(TAG_E85PRICE, e85PriceS);
			stationsList.add(map);
			list_stations = getListView();
			
			// MISE EN PLACE D'UN ADAPTER POUR L'AFFICHAGE DE LA LISTE
			ListAdapter adapter = new SimpleAdapter(getActivity(), stationsList,
					R.layout.list_pattern,
					new String[] {TAG_PICTURE, TAG_STATIONNAME,TAG_STATIONADRESS, TAG_GAZOLEPRICE, TAG_SP98PRICE}, new int[] {
					R.id.thumbnail, R.id.stationName,R.id.stationAdress, R.id.gazolePrice, R.id.sp95Price});
			list_stations.setAdapter(adapter);
		}
			
			// LORSQU'ON CLIQUE SUR UNE STATION DE LA LISTE
			list_stations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					HashMap<String, Object> item = stationsList.get(position);
					Intent intent = new Intent(getActivity(), StationActivity.class);
					intent.putExtra("idStation", item.get("id").toString());
					intent.putExtra("stationAdress", item.get("stationAdress").toString());
					intent.putExtra("stationTown", item.get("stationName").toString());
					intent.putExtra("gazolePrice", item.get("gazolePrice").toString());
					intent.putExtra("sp98Price", item.get("sp98Price").toString());
					intent.putExtra("sp95Price", item.get("sp95Price").toString());
					intent.putExtra("e85Price", item.get("e85Price").toString());
					startActivity(intent);
				}
			});
	}; 
}
