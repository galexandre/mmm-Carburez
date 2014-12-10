package fr.istic.project.gasLocation.models;

import java.util.List;

import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class GasDao {

	/*
	 * public void createGas(ConnectionSource cs, String key, Integer value)
	 * throws SQLException { Dao<Gas, Integer> dao = DaoManager.createDao(cs,
	 * Gas.class);
	 * 
	 * Gas Gas = new Gas(); Gas.setKey(key); Gas.setValue(value);
	 * dao.create(Gas); }
	 */

	public Gas findById(ConnectionSource cs, Integer id) throws SQLException,
			java.sql.SQLException {
		Dao<Gas, Integer> dao;

		dao = DaoManager.createDao(cs, Gas.class);

		return dao.queryForId(id);
	}

	public List<Gas> getAll(ConnectionSource cs) throws SQLException,
			java.sql.SQLException {
		Dao<Gas, Integer> dao = DaoManager.createDao(cs, Gas.class);
		return dao.queryForAll();
	}

	/*
	 * public void update(ConnectionSource cs, Integer id, Integer value) throws
	 * SQLException { Gas Gas = findById(cs, id);
	 * 
	 * if (Gas != null) { Dao<Gas, Integer> dao = DaoManager.createDao(cs,
	 * Gas.class); Gas.setValue(value); dao.update(Gas); } }
	 */
}
