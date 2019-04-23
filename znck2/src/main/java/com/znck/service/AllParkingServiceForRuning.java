package com.znck.service;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.znck.service.serviceImpl.AllParkingModle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ParkingEntity;
import com.znck.entity.PublicMethods;
import com.znck.entity.SpaceEntity;
import com.znck.enums.InitDataListener;

/**
 * 车库运行服务类
 * 
 * @author 肖舒翔
 * 2019-04-09
 * @version 1.0
 */
@Service
public class AllParkingServiceForRuning {

	@Autowired
	private ContrastServiceImpl contrastServiceImpl;

	@Autowired
	private AllParkingModle allParkingModle;
	
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

	private final static String X = "x";

	private final static String Y = "y";

	private final static String Z = "z";
	
	private final static String BUFFER_VACANCY = "缓冲区-空置";
	
	private final static String WAIT_USER_GET = "等待用户取车中";

	private final static String VIP_GETCAR = "vip取车中";

	private final static String GETIN_CAR = "取车中";

	private final static String IN_THE_PARKING = "停车中";

	private final static String VIP_WAIT_USER_PARKING = "等待用户停车中";

	private final static String VIP_IN_THE_PARKING = "vip停车中";

	private final static String VIP_APP_GETCAR = "vip预约取车中";

	private void onclock(String threadId) throws InterruptedException {
		do {
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForParking)) {
				InitDataListener.lockForParking = threadId;
			}
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForSpace)) {
				InitDataListener.lockForSpace = threadId;
			}
			Thread.sleep(100);
		} while (!(threadId.equals(InitDataListener.lockForParking) & threadId.equals(InitDataListener.lockForSpace)));
	}

	private void offclock() {
		InitDataListener.lockForParking = null;
		InitDataListener.lockForSpace = null;
	}
	
	public void parkingRun() throws InterruptedException,ParseException {
		System.out.println("车库运行中");
		// 上锁
		String threadId = PublicMethods.getId();
		onclock(threadId);
		//等待用户存车运行方法
		allParkingModle.getParkingEntitysByNatureAndTime(VIP_WAIT_USER_PARKING,null).forEach(parking ->{ appSaveCarIfOutTime(parking); });
		//vip用户停车方法
		allParkingModle.getParkingEntitysByNatureAndTime(VIP_IN_THE_PARKING,"getVipSendTimeForSort").forEach(parking ->{saveCarNextTodo(parking);});
		//vip用户预约取车等待方法
		allParkingModle.getParkingEntitysByNatureAndTime(VIP_APP_GETCAR,"getVipAppGetTimeForSort").forEach(parking -> { appGetCarIfOutTime(parking); });
		//vip用户取车方法
		allParkingModle.getParkingEntitysByNatureAndTime(VIP_GETCAR,"getVipAppGetTimeForSort").forEach(parking ->{ getCarNextToDo(parking); });
		//普通用户停车方法
		allParkingModle.getParkingEntitysByNatureAndTime(IN_THE_PARKING,"getInPlaceTimeForSort").forEach(parking ->{ saveCarNextTodo(parking); });
		//普通用户取车方法
		allParkingModle.getParkingEntitysByNatureAndTime(GETIN_CAR,"getOutTimeForSort").forEach(parking ->{ getCarNextToDo(parking); });
		offclock();
	}

	public void showInfo(){
		System.out.println("总共一共有parking类："+InitDataListener.parkings.size());
		InitDataListener.parkings.forEach(parking ->{
			System.out.println(parking.toString());
		});
		System.out.println("总共有车位：" + allParkingModle.getSpaceEntitysByNature(GARAGE_VACANCY).size());
		System.out.println("总共有缓冲区：" + allParkingModle.getSpaceEntitysByNature(BUFFER_VACANCY).size());
		System.out.println("普通的开往停车位的车辆："+allParkingModle.getParkingEntitysByNatureAndTime(IN_THE_PARKING,null).size());
		System.out.println("vip正在开往停车位的车辆：" + allParkingModle.getParkingEntitysByNatureAndTime(VIP_IN_THE_PARKING,null).size());
		System.out.println("停放车辆数量：" + allParkingModle.getParkingEntitysByNatureAndTime(PUTIN_CAR,null).size());
		System.out.println("普通的开往出口的车辆：" + allParkingModle.getParkingEntitysByNatureAndTime(GETIN_CAR,null).size());
		System.out.println("等待取走的车辆：" + allParkingModle.getParkingEntitysByNatureAndTime(WAIT_USER_GET,null).size());
		System.out.println("vip等待开往停车位的车辆：" + allParkingModle.getParkingEntitysByNatureAndTime(VIP_WAIT_USER_PARKING,null).size());
		System.out.println("vip预约取车等待的车辆：" + allParkingModle.getParkingEntitysByNatureAndTime(VIP_APP_GETCAR,null).size());
		System.out.println("vip正在取车的车辆：" + allParkingModle.getParkingEntitysByNatureAndTime(VIP_GETCAR,null).size());
		System.out.println("车库中存在多少被占用的通道：" + allParkingModle.getSpaceEntitysByNature(CHANNEL_TAKE_UP).size());
		System.out.println("\n");
	}

	/**
	 * 	运行时vip预约车辆（没来的处理方法）如果超时，返回ture，没超时返回false
	 * 
	 * @param parking
	 * @return
	 * @throws ParseException
	 */
	private void appSaveCarIfOutTime(ParkingEntity parking) {
		try {
			if (PublicMethods.getDate().compareTo(parking.getVipAppParkingTime()) == 1) {
				SpaceEntity appSpace = allParkingModle.getSpaceById(parking.getInSpaceId());
				SpaceEntity saveSpace = allParkingModle.getSpaceById(parking.getSaveSpaceId());
				appSpace.setCarId(null);
				appSpace.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
				saveSpace.setCarId(null);
				saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
				InitDataListener.parkings.remove(parking);
				allParkingModle.replaceSpace(saveSpace);
				allParkingModle.replaceSpace(appSpace);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 	 运行中正在开往停车位的方法
	 * 
	 * @param parking
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	private void saveCarNextTodo(ParkingEntity parking){
		SpaceEntity nowSpace = allParkingModle.getSpaceById(parking.getNowSapceId());
		SpaceEntity fetureSpace = allParkingModle.getSpaceById(parking.getSaveSpaceId());
		final Map<String, Integer> nextSpaceMap = this.getNextSpaceWhenSavingCar(nowSpace.getX(), nowSpace.getY(),
				nowSpace.getZ(), fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
		SpaceEntity nextSpace = allParkingModle.getSpaceByXYZ(nextSpaceMap.get(X),nextSpaceMap.get(Y),nextSpaceMap.get(Z));
		if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
			nextSpace.setCarId(parking.getCarId());
			nowSpace.setCarId(null);
			parking.setNowSapceId(nextSpace.getId());
			if (nowSpace.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId())) {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId());
			} else {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
			}
			if (allParkingModle.nextSpaceIsFetureSpace(nextSpaceMap,fetureSpace)) {
				parking.setNature(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId());
				try {
					parking.setInTime(PublicMethods.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				nextSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_TAKE_UP).getId());
			}
			parking.setWay(parking.getWay() + "|" + nextSpace.getX() + "," + nextSpace.getY() + "," + nextSpace.getZ());
		} else {
			parking.setWay(parking.getWay() + "|wait");
		}
		allParkingModle.replaceSpace(nowSpace);
		allParkingModle.replaceSpace(fetureSpace);
		allParkingModle.replaceParking(parking);
	}
	
	/**
	 * 	运行时vip取车预约取辆（没来的处理方法）如果超时，则开始取车
	 * 
	 * @param parking
	 * @return
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	private void appGetCarIfOutTime(ParkingEntity parking) {
		try {
			if (PublicMethods.getDate().compareTo(parking.getVipAppGetTime()) == 1) {
				// 修改数据
				parking.setOutTime(PublicMethods.getDate());
				parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_GETCAR).getId());
				// 更新数据
				allParkingModle.replaceParking(parking);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 	运行时普通用户取车方法
	 * @param parking
	 * @throws ParseException
	 */
	private void getCarNextToDo(ParkingEntity parking) {
		SpaceEntity nowSpace = allParkingModle.getSpaceById(parking.getNowSapceId());
		SpaceEntity fetureSpace = allParkingModle.getBufferOrFeture(EXPORT);
		final Map<String, Integer> nextSpaceMap = this.getNextSpaceWhenTakingOutCar(nowSpace.getX(), nowSpace.getY(),
				nowSpace.getZ(), fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
		SpaceEntity nextSpace = allParkingModle.getSpaceByXYZ(nextSpaceMap.get(X),nextSpaceMap.get(Y),nextSpaceMap.get(Z));
		if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
			nowSpace.setCarId(null);
			if (allParkingModle.nextSpaceIsFetureSpace(nextSpaceMap,fetureSpace)) {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
				String way = parking.getWay() + "|" + fetureSpace.getX() + "," + fetureSpace.getY() + ","
						+ fetureSpace.getZ();
				try {
					parking.setOutPlaceTime(PublicMethods.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		allParkingModle.replaceParking(parking);
		allParkingModle.replaceSpace(nowSpace);
		allParkingModle.replaceSpace(nextSpace);
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
	private Map<String, Integer> getNextSpaceWhenTakingOutCar(int nX, int nY, int nZ, int fX, int fY, int fZ) {
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
	private Map<String, Integer> getNextSpaceWhenSavingCar(int nX, int nY, int nZ, int fX, int fY, int fZ) {
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
	private Map<String, Integer> getNextSpaceMapStringInteger(int x, int y, int z) {
		Map<String, Integer> zb = new HashMap<String, Integer>(3);
		zb.put("x", x);
		zb.put("y", y);
		zb.put("z", z);
		return zb;
	}
}
