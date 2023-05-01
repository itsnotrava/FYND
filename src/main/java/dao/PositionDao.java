package dao;

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDao {
	private final Connection con;

	public PositionDao() throws SQLException {
		this.con = ConnectionFactory.createConnection("sqlite");
	}

	public boolean checkIfUsernameAlreadyExists(String username) throws SQLException {
		String sql = "SELECT * FROM Position WHERE username=?";
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt(1) != 0;
		}
		return false;
	}

	public void insertUsername(String username) throws SQLException {
		String sql = "INSERT INTO Position (username) VALUES (?)";
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, username);

		ps.execute();
	}

	public void insertPosition(String username, String position) throws SQLException {
		String sql = "UPDATE Position SET position=? WHERE username=?";
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, position);
		ps.setString(2, username);

		ps.execute();
	}
}
