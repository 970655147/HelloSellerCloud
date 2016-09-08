/**
 * file name : MsyqlSqlGenerator.java
 * created at : 8:58:47 PM May 23, 2016
 * created by 970655147
 */

package com.hx.sellerCloud.util;

import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class MysqlSqlGenerator {

	// disable constructor
	private MysqlSqlGenerator() {
		Tools.assert0("can't instantiate !");
	}
	
	// 插入sql的模板
	public static final String insertSqlTemplate = " insert into %s %s values %s ";
	public static final String querySqlTemplate = " select %s from %s ";
	public static final String updateSqlTemplate = " update %s set %s ";
	public static final String deleteSqlTemplate = " delete from %s ";
	
	// for decoupe 			--2016.05.25
	public static final String whereCond = " where %s ";
	public static final String limitCond = " limit %s ";
	public static final String sortByCond = " order by %s ";
	public static final String groupByCond = " group by %s ";
	public static final String skip0Limit1 = "0, 1";
	public static final String limit1 = String.format(limitCond, skip0Limit1);

	public static final String sep = ", ";
	public static final String quote = "'";
	
	// 生成插入数据的sql
	public static String generateInsertSql(String table, JSONObject beanObj) {
		return generateInsertSql(table, Arrays.asList(beanObj) );
	}
	public static String generateInsertSql(String table, List<JSONObject> beanObjs) {
		StringBuilder colNames = new StringBuilder();
		
		// (name, pwd, email)
		JSONArray names = beanObjs.get(0).names();
		colNames.append("(");
		for(Object nameObj : names) {
			colNames.append((String) nameObj );
			colNames.append(sep);
		}
		Tools.removeLastSep(colNames, sep);
		colNames.append(")");
		
		// ('hx', 'hxPwd', 'email')
		// 全部以字符串的形式插入
			// 这样就不能使用insert into (col01, col02) cols values (2, col01 * 3)了,,
		StringBuilder values = new StringBuilder();
		for(JSONObject beanObj : beanObjs) {
			values.append("(");
			for(Object nameObj : names) {
				values.append(quote );
				values.append(beanObj.getString((String) nameObj) );
				values.append(quote);
				values.append(sep);
			}
			Tools.removeLastSep(values, sep);
			values.append(")");
			values.append(sep);
		}
		Tools.removeLastSep(values, sep);
		
		return String.format(insertSqlTemplate, table, colNames.toString(), values);
	}
	
	// 生成查询数据的sql
	public static String generateQuerySql(String table, String projection, String cond, String limit, String sort) {
		StringBuilder querySqlTeplate = new StringBuilder(querySqlTemplate);
		List<String> args = Tools.asList(projection, table);
		if(! Tools.isEmpty(cond) ) {
			querySqlTeplate.append(whereCond);
			args.add(cond);
		}
		if(! Tools.isEmpty(limit) ) {
			querySqlTeplate.append(limitCond);
			args.add(limit);
		}
		if(! Tools.isEmpty(sort) ) {
			querySqlTeplate.append(sortByCond);
			args.add(sort);
		}
		
		return String.format(querySqlTeplate.toString(), args.toArray() );
	}
	// 		reduce					  key		 cond
	// select * from person group by name where age > 20;
	public static String generateGroupBySql(String table, String projection, String cond, String groupBy) {
		StringBuilder querySqlTeplate = new StringBuilder(querySqlTemplate);
		List<String> args = Tools.asList(projection, table);
		if(! Tools.isEmpty(groupBy) ) {
			querySqlTeplate.append(groupByCond);
			args.add(groupBy);
		}
		if(! Tools.isEmpty(cond) ) {
			querySqlTeplate.append(whereCond);
			args.add(cond);
		}
		
		return String.format(querySqlTeplate.toString(), args.toArray() );
	}
	public static String generateQueryOneSql(String table, String projection, String cond) {
		return generateQuerySql(table, projection, cond, skip0Limit1, null);
	}
	
	// 生成更新数据的sql
	public static String generateUpdateSql(String table, String update, String cond) {
		if(Tools.isEmpty(cond) ) {
			return String.format(updateSqlTemplate, table, update);
		} else {
			return String.format(updateSqlTemplate + whereCond, table, update, cond);
		}
	}
	public static String generateUpdateOneSql(String table, String update, String cond) {
		if(Tools.isEmpty(cond) ) {
			return String.format(updateSqlTemplate + limit1, table, update);
		} else {
			return String.format(updateSqlTemplate + whereCond + limit1, table, update, cond);
		}
	}
	
	// 生成删除数据的sql
	public static String generateDeleteSql(String table, String cond) {
		if(Tools.isEmpty(cond) ) {
			return String.format(deleteSqlTemplate, table);
		} else {
			return String.format(deleteSqlTemplate + whereCond, table, cond);
		}
	}
	public static String generateDeleteOneSql(String table, String cond) {
		if(Tools.isEmpty(cond) ) {
			return String.format(deleteSqlTemplate + limit1, table);
		} else {
			return String.format(deleteSqlTemplate + whereCond + limit1, table, cond);
		}
	}
	

}
