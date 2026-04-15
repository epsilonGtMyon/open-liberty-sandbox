package epsilongtmyon.app.sandbox02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/sandbox02" })
public class Sandbox02Servlet extends HttpServlet {

	@Inject
	Sandbox02Service service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

		String ex = req.getParameter("ex");
		service.execute(ex);

		PrintWriter pw = resp.getWriter();

		pw.println("<html>");

		pw.println("<head>");
		pw.println("  <meta charset=\"UTF-8\">");
		pw.println("</head>");

		pw.println("<body>");
		pw.println("はろー：" + getClass().getName());

		pw.println("</body>");

		pw.println("</html>");
	}
}
