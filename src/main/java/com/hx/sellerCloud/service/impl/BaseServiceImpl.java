/**
 * file name : BaseServiceImpl.java
 * created at : 下午5:20:27 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hx.sellerCloud.service.BaseService;
import com.hx.sellerCloud.util.MysqlSqlGenerator;
import com.hx.sellerCloud.util.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

// BaseServiceImpl
public class BaseServiceImpl<E> implements BaseService<E> {

	@Autowired
    protected JdbcTemplate jdbcTemplate;
	protected String tableName;
	protected String id;
	protected RowMapper<E> rowMapper;
    
	// 初始化
//	public BaseServiceImpl(String tableName, RowMapper<E> rowMapper) {
//		if((tableName == null) || (rowMapper == null) ) {
//			throw new RuntimeException("tableName or rowMapper can't be null !");
//		}
//		
//		this.tableName = tableName;
//		this.rowMapper = rowMapper;
//	}
	public BaseServiceImpl(RowMapper<E> rowMapper) {
		if((rowMapper == null) ) {
			throw new RuntimeException("tableName or rowMapper can't be null !");
		}
		
		this.id = "id";
		this.rowMapper = rowMapper;
	}
	
	@Override
	public E findById(String id) {
		// 可能主键并非id属性, 因此 这就需要有一定的约束了, 使用此方法的表统一约定id为主键
		String sql = MysqlSqlGenerator.generateQuerySql(tableName, "*", this.id + "=" + id, null, null);
//		try {
//			E result = jdbcTemplate.queryForObject(sql, rowMapper);
//			return result;
//			// got 'EmptyResultDataAccessException' if there are any record
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		}
		// ---------------------------------------------------------------------
		List<E> results = jdbcTemplate.query(sql, rowMapper);
		if(results.size() > 0) {
			return results.get(0);
		}
		
		return null;
	}
	
	@Override
	public List<E> findAll() {
		String sql = MysqlSqlGenerator.generateQuerySql(tableName, "*", null, null, null);
		List<E> result = jdbcTemplate.query(sql, rowMapper);
		return result;
	}

	@Override
	public void insert(E ele) throws Exception {
		String sql = MysqlSqlGenerator.generateInsertSql(tableName, JSONObject.fromObject(ele) );
		jdbcTemplate.execute(sql);
	}

	@Override
	public void update(E ele) throws Exception {
		JSONObject obj = JSONObject.fromObject(ele);
		quote(obj);
		String update = Tools.encapQueryString0(obj, "=", ",");
		String sql = MysqlSqlGenerator.generateUpdateSql(tableName, update, this.id + "=" + obj.get(this.id) );
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public void saveOrUpdate(E ele) throws Exception {
		JSONObject obj = JSONObject.fromObject(ele);
		String insertSql = MysqlSqlGenerator.generateInsertSql(tableName, obj);
		
		quote(obj);
		String updateSql = Tools.encapQueryString0(obj, "=", ",");
		
		String saveOrUpdateSql = insertSql + " on duplicate key update " + updateSql;
		jdbcTemplate.execute(saveOrUpdateSql);
	}

	@Override
	public void removeById(String id) throws Exception {
		String sql = MysqlSqlGenerator.generateDeleteSql(tableName, this.id + "=" + id);
		jdbcTemplate.execute(sql);
	}
	
	// 为obj的各个value加上'', for sql grammer
	private void quote(JSONObject obj) {
		JSONArray names = obj.names();
		for(Object _key : names) {
			String key = (String) _key;
			obj.put(key, "'" + obj.getString(key) + "'");
		}
	}
	
}
