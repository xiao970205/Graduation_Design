package com.znck.service;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.PublicMethods;
import com.znck.entity.SpaceEntity;
import com.znck.entity2.ParkingEntity2;
import com.znck.enums.InitDataListener;

@Service
public class AllParkingServiceForRuning {

	@Autowired
	private ContrastServiceImpl contrastServiceImpl;
	
	private final static String ENTRANCE = "入口";

	private final static String PUTIN_CAR = "存车中";

	private final static String EXPORT = "出口";

	private final static String GARAGE_TAKE_UP = "车库-占用";

	private final static String CHANNEL_PASSABLE = "通道-可通过";

	private final static String CHANNEL_TAKE_UP = "通道-占用";

	private final static String GARAGE_VACANCY = "车库-空置";

	private final static int SIZETWO = 2;

	private final static int SIZETHREE = 3;

	private final static int SIZETWENTYTWO = 22;
	
	private final static String BUFFER_VACANCY = "缓冲区-空置";
	
	private final static String WAIT_USER_GET = "等待用户取车中";

	private final static String VIP_GETCAR = "vip取车中";
	
	/**
	 * 	运行时vip预约车辆（没来的处理方法）如果超时，返回ture，没超时返回false
	 * 
	 * @param parking
	 * @return
	 * @throws ParseException
	 */
	public void appSaveCarIfOutTime(ParkingEntity2 parking) throws ParseException {
		if (PublicMethods.getDate().compareTo(parking.getVipAppParkingTime()) == 1) {
			List<SpaceEntity> spaces = InitDataListener.spaces;
			SpaceEntity appSpaceOld = spaces.stream().filter(space -> space.getId().equals(parking.getInSpaceId())).collect(Collectors.toList()).get(0);
			SpaceEntity appSpace = appSpaceOld;
			SpaceEntity saveSPlaceOld = spaces.stream().filter(space -> space.getId().equals(parking.getSaveSpaceId())).collect(Collectors.toList()).get(0);
			SpaceEntity saveSpace = saveSPlaceOld;
			appSpace.setCarId(null);
			appSpace.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
			saveSpace.setCarId(null);
			saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
			Collections.replaceAll(spaces, appSpaceOld, appSpace);
			Collections.replaceAll(spaces, saveSPlaceOld, saveSpace);
			InitDataListener.spaces = spaces;
			InitDataListener.parkings2.remove(parking);
		}
	}
	
