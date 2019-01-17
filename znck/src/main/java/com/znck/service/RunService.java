package com.znck.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ContrastEntity;
import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;

@Service
public class RunService{

    /**
     * id:729352f0e3b74fee91bf15baa7187e58 realName:出口
     */
    private ContrastEntity ck;

    /**
     * id:7bd42e7aad7645e4ad586349691a87e6 realName:车库-占用
     */
    private ContrastEntity ckZy;

    /**
     * id:920005b52e54468ca653be0b593e6025 realName:通道-可通过
     */
    private ContrastEntity tDktg;

    /**
     * id:1e95f2208c7f4dd9b584889bba7e3164 realName:入口
     */
    private ContrastEntity rk;

    /**
     * id:b8dd53a77a1e4c809550dc1dc750f6ce realName:停车中
     */
    private ContrastEntity tCz;

    /**
     * id:9774f488d8054ebab4c9e843ff7e86a4 realName:通道-占用
     */
    private ContrastEntity tdzy;

    /**
     * id:85f8320ee6004d5eb7f21a470fadb366 realName:取车中
     */
    private ContrastEntity qCz;

    /**
     * id:351cc2cdda6547528e20c6444e4a3bbd realName:存车中
     */
    private ContrastEntity cCc;

    @Autowired
    private ContrastService contrastService;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private SpaceService spaceService;
    
    public void setAllContrast() {
        this.setCkZy(contrastService.getContrastByRealName("车库-占用"));
        this.settDktg(contrastService.getContrastByRealName("通道-可通过"));
        this.setRk(contrastService.getContrastByRealName("入口"));
        this.settCz(contrastService.getContrastByRealName("停车中"));
        this.setTdzy(contrastService.getContrastByRealName("通道-占用"));
        this.setqCz(contrastService.getContrastByRealName("取车中"));
        this.setcCc(contrastService.getContrastByRealName("存车中"));
        this.setCk(contrastService.getContrastByRealName("出口"));
    }
    
    /**
     * 存车方法
     * 
     * @param userId 用户id，字符串
     * @param carId 车辆id，字符串
     * @param outTime 取车时间，Date类型
     * @throws ParseException
     */
    public void saveCar(String userId, String carId, Date outTime) throws ParseException {

        this.setAllContrast();
        
        // 获得存车数据
        ParkingEntity parking = new ParkingEntity();

        // 获得入口id
        String inSpaceId = spaceService.getCrk(this.getRk().getRealName()).getId();
        Date inTime = getDate();

        String nature = this.getcCc().getId();

        SpaceEntity fetureSpace = spaceService.getSaveSpace();
        String fetureSpaceId = fetureSpace.getId();

        fetureSpace.setNature(this.getCkZy().getId());
        // 锁定空间
        spaceService.update(fetureSpace);

        parking.setId(getId());
        parking.setUserId(userId);
        parking.setCarId(carId);
        parking.setOrginalSpaceId(inSpaceId);
        parking.setNowSpaceId(inSpaceId);
        parking.setFetureSpaceId(fetureSpaceId);
        parking.setInTime(inTime);
        parking.setOutTime(outTime);
        parking.setNature(nature);

        parkingService.save(parking);
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        Date date = new Date();
        String dateStr = format.format(date);
        Date date2 = format.parse(dateStr);
        return date2;
    }

    /**
     * 定时器每时每刻（1分钟）进行的方法。
     * 
     * @throws ParseException
     */
    public void getCar() throws ParseException {
        this.setAllContrast();
        String saveCarRealNameId = this.getcCc().getId();
        String inCarRealNameId = this.gettCz().getId();
        String takeOutCarRealNameId = this.getqCz().getId();

        List<ParkingEntity> inCar = parkingService.getParkingsByNature(inCarRealNameId, "");
//        this.inCarForTimeToDo(inCar, inCarRealNameId, takeOutCarRealNameId);

        List<ParkingEntity> takeOutCar = parkingService.getParkingsByNature(takeOutCarRealNameId, "order by outTime");
//        this.takeOutCarForTimeToDo(takeOutCar);

        List<ParkingEntity> saveCar = parkingService.getParkingsByNature(saveCarRealNameId, "order by inTime");
        this.takeOutCarForTimeToDo(saveCar);
    }

