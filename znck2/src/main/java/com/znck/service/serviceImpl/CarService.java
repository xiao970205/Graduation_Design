package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.CarEntity;

public interface CarService {
    
    public List<CarEntity> getAll();

    public CarEntity getOne(String id);

    public void insert(CarEntity car);

    public void update(CarEntity car);

    public void delete(String id);

    public List<CarEntity> getCarsByUserId(String userId);

    public List<CarEntity> getCarsHaveNatureByUserId(String userId);
}
