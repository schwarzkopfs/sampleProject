package ssb.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ssb.model.BarUser;

public interface BarUserRepository {
		
	static final String SQL_SELECY_BY_MSISDN = "SELECT * FROM BAR_GPRS WHERE MSISDN = #{msisdn}";

	static final String SQL_INSERT = "INSERT INTO BAR_GPRS (ID, MSISDN , MODIFY_DATE) "
			+ "VALUES ( #{id} , #{msisdn} , #{modifyDate} )";
	
	static final String SQL_UPDATE = "UPDATE BAR_GPRS SET MODIFY_DATE = #{modifyDate} WHERE MSISDN = #{msisdn}";
	
	static final String SQL_DELETE_BY_LIST = "DELETE FROM BAR_GPRS WHERE MSISDN IN (${msisdn})";
	
	static final String SQL_SELECT_BY_LIST = "SELECT * FROM BAR_GPRS WHERE MSISDN IN (${msisdn})";

	static final String SQL_DELETE_BY_DAYS = "DELETE FROM BAR_GPRS WHERE MODIFY_DATE <= (SYSDATE- #{clearInDays})";
	
	@Select(SQL_SELECY_BY_MSISDN)
	public BarUser findByMsisdn(BarUser barUser) throws RuntimeException;
	
	@Insert(SQL_INSERT)
	@Options(flushCache=FlushCachePolicy.TRUE)
	public int insert(BarUser barUser) throws RuntimeException;
	
	@Update(SQL_UPDATE)
	public int update(BarUser barUser) throws RuntimeException;

	@Delete(SQL_DELETE_BY_LIST)
	@Options(flushCache=FlushCachePolicy.TRUE)
	public void deleteByList(@Param("msisdn") String msisdn) throws RuntimeException;
	
	@Select(SQL_SELECT_BY_LIST)
	public List<BarUser> findByList(@Param("msisdn") String msisdn) throws RuntimeException;
	
	@Delete(SQL_DELETE_BY_DAYS)
	@Options(flushCache=FlushCachePolicy.TRUE)
	public int clear(String clearInDays) throws RuntimeException;
}
