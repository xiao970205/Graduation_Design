 package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.ContrastEntity;

public interface ContrastService {
     public List<ContrastEntity> getAll();

     public ContrastEntity getOne(String id);

     public void insert(ContrastEntity car);

     public void update(ContrastEntity car);

     public void delete(String id);
     
     public ContrastEntity getContrastByRealName(String realName);
}