    /**
     * 每分钟进行的方法进行每个车的获得下一步路径并处理
     * 
     * @param saveCars
     */
    public void takeOutCarForTimeToDo(List<ParkingEntity> saveCars) {
        saveCars.forEach(saveCar -> {
            this.carNextWay(spaceService.getOne(saveCar.getNowSpaceId()), spaceService.getOne(saveCar.getFetureSpaceId()), saveCar);
        });
    }

    /**
     * 每分钟进行的方法进行每个车的获得下一步路径.
     * 
     * @param nowSpace
     * @param fetureSpace
     * @param parking
     */
    public void carNextWay(SpaceEntity nowSpace, SpaceEntity fetureSpace, ParkingEntity parking) {
        final String x = "x";
        final String y = "y";
        final String z = "z";
        String nature = parking.getNature();
        Map<String, Integer> zb = null;
        String saveCarRealNameId = this.getcCc().getId();
        if (saveCarRealNameId.equals(nature)) {
            zb = this.getNextSpaceWhenSavingCar(nowSpace.getX(), nowSpace.getY(), nowSpace.getZ(), fetureSpace.getX(),
                fetureSpace.getY(), fetureSpace.getZ());
            SpaceEntity nextSpace = spaceService.getSpaceByXYZ(zb.get(x), zb.get(y), zb.get(z));
            if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
                if (zb.get(x) == fetureSpace.getX() && zb.get(y) == fetureSpace.getY()
                    && zb.get(z) == fetureSpace.getZ()) {
                    nextSpace.setCarId(parking.getCarId());
                    nextSpace.setNature(this.getCkZy().getId());
                    spaceService.update(nextSpace);
                    nowSpace.setCarId(null);
                    nowSpace.setNature(this.gettDktg().getId());
                    if (!nowSpace.getNature().equals(this.getRk().getId())) {
                        nowSpace.setNature(this.gettDktg().getId());
                    }
                    spaceService.update(nowSpace);
                    parking.setNature(this.gettCz().getId());
                    parking.setNowSpaceId(nextSpace.getId());
                    parkingService.update(parking);
                } else {
                    nextSpace.setCarId(parking.getCarId());
                    nextSpace.setNature(this.getTdzy().getId());
                    spaceService.update(nextSpace);
                    nowSpace.setCarId(null);
                    if (!nowSpace.getNature().equals(this.getRk().getId())) {
                        nowSpace.setNature(this.gettDktg().getId());
                    }
                    spaceService.update(nowSpace);
                    parking.setNowSpaceId(nextSpace.getId());
                    parkingService.update(parking);
                }
            } else {
            }
        } else {
            zb = this.getNextSpaceWhenTakingOutCar(nowSpace.getX(), nowSpace.getY(), nowSpace.getZ(),
                fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
            SpaceEntity nextSpace = spaceService.getSpaceByXYZ(zb.get(x), zb.get(y), zb.get(z));
            if (nextSpace.getCarId() == null) {
                if (zb.get(x) == fetureSpace.getX() && zb.get(y) == fetureSpace.getY()
                    && zb.get(z) == fetureSpace.getZ()) {
                    nowSpace.setCarId(null);
                    nowSpace.setNature(this.gettDktg().getId());
                    spaceService.update(nowSpace);
                    parkingService.delete(parking.getId());;
                } else {
                    nextSpace.setCarId(parking.getCarId());
                    nextSpace.setNature(this.getCkZy().getId());
                    spaceService.update(nextSpace);
                    nowSpace.setCarId(null);
                    nowSpace.setNature(this.gettDktg().getId());
                    spaceService.update(nowSpace);
                    parking.setNature(this.gettCz().getId());
                    parking.setNowSpaceId(nextSpace.getId());
                    parkingService.update(parking);
                }
            } else {
            }
        }
        System.out.println();
    }

    /**
     * 获得map类型映射的下一步xyz
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Map<String, Integer> getNextSpaceMapStringInteger(int x, int y, int z) {
        Map<String, Integer> zb = new HashMap<String, Integer>(3);
        zb.put("x", x);
        zb.put("y", y);
        zb.put("z", z);

        return zb;
    }

    /**
     * 获得取车时候获得下一步路径放法
     * 
     * @param nX
     * @param nY
     * @param nZ
     * @param fX
     * @param fY
     * @param fZ
     * @return
     */
    public Map<String, Integer> getNextSpaceWhenTakingOutCar(int nX, int nY, int nZ, int fX, int fY, int fZ) {
        Map<String, Integer> zb = new HashMap<String, Integer>(3);
        if (nY % 3 != 2) {
            // 在车位上
            if (nY == 1) {
                // 在第一排,后挪,y++
                zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
            } else {
                // 不在第一排,前挪动,y--
                zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
            }
        } else {
            // 不再车位上
            if (nX != 22) {
                // 在横向通道,右挪
                zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
            } else if (nY != fY) {
                // 在纵向通道,只能往前挪动
                zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
            } else {
                // 只能是楼层不同.
                zb = getNextSpaceMapStringInteger(nX, nY, nZ - 1);
            }
        }
        return zb;
    }

