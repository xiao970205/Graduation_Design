 package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.UserEntity;

public interface UserService {
     public UserEntity findByUserNameAndPassword(String phone,String password);
     
     public UserEntity getUserByPhone(String phone);

     public List<UserEntity> getAll();

     public UserEntity getOne(String id);

     public void insert(UserEntity user);

     public void update(UserEntity user);

     public void delete(String id);
}
