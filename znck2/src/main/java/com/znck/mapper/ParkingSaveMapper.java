package com.znck.mapper;

import java.util.List;

import com.znck.entity.ParkingSaveEntity;

public interface ParkingSaveMapper {
	
	/**
	 * 获得所有方法
	 *
	 * @return
	 */
	List<ParkingSaveEntity> getAll();

	/**
	 * 插入方法
	 *
	 * @param parking
	 */
	void insert(ParkingSaveEntity parking);

	/**
	 * 更新方法
	 *
	 * @param parking
	 */
	void update(ParkingSaveEntity parking);

	/**
	 * 删除方法
	 *
	 * @param id
	 */
	void delete(String id);
}
