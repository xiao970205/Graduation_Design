package com.znck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.UserEntity;

/**
 * 
 * UserMapper
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface UserMapper {

    /**
     * 登陆方法
     * @param phone
     * @param password
     * @return
     */
    UserEntity findByUserNameAndPassword(@Param("phone") String phone, @Param("password") String password);

    /**
     * 通过用户手机号码获得方法
     * @param phone
     * @return
     */
    UserEntity getUserByPhone(String phone);

    /**
     * 获得所有方法
     * @return
     */
    List<UserEntity> getAll();

    /**
     * 通过id获得方法
     * @param id
     * @return
     */
    UserEntity getOne(String id);

    /**
     * 插入方法
     * @param user
     */
    void insert(UserEntity user);
    
    /**
     * 更新方法
     * @param user
     */
    void update(UserEntity user);
    
    /**
     * 删除方法
     * @param id
     */
    void delete(String id);
    
    List<UserEntity> getUserByEmail(String email);
}
