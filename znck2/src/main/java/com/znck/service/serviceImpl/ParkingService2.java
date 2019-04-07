package com.znck.service.serviceImpl;

import com.znck.entity2.ParkingEntity2;

import java.util.List;

public interface ParkingService2 {
    /**
     * 获得所有方法
     *
     * @return
     */
    public List<ParkingEntity2> getAll();


    /**
     * 插入方法
     *
     * @param parking
     */
    public void insert(ParkingEntity2 parking);

    /**
     * 更新方法
     *
     * @param parking
     */
    public void update(ParkingEntity2 parking);

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
    public void save(ParkingEntity2 parking);

    /**
     * 清空表
     */
    public void truncateTable();
}
