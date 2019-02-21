 package com.znck.mapper;

import java.util.List;

import com.znck.entity.PhoneActiveEntity;

public interface PhoneActiveMapper {
     List<PhoneActiveEntity> getAll();

     PhoneActiveEntity getOne(String id);

     void insert(PhoneActiveEntity phone);

     void update(PhoneActiveEntity phone);

     void delete(String id);

     PhoneActiveEntity getPhoneActiveByUserId(String userId);
     
     List<PhoneActiveEntity> getPhoneActiveByUserPhone(String phone);
     
     void deleteByUserId(String userId);
}
