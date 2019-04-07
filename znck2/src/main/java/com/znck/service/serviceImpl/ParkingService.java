package com.znck.service.serviceImpl;

import com.znck.entity.ParkingEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingService {

    /**
     * 通过汽车id查找方法
     *
     * @param carId
     * @return
     */
    public ParkingEntity getParkingByCarid(String carId);

    public ParkingEntity getOne(String id);

    /**
     * 通过性质查找方法
     *
     * @param nature
     * @param order
     * @return
     */
    public List<ParkingEntity> getParkingsByNature(@Param("nature") String nature, @Param("order") String order);

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
