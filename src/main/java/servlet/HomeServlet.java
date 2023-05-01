package servlet;

import dao.PositionDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.google.gson.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name="home", value="/home")
public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");

		// Genero e salvo su DB uno username temporaneo
		String username = "";
		try {
			PositionDao positionDao = new PositionDao();

			do {
				username = generateRandomUsername();
			} while (positionDao.checkIfUsernameAlreadyExists(username));

			positionDao.insertUsername(username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Restituisco lo username generato all'utente
		JsonObject jsResponse = new JsonObject();
		jsResponse.addProperty("username", username);

		// Invio la risposta
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsResponse);
		printWriter.flush();
	}

	private String generateRandomUsername() {
		// TODO: cambiare in futuro
		return UUID.randomUUID().toString().replaceAll("_", "").replaceAll("-", "").substring(0, 10);
	}

}