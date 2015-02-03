package fr.istic.project.gasLocation.adapter;

import fr.istic.project.gasLocation.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListSectionFragment extends ListFragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_list, container, false);

		// Get ListView object from xml
		final ListView list_stations; 
		//list_stations = (ListView) findViewById(R.id.list_stations);

		// Defined Array values to show in ListView
		String[] values = new String[] { "Android List View", 
				"Adapter implementation",
				"Simple List View In Android",
				"Create List View Android", 
				"Android Example", 
				"List View Source Code", 
				"List View Array Adapter", 
				"Android Example List View" 
		};

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, values);


		// Assign adapter to ListView
		setListAdapter(adapter); 

		// ListView Item Click Listener
		return rootView;
	}


	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  
		Toast toast = Toast.makeText(super.getActivity().getApplicationContext(), "A cliqué !", Toast.LENGTH_SHORT);
		toast.show();   
	}   
}
