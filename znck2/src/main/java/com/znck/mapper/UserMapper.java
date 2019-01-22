 package com.znck.mapper;

import java.util.List;

import com.znck.entity.UserEntity;

public interface UserMapper {

     List<UserEntity> getAll();
     
     UserEntity getOne(String id);
     
     void insert(UserEntity user);
     
     void update(UserEntity user);
     
     void delete(String id);
}
