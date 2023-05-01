package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.PositionDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="PositionServlet", value="/position")
public class PositionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");

		JsonObject jsResponse = new JsonObject();
		jsResponse.addProperty("position", "44.488 11.3752");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsResponse);
		printWriter.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");

		String sBody = getBody(request);
		Gson gson = new Gson();
		JsonObject jsBody = gson.fromJson(sBody, JsonObject.class);

		String username = jsBody.get("username").getAsString();
		String position = jsBody.get("position").getAsString();

		try {
			PositionDao positionDao = new PositionDao();
			positionDao.insertPosition(username, position);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		JsonObject jsResponse = new JsonObject();
		jsResponse.addProperty("result", "success");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsResponse);
		printWriter.flush();
	}

	private static String getBody(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}