package fr.istic.project.gasLocation.models;

import java.util.List;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;

public class GasDao extends DroidDao<Gas,Long> {

    public GasDao(TableDefinition<Gas> tableDefinition, SQLiteDatabase database) {
            super(Gas.class, tableDefinition, database);
    }
    
    public List<Gas> getGasByStationId(Long id){
    	return this.getAllbyClause("idStation = ?", new String[]{""+id}, null, null, null);
    }
}
