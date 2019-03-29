package com.znck.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ParkingEntity;
import com.znck.entity.PublicMethods;
import com.znck.entity.SpaceEntity;
import com.znck.entity2.ParkingEntity2;
import com.znck.enums.InitDataListener;

@Service
public class AllParkingService2 {
	@Autowired
	private ContrastServiceImpl contrastServiceImpl;

	private final static String ENTRANCE = "入口";

	private final static String PUTIN_CAR = "存车中";

	private final static String EXPORT = "出口";

	private final static String GARAGE_TAKE_UP = "车库-占用";

	private final static String GETIN_CAR = "取车中";

	private final static String CHANNEL_PASSABLE = "通道-可通过";

	private final static String CHANNEL_TAKE_UP = "通道-占用";

	private final static String GARAGE_VACANCY = "车库-空置";

	private final static String IN_THE_PARKING = "停车中";

	private final static int SIZETWO = 2;

	private final static int SIZETHREE = 3;

	private final static int SIZETWENTYTWO = 22;

	public void onclock(String threadId) throws InterruptedException {
		do {
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForParking)) {
				InitDataListener.lockForParking = threadId;
			}
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForSpace)) {
				InitDataListener.lockForSpace = threadId;
			}
			Thread.sleep(100);
		} while (!(InitDataListener.lockForParking.equals(threadId) & InitDataListener.lockForSpace.equals(threadId)));
	}

	public void offclock(String threadId) {
		InitDataListener.lockForParking = null;
		InitDataListener.lockForSpace = null;
	}

	public void parkingRun() throws InterruptedException {
		// 上锁
		String threadId = PublicMethods.getId();
		onclock(threadId);
		//等待用户存车运行方法
		InitDataListener.parkings2.stream().filter(parking -> parking.getNature().equals("等待用户停车中")).collect(Collectors.toList()).forEach(parking ->{
			try {
				appSaveCarIfOutTime(parking);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//vip用户停车方法
		InitDataListener.parkings2.stream()
			.filter(parking -> parking.getNature().equals(contrastServiceImpl.getContrastByRealName(IN_THE_PARKING)) & parking.getVipAppGetTime() != null)
			.sorted(Comparator.comparing(ParkingEntity2::getVipSendTimeForSort))
			.collect(Collectors.toList());
		//vip用户预约停车等待方法
		//vip用户取车方法
		//普通用户停车方法
		//普通用户取车方法
		
		offclock(threadId);
	}
	
	/**
	 * 用户预约停车-预约
	 * 
	 * @param carId
	 * @param vipAppParingTimeByString
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String vipSave(String carId, String vipAppParingTimeByString) throws InterruptedException, ParseException {
		// 上锁
		String threadId = PublicMethods.getId();
		onclock(threadId);
		// 从静态变量中获得数据
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		// 获得车位，若没有车位则返回没有车位
		List<SpaceEntity> spacesForFeture = spaces.stream()
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId()))
				.sorted(Comparator.comparing(SpaceEntity::getWeight)).collect(Collectors.toList());
		if (spacesForFeture.size() == 0) {
			offclock(threadId);
			return "没有车位，请等待车位";
		}
		// 获得缓冲区，没有缓冲区则返回请选择普通停车方法
		List<SpaceEntity> spacesForApp = spaces.stream().filter(a -> a.getNature().equals("缓冲区-空闲"))
				.collect(Collectors.toList());
		if (spacesForApp.size() == 0) {
			offclock(threadId);
			return "缓冲区已满，请选择普通停车方法";
		}
		// 更新空间列表，锁定空间
		SpaceEntity spaceForSave = spacesForFeture.get(0);
		spaceForSave.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
		spaceForSave.setCarId(carId);
		SpaceEntity spaceForApp = spacesForApp.get(0);
		spaceForApp.setCarId(carId);
		spaceForApp.setNature("缓冲区-占用");
		Collections.replaceAll(spaces, spacesForFeture.get(0), spaceForSave);
		Collections.replaceAll(spaces, spacesForApp.get(0), spaceForApp);
		// 新建停车信息
		ParkingEntity2 parkingEntity2 = new ParkingEntity2();
		parkingEntity2.setId(PublicMethods.getId());
		parkingEntity2.setCarId(carId);
		parkingEntity2.setInSpaceId(spaceForApp.getId());
		parkingEntity2.setNowSapceId(null);
		parkingEntity2.setSaveSpaceId(spaceForSave.getId());
		Random r = new Random();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		ParsePosition pos = new ParsePosition(0);
		Date vipAppParkingTime = formatter.parse(vipAppParingTimeByString + " " + (r.nextInt(899) + 100), pos);
		parkingEntity2.setVipAppParkingTime(vipAppParkingTime);
		parkingEntity2.setVipSendTime(PublicMethods.getDate());
		parkingEntity2.setNature("等待用户存车中");
		parkingEntity2.setWay("");
		parkings.add(parkingEntity2);
		InitDataListener.parkings2 = parkings;
		InitDataListener.spaces = spaces;
		return "true";

	}

	/**
	 * 预约车辆（没来的处理方法）如果超时，返回ture，没超时返回false
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
			appSpace.setNature("缓冲区-空置");
			saveSpace.setCarId(null);
			saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
			Collections.replaceAll(spaces, appSpaceOld, appSpace);
			Collections.replaceAll(spaces, saveSPlaceOld, saveSpace);
			InitDataListener.spaces = spaces;
			InitDataListener.parkings2.remove(parking);
		}
	}

	/**
	 * 预约停车-用户已经来了并且扫码
	 * 
	 * @param carId
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String vipSaveCarWhenUserInParking(String carId) throws InterruptedException, ParseException {
		// 上锁
		String threadId = PublicMethods.getId();
		onclock(threadId);
		// 从静态变量中获得数据
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		ParkingEntity2 parkingOld = parkings.stream().filter(parking -> parking.getCarId().equals(carId))
				.collect(Collectors.toList()).get(0);
		ParkingEntity2 parking = parkingOld;
		SpaceEntity spaceForAppOld = spaces.stream().filter(space -> space.getId().equals(parking.getInSpaceId()))
				.collect(Collectors.toList()).get(0);
		SpaceEntity spaceForApp = spaceForAppOld;
		// 获得入口id
		SpaceEntity nowSpace = spaces.stream().filter(a -> !StringUtils.isNullOrEmpty(a.getNature()))
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId()))
				.collect(Collectors.toList()).get(0);
		// 更新数据
		spaceForApp.setNature("缓冲区-空置");
		spaceForApp.setCarId(null);
		Collections.replaceAll(spaces, spaceForAppOld, spaceForApp);
		parking.setInPlaceTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId());
		parking.setNowSapceId(nowSpace.getId());
		parking.setWay(nowSpace.getX() + "," + nowSpace.getY() + "," + nowSpace.getZ());
		Collections.replaceAll(parkings, parkingOld, parking);
		InitDataListener.parkings2 = parkings;
		InitDataListener.spaces = spaces;
		offclock(threadId);
		return "true";
	}

	/**
	 * 普通用户停车
	 * 
	 * @param carId
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String save(String carId) throws InterruptedException, ParseException {
		// 上锁
		String threadId = PublicMethods.getId();
		onclock(threadId);
		// 从静态变量中获得数据
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		// 获得车位，若没有车位则返回没有车位
		List<SpaceEntity> spacesForFeture = spaces.stream()
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId()))
				.sorted(Comparator.comparing(SpaceEntity::getWeight)).collect(Collectors.toList());
		if (spacesForFeture.size() == 0) {
			offclock(threadId);
			return "没有车位，请等待车位";
		}
		// 获得入口id
		SpaceEntity inSpace = spaces.stream().filter(a -> !StringUtils.isNullOrEmpty(a.getNature()))
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId()))
				.collect(Collectors.toList()).get(0);

		// 更新数据
		SpaceEntity saveSpaceOld = spacesForFeture.get(0);
		SpaceEntity saveSpace = saveSpaceOld;
		saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
		saveSpace.setCarId(carId);
		Collections.replaceAll(spaces, saveSpaceOld, saveSpace);
		ParkingEntity2 parking = new ParkingEntity2();
		parking.setId(PublicMethods.getId());
		parking.setCarId(carId);
		parking.setInSpaceId(inSpace.getId());
		parking.setNowSapceId(inSpace.getId());
		parking.setSaveSpaceId(saveSpace.getId());
		parking.setInPlaceTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId());
		parking.setWay(inSpace.getX() + "," + inSpace.getY() + "," + inSpace.getZ());
		parkings.add(parking);
		InitDataListener.parkings2 = parkings;
		InitDataListener.spaces = spaces;
		offclock(threadId);
		return "true";
	}

	/**
	 * 正在开往停车位的方法
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
				parking.setSaveSpaceId(nextSpace.getId());
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

	/**
	 * vip用户取车方法，需要两个参数：汽车id和时间
	 * 
	 * @param carId
	 * @param getOutTimeString
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String vipgetCar(String carId, String getOutTimeString) throws InterruptedException, ParseException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		// 获得缓冲区，没有缓冲区则返回请选择普通停车方法

		List<SpaceEntity> spacesForAppGet = spaces.stream().filter(a -> a.getNature().equals("缓冲区-空闲"))
				.collect(Collectors.toList());
		if (spacesForAppGet.size() == 0) {
			offclock(clockId);
			return "缓冲区已满，请选择普通取车方法";
		}
		// 获得基本数据
		ParkingEntity2 oldParking = parkings.stream().filter(parking -> parking.getCarId().equals(carId))
				.collect(Collectors.toList()).get(0);
		ParkingEntity2 parking = oldParking;
		SpaceEntity appGetSpaceOld = spacesForAppGet.get(0);
		SpaceEntity appGetSpace = appGetSpaceOld;
		Random r = new Random();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		ParsePosition pos = new ParsePosition(0);
		Date vipAppGetTime = formatter.parse(getOutTimeString + " " + (r.nextInt(899) + 100), pos);
		// 修改数据
		parking.setOutSpaceId(appGetSpace.getId());
		parking.setVipGetTime(PublicMethods.getDate());
		parking.setVipAppGetTime(vipAppGetTime);
		parking.setNature("vip预约取车中");
		appGetSpace.setCarId(parking.getCarId());
		appGetSpace.setNature("缓冲区-占用");
		// 更新数据
		Collections.replaceAll(parkings, oldParking, parking);
		Collections.replaceAll(spaces, appGetSpaceOld, appGetSpace);
		InitDataListener.spaces = spaces;
		InitDataListener.parkings2 = parkings;
		offclock(clockId);
		return "true";
	}

	/**
	 * 预约取辆（没来的处理方法）如果超时，则开始取车
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
			parking.setNature(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId());
			// 更新数据
			Collections.replaceAll(parkings, parkingOld, parking);
			InitDataListener.parkings2 = parkings;
		}
	}

	/**
	 * 普通用户取车方法
	 * 
	 * @param carId
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void getCar(String carId) throws InterruptedException, ParseException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		// 获得数据
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		ParkingEntity2 parkingOld = parkings.stream().filter(parking -> parking.getCarId().equals(carId))
				.collect(Collectors.toList()).get(0);
		ParkingEntity2 parking = parkingOld;
		// 修改数据
		parking.setOutSpaceId(spaces.stream()
				.filter(space -> space.getId().equals(contrastServiceImpl.getContrastByRealName(EXPORT).getId()))
				.collect(Collectors.toList()).get(0).getId());
		parking.setOutTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId());
		// 更新数据
		Collections.replaceAll(parkings, parkingOld, parking);
		InitDataListener.parkings2 = parkings;
		offclock(clockId);
	}

	public void getCarNextToDo(ParkingEntity2 parking) throws ParseException {
		ParkingEntity2 parkingOral = parking;
		SpaceEntity nowSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getNowSapceId())).collect(Collectors.toList()).get(0);
		SpaceEntity nowSpaceOral = nowSpace;
		SpaceEntity fetureSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getOutSpaceId())).collect(Collectors.toList()).get(0);
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
				parking.setNature("等待用户取车中");
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
	 * 用户开走车辆的方法
	 * @param carId
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public void userOutCarToDo(String carId) throws InterruptedException, ParseException {
		String clockId = PublicMethods.getId();
		onclock(clockId);

		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		ParkingEntity2 parkingOld = parkings.stream().filter(parking -> parking.getCarId().equals(carId)).collect(Collectors.toList()).get(0);
		ParkingEntity2 parking = parkingOld;
		parking.setGetTime(PublicMethods.getDate());
//		ParkingSaveEntity2 parkingSave = new ParkingSaveEntity2(parking);
//		parkingSaveServiceImpl.insert(parkingSave);
		InitDataListener.parkings.remove(parkingOld);
		offclock(clockId);
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
}
