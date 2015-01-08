package fr.istic.project.gasLocation.adapter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import fr.istic.project.gasLocation.R;

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
		
		 final Button button = (Button) super.getActivity().findViewById(R.id.button_filter);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 Toast toast = Toast.makeText(getActivity().getApplicationContext(), "A cliqué !", Toast.LENGTH_SHORT);
         		toast.show();   
             }
         });
		

		// ListView Item Click Listener
		return rootView;
	}
	
	@Override  
	  public void onListItemClick(ListView l, View v, int position, long id) {  
		Toast toast = Toast.makeText(super.getActivity().getApplicationContext(), "A cliqué !", Toast.LENGTH_SHORT);
		toast.show();   
	  }
	
    public void OpenFilter(){
    	// 1. Instantiate an AlertDialog.Builder with its constructor
    	//AlertDialog.Builder builder = new AlertDialog.Builder("MainActivity()");
    	
    	// 2. Chain together various setter methods to set the dialog characteristics
    	//builder.setMessage(R.string.dialog_message)
    	//       .setTitle(R.string.dialog_title);

    	// 3. Get the AlertDialog from create()
    	//AlertDialog dialog = builder.create();
    	Toast toast = Toast.makeText(super.getActivity().getApplicationContext(), "A cliqué !", Toast.LENGTH_SHORT);
		toast.show();  
    }
}
