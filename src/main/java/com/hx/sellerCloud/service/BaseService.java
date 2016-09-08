/**
 * file name : BaseService.java
 * created at : 下午5:19:10 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.service;

import java.util.List;

// BaseService
public interface BaseService<E> {

	/**
	 * @Name: findById 
	 * @Description: 根据给定的id查询记录
	 * @param id
	 * @return  
	 * @Create at 2016年9月7日 下午9:21:56 by '970655147'
	 */
	public E findById(String id);
	
	/**
	 * @Name: findAll 
	 * @Description: 查询给定的"表"中的所有的元素
	 * @return  
	 * @Create at 2016年9月7日 下午5:23:33 by '970655147'
	 */
	public List<E> findAll();
	
	/**
	 * @Name: insert 
	 * @Description: 想给定的表中插入一行记录
	 * @param ele
	 * @throws Exception  
	 * @Create at 2016年9月7日 下午7:28:17 by '970655147'
	 */
	public void insert(E ele) throws Exception;
	
	/**
	 * @Name: update 
	 * @Description: 根据ele的id更新对应的记录
	 * @param ele
	 * @throws Exception  
	 * @Create at 2016年9月7日 下午10:17:48 by '970655147'
	 */
	public void update(E ele) throws Exception;
	
	/**
	 * @Name: removeById 
	 * @Description: 根据id移除一行记录
	 * @param id
	 * @throws Exception  
	 * @Create at 2016年9月7日 下午9:39:52 by '970655147'
	 */
	public void removeById(String id) throws Exception;
	
}
