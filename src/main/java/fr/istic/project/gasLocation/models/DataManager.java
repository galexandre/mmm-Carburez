package fr.istic.project.gasLocation.models;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataManager {
	 private Context context;
     private SQLiteDatabase database;
     private GasDao gasDao;
     private StationDao stationDao;
     
     public DataManager(Context context) throws IOException{
             setContext(context);
             SQLiteOpenHelper openHelper = new GasStationDatabase(context);
             setDatabase(openHelper.getWritableDatabase());
             openHelper.onOpen(database);
             this.stationDao = new StationDao(new StationTableDefinition(), database);
             this.gasDao = new GasDao(new GasTableDefinition(), database);                
     }
     
    public GasDao getGasDao() {
		return gasDao;
	}
	public StationDao getStationDao() {
		return stationDao;
	}
	public Gas getGas(Long id){           
             return getGasDao().get(id);
     }

	public List<Gas> getGasLinkedToStation(Long id){
		return getGasDao().getGasByStationId(id);
	}
	
     public List<Gas> getGasList(){
             return getGasDao().getAll();
     }
     
     public void setDatabase(SQLiteDatabase db)
     {
    	 database = db;
     }
     public void setContext(Context context)
     {
    	 this.context = context;
     }
    
}
