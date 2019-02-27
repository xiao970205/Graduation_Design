package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.VipEntity;

/**
 * VIP服务接口
 * 
 * @author 肖舒翔
 * @version 1.0
 */
public interface VipService {

	/**
	 * 获得所有数据
	 * 
	 * @return
	 */
	public List<VipEntity> getAll();

	/**
	 * 根据主键获得数据
	 * 
	 * @param id
	 * @return
	 */
	public VipEntity getOne(String id);

	/**
	 * 插入一条数据
	 * 
	 * @param vip
	 */
	public void insert(VipEntity vip);

	/**
	 * 根据主键更新数据
	 * 
	 * @param vip
	 */
	public void update(VipEntity vip);

	/**
	 * 根据主键删除数据
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 根据用户主键获得数据
	 * 
	 * @param userId
	 * @return
	 */
	public VipEntity getVipByUserId(String userId);

	/**
	 * 删除过期vip
	 */
	public void deleteNowBiggerEndDate();
}
