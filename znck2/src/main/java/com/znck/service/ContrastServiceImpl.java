package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ContrastEntity;
import com.znck.mapper.ContrastMapper;
import com.znck.service.serviceimpl.ContrastService;

/**
 * 
 * ContrastServiceImpl
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class ContrastServiceImpl implements ContrastService {
    @Autowired
    private ContrastMapper contrastMapper;

    @Override
    public List<ContrastEntity> getAll() {
        return contrastMapper.getAll();
    }

    @Override
    public ContrastEntity getOne(String id) {
        return contrastMapper.getOne(id);
    }

    @Override
    public void update(ContrastEntity contrastEntity) {
        contrastMapper.update(contrastEntity);
    }

    @Override
    public void delete(String id) {
        contrastMapper.delete(id);
    }

    @Override
    public ContrastEntity getContrastByRealName(String realName) {
        return contrastMapper.getContrastByRealName(realName);
    }

    @Override
    public void insert(ContrastEntity car) {
        // TODO Auto-generated method stub
        contrastMapper.insert(car);
    }
}
