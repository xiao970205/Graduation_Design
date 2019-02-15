package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.UserEntity;

/**
 * 
 * UserService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public interface UserService {
    
    /**
     * 登陆方法
     * @param phone
     * @param password
     * @return
     */
    public UserEntity findByUserNameAndPassword(String phone, String password);

    /**
     * 通过电话查找方法
     * @param phone
     * @return
     */
    public UserEntity getUserByPhone(String phone);

    /**
     * 获得所有方法
     * @return
     */
    public List<UserEntity> getAll();

    /**
     * 获得一个方法
     * @param id
     * @return
     */
    public UserEntity getOne(String id);

    /**
     * 插入方法
     * @param user
     */
    public void insert(UserEntity user);
    
    /**
     * 更新方法
     * @param user
     */
    public void update(UserEntity user);
    
    /**
     * 删除方法
     * @param id
     */
    public void delete(String id);
}
