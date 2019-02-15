 package com.znck.mapper;

import java.util.List;

import com.znck.entity.ContrastEntity;

/**
 * 
 * ContrastMapper
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface ContrastMapper {
    
    /**
     * 获得所有方法
     * @return
     */
     List<ContrastEntity> getAll();

     /**
      * 通过id查找一个对应
      * @param id
      * @return
      */
     ContrastEntity getOne(String id);

     /**
      * 插入一个对应
      * @param car
      */
     void insert(ContrastEntity car);

     /**
      * 更新一个对应
      * @param car
      */
     void update(ContrastEntity car);

     /**
      * 删除一个对应
      * @param id
      */
     void delete(String id);
     
     /**
      * 通过对应名查找对应
      * @param realName
      * @return
      */
     ContrastEntity getContrastByRealName(String realName);
}
