package com.znck.mapper;

import java.util.List;

import com.znck.entity.VipEntity;

public interface VipMapper {

    List<VipEntity> getAll();

    VipEntity getOne(String id);

    void insert(VipEntity vip);

    void update(VipEntity vip);

    void delete(String id);

    VipEntity getVipByUserId(String userId);
    
    void deleteNowBiggerEndDate();
    
}
