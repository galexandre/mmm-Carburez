package fr.istic.project.gasLocation.models;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataManager {
	private Context context;
	private SQLiteDatabase database;
	private GasDao gasDao;
	private StationDao stationDao;

	public DataManager(Context context) throws IOException {
		setContext(context);
		SQLiteOpenHelper openHelper = new GasStationDatabase(context);
		((GasStationDatabase) openHelper).createDataBase();
		setDatabase(openHelper.getWritableDatabase());
		// openHelper.onOpen(database);
		/*this.stationDao = new StationDao(new StationTableDefinition(), database);
		this.gasDao = new GasDao(new GasTableDefinition(), database);*/
	}

	public GasDao getGasDao() {
		return gasDao;
	}

	public StationDao getStationDao() {
		return stationDao;
	}

	/*public Gas getGas(Long id) {
		return getGasDao().get(id);
	}*/

	/*public long saveStation() {
		Station s = new Station(1, (double) 44, (double) 4, 49000, "biduc",
				"rennes", new Date(12), new Date(20), "", "");
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getStationDao().save(s);
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

	/*
	 * public List<Gas> getGasList(){ return getGasDao().getAll(); }
	 */

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
