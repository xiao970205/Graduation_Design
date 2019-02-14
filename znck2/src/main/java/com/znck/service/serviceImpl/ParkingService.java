 package com.znck.service.serviceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;

public interface ParkingService {
    
     public ParkingEntity getParkingByCarid(String carId);

     public List<ParkingEntity> getParkingsByNature(@Param("nature") String nature,@Param("order") String order);
     
     public List<ParkingEntity> getAll();

     public SpaceEntity getOne(String id);

     public void insert(ParkingEntity parking);

     public void update(ParkingEntity parking);

     public void delete(String id);
}
