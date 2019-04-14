package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.EmailActiveEntity;

/**
 * 邮箱激活接口
 * 
 * @author 肖舒翔 2019-02-27
 * @version 1.0
 */
public interface EmailActiveService {

	/**
	 * 获得所有数据
	 * 
	 * @return
	 */
	public List<EmailActiveEntity> getAll();

	/**
	 * 根据主键获得数据
	 * 
	 * @param id
	 * @return
	 */
	public EmailActiveEntity getOne(String id);

	/**
	 * 插入一条数据
	 * 
	 * @param car
	 */
	public void insert(EmailActiveEntity car);

	/**
	 * 根据主键更新数据
	 * 
	 * @param car
	 */
	public void update(EmailActiveEntity car);

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
	public EmailActiveEntity getEmailActiveByUserId(String userId);
}
