package com.znck.service;

import java.util.List;

import com.znck.service.serviceImpl.ParkingSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ParkingSaveEntity;
import com.znck.mapper.ParkingSaveMapper;

@Service
public class ParkingSaveServiceImpl implements ParkingSaveService {
	
	@Autowired
    private ParkingSaveMapper parkingSaveMapper;

    @Override
    public List<ParkingSaveEntity> getAll() {
        return parkingSaveMapper.getAll();
    }

    @Override
    public void insert(ParkingSaveEntity parking) {
    	parkingSaveMapper.insert(parking);
    }

    @Override
    public void update(ParkingSaveEntity parking) {
    	parkingSaveMapper.update(parking);
    }

    @Override
    public void delete(String id) {
    	parkingSaveMapper.delete(id);
    }
}
