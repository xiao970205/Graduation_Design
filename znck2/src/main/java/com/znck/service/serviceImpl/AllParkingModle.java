package com.znck.service.serviceImpl;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;
import com.znck.enums.InitDataListener;
import com.znck.service.ContrastServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AllParkingModle {
    @Autowired
    private ContrastServiceImpl contrastServiceImpl;

    private final static String x = "x";

    private final static String y = "y";

    private final static String z = "z";

    public ParkingEntity getPakringByCarId(String carId){
        return InitDataListener.parkings.stream().filter(parking -> parking.getCarId().equals(carId)).collect(Collectors.toList()).get(0);
    }

    public void replaceParking(ParkingEntity parkingNew){
        List<ParkingEntity> parkings = InitDataListener.parkings;
        ParkingEntity parkingOld = getPakringByCarId(parkingNew.getCarId());
        Collections.replaceAll(parkings,parkingOld,parkingNew);
        InitDataListener.parkings = parkings;
    }

    public SpaceEntity getSpaceById(String spaceId){
        return InitDataListener.spaces.stream().filter(space -> space.getId().equals(spaceId)).collect(Collectors.toList()).get(0);
    }

    public void replaceSpace(SpaceEntity spaceNew){
        List<SpaceEntity> spaces = InitDataListener.spaces;
        SpaceEntity spaceOld = getSpaceById(spaceNew.getId());
        Collections.replaceAll(spaces,spaceOld,spaceNew);
        InitDataListener.spaces = spaces;
    }

    public void addPakringEntity(ParkingEntity parkingEntity){
        List<ParkingEntity> parkings = InitDataListener.parkings;
        parkings.add(parkingEntity);
        InitDataListener.parkings = parkings;
    }

    public SpaceEntity getBufferOrFeture(String spaceNature){
        List<SpaceEntity> spaces = InitDataListener.spaces.stream()
                .filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(spaceNature).getId()))
                .sorted(Comparator.comparing(SpaceEntity::getWeight)).collect(Collectors.toList());
        if (spaces.size() == 0) {
            return null;
        }else {
            return spaces.get(0);
        }
    }

    public Date getAppTime(String vipAppParingTimeByString){
        Random r = new Random();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(vipAppParingTimeByString + " " + (r.nextInt(899) + 100), pos);
    }

    public List<ParkingEntity> getParkingEntitysByNatureAndTime(String nature,String sort){
        List<ParkingEntity> parkings = InitDataListener.parkings.stream()
                .filter(parking -> parking.getNature().equals(contrastServiceImpl.getContrastByRealName(nature).getId()))
                .collect(Collectors.toList());
        if(StringUtils.isNullOrEmpty(sort)){
            return parkings;
        }
        Stream<ParkingEntity> parkingEntityStream = parkings.stream();
        if("getVipSendTimeForSort".equals(sort)){
            return parkingEntityStream.sorted(Comparator.comparing(ParkingEntity::getVipSendTimeForSort)).collect(Collectors.toList());
        }
        if("getVipAppGetTimeForSort".equals(sort)){
            return parkingEntityStream.sorted(Comparator.comparing(ParkingEntity::getVipAppGetTimeForSort)).collect(Collectors.toList());
        }
        if("getInPlaceTimeForSort".equals(sort)){
            return parkingEntityStream.sorted(Comparator.comparing(ParkingEntity::getInPlaceTimeForSort)).collect(Collectors.toList());
        }
        if("getOutTimeForSort".equals(sort)){
            return parkingEntityStream.sorted(Comparator.comparing(ParkingEntity::getOutTimeForSort)).collect(Collectors.toList());
        }
        return null;
    }

    public List<SpaceEntity> getSpaceEntitysByNature(String nature){
        return InitDataListener.spaces.stream()
                .filter(space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(nature).getId()))
                .collect(Collectors.toList());
    }

    public SpaceEntity getSpaceByXYZ(int x,int y,int z){
        return InitDataListener.spaces.stream().filter(space -> space.getX() == x
                && space.getY() == y && space.getZ() == z)
                .collect(Collectors.toList()).get(0);
    }

    public boolean nextSpaceIsFetureSpace(Map<String, Integer> nextSpaceMap,SpaceEntity fetureSpace){
        return nextSpaceMap.get(x) == fetureSpace.getX() && nextSpaceMap.get(y) == fetureSpace.getY()
                && nextSpaceMap.get(z) == fetureSpace.getZ();
    }
}
