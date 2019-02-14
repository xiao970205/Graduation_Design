package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ContrastEntity;
import com.znck.mapper.ContrastMapper;
import com.znck.service.serviceImpl.ContrastService;

@Service
public class ContrastServiceImpl implements ContrastService{
    @Autowired
    private ContrastMapper contrastMapper;

    public List<ContrastEntity> getAll() {
        return contrastMapper.getAll();
    }

    public ContrastEntity getOne(String id) {
        return contrastMapper.getOne(id);
    }

    public void update(ContrastEntity contrastEntity) {
        contrastMapper.update(contrastEntity);
    }

    public void delete(String id) {
        contrastMapper.delete(id);
    }
    
    public ContrastEntity getContrastByRealName(String realName){
        return contrastMapper.getContrastByRealName(realName);
    }

    @Override
    public void insert(ContrastEntity car) {
        // TODO Auto-generated method stub
         contrastMapper.insert(car);
    }
}
