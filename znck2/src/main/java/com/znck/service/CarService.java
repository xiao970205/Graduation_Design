 package com.znck.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.mapper.CarMapper;
import com.znck.mapper.UserMapper;

@Service
 public class CarService {
    
    @Autowired
    private CarMapper carMapper;
    
    @Autowired
    private UserMapper userMapper;
    
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
    
    public List<CarEntity> getCardByUserId(ContrastEntity data){
        return carMapper.getCarsHaveNatureByUserId(data.getId());
    }
    
    public void saveNewCarByPhone(CarEntity carEntity){
        carEntity.setUserId(this.userMapper.getUserByPhone(carEntity.getId()).get(0).getId());
        carEntity.setId(getId());
        carMapper.insert(carEntity);
    }
    
    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
    
    public void updateCarWithoutUserId(CarEntity car){
        car.setId(this.carMapper.getOne(car.getId()).getId());
        this.carMapper.update(car);
    }
}
