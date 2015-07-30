package spring.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface BaseDao<T extends Serializable> {

	public String getSql();

	@SuppressWarnings("rawtypes")
	public List queryForSetMapped(String sql) throws Exception;

	@SuppressWarnings("rawtypes")
	public List queryByIocSql() throws Exception;

	public List<T> queryForEntry(String sql) throws Exception;

	public List<?> query(String sql, RowMapper<?> rowMapper) throws Exception;

	public Integer save(T entry) throws Exception;

}
