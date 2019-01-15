package com.hibernate.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mysql.cj.util.StringUtils;

/**
 * 
 * Run
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public class Run {

    //车库-占用 7bd42e7aad7645e4ad586349691a87e6
    private Contrast ckZy;
    //920005b52e54468ca653be0b593e6025通道-可通过
    private Contrast tDktg;
    //1e95f2208c7f4dd9b584889bba7e3164入口
    private Contrast rk;
    //b8dd53a77a1e4c809550dc1dc750f6ce停车中
    private Contrast tCz;
    //9774f488d8054ebab4c9e843ff7e86a4通道-占用
    private Contrast tdZy;
    //85f8320ee6004d5eb7f21a470fadb366取车中
    private Contrast qCz;
    
    public Run(){
        ContrastRun run = new ContrastRun();
        this.setCkZy(run.getContrastByRealName("车库-占用"));
        this.settDktg(run.getContrastByRealName("通道-可通过"));
        this.setRk(run.getContrastByRealName("入口"));
        this.settCz(run.getContrastByRealName("停车中"));
        this.setTdZy(run.getContrastByRealName("通道-占用"));
        this.setqCz(run.getContrastByRealName("取车中"));
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

        // 获得存车数据
        Parking parking = new Parking();
        ParkingRun parkingRun = new ParkingRun();
        SpaceRun spaceRun = new SpaceRun();
        // 获得入口id
        String inSpaceId = spaceRun.getCrk("入口").getId();
        Date inTime = getDate();
        ContrastRun contrastRun = new ContrastRun();
        String nature = contrastRun.getContrastByRealName("存车中").getId();
        Space fetureSpace = spaceRun.getSaveSpace();
        String fetureSpaceId = fetureSpace.getId();
        fetureSpace.setNature("7bd42e7aad7645e4ad586349691a87e6");
        // 锁定空间
        spaceRun.Update(fetureSpace);

        parking.setId(getId());
        parking.setUserId(userId);
        parking.setCarId(carId);
        parking.setOrginalSpaceId(inSpaceId);
        parking.setNowSpaceId(inSpaceId);
        parking.setFetureSpaceId(fetureSpaceId);
        parking.setInTime(inTime);
        parking.setOutTime(outTime);
        parking.setNature(nature);

        parkingRun.save(parking);
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
     * @throws ParseException 
     */
    public void getCar() throws ParseException {
        ContrastRun contrastRun = new ContrastRun();
        String saveCarRealNameId = contrastRun.getContrastByRealName("存车中").getId();
        String inCarRealNameId = contrastRun.getContrastByRealName("停车中").getId();
        String takeOutCarRealNameId = contrastRun.getContrastByRealName("取车中").getId();

        ParkingRun parkingRun = new ParkingRun();
        List<Parking> inCar = parkingRun.getParkingsByNature(inCarRealNameId, "");
        this.inCarForTimeToDo(inCar, inCarRealNameId, takeOutCarRealNameId);

        List<Parking> takeOutCar = parkingRun.getParkingsByNature(takeOutCarRealNameId, "order by outTime");
        this.takeOutCarForTimeToDo(takeOutCar);

        List<Parking> saveCar = parkingRun.getParkingsByNature(saveCarRealNameId, "order by inTime");
        this.takeOutCarForTimeToDo(saveCar);
    }

    /**
     * 每分钟进行的方法进行每个车的获得下一步路径并处理
     * 
     * @param saveCars
     */
    public void takeOutCarForTimeToDo(List<Parking> saveCars) {
        SpaceRun spaceRun = new SpaceRun();
        saveCars.forEach(saveCar -> {
            Space nowSpace = spaceRun.get(saveCar.getNowSpaceId());
            Space fetureSpace = spaceRun.get(saveCar.getFetureSpaceId());
            this.carNextWay(nowSpace, fetureSpace, saveCar);
        });
    }

    /**
     * 每分钟进行的方法进行每个车的获得下一步路径.
     * 
     * @param nowSpace
     * @param fetureSpace
     * @param parking
     */
    public boolean carNextWay(Space nowSpace, Space fetureSpace, Parking parking) {
        int nX = nowSpace.getX();
        int nY = nowSpace.getY();
        int nZ = nowSpace.getZ();
        int fX = fetureSpace.getX();
        int fY = fetureSpace.getY();
        int fZ = fetureSpace.getZ();
        ParkingRun parkingRun = new ParkingRun();
        String nature = parking.getNature();
        Map<String, Integer> zb = null;
        ContrastRun contrastRun = new ContrastRun();
        String saveCarRealNameId = contrastRun.getContrastByRealName("存车中").getId();
        SpaceRun spaceRun = new SpaceRun();
        if (saveCarRealNameId.equals(nature)) {
            // 存车中
            zb = this.getNextSpaceWhenSavingCar(nX, nY, nZ, fX, fY, fZ);
            // 能不能挪动
            Space nextSpace = spaceRun.getSpaceByXYZ(zb.get("x"), zb.get("y"), zb.get("z"));
            if(StringUtils.isNullOrEmpty(nextSpace.getCarId())){
                // 可以挪动
                if (zb.get("x") == fX && zb.get("y") == fY && zb.get("z") == fZ) {
                    // 挪动到位置
                    nextSpace.setCarId(parking.getCarId());
                    nextSpace.setNature("7bd42e7aad7645e4ad586349691a87e6");
                    spaceRun.Update(nextSpace);
                    nowSpace.setCarId(null);
                    nowSpace.setNature("920005b52e54468ca653be0b593e6025");
                    if(!nowSpace.getNature().equals("1e95f2208c7f4dd9b584889bba7e3164")){
                        nowSpace.setNature("920005b52e54468ca653be0b593e6025");
                    }
                        
                    spaceRun.Update(nowSpace);
                    parking.setNature("b8dd53a77a1e4c809550dc1dc750f6ce");
                    parking.setNowSpaceId(nextSpace.getId());
                    parkingRun.Update(parking);
                    return true;
                } else {
                    // 未挪动到位置
                    nextSpace.setCarId(parking.getCarId());
                    nextSpace.setNature("9774f488d8054ebab4c9e843ff7e86a4");
                    spaceRun.Update(nextSpace);
                    nowSpace.setCarId(null);
                    if(!nowSpace.getNature().equals("1e95f2208c7f4dd9b584889bba7e3164")){
                        nowSpace.setNature("920005b52e54468ca653be0b593e6025");
                    }
                    spaceRun.Update(nowSpace);
                    parking.setNowSpaceId(nextSpace.getId());
                    parkingRun.Update(parking);
                    return true;
                }
            } else {
                // 不可以挪动
                return false;
            }
        } else {
            // 取车中
            zb = this.getNextSpaceWhenTakingOutCar(nX, nY, nZ, fX, fY, fZ);
            // 能不能挪动
            Space nextSpace = spaceRun.getSpaceByXYZ(zb.get("x"), zb.get("y"), zb.get("z"));
            if (nextSpace.getCarId() == null) {
                // 可以挪动
                if (zb.get("x") == fX && zb.get("y") == fY && zb.get("z") == fZ) {
                    // 挪动到位置
                    nowSpace.setCarId(null);
                    nowSpace.setNature("920005b52e54468ca653be0b593e6025");
                    spaceRun.Update(nowSpace);
                    parkingRun.delete(parking.getId());;
                    return true;
                } else {
                    // 未挪动到位置

                    nextSpace.setCarId(parking.getCarId());
                    nextSpace.setNature("7bd42e7aad7645e4ad586349691a87e6");
                    spaceRun.Update(nextSpace);
                    nowSpace.setCarId(null);
                    nowSpace.setNature("920005b52e54468ca653be0b593e6025");
                    spaceRun.Update(nowSpace);
                    parking.setNature("b8dd53a77a1e4c809550dc1dc750f6ce");
                    parking.setNowSpaceId(nextSpace.getId());
                    parkingRun.Update(parking);
                    return true;
                }
            } else {
                // 不可以挪动
                return false;
            }
            // 是否到位置上
        }
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
        Map<String, Integer> zb = new HashMap<String, Integer>();
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
        Map<String, Integer> zb = new HashMap<String, Integer>();
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
        Map<String, Integer> zb = new HashMap<String, Integer>();
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
                        if(nY-fY>0){
                            zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
                        }else{
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
    public void inCarForTimeToDo(List<Parking> inCar, String inCarRealNameId, String takeOutCarRealNameId) throws ParseException {
        ParkingRun parkingRun = new ParkingRun();

        Date date = getDate();
        inCar.forEach(parks -> {
            if (parks.getOutTime() != null && date.compareTo(parks.getOutTime()) == 1) {
                Parking inCarToOutCar = parks;
                try {
                    takeOutCar(inCarToOutCar.getCarId());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
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
    public Parking takeOutCar(String carId) throws ParseException {
        ParkingRun parkingRun = new ParkingRun();
        SpaceRun spaceRun = new SpaceRun();
        Parking parking = parkingRun.getParkingByCarid(carId);
        String fetrueSpaceId = spaceRun.getCrk("出口").getId();
        Date outTime = parking.getOutTime();
        if (outTime == null) {
            outTime = getDate();
        }
        parking.setOutTime(outTime);
        parking.setFetureSpaceId(fetrueSpaceId);
        parking.setOrginalSpaceId(parking.getNowSpaceId());
        parking.setNature("85f8320ee6004d5eb7f21a470fadb366");
        parkingRun.Update(parking);

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

    public Contrast getCkZy() {
        return ckZy;
    }

    public void setCkZy(Contrast ckZy) {
        this.ckZy = ckZy;
    }

    public Contrast gettDktg() {
        return tDktg;
    }

    public void settDktg(Contrast tDktg) {
        this.tDktg = tDktg;
    }

    public Contrast getRk() {
        return rk;
    }

    public void setRk(Contrast rk) {
        this.rk = rk;
    }

    public Contrast gettCz() {
        return tCz;
    }

    public void settCz(Contrast tCz) {
        this.tCz = tCz;
    }

    public Contrast getTdZy() {
        return tdZy;
    }

    public void setTdZy(Contrast tdZy) {
        this.tdZy = tdZy;
    }

    public Contrast getqCz() {
        return qCz;
    }

    public void setqCz(Contrast qCz) {
        this.qCz = qCz;
    }
    
    
}
