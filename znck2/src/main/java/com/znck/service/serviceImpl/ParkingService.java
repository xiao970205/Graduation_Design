package com.znck.service.serviceimpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;

/**
 * 
 * ParkingService
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface ParkingService {

    /**
     * 通过汽车id查找方法
     * @param carId
     * @return
     */
    public ParkingEntity getParkingByCarid(String carId);

    /**
     * 通过性质查找方法
     * @param nature
     * @param order
     * @return
     */
    public List<ParkingEntity> getParkingsByNature(@Param("nature") String nature, @Param("order") String order);

    /**
     * 获得所有方法
     * @return
     */
    public List<ParkingEntity> getAll();

    /**
     * 通过id查找方法
     * @param id
     * @return
     */
    public SpaceEntity getOne(String id);

    /**
     * 插入方法
     * @param parking
     */
    public void insert(ParkingEntity parking);

    /**
     * 更新方法
     * @param parking
     */
    public void update(ParkingEntity parking);

    /**
     * delete方法
     * @param id
     */
    public void delete(String id);

    /**
     * save方法
     * @param parking
     */
    public void save(ParkingEntity parking);
}
