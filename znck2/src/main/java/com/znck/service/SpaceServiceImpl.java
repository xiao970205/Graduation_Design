package com.znck.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.SpaceEntity;
import com.znck.mapper.SpaceMapper;
import com.znck.service.serviceimpl.SpaceService;

/**
 * 
 * SpaceServiceImpl
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class SpaceServiceImpl implements SpaceService {
    @Autowired
    private SpaceMapper spaceMapper;

    @Override
    public SpaceEntity getCrk(String cRk) {
        return spaceMapper.getCrk(cRk);
    }

    @Override
    public SpaceEntity getSpaceByXYZ(int x, int y, int z) {
        return spaceMapper.getSpaceByXYZ(x, y, z);
    }

    @Override
    public List<SpaceEntity> getAll() {
        return spaceMapper.getAll();
    }

    @Override
    public SpaceEntity getOne(String id) {
        return spaceMapper.getOne(id);
    }

    @Override
    public void update(SpaceEntity space) {
        spaceMapper.update(space);
    }

    @Override
    public void delete(String id) {
        spaceMapper.delete(id);
    }

    @Override
    public List<SpaceEntity> getSaveSpace() {
        // TODO Auto-generated method stub
        return spaceMapper.getSaveSpace();
    }

    @Override
    public void insert(SpaceEntity space) {
        // TODO Auto-generated method stub
        spaceMapper.insert(space);
    }
}
