package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.VipEntity;

public interface VipService {
    public List<VipEntity> getAll();

    public VipEntity getOne(String id);

    public void insert(VipEntity vip);

    public void update(VipEntity vip);

    public void delete(String id);
    
    public VipEntity getVipByUserId(String userId);
    
    public void deleteNowBiggerEndDate();
}
