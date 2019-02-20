 package com.znck.service.serviceimpl;

import java.util.List;

import com.znck.entity.EmailActiveEntity;

public interface EmailActiveService {

     public List<EmailActiveEntity> getAll();

     public EmailActiveEntity getOne(String id);

     public void insert(EmailActiveEntity car);

     public void update(EmailActiveEntity car);

     public void delete(String id);

     public EmailActiveEntity getEmailActiveByUserId(String userId);
}
