package com.znck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.SpaceEntity;

/**
 * 
 * SpaceMapper
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface SpaceMapper {

	/**
	 * 通过真实名称查找方法
	 * 
	 * @param cRk
	 * @return
	 */
	SpaceEntity getCrk(String cRk);

	/**
	 * 通过坐标查找方法
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	SpaceEntity getSpaceByXYZ(@Param("x") int x, @Param("y") int y, @Param("z") int z);

	/**
	 * 获得所有方法
	 * 
	 * @return
	 */
	List<SpaceEntity> getAll();

	/**
	 * 获得所有可以停的方法
	 * 
	 * @return
	 */
	List<SpaceEntity> getSaveSpace();

	/**
	 * 通过id查找方法
	 * 
	 * @param id
	 * @return
	 */
	SpaceEntity getOne(String id);

	/**
	 * 插入方法
	 * 
	 * @param user
	 */
	void insert(SpaceEntity user);

	/**
	 * 更新方法
	 * 
	 * @param user
	 */
	void update(SpaceEntity user);

	/**
	 * 删除方法
	 * 
	 * @param id
	 */
	void delete(String id);
}
