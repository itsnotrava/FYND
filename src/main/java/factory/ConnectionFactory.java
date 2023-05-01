package factory;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection createConnection(String typeOfDatabase) throws SQLException {
		switch (typeOfDatabase) {
			case "sqlite" -> {
				SQLiteDataSource dataSource = new SQLiteDataSource();
				dataSource.setUrl("jdbc:sqlite:/");
				return dataSource.getConnection();
			}
			default -> {
				return null;
			}
		}
	}
}
