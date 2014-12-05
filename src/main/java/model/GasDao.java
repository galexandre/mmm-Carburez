package model;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;

public class GasDao extends DroidDao<Gas,Long> {

    public GasDao(TableDefinition<Gas> tableDefinition, SQLiteDatabase database) {
            super(Gas.class, tableDefinition, database);
    }
}
