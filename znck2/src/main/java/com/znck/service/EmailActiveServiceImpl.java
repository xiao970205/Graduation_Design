 package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.EmailActiveEntity;
import com.znck.mapper.EmailActiveMapper;
import com.znck.service.serviceimpl.EmailActiveService;

@Service
 public class EmailActiveServiceImpl implements EmailActiveService{

    @Autowired
    private EmailActiveMapper emailActiveMapper;

    @Override
    public List<EmailActiveEntity> getAll() {
        // TODO Auto-generated method stub
         return emailActiveMapper.getAll();
    }

    @Override
    public EmailActiveEntity getOne(String id) {
        // TODO Auto-generated method stub
         return emailActiveMapper.getOne(id);
    }

    @Override
    public void insert(EmailActiveEntity car) {
        // TODO Auto-generated method stub
        emailActiveMapper.insert(car);
    }

    @Override
    public void update(EmailActiveEntity car) {
        // TODO Auto-generated method stub
        emailActiveMapper.update(car);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        emailActiveMapper.delete(id);
    }

    @Override
    public EmailActiveEntity getEmailActiveByUserId(String userId) {
        // TODO Auto-generated method stub
         return emailActiveMapper.getEmailActiveByUserId(userId);
    }
}
