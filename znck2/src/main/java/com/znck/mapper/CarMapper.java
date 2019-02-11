 package com.znck.mapper;

import java.util.List;

import com.znck.entity.CarEntity;

public interface CarMapper {

     List<CarEntity> getAll();
     
     CarEntity getOne(String id);
     
     void insert(CarEntity car);
     
     void update(CarEntity car);
     
     void delete(String id);    
     
     List<CarEntity> getCarsByUserId(String userId);
}
