 package com.znck.mapper;

import java.util.List;

import com.znck.entity.ContrastEntity;

public interface ContrastMapper {
    
     List<ContrastEntity> getAll();

     ContrastEntity getOne(String id);

     void insert(ContrastEntity car);

     void update(ContrastEntity car);

     void delete(String id);
     
     ContrastEntity getContrastByRealName(String realName);
}
