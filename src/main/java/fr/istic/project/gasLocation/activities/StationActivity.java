package fr.istic.project.gasLocation.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	
	private Station station;

	private Button bt_road;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_activity);
		Intent intent = getIntent();

		// get widgets
		bt_road = (Button) findViewById(R.id.bt_road);
		logoStation = (ImageView) findViewById(R.id.logoStation);
		logoStation.setImageResource(R.drawable.leclerc);
		stationTown = (TextView) findViewById(R.id.tv_town);
		stationAdress = (TextView) findViewById(R.id.tv_adress);
		gazolePrice = (TextView) findViewById(R.id.tv_gazolePrice);
		sp95price = (TextView) findViewById(R.id.tv_sp95price);
		sp98price = (TextView) findViewById(R.id.tv_sp98price);
		e85price = (TextView) findViewById(R.id.tv_e85price);

		// fill data
		if (intent != null) {
			if (intent.getParcelableExtra("station")!=null) {
				station = intent.getParcelableExtra("station");

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
		
		// events
		bt_road.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent navigation = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://maps.google.com/maps?saddr="
								+ station.getLatitude() + "," + station.getLongitude()
								+ "&daddr=" + station.getLatitude() + ","
								+ station.getLongitude()));
				startActivity(navigation);
			}
		});
		
	}
}