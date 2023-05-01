package factory;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection createConnection(String typeOfDatabase) throws SQLException {
		switch (typeOfDatabase) {
			case "sqlite" -> {
				SQLiteDataSource dataSource = new SQLiteDataSource();
				dataSource.setUrl("jdbc:sqlite:/home/nicola/Documents/CODE/Java/FYND/fynd.sqlite");
				return dataSource.getConnection();
			}
			case "mysql" -> {
				MysqlDataSource dataSource = new MysqlDataSource();
				dataSource.setUrl("jdbc:mysql://root@localhost:3306/Fynd");
				return dataSource.getConnection();
			}
			default -> {
				return null;
			}
		}
	}
}
