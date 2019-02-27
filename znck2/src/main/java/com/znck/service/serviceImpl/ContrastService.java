package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.ContrastEntity;

/**
 * 
 * ContrastService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface ContrastService {

	/**
	 * 获得所有方法
	 * 
	 * @return
	 */
	public List<ContrastEntity> getAll();

	/**
	 * 获得一个方法
	 * 
	 * @param id
	 * @return
	 */
	public ContrastEntity getOne(String id);

	/**
	 * 插入方法
	 * 
	 * @param car
	 */
	public void insert(ContrastEntity car);

	/**
	 * 更新方法
	 * 
	 * @param car
	 */
	public void update(ContrastEntity car);

	/**
	 * 删除方法
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 通过真实名称获得
	 * 
	 * @param realName
	 * @return
	 */
	public ContrastEntity getContrastByRealName(String realName);
}
