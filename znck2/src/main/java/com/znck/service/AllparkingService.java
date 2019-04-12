package com.znck.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.PublicMethods;
import com.znck.entity.SpaceEntity;
import com.znck.entity2.ParkingEntity2;
import com.znck.enums.InitDataListener;

@Service
public class AllParkingService2 {
	@Autowired
	private ContrastServiceImpl contrastServiceImpl;

	private final static String ENTRANCE = "入口";

	private final static String EXPORT = "出口";

	private final static String GARAGE_TAKE_UP = "车库-占用";

	private final static String GETIN_CAR = "取车中";

	private final static String GARAGE_VACANCY = "车库-空置";

	private final static String IN_THE_PARKING = "停车中";
	
	private final static String BUFFER_VACANCY = "缓冲区-空置";
	
	private final static String BUFFER_TAKE_UP = "缓冲区-占用";
	
	private final static String VIP_WAIT_USER_PARKING = "等待用户停车中";

	private final static String VIP_IN_THE_PARKING = "vip停车中";
	
	private final static String VIP_APP_GETCAR = "vip预约取车中";
	
	private final static String VIP_GETCAR = "vip取车中";

	
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
	
	/**
	 * 	用户预约停车-预约
	 * 
	 * @param carId
	 * @param vipAppParingTimeByString
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String vipAppSaveCar(String carId, String vipAppParingTimeByString) throws InterruptedException, ParseException {
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
		List<SpaceEntity> spacesForApp = spaces.stream().filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId()))
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
		spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_TAKE_UP).getId());
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
		parkingEntity2.setNature(contrastServiceImpl.getContrastByRealName(VIP_WAIT_USER_PARKING).getId());
		parkingEntity2.setWay("");
		parkings.add(parkingEntity2);
		InitDataListener.parkings2 = parkings;
		InitDataListener.spaces = spaces;
		offclock(threadId);
		return "恭喜！停车成功。";

	}



	/**
	 * 预约停车-用户已经来了并且扫码
	 * 
	 * @param carId
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String vipSaveCar(String carId) throws InterruptedException, ParseException {
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
		spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
		spaceForApp.setCarId(null);
		Collections.replaceAll(spaces, spaceForAppOld, spaceForApp);
		parking.setInPlaceTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_IN_THE_PARKING).getId());
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
	public String saveCar(String carId) throws ParseException, InterruptedException {
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
		System.out.println("空车位数量："+spacesForFeture.size());
		// 获得入口id
		SpaceEntity inSpace = spaces.stream().filter(a -> !StringUtils.isNullOrEmpty(a.getNature()))
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId()))
				.collect(Collectors.toList()).get(0);
		
		// 更新数据
		SpaceEntity saveSpaceOld = spacesForFeture.get(0);
		SpaceEntity saveSpace = saveSpaceOld;
		saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
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
		return "停车成功";
	}

	/**
	 * vip用户取车方法，需要两个参数：汽车id和时间
	 * 
	 * @param carId
	 * @param getOutTimeString
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public String vipTakeOutCar(String carId, String getOutTimeString) throws InterruptedException, ParseException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		// 获得缓冲区，没有缓冲区则返回请选择普通停车方法

		List<SpaceEntity> spacesForAppGet = spaces.stream().filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId()))
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
		parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_APP_GETCAR).getId());
		appGetSpace.setCarId(parking.getCarId());
		appGetSpace.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_TAKE_UP).getId());
		// 更新数据
		Collections.replaceAll(parkings, oldParking, parking);
		Collections.replaceAll(spaces, appGetSpaceOld, appGetSpace);
		InitDataListener.spaces = spaces;
		InitDataListener.parkings2 = parkings;
		offclock(clockId);
		return "true";
	}

	/**
	 * 	普通用户取车方法
	 * 
	 * @param carId
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void takeOutCar(String carId) throws InterruptedException, ParseException {
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
				.filter(space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(EXPORT).getId()))
				.collect(Collectors.toList()).get(0).getId());
		parking.setOutTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId());
		// 更新数据
		Collections.replaceAll(parkings, parkingOld, parking);
		InitDataListener.parkings2 = parkings;
		offclock(clockId);
	}

	/**
	 * 用户开走车辆的方法
	 * @param carId
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	public void getCar(String carId) throws InterruptedException, ParseException {
		String clockId = PublicMethods.getId();
		onclock(clockId);

		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		ParkingEntity2 parkingOld = parkings.stream().filter(parking -> parking.getCarId().equals(carId)).collect(Collectors.toList()).get(0);
		ParkingEntity2 parking = parkingOld;
		parking.setGetTime(PublicMethods.getDate());
//		ParkingSaveEntity2 parkingSave = new ParkingSaveEntity2(parking);
//		parkingSaveServiceImpl.insert(parkingSave);
		InitDataListener.parkings2.remove(parkingOld);
		offclock(clockId);
	}

	/**
	 * vip用户取消存车的方法
	 * @param carId
	 */
	public void vipCancelSaveCar(String carId) throws InterruptedException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		ParkingEntity2 removeParking = InitDataListener.parkings2.stream().filter(parking -> parking.getCarId().equals(carId)).collect(Collectors.toList()).get(0);
		SpaceEntity spaceForAppOld = InitDataListener.spaces.stream().filter(space -> space.getId().equals(removeParking.getInSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity spaceForSaveOld = InitDataListener.spaces.stream().filter(space -> space.getId().equals(removeParking.getSaveSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity spaceForApp = spaceForAppOld;
		SpaceEntity spaceForSave = spaceForSaveOld;
		spaceForApp.setCarId(null);
		spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
		spaceForSave.setCarId(null);
		spaceForSave.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
		List<SpaceEntity> spaces = InitDataListener.spaces;
		Collections.replaceAll(spaces,spaceForAppOld,spaceForApp);
		Collections.replaceAll(spaces,spaceForSaveOld,spaceForSave);
		InitDataListener.spaces = spaces;
		InitDataListener.parkings2.remove(removeParking);
		offclock(clockId);
	}

	/**
	 * vip用户立即取车方法
	 * @param carId
	 */
	public void vipTakeOutCarNow(String carId) throws ParseException, InterruptedException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		ParkingEntity2 parkingOld = InitDataListener.parkings2.stream().filter(parking -> parking.getCarId().equals(carId)).collect(Collectors.toList()).get(0);
		List<ParkingEntity2> parkings = InitDataListener.parkings2;
		ParkingEntity2 parking = parkingOld;
		// 修改数据
		parking.setOutTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_GETCAR).getId());
		// 更新数据
		Collections.replaceAll(parkings, parkingOld, parking);
		InitDataListener.parkings2 = parkings;
		offclock(clockId);
	}
}
