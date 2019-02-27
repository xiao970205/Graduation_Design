package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.ParkingSaveEntity;

/**
 * 
 * ParkingSaveService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface ParkingSaveService {

	/**
	 * 获得所有方法
	 * 
	 * @return
	 */
	public List<ParkingSaveEntity> getAll();

	/**
	 * 获得一个方法
	 * 
	 * @param id
	 * @return
	 */
	public ParkingSaveEntity getOne(String id);

	/**
	 * 插入方法
	 * 
	 * @param parkingSave
	 */
	public void insert(ParkingSaveEntity parkingSave);

	/**
	 * 更新方法
	 * 
	 * @param parkingSave
	 */
	public void update(ParkingSaveEntity parkingSave);

	/**
	 * 删除方法
	 * 
	 * @param id
	 */
	public void delete(String id);
}
