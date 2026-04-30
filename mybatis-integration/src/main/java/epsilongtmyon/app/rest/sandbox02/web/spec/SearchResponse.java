package epsilongtmyon.app.rest.sandbox02.web.spec;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import epsilongtmyon.app.rest.sandbox02.bl.param.SearchResult;
import epsilongtmyon.app.rest.sandbox02.bl.param.SearchResult.SearchResultDto;

public class SearchResponse {

	private List<SearchResponseDto> records;
	
	
	public List<SearchResponseDto> getRecords() {
		return records;
	}



	public void setRecords(List<SearchResponseDto> records) {
		this.records = records;
	}
	
	public static SearchResponse fromSearchResult(SearchResult result) {
		SearchResponse resp = new SearchResponse();
		
		List<SearchResponseDto> records = result.getRecords().stream().map(SearchResponseDto::fromResultDto).toList();
		resp.setRecords(records);
		
		return resp;
	}



	public static class SearchResponseDto {

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

		public static SearchResponseDto fromResultDto(SearchResultDto log) {
			SearchResponseDto d = new SearchResponseDto();
			d.setSeq(log.getSeq());
			d.setLogMessage(log.getLogMessage());
			d.setCreatedAt(log.getCreatedAt());
			d.setUpdatedAt(log.getUpdatedAt());

			return d;
		}

	}
}
