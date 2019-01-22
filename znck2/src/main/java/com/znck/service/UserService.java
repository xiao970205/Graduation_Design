package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.UserEntity;
import com.znck.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> getAll() {
        return userMapper.getAll();
    }

    public UserEntity getOne(String id) {
        return userMapper.getOne(id);
    }

    public void update(UserEntity userEntity) {
        userMapper.update(userEntity);
    }

    public void delete(String id) {
        userMapper.delete(id);
    }
}
