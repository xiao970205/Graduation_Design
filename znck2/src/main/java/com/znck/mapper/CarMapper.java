 package com.znck.mapper;

import java.util.List;

import com.znck.entity.CarEntity;

/**
 * 
 * CarMapper
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface CarMapper {

    /**
     * 获得所有的汽车实体类
     * @return
     */
     List<CarEntity> getAll();
     
     /**
      * 获得一个汽车实体类
      * @param id
      * @return
      */
     CarEntity getOne(String id);
     
     /**
      * 插入汽车实体类方法
      * @param car
      */
     void insert(CarEntity car);
     
     /**
      * 更新汽车实体类方法
      * @param car
      */
     void update(CarEntity car);
     
     /**
      * 删除汽车实体类方法
      * @param id
      */
     void delete(String id);    
     
     /**
      * 通过用户的id查找用户名下的汽车实体类
      * @param userId
      * @return
      */
     List<CarEntity> getCarsByUserId(String userId);
     
     /**
      * 通过用户id找到含有汽车停车状态的汽车实体类
      * @param userId
      * @return
      */
     List<CarEntity> getCarsHaveNatureByUserId(String userId);
}
