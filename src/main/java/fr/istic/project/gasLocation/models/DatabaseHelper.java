package fr.istic.project.gasLocation.models;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import fr.istic.project.gasLocation.R;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something
	// appropriate for your app
	public static final String DATABASE_NAME = "gasStation.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;
	// the DAO object we use to access the SimpleData table
	private Dao<Gas, Integer> gasDao = null;
	private Dao<Station, Integer> stationDao = null;
	private RuntimeExceptionDao<Gas, Integer> gasRuntimeDao = null;
	private RuntimeExceptionDao<Station, Integer> stationRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Gas.class);
			TableUtils.createTable(connectionSource, Station.class);
		} catch (SQLException e) {
			System.out.println("lol");
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

		// here we try inserting data in the on-create as a test
		/*
		 * RuntimeExceptionDao<SimpleData, Integer> dao = getSimpleDataDao();
		 * long millis = System.currentTimeMillis(); // create some entries in
		 * the onCreate SimpleData simple = new SimpleData(millis);
		 * dao.create(simple); simple = new SimpleData(millis + 1);
		 * dao.create(simple); Log.i(DatabaseHelper.class.getName(),
		 * "created new entries in onCreate: " + millis);
		 */
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Gas.class, true);
			TableUtils.dropTable(connectionSource, Station.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our Gas class. It will
	 * create it or just give the cached value.
	 * 
	 * @throws java.sql.SQLException
	 */
	public Dao<Gas, Integer> getDaoGas() throws SQLException {
		if (gasDao == null) {
			try {
				gasDao = getDao(Gas.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gasDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our SimpleData class. It will create it or just give the cached
	 * value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<Gas, Integer> getGasRuntimeDao() {
		if (gasRuntimeDao == null) {
			gasRuntimeDao = getRuntimeExceptionDao(Gas.class);
		}
		return gasRuntimeDao;
	}

	/**
	 * Returns the Database Access Object (DAO) for our Station class. It will
	 * create it or just give the cached value.
	 * 
	 * @throws java.sql.SQLException
	 */
	public Dao<Station, Integer> getDaoStation() throws SQLException {
		if (stationDao == null) {
			stationDao = getDao(Station.class);
		}
		return stationDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our SimpleData class. It will create it or just give the cached
	 * value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<Station, Integer> getStationRuntimeDao() {
		if (stationRuntimeDao == null) {
			stationRuntimeDao = getRuntimeExceptionDao(Station.class);
		}
		return stationRuntimeDao;
	}

	// method for insert data
	public int addGas(Gas gas) {
		RuntimeExceptionDao<Gas, Integer> dao = getGasRuntimeDao();
		int i = dao.create(gas);
		return i;
	}

	// method for insert data
	public int addStation(Station s) {
		RuntimeExceptionDao<Station, Integer> dao = getStationRuntimeDao();
		int i = dao.create(s);
		return i;
	}

	// method for insert data
	public Gas getGas() {
		RuntimeExceptionDao<Gas, Integer> dao = getGasRuntimeDao();
		Gas g = dao.queryForId(1);
		return g;
	}

	// method for insert data
	public List<Station> getAllStationFromPostalCode(String postalCode) {
		RuntimeExceptionDao<Station, Integer> dao = getStationRuntimeDao();
		List<Station> stat = dao.queryForEq("postalCode", postalCode);
		for(Station s : stat)
		{
			RuntimeExceptionDao<Gas, Integer> daoGas = getGasRuntimeDao();
			List<Gas> gas = daoGas.queryForEq("station_id", s.getIdStation());
			for(Gas g : gas){
			s.getGases().put(g.getGasName(), (float)g.getPrice());
			}
		}
		return stat;
	}

	// method for insert data
	public List<Station> getAllStation() {
		RuntimeExceptionDao<Station, Integer> dao = getStationRuntimeDao();
		List<Station> stat = dao.queryForAll();
		return stat;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		gasDao = null;
		stationDao = null;
		gasRuntimeDao = null;
		stationRuntimeDao = null;
	}
}
