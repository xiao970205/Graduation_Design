package com.znck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znck.entity.SpaceEntity;

public interface SpaceMapper {
    
    SpaceEntity getCrk(String cRk);

    SpaceEntity getSpaceByXYZ(@Param("x") int x, @Param("y") int y, @Param("z") int z);

    List<SpaceEntity> getAll();

    List<SpaceEntity> getSaveSpace();

    SpaceEntity getOne(String id);

    void insert(SpaceEntity user);

    void update(SpaceEntity user);

    void delete(String id);
}