	/**
	 * 	 运行中正在开往停车位的方法
	 * 
	 * @param parking
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	public void saveCarNextTodo(ParkingEntity2 parking) throws ParseException, InterruptedException {
		ParkingEntity2 parkingOral = parking;
		SpaceEntity nowSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getNowSapceId())).collect(Collectors.toList()).get(0);
		SpaceEntity nowSpaceOral = nowSpace;
		SpaceEntity fetureSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getSaveSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity fetureSpaceOral = fetureSpace;
		final String x = "x";
		final String y = "y";
		final String z = "z";
		final Map<String, Integer> nextSpaceMap = this.getNextSpaceWhenSavingCar(nowSpace.getX(), nowSpace.getY(),
				nowSpace.getZ(), fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
		SpaceEntity nextSpace = InitDataListener.spaces.stream().filter(space -> space.getX() == nextSpaceMap.get(x)
				&& space.getY() == nextSpaceMap.get(y) && space.getZ() == nextSpaceMap.get(z))
				.collect(Collectors.toList()).get(0);
		if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
			nextSpace.setCarId(parking.getCarId());
			nowSpace.setCarId(null);
			parking.setNowSapceId(nextSpace.getId());
			if (nowSpace.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId())) {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId());
			} else {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
			}
			if (nextSpaceMap.get(x) == fetureSpace.getX() && nextSpaceMap.get(y) == fetureSpace.getY()
					&& nextSpaceMap.get(z) == fetureSpace.getZ()) {
				parking.setNature(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId());
				parking.setInTime(PublicMethods.getDate());
			} else {
				nextSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_TAKE_UP).getId());
			}
			parking.setWay(parking.getWay() + "|" + nextSpace.getX() + "," + nextSpace.getY() + "," + nextSpace.getZ());
		} else {
			parking.setWay(parking.getWay() + "|wait");
		}
		Collections.replaceAll(InitDataListener.spaces, nowSpaceOral, nowSpace);
		Collections.replaceAll(InitDataListener.spaces, fetureSpaceOral, fetureSpace);
		Collections.replaceAll(InitDataListener.parkings2, parkingOral, parking);
	}
	
	/**
	 * 	运行时vip取车预约取辆（没来的处理方法）如果超时，则开始取车
	 * 
	 * @param parking
	 * @return
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	public void appGetCarIfOutTime(ParkingEntity2 parkingOld) throws ParseException, InterruptedException {
		if (PublicMethods.getDate().compareTo(parkingOld.getVipGetTime()) == 1) {
			// 获得数据
			List<ParkingEntity2> parkings = InitDataListener.parkings2;
			ParkingEntity2 parking = parkingOld;
			// 修改数据
			parking.setOutTime(PublicMethods.getDate());
			parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_GETCAR).getId());
			// 更新数据
			Collections.replaceAll(parkings, parkingOld, parking);
			InitDataListener.parkings2 = parkings;
		}
	}
	
	/**
	 * 	运行时普通用户取车方法
	 * @param parking
	 * @throws ParseException
	 */
	public void getCarNextToDo(ParkingEntity2 parking) throws ParseException {
		ParkingEntity2 parkingOral = parking;
		SpaceEntity nowSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getNowSapceId())).collect(Collectors.toList()).get(0);
		SpaceEntity nowSpaceOral = nowSpace;
		SpaceEntity fetureSpace = InitDataListener.spaces.stream().filter(a -> !StringUtils.isNullOrEmpty(a.getNature()))
		.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(EXPORT).getId()))
		.collect(Collectors.toList()).get(0);
		final String x = "x";
		final String y = "y";
		final String z = "z";
		final Map<String, Integer> nextSpaceMap = this.getNextSpaceWhenTakingOutCar(nowSpace.getX(), nowSpace.getY(),
				nowSpace.getZ(), fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
		SpaceEntity nextSpaceOld = InitDataListener.spaces.stream().filter(space -> space.getX() == nextSpaceMap.get(x)
				&& space.getY() == nextSpaceMap.get(y) && space.getZ() == nextSpaceMap.get(z))
				.collect(Collectors.toList()).get(0);
		SpaceEntity nextSpace = nextSpaceOld;
		if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
			nowSpace.setCarId(null);
			if (nextSpaceMap.get(x) == fetureSpace.getX() && nextSpaceMap.get(y) == fetureSpace.getY()
					&& nextSpaceMap.get(z) == fetureSpace.getZ()) {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
				String way = parking.getWay() + "|" + fetureSpace.getX() + "," + fetureSpace.getY() + ","
						+ fetureSpace.getZ();
				parking.setOutPlaceTime(PublicMethods.getDate());
				parking.setWay(way);
				parking.setNature(contrastServiceImpl.getContrastByRealName(WAIT_USER_GET).getId());
			} else {
				nextSpace.setCarId(parking.getCarId());
				nextSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_TAKE_UP).getId());
				if (nowSpace.getNature().equals(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId())) {
					nowSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
				} else {
					nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
				}
				parking.setNowSapceId(nextSpace.getId());
				parking.setWay(
						parking.getWay() + "|" + nextSpace.getX() + "," + nextSpace.getY() + "," + nextSpace.getZ());
			}
		} else {
			parking.setWay(parking.getWay() + "|wait");
		}
		Collections.replaceAll(InitDataListener.parkings2, parkingOral, parking);
		Collections.replaceAll(InitDataListener.spaces, nowSpaceOral, nowSpace);
		Collections.replaceAll(InitDataListener.spaces, nextSpaceOld, nextSpace);
	}
	
	/**
	 * 获得取车时候获得下一步路径算法
	 * 
	 * 取车的时候一共有几种情况 1.车辆没有入库在左侧通道被取走 1-1车辆在上下电梯上 nX = 1 & nY = 1 ; nY++ （被包括了）
	 * 1-2车辆在左侧通道上 1-2-1 可以通向横向通道 nX = 1 & (nY % 3) == 2 ;nX++ 1-2-2 不可以通向横向通道 nX =
	 * 1 & (nY % 3) != 2 ;nY++ （用） 1-3车辆在横向通道上 nX != maxX & nX != 1 ; nX++ （用）
	 * 2.车辆在车库中 nY % 3 != 2 2-1 在第一排，下挪 nY % 3 != 2 & nY == 1 ; nY++ 2-2不再第一排，上挪 nY
	 * % 3 != 2 & nY != 1 ; nY-- 3.车辆在取车路上 3-1在横向通道中，右挪 nX != maxX & nX == 1 ; nX++
	 * （重复） 3-2在右侧通道中，上挪 nX == maxX & nY != 1; nY-- 3-3在升降电梯，上挪 nX == maxX & nY == 1
	 * ; nZ--
	 * 
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
		if (nX == 1 & nY % SIZETHREE == SIZETWO) {
			// 在左侧通道并且可以往右移动
			zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
		} else if (nX == 1 & nY % SIZETHREE != SIZETWO) {
			// 在左侧通道但是不能往右移动，只能向下移动
			zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
		} else if ((nY % SIZETHREE != SIZETWO) & nY == 1) {
			// 在车位上，且在第一排，往下挪动。
			zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
		} else if ((nY % SIZETHREE != SIZETWO) & nY != 1) {
			// 在车位上，且不在第一排，可以上下移动。
			zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
		} else if (nX != SIZETWENTYTWO) {
			// 不在车位上，且不在最右侧通道，只能向右移动。
			zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
		} else if (nY != fY) {
			// 不在车位上，且不在同一排，所以只能在右侧通道,只能往前挪动
			zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
		} else {
			// 只能是楼层不同.
			zb = getNextSpaceMapStringInteger(nX, nY, nZ - 1);
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
		if (fZ - nZ != 0) {
			zb = getNextSpaceMapStringInteger(nX, nY, nZ + 1);
		} else if (nX == 1 & Math.abs(fY - nY) != 1) {
			zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
		} else if (nY % SIZETHREE == SIZETWO & Math.abs(fY - nY) == 1 & nX != fX) {
			zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
		} else if (nY > fY) {
			zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
		} else {
			zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
		}
		return zb;
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
}
