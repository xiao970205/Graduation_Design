package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.CarEntity;
import com.znck.mapper.CarMapper;
import com.znck.service.serviceimpl.CarService;

/**
 * 
 * CarServiceImpl
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarMapper carMapper;

	@Override
	public void insert(CarEntity car) {
		// TODO Auto-generated method stub
		carMapper.insert(car);
	}

	@Override
	public List<CarEntity> getCarsByUserId(String userId) {
		// TODO Auto-generated method stub
		return carMapper.getCarsByUserId(userId);
	}

	@Override
	public List<CarEntity> getCarsHaveNatureByUserId(String userId) {
		// TODO Auto-generated method stub
		return carMapper.getCarsHaveNatureByUserId(userId);
	}

	@Override
	public List<CarEntity> getAll() {
		// TODO Auto-generated method stub
		return carMapper.getAll();
	}

	@Override
	public CarEntity getOne(String id) {
		// TODO Auto-generated method stub
		return carMapper.getOne(id);
	}

	@Override
	public void update(CarEntity car) {
		// TODO Auto-generated method stub
		carMapper.update(car);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		carMapper.delete(id);
	}
}
