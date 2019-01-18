package com.znck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.SpaceEntity;
import com.znck.mapper.SpaceMapper;

@Service
public class SpaceService {
    @Autowired
    private SpaceMapper spaceMapper;

    public SpaceEntity getCrk(String cRk){
        return spaceMapper.getCrk(cRk);
    }
    
    public SpaceEntity getSpaceByXYZ(int x,int y,int z){
        return spaceMapper.getSpaceByXYZ(x, y, z);
    }
    
    public SpaceEntity getSaveSpace(){
        return spaceMapper.getSaveSpace().get(0);
    }
    
    public List<SpaceEntity> getAll() {
        return spaceMapper.getAll();
    }

    public SpaceEntity getOne(String id) {
        return spaceMapper.getOne(id);
    }

    public void update(SpaceEntity spaceEntity) {
        spaceMapper.update(spaceEntity);
    }

    public void delete(String id) {
        spaceMapper.delete(id);
    }
}
