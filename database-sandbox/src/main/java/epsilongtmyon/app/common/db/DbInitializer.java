package epsilongtmyon.app.common.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.sql.DataSource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import epsilongtmyon.app.common.db.annotation.MyDB;

/**
 * 開発用データベースの初期化
 */
@ApplicationScoped
public class DbInitializer {

	@Inject
	Logger logger;

	@Inject
	@MyDB
	DataSource dataSource;

	@Transactional
	public void initialize(@Observes Startup startup) {
		try (Connection con = dataSource.getConnection()) {
			
			execute(con, "init-db.sql");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void execute(Connection con, String sqlResource) {

		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(sqlResource);
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr)) {

			StringBuilder sqlBuf = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null) {
				line = line.stripTrailing();
				if (line.isEmpty() ||
						line.stripLeading().startsWith("--")) {
					continue;
				}

				sqlBuf.append(line).append(System.lineSeparator());

				if (line.endsWith(";")) {
					String sql = sqlBuf.substring(0, sqlBuf.lastIndexOf(";"));
					sqlBuf = new StringBuilder();

					try (Statement stmt = con.createStatement()) {
						logger.info(sql);
						stmt.execute(sql);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}

				}

			}

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}
}
