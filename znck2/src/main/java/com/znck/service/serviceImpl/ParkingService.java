package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.ParkingEntity;

/**
 * 服务层
 * 
 * @author 肖舒翔 2019-04-09
 * @version 1.0
 */
public interface ParkingService {
	/**
	 * 获得所有方法
	 *
	 * @return
	 */
	public List<ParkingEntity> getAll();

	/**
	 * 插入方法
	 *
	 * @param parking
	 */
	public void insert(ParkingEntity parking);

	/**
	 * 更新方法
	 *
	 * @param parking
	 */
	public void update(ParkingEntity parking);

	/**
	 * delete方法
	 *
	 * @param id
	 */
	public void delete(String id);

	/**
	 * save方法
	 *
	 * @param parking
	 */
	public void save(ParkingEntity parking);

	/**
	 * 清空表
	 */
	public void truncateTable();
}
