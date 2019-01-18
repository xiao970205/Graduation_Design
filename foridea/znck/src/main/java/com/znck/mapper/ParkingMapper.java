package com.znck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;

public interface ParkingMapper {

    ParkingEntity getParkingByCarid(String carId);

    List<ParkingEntity> getParkingsByNature(@Param("nature") String nature,@Param("order") String order);
    
    List<ParkingEntity> getAll();

    SpaceEntity getOne(String id);

    void insert(ParkingEntity parking);

    void update(ParkingEntity parking);

    void delete(String id);
}
