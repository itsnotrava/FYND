package servlet;

import dao.PositionDao;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		PositionDao dao = new PositionDao();
		dao.insertUsername("ciao");
		dao.insertPosition("ciao", "24°34'N 89°11'E");
	}
}
