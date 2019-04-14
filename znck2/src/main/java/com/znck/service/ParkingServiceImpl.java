package com.znck.service;

import java.util.List;

import com.znck.service.serviceImpl.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ParkingEntity;
import com.znck.mapper.ParkingMapper;

/**
 * 服务层
 * 
 * @author 肖舒翔
 * 2019-04-09
 * @version 1.0
 */
@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public List<ParkingEntity> getAll() {
        return parkingMapper.getAll();
    }

    @Override
    public void insert(ParkingEntity parking) {
        parkingMapper.insert(parking);
    }

    @Override
    public void update(ParkingEntity parking) {
        parkingMapper.update(parking);
    }

    @Override
    public void delete(String id) {
        parkingMapper.delete(id);
    }

    @Override
    public void save(ParkingEntity parking) {
        parkingMapper.insert(parking);
    }

    @Override
    public void truncateTable() {
        parkingMapper.truncateTable();
    }
}
