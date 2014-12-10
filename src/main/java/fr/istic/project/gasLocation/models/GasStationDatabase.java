package fr.istic.project.gasLocation.models;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

public class GasStationDatabase extends OrmLiteSqliteOpenHelper {

	private Context mycontext;

	private String DB_PATH;
	private static String DB_NAME = "gasStation.sqlite";// the extension may be
														// .sqlite or .db
	public SQLiteDatabase myDataBase;
	// the DAO object we use to access the SimpleData table
	private Dao<Gas, Long> gasDao = null;
	private Dao<Station, Long> stationDao = null;
	private static final AtomicInteger usageCounter = new AtomicInteger(0);
	// we do this so there is only one helper
	private static GasStationDatabase helperGasStation = null;

	public GasStationDatabase(Context context) throws IOException {
		super(context, DB_NAME, null, 1);
		this.mycontext = context;
		DB_PATH = "/data/data/database/";
		//createDataBase();
	}




		/**
		 * Get the helper, possibly constructing it if necessary. For each call to this method, there should be 1 and only 1
		 * call to {@link #close()}.
		 * @throws IOException 
		 */
		public static synchronized GasStationDatabase getGasStationDatabase(Context context) throws IOException {
			if (helperGasStation == null) {
				helperGasStation = new GasStationDatabase(context);
			}
			usageCounter.incrementAndGet();
			return helperGasStation;
		}

		/**
		 * This is called when the database is first created. Usually you should call createTable statements here to create
		 * the tables that will store your data.
		 */
		public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
			try {
				Log.i(GasStationDatabase.class.getName(), "onCreate");
				TableUtils.createTable(connectionSource, Gas.class);
				TableUtils.createTable(connectionSource, Station.class);

				// here we try inserting data in the on-create as a test
				Dao<Gas, Long> dao = getGasDao();
				long millis = System.currentTimeMillis();
				// create some entries in the onCreate
				Gas gas = new Gas();
				dao.create(gas);
				Log.i(GasStationDatabase.class.getName(), "created new entries in onCreate: " + millis);
			} catch (SQLException e) {
				Log.e(GasStationDatabase.class.getName(), "Can't create database", e);
				throw new RuntimeException(e);
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
		 * the various data to match the new version number.
		 */
		public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
			try {
				Log.i(GasStationDatabase.class.getName(), "onUpgrade");
				TableUtils.dropTable(connectionSource, Gas.class, true);
				TableUtils.dropTable(connectionSource, Station.class, true);
				// after we drop the old databases, we create the new ones
				onCreate(db, connectionSource);
			} catch (SQLException e) {
				Log.e(GasStationDatabase.class.getName(), "Can't drop databases", e);
				throw new RuntimeException(e);
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Returns the Database Access Object (DAO) for our Gas class. It will create it or just give the cached
		 * value.
		 * @throws java.sql.SQLException 
		 */
		public Dao<Gas, Long> getGasDao() throws SQLException, java.sql.SQLException {
			if (gasDao == null) {
				gasDao = getDao(Gas.class);
			}
			return gasDao;
		}
		
		/**
		 * Returns the Database Access Object (DAO) for our Station class. It will create it or just give the cached
		 * value.
		 * @throws java.sql.SQLException 
		 */
		public Dao<Station, Long> getStationDao() throws SQLException, java.sql.SQLException {
			if (stationDao == null) {
				stationDao = getDao(Station.class);
			}
			return stationDao;
		}

		/**
		 * Close the database connections and clear any cached DAOs. For each call to {@link #getHelper(Context)}, there
		 * should be 1 and only 1 call to this method. If there were 3 calls to {@link #getHelper(Context)} then on the 3rd
		 * call to this method, the helper and the underlying database connections will be closed.
		 */
		@Override
		public void close() {
			if (usageCounter.decrementAndGet() == 0) {
				super.close();
				gasDao = null;
				stationDao = null;
				helperGasStation = null;
			}
		}


}
