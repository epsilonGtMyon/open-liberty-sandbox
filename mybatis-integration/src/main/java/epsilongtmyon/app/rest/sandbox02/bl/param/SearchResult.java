package epsilongtmyon.app.rest.sandbox02.bl.param;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import epsilongtmyon.app.common.db.entity.AppLog;

public class SearchResult {

	private List<SearchResultDto> records;

	public List<SearchResultDto> getRecords() {
		return records;
	}

	public void setRecords(List<SearchResultDto> records) {
		this.records = records;
	}

	public static SearchResult fromAppLogs(List<AppLog> logs) {
		SearchResult r = new SearchResult();

		List<SearchResultDto> resultDtos = logs.stream().map(SearchResultDto::fromAppLog).toList();
		r.setRecords(resultDtos);

		return r;
	}

	// ------------------

	public static class SearchResultDto {

		private BigInteger seq;

		private String logMessage;

		protected Timestamp createdAt;

		protected Timestamp updatedAt;

		public BigInteger getSeq() {
			return seq;
		}

		public void setSeq(BigInteger seq) {
			this.seq = seq;
		}

		public String getLogMessage() {
			return logMessage;
		}

		public void setLogMessage(String logMessage) {
			this.logMessage = logMessage;
		}

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Timestamp getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}

		public static SearchResultDto fromAppLog(AppLog log) {
			SearchResultDto d = new SearchResultDto();
			d.setSeq(log.getSeq());
			d.setLogMessage(log.getLogMessage());
			d.setCreatedAt(log.getCreatedAt());
			d.setUpdatedAt(log.getUpdatedAt());

			return d;
		}

	}
}
