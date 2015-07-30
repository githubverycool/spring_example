package spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import spring.dao.BaseDao;
import spring.support.JdbcDaoSupport;


public  class BaseDaoImpl<T extends Serializable> extends JdbcDaoSupport
		implements BaseDao<T> {

	private static final Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	private Serializable mapped;

	private String sql;

	@SuppressWarnings("rawtypes")
	private RowMapper rowMapper;

	public Serializable getMapped() {
		return mapped;
	}

	public void setMapped(Serializable mapped) {
		this.mapped = mapped;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RowMapper getRowMapper() {
		if (rowMapper == null) {
			if (mapped != null) {
				rowMapper = new BeanPropertyRowMapper(mapped.getClass());
			} else {
				rowMapper = new BeanPropertyRowMapper<T>();
			}
		}
		log.info("rowMapper:"+rowMapper);
		return rowMapper;
	}

	@SuppressWarnings("rawtypes")
	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}

	/**
	 * 通过sql语句将查询结果封装在List的对象中;
	 * list的中对象类型由rowMapper决定,rowMapper若为空,通过mapped类型决定,
	 * 若mapped为空则默认为对应的实体
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List queryForSetMapped(String sql) throws Exception {
		return getJdbcTemplate().query(sql, getRowMapper());
	}

	/**
	 * 使用spring 配置的sql语句进行查询
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryByIocSql() throws Exception {
		return queryForSetMapped(getSql());
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> query(String sql,RowMapper<?> rowMapper)throws Exception{
		return getJdbcTemplate().query(sql, rowMapper);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> queryForEntry(String sql)throws Exception{
		return (List<T>) query(sql,new BeanPropertyRowMapper<T>());
	}

	@Override
	public Integer save(T entry) throws Exception {
		String sql = "insert into user(name)value('李四')";
		getJdbcTemplate().execute(sql);
		return 1;
	}

	
}
