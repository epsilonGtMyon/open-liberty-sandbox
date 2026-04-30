package epsilongtmyon.app.common.db.entity;

import java.sql.Timestamp;

/**
 * 共通クラス
 */
public abstract class AbstractEntity {

	/** 作成日時 */
	protected Timestamp createdAt;

	/** 更新日時 */
	protected Timestamp updatedAt;

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

}
