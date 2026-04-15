package epsilongtmyon.app.sandbox01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/sandbox01" })
public class Sandbox01Servlet extends  HttpServlet {


	@Resource(name = "jdbc/myDB")
	DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		PrintWriter pw = resp.getWriter();

		pw.println("<html>");

		pw.println("<head>");
		pw.println("  <meta charset=\"UTF-8\">");
		pw.println("</head>");

		pw.println("<body>");
		pw.println("はろー：" + getClass().getName());

			try (Connection con = dataSource.getConnection();
					PreparedStatement pstmt = con.prepareStatement("select * from APP_LOG order by LOG_ID");) {
				try (ResultSet rs = pstmt.executeQuery()) {

					pw.println("<table><tbody>");
					while (rs.next()) {
						pw.println("<tr>");
						pw.println("<td>" + rs.getString("LOG_ID") + "</td>");
						pw.println("<td>" + rs.getString("LOG_MESSAGE") + "</td>");
						pw.println("<td>" + rs.getString("LOGGED_AT") + "</td>");
						pw.println("</tr>");
					}
					pw.println("</tbody></table>");
	
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		pw.println("</body>");

		pw.println("</html>");
	}
}
