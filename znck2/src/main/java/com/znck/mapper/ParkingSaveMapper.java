 package com.znck.mapper;

import java.util.List;

import com.znck.entity.ParkingSaveEntity;
/**
 * 
 * ParkingSaveMapper
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface ParkingSaveMapper {
     
    /**
     * 获得所有方法
     * @return
     */
     List<ParkingSaveEntity> getAll();

     /**
      * 获得一个方法
      * @param id
      * @return
      */
     ParkingSaveEntity getOne(String id);

     /**
      * 插入方法
      * @param parkingSave
      */
     void insert(ParkingSaveEntity parkingSave);

     /**
      * 更新方法
      * @param parkingSave
      */
     void update(ParkingSaveEntity parkingSave);

     /**
      * 删除方法
      * @param id
      */
     void delete(String id);
}