    /**
     * 获得存车时候获得下一步路径放法
     * 
     * @param nX
     * @param nY
     * @param nZ
     * @param fX
     * @param fY
     * @param fZ
     * @return
     */
    public Map<String, Integer> getNextSpaceWhenSavingCar(int nX, int nY, int nZ, int fX, int fY, int fZ) {
        Map<String, Integer> zb = new HashMap<String, Integer>(3);
        if (nZ == fZ) {
            // 相同层
            if (nY % 3 == 2) {
                // 在横向通道中
                if (Math.abs(nY - fY) == 1) {
                    // 在合适的横向通道中
                    if (nX != fX) {
                        // 未移动到合适的横向位置,只能往右走
                        zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
                    } else {
                        // 仅差上下移动
                        if (nY - fY > 0) {
                            zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
                        } else {
                            zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
                        }
                    }
                } else {
                    zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
                }
            } else {
                // 在纵向通道,只能往下走,y++
                zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);

            }
        } else {
            // 不同层,只能下,z++
            zb = getNextSpaceMapStringInteger(nX, nY, nZ + 1);
        }
        return zb;
    }

    /**
     * 每时每刻的停车方法。
     * 
     * @param inCar
     * @param inCarRealNameId
     * @param takeOutCarRealNameId
     * @throws ParseException
     */
    public void inCarForTimeToDo(List<ParkingEntity> inCar, String inCarRealNameId, String takeOutCarRealNameId)
        throws ParseException {

        Date date = getDate();
        inCar.forEach(parks -> {
            if (parks.getOutTime() != null && date.compareTo(parks.getOutTime()) == 1) {
                ParkingEntity inCarToOutCar = parks;
                try {
                    takeOutCar(inCarToOutCar.getCarId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 分两种情况 正常取车 用户强制取车
     * 
     * @param carId
     * @return
     * @throws ParseException
     */
    public ParkingEntity takeOutCar(String carId) throws ParseException {
        this.setAllContrast();
        
        ParkingEntity parking = parkingService.getParkingByCarid(carId);

        String fetureSpaceId = spaceService.getCrk(this.getCk().getRealName()).getId();

        Date outTime = parking.getOutTime();
        if (outTime == null) {
            outTime = getDate();
        }
        parking.setOutTime(outTime);
        parking.setFetureSpaceId(fetureSpaceId);
        parking.setOrginalSpaceId(parking.getNowSpaceId());

        parking.setNature(this.getqCz().getId());

        parkingService.update(parking);

        return parking;
    }
    
    /**
     * 获得自生成id
     * 
     * @return
     */
    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public ContrastEntity getCkZy() {
        return ckZy;
    }

    public void setCkZy(ContrastEntity ckZy) {
        this.ckZy = ckZy;
    }

    public ContrastEntity gettDktg() {
        return tDktg;
    }

    public void settDktg(ContrastEntity tDktg) {
        this.tDktg = tDktg;
    }

    public ContrastEntity getRk() {
        return rk;
    }

    public void setRk(ContrastEntity rk) {
        this.rk = rk;
    }

    public ContrastEntity gettCz() {
        return tCz;
    }

    public void settCz(ContrastEntity tCz) {
        this.tCz = tCz;
    }

    public ContrastEntity getTdzy() {
        return tdzy;
    }

    public void setTdzy(ContrastEntity tdzy) {
        this.tdzy = tdzy;
    }

    public ContrastEntity getqCz() {
        return qCz;
    }

    public void setqCz(ContrastEntity qCz) {
        this.qCz = qCz;
    }

    public ContrastEntity getcCc() {
        return cCc;
    }

    public void setcCc(ContrastEntity cCc) {
        this.cCc = cCc;
    }

    public ContrastEntity getCk() {
        return ck;
    }

    public void setCk(ContrastEntity ck) {
        this.ck = ck;
    }

}
