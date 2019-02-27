package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.PhoneActiveEntity;
import com.znck.mapper.PhoneActiveMapper;
import com.znck.service.serviceimpl.PhoneActiveService;

/**
 * 手机激活service
 * 
 * @author 肖舒翔 2019-02-27
 * @version 1.0
 */
@Service
public class PhoneActiveServiceImpl implements PhoneActiveService {

	@Autowired
	private PhoneActiveMapper phoneActiveMapper;

	@Override
	public List<PhoneActiveEntity> getAll() {
		// TODO Auto-generated method stub
		return phoneActiveMapper.getAll();
	}

	@Override
	public PhoneActiveEntity getOne(String id) {
		// TODO Auto-generated method stub
		return phoneActiveMapper.getOne(id);
	}

	@Override
	public void insert(PhoneActiveEntity phone) {
		// TODO Auto-generated method stub
		phoneActiveMapper.insert(phone);
	}

	@Override
	public void update(PhoneActiveEntity phone) {
		// TODO Auto-generated method stub
		phoneActiveMapper.update(phone);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		phoneActiveMapper.delete(id);
	}

	@Override
	public PhoneActiveEntity getPhoneActiveByUserId(String userId) {
		// TODO Auto-generated method stub
		return phoneActiveMapper.getPhoneActiveByUserId(userId);
	}

	@Override
	public List<PhoneActiveEntity> getPhoneActiveByUserPhone(String phone) {
		// TODO Auto-generated method stub
		return phoneActiveMapper.getPhoneActiveByUserPhone(phone);
	}

	@Override
	public void deleteByUserId(String userId) {
		// TODO Auto-generated method stub
		phoneActiveMapper.deleteByUserId(userId);
	}

}
