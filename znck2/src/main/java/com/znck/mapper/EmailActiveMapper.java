package com.znck.mapper;

import java.util.List;

import com.znck.entity.EmailActiveEntity;

/**
 * 激活邮件的mapper接口 EmailActiveMapper
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface EmailActiveMapper {

	/**
	 * 获得所有方法
	 * 
	 * @return
	 */
	List<EmailActiveEntity> getAll();

	/**
	 * 根据id获得方法
	 * 
	 * @param id
	 * @return
	 */
	EmailActiveEntity getOne(String id);

	/**
	 * 插入一条新数据
	 * 
	 * @param car
	 */
	void insert(EmailActiveEntity car);

	/**
	 * 更新方法
	 * 
	 * @param car
	 */
	void update(EmailActiveEntity car);

	/**
	 * 根据id删除方法
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 根据用户id获得数据
	 * 
	 * @param userId
	 * @return
	 */
	EmailActiveEntity getEmailActiveByUserId(String userId);
}
