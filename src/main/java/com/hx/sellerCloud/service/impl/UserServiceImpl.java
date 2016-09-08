/**
 * file name : UserServiceImpl.java
 * created at : 下午5:19:21 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.hx.sellerCloud.bean.User;
import com.hx.sellerCloud.service.UserService;
import com.hx.sellerCloud.util.MysqlSqlGenerator;
import com.hx.sellerCloud.util.Tools;

@Service
// @ConfigurationProperties(locations = "classpath:resources/service.properties", ignoreUnknownFields = false, prefix = "user")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	// 初始化
	public UserServiceImpl() {
		super(new UserRowMapper() );
		this.tableName = "user";
	}
	
	@Override
	public void sealUser(String id) throws Exception {
		String sql = MysqlSqlGenerator.generateUpdateSql(tableName, "state=" + User.STAT_SEAL, "id=" + id);
		jdbcTemplate.execute(sql);
	}


	@Override
	public void unsealUser(String id) throws Exception {
		String sql = MysqlSqlGenerator.generateUpdateSql(tableName, "state=" + User.STAT_NORMAL, "id=" + id);
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public List<User> findBy(String id, String name, String start, String end, String type) {
		// 1. impl func
		StringBuilder cond = new StringBuilder();
		cond.append(" 1=1 ");
		// 如果表过大可以使用全文索引进行优化, 或者其他的全文搜索引擎进行优化
		// 这里将date, type放在前面会不会快一点呢??
		if(! Tools.isEmpty(id) ) {
			cond.append(" and " + this.id + " like '%" + id + "%'");
		}
		if(! Tools.isEmpty(name) ) {
			cond.append(" and name like '%" + name + "%'");
		}
		if(! Tools.isEmpty(start) ) {
			cond.append(" and createDate > '" + start + "'");
		}
		if(! Tools.isEmpty(end) ) {
			cond.append(" and createDate < '" + end + "'");
		}
		if(! Tools.isEmpty(type) && (! "-1".equals(type)) ) {
			cond.append(" and type = '" + type + "'");
		}
		
		String sql = MysqlSqlGenerator.generateQuerySql(tableName, "*", cond.toString(), null, null );
		List<User> result = jdbcTemplate.query(sql, rowMapper);
		return result;
	}
	
	// UserRowMapper
	private static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id") );
			user.setName(rs.getString("name") );
			user.set_desc(rs.getString("_desc") );
			user.setState(rs.getInt("state") );
			user.setCreateDate(rs.getString("createDate") );
			user.setLastLoginDate(rs.getString("lastLoginDate") );
			user.setType(rs.getInt("type") );
			return user;
		}
	}
	
}
