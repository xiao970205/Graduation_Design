package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.PhoneActiveEntity;

/**
 * 手机激活service接口
 * 
 * @author 肖舒翔 2019-02-27
 * @version 1.0
 */
public interface PhoneActiveService {

	/**
	 * 获得所有数据
	 * 
	 * @return
	 */
	public List<PhoneActiveEntity> getAll();

	/**
	 * 根据主键获得数据
	 * 
	 * @param id
	 * @return
	 */
	public PhoneActiveEntity getOne(String id);

	/**
	 * 插入一条数据
	 * 
	 * @param car
	 */
	public void insert(PhoneActiveEntity car);

	/**
	 * 根据主键更新数据
	 * 
	 * @param car
	 */
	public void update(PhoneActiveEntity car);

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
	public PhoneActiveEntity getPhoneActiveByUserId(String userId);

	/**
	 * 根据用户主键获得多条数据
	 * 
	 * @param phone
	 * @return
	 */
	public List<PhoneActiveEntity> getPhoneActiveByUserPhone(String phone);

	/**
	 * 根据用户主键删除数据
	 * 
	 * @param userId
	 */
	public void deleteByUserId(String userId);
}
