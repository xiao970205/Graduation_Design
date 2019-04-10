package com.znck.mapper;

import java.util.List;

import com.znck.entity.ParkingEntity;

/**
 * mapper层
 * 
 * @author 肖舒翔
 * 2019-04-09
 * @version 1.0
 */
public interface ParkingMapper {

    /**
     * 获得所有方法
     *
     * @return
     */
    List<ParkingEntity> getAll();

    /**
     * 插入方法
     *
     * @param parking
     */
    void insert(ParkingEntity parking);

    /**
     * 更新方法
     *
     * @param parking
     */
    void update(ParkingEntity parking);

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
