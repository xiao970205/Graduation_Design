package com.znck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;

/**
 * 
 * ParkingMapper
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface ParkingMapper {

    /**
     * 通过汽车id获得方法
     * @param carId
     * @return
     */
    ParkingEntity getParkingByCarid(String carId);

    /**
     * 通过性质获得方法，时间排序
     * @param nature
     * @param order
     * @return
     */
    List<ParkingEntity> getParkingsByNature(@Param("nature") String nature,@Param("order") String order);
    
    /**
     * 获得所有方法
     * @return
     */
    List<ParkingEntity> getAll();

    /**
     * 获得一个方法
     * @param id
     * @return
     */
    SpaceEntity getOne(String id);

    /**
     * 插入方法
     * @param parking
     */
    void insert(ParkingEntity parking);

    /**
     * 更新方法
     * @param parking
     */
    void update(ParkingEntity parking);

    /**
     * 删除方法
     * @param id
     */
    void delete(String id);
}
