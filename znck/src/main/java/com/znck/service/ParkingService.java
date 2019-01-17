package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;
import com.znck.mapper.ParkingMapper;

@Service
public class ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;

    public ParkingEntity getParkingByCarid(String carId){
        return parkingMapper.getParkingByCarid(carId);
    }

    public List<ParkingEntity> getParkingsByNature(String nature,String order){
        return parkingMapper.getParkingsByNature(nature, order);
    }
    
    public void save(ParkingEntity parking) {
        this.parkingMapper.insert(parking);
    }

    public List<ParkingEntity> getAll(){
        return parkingMapper.getAll();
    }

    public SpaceEntity getOne(String id){
        return parkingMapper.getOne(id);
    }

    public void insert(ParkingEntity parking){
        parkingMapper.insert(parking);
    }

    public void update(ParkingEntity parking){
        parkingMapper.update(parking);
    }

    public void delete(String id){
        parkingMapper.delete(id);
    }
}
