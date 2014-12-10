package fr.istic.project.gasLocation.models;

import java.util.List;

import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class StationDao {

/*	public void createStation(ConnectionSource cs, String key, Integer value)
			throws SQLException {
		Dao<Station, Integer> dao = DaoManager.createDao(cs, Station.class);

		Station Station = new Station();
		Station.setKey(key);
		Station.setValue(value);
		dao.create(Station);
	}*/

	public Station findById(ConnectionSource cs, Integer id)
			throws SQLException, java.sql.SQLException {
		Dao<Station, Integer> dao;
		
			dao = DaoManager.createDao(cs, Station.class);
	
		return dao.queryForId(id);
	}

	public List<Station> getAll(ConnectionSource cs) throws SQLException, java.sql.SQLException {
		Dao<Station, Integer> dao = DaoManager.createDao(cs, Station.class);
		return dao.queryForAll();
	}

	/*public void update(ConnectionSource cs, Integer id, Integer value)
			throws SQLException {
		Station Station = findById(cs, id);

		if (Station != null) {
			Dao<Station, Integer> dao = DaoManager.createDao(cs, Station.class);
			Station.setValue(value);
			dao.update(Station);
		}
	}*/
}
