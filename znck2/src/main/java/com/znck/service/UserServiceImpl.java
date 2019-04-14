package com.znck.service;

import java.util.List;

import com.znck.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.UserEntity;
import com.znck.mapper.UserMapper;

/**
 * 
 * UserServiceImpl
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserEntity findByUserNameAndPassword(String phone, String password) {
		// TODO Auto-generated method stub
		return userMapper.findByUserNameAndPassword(phone, password);
	}

	@Override
	public UserEntity getUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return userMapper.getUserByPhone(phone);
	}

	@Override
	public List<UserEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity getOne(String id) {
		// TODO Auto-generated method stub
		return userMapper.getOne(id);
	}

	@Override
	public void insert(UserEntity user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

	@Override
	public void update(UserEntity user) {
		// TODO Auto-generated method stub
		userMapper.update(user);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		userMapper.delete(id);
	}

	@Override
	public List<UserEntity> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userMapper.getUserByEmail(email);
	}
}
