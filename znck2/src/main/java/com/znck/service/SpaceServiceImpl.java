package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.SpaceEntity;
import com.znck.mapper.SpaceMapper;
import com.znck.service.serviceImpl.SpaceService;

@Service
public class SpaceServiceImpl implements SpaceService{
    @Autowired
    private SpaceMapper spaceMapper;

    public SpaceEntity getCrk(String cRk){
        return spaceMapper.getCrk(cRk);
    }
    
    public SpaceEntity getSpaceByXYZ(int x,int y,int z){
        return spaceMapper.getSpaceByXYZ(x, y, z);
    }
    
    public List<SpaceEntity> getAll() {
        return spaceMapper.getAll();
    }

    public SpaceEntity getOne(String id) {
        return spaceMapper.getOne(id);
    }

    public void update(SpaceEntity space) {
        spaceMapper.update(space);
    }

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
