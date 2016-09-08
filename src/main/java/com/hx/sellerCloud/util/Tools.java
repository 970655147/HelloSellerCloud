/**
 * file name : Tools.java
 * created at : 下午8:39:14 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Tools {
	
	// disable constructor
	private Tools() {
		Tools.assert0("can't instantiate !");
	}
	
	// ------------ assert相关 ------- 2016.03.22 -------------
	// 工具方法
	// 确保boo为true, 否则 抛出异常
	public static void assert0(String msg) {
		assert0(false, msg);
	}
	public static void assert0(boolean boo, String msg) {
		if(msg == null) {
			System.err.println("'msg' can't be null ");
			return ;
		}
		if(! boo) {
			throw new RuntimeException("assert0Exception : " + msg);
		}
	}
	// add at 2016.05.02
	public static void assert0(Exception e) {
		assert0(false, e);
	}
	public static void assert0(boolean boo, Exception e) {
		Tools.assert0(e != null, "'e' can't be null ");
		if(! boo) {
			throw new RuntimeException(e);
		}
	}
	
	// 移除掉sb的添加的最后一个分隔符
	public static void removeLastSep(StringBuilder sb, String lastSep) {
		if(sb.length() > lastSep.length() ) {
			sb.delete(sb.length()-lastSep.length(), sb.length() );
		}
	}
	
	// 判断str是否为空字符串
	public static boolean isEmpty(String str) {
		return (str == null) || "".equals(str.trim());
	}
	
	// 根据给定的元素, 创建一个List 
    public static <T> List<T> asList(T... eles) {
    	List<T> res = new ArrayList<>(eles.length);
	    for(T ele : eles) {
	    	res.add(ele);
	    }
	    return res;
    }
    
    // 根据给定的paramMap封装"查询字符串"
	public static String encapQueryString0(Map<String, Object> params, String KVSep, String paramsSep) {
		Tools.assert0(params != null, "'params' can't be null ");
		Tools.assert0(KVSep != null, "'KVSep' can't be null ");
		Tools.assert0(paramsSep != null, "'paramsSep' can't be null ");
		
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Object> entry : params.entrySet() ) {
			sb.append(entry.getKey() );		sb.append(KVSep);
			sb.append(entry.getValue());	sb.append(paramsSep);
		}
		removeLastSep(sb, paramsSep);
		
		return sb.toString();
	}
	
	// dateFormat
	private static DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	
	// 获取当前的时间[格式化]
	public static String now() {
		return dateFormat.format(new Date() );
	}
	// 解析给定的"时间字符串"
	public static Date parse(String date) throws ParseException {
		return dateFormat.parse(date);
	}
	
}

