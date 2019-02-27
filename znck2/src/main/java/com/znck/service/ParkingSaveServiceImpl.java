package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ParkingSaveEntity;
import com.znck.mapper.ParkingSaveMapper;
import com.znck.service.serviceimpl.ParkingSaveService;

/**
 * 
 * ParkingSaveServiceImpl
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class ParkingSaveServiceImpl implements ParkingSaveService {

	@Autowired
	private ParkingSaveMapper parkingSaveMapper;

	@Override
	public List<ParkingSaveEntity> getAll() {
		return parkingSaveMapper.getAll();
	}

	@Override
	public ParkingSaveEntity getOne(String id) {
		return parkingSaveMapper.getOne(id);
	}

	@Override
	public void insert(ParkingSaveEntity parkingSave) {
		parkingSaveMapper.insert(parkingSave);
	}

	@Override
	public void update(ParkingSaveEntity parkingSave) {
		parkingSaveMapper.update(parkingSave);
	}

	@Override
	public void delete(String id) {
		parkingSaveMapper.delete(id);
	}
}
