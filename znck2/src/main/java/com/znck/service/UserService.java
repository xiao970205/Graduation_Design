package com.znck.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.CarEntity;
import com.znck.entity.UserEntity;
import com.znck.mapper.CarMapper;
import com.znck.mapper.UserMapper;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private CarMapper carMapper;

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
    
    public String regist(String data){
        String phone = data.split("\"phone\":\"")[1].split("\"")[0];
        if(userMapper.getUserByPhone(phone).size()>0){
            //已注册
            return "false";
        }else{
            //未注册
            UserEntity user = new UserEntity();
            user.setId(getId());
            user.setPhone(phone);
            user.setNickName("新用户"+phone);
            user.setNature("0");
            user.setPassword("123456");
            userMapper.insert(user);
            user = userMapper.getUserByPhone(phone).get(0);
            return "true";
        }
    }
    
    public UserEntity landing(String massage){
        String phone = massage.split("\"phone\":\"")[1].split("\"")[0];
        String password = massage.split("\"password\":\"")[1].split("\"")[0];
        return userMapper.findByUserNameAndPassword(phone, password);
    }
    
    public UserEntity getUserByPhone(String data){
        String phone = data.split("\"phone\":\"")[1].split("\"")[0];
        
        return userMapper.getUserByPhone(phone).get(0);
    }
    
    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
