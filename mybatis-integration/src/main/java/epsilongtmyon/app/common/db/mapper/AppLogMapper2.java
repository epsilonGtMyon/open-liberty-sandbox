package epsilongtmyon.app.common.db.mapper;

import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

import epsilongtmyon.app.common.db.entity.AppLog;

@Mapper
public interface AppLogMapper2 {

	/**
	 * 最後に登録したものを取得
	 * 
	 * @return
	 */
	@Select("""
			select
			   SEQ
			  ,LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			from
			  APP_LOG
			order by
			   SEQ desc
			fetch first 1 rows only
			""")
	AppLog selectLast();
}
