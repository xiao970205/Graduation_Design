package com.znck.service;

import java.text.ParseException;

import com.znck.entity.ParkingEntity;
import com.znck.entity.ParkingSaveEntity;
import com.znck.service.serviceImpl.AllParkingModle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.PublicMethods;
import com.znck.entity.SpaceEntity;
import com.znck.enums.InitDataListener;

@Service
public class AllparkingService {

	@Autowired
	private ContrastServiceImpl contrastServiceImpl;

	@Autowired
	private ParkingSaveServiceImpl parkingSaveServiceImpl;

	@Autowired
	private AllParkingModle allParkingModle;

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

	private final static String PUTIN_CAR = "存车中";
	
	public void onclock(String threadId) throws InterruptedException {
		do {
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForParking)) {
				InitDataListener.lockForParking = threadId;
			}
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForSpace)) {
				InitDataListener.lockForSpace = threadId;
			}
			Thread.sleep(100);
		} while (!((threadId.equals(InitDataListener.lockForParking) & threadId.equals(InitDataListener.lockForSpace))));
	}

	public void offclock() {
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

		SpaceEntity spaceForSave = allParkingModle.getBufferOrFeture(GARAGE_VACANCY);
		if(spaceForSave == null){
			offclock();
			return "没有车位，请等待车位";
		}

		SpaceEntity spaceForApp = allParkingModle.getBufferOrFeture(BUFFER_VACANCY);
		if(spaceForApp == null){
			offclock();
			return "缓冲区已满，请选择普通停车方法";
		}

		// 更新空间列表，锁定空间
		spaceForSave.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
		spaceForApp.setCarId(carId);
		spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_TAKE_UP).getId());
		allParkingModle.replaceSpace(spaceForSave);
		allParkingModle.replaceSpace(spaceForApp);

		// 新建停车信息
		ParkingEntity parkingEntity = new ParkingEntity(PublicMethods.getId(),carId,spaceForApp.getId()
			,null,spaceForSave.getId(),allParkingModle.getAppTime(vipAppParingTimeByString)
			,PublicMethods.getDate(),contrastServiceImpl.getContrastByRealName(VIP_WAIT_USER_PARKING).getId(),"");
		allParkingModle.addPakringEntity(parkingEntity);
		offclock();
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
		ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
		SpaceEntity spaceForApp = allParkingModle.getSpaceById(parking.getInSpaceId());
		// 获得入口id
		SpaceEntity nowSpace = allParkingModle.getBufferOrFeture(ENTRANCE);
		// 更新数据
		spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
		spaceForApp.setCarId(null);
		parking.setInPlaceTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_IN_THE_PARKING).getId());
		parking.setNowSapceId(nowSpace.getId());
		parking.setWay(nowSpace.getX() + "," + nowSpace.getY() + "," + nowSpace.getZ());
		allParkingModle.replaceSpace(spaceForApp);
		allParkingModle.replaceParking(parking);
		offclock();
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
		// 获得车位，若没有车位则返回没有车位
		SpaceEntity saveSpace = allParkingModle.getBufferOrFeture(GARAGE_VACANCY);
		if(saveSpace == null){
			offclock();
			return "没有车位，请等待车位";
		}
		// 获得入口id
		SpaceEntity inSpace = allParkingModle.getBufferOrFeture(ENTRANCE);
		
		// 更新数据
		saveSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
		ParkingEntity parking = new ParkingEntity(PublicMethods.getId(),carId,inSpace.getId()
				,inSpace.getId(),saveSpace.getId(),PublicMethods.getDate()
				,contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId()
				,inSpace.getX() + "," + inSpace.getY() + "," + inSpace.getZ());
		allParkingModle.addPakringEntity(parking);
		allParkingModle.replaceSpace(saveSpace);
		offclock();
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
		// 获得缓冲区，没有缓冲区则返回请选择普通停车方法
		SpaceEntity appGetSpace = allParkingModle.getBufferOrFeture(BUFFER_VACANCY);
		if(appGetSpace == null){
			offclock();
			return "缓冲区已满，请选择普通取车方法";
		}
		// 获得基本数据
		ParkingEntity parkingEntity = allParkingModle.getPakringByCarId(carId);
		// 修改数据
		parkingEntity.setOutSpaceId(appGetSpace.getId());
		parkingEntity.setVipGetTime(PublicMethods.getDate());
		parkingEntity.setVipAppGetTime(allParkingModle.getAppTime(getOutTimeString));
		parkingEntity.setNature(contrastServiceImpl.getContrastByRealName(VIP_APP_GETCAR).getId());
		appGetSpace.setCarId(parkingEntity.getCarId());
		appGetSpace.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_TAKE_UP).getId());
		// 更新数据
		allParkingModle.replaceSpace(appGetSpace);
		allParkingModle.replaceParking(parkingEntity);
		offclock();
		return "true";
	}

	public void vipCancelTakeOutCar(String carId) throws InterruptedException {
		// 上锁
		String threadId = PublicMethods.getId();
		onclock(threadId);
		// 从静态变量中获得数据
		ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
		SpaceEntity space = allParkingModle.getSpaceById(parking.getOutSpaceId());

		parking.setOutSpaceId(null);
		parking.setVipGetTime(null);
		parking.setVipAppGetTime(null);
		parking.setNature(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId());
		space.setCarId(null);
		space.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());

		// 更新数据
		allParkingModle.replaceParking(parking);
		allParkingModle.replaceSpace(space);
		offclock();
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
		ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
		// 修改数据
		parking.setOutSpaceId(allParkingModle.getBufferOrFeture(EXPORT).getId());
		parking.setOutTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId());
		// 更新数据
		allParkingModle.replaceParking(parking);
		offclock();
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
		ParkingEntity parking = allParkingModle.getPakringByCarId(carId);
		InitDataListener.parkings.remove(parking);
		parking.setGetTime(PublicMethods.getDate());
		ParkingSaveEntity parkingSave = new ParkingSaveEntity(parking);
		parkingSaveServiceImpl.insert(parkingSave);
		offclock();
	}

	/**
	 * vip用户取消存车的方法
	 * @param carId
	 */
	public void vipCancelSaveCar(String carId) throws InterruptedException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		ParkingEntity removeParking = allParkingModle.getPakringByCarId(carId);
		SpaceEntity spaceForApp = allParkingModle.getSpaceById(removeParking.getInSpaceId());
		SpaceEntity spaceForSave = allParkingModle.getSpaceById(removeParking.getSaveSpaceId());
		spaceForApp.setCarId(null);
		spaceForApp.setNature(contrastServiceImpl.getContrastByRealName(BUFFER_VACANCY).getId());
		spaceForSave.setCarId(null);
		spaceForSave.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
		allParkingModle.replaceSpace(spaceForApp);
		allParkingModle.replaceSpace(spaceForSave);
		InitDataListener.parkings.remove(removeParking);
		offclock();
	}

	/**
	 * vip用户立即取车方法
	 * @param carId
	 */
	public void vipTakeOutCarNow(String carId) throws ParseException, InterruptedException {
		String clockId = PublicMethods.getId();
		onclock(clockId);
		ParkingEntity parkingOld = allParkingModle.getPakringByCarId(carId);
		ParkingEntity parking = parkingOld;
		// 修改数据
		parking.setOutTime(PublicMethods.getDate());
		parking.setNature(contrastServiceImpl.getContrastByRealName(VIP_GETCAR).getId());
		// 更新数据
		allParkingModle.replaceParking(parking);
		offclock();
	}
}
