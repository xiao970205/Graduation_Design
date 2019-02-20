package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.PhoneActiveEntity;

public interface PhoneActiveService {
    public List<PhoneActiveEntity> getAll();

    public PhoneActiveEntity getOne(String id);

    public void insert(PhoneActiveEntity car);

    public void update(PhoneActiveEntity car);

    public void delete(String id);

    public PhoneActiveEntity getPhoneActiveByUserId(String userId);
    
    public PhoneActiveEntity getPhoneActiveByUserPhone(String phone);
}
