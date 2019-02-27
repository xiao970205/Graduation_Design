package com.znck.mapper;

import java.util.List;

import com.znck.entity.VipEntity;

/**
 * vipmapper接口
 * 
 * @author 肖舒翔 2019-02-27
 * @version 1.0
 */
public interface VipMapper {

	/**
	 * 获得所有
	 * 
	 * @return
	 */
	List<VipEntity> getAll();

	/**
	 * 根据主键获得数据
	 * 
	 * @param id
	 * @return
	 */
	VipEntity getOne(String id);

	/**
	 * 插入一条数据
	 * 
	 * @param vip
	 */
	void insert(VipEntity vip);

	/**
	 * 根据主键更新数据
	 * 
	 * @param vip
	 */
	void update(VipEntity vip);

	/**
	 * 根据主键删除数据
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 根据用户主键获得数据
	 * 
	 * @param userId
	 * @return
	 */
	VipEntity getVipByUserId(String userId);

	/**
	 * 删除过期vip
	 */
	void deleteNowBiggerEndDate();

}
