package fr.istic.project.gasLocation.models;

import java.io.File;
import java.io.IOException;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class GasStationDatabase extends SQLiteOpenHelper {

	private Context mycontext;

	private String DB_PATH;
	private static String DB_NAME = "gasStation.sqlite";// the extension may be
														// .sqlite or .db
	public SQLiteDatabase myDataBase;

	public GasStationDatabase(Context context) throws IOException {
		super(context, DB_NAME, null, 1);
		this.mycontext = context;
		DB_PATH = mycontext.getApplicationContext().getPackageName() + "/database/";
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();
			this.onCreate(myDataBase);
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}
	/**
	 * Method used to open the database
	 * @throws SQLException
	 */
	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}
	
	/**
	 * Method used to delete the database
	 * @throws SQLException
	 */
	public void deleteDatabase() throws SQLException {
		boolean isDataBaseExists = this.checkDataBase();
		if(isDataBaseExists) {
			this.close();
			File dataBase = new File(DB_PATH + DB_NAME);
			dataBase.delete();
		}
	}
	
	/**
	 * Method which aim is to fill the database using a sql script after having parsing the xml file from open data
	 */
	public void fillDataBase()
	{
		//myDataBase.execSQL(sqlQuery);
	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	/**
	 * Method used to create the database
	 * @param db
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "" 
				
				+ "CREATE TABLE Gas ("
				+ "idGas bigint not null," 
				+ "idStation bigint not null,"
				+ "gasName varchar(255) not null,"
				+ "price integer not null,"
				+ "dateMAJ date not null,"
				+ "dateUpdate date,"
				+ "dateRupture date,"
				+ "typeRupture char,"
				+ "primary key (idGas)" 
				+ ");"
				
				+ "CREATE TABLE Station (" 
				+ "idStation bigint not null,"
				+ "address varchar(255) not null,"
				+ "postalCode integer not null,"
				+ "startClosed date not null,"
				+ "endClosed date not null,"
				+ "startHour varchar(255) not null,"
				+ "endHour varchar(255) not null,"
				+ "latitude varchar(255) not null,"
				+ "longitude varchar(255) not null,"
				+ "exceptDays varchar(255) not null,"
				+ "services varchar(255) not null,"
				+ "closedType char(1) not null,"
				+ "town varchar(255) not null," 
				+ "primary key (idStation)" 
				+ ");";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}