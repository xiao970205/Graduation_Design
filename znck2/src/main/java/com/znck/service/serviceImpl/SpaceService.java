package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.SpaceEntity;

/**
 * 
 * SpaceService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface SpaceService {

	/**
	 * 通过真实名称查找方法
	 * 
	 * @param cRk
	 * @return
	 */
	public SpaceEntity getCrk(String cRk);

	/**
	 * 通过坐标查找方法
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public SpaceEntity getSpaceByXYZ(int x, int y, int z);

	/**
	 * 获得所有方法
	 * 
	 * @return
	 */
	public List<SpaceEntity> getAll();

	/**
	 * 获得可以停的地方的方法
	 * 
	 * @return
	 */
	public List<SpaceEntity> getSaveSpace();

	/**
	 * 通过id获得方法
	 * 
	 * @param id
	 * @return
	 */
	public SpaceEntity getOne(String id);

	/**
	 * 插入方法
	 * 
	 * @param user
	 */
	public void insert(SpaceEntity user);

	/**
	 * 更新方法
	 * 
	 * @param user
	 */
	public void update(SpaceEntity user);

	/**
	 * 删除方法
	 * 
	 * @param id
	 */
	public void delete(String id);
}
