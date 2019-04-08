package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity2.ParkingEntity2;
import com.znck.mapper.ParkingMapper2;
import com.znck.service.serviceimpl.ParkingService2;

@Service
public class ParkingServiceImpl2 implements ParkingService2 {

    @Autowired
    private ParkingMapper2 parkingMapper;

    @Override
    public List<ParkingEntity2> getAll() {
        return parkingMapper.getAll();
    }

    @Override
    public void insert(ParkingEntity2 parking) {
        parkingMapper.insert(parking);
    }

    @Override
    public void update(ParkingEntity2 parking) {
        parkingMapper.update(parking);
    }

    @Override
    public void delete(String id) {
        parkingMapper.delete(id);
    }

    @Override
    public void save(ParkingEntity2 parking) {
        parkingMapper.insert(parking);
    }

    @Override
    public void truncateTable() {
        parkingMapper.truncateTable();
    }
}
