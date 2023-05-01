package dao;

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PositionDao {
	private final Connection con;

	public PositionDao() throws SQLException {
		this.con = ConnectionFactory.createConnection("sqlite");
	}

	public boolean checkIfUsernameAlreadyExists(String username) {
		return false;
	}

	public void insertUsername(String username) {

	}

	public void insertPosition(String username, String lat, String lon) {

	}
}
