/**
 * file name : UserService.java
 * created at : 下午4:59:50 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.service;

import java.util.List;

import com.hx.sellerCloud.bean.User;

public interface UserService extends BaseService<User> {
    
	/**
	 * @Name: sealUser 
	 * @Description: 封禁id对应的用户
	 * @param id
	 * @throws Exception  
	 * @Create at 2016年9月7日 下午9:10:53 by '970655147'
	 */
	public void sealUser(String id) throws Exception;
	
	/**
	 * @Name: unsealUser 
	 * @Description: 解除封禁id对应的用户
	 * @param id
	 * @throws Exception  
	 * @Create at 2016年9月7日 下午9:11:26 by '970655147'
	 */
	public void unsealUser(String id) throws Exception;

	/**
	 * @Name: findBy 
	 * @Description: 根据用户的条件查询符合条件的结果
	 * @param id
	 * @param name
	 * @param start
	 * @param end
	 * @param type
	 * @return  
	 * @Create at 2016年9月8日 上午10:41:00 by '970655147'
	 */
	public List<User> findBy(String id, String name, String start, String end, String type);
	
}
