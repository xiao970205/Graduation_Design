package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.CarEntity;

/**
 * 
 * CarService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface CarService {

    /**
     * 获得所有方法
     * @return
     */
    public List<CarEntity> getAll();

    /**
     * 通过id获得汽车实体类
     * @param id
     * @return
     */
    public CarEntity getOne(String id);

    /**
     * 插入一个汽车实体类
     * @param car
     */
    public void insert(CarEntity car);

    /**
     * 通过id更新一个汽车实体类
     * @param car
     */
    public void update(CarEntity car);

    /**
     * 删除方法
     * @param id
     */
    public void delete(String id);

    /**
     * 通过用户id查找名下汽车
     * @param userId
     * @return
     */
    public List<CarEntity> getCarsByUserId(String userId);

    /**
     * 通过用户id查找带停车状态的汽车实体类
     * @param userId
     * @return
     */
    public List<CarEntity> getCarsHaveNatureByUserId(String userId);
}
