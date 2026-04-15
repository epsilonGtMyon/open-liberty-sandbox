package epsilongtmyon.app.sandbox02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

// Transactionalと連動させる。

@ApplicationScoped
@Transactional
public class Sandbox02Service {

	@Resource(name = "jdbc/myDB")
	DataSource dataSource;

	public void execute(String ex) {

		try (Connection con = dataSource.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement("insert into APP_LOG(LOG_MESSAGE, LOGGED_AT) values (?, ?)");) {

			pstmt.setString(1, "登録します" + LocalDateTime.now().toString());
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

			pstmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		if (ex != null) {
			// この時にロールバックされることを確認
			throw new RuntimeException("例外");
		}
	}
}
