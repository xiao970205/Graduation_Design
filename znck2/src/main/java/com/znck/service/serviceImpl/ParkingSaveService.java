 package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.ParkingSaveEntity;

public interface ParkingSaveService {
     public List<ParkingSaveEntity> getAll();

     public ParkingSaveEntity getOne(String id);

     public void insert(ParkingSaveEntity parkingSave);

     public void update(ParkingSaveEntity parkingSave);

     public void delete(String id);
}
