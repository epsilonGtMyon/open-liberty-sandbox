package epsilongtmyon.app.common.db.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.cdi.Mapper;

import epsilongtmyon.app.common.db.entity.AppLog;

// injectできるようにするために@Mapperをつける
// MyBatis自体に認識されるわけではない
@Mapper 
public interface AppLogMapper {

	@Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "SEQ")
	@Insert("""
			insert into APP_LOG (
			   LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			) values (
			   #{logMessage, jdbcType=VARCHAR}
			  ,#{createdAt, jdbcType=TIMESTAMP}
			  ,#{updatedAt, jdbcType=TIMESTAMP}
			)
						""")
	int insert(AppLog myLog);

	@Update("""
			update APP_LOG set
			   LOG_MESSAGE = #{logMessage, jdbcType=VARCHAR}
			  ,UPDATED_AT = #{updatedAt, jdbcType=TIMESTAMP}
			where
			  SEQ = #{seq}
			""")
	int update(AppLog myLog);

	@Delete("""
			delete from APP_LOG
			where
			  SEQ = #{seq}
			""")
	int delete(AppLog myLog);

	@Delete("""
			delete from APP_LOG
			where
			  SEQ = #{seq}
			""")
	int deleteByKey(@Param("seq") BigInteger seq);

	@Select("""
			select
			   SEQ
			  ,LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			from
			  APP_LOG
			where
			  SEQ = #{seq}
			""")
	AppLog select(@Param("seq") BigInteger seq);

	@Select("""
			select
			   SEQ
			  ,LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			from
			  APP_LOG
			order by
			  SEQ
			""")
	List<AppLog> selectAll();
}
