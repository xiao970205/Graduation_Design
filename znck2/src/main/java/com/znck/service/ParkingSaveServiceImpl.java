package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ParkingSaveEntity;
import com.znck.mapper.ParkingSaveMapper;
import com.znck.service.serviceImpl.ParkingSaveService;

@Service
public class ParkingSaveServiceImpl implements ParkingSaveService {
    
    @Autowired
    private ParkingSaveMapper parkingSaveMapper;

    public List<ParkingSaveEntity> getAll() {
        return parkingSaveMapper.getAll();
    }

    public ParkingSaveEntity getOne(String id) {
        return parkingSaveMapper.getOne(id);
    }

    public void insert(ParkingSaveEntity parkingSave) {
        parkingSaveMapper.insert(parkingSave);
    }

    public void update(ParkingSaveEntity parkingSave) {
        parkingSaveMapper.update(parkingSave);
    }

    public void delete(String id) {
        parkingSaveMapper.delete(id);
    }
}
