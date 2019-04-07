package com.znck.mapper;

import com.znck.entity2.ParkingEntity2;

import java.util.List;

public interface ParkingMapper2 {

    /**
     * 获得所有方法
     *
     * @return
     */
    List<ParkingEntity2> getAll();

    /**
     * 插入方法
     *
     * @param parking
     */
    void insert(ParkingEntity2 parking);

    /**
     * 更新方法
     *
     * @param parking
     */
    void update(ParkingEntity2 parking);

    /**
     * 删除方法
     *
     * @param id
     */
    void delete(String id);

    /**
     * 清空整个表
     */
    void truncateTable();
}
