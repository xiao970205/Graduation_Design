package com.znck.service.serviceImpl;

import java.util.List;

import com.znck.entity.SpaceEntity;

public interface SpaceService {
    public SpaceEntity getCrk(String cRk);

    public SpaceEntity getSpaceByXYZ(int x, int y, int z);

    public List<SpaceEntity> getAll();

    public List<SpaceEntity> getSaveSpace();

    public SpaceEntity getOne(String id);

    public void insert(SpaceEntity user);

    public void update(SpaceEntity user);

    public void delete(String id);
}
