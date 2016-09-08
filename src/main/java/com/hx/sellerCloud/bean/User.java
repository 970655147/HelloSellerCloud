/**
 * file name : User.java
 * created at : 下午4:55:05 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.bean;

// 一个用户[账户]
public class User {

	// 相关常量
	public static final int STAT_NORMAL = 0;
	public static final int STAT_SEAL = STAT_NORMAL + 1;
	
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_VIP = TYPE_NORMAL + 1;
	
	// 相关属性
	private Integer id;
	private String name;
	private String _desc;
	// 未封禁 / 封禁
	private int state;			// 0 / 1
	private String createDate;
	private String lastLoginDate;
	// 账户类型
	private int type;
	
	// 初始化
	public User() {
		super();
	}
	public User(String name, String _desc, int state, String createDate, String lastLoginDate, int type) {
		this.name = name;
		this._desc = _desc;
		this.state = state;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
		this.type = type;
	}
	
	// setter & getter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_desc() {
		return _desc;
	}
	public void set_desc(String _desc) {
		this._desc = _desc;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
