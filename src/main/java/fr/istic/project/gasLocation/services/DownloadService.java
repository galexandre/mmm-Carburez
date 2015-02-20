package fr.istic.project.gasLocation.services;

import fr.istic.project.gasLocation.controller.DownloadController;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class DownloadService extends IntentService{

	private static final String TAG = "DownloadService";
	

    private String url = "http://donnees.roulez-eco.fr/opendata/jour";

	
	public DownloadService() {
		super("DownloadService");
		Log.d(TAG, "Download service init");
	}
	
	public DownloadService(String name) {
		super(name);
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "DownloadService starting");
		
        // downloading, unzip, extract of xml file and persist
		DownloadController downloadController = new DownloadController(this.getApplicationContext(), this.url);
        downloadController.execute();
	}

}
