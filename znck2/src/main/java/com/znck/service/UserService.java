package com.znck.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.UserEntity;
import com.znck.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserEntity changeUserInfo(UserEntity userEntity) {
        UserEntity userOlder = userMapper.getOne(userEntity.getId());
        int nature = 0;
        if (!StringUtils.isNullOrEmpty(userEntity.getEmail())) {
            userOlder.setEmail(userEntity.getEmail());
            nature++;
        }
        if (!StringUtils.isNullOrEmpty(userEntity.getIdCard())) {
            userOlder.setIdCard(userEntity.getIdCard());
            nature++;
        }
        if (!StringUtils.isNullOrEmpty(userEntity.getNickName())) {
            userOlder.setNickName(userEntity.getNickName());
        }
        if (!StringUtils.isNullOrEmpty(userEntity.getPassword())) {
            userOlder.setPassword(userEntity.getPassword());
        }
        if (!StringUtils.isNullOrEmpty(userEntity.getPhone())) {
            userOlder.setPhone(userEntity.getPhone());
            nature++;
        }
        if (!StringUtils.isNullOrEmpty(userEntity.getRealName())) {
            userOlder.setRealName(userEntity.getRealName());
            nature++;
        }
        if(userOlder.getNature().equals("0") && nature == 4){
            userOlder.setNature("1");
        }
        userMapper.update(userOlder);
        return userOlder;
    }

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

    public String regist(String data) {
        String phone = data.split("\"phone\":\"")[1].split("\"")[0];
        if (userMapper.getUserByPhone(phone).size() > 0) {
            // 已注册
            return "false";
        } else {
            // 未注册
            UserEntity user = new UserEntity();
            user.setId(getId());
            user.setPhone(phone);
            user.setNickName("新用户" + phone);
            user.setNature("0");
            user.setPassword("123456");
            userMapper.insert(user);
            user = userMapper.getUserByPhone(phone).get(0);
            return "true";
        }
    }

    public UserEntity landing(String massage) {
        String phone = massage.split("\"phone\":\"")[1].split("\"")[0];
        String password = massage.split("\"password\":\"")[1].split("\"")[0];
        return userMapper.findByUserNameAndPassword(phone, password);
    }

    public UserEntity getUserByPhone(String data) {
        String phone = data.split("\"phone\":\"")[1].split("\"")[0];

        return userMapper.getUserByPhone(phone).get(0);
    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
