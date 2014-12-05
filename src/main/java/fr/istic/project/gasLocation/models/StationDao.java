package fr.istic.project.gasLocation.models;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;

public class StationDao extends DroidDao<Station,Long> {

    public StationDao(TableDefinition<Station> tableDefinition, SQLiteDatabase database) {
            super(Station.class, tableDefinition, database);
    }
}
