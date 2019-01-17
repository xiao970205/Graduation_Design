 package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.CarEntity;
import com.znck.mapper.CarMapper;

@Service
 public class CarService {
    
    @Autowired
    private CarMapper carMapper;
    
    public List<CarEntity> getAll(){
        return carMapper.getAll();
    }
    
    public CarEntity getOne(String id){
        return carMapper.getOne(id);
    }
    
    public void update(CarEntity car){
        carMapper.update(car);
    }
    
    public void delete(String id){
        carMapper.delete(id);
    }
}
