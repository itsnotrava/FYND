package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="PositionServlet", value="/position")
public class PositionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");

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