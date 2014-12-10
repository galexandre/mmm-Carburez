package fr.istic.project.gasLocation.models;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import android.R.integer;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataManager {
	private Context context;
	private GasStationDatabase gasStationData;
	private SQLiteDatabase database;
	private GasDao gasDao;
	private StationDao stationDao;
	private ConnectionSource cs;

	public DataManager(Context context) throws IOException, SQLException, java.sql.SQLException {
		setContext(context);
		this.getGasStationDatabase();
		cs = new AndroidConnectionSource(gasStationData);
		///*((GasStationDatabase) openHelper)*/gasStationData.createDataBase();
		setDatabase(gasStationData.getWritableDatabase());
		// openHelper.onOpen(database);
		this.stationDao = (StationDao) gasStationData.getStationDao();
		this.gasDao = (GasDao) gasStationData.getGasDao();
	}
	
	/**
	 * You'll need this in your class to get the helper from the manager once per class.
	 * @throws IOException 
	 */
	private GasStationDatabase getGasStationDatabase() throws IOException {
		if (gasStationData == null) {
			gasStationData = GasStationDatabase.getGasStationDatabase(context);
		}
		return gasStationData;
	}

	public GasDao getGasDao() {
		return gasDao;
	}

	public StationDao getStationDao() {
		return stationDao;
	}

	public Gas getGas(Long id) throws SQLException, java.sql.SQLException {
		return getGasDao().findById(cs,(id.intValue()));
	}

	/*public long saveStation() {
		Station s = new Station(1, (double) 44, (double) 4, 49000, "biduc",
				"rennes", new Date(12), new Date(20), "", "");
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getStationDao(). save(s);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}*/

	/*
	 * public List<Gas> getGasLinkedToStation(Long id){ return getGasDao().(id);
	 * }
	 */

	
	 public List<Gas> getGasList() throws SQLException, java.sql.SQLException{ 
		 return getGasDao().getAll(cs); 
		 }
	 

	public void setDatabase(SQLiteDatabase db) {
		database = db;
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
