package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.ParkingSaveEntity;

public interface ParkingSaveService {
	
	/**
	 * 获得所有方法
	 *
	 * @return
	 */
	public List<ParkingSaveEntity> getAll();

	/**
	 * 插入方法
	 *
	 * @param parking
	 */
	public void insert(ParkingSaveEntity parking);

	/**
	 * 更新方法
	 *
	 * @param parking
	 */
	public void update(ParkingSaveEntity parking);

	/**
	 * delete方法
	 *
	 * @param id
	 */
	public void delete(String id);
}
