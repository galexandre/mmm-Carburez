package fr.istic.project.gasLocation.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.models.Station;

public class StationActivity extends Activity {

	public static final String ARG_SECTION_NUMBER = "section_number";

	public static final String TAG = "StationActivity";
	
	private TextView stationTown;
	private TextView stationAdress;
	private TextView gazolePrice;
	private TextView sp98price;
	private TextView sp95price;
	private TextView e85price;
	private ImageView logoStation;

	private ArrayList<Station> currentStations;
	private String idStation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_activity);
		Intent intent = getIntent();

		logoStation = (ImageView) findViewById(R.id.logoStation);
		logoStation.setImageResource(R.drawable.leclerc);
		stationTown = (TextView) findViewById(R.id.tv_town);
		stationAdress = (TextView) findViewById(R.id.tv_adress);
		gazolePrice = (TextView) findViewById(R.id.tv_gazolePrice);
		sp95price = (TextView) findViewById(R.id.tv_sp95price);
		sp98price = (TextView) findViewById(R.id.tv_sp98price);
		e85price = (TextView) findViewById(R.id.tv_e85price);


		if (intent != null) {
			
			if (intent.getParcelableExtra("station")!=null) {
				Station station = intent.getParcelableExtra("station");
				
				// fill data on the UI
				stationAdress.setText(station.getAddress());
				stationTown.setText(station.getTown());
				
				if (station.getGases() != null) {
					
				} else {
					gazolePrice.setText("?");
					sp98price.setText("?");
					sp95price.setText("?");
					e85price.setText("?");
				}
			}
		}
	}
}