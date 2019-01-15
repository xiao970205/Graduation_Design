 package com.znck.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.znck.entity.UserEntity;
import com.znck.mapper.UserMapper;

@RestController
 public class UserController {
     @Autowired
     private UserMapper userMapper;
     
     @RequestMapping("/getUsers")
     public List<UserEntity> getUsers() {
         List<UserEntity> users=userMapper.getAll();
         return users;
     }
     
     @RequestMapping("/getUser")
     public UserEntity getUser(String id) {
         UserEntity user=userMapper.getOne(id);
         return user;
     }
     
     @RequestMapping("/add")
     public void save(UserEntity user) {
         userMapper.insert(user);
     }
     
     @RequestMapping(value="update")
     public void update(UserEntity user) {
         userMapper.update(user);
     }
     
     @RequestMapping(value="/delete/{id}")
     public void delete(@PathVariable("id") String id) {
         userMapper.delete(id);
     }
}
