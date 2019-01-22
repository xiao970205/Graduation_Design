 package com.znck.mapper;

import java.util.List;

import com.znck.entity.ParkingSaveEntity;

public interface ParkingSaveMapper {
     
     List<ParkingSaveEntity> getAll();

     ParkingSaveEntity getOne(String id);

     void insert(ParkingSaveEntity parkingSave);

     void update(ParkingSaveEntity parkingSave);

     void delete(String id);
}
