package com.znck.mapper;

import java.util.List;

import com.znck.entity.PhoneActiveEntity;

/**
 * 手机激活mapper接口
 * 
 * @author 肖舒翔 2019-02-27
 * @version 1.0
 */
public interface PhoneActiveMapper {

	/**
	 * 获得所有
	 * 
	 * @return
	 */
	List<PhoneActiveEntity> getAll();

	/**
	 * 根据主键获得数据
	 * 
	 * @param id
	 * @return
	 */
	PhoneActiveEntity getOne(String id);

	/**
	 * 插入一条新数据
	 * 
	 * @param phone
	 */
	void insert(PhoneActiveEntity phone);

	/**
	 * 根据主键更新数据
	 * 
	 * @param phone
	 */
	void update(PhoneActiveEntity phone);

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
	PhoneActiveEntity getPhoneActiveByUserId(String userId);

	/**
	 * 根据用户主键获得多条数据
	 * 
	 * @param phone
	 * @return
	 */
	List<PhoneActiveEntity> getPhoneActiveByUserPhone(String phone);

	/**
	 * 根据用户主键删除数据
	 * 
	 * @param userId
	 */
	void deleteByUserId(String userId);
}
