 package com.znck.mapper;

import java.util.List;

import com.znck.entity.EmailActiveEntity;

public interface EmailActiveMapper {

      List<EmailActiveEntity> getAll();

      EmailActiveEntity getOne(String id);

      void insert(EmailActiveEntity car);

      void update(EmailActiveEntity car);

      void delete(String id);

      EmailActiveEntity getEmailActiveByUserId(String userId);
}
