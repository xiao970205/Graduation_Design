package com.znck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.UserEntity;

public interface UserMapper {

    UserEntity findByUserNameAndPassword(@Param("phone") String phone,@Param("password") String password);
    
    UserEntity getUserByPhone(String phone);

    List<UserEntity> getAll();

    UserEntity getOne(String id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(String id);
}
