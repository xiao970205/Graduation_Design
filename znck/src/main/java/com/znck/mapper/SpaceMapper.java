package com.znck.mapper;

import java.util.List;

import com.znck.entity.SpaceEntity;

public interface SpaceMapper {
    List<SpaceEntity> getAll();

    SpaceEntity getOne(String id);

    void insert(SpaceEntity user);

    void update(SpaceEntity user);

    void delete(String id);
}
