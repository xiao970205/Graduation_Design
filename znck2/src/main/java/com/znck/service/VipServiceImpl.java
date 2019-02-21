package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.VipEntity;
import com.znck.mapper.VipMapper;
import com.znck.service.serviceimpl.VipService;

@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private VipMapper vipMapper;

    @Override
    public List<VipEntity> getAll() {
        // TODO Auto-generated method stub
        return vipMapper.getAll();
    }

    @Override
    public VipEntity getOne(String id) {
        // TODO Auto-generated method stub
        return vipMapper.getOne(id);
    }

    @Override
    public void insert(VipEntity vip) {
        // TODO Auto-generated method stub
        vipMapper.insert(vip);
    }

    @Override
    public void update(VipEntity vip) {
        // TODO Auto-generated method stub
        vipMapper.update(vip);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        vipMapper.delete(id);
    }

    @Override
    public VipEntity getVipByUserId(String userId) {
        // TODO Auto-generated method stub
         return vipMapper.getVipByUserId(userId);
    }

    @Override
    public void deleteNowBiggerEndDate() {
        // TODO Auto-generated method stub
         vipMapper.deleteNowBiggerEndDate();
    }
}
